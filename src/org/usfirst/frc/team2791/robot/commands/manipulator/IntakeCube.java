package org.usfirst.frc.team2791.robot.commands.manipulator;

import org.usfirst.frc.team2791.robot.Constants;
import org.usfirst.frc.team2791.robot.Robot;
import org.usfirst.frc.team2791.robot.subsystems.Manipulator;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;


public class IntakeCube extends Command {
    private Manipulator manipulator;
    private Timer intakeCurrentTimer, cubeInGripperTimer;
    private boolean intakeCurrentTimerStarted, cubeInGripperTimerStarted;

    public IntakeCube() {
    	requires(Robot.manipulator);
        manipulator = Robot.manipulator;
        intakeCurrentTimer = new Timer();
        intakeCurrentTimerStarted = false;
        
        cubeInGripperTimer = new Timer();
    }

    @Override
    protected void initialize() {
        manipulator.setLeftRightMotorSpeed(Constants.INTAKE_SPEED, Constants.INTAKE_SPEED);
    }

    @Override
    protected void execute() {
        if (manipulator.isCubeInGripper()) {
            // we have the cube
            manipulator.setLeftRightMotorSpeed(Constants.HOLD_SPEED, Constants.HOLD_SPEED);
        } else {
            // we are trying to get the cube
            if (manipulator.isCubeJammed()) {
//            	System.out.println("Manipulator - FIXING JAM");
                // if the cube is jammed we want to unjam it
                manipulator.setLeftRightMotorSpeed(Constants.INTAKE_SPEED, 0);
            } else {
                // if we don't have the cube and it is not jammed run normally
                manipulator.setLeftRightMotorSpeed(Constants.INTAKE_SPEED, Constants.INTAKE_SPEED);
            }
        }
        
        if(manipulator.getCurrentUsage() > Constants.INTAKE_CUBE_STALL_CURRENT && !intakeCurrentTimerStarted) {
        	intakeCurrentTimer.start();
        	intakeCurrentTimerStarted = true;
        }
        if(! (manipulator.getCurrentUsage() > Constants.INTAKE_CUBE_STALL_CURRENT)) {
        	intakeCurrentTimer.stop();
        	intakeCurrentTimer.reset();
        	intakeCurrentTimerStarted = false;
        }

        
        if(manipulator.isCubeInGripper() && !cubeInGripperTimerStarted) {
        	cubeInGripperTimer.start();
        	cubeInGripperTimerStarted = true;
        }
        if(!manipulator.isCubeInGripper()) {
        	cubeInGripperTimer.stop();
        	cubeInGripperTimer.reset();
        	cubeInGripperTimerStarted = false;
        }
    }


    @Override
    protected boolean isFinished() {
        return manipulator.isCubeInGripper() || intakeCurrentTimer.get() > 3;
    }

    @Override
    protected void end() {
    	System.out.println("Intake cube finished.");
    }

    @Override
    protected void interrupted() {
        end();
    }
}
