package org.usfirst.frc.team2791.robot.commands.auto.bangbang;

import org.usfirst.frc.team2791.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TurnGyroBangBang extends Command {

	double power, amountToTurn;
	double stopAngle;
	double timeToTurn = 2791;

	protected Timer timer = new Timer();

	/**
	 * @param angle degrees that you want to turn
	 * @param power amount of power you want in the turn
	 * @param timeOut seconds before you want to time out
	 */
	public TurnGyroBangBang(double angle, double power, double timeOut) {
		this(angle, power);
		timeToTurn = timeOut;
	}

	/**
	 * Time Out is defaulted to 2791 seconds
	 * @param power amount of power you want in the turn
	 * @param angle degrees that you want to turn  
	 */
	public TurnGyroBangBang(double angle, double power) {
		super("TurnGyro w/ BangBang");
		requires(Robot.drivetrain);
		this.power = power;
		amountToTurn = angle;
		assert(Math.signum(power) == Math.signum(angle));
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		timer.reset();
		timer.start();
		stopAngle = Robot.drivetrain.getGyroAngle() + amountToTurn;
		System.out.println("Starting gyro bang bang turn. power: "+power+"  Stop angle: "+stopAngle);
		System.out.println("Starting at distance: "+Robot.drivetrain.getAverageDist());
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.drivetrain.setLeftRightMotorOutputs(power, -power);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if(timer.get() > timeToTurn){
			System.out.println("Gyro bang bang turn timed out");
			return true;
		}

		if(power > 0) {
			if(Robot.drivetrain.getGyroAngle() > stopAngle) {
				System.out.println("Stopping at angle: "+Robot.drivetrain.getGyroAngle());
				return true;
			}
		} else {
			if(Robot.drivetrain.getGyroAngle() < stopAngle) {
				System.out.println("Stopping at angle: "+Robot.drivetrain.getGyroAngle());
				return true;
			}
		}
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.drivetrain.setLeftRightMotorOutputs(0, 0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
