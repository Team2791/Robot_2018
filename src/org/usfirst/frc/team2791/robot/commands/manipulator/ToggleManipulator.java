package org.usfirst.frc.team2791.robot.commands.manipulator;

import org.usfirst.frc.team2791.robot.Constants;
import org.usfirst.frc.team2791.robot.Robot;
import org.usfirst.frc.team2791.robot.subsystems.Manipulator;

import edu.wpi.first.wpilibj.command.Command;

class ToggleManipulator{
    public ToggleManipulator() {
    	requires(Robot.manipulator);
    }

    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
    	Robot.manipulator.setRetracted(!Robot.manipulator.getRetracted());
    }


    @Override
    protected boolean isFinished() {
        return true;
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
