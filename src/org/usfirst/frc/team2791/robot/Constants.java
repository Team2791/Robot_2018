package org.usfirst.frc.team2791.robot;

/*
 * This should have all the project's important constants
 * Currently, all the constants are taken from last year
 * so adjustments will be needed when the robot is made
 */

public class Constants {

	// Server Constants
	public static final String TEAM_NUMBER = "2791";
	public static final String SERVER_PORT = "roboRIO-" + TEAM_NUMBER + "-FRC";

	//Lift Constants

	// Pbot offset = -1.13
	// flight bot offset = -0.05
    public static final double LIFT_POT_OFFSET = -0.05; 
	//at 60% the lift was at 36'' from the ground, 33'' from the bottom of it's travel
    public static final double LIFT_POT_FULL_RANGE = 33.0 / 0.6;
    
    public static final double FAR_AWAY_DISTANCE = 5;
    public static final double CLOSE_DISTANCE = 1;
    public static final double FAR_AWAY_POWER = 1;
    public static final double CLOSE_POWER = .25;
    
    public static final double LIFT_MAX_HEIGHT = 39-1; // -1 for safety
    public static final double LIFT_MIN_HEIGHT = .25 + 1; // +1 for safety
    
    public static final double BOTTOM_SAFTEY_DISTANCE = 2.5;
    public static final double TOP_SAFTEY_DISTANCE = 12;
    public static final double MANUAL_POWER = 0.3;

	// Joystick constants
	public static final double DEADZONE = 0.05;
	
	// Drive train constants
	public static double driveEncoderTicks = 256;
	public static final double SPEED_MULTIPLIER = 0.7;
	public static final double WHEEL_DIAMETER_IN_FEET = 1.0;
	public static final double RAISE_RAMPS_SPEED = 0.8;

	// Time when ramps are allowed to be released
	// (3 * 60) - 30 = (3 minutes * 60 seconds) - 30 seconds
	public static final double RAMP_RELEASE_TIME = (3 * 60) - 30;

	// Limelight Driving Constants
	public static final double LIMELIGHT_TURNING_KP = 10;
	public static final double LIMELIGHT_TURNING_DIVISOR = 0.05;
	public static final double LIMELIGHT_TURNING_CAP = 0.3;

	// Manipulator Constants
	public static final double INTAKE_SPEED = .65;
	public static final double OUTPUT_SPEED = -.25;
	 // 2v is the maximum we can give the 775 pros for a long time stalled
	// 2/12 ~= 0.17. We use voltage compensation so we can assume the voltage is 12v
	public static final double HOLD_SPEED = 0.17;
	public static final double SMALL_OUTPUT_SPEED = -.3;
	public static final double LARGE_OUTPUT_SPEED = -.7;


	//Auto Constants
	public static final double LINE_DISTANCE = 10; //THIS IS NOT FINAL TODO FIND ACTUAL DISTANCE
	public static final double SMALL_DISTANCE = 2; //THIS IS NOT FINAL TODO FIND ACTUAL VALUABLE SMALL DISTANCE
	
	
	// Auto PID constants
	public static double DRIVE_DISTANCE_P = 0;
	public static double DRIVE_DISTANCE_I = 0;
	public static double DRIVE_DISTANCE_D = 0;
	
	public static double DRIVE_ANGLE_P = 0.04; // want .35 output with error 5 degrees 
	public static double DRIVE_ANGLE_I = 0.01; // I and D values are guesses
	public static double DRIVE_ANGLE_D = 0.015;
	
	public static double STATIONARY_ANGLE_P;
	public static double STATIONARY_ANGLE_I;
	public static double STATIONARY_ANGLE_D;
	
}
