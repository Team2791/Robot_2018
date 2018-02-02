package org.usfirst.frc.team2791.robot.commands;
import org.usfirst.frc.team2791.robot.subsystems.ShakerLift;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team2791.robot.Robot;

import static java.lang.Math.abs;
import static javax.swing.plaf.basic.BasicTableUI.Actions.sign;
import static org.usfirst.frc.team2791.robot.util.Constants.*;

public class GoToHeight extends Command {
    public GoToHeight() {
        super("GoToHeight");
        requires(Robot.lift);
    }

    protected void initialize() {

    }

    @Override
    protected void execute(){
        public double traveltoHeight ( double height){
            double diff = Robot.lift.getHeight() - height;
            double diffsign = sign(diff);
            if (abs(diff) > FAR_AWAY) {
                setPower(-diffsign * LARGE_NUMBER);

            } else if (abs(diff) > CLOSE) {
                setPower(-diffsign * SMALL_NUMBER);
            } else {
                setPower(0);

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
