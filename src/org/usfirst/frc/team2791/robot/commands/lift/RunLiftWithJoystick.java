package org.usfirst.frc.team2791.robot.commands.lift;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team2791.robot.OI;
import org.usfirst.frc.team2791.robot.Robot;

import static org.usfirst.frc.team2791.robot.util.Constants.POWER;

public class RunLiftWithJoystick extends Command {
    public RunLiftWithJoystick(){
        //idk what requires things in here
    }
    protected void initiliaze(){

    }

    @Override
    protected void execute() {
        double upSpeed =Robot.oi.driver.getAxisLeftX()*POWER;
        double downSpeed=Robot.oi.driver.getAxisLeftY()*POWER;
        Robot.lift.setMotorPower( upSpeed , downSpeed);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
    @Override
    protected void end(){
        Robot.lift.setMotorPower(0,0);

    }
    @Override
    protected void interrupted(){

    }
}
