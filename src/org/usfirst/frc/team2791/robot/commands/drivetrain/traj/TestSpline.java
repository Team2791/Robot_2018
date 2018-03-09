package org.usfirst.frc.team2791.robot.commands.drivetrain.traj;
 

import org.usfirst.frc.team2791.robot.util.Paths;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TestSpline extends CommandGroup{

	public TestSpline() {
		addSequential(new DrivePath(Paths.driveForward));
	}

}