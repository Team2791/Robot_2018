package org.usfirst.frc.team2791.robot.commands.manipulator;

import org.usfirst.frc.team2791.robot.Constants;
import org.usfirst.frc.team2791.robot.Robot;
import org.usfirst.frc.team2791.robot.subsystems.Manipulator;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Timer;


public class ShootCube extends Command {
    private double speed;
    private Timer timer;
    public ShootCube(double outputSpeed) {
    	requires(Robot.manipulator);
        speed = outputSpeed;
        timer = new Timer();
    }

    @Override
    protected void initialize() {
        timer.reset();
        timer.start();
    }


    @Override
    protected void execute() {
        Robot.manipulator.setLeftRightMotorSpeed(speed, speed);


        }


    @Override
    protected boolean isFinished() {
        // TODO: Make this return true when this Command no longer needs to run execute()
        return timer.get() > 2.5;
    }

    @Override
    protected void end() {
        Robot.manipulator.setLeftRightMotorSpeed(0, 0);
        timer.stop();
    }

    @Override
    protected void interrupted() {
        end();
    }
}
