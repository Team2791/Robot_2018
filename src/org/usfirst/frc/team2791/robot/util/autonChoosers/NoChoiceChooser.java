package org.usfirst.frc.team2791.robot.util.autonChoosers;

import edu.wpi.first.wpilibj.command.Command;

public class NoChoiceChooser extends AutonCommandChooser {
	Command cmd;
	public NoChoiceChooser(Command cmd) {
		this.cmd = cmd;
	}
	@Override
	public Command getCommand(boolean weOwnLeftSideNearSwitch,
			boolean weOwnLeftSideScale, boolean weOwnLeftSideFarSwitch) {
		return cmd;
	}

}
