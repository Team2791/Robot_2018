package org.usfirst.frc.team2791.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

	// ===== Actuators =====
	
	// __ _Motors ___

	// Drive train
	public static final int VICTOR_LEFT_1 = 32;
	public static final int VICTOR_LEFT_2 = 31;
	public static final int TALON_LEFT_3 = 27;
	public static final int VICTOR_RIGHT_1 = 36;
	public static final int VICTOR_RIGHT_2 = 33;
	public static final int TALON_RIGHT_3 = 20;
	
	// Manipulator
	public static final int INTAKE_SPARK_LEFT_PORT = 34;
	public static final int INTAKE_SPARK_RIGHT_PORT = 35;
	
	// Lift
	public static final int LIFT_TALON_ONE = 28; // this is the
	public static final int LIFT_VICTOR_TWO = 37; // this is the
	public static final int LIFT_VICTOR_THREE = 38; // this is the
	
	
	// __ Pistons ___
	
	// Drive train
	public static final int DRIVETRAIN_GEARBOX_SHIFTER_IN = 0;
	public static final int DRIVETRAIN_GEARBOX_SHIFTER_OUT = 1;
	
	// Ramps
	public static final int RAMP_DEPLOY_SOLENOID = 5;
	
	// Lift
	public static final int BREAK_SOLENOID = 2;

	// Manipulator
	public static final int INTAKE_EXTENDER_SOLENOID_PORT = 3;
	//public static final int INTAKE_GRABBER_SOLENOID_PORT = 5;
	
	// ===== Sensors =====
	

	// ___ Digital Sensors ____
	
	// Lift Ports
    public static final int LIMIT_SWITCH_TOP = 8;
	public static final int LIMIT_SWITCH_BOTTOM = 9;
	public static final int IR_SENSOR_LEFT = 3;
	public static final int IR_SENSOR_RIGHT = 2;

	
	// Drive train
	/*public static final int LEFT_DRIVE_ENCODER_PORT_A = 0;
	public static final int LEFT_DRIVE_ENCODER_PORT_B = 1;
	public static final int RIGHT_DRIVE_ENCODER_PORT_A = 2;
	public static final int RIGHT_DRIVE_ENCODER_PORT_B = 3;*/
	
	// Ramps
	public static final int LIMIT_SWITCH_LEFT_1 = 4;
	public static final int LIMIT_SWITCH_LEFT_2 = 5;
	public static final int LIMIT_SWITCH_RIGHT_1 = 6;
	public static final int LIMIT_SWITCH_RIGHT_2 = 7;
	
	// ___ Analog Sensors ___
	
	// Lift 
	public static final int LIFT_POT_PORT = 1;
	

	
	// ___ PDP Stuff ____
	
	// Power distribution panel
	public static final int PDP = 0;

	//PDP Ports
	public static final int POWER_RIGHT_DRIVE_1 = 0;
	public static final int POWER_RIGHT_DRIVE_2 = 1;
	public static final int POWER_RIGHT_DRIVE_3 = 2;
	public static final int POWER_LEFT_DRIVE_1 = 3;
	public static final int POWER_LEFT_DRIVE_2 = 4;
	public static final int POWER_LEFT_DRIVE_3 = 5;
	
	public static final int PDP_INTAKE_LEFT = 3;
	public static final int PDP_INTAKE_RIGHT = 4;
	
	// JOYSTICK PORTS
	public static final int JOYSTICK_DRIVER_PORT = 0;
	public static final int JOYSTICK_OPERATOR_PORT = 1;
}
