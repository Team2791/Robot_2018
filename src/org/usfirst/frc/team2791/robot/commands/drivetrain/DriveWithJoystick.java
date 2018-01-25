package org.usfirst.frc.team2791.robot.commands.drivetrain;

import org.usfirst.frc.team2791.robot.Robot;
import org.usfirst.frc.team2791.robot.subsystems.ShakerDrivetrain;
import org.usfirst.frc.team2791.robot.util.Constants;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
		double left = speedMultiplier * Robot.oi.driver.getGtaDriveLeft();
		double right = speedMultiplier * Robot.oi.driver.getGtaDriveRight();
		
		Robot.drivetrain.setLeftRightMotorOutputs(left, right);
		
		SmartDashboard.putNumber("Left Joystick Output", left);
		SmartDashboard.putNumber("Right Joystick Output", right);

	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		Robot.drivetrain.setLeftRightMotorOutputs(0.0,0.0);
	}

	protected void interrupted() {
		new DriveWithJoystick();
		System.out.println("Drive with joystick interrupted.");
	}

}
