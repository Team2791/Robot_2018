package org.usfirst.frc.team2791.robot.commands.lift;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team2791.robot.Constants;
import org.usfirst.frc.team2791.robot.Robot;
import org.usfirst.frc.team2791.robot.subsystems.ShakerLift;

import static java.lang.Math.abs;

public class SetLiftHeightMagicMotion extends Command {
    // Height measered inches
    private double targetHeight;
    private ShakerLift lift;
    public SetLiftHeightMagicMotion(double height) {
        super("GoToHeight");
        requires(Robot.lift);
        this.targetHeight = height;
        this.lift = Robot.lift;

    }


    /**
     * The initialize method is called just before the first time
     * this Command is run after being started.
     */
    @Override
    protected void initialize() {
        lift.setBreak(false);
        lift.setTarget(targetHeight);
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
    	if(targetHeight <= 0.01) {
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
    @Override
    protected void interrupted() {
        super.interrupted();
    }
}
