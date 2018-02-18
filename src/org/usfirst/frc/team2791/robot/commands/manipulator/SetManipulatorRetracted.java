package org.usfirst.frc.team2791.robot.commands.manipulator;

import org.usfirst.frc.team2791.robot.Constants;
import org.usfirst.frc.team2791.robot.Robot;
import org.usfirst.frc.team2791.robot.subsystems.Manipulator;

import edu.wpi.first.wpilibj.command.Command;


public class SetManipulatorRetracted extends Command {
    private Boolean setRetracted;

    public SetManipulatorRetracted(boolean setRetracted) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.manipulator);
    	this.setRetracted = setRetracted;

    }

    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
    	Robot.manipulator.setRetracted(setRetracted);
    }


    @Override
    protected boolean isFinished() {
        // TODO: Make this return true when this Command no longer needs to run execute()
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
