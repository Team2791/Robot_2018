package org.usfirst.frc.team2791.robot.commands.drivetrain.limelightTarget;


//import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import org.usfirst.frc.team2791.robot.Constants;
import org.usfirst.frc.team2791.robot.Robot;
import org.usfirst.frc.team2791.robot.util.Limelight;

import edu.wpi.first.wpilibj.command.Command;


public abstract class AbstractDriveTowardLimelightTarget extends Command {
    private Limelight limelight;
    private double limelightTurningKp = Constants.LIMELIGHT_TURNING_KP;
    protected double driveSpeed;

        public AbstractDriveTowardLimelightTarget(double speed) {
        	driveSpeed = speed;
        	limelight = Robot.limelight;
    }

    /**
     * The execute method is called repeatedly when this Command is
     * scheduled to run until this Command either finishes or is canceled.
     */

    @Override
    protected void execute() {
    	if(!limelight.targetValid()) {
    		// TODO make this only print out the message the first loop we don't see the target so we don't flood the terminal.
    		System.out.println("Can not see target. Waiting to see target.");
    	} else {

    		// drive turn towards the target.
    		double error = limelight.getHorizontalOffset();
    		double steering = error * Constants.LIMELIGHT_TURNING_DIVISOR;

    		// Cap the steering
            if(steering > Constants.LIMELIGHT_TURNING_CAP){
                steering = Constants.LIMELIGHT_TURNING_CAP;
            }
            else if(steering < Constants.LIMELIGHT_TURNING_CAP){
                steering = -Constants.LIMELIGHT_TURNING_CAP;
            }
    		if(error > limelightTurningKp) {
    		    Robot.drivetrain.setLeftRightMotorOutputs(steering, -steering);
                //Robot.drivetrain.setLeftRightMotorOutputs(driveSpeed + steering, driveSpeed - steering);
                System.out.println("Turning Right: " + steering);
            }
            else if(error < -limelightTurningKp){
    		    Robot.drivetrain.setLeftRightMotorOutputs(steering, -steering);
                //Robot.drivetrain.setLeftRightMotorOutputs(driveSpeed + steering, driveSpeed - steering);
                System.out.println("Turning Left: " + steering);
            }
            else{
    		    Robot.drivetrain.setLeftRightMotorOutputs(-steering, -steering); // <----Need to play around with the negatives
            }
    	}
    }
    
    protected void end() {
    	Robot.drivetrain.setLeftRightMotorOutputs(0.0, 0.0);
    }
    
    protected void interupted() {
    	end();
    }
}

