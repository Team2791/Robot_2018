package org.usfirst.frc.team2791.pathing;


import java.util.function.Function;

import org.usfirst.frc.team2791.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class RunPath extends Command {
	public static enum Direction {
		FORWARDS,
		FORWARDS_MIRRORED,
		BACKWARDS,
		BACKWARDS_MIRRORED
	}

	private final double arcDivisor = 20;
	private Path path;
	private Function<Double, Double> speed;
	private double distanceAtStart = 0;
	private Direction direction = Direction.FORWARDS;

    public RunPath(Path path, double speed) {
    	this(path, x-> speed);
    }
    
    public RunPath(Path path, double speed, Direction direction) {
    	this(path,  speed);
    	this.direction = direction;
    }
    
    public RunPath(Path path, Function<Double, Double> speed, Direction direction) {
    	this(path, speed);
    	this.direction = direction;
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

	public double dydx(double s) {
		PathSegment segment = path.getPathAtDistance(s);
		return segment.getDerivative().apply((s - path.getTotalOfCompletedPaths(s))/segment.getLength());
	}

    // Called just before this Command runs the first time
    protected void initialize() {	
    	Robot.drivetrain.setLeftRightMotorOutputs(0, 0);
    	System.out.println("RUNPATH INIT");
    	System.out.println("Running path of length "+path.getTotalLength());
    	SmartDashboard.putNumber("340_Path - Current Path Length", path.getTotalLength());
    	distanceAtStart = Robot.drivetrain.getAverageDist();
    }

    private double getDistance() {
    	if(direction == Direction.FORWARDS || direction == Direction.FORWARDS_MIRRORED) {
    		return Math.abs(Robot.drivetrain.getAverageDist() - distanceAtStart);
    	} else {
    		return 1.0 - Math.abs(Robot.drivetrain.getAverageDist() - distanceAtStart);
    	}
    }

    private double deltaAngle(double currentAngle) {
    	double currentSlope = Math.tan(currentAngle * Math.PI / 180);
    	double nextSlope = dydx(getDistance());
    	
    	double angle = Math.atan((nextSlope - currentSlope)/(1 + currentSlope * nextSlope))*180/Math.PI;
    	
//    	System.out.println("m1: " + currentSlope + " m2: " + nextSlope + " dTheta: " + angle);
//    	System.out.println("Encoder: " + getDistance() + " dydx: " + dydx(getDistance()));
    	if(direction == Direction.FORWARDS || direction == Direction.BACKWARDS) {
    		return angle;
    	} else {
    		return -angle;
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double error = deltaAngle(Robot.drivetrain.getGyroAngle());
    	SmartDashboard.putNumber("340_Path - Error", error);

    	double amountOfPathDriven = getDistance()/path.getTotalLength();
    	SmartDashboard.putNumber("340_Path - amountOfPathDriven", amountOfPathDriven);
    	double driveSpeed = speed.apply(amountOfPathDriven);

    	if(Math.abs(getDistance()) > 3) {
    		Robot.drivetrain.setLeftRightMotorOutputs(
        			(driveSpeed+((error)/(arcDivisor/Math.abs(driveSpeed)))),
        			(driveSpeed-(((error)/(arcDivisor/Math.abs(driveSpeed))))));
    	} else {
    		Robot.drivetrain.setLeftRightMotorOutputs(driveSpeed, driveSpeed);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	double robotDistance = getDistance();
    	boolean result = robotDistance > path.getTotalLength();
    	if(result) {
    		System.out.println("About to return true to finish RunPath.");
    		System.out.println("Robot distance " + robotDistance);
    		System.out.println("Path distance "+path.getTotalLength());
    	}
    	return result;
//        return Math.abs(getDistance()) > path.getTotalLength();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.setLeftRightMotorOutputs(0, 0);
    	System.out.println("Finished run path!");
    	System.out.println("Robot distance at "+getDistance());
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
