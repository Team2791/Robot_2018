package org.usfirst.frc.team2791.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import org.usfirst.frc.team2791.robot.Robot;
import org.usfirst.frc.team2791.robot.RobotMap;
import org.usfirst.frc.team2791.robot.commands.drivetrain.DriveWithJoystick;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team2791.robot.util.Constants;

/**
 * This class corresponds to the drivetrain. The drivetrain is 6 wheel, center drop with 2 CIMs, 1 miniCIM on each
 * side. Each motor output is controlled by a Spark speed controller. The Rio outputs signal through a single PWM 
 * per side which is branched off to all three speed controllers. There is a 256 count Greyhill encoder on each side.
 * We have an ADXRS453 gyro in the robot (writing side up). Max velocity ~15 feet/sec.
 * 
 * @author team2791: See Robot.java for contact info
 */
public class ShakerDrivetrain extends Subsystem{

	// Victor speed controllers can be controlled with the WPI Talon class.
	private VictorSPX victorLeft1, victorLeft2, victorLeft3;
	private VictorSPX victorRight1, victorRight2, victorRight3;
	private VictorSPX[] leftDrive;
	private VictorSPX[] rightDrive;

	public Encoder leftDriveEncoder;
	public Encoder rightDriveEncoder;

	public ADXRS450_Gyro gyro;
	public boolean gyroDisabled = false;


	
	//Determines the amount of distance traveled for every pulse read on the encoders
//	private double distancePerPulse = Util.tickToFeet(CONSTANTS.driveEncoderTicks, CONSTANTS.WHEEL_DIAMETER_IN_FEET);

	public ShakerDrivetrain(){
		victorLeft1 = new VictorSPX(RobotMap.VICTOR_LEFT_1);
		victorLeft2 = new VictorSPX(RobotMap.VICTOR_LEFT_2);
		victorLeft3 = new VictorSPX(RobotMap.VICTOR_LEFT_3);
		victorRight1 = new VictorSPX(RobotMap.VICTOR_RIGHT_1);
		victorRight2 = new VictorSPX(RobotMap.VICTOR_RIGHT_2);
		victorRight3 = new VictorSPX(RobotMap.VICTOR_RIGHT_3);

		leftDrive = new VictorSPX[] {victorLeft1, victorLeft2, victorLeft3};
		rightDrive = new VictorSPX[] {victorRight1, victorRight2, victorRight3};

		leftDriveEncoder = new Encoder(RobotMap.LEFT_DRIVE_ENCODER_PORT_A, RobotMap.LEFT_DRIVE_ENCODER_PORT_B);
		rightDriveEncoder = new Encoder(RobotMap.RIGHT_DRIVE_ENCODER_PORT_A,RobotMap.RIGHT_DRIVE_ENCODER_PORT_B);

		
		//Inverts the motor outputs so that the right and left motors both turn the right direction for forward drive
		boolean letSideInverted = true;
		for(int i = 0; i < leftDrive.length; i++) {
			leftDrive[i].setInverted(letSideInverted);
		}
		for(int i = 0; i < rightDrive.length; i++){
			rightDrive[i].setInverted(!letSideInverted);
		}

		// stops all motors right away just in case
		disable();

		leftDriveEncoder.reset();
		rightDriveEncoder.reset();

		//configures the encoder so that it can be used in drive PID functions
//		leftDriveEncoder.setDistancePerPulse(distancePerPulse); 
//		rightDriveEncoder.setDistancePerPulse(-distancePerPulse); 

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
	
	/* // inDriveMode == True ---> Drive, inDriveMode == False ---> Ramp
	void setDriveOrRampMode(boolean inDriveMode, double time) {
		// change gear only if time is larger than Constants.RAMP_RELEASE_TIME
		// I'm thinking of making a command for this
		if (time >= Constants.RAMP_RELEASE_TIME) {
			if (inDriveMode) {
				System.out.println("Setting Gear Mode to Drive");
			} else if (!inDriveMode) {
				System.out.println("Setting Gear Mode to Ramp");
			}
		}
	}*/
//
//	/**
//	 * Sets PID values for Stationary Angle, Moving Angle, and Distance for use in auto
//	 */
//	public void setAutoPID(){
//		if(SmartDashboard.getNumber("kp", -2791) == -2791){
//			SmartDashboard.putNumber("kp",7.0);
//			SmartDashboard.putNumber("ki",0);
//			SmartDashboard.putNumber("kd",0.25);
//			SmartDashboard.putNumber("kv",0.09);
//			SmartDashboard.putNumber("ka",0.033);
//		}
//		
//		SmartDashboard.putNumber("Stat Angle P", CONSTANTS.STATIONARY_ANGLE_P);
//		SmartDashboard.putNumber("Stat Angle I", CONSTANTS.STATIONARY_ANGLE_I);
//		SmartDashboard.putNumber("Stat Angle D", CONSTANTS.STATIONARY_ANGLE_D);
//		SmartDashboard.putNumber("Moving Angle P", CONSTANTS.DRIVE_ANGLE_P);
//		SmartDashboard.putNumber("Moving Angle I", CONSTANTS.DRIVE_ANGLE_I);
//		SmartDashboard.putNumber("Moving Angle D", CONSTANTS.DRIVE_ANGLE_D);
//		SmartDashboard.putNumber("Distance P", CONSTANTS.DRIVE_DISTANCE_P);
//		SmartDashboard.putNumber("Distance I", CONSTANTS.DRIVE_DISTANCE_I);
//		SmartDashboard.putNumber("Distance D", CONSTANTS.DRIVE_DISTANCE_D);
//		
//		SmartDashboard.putNumber("TUNE PID Distance", 0.0);
//		SmartDashboard.putNumber("TUNE PID Stat Angle", 0.0);
//	}
//	
	/**
	 * Drivetrain sfx outputs
	 */
	public void debug() {

		SmartDashboard.putNumber("Left Drive Encoders Rate", leftDriveEncoder.getRate());
		SmartDashboard.putNumber("Right Drive Encoders Rate", rightDriveEncoder.getRate());
		SmartDashboard.putNumber("Encoder Angle", getAngleEncoder());

		SmartDashboard.putNumber("LEncoderDistance", leftDriveEncoder.getDistance());
		SmartDashboard.putNumber("REncoderDistance", rightDriveEncoder.getDistance());
		SmartDashboard.putNumber("AvgEncoderDistance", getAverageDist());

		SmartDashboard.putNumber("Gyro angle", gyro.getAngle());
		SmartDashboard.putNumber("Gyro rate", gyro.getRate());

//		SmartDashboard.putNumber("Avg Acceleration", getAverageAcceleration());
		
		SmartDashboard.putNumber("Drivetrain total current", getCurrentUsage());

//		SmartDashboard.putNumber("shooting drive P",CONSTANTS.shootingDriveP);

		SmartDashboard.putString("LDist vs RDist vs AvgDist", getLeftDistance()+":"+getRightDistance()+":"+getAverageDist());
		SmartDashboard.putString("LVel vs RVel vs AvgVel", getLeftVelocity()+":"+getRightVelocity()+":"+getAverageVelocity());
//		SmartDashboard.putString("LAcc vs RAcc vs AvgAcc", getLeftAcceleration()+":"+getRightAcceleration()+":"+getAverageAcceleration());
		
	}
	
	//************** Gyro and Encoder Helper Methods **************//

	public double getAngleEncoder() {
		return (360 / 7.9) * (getLeftDistance() - getRightDistance()) / 2.0;
	}

	public double getGyroAngle() {
		if(!gyroDisabled)  
			return gyro.getAngle();
		System.err.println("Gyro is Disabled, Angle is Incorrect");
		return 0.0;
	}
	
	public double getEncoderAngleRate() {
		return (360/7.9) * (leftDriveEncoder.getRate() - rightDriveEncoder.getRate()) / 2.0;
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
		leftDriveEncoder.reset();
		rightDriveEncoder.reset();
	}
	public void resetGyro() {
		if(!gyroDisabled)
			gyro.reset();
		else{
			System.err.println("Gyro is Disabled, Unable to Reset");
		}
	}
	
	public void calibrateGyro() {
		if(!gyroDisabled){
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
		return leftDriveEncoder.getDistance();
	}
	
	/**
	 * @return distance traveled by right side based on encoder
	 */
	public double getRightDistance() {
		return rightDriveEncoder.getDistance();
	}

	/**@return average distance of both encoder velocities */
	public double getAverageDist() {
		return (getLeftDistance() + getRightDistance()) / 2;
//		return getLeftDistance();
	}
	
	public double getLeftVelocity() {
		return leftDriveEncoder.getRate();
	}

	public double getRightVelocity() {
		return rightDriveEncoder.getRate();
	}

	/**@return average velocity of both encoSder velocities */
	public double getAverageVelocity() {
		return (getLeftVelocity() + getRightVelocity()) / 2;
	}
	
	// THESE METHODS ARE IN THE 2017 code!!
	public void getLeftAcceleration() {}	
	public void getRightAcceleration() {}	
	public void getAverageAcceleration() {}

	
	
	//*****************Debugging Methods*****************//
	
	/**
	 * @return total current usage for all 6 motors in the drivetrain
	 */
	public double getCurrentUsage() {
		double totalCurrent = 0.0;
		for(int i=0; i<=2; i++){
			totalCurrent += Robot.pdp.getCurrent(i);
		}
		for(int i=13; i<=15; i++){
			totalCurrent += Robot.pdp.getCurrent(i);
		}
		return totalCurrent;
	}

}
