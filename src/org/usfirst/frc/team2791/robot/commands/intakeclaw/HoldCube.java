package org.usfirst.frc.team2791.robot.commands.intakeclaw;

import org.usfirst.frc.team2791.robot.Robot;
import org.usfirst.frc.team2791.robot.subsystems.Manipulator;


import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team2791.robot.util.Constants;


public class HoldCube extends Command {
    private Manipulator manipulator;

    public HoldCube() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.manipulator);

    }

    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
    	Robot.manipulator.setLeftRightMotorSpeed(Constants.HOLD_SPEED, Constants.HOLD_SPEED);
    }


    @Override
    protected boolean isFinished() {
        // TODO: Make this return true when this Command no longer needs to run execute()
        return false;
    }

    @Override
    protected void end() {
        manipulator.setLeftRightMotorSpeed(0,0);
    }

    @Override
    protected void interrupted() {
        end();
    }
}
