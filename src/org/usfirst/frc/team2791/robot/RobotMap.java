package org.usfirst.frc.team2791.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

	// ===== Actuators =====
	
	// Drive train
	public static final int VICTOR_LEFT_1 = 32;
	public static final int VICTOR_LEFT_2 = 31;
	public static final int TALON_LEFT_3 = 27;
	public static final int VICTOR_RIGHT_1 = 36;
	public static final int VICTOR_RIGHT_2 = 33;
	public static final int TALON_RIGHT_3 = 20;

	// Intake
	public static final int INTAKE_SPARK_LEFT_PORT = 2;
	public static final int INTAKE_SPARK_RIGHT_PORT = 3;

	// Ramp release double soleniod
	public static final int RAMP_RELEASE_SOLENIOD = 5;
	
	// ===== Sensors =====
	
	// Power distribution panel
	public static final int PDP = 0;
	
	// Drive train
	public static final int LEFT_DRIVE_ENCODER_PORT_A = 0;
	public static final int LEFT_DRIVE_ENCODER_PORT_B = 1;
	public static final int RIGHT_DRIVE_ENCODER_PORT_A = 2;
	public static final int RIGHT_DRIVE_ENCODER_PORT_B = 3;


	// JOYSTICK PORTS
	public static final int JOYSTICK_DRIVER_PORT = 0;
	public static final int JOYSTICK_OPERATOR_PORT = 1;

	//PDP Ports
	public static final int POWER_RIGHT_DRIVE_1 = 0;
	public static final int POWER_RIGHT_DRIVE_2 = 1;
	public static final int POWER_RIGHT_DRIVE_3 = 2;
	public static final int POWER_LEFT_DRIVE_1 = 3;
	public static final int POWER_LEFT_DRIVE_2 = 4;
	public static final int POWER_LEFT_DRIVE_3 = 5;
}
