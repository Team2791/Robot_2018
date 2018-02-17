package org.usfirst.frc.team2791.robot.subsystems;


import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import org.usfirst.frc.team2791.robot.Robot;
import org.usfirst.frc.team2791.robot.RobotMap;
import org.usfirst.frc.team2791.robot.commands.drivetrain.DriveWithJoystick;
import org.usfirst.frc.team2791.robot.util.Constants;
import org.usfirst.frc.team2791.robot.util.Util;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.BaseMotorController;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/**
 * This class corresponds to the drivetrain. This code is modeled after a drivetrain with six motors, four Spark and two tallon speed controllers.
 * (Note: Talon and Spark speed controllers work off of the same code.) The controls are done for a tank drive system.
 * In terms, of sensors, this subsystem assumed one encoder on each side of the drivetrain and a gyro within the robot.
 * This code works with the ADXRS450 gyro.
 *
 * @author team2791: See Robot.java for contact info
 */

public class ShakerDrivetrain extends Subsystem{


	boolean inDriveMode = true;
	// Victor speed controllers can be controlled with the WPI Talon class.
	private VictorSPX victorLeft1, victorLeft2;
	private TalonSRX talonLeft3;
	private VictorSPX victorRight1, victorRight2;
	private TalonSRX  talonRight3;
	private BaseMotorController[] leftDrive;
	private BaseMotorController[] rightDrive;
	
	private DoubleSolenoid shiftingSolenoid;

	private ADXRS450_Gyro gyro;
	private boolean gyroDisabled = false;


	//The next three initialization sections are for encoder and gyro helpers.
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

	//Determines the amount of distance traveled for every pulse read on the encoders
	private double distancePerPulse = Util.tickToFeet(Constants.driveEncoderTicks, Constants.WHEEL_DIAMETER_IN_FEET);

	public ShakerDrivetrain(){
		victorLeft1 = new VictorSPX(RobotMap.VICTOR_LEFT_1);
		victorLeft2 = new VictorSPX(RobotMap.VICTOR_LEFT_2);
		talonLeft3 = new TalonSRX(RobotMap.TALON_LEFT_3);
		victorRight1 = new VictorSPX(RobotMap.VICTOR_RIGHT_1);
		victorRight2 = new VictorSPX(RobotMap.VICTOR_RIGHT_2);
		talonRight3 = new TalonSRX(RobotMap.TALON_RIGHT_3);

		leftDrive = new BaseMotorController[] {victorLeft1, victorLeft2, talonLeft3};
		rightDrive = new BaseMotorController[] {victorRight1, victorRight2, talonRight3};

		talonLeft3.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
		talonRight3.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
		
		shiftingSolenoid = new DoubleSolenoid(RobotMap.DRIVETRAIN_GEARBOX_SHIFTER_IN, RobotMap.DRIVETRAIN_GEARBOX_SHIFTER_OUT);
		setDriveOrRampMode(true); // set to drive mode
		
		
		//Inverts the motor outputs so that the right and left motors both turn the right direction for forward drive
		boolean letSideInverted = false;
		for(int i = 0; i < leftDrive.length; i++) {
			leftDrive[i].setInverted(letSideInverted);
			leftDrive[i].setNeutralMode(NeutralMode.Coast);
		}
		for(int i = 0; i < rightDrive.length; i++){
			rightDrive[i].setInverted(!letSideInverted);
			rightDrive[i].setNeutralMode(NeutralMode.Coast);
		}

		// stops all motors right away just in case
		disable();

		//keeps the gyro from throwing startCompetition() errors and allows us to troubleshoot errors
		try{
			gyro = new ADXRS450_Gyro();//SPI.Port.kOnboardCS1
			gyro.calibrate(); //takes 5 seconds
			gyro.reset();
		}catch(NullPointerException e){
			gyroDisabled = true;
			System.out.println("Gyro is unplugged, Disabling Gyro");
		}

	}

	public void initDefaultCommand() {
		setDefaultCommand(new DriveWithJoystick());
	}

	/**
	 * Stops the drivetrain
	 */
	public void disable() {
		for (int i = 0; i < leftDrive.length; i++){
			leftDrive[i].set(ControlMode.PercentOutput, 0.0);
		}
		for(int i = 0; i < rightDrive.length; i++){
			rightDrive[i].set(ControlMode.PercentOutput, 0.0);
		}
	}

	/**
	 * Drivetrain motor outputs; Accepts values between -1.0 and +1.0
	 * @param left motor output
	 * @param right motor output*/
	public void setLeftRightMotorOutputs(double left, double right){
		SmartDashboard.putString("LeftOutput vs RightOutput", left+":"+right);
		for(int i = 0; i < leftDrive.length; i++){
			leftDrive[i].set(ControlMode.PercentOutput, left);
		}
		for(int i = 0; i < rightDrive.length; i++){
			rightDrive[i].set(ControlMode.PercentOutput, right);
		}
	}
	
	/**
	 * inDriveMode == True ---> Drive
	 * inDriveMode == False -----> Ramp
	 * @param inDrivingMode
	 */
	public void setDriveOrRampMode(boolean inDrivingMode) {
		if(inDrivingMode) {
			inDriveMode = true;
			shiftingSolenoid.set(Value.kForward);
		} else {
			inDriveMode = false;
			shiftingSolenoid.set(Value.kReverse);
		}
	}

	public boolean getDriveOrRampMode(){
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

		SmartDashboard.putNumber("DT - Gyro angle", gyro.getAngle());
		SmartDashboard.putNumber("DT - Gyro rate", gyro.getRate());

		SmartDashboard.putNumber("DT - total current", getCurrentUsage());
//		SmartDashboard.putString("LDist vs RDist vs AvgDist", getLeftDistance()+":"+getRightDistance()+":"+getAverageDist());
//		SmartDashboard.putString("LVel vs RVel vs AvgVel", getLeftVelocity()+":"+getRightVelocity()+":"+getAverageVelocity());
		
		// these are commented out because we don't need them. They were created for motion profiling we are not using.
		// I'm leaving them in because we may use them in the future and I want to be explicit why we are not using them right now.
//		SmartDashboard.putNumber("Encoder Angle", getAngleEncoder());
//		SmartDashboard.putNumber("Avg Acceleration", getAverageAcceleration());
//		SmartDashboard.putString("LAcc vs RAcc vs AvgAcc", getLeftAcceleration()+":"+getRightAcceleration()+":"+getAverageAcceleration());

	}



	//************** Gyro and Encoder Helper Methods **************//

	@Deprecated
	public double getAngleEncoder() {
		return (360 / 7.9) * (getLeftDistance() - getRightDistance()) / 2.0;
	}

	public double getGyroAngle() {
		if(!gyroDisabled)
			return gyro.getAngle();
		System.err.println("Gyro is Disabled, Angle is Incorrect");
		return 0.0;
	}

	@Deprecated
	public double getEncoderAngleRate() {
		return (360/7.9) * (getLeftVelocity() - getRightVelocity()) / 2.0;
	}

	public double getGyroRate() {
		if(!gyroDisabled)
			return gyro.getRate();
		System.err.println("Gyro is Disabled, Rate is Incorrect");
		return 0.0;
	}

	public double getGyroAngleInRadians() {
		return getGyroAngle() * (Math.PI/180);
	}

	/**
	 * resets the left and right drive encoders;
	 * resets the gyro;
	 * stops the drivetrain
	 */
	public void reset() {
		disable();
		resetGyro();
		resetEncoders();
	}

	public void resetEncoders() {
		// zero the encoders
		talonLeft3.setSelectedSensorPosition(0, 0,0 );
		talonRight3.setSelectedSensorPosition(0, 0,0);
	}

	public void resetGyro() {
		if(!gyroDisabled) {
			gyro.reset();
		} else {
			System.err.println("Gyro is Disabled, Unable to Reset");
		}
	}

	public void calibrateGyro() {
		if(!gyroDisabled) {
			System.out.println("Gyro calibrating");
			gyro.calibrate();
			System.out.println("Done calibrating " + " The current rate is " + gyro.getRate());
		}
	}



	//************** Pos/Vel/Acc Helper Methods **************//

	/**
	 * @return distance traveled by left side based on encoder
	 */
	public double getLeftDistance() {
		return talonLeft3.getSelectedSensorPosition(0) * distancePerPulse;
	}

	/**
	 * @return distance traveled by right side based on encoder
	 */
	public double getRightDistance() {
		return talonRight3.getSelectedSensorPosition(0) * distancePerPulse;
	}

	/**@return average distance of both encoder velocities */
	public double getAverageDist() {
		return Util.average(getLeftDistance(), getRightDistance());
//		left side commented out due to potential wiring issues
		//return getLeftDistance();
	}

	public double getLeftVelocity() {
		return  talonLeft3.getSelectedSensorVelocity(0) * distancePerPulse * 10;// 10 to convert from milliseconds a
	}

	public double getRightVelocity() {
		return  talonRight3.getSelectedSensorVelocity(0) * distancePerPulse * 10;
	}

	/**@return average velocity of both encoder velocities */
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



	//*****************Debugging Methods*****************//

	/**
	 * @return total current usage for all 4 motors in the drivetrain
	 */
	public double getCurrentUsage() {
		return Robot.pdp.getCurrent(RobotMap.POWER_RIGHT_DRIVE_1) +
									Robot.pdp.getCurrent(RobotMap.POWER_RIGHT_DRIVE_2) +
									Robot.pdp.getCurrent(RobotMap.POWER_RIGHT_DRIVE_3) +
									Robot.pdp.getCurrent(RobotMap.POWER_LEFT_DRIVE_1) + 
									Robot.pdp.getCurrent(RobotMap.POWER_LEFT_DRIVE_2) +
									Robot.pdp.getCurrent(RobotMap.POWER_LEFT_DRIVE_3);
	}
}
