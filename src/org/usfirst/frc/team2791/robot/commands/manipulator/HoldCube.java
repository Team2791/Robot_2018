package org.usfirst.frc.team2791.robot.commands.manipulator;

import org.usfirst.frc.team2791.robot.Robot;
import org.usfirst.frc.team2791.robot.util.Constants;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;


public class HoldCube extends Command {
	
	Timer lostCubeTimer;
	boolean timerStarted;

    public HoldCube() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.manipulator);
    	lostCubeTimer = new Timer();
    	timerStarted = false;

    }

    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
    	// if the cube is slipping out pull it back in
    	if(Robot.manipulator.isCubeInGripper()) {
    		Robot.manipulator.setLeftRightMotorSpeed(Constants.HOLD_SPEED, Constants.HOLD_SPEED);
    	} else {
    		Robot.manipulator.setLeftRightMotorSpeed(Constants.INTAKE_SPEED, Constants.INTAKE_SPEED);
    	}
    }


    @Override
    protected boolean isFinished() {
    	// return true when we have lost the cube for whatever reason
    	// to avoid accidentally stopping the command when the cube is just loose
    	// we have a timer to make sure we've really lost the cube and it's not
    	// just loose while we're turning
    	if(!Robot.manipulator.isCubeInGripper() && !timerStarted) {
    		timerStarted = true;
    		lostCubeTimer.start();
    	}
    	
    	if(Robot.manipulator.isCubeInGripper()) {
    		lostCubeTimer.reset();
    		lostCubeTimer.stop();
    		timerStarted = false;
    	}
    	
        return lostCubeTimer.get() > 2;
    }

    @Override
    protected void end() {
    	Robot.manipulator.setLeftRightMotorSpeed(0,0);
    }

    @Override
    protected void interrupted() {
        end();
    }
}
