package org.usfirst.frc.team2791.robot.commands.lift;
import org.usfirst.frc.team2791.robot.subsystems.ShakerLift;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team2791.robot.Robot;

import static java.lang.Math.abs;
import static org.usfirst.frc.team2791.robot.util.Constants.*;

public class GoToHeight extends Command {
    private double targetHeight;
    public GoToHeight(double height) {
        super("GoToHeight");
        requires(Robot.lift);
        targetHeight = height;
    }

    protected void initialize() {

    }

    @Override
    protected void execute(){
            double diff = Robot.lift.getHeight() - targetHeight;
            int diffSign = (int) Math.signum(diff);
            if (abs(diff) > FAR_AWAY) {
                Robot.lift.setPower(-diffSign * LARGE_NUMBER);

            } else if (abs(diff) > CLOSE) {
                Robot.lift.setPower(-diffSign * SMALL_NUMBER);

            } else {
                Robot.lift.setPower(0);
            }
        }

    @Override
    public boolean isFinished() {
        return Math.abs(Robot.lift.getHeight() - targetHeight) < SMALL_NUMBER;
    }
    @Override
    protected void end () {
    	Robot.lift.setPower(0);
    }

    protected void interrupted () {

    }
}


