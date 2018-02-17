package org.usfirst.frc.team2791.robot.commands.auto;

import static org.usfirst.frc.team2791.robot.Robot.drivetrain;
import static org.usfirst.frc.team2791.robot.util.Constants.LINE_DISTANCE;
import static org.usfirst.frc.team2791.robot.util.Constants.SMALL_DISTANCE;

import org.usfirst.frc.team2791.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DriveForward extends Command {
    private double speed;
    public DriveForward(){
        requires(drivetrain);
    }
    public void intialize(){
}
    public void execute() {
        if (LINE_DISTANCE > Robot.drivetrain.getAverageDist()) {
            speed = .3;
        } else if (SMALL_DISTANCE < Robot.drivetrain.getAverageDist() && LINE_DISTANCE > Robot.drivetrain.getAverageDist()) {
            speed = .1;
        } else if (LINE_DISTANCE == Robot.drivetrain.getAverageDist()) {
            speed = 0;
        }
        Robot.drivetrain.setLeftRightMotorOutputs(speed, speed);
}

    @Override
    protected boolean isFinished() {
        return false;
    }
}
