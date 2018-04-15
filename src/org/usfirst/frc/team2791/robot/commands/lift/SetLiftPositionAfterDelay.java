package org.usfirst.frc.team2791.robot.commands.lift;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class SetLiftPositionAfterDelay extends CommandGroup {

    public SetLiftPositionAfterDelay(double time, double position) {
    	addSequential(new PauseLift(time));
		addSequential(new SetLiftHeightMagicMotion(position));
    }
}
