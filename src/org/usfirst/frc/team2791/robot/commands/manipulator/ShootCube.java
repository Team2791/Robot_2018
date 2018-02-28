package org.usfirst.frc.team2791.robot.commands.manipulator;

import org.usfirst.frc.team2791.robot.Constants;
import org.usfirst.frc.team2791.robot.Robot;
import org.usfirst.frc.team2791.robot.subsystems.Manipulator;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Timer;


public class ShootCube extends Command {
    private double speed;
    
    public ShootCube(double outputSpeed, double timeout) {
    	requires(Robot.manipulator);
        speed = outputSpeed;
        setTimeout(timeout);
    }
    
    public ShootCube(double outputSpeed) {
    	this(outputSpeed, 2);
    }

    @Override
    protected void initialize() {
    }


    @Override
    protected void execute() {
        Robot.manipulator.setLeftRightMotorSpeed(speed, speed);
       }


    @Override
    protected boolean isFinished() {
        // TODO: Make this return true when this Command no longer needs to run execute()
        return isTimedOut();
    }

    @Override
    protected void end() {
        Robot.manipulator.setLeftRightMotorSpeed(0, 0);
    }

    @Override
    protected void interrupted() {
        end();
    }
}
