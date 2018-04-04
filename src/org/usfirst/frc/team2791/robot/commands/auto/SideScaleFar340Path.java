package org.usfirst.frc.team2791.robot.commands.auto;

import org.usfirst.frc.team2791.pathing.RunPath;
import org.usfirst.frc.team2791.pathing.ShakerPaths;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class SideScaleFar340Path extends CommandGroup {
	public SideScaleFar340Path(boolean leftSide) {
		addSequential(new RunPath(ShakerPaths.FROM_RIGHT.TravelToLeftScale, 0.3));
		addSequential(new RunPath(ShakerPaths.FROM_RIGHT.DriveIntoLeftScale, 0.3));
	}
}
