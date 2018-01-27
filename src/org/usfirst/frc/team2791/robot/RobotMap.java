package org.usfirst.frc.team2791.robot;

import edu.wpi.first.wpilibj.DigitalSource;

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
	public static final int VICTOR_RIGHT_1 = 24;
	public static final int VICTOR_RIGHT_2 = 33;
	public static final int TALON_RIGHT_3 = 36;

	// Intake
	public static final int INTAKE_SPARK_LEFT_PORT = 2;
	public static final int INTAKE_SPARK_RIGHT_PORT = 3;

	// Ramp release double soleniod
	public static final int RAMP_RELEASE_SOLENIOD = 5;
	
	// ===== Sensors =====
	
	// Power distribution pannel
	public static final int PDP = 0;
	
	// Drive train
	public static final int LEFT_DRIVE_ENCODER_PORT_A = 0;
	public static final int LEFT_DRIVE_ENCODER_PORT_B = 1;
	public static final int RIGHT_DRIVE_ENCODER_PORT_A = 2;
	public static final int RIGHT_DRIVE_ENCODER_PORT_B = 3;
	

	
}
