package org.usfirst.frc.team2791.robot.commands.lift;
import static java.lang.Math.abs;

import org.usfirst.frc.team2791.robot.Constants;
import org.usfirst.frc.team2791.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class SetLiftHeight extends Command {
    private double targetHeight;
    // Height measered inches
    public SetLiftHeight(double height) {
        super("GoToHeight");
        requires(Robot.lift);
        targetHeight = height;
    }

    protected void initialize() {
    	Robot.lift.setBreak(false);
    }

    @Override
    protected void execute(){
        double diff = Robot.lift.getHeight() - targetHeight;
        int diffSign = (int) Math.signum(diff);
        if (abs(diff) > Constants.FAR_AWAY_DISTANCE) {
            Robot.lift.setPower(-diffSign * Constants.FAR_AWAY_POWER);
            Robot.lift.setBreak(false);
         // we special case going to 0 because we need to hit it almost exactly.
        } else if (abs(diff) > Constants.CLOSE_DISTANCE || (
        		targetHeight <=0.01 && abs(diff) > 0.1)) {
            Robot.lift.setPower(-diffSign * Constants.CLOSE_POWER);
            Robot.lift.setBreak(false);
        } else {
            Robot.lift.setPower(0);
            Robot.lift.setBreak(true);
        }
    }
    
    @Override
    public boolean isFinished() {
    	double diff = Robot.lift.getHeight() - targetHeight; // TODO make this a method
    	if(targetHeight <=0.01) {
    		return abs(diff) < 0.1; 
    	} else {
    		return Math.abs(diff) < Constants.CLOSE_DISTANCE;
    	}
   }
    @Override
    protected void end () {
    	Robot.lift.setPower(0);
    	Robot.lift.setBreak(true);
    }
    protected void interrupted () {
    	end();
    }
}
