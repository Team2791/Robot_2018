package org.usfirst.frc.team2791.robot.commands;

import org.usfirst.frc.team2791.robot.commands.lift.SetLiftHeightBangBang;
import org.usfirst.frc.team2791.robot.commands.manipulator.SetManipulatorRetracted;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class SetLiftAndManipulator extends CommandGroup {
    public SetLiftAndManipulator(double height, boolean retract) {
        addParallel(new SetLiftHeightBangBang(height));
        addParallel(new SetManipulatorRetracted(retract));
    }
}
