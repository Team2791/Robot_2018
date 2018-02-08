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
    public static final double offset= 30;
    public static final double ratio = 1/2;
    public static final double FAR_AWAY= 50;
    public static final double LARGE_NUMBER = .3;
    public static final double SMALL_NUMBER = .1;
    public static final double CLOSE = 10;
    public static final double CLOSE_TO_BOTTOM_DISTANCE = 5;
    public static final double CLOSE_TO_TOP_DISTANCE = 3;
    public static final double POWER = .3;

	// Joystick constants
	public static final double DEADZONE = 0.05;
	
	// Drive train constants
	public static double driveEncoderTicks = 256;
	public static final double SPEED_MULTIPLIER = 0.2;
	public static final double WHEEL_DIAMETER_IN_FEET = 1.0;

	// Time when ramps are allowed to be released
	// (3 * 60) - 30 = (3 minutes * 60 seconds) - 30 seconds
	public static final double RAMP_RELEASE_TIME = (3 * 60) - 30;

	// Limelight Driving Constants
	public static final double LIMELIGHT_TURNING_KP = 0;
}
