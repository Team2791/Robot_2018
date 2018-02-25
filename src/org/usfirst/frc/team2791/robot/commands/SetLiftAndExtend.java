package org.usfirst.frc.team2791.robot.commands;

import org.usfirst.frc.team2791.robot.Constants;
import org.usfirst.frc.team2791.robot.commands.lift.SetLiftHeight;
import org.usfirst.frc.team2791.robot.commands.manipulator.SetManipulatorRetracted;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class SetLiftAndExtend extends CommandGroup {
	public SetLiftAndExtend(double height) {
        addParallel(new SetLiftHeight(height));
        addParallel(new SetManipulatorRetracted(false));
    }
    
    public SetLiftAndExtend(double height, boolean retract) {
        addParallel(new SetLiftHeight(height));
        addParallel(new SetManipulatorRetracted(retract));
    }
}
