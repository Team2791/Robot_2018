package org.usfirst.frc.team2791.robot.commands.lift;

import static java.lang.Math.abs;

import org.usfirst.frc.team2791.robot.Constants;
import org.usfirst.frc.team2791.robot.Robot;
import org.usfirst.frc.team2791.robot.subsystems.ShakerLift;
import org.usfirst.frc.team2791.robot.util.DelayedBoolean;

import edu.wpi.first.wpilibj.command.Command;

public class SetLiftHeightMagicMotionTopOrBottom extends Command {
    // Height measured in inches
    private double targetHeight;
    private double targetMagicMotionHeight;
    private boolean finishedMagicMotion;
   
    private final double MM_TO_BANGBANG_RANGE = 4;
    
    
    public SetLiftHeightMagicMotionTopOrBottom(boolean goingToTop) {
        super("GoToHeight");
        requires(Robot.lift);
        targetHeight = goingToTop ? Constants.LIFT_MAX_HEIGHT - 0.5 : Constants.LIFT_MIN_HEIGHT;
        targetMagicMotionHeight = goingToTop ? Constants.LIFT_MAX_HEIGHT - 2.75 : Constants.LIFT_MIN_HEIGHT + 2.5;
    }


    /**
     * The initialize method is called just before the first time
     * this Command is run after being started.
     */
    @Override
    protected void initialize() {
        Robot.lift.setBreak(false);
        double magicMotionHeightDiff = Robot.lift.getHeight() - targetMagicMotionHeight;
        if (abs(magicMotionHeightDiff) < MM_TO_BANGBANG_RANGE) {
			// exit to bang bang
			finishedMagicMotion = true;
		} else {
			finishedMagicMotion = false;
	        Robot.lift.setTargetMagicMotion(targetMagicMotionHeight);
		}
        
    }

    @Override
    protected void execute() {
    	if(!finishedMagicMotion) { // Far from Target
    		double magicMotionHeightDiff = Robot.lift.getHeight() - targetMagicMotionHeight;
    		if (abs(magicMotionHeightDiff) < MM_TO_BANGBANG_RANGE) {
    			// exit to bang bang
    			finishedMagicMotion = true;
    		}
    	} else { // close to target
        	double diff = Robot.lift.getHeight() - targetHeight;
        	int diffSign = (int) Math.signum(diff);

    		if (abs(diff) > 0.1) {
//    			System.out.println("-Diff sign "+ (-diffSign));
                Robot.lift.setPower(-diffSign * Constants.CLOSE_POWER);
                Robot.lift.setBreak(false);
    		} else {
    			Robot.lift.setPower(0);
                Robot.lift.setBreak(false);
    		}
	    }
    }

    @Override
    public boolean isFinished() {
    	return abs(Robot.lift.getHeight() - targetHeight) < 0.1;
   }

    @Override
    protected void end () {
    	System.out.println("Lift magic motion top or bottom done!");
    	Robot.lift.setPower(0);
    	Robot.lift.setBreak(true);
    }

    @Override
    protected void interrupted() {
        end();
    }
}
