package org.usfirst.frc.team2791.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/*
 * This should have all the project's important constants
 * Currently, all the constants are taken from last year
 * so adjustments will be needed when the robot is made
 */

public class Constants {

	// Server Constants
	public static final String TEAM_NUMBER = "2791";
	public static final String SERVER_PORT = "roboRIO-" + TEAM_NUMBER + "-FRC";
	public static final String msg = "I'm Alive!!!! :(";

	//Lift Constants

    public static double LIFT_POT_OFFSET = -4.0; //-3.2795698924731185;
	//at 60% the lift was at 36'' from the ground, 33'' from the bottom of it's travel
    public static final double LIFT_POT_FULL_RANGE = 33.0 / 0.6;
    
    public static final double FAR_AWAY_DISTANCE = 12;
    public static final double CLOSE_DISTANCE = 1;
    public static final double FAR_AWAY_POWER = 1;
    public static final double CLOSE_POWER = .4;
    
    public static final double LIFT_MAX_HEIGHT = 38.5;
    public static final double LIFT_MIN_HEIGHT = .25;
    public static final double LIFT_HEIGHT_ENDGAME = 18.0;

    public static final double BOTTOM_SAFTEY_DISTANCE = 5; //11 when using bang bang
    public static final double TOP_SAFTEY_DISTANCE = 5; // 12 when using bang bang
    public static final double MANUAL_POWER = 0.75;

    // Lift Magic Motion values
	public static final int MM_PID_SLOT_ID = 0;
    public static double LIFT_P_VALUE = 18; // 3.5
    public static double LIFT_I_VALUE = 0.008; // 0
    public static double LIFT_D_VALUE = 180; // 40
    public static final double LIFT_MAX_SPEED_RAW_UNITS = 85.0;// max velocity is 85 U/.1s up and 95 U/.1s down
    public static final int MOTION_VELOCITY = (int) (LIFT_MAX_SPEED_RAW_UNITS * 1.0); 
    public static final double LIFT_F_VALUE = 1023.0 / LIFT_MAX_SPEED_RAW_UNITS; // F-gain = (100% X 1023) / MAX_VEL 
    public static final int MOTION_ACCELERATION = (int) (MOTION_VELOCITY / 0.75); // want 0.75s to reach curise speed

    
    // TODO Make switch heights constants
    public static final double AUTON_RETRACTED_SWITCH_HEIGHT = 10;
    public static final double AUTON_EXTENDED_SWITCH_HEIGHT = 13;
    public static final double AUTON_SCALE_HEIGHT = LIFT_MAX_HEIGHT - 0.1;

	// Joystick constants
	public static final double DEADZONE = 0.05;
	
	// Drive train constants
	public static double driveEncoderTicks = 256 * 2; // not sure why we're doing this ??
	public static final double SPEED_MULTIPLIER = 1;
	public static final double WHEEL_DIAMETER_IN_IN = 6.0 * 0.95833333; //because of carpet + real size wheels are actually 0.95833333 * 6''
	//6.0 * 3.14159 / 12.0; // was 1.0, now 6.0*3.14159/12.0 = 1.570
	public static final double RAISE_RAMPS_SPEED = 0.9;
	public static final double LOWER_RAMPS_SPEED = -0.5;
	
	public static final double RIGHT_JOYSTICK_TURN_MULTIPLIER = 0.5;

	// Time when ramps are allowed to be released
	// (3 * 60) - 30 = (3 minutes * 60 seconds) - 30 seconds
	public static final double RAMP_RELEASE_TIME = (3 * 60) - 30;

	// Limelight Driving Constants
	public static final double LIMELIGHT_TURNING_KP = 10;
	public static final double LIMELIGHT_TURNING_DIVISOR = 0.05;
	public static final double LIMELIGHT_TURNING_CAP = 0.3;

	// Manipulator Constants
	public static final double INTAKE_SPEED = -1.0;  //Made negative for P-Bot, wires are backwards //Increased by 40% for new 1:10 reduction
	public static final double OUTPUT_SPEED = .3; //Made positive for P-Bot, wires are backwards //Increased by 40% for new 1:10 reduction
	 // 2v is the maximum we can give the 775 pros for a long time stalled
	// 2/12 ~= 0.17. We use voltage compensation so we can assume the voltage is 12v
	public static final double HOLD_SPEED = -0.1; //Made negative for P-Bot, wires are backwards 
	public static final double SMALL_OUTPUT_SPEED = .45; //Made negative for P-Bot, wires are backwards //Increased by 40% for new 1:10 reduction
	public static final double LARGE_OUTPUT_SPEED = 1.0; //Made negative for P-Bot, wires are backwards //Increased by 40% for new 1:10 reduction
	public static final double INTAKE_CUBE_STALL_CURRENT = 7;
	

	//Auto Constants
	public static final double LINE_DISTANCE = 10; //THIS IS NOT FINAL TODO FIND ACTUAL DISTANCE
	public static final double SMALL_DISTANCE = 2; //THIS IS NOT FINAL TODO FIND ACTUAL VALUABLE SMALL DISTANCE
	public static final double RADIANS_TO_DEGREES = -180/Math.PI;
	public static final double LIFT_HOLD_VOLTAGE = 0.12;
	
	
	// Auto PID constants
	public static double DRIVE_DISTANCE_P = 0.035;
	public static double DRIVE_DISTANCE_I = 0.08;
	public static double DRIVE_DISTANCE_D = 0.008;
	
	public static double DRIVE_ANGLE_P = 0.04; // want .35 output with error 5 degrees 
	public static double DRIVE_ANGLE_I = 0.0; // I and D values are guesses
	public static double DRIVE_ANGLE_D = 0.0;
	
	public static double STATIONARY_ANGLE_P = 0.05;
	public static double STATIONARY_ANGLE_I = 0.10;
	public static double STATIONARY_ANGLE_D = 0.005;
	
	
	public static double FeetToMeters = 0.3048;
	public static double InchesToMeters = 0.0254; // :o cheeeeeeesy  o:
	

	public static class DrivetrainProfiling {
		// TODO: TUNE CONSTANTS
		public static double kp = 5.0; // 6.0 Maybe higher, less than 6.5. Using 5 because on 2nd day gave best results
		public static double kd = 0.04; // 0.04
		public static double gp = 0.03; //  0.03
		public static double gd = 0.0; // 0.0025

		public static double ki = 0.0;

		// Gyro logging for motion profiling
		public static double last_gyro_error = 0.0;

		// this stuff is in meters
		public static double path_angle_offset = 0.0;
		public static final double max_velocity = 3; // 125 in/s -> 3.175m/s
		public static double kv = 1.0/max_velocity;
		public static final double max_acceleration = 3.5; // was 3 // 200 in/s^2 at 0, -> 5.08m. 2.5 max accel is okay for now. Can add later.
		public static double ka = 0.03; // was 0.065
		
		// guessed it 0.015 // this should be final once the number is confirmed
		// was 0.1 and looked too high
		// 1.0/5.08 = 0.20.
		public static final double max_jerk = 5000000.0; // was 8
		public static final double wheel_diameter = Constants.WHEEL_DIAMETER_IN_IN * Constants.InchesToMeters; // 6'' in meters

		public static final double wheel_base_width = 0.662; // 24.5 inches
		public static final int ticks_per_rev = (int) Constants.driveEncoderTicks;
		public static final double dt = 0.02;

		/**
		 * Spline PID
		 * 
		 * @param p
		 * @param i
		 * @param d
		 * @param gp
		 * @param gd
		 */
		public static void sendToDashboardPIDG(double p, double i, double d, double gp, double gd) {
			SmartDashboard.putNumber("Pathfinder - kP", p);
			SmartDashboard.putNumber("Pathfinder - kI", i);
			SmartDashboard.putNumber("Pathfinder - kD", d);
			SmartDashboard.putNumber("Pathfinder - gP", gp);
			SmartDashboard.putNumber("Pathfinder - gD", gd);
			SmartDashboard.putNumber("Pathfinder - kV", kv);
			SmartDashboard.putNumber("Pathfinder - kA", ka);
		}

	}

	
}
