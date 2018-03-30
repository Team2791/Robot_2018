package org.usfirst.frc.team340.pathing;

import java.util.function.Function;

import org.usfirst.frc.team2791.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RunPath extends Command {

	private final double kP = 1/15;
	private Path path;	
	private Function<Double, Double> speed;
	
    public RunPath(Path path, double speed) {
    	this(path, x -> speed);
//    	requires(Robot.drivetrain);
//    	this.path = path;
//    	this.leftSpeed = -speed;
//    	this.rightSpeed = -speed;
//    	this.speed = x -> speed;
    }
    
    public RunPath(Path path, Function<Double, Double> speed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    	this.path = path;
    	this.speed = speed;
    }

    
	public double dydx(double s) {
		PathSegment segment = path.getPathAtDistance(s);
		return segment.getDerivative().apply((s - path.getTotalOfCompletedPaths(s))/segment.getLength());
	}

    // Called just before this Command runs the first time
    protected void initialize() {	
    	Robot.drivetrain.setLeftRightMotorOutputs(0, 0);
    	Robot.drivetrain.resetEncoders();
    	Robot.drivetrain.resetGyro();
    	System.out.println("RUNPATH INIT");
    }

    private double getDistance() {
    	return Robot.drivetrain.getAverageDist();
    }

    private double deltaAngle(double currentAngle) {
    	double currentSlope = Math.tan(currentAngle * Math.PI / 180);
    	double nextSlope = dydx(getDistance());
    	
    	double angle = Math.atan((nextSlope - currentSlope)/(1 + currentSlope * nextSlope))*180/Math.PI;
    	
    	System.out.println("m1: " + currentSlope + " m2: " + nextSlope + " dTheta: " + angle);
    	System.out.println("Encoder: " + getDistance() + " dydx: " + dydx(getDistance()));
    	return angle;
    }
    
    public double speed() {
//    	System.out.println(-speed.apply(getDistance()/path.getTotalLength()));
    	return -speed.apply(getDistance()/path.getTotalLength());
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double error = -deltaAngle(Robot.drivetrain.getGyroAngle());
    	
    	double leftSpeed = speed();
    	double rightSpeed = speed();
    	
    	System.out.println("error: " + error);
    	if(Math.abs(getDistance()) > 3) {
    		double speed = leftSpeed;
    		double turn = Math.abs(speed) * error * kP;
    		Robot.drivetrain.setLeftRightMotorOutputs(leftSpeed + turn, rightSpeed - turn);
//        	Robot.drivetrain.setLeftRightMotorOutputs(
//        			(leftSpeed+((error)/(arcDivisor/Math.abs(speed)))), 
//        			(rightSpeed-(((error)/(arcDivisor/Math.abs(speed))))));
    	} else {
        	Robot.drivetrain.setLeftRightMotorOutputs(leftSpeed, rightSpeed);
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
    	Robot.drivetrain.setLeftRightMotorOutputs(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
