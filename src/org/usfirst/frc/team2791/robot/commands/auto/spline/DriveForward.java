package org.usfirst.frc.team2791.robot.commands.auto.spline;

import org.usfirst.frc.team2791.robot.commands.drivetrain.traj.DrivePath;
import org.usfirst.frc.team2791.robot.util.Paths;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DriveForward extends CommandGroup {
	public DriveForward() {
		addSequential(new DrivePath(Paths.driveForward));

}
}