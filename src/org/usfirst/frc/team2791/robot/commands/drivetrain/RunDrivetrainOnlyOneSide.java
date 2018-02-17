package org.usfirst.frc.team2791.robot.commands.drivetrain;

import org.usfirst.frc.team2791.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RunDrivetrainOnlyOneSide extends Command {
	private double speed;
	private boolean leftSide;
	private boolean rightSide;

    public RunDrivetrainOnlyOneSide(boolean leftSide, double speed) {
    	// the ternary operator (?) is used to set the name based on the boolean
    	super("RunDrivetrainOnlyOneSide-"+ (leftSide ? "left" : "right"));
    	requires(Robot.drivetrain);
    	this.speed = speed;
    	this.leftSide = leftSide;
    	this.rightSide = !leftSide;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Robot.drivetrain.setDriveOrRampMode(true);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if(Robot.ramps.isLeftRampUp() || Robot.ramps.isRightRampUp()){

        }
    	// TODO verify that the negative are on the right sides here!
    	double leftOutput;
    	double rightOutput;

    	if(leftSide && Robot.ramps.isLeftRampUp()) {
    	    leftOutput = 0;
        } else {
    	     leftOutput = leftSide ? speed : 0;
        }

        if(rightSide && Robot.ramps.isRightRampUp()) {
    	    rightOutput = 0;
        } else {
    	    rightOutput = leftSide ? 0 : -speed;
        }


    	Robot.drivetrain.setLeftRightMotorOutputs(leftOutput, rightOutput);
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
