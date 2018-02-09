package org.usfirst.frc.team2791.robot.commands.auto;

import org.usfirst.frc.team2791.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DriveForward extends Command {
    public DriveForward(){
        requires(Robot.drivetrain);
    }
    
    public void execute() {
    	// TODO PUT SOME CODE HERE
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
