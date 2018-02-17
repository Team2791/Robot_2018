package org.usfirst.frc.team2791.robot.util;

import edu.wpi.first.wpilibj.command.CommandGroup;

public abstract class DelayedCommandGroup extends CommandGroup {
	boolean initRunOnce = false;
	
	/**
	 * This method is used to set up the command in auton init
	 * AFTER we have gotten the game specific data string.
	 * In 2018 that tells us which side of the switch is ours.
	 */
	protected abstract void initCode();
	
	public void init() {
		if(initRunOnce) {
			return;
		} else {
			initCode();
			initRunOnce = true;
		}
	}
	
	
}
