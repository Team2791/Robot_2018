package org.usfirst.frc.team2791.robot.commands.drivetrain;

import org.usfirst.frc.team2791.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Drives a set distance based on Encoders and power outputs without using PID
 */
public class SetDrivetrainBreakMode extends Command {
	boolean inBreakMode;
	
    public SetDrivetrainBreakMode(boolean inBreakMode) {
    	requires(Robot.drivetrain);
    	this.inBreakMode = inBreakMode;
    }


    protected void initialize() {
    }

    protected void execute() {
    	Robot.drivetrain.setBrakeMode(inBreakMode);
    }

    protected boolean isFinished() {
    	return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
