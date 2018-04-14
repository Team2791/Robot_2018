package org.usfirst.frc.team2791.robot.commands.lift;

import org.usfirst.frc.team2791.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Drives a set distance based on Encoders and power outputs without using PID
 */
public class PauseLift extends Command {
	
    public PauseLift(double time) {
    	requires(Robot.lift);
    	setTimeout(time);
    }


    protected void initialize() {
    }

    protected void execute() {
    	Robot.lift.setPower(0);
    }

    protected boolean isFinished() {
    	return isTimedOut();
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
