package org.usfirst.frc.team2791.robot.commands.lift;
import org.usfirst.frc.team2791.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class StopLift extends Command {

    public StopLift() {
    	requires(Robot.lift);
    }

    protected void initialize() {

    }

    @Override
    protected void execute() {
    	Robot.lift.setPower(0);
    }
    
    @Override
    public boolean isFinished() {
        return true;
    }
    @Override
    protected void end () {

    }
    protected void interrupted () {

    }
}
