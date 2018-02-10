package org.usfirst.frc.team2791.robot.commands.lift;

import org.usfirst.frc.team2791.robot.OI;
import org.usfirst.frc.team2791.robot.Robot;
import org.usfirst.frc.team2791.robot.util.Constants;

import edu.wpi.first.wpilibj.command.Command;

public class RunLiftWithJoystick extends Command {
    public RunLiftWithJoystick(){
        requires(Robot.lift);
    }
    protected void initialize(){
    	Robot.lift.setBreak(false);
    }

    @Override
    protected void execute() {
        double speed = OI.operator.getAxisLeftY() * Constants.MANUAL_POWER;
        Robot.lift.setPower(speed);
        Robot.lift.setBreak(false);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end(){
    }
    
    @Override
    protected void interrupted(){

    }
}
