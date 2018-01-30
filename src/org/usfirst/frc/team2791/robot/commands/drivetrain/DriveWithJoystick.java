package org.usfirst.frc.team2791.robot.commands.drivetrain;

import org.usfirst.frc.team2791.robot.Robot;
import org.usfirst.frc.team2791.robot.subsystems.ShakerDrivetrain;
import org.usfirst.frc.team2791.robot.util.Constants;
import org.usfirst.frc.team2791.robot.util.GTADrive;

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

		Robot.drivetrain.setLeftRightMotorOutputs(speedMultiplier*GTADrive.getLeftValue(Robot.oi.driver),
				speedMultiplier*GTADrive.getRightValue(Robot.oi.driver));
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
	}

}
