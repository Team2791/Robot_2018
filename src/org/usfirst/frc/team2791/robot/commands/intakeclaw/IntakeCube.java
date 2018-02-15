package org.usfirst.frc.team2791.robot.commands.intakeclaw;

import org.usfirst.frc.team2791.robot.Robot;
import org.usfirst.frc.team2791.robot.subsystems.Manipulator;


import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team2791.robot.util.Constants;


public class IntakeCube extends Command {
    private Manipulator manipulator;

    public IntakeCube() {
    	requires(Robot.manipulator);
        manipulator = Robot.manipulator;
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
                manipulator.setLeftRightMotorSpeed(Constants.INTAKE_SPEED, 0.5 * Constants.INTAKE_SPEED);
            } else {
                // if we don't have the cube and it is not jammed run normally
                manipulator.setLeftRightMotorSpeed(Constants.INTAKE_SPEED, Constants.INTAKE_SPEED);
            }
        }
    }


    @Override
    protected boolean isFinished() {
        return manipulator.isCubeInGripper();
    }

    @Override
    protected void end() {
    	manipulator.setLeftRightMotorSpeed(Constants.HOLD_SPEED, Constants.HOLD_SPEED);
    }

    @Override
    protected void interrupted() {
        end();
    }
}
