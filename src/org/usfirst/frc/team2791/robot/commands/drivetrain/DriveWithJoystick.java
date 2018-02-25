package org.usfirst.frc.team2791.robot.commands.drivetrain;

import org.usfirst.frc.team2791.robot.Constants;
import org.usfirst.frc.team2791.robot.OI;
import org.usfirst.frc.team2791.robot.Robot;
import org.usfirst.frc.team2791.robot.subsystems.ShakerDrivetrain;

import edu.wpi.first.wpilibj.command.Command;


public class DriveWithJoystick extends Command {

	private double speedMultiplier = Constants.SPEED_MULTIPLIER;

	public DriveWithJoystick() {

		super("DriveWithJoystick");
		requires(Robot.drivetrain);

	}

	protected void initialize() {
//		Robot.drivetrain.setDriveOrRampMode(true);
	}
	
	@Override
	protected void execute() {
		// exit early if in ramp mode
		if(!Robot.drivetrain.isDrivetrainInDriveMode()){
//			System.out.println("DriveWithJoystick: Drivetrain is not in Driving mode. Ending Command");
			Robot.drivetrain.setLeftRightMotorOutputs(0, 0);
			return;
		}
		
		if(Robot.lift.getHeight() > 13.5) {
			speedMultiplier = 0.5;
		} else {
			speedMultiplier = Constants.SPEED_MULTIPLIER;
		}
		
		double leftSpeed = OI.driver.getGtaDriveLeft() * speedMultiplier;
		double rightSpeed = OI.driver.getGtaDriveRight() * speedMultiplier;
		
		double turn = OI.driver.getAxisRightY() * Constants.RIGHT_JOYSTICK_TURN_MULTIPLIER;
		
		Robot.drivetrain.setLeftRightMotorOutputs(leftSpeed + turn, rightSpeed - turn);
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
