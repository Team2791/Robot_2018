package org.usfirst.frc.team2791.robot.commands.drivetrain.limelightTarget;


//import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import org.usfirst.frc.team2791.robot.Robot;

import edu.wpi.first.wpilibj.Timer;


public class DriveTowardLimelightTargetStopWithDistance extends AbstractDriveTowardLimelightTarget {
    private Timer timer;
    private double distanceToTravel, targetDistance;
    private boolean goingForward;


    public DriveTowardLimelightTargetStopWithDistance(double speed, double distance) {
    	super(speed);
    	requires(Robot.drivetrain);
    	distanceToTravel = distance;
    }
    
    /**
     * The initialize method is called just before the first time
     * this Command is run after being started.
     */
    @Override
    protected void initialize() {
    	targetDistance = Robot.drivetrain.getAverageDist() + distanceToTravel;
    	goingForward = distanceToTravel > 0; // record if we're going
    }

    @Override
    protected boolean isFinished(){
    	// if we are driving forwards we want to stop when our distance is greater than the target
    	// if we are driving backwards we want to stop when our distance is less than the target

    	// the driveSpeed varaible comes from the super class
    	if(driveSpeed >= 0) {
    		return Robot.drivetrain.getAverageDist() >= targetDistance;
    	} else {
    		return Robot.drivetrain.getAverageDist() <= targetDistance;
    	}
    }
}

