package org.usfirst.frc.team2791.robot.commands.drivetrain;

import org.usfirst.frc.team2791.robot.Robot;
import org.usfirst.frc.team2791.robot.subsystems.ShakerDrivetrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SwitchDrivetrainShifterMode extends Command {
	private boolean inDrivingMode;

    public SwitchDrivetrainShifterMode() {
//    	requires(Robot.drivetrain);
	// IMPORTANT NOTE!! WE DO NOT REQUIRE DRIVE TRAIN HERE BECAUSE WE DO NOT WANT TO STOP THE DriveWithJoystick
	// Command. when this is running. Maybe Max S was right about the command based framework.    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	Robot.drivetrain.setDriveOrRampMode(!Robot.drivetrain.isDrivetrainInDriveMode());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
