package org.usfirst.frc.team340.pathing;

import java.util.function.Function;

import org.usfirst.frc.team2791.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class RunPath extends Command {

	private double kP = 0.1; // was 1.0/15.0 = 0.666, was way too much
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
    	SmartDashboard.putNumber("340_Path - error", 0);
    	SmartDashboard.putNumber("340_Path - turn", 0);
    	SmartDashboard.putNumber("340_Path - kP", kP);
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
    	SmartDashboard.putNumber("340_Path - error", 0);
    	SmartDashboard.putNumber("340_Path - turn", 0);
    	kP = SmartDashboard.getNumber("340_Path - kP", kP);
    }

    private double getDistance() {
    	return Robot.drivetrain.getAverageDist();
    }

    private double deltaAngle(double currentAngle) {
    	double currentSlope = Math.tan(currentAngle * Math.PI / 180);
    	double targetSlope = dydx(getDistance());
    	
    	double angle = Math.atan((targetSlope - currentSlope)/(1 + currentSlope * targetSlope))*180/Math.PI;
    	
    	System.out.println("currentSlope: " + currentSlope + " targetSlope: " + targetSlope + " dTheta: " + angle);
    	System.out.println("Encoder: " + getDistance() + " dydx: " + dydx(getDistance()));
    	return angle;
    }
    
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double error = deltaAngle(Robot.drivetrain.getGyroAngle());
    	SmartDashboard.putNumber("340_Path - error", error);
//    	System.out.println("Angle Error:" + error);

    	double baseSpeed = speed.apply(getDistance()/path.getTotalLength());

    	double turn = Math.abs(baseSpeed) * error * kP;
    	SmartDashboard.putNumber("340_Path - turn", turn);
    	System.out.println("error: " + error + "   speed: " + baseSpeed + "   turn: "+ turn);
    	
    	if(Math.abs(getDistance()) > 3) {    		
    		Robot.drivetrain.setLeftRightMotorOutputs(baseSpeed + turn, baseSpeed - turn);
//        	Robot.drivetrain.setLeftRightMotorOutputs(
//        			(leftSpeed+((error)/(arcDivisor/Math.abs(speed)))), 
//        			(rightSpeed-(((error)/(arcDivisor/Math.abs(speed))))));
    	} else {
        	Robot.drivetrain.setLeftRightMotorOutputs(baseSpeed, baseSpeed);
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
