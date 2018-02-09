package org.usfirst.frc.team2791.robot.commands.intakeclaw;

import org.usfirst.frc.team2791.robot.Robot;
import org.usfirst.frc.team2791.robot.subsystems.Manipulator;
import org.usfirst.frc.team2791.robot.util.Constants;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Timer;


public class ShootCube extends Command {
    private Manipulator manipulator;
    private Timer timer;
    public ShootCube() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        manipulator = Robot.manipulator;
        timer = new Timer();

    }

    @Override
    protected void initialize() {
        timer.reset();
        timer.start();
        manipulator.setCubeArmsClosed(false);

    }


    @Override
    protected void execute() {
        if (timer.get() >= 0.25) {
            manipulator.setLeftRightMotorSpeed(Constants.OUTPUT_SPEED, Constants.OUTPUT_SPEED);
        }
    }

    @Override
    protected boolean isFinished() {
        // TODO: Make this return true when this Command no longer needs to run execute()
        if(timer.get() > 1.5) {
            return !manipulator.isCubeInGripper();
        }
        return false;
    }

    @Override
    protected void end() {

        manipulator.setLeftRightMotorSpeed(0, 0);
        timer.stop();
    }

    @Override
    protected void interrupted() {
        end();
    }
}
