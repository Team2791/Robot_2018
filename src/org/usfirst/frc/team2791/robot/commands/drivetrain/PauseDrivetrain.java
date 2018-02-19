package org.usfirst.frc.team2791.robot.commands.drivetrain;

import org.usfirst.frc.team2791.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Drives a set distance based on Encoders and power outputs without using PID
 */
public class PauseDrivetrain extends Command {
	
    public PauseDrivetrain(double time) {
    	requires(Robot.drivetrain);
    	setTimeout(time);
    }


    protected void initialize() {
    }

    protected void execute() {
    	Robot.drivetrain.setLeftRightMotorOutputs(0, 0);
    }

    protected boolean isFinished() {
    	return isTimedOut();
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
