package org.usfirst.frc.team2791.robot.commands.auto.pid;

import org.usfirst.frc.team2791.robot.Constants;
import org.usfirst.frc.team2791.robot.Robot;
import org.usfirst.frc.team2791.robot.util.BasicPID;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Drives a set distance based on Encoders and power outputs without using PID
 */
public class DriveEncoderBangBangGyroPID extends Command {
	
	double power, distanceToDrive, timeToDrive;
	double stopDistance;
	BasicPID anglePID;
	
	protected Timer timer = new Timer();
	
    public DriveEncoderBangBangGyroPID(double power, double distance, double timeOut) {
    	this.power = power;
    	timeToDrive = timeOut;
    	distanceToDrive = distance;
    	anglePID = new BasicPID(Constants.DRIVE_ANGLE_P, Constants.DRIVE_ANGLE_I, Constants.DRIVE_ANGLE_D);
    }


    protected void initialize() {
    	timer.reset();
    	timer.start();
    	stopDistance = Robot.drivetrain.getAverageDist() + distanceToDrive;
    	System.out.println("Starting encoder bang bang drive. power: " +power+"  stopDistance: "+stopDistance);
    	anglePID.setSetPoint(Robot.drivetrain.getGyroAngle());
    	updatePIDGains();
    }

    protected void execute() {
    	double anglePIDOutput = anglePID.updateAndGetOutput(Robot.drivetrain.getGyroAngle());
    	Robot.drivetrain.setLeftRightMotorOutputs(power - anglePIDOutput, power + anglePIDOutput);
    	debug();
    }

    protected boolean isFinished() {
    	if(timer.get() > timeToDrive)
    		return true;
    	
    	if(power > 0) {
    		if(Robot.drivetrain.getAverageDist() > stopDistance) {
    			System.out.println("Drive EncoderBB,+ Gyro ending at distance: " + Robot.drivetrain.getAverageDist());
    			return true;
    		}
    	} else {
    		if(Robot.drivetrain.getAverageDist() < stopDistance) {
    			System.out.println("Drive EncoderBB,+ Gyro ending at distance: " + Robot.drivetrain.getAverageDist());
    			return true;
    		}
    	}
    	
    	return false;
    }

    protected void end() {
    	Robot.drivetrain.setLeftRightMotorOutputs(0, 0);
    }
    
    public void updatePIDGains() {
    	Constants.DRIVE_ANGLE_P = SmartDashboard.getNumber("Moving Angle P", Constants.DRIVE_ANGLE_P);
		Constants.DRIVE_ANGLE_I = SmartDashboard.getNumber("Moving Angle I", Constants.DRIVE_ANGLE_I);
		Constants.DRIVE_ANGLE_D = SmartDashboard.getNumber("Moving Angle D", Constants.DRIVE_ANGLE_D);
		anglePID.changeGains(Constants.DRIVE_ANGLE_P, Constants.DRIVE_ANGLE_I, Constants.DRIVE_ANGLE_D);
    }
    
	public void debug() {
//		System.out.println("Drive EncoderBB + Gyro error: "+anglePID.getError() +" output: "+anglePID.getOutput());
		// No idea where we're not seeing these values in Shuffleboard. *grumble*
		SmartDashboard.putNumber("PID - Moving Angle Error", anglePID.getError());
		SmartDashboard.putNumber("PID - Moving Angle Output", anglePID.getOutput());
	}

    protected void interrupted() {
    	end();
    }
}
