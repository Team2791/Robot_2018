package org.usfirst.frc.team2791.pathing;


import java.util.function.Function;

import org.usfirst.frc.team2791.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class RunPath extends Command {
	private final double arcDivisor = 20;

	private double length = -1;
	
	private boolean reset = true;
	
	private Path path;
	
	private Function<Double, Double> speed;
	
    public RunPath(Path path, double speed) {
    	this(path, x-> speed);
    }
    
    public RunPath(Path path, double speed, boolean reset) {
    	this(path, speed);
    	this.reset = reset;
    }
    
    public RunPath(Path path, Function<Double, Double> speed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    	this.path = path;
    	this.speed = speed;
    	SmartDashboard.putNumber("340_Path - amountOfPathDriven", 0);
    	SmartDashboard.putNumber("340_Path - Error", 0);
    }
    
    public RunPath(Path path, Function<Double, Double> speed, boolean reset) {
    	this(path, speed);
    	this.reset = reset;
    }
    
	public double dydx(double s) {
		PathSegment segment = path.getPathAtDistance(s);
		return segment.getDerivative().apply((s - path.getTotalOfCompletedPaths(s))/segment.getLength());
	}

    // Called just before this Command runs the first time
    protected void initialize() {	
    	Robot.drivetrain.setLeftRightMotorOutputs(0, 0);
//    	Robot.drive.resetBothEncoders();
//    	Robot.drive.resetIMU();
    	Robot.drivetrain.reset();
    	System.out.println("RUNPATH INIT");
    }
    
    private double getDistance() {
//    	return Math.abs((Robot.drive.getRightEncoder() + Robot.drive.getLeftEncoder())/2);
    	return Math.abs((Robot.drivetrain.getAverageDist()));
    }
    
    private double deltaAngle(double currentAngle) {
    	double currentSlope = Math.tan(currentAngle * Math.PI / 180);
    	double nextSlope = dydx(getDistance());
    	
    	double angle = Math.atan((nextSlope - currentSlope)/(1 + currentSlope * nextSlope))*180/Math.PI;
    	
//    	System.out.println("m1: " + currentSlope + " m2: " + nextSlope + " dTheta: " + angle);
//    	System.out.println("Encoder: " + getDistance() + " dydx: " + dydx(getDistance()));
    	return angle;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
//    	double error = -deltaAngle(Robot.drive.getYaw());
    	double error = deltaAngle(Robot.drivetrain.getGyroAngle());
    	SmartDashboard.putNumber("340_Path - Error", error);
    	
    	double amountOfPathDriven = getDistance()/path.getTotalLength();
    	SmartDashboard.putNumber("340_Path - amountOfPathDriven", amountOfPathDriven);
    	double driveSpeed = speed.apply(amountOfPathDriven);
    	
//    	System.out.println("error: " + error);
    	if(Math.abs(getDistance()) > 3) {
//        	Robot.drive.setBothDrive(
//        			(leftSpeed+((error)/(arcDivisor/Math.abs(speed)))), 
//        			(rightSpeed-(((error)/(arcDivisor/Math.abs(speed))))));
    		Robot.drivetrain.setLeftRightMotorOutputs(
        			(driveSpeed+((error)/(arcDivisor/Math.abs(driveSpeed)))),
        			(driveSpeed-(((error)/(arcDivisor/Math.abs(driveSpeed))))));
    	} else {
//        	Robot.drive.setBothDrive(leftSpeed, rightSpeed);
    		Robot.drivetrain.setLeftRightMotorOutputs(driveSpeed, driveSpeed);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	try {
//        	System.out.println(path.getPathAtDistance(Robot.drive.getRightDistance()).getLength());
            return Math.abs(getDistance()) > (path.getTotalLength());
    	} catch (Exception e) {
    		System.err.println(e);
    		return true;
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
//    	Robot.drive.setBothDrive(0, 0);
    	Robot.drivetrain.setLeftRightMotorOutputs(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
