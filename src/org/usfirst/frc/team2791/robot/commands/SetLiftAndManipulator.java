package org.usfirst.frc.team2791.robot.commands;

import org.usfirst.frc.team2791.robot.commands.lift.SetLiftHeight;
import org.usfirst.frc.team2791.robot.commands.manipulator.SetManipulatorRetracted;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class SetLiftAndManipulator extends CommandGroup {
    public SetLiftAndManipulator(double height, boolean retract) {
        addParallel(new SetLiftHeight(height));
        addParallel(new SetManipulatorRetracted(retract));
    }
}
