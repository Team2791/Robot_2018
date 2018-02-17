package org.usfirst.frc.team2791.robot.commands.drivetrain;

import org.usfirst.frc.team2791.robot.Robot;
import org.usfirst.frc.team2791.robot.subsystems.ShakerDrivetrain;
import org.usfirst.frc.team2791.robot.util.Constants;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Uses the {@link GTADrive} I/O Logic to control the {@link ShakerDrivetrain Drivetrain}.
 * @see GTADrive
 */
public class DriveWithJoystick extends Command {

	private double speedMultiplier = Constants.SPEED_MULTIPLIER;

	public DriveWithJoystick() {
		super("DriveWithJoystick");
		requires(Robot.drivetrain);
	}

	protected void initialize() {
	}
	
	@Override
	
	protected void execute() {
		if(Robot.lift.getHeight() > 13.5) {
			speedMultiplier = 0.5;
		} else {
			speedMultiplier = Constants.SPEED_MULTIPLIER;
		}
		
		
		double leftSpeed = Robot.oi.driver.getGtaDriveLeft() * speedMultiplier;
		double rightSpeed = Robot.oi.driver.getGtaDriveRight() * speedMultiplier;
		Robot.drivetrain.setLeftRightMotorOutputs(leftSpeed, rightSpeed);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		Robot.drivetrain.setLeftRightMotorOutputs(0.0, 0.0);
	}

	protected void interrupted() {
		end();
	}
}
