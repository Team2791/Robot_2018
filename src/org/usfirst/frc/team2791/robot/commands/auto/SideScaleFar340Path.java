package org.usfirst.frc.team2791.robot.commands.auto;

import org.usfirst.frc.team2791.pathing.RunPath;
import org.usfirst.frc.team2791.pathing.ShakerPaths;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class SideScaleFar340Path extends CommandGroup {
	public SideScaleFar340Path(boolean leftSide) {
		addSequential(new RunPath(ShakerPaths.FROM_RIGHT.TravelToLeftScale, x -> {
			if (x < 0.05) {
				return 0.4;
			} else if (x < 0.8) {
				return 0.85;
			} else {
				return 0.45;
			}
		}));
		addSequential(new RunPath(ShakerPaths.FROM_RIGHT.DriveIntoLeftScale, 0.3));
	}
}
