package org.usfirst.frc.team2791.robot.commands.lift;

import org.usfirst.frc.team2791.robot.Constants;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RaiseLiftToTopAfterDelay extends CommandGroup {

    public RaiseLiftToTopAfterDelay(double time) {
    	addSequential(new PauseLift(time));
		addSequential(new SetLiftHeightBangBang(Constants.AUTON_SCALE_HEIGHT));
    }
}
