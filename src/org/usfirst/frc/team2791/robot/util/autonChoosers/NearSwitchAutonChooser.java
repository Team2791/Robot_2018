package org.usfirst.frc.team2791.robot.util.autonChoosers;

import edu.wpi.first.wpilibj.command.Command;

public class NearSwitchAutonChooser extends AutonCommandChooser {
	Command leftSideAuto, rightSideAuto;
	public NearSwitchAutonChooser(Command leftSideAuto, Command rightSideAuto) {
		this.leftSideAuto = leftSideAuto;
		this.rightSideAuto = rightSideAuto;
	}
	@Override
	public Command getCommand(boolean weOwnLeftSideNearSwitch,
			boolean weOwnLeftSideScale, boolean weOwnLeftSideFarSwitch) {
		if(weOwnLeftSideNearSwitch) {
			return leftSideAuto;
		} else {
			return rightSideAuto;
		}
	}
	
	
}
