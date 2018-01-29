package org.usfirst.frc.team2791.robot.subsystems;

import org.usfirst.frc.team2791.robot.RobotMap;
import org.usfirst.frc.team2791.robot.commands.intakeclaw.RunIntakeWithJoystick;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;


/**
 *
 */
public class IntakeClaw extends Subsystem {
	
	private Spark leftMotorSpark, rightMotorSpark;

    
	public IntakeClaw() {
		leftMotorSpark = new Spark(RobotMap.INTAKE_SPARK_LEFT_PORT);
		rightMotorSpark = new Spark(RobotMap.INTAKE_SPARK_RIGHT_PORT);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new RunIntakeWithJoystick());
    }
    
    public void setIntakeSpeed(double speed) {
    	leftMotorSpark.set(speed);
    	rightMotorSpark.set(speed);

    }
}

