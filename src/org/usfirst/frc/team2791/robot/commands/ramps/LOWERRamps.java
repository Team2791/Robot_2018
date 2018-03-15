package org.usfirst.frc.team2791.robot.commands.ramps;

import org.usfirst.frc.team2791.robot.Constants;
import org.usfirst.frc.team2791.robot.Robot;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LOWERRamps extends Command {
	private Button leftRampLowerButton, rightRampLowerButton;

    public LOWERRamps(Button leftRampLowerButton, Button rightRampLowerButton) {
    	this.leftRampLowerButton = leftRampLowerButton;
    	this.rightRampLowerButton = rightRampLowerButton;
    	requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.setDriveOrRampMode(false);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
//    	if(Robot.drivetrain.isDrivetrainInDriveMode()) {
//    		Robot.drivetrain.setLeftRightMotorOutputs(0, 0);
//    		return;
//    	}
    	
    	double leftOutput = 0;
    	double rightOutput = 0;

    	// if ramp is up OR button is not pressed 
    	if(rightRampLowerButton.get()) {
    		leftOutput = Constants.LOWER_RAMPS_SPEED;
        }

        if(leftRampLowerButton.get()) {
        	rightOutput = Constants.LOWER_RAMPS_SPEED; 
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
