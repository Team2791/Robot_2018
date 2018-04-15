package org.usfirst.frc.team2791.robot.commands.manipulator;

import org.usfirst.frc.team2791.robot.commands.GenericPause;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ExtendManipulatorAutonStart extends CommandGroup {

    public ExtendManipulatorAutonStart() {
    	addSequential(new SetManipulatorRetracted(true));
    	addSequential(new GenericPause(0.6));
    	addSequential(new SetManipulatorRetracted(false));
    }
}
