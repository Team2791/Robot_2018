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
                Robot.lift.setBreak(false);
            } else if (abs(diff) > CLOSE) {
                Robot.lift.setPower(-diffSign * SMALL_NUMBER);
                Robot.lift.setBreak(false);
            } else {
                Robot.lift.setPower(0);
                Robot.lift.setBreak(true);
            }

        }
    }
    @Override
    public boolean isFinished() {
        return false;
    }
    @Override
    protected void end () {

    }
    protected void interrupted () {

    }
}
}
}
