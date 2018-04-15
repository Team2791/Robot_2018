package org.usfirst.frc.team2791.robot.commands.auto.GrrPaths;

import java.util.function.Function;

import org.usfirst.frc.team2791.pathing.RunPath;
import org.usfirst.frc.team2791.pathing.ShakerPaths;
import org.usfirst.frc.team2791.robot.Constants;
import org.usfirst.frc.team2791.robot.commands.lift.LowerLiftAfterDelay;
import org.usfirst.frc.team2791.robot.commands.lift.SetLiftHeightBangBang;
import org.usfirst.frc.team2791.robot.commands.lift.SetLiftPositionAfterDelay;
import org.usfirst.frc.team2791.robot.commands.manipulator.ExtendManipulatorAutonStart;
import org.usfirst.frc.team2791.robot.commands.manipulator.IntakeCube;
import org.usfirst.frc.team2791.robot.commands.manipulator.ShootCube;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class GrrPathScoreSwitch extends CommandGroup {
	Function<Double, Double> driveToSwitchSpeedFunction = x -> {
		if (x < 0.15) {
			return 0.4;
		} else if (x < 0.8) {
			return 0.75;
		} else {
			return 0.20;
		}
	};
	Function<Double, Double> backupFromSwitchSpeedFunction = x -> {
		if (x < 0.35) {
			return -0.45;
		} else {
			return -0.30;
		}
	};
	
	public GrrPathScoreSwitch(boolean isRight) {
		addParallel(new SetLiftHeightBangBang(Constants.AUTON_EXTENDED_SWITCH_HEIGHT));
		addParallel(new ExtendManipulatorAutonStart());
		if(isRight) {
			addSequential(new RunPath(ShakerPaths.FROM_CENTER.SWITCH_RIGHT, driveToSwitchSpeedFunction), 2.5);
		} else {
			addSequential(new RunPath(ShakerPaths.FROM_CENTER.SWITCH_LEFT, driveToSwitchSpeedFunction), 2.6);
		}
		addSequential(new ShootCube(Constants.LARGE_OUTPUT_SPEED), 0.25);
		addParallel(new LowerLiftAfterDelay(1));
		
		if(isRight) {
			addSequential(new RunPath(ShakerPaths.FROM_CENTER.SWITCH_TO_CUBE_LEFT, backupFromSwitchSpeedFunction, RunPath.Direction.BACKWARDS_MIRRORED));
		} else {
			addSequential(new RunPath(ShakerPaths.FROM_CENTER.SWITCH_TO_CUBE_LEFT, backupFromSwitchSpeedFunction, RunPath.Direction.BACKWARDS));
		}

		addParallel(new IntakeCube());
		addSequential(new RunPath(ShakerPaths.straightPath(33), 0.35));
		addSequential(new RunPath(ShakerPaths.straightPath(33), -0.6, RunPath.Direction.BACKWARDS));
		
		addParallel(new SetLiftHeightBangBang(Constants.AUTON_EXTENDED_SWITCH_HEIGHT));
		if(isRight) {
			addSequential(new RunPath(ShakerPaths.FROM_CENTER.SWITCH_TO_CUBE_LEFT, driveToSwitchSpeedFunction, RunPath.Direction.FORWARDS_MIRRORED));
		} else {
			addSequential(new RunPath(ShakerPaths.FROM_CENTER.SWITCH_TO_CUBE_LEFT, driveToSwitchSpeedFunction, RunPath.Direction.FORWARDS));
		}
		addSequential(new ShootCube(Constants.LARGE_OUTPUT_SPEED), 0.25);
		
		addParallel(new SetLiftPositionAfterDelay(1, 5.25));
		if(isRight) {
			addSequential(new RunPath(ShakerPaths.FROM_CENTER.SWITCH_TO_CUBE_LEFT, backupFromSwitchSpeedFunction, RunPath.Direction.BACKWARDS_MIRRORED));
		} else {
			addSequential(new RunPath(ShakerPaths.FROM_CENTER.SWITCH_TO_CUBE_LEFT, backupFromSwitchSpeedFunction, RunPath.Direction.BACKWARDS));
		}
		addSequential(new RunPath(ShakerPaths.straightPath(30), 0.3));
		addParallel(new IntakeCube());
	}
}
