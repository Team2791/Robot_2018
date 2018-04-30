package org.usfirst.frc.team2791.robot.commands.lift;

import org.usfirst.frc.team2791.robot.Constants;
import org.usfirst.frc.team2791.robot.OI;
import org.usfirst.frc.team2791.robot.Robot;
import org.usfirst.frc.team2791.robot.subsystems.ShakerLift;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.command.Command;

public class RunLiftWithJoystick extends Command {
	Button callingButton;
	
    public RunLiftWithJoystick(Button callingButton){
        requires(Robot.lift);
        this.callingButton = callingButton;
    }
  
    protected void initialize(){
    	Robot.lift.setBreak(false);
    }

    @Override
    protected void execute() {
        double speed = OI.operator.getAxisLeftY() * Constants.MANUAL_POWER * ShakerLift.speedModifier;

        if(Robot.oi.operatorLS.get()) {
	        Robot.lift.setPowerUnsafe(speed);
        } else {
            if(Robot.lift.getHeight() < 12) {
            	speed = Math.max(-0.3, speed);
            }
        	Robot.lift.setPower(speed);
        }

        Robot.lift.setBreak(false);
    }

    @Override
    protected boolean isFinished() {
        return !callingButton.get();
    }

    @Override
    protected void end(){
    	Robot.lift.setBreak(true);
    }
    
    @Override
    protected void interrupted(){

    }
}
