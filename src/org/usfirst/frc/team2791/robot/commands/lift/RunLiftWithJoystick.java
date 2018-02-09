package org.usfirst.frc.team2791.robot.commands.lift;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team2791.robot.OI;
import org.usfirst.frc.team2791.robot.Robot;
import org.usfirst.frc.team2791.robot.subsystems.ShakerLift;

import static org.usfirst.frc.team2791.robot.util.Constants.POWER;

public class RunLiftWithJoystick extends Command {
    public RunLiftWithJoystick(){
        requires(Robot.lift);
    }
    protected void initialize(){

    }

    @Override
    protected void execute() {
        double speed = OI.driver.getAxisLeftY()*POWER;
        Robot.lift.setPower(speed);
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
