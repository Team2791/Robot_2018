package org.usfirst.frc.team2791.robot.subsystems;

import java.io.File;

import org.usfirst.frc.team2791.robot.Constants;
import org.usfirst.frc.team2791.robot.Constants.DrivetrainProfiling;
import org.usfirst.frc.team2791.robot.Robot;
import org.usfirst.frc.team2791.robot.RobotMap;
import org.usfirst.frc.team2791.robot.commands.drivetrain.DriveWithJoystick;
import org.usfirst.frc.team2791.robot.util.Util;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.BaseMotorController;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.followers.EncoderFollower;
import jaci.pathfinder.modifiers.TankModifier;

/**
 * This class corresponds to the drivetrain. This code is modeled after a
 * drivetrain with six motors, four Spark and two tallon speed controllers.
 * (Note: Talon and Spark speed controllers work off of the same code.) The
 * controls are done for a tank drive system. In terms, of sensors, this
 * subsystem assumed one encoder on each side of the drivetrain and a gyro
 * within the robot. This code works with the ADXRS450 gyro.
 *
 * @author team2791: See Robot.java for contact info
 */

public class ShakerDrivetrain extends Subsystem {

	private double DRIVE_OUTPUT_SCALING_FACTOR = 1.0;
	boolean inDriveMode = true;
	// Victor speed controllers can be controlled with the WPI Talon class.
	private VictorSPX victorLeft1;// , victorLeft2;
	private TalonSRX talonLeft2;
	private VictorSPX victorRight1;// , victorRight2;
	private TalonSRX talonRight2;
	private BaseMotorController[] leftDrive;
	private BaseMotorController[] rightDrive;

	private DoubleSolenoid shiftingSolenoid;

	private ADXRS450_Gyro gyro;
	private boolean gyroDisabled = false;

	// The next three initialization sections are for encoder and gyro helpers.
	protected double previousRate = 0;
	protected double previousRateTime = 0;
	protected double currentRate = 0;
	protected double currentTime = 0;

	protected double leftPreviousRate = 0;
	protected double leftPreviousTime = 0;
	protected double leftCurrentRate = 0;
	protected double leftCurrentTime = 0;
	protected double leftFilteredAccel = 0;

	protected double rightPreviousRate = 0;
	protected double rightPreviousTime = 0;
	protected double rightCurrentRate = 0;
	protected double rightCurrentTime = 0;
	protected double rightFilteredAccel = 0;

	// Determines the amount of distance traveled for every pulse read on the
	// encoders
	private double distancePerPulse = Util.tickToFeet(Constants.driveEncoderTicks, Constants.WHEEL_DIAMETER_IN_IN);

	private boolean isProfileFinished = false;

	public ShakerDrivetrain() {
		victorLeft1 = new VictorSPX(RobotMap.VICTOR_LEFT_1);
		talonLeft2 = new TalonSRX(RobotMap.TALON_LEFT_2);
		victorRight1 = new VictorSPX(RobotMap.VICTOR_RIGHT_1);
		talonRight2 = new TalonSRX(RobotMap.TALON_RIGHT_2);

		leftDrive = new BaseMotorController[] { victorLeft1, talonLeft2 };
		rightDrive = new BaseMotorController[] { victorRight1, talonRight2 };

		talonLeft2.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
		talonRight2.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);

		shiftingSolenoid = new DoubleSolenoid(RobotMap.PCM_CAN_ID, RobotMap.DRIVETRAIN_GEARBOX_SHIFTER_IN,
				RobotMap.DRIVETRAIN_GEARBOX_SHIFTER_OUT);
		setDriveOrRampMode(true); // set to drive mode

		// Inverts the motor outputs so that the right and left motors both turn the
		// right direction for forward drive
		boolean letSideInverted = false;
		for (int i = 0; i < leftDrive.length; i++) {
			leftDrive[i].setInverted(letSideInverted);
			leftDrive[i].enableVoltageCompensation(true);
			leftDrive[i].configVoltageCompSaturation(12, 20);
		}
		for (int i = 0; i < rightDrive.length; i++) {
			rightDrive[i].setInverted(!letSideInverted);
			rightDrive[i].enableVoltageCompensation(true);
			rightDrive[i].configVoltageCompSaturation(12, 20);
		}
		setBrakeMode(false);

		setLeftRightMotorOutputs(0, 0);

		// keeps the gyro from throwing startCompetition() errors and allows us to
		// troubleshoot errors
		try {
			gyro = new ADXRS450_Gyro();// SPI.Port.kOnboardCS1
			gyro.calibrate(); // takes 5 seconds
			gyro.reset();
			System.out.println("Gyro is working! :-)");
		} catch (NullPointerException e) {
			gyroDisabled = true;
			System.out.println("Gyro is unplugged, Disabling Gyro");
		}

	}

	public void initDefaultCommand() {
		setDefaultCommand(new DriveWithJoystick());
	}

	public void setBrakeMode(boolean inBrakeMode) {
		NeutralMode mode = inBrakeMode ? NeutralMode.Brake : NeutralMode.Coast;
		for (int i = 0; i < leftDrive.length; i++) {
			leftDrive[i].setNeutralMode(mode);
		}
		for (int i = 0; i < rightDrive.length; i++) {
			rightDrive[i].setNeutralMode(mode);
		}
	}

	public void setVoltageRampRate(double rampTime) {
		for (int i = 0; i < leftDrive.length; i++) {
			leftDrive[i].configOpenloopRamp(rampTime, 3);
		}
		for (int i = 0; i < rightDrive.length; i++) {
			rightDrive[i].configOpenloopRamp(rampTime, 3);
		}
	}

	/**
	 * Drivetrain motor outputs; Accepts values between -1.0 and +1.0
	 * 
	 * @param left
	 *            motor output
	 * @param right
	 *            motor output
	 */
	public void setLeftRightMotorOutputs(double left, double right) {
		SmartDashboard.putString("LeftOutput vs RightOutput", left + ":" + right);
		for (int i = 0; i < leftDrive.length; i++) {
			leftDrive[i].set(ControlMode.PercentOutput, left * DRIVE_OUTPUT_SCALING_FACTOR);
		}
		for (int i = 0; i < rightDrive.length; i++) {
			rightDrive[i].set(ControlMode.PercentOutput, right * DRIVE_OUTPUT_SCALING_FACTOR);
		}
	}

	/**
	 * inDriveMode == True ---> Drive inDriveMode == False -----> Ramp
	 * 
	 * @param inDrivingMode
	 */
	public void setDriveOrRampMode(boolean inDrivingMode) {
		this.inDriveMode = inDrivingMode;
		if (inDrivingMode) {
			inDriveMode = true;
			shiftingSolenoid.set(Value.kForward);
		} else {
			inDriveMode = false;
			shiftingSolenoid.set(Value.kReverse);
		}
	}

	public boolean isDrivetrainInDriveMode() {
		return inDriveMode;
	}

	/**
	 * Drivetrain sfx outputs
	 */
	public void debug() {
		SmartDashboard.putNumber("DT - Left Encoders Rate", getLeftVelocity());
		SmartDashboard.putNumber("DT - Right Encoders Rate", getRightVelocity());

		SmartDashboard.putNumber("DT - Left Encoder Distance", getLeftDistance());
		SmartDashboard.putNumber("DT - Right Encoder Distance", getRightDistance());
		SmartDashboard.putNumber("DT - Avg Encoder Distance", getAverageDist());

		SmartDashboard.putNumber("DT - Left Encoder Ticks", getEncoderRawLeft());
		SmartDashboard.putNumber("DT - Right Encoder Ticks", getEncoderRawRight());
//		SmartDashboard.putNumber("DT - Left Encoder Distance Meters", this.getLeftDistanceMet());
//		SmartDashboard.putNumber("DT - Right Encoder Distance Meters", this.getRightDistanceMet());

		SmartDashboard.putNumber("DT - Gyro angle", gyro.getAngle());
		SmartDashboard.putNumber("DT - Gyro rate", gyro.getRate());
		DrivetrainProfiling.sendToDashboardPIDG(DrivetrainProfiling.kp, DrivetrainProfiling.ki, DrivetrainProfiling.kd,
				DrivetrainProfiling.gp, DrivetrainProfiling.gd);

		// we are getting a lot of CAN recieve timeout errors. This is a pain in the
		// rear.
		// SmartDashboard.putNumber("DT - total current", getCurrentUsage());

		// SmartDashboard.putString("LDist vs RDist vs AvgDist",
		// getLeftDistance()+":"+getRightDistance()+":"+getAverageDist());
		// SmartDashboard.putString("LVel vs RVel vs AvgVel",
		// getLeftVelocity()+":"+getRightVelocity()+":"+getAverageVelocity());

		// these are commented out because we don't need them. They were created for
		// motion profiling we are not using.
		// I'm leaving them in because we may use them in the future and I want to be
		// explicit why we are not using them right now.
		// SmartDashboard.putNumber("Encoder Angle", getAngleEncoder());
//		 SmartDashboard.putNumber("DT - Avg Acceleration", getAverageAcceleration());
		// SmartDashboard.putString("LAcc vs RAcc vs AvgAcc",
		// getLeftAcceleration()+":"+getRightAcceleration()+":"+getAverageAcceleration());
		 
		 SmartDashboard.putBoolean("Pathfinder -Path Finished", Robot.drivetrain.isProfileFinished);

	}

	public static void putPIDGainsOnSmartDash() {
		SmartDashboard.putNumber("PID - Stat Angle P", Constants.STATIONARY_ANGLE_P);
		SmartDashboard.putNumber("PID - Stat Angle I", Constants.STATIONARY_ANGLE_I);
		SmartDashboard.putNumber("PID - Stat Angle D", Constants.STATIONARY_ANGLE_D);
		SmartDashboard.putNumber("PID - Moving Angle P", Constants.DRIVE_ANGLE_P);
		SmartDashboard.putNumber("PID - Moving Angle I", Constants.DRIVE_ANGLE_I);
		SmartDashboard.putNumber("PID - Moving Angle D", Constants.DRIVE_ANGLE_D);
		SmartDashboard.putNumber("PID - Distance P", Constants.DRIVE_DISTANCE_P);
		SmartDashboard.putNumber("PID - Distance I", Constants.DRIVE_DISTANCE_I);
		SmartDashboard.putNumber("PID - Distance D", Constants.DRIVE_DISTANCE_D);

		SmartDashboard.putNumber("TUNE PID Distance", 0.0);
		SmartDashboard.putNumber("TUNE PID Stat Angle", 0.0);

		SmartDashboard.putNumber("PID - Stationary Angle Error", 0);
		SmartDashboard.putNumber("PID - Stationary Angle Output", 0);
		SmartDashboard.putNumber("PID - Moving Angle Error", 0);
		SmartDashboard.putNumber("PID - Moving Angle Output", 0);
		SmartDashboard.putNumber("PID - Distance output", 0);
		SmartDashboard.putNumber("PID - Distance error", 0);

	}

	// ************** Gyro and Encoder Helper Methods **************//

	@Deprecated
	public double getAngleEncoder() {
		return (360 / 7.9) * (getLeftDistance() - getRightDistance()) / 2.0;
	}

	public double getGyroAngle() {
		if (!gyroDisabled)
			return gyro.getAngle();
		System.err.println("Gyro is Disabled, Angle is Incorrect");
		return 0.0;
	}

	@Deprecated
	public double getEncoderAngleRate() {
		return (360 / 7.9) * (getLeftVelocity() - getRightVelocity()) / 2.0;
	}

	public double getGyroRate() {
		if (!gyroDisabled)
			return gyro.getRate();
		System.err.println("Gyro is Disabled, Rate is Incorrect");
		return 0.0;
	}

	public double getGyroAngleInRadians() {
		return getGyroAngle() * (Math.PI / 180);
	}

//	/**
//	 * resets the left and right drive encoders; resets the gyro; stops the
//	 * drivetrain
//	 */
//	public void reset() {
//		disable();
//		resetGyro();
//		resetEncoders();
//	}

	public void resetEncoders() {
		// zero the encoders
		talonLeft2.setSelectedSensorPosition(0, 0, 0);
		talonRight2.setSelectedSensorPosition(0, 0, 0);
		victorLeft1.setSelectedSensorPosition(0, 0, 0);
		victorRight1.setSelectedSensorPosition(0, 0, 0);
	}

	public void resetGyro() {
		if (!gyroDisabled) {
			gyro.reset();
		} else {
			System.err.println("Gyro is Disabled, Unable to Reset");
		}
	}

	public boolean getGyroDisabled() {
		return gyroDisabled;
	}

	public void calibrateGyro() {
		if (!gyroDisabled) {
			System.out.println("Gyro calibrating");
			gyro.calibrate();
			System.out.println("Done calibrating " + " The current rate is " + gyro.getRate());
		}
	}

	// ************** Pos/Vel/Acc Helper Methods **************//

	/**
	 * @return distance traveled by left side based on encoder
	 */
	public double getLeftDistance() {
		return talonLeft2.getSelectedSensorPosition(0) * distancePerPulse;
	}

	public double getLeftDistanceMet() {
		return getLeftDistance() * Constants.InchesToMeters;
	}

	public double getRightDistanceMet() {
		return getRightDistance() * Constants.InchesToMeters;
	}

	/**
	 * @return distance traveled by right side based on encoder
	 */
	public double getRightDistance() {
		return talonRight2.getSelectedSensorPosition(0) * distancePerPulse;
	}

	/** @return average distance of both encoder velocities */
	public double getAverageDist() {
		return Util.average(getLeftDistance(), getRightDistance());
		// right side commented out due to potential wiring issues
		// return getLeftDistance();
	}

	public double getLeftVelocity() {
		return talonLeft2.getSelectedSensorVelocity(0) * distancePerPulse * 10;// units/.1s  to units/1s
	}
	
	public double getLeftVelocityMet() {
		return getLeftVelocity() * Constants.InchesToMeters;
	}

	public double getRightVelocity() {
		return talonRight2.getSelectedSensorVelocity(0) * distancePerPulse * 10;
	}
	
	public double getRightVelocityMet() {
		return getRightVelocity() * Constants.InchesToMeters;
	}

	/** @return average velocity of both encoder velocities */
	public double getAverageVelocity() {
		return Util.average(getLeftVelocity(), getRightVelocity());
	}

	public double getLeftAcceleration() {
		leftCurrentRate = getLeftVelocity();
		leftCurrentTime = Timer.getFPGATimestamp();

		double acceleration = (leftCurrentRate - leftPreviousRate) / (leftCurrentTime - leftPreviousTime);

		leftPreviousRate = leftCurrentRate;
		leftPreviousTime = leftCurrentTime;

		leftFilteredAccel = acceleration * 0.5 + leftFilteredAccel * 0.5;

		return leftFilteredAccel;
	}

	public double getRightAcceleration() {
		rightCurrentRate = getLeftVelocity();
		rightCurrentTime = Timer.getFPGATimestamp();

		double acceleration = (rightCurrentRate - rightPreviousRate) / (rightCurrentTime - rightPreviousTime);

		rightPreviousRate = rightCurrentRate;
		rightPreviousTime = rightCurrentTime;

		rightFilteredAccel = acceleration * 0.5 + rightFilteredAccel * 0.5;

		return rightFilteredAccel;
	}

	public double getAverageAcceleration() {
		currentRate = getAverageVelocity();
		currentTime = Timer.getFPGATimestamp();

		double acceleration = (currentRate - previousRate) / (currentTime - previousRateTime);

		previousRate = currentRate;
		previousRateTime = currentTime;

		return acceleration;
	}

	// *****************Debugging Methods*****************//

	/**
	 * @return total current usage for all 4 motors in the drivetrain
	 */
	public double getCurrentUsage() {
		return Robot.pdp.getCurrent(RobotMap.POWER_RIGHT_DRIVE_1) + Robot.pdp.getCurrent(RobotMap.POWER_RIGHT_DRIVE_2)
				+ Robot.pdp.getCurrent(RobotMap.POWER_RIGHT_DRIVE_3) + Robot.pdp.getCurrent(RobotMap.POWER_LEFT_DRIVE_1)
				+ Robot.pdp.getCurrent(RobotMap.POWER_LEFT_DRIVE_2) + Robot.pdp.getCurrent(RobotMap.POWER_LEFT_DRIVE_3);
	}

	// **********************Trajectory***********************//

	// if the robot is going in reverse, it means that we either double negated
	// something, or didn't negate enough things
	// flipping the sign here should solve that
	public int getEncoderRawLeft() {
		return this.talonLeft2.getSelectedSensorPosition(0);
	}

	// if the robot is going in reverse, it means that we either double negated
	// something, or didn't negate enough things
	// flipping the sign here should solve that
	public int getEncoderRawRight() {
		return this.talonRight2.getSelectedSensorPosition(0);
	}

	public EncoderFollower[] pathSetup(Waypoint[] path) {

		// create a new set of follower for the new path
		EncoderFollower left = new EncoderFollower();
		EncoderFollower right = new EncoderFollower();
		Trajectory.Config cfg = new Trajectory.Config(Trajectory.FitMethod.HERMITE_QUINTIC,
				Trajectory.Config.SAMPLES_HIGH, DrivetrainProfiling.dt,
				DrivetrainProfiling.max_velocity,
				DrivetrainProfiling.max_acceleration, DrivetrainProfiling.max_jerk);

		// Fast Path calculations to find the toFollow Trajectories
		String pathHash = String.valueOf(Util.generatePathHashCode(path));
		SmartDashboard.putString("Path Hash", pathHash);
		Trajectory toFollow;// = Pathfinder.generate(path, cfg);

		// create the csv file for the paths if they haven't already been created
//		File trajectory = new File("/home/lvuser/paths/" + pathHash + ".csv");
//		if (!trajectory.exists()) {
		// Until the paths are final don't do this!!
			toFollow = Pathfinder.generate(path, cfg);
//			Pathfinder.writeToCSV(trajectory, toFollow);
//			System.out.println(pathHash + ".csv not found, wrote to file");
//		} else {
//			System.out.println(pathHash + ".csv read from file");
//			toFollow = Pathfinder.readFromCSV(trajectory);
//		}

		TankModifier modifier = new TankModifier(toFollow)
				.modify((DrivetrainProfiling.wheel_base_width));

		DrivetrainProfiling.last_gyro_error = 0.0;
		left = new EncoderFollower(modifier.getLeftTrajectory());
		right = new EncoderFollower(modifier.getRightTrajectory());
		left.configureEncoder(getEncoderRawLeft(), DrivetrainProfiling.ticks_per_rev,
				DrivetrainProfiling.wheel_diameter);
		right.configureEncoder(getEncoderRawRight(), DrivetrainProfiling.ticks_per_rev,
				DrivetrainProfiling.wheel_diameter);
		
		left.configurePIDVA(DrivetrainProfiling.kp, DrivetrainProfiling.ki, DrivetrainProfiling.kd,
				DrivetrainProfiling.kv, DrivetrainProfiling.ka);
		right.configurePIDVA(DrivetrainProfiling.kp, DrivetrainProfiling.ki, DrivetrainProfiling.kd,
				DrivetrainProfiling.kv, DrivetrainProfiling.ka);

		return new EncoderFollower[] { left, // 0
				right, // 1
		};
	}

	public void resetForPath() {
		isProfileFinished = false;
		setBrakeMode(true);
		resetEncoders();
	}

	public void resetPathAngleOffset() {
		DrivetrainProfiling.path_angle_offset = 0.0;
	}

	public boolean getIsProfileFinished() {
		return isProfileFinished;
	}

	public void pathFollow(EncoderFollower[] followers, boolean reverse) {

		EncoderFollower left = followers[0];
		EncoderFollower right = followers[1];
		double l;
		double r;
		double localGp = DrivetrainProfiling.gp;
		
		if (!reverse) {
			l = left.calculate(getEncoderRawLeft());
			r = right.calculate(getEncoderRawRight());
		} else {
			localGp *= -1;
			l = left.calculate(-getEncoderRawLeft());
			r = right.calculate(-getEncoderRawRight());
		}

		//
		double gyro_heading = !reverse ? -getGyroAngle() - DrivetrainProfiling.path_angle_offset
				: getGyroAngle() + DrivetrainProfiling.path_angle_offset;

		double angle_setpoint = Pathfinder.r2d(left.getHeading());
		SmartDashboard.putNumber("Angle setpoint", angle_setpoint);
		double angleDifference = Pathfinder.boundHalfDegrees(angle_setpoint - gyro_heading);
		SmartDashboard.putNumber("Angle difference", angleDifference);

		double turn = localGp * angleDifference + (DrivetrainProfiling.gd
				* ((angleDifference - DrivetrainProfiling.last_gyro_error) / DrivetrainProfiling.dt));

		DrivetrainProfiling.last_gyro_error = angleDifference;

		if (left != null && !left.isFinished()) {

			SmartDashboard.putNumber("Pathfinder - Left error dist", this.getLeftDistanceMet() - left.getSegment().x);
			SmartDashboard.putNumber("Pathfinder - Left error vel", this.getLeftVelocityMet() - left.getSegment().velocity);
			SmartDashboard.putNumber("Pathfinder - Left Distance Meters", left.getSegment().x);
			SmartDashboard.putNumber("Pathfinder - Left set vel", left.getSegment().velocity);
			SmartDashboard.putNumber("Pathfinder - Left set pos", left.getSegment().x);
			SmartDashboard.putNumber("Pathfinder - Left calc voltage", l);
			SmartDashboard.putNumber("Pathfinder - Left Commanded seg heading", left.getHeading()*Constants.RADIANS_TO_DEGREES);
			SmartDashboard.putNumber("Pathfinder - Left + turn", l + turn);
			SmartDashboard.putNumber("Pathfinder - Left seg acceleration", left.getSegment().acceleration);
			
			SmartDashboard.putNumber("Pathfinder - Right error dist", this.getRightDistanceMet() - right.getSegment().x);
			SmartDashboard.putNumber("Pathfinder - Right error vel", this.getRightVelocityMet() - right.getSegment().velocity);
			SmartDashboard.putNumber("Pathfinder - Right Distance Meters", right.getSegment().x);
			SmartDashboard.putNumber("Pathfinder - Right set vel", right.getSegment().velocity);
			SmartDashboard.putNumber("Pathfinder - Right set pos", right.getSegment().x);
			SmartDashboard.putNumber("Pathfinder - Right calc voltage", r);
			SmartDashboard.putNumber("Pathfinder - Right Commanded seg heading degrees", right.getHeading()*Constants.RADIANS_TO_DEGREES);
			SmartDashboard.putNumber("Pathfinder - Right - turn", r - turn);
			SmartDashboard.putNumber("Pathfinder - Right seg acceleration", right.getSegment().acceleration);

			SmartDashboard.putNumber("Pathfinder - Path angle offset", DrivetrainProfiling.path_angle_offset);
			SmartDashboard.putNumber("Pathfinder - Angle offset w/ new path angle offset",
					angleDifference + DrivetrainProfiling.path_angle_offset);
		
			SmartDashboard.putNumber("Pathfinder - Angle Error", getGyroAngle() - left.getHeading()*Constants.RADIANS_TO_DEGREES);
		}
		
		if (!reverse) {
			setLeftRightMotorOutputs(l - turn, r + turn);
		} else {
			setLeftRightMotorOutputs(-l - turn, -r + turn);
		}

		if (left.isFinished() && right.isFinished()) {
			System.out.println("DT Finished driving path");
			isProfileFinished = true;
			DrivetrainProfiling.path_angle_offset = angleDifference;
			return;
		}
	}

}
