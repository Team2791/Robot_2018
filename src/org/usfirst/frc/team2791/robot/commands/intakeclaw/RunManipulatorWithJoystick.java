package org.usfirst.frc.team2791.robot.commands.intakeclaw;

import org.usfirst.frc.team2791.robot.OI;
import org.usfirst.frc.team2791.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RunManipulatorWithJoystick extends Command {

    public RunManipulatorWithJoystick() {
    	requires(Robot.manipulator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double speed = -1 * OI.operator.getAxisRightY() * 0.3;
    	Robot.manipulator.setLeftRightMotorSpeed(speed, speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
