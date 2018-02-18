package org.usfirst.frc.team2791.robot.commands.ramps;

import org.usfirst.frc.team2791.robot.Constants;
import org.usfirst.frc.team2791.robot.Robot;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RaiseRamps extends Command {
	private Button leftRampRaisebutton, rightRampsRaiseButton;

    public RaiseRamps(Button leftRampRaisebutton, Button rightRampsRaiseButton) {
    	this.leftRampRaisebutton = leftRampRaisebutton;
    	this.rightRampsRaiseButton = rightRampsRaiseButton;
    	requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Robot.drivetrain.setDriveOrRampMode(false);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double leftOutput;
    	double rightOutput;

    	// if ramp is up OR button is not pressed 
    	if(Robot.ramps.isLeftRampUp() || !leftRampRaisebutton.get()) {
    	    leftOutput = 0;
        } else {
    	     leftOutput = Constants.RAISE_RAMPS_SPEED;
        }

        if(Robot.ramps.isRightRampUp() || !rightRampsRaiseButton.get()) {
    	    rightOutput = 0;
        } else {
    	    rightOutput = Constants.RAISE_RAMPS_SPEED;
        }

    	Robot.drivetrain.setLeftRightMotorOutputs(-leftOutput, -rightOutput);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	// Can't use this because the freaking whileHeld will call it every loop.
//    	Robot.drivetrain.setLeftRightMotorOutputs(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
