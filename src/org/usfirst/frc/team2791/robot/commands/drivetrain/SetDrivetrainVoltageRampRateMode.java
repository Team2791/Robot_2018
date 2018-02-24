package org.usfirst.frc.team2791.robot.commands.drivetrain;

import org.usfirst.frc.team2791.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Drives a set distance based on Encoders and power outputs without using PID
 */
public class SetDrivetrainVoltageRampRateMode extends Command {
	double rampTime;
	
    public SetDrivetrainVoltageRampRateMode(double rampTime) {
    	requires(Robot.drivetrain);
    	this.rampTime = rampTime;
    }


    protected void initialize() {
    }

    protected void execute() {
    	Robot.drivetrain.setVoltageRampRate(rampTime);
    }

    protected boolean isFinished() {
    	return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
