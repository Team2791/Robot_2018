package org.usfirst.frc.team2791.robot.util.autonChoosers;

import edu.wpi.first.wpilibj.command.Command;

public abstract class AutonCommandChooser {
	
	public abstract Command getCommand(boolean weOwnLeftSideNearSwitch, boolean weOwnLeftSideScale, boolean weOwnLeftSideFarSwitch);

}
