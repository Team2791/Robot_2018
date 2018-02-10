package org.usfirst.frc.team2791.robot.commands.lift;
import static java.lang.Math.abs;

import org.usfirst.frc.team2791.robot.Robot;
import org.usfirst.frc.team2791.robot.util.Constants;

import edu.wpi.first.wpilibj.command.Command;

public class GoToHeight extends Command {
    private double targetHeight;
    public GoToHeight(double height) {
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
        } else if (abs(diff) > Constants.CLOSE_DISTANCE) {
            Robot.lift.setPower(-diffSign * Constants.CLOSE_POWER);
            Robot.lift.setBreak(false);
        } else {
            Robot.lift.setPower(0);
            Robot.lift.setBreak(true);
        }
    }
    
    @Override
    public boolean isFinished() {
        return Math.abs(Robot.lift.getHeight() - targetHeight) < Constants.CLOSE_DISTANCE;
    }
    @Override
    protected void end () {

    }
    protected void interrupted () {

    }
}
