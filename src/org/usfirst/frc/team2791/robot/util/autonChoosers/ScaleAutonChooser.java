package org.usfirst.frc.team2791.robot.util.autonChoosers;

import edu.wpi.first.wpilibj.command.Command;

public class ScaleAutonChooser extends AutonCommandChooser {
	Command leftSideAuto, rightSideAuto;
	public ScaleAutonChooser(Command leftSideAuto, Command rightSideAuto) {
		this.leftSideAuto = leftSideAuto;
		this.rightSideAuto = rightSideAuto;
	}
	@Override
	public Command getCommand(boolean weOwnLeftSideNearSwitch,
			boolean weOwnLeftSideScale, boolean weOwnLeftSideFarSwitch) {
		if(weOwnLeftSideScale) {
			return leftSideAuto;
		} else {
			return rightSideAuto;
		}
	}
	
	
}
