package org.usfirst.frc.team2791.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RunCommandAfterDelay extends CommandGroup {

    public RunCommandAfterDelay(double delay, Command commandToRun) {
        addSequential(new GenericPause(delay));
        addSequential(commandToRun);
    }
}
