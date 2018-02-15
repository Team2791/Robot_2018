package org.usfirst.frc.team2791.robot.util;

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
	//at 60% the lift was at 36'' from the ground, 33'' from the bottom of it's travel
    public static final double LIFT_POT_OFFSET = -1.13;
    public static final double LIFT_POT_FULL_RANGE = 33.0 / 0.6;
    
    public static final double FAR_AWAY_DISTANCE = 5;
    public static final double CLOSE_DISTANCE = 1;
    public static final double FAR_AWAY_POWER = 1;
    public static final double CLOSE_POWER = .25;
    
    public static final double LIFT_MAX_HEIGHT = 39-1; // -1 for safety
    public static final double LIFT_MIN_HEIGHT = .25 + 1; // +1 for safety
    
    public static final double BOTTOM_SAFTEY_DISTANCE = 4;
    public static final double TOP_SAFTEY_DISTANCE = 12;
    public static final double MANUAL_POWER = .5;

	// Joystick constants
	public static final double DEADZONE = 0.05;
	
	// Drive train constants
	public static double driveEncoderTicks = 256;
	public static final double SPEED_MULTIPLIER = 1;
	public static final double WHEEL_DIAMETER_IN_FEET = 1.0;

	// Time when ramps are allowed to be released
	// (3 * 60) - 30 = (3 minutes * 60 seconds) - 30 seconds
	public static final double RAMP_RELEASE_TIME = (3 * 60) - 30;

	// Limelight Driving Constants
	public static final double LIMELIGHT_TURNING_KP = 10;
	public static final double LIMELIGHT_TURNING_DIVISOR = 0.05;
	public static final double LIMELIGHT_TURNING_CAP = 0.3;

	// Manipulator Constants
	public static final double INTAKE_SPEED = .5;
	public static final double OUTPUT_SPEED = -.4;
	public static final double HOLD_SPEED = 0.15;
	 // 2v is the maximum we can give the 775 pros for a long time stalled
	// 2/13 ~= 0.15

	//Auto Constants
	public static final double LINE_DISTANCE = 10; //THIS IS NOT FINAL TODO FIND ACTUAL DISTANCE
	public static final double SMALL_DISTANCE = 2; //THIS IS NOT FINAL TODO FIND ACTUAL VALUABLE SMALL DISTANCE
}
