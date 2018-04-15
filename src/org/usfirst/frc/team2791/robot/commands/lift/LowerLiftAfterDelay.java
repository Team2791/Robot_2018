package org.usfirst.frc.team2791.robot.commands.lift;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LowerLiftAfterDelay extends CommandGroup {

    public LowerLiftAfterDelay(double time) {
    	addSequential(new PauseLift(time));
		addSequential(new SetLiftHeightMagicMotionTopOrBottom(false));
    }
}
