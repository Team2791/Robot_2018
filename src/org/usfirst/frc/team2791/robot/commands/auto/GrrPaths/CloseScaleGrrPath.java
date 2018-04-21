package org.usfirst.frc.team2791.robot.commands.auto.GrrPaths;

import java.util.function.Function;

import org.usfirst.frc.team2791.pathing.RunPath;
import org.usfirst.frc.team2791.pathing.ShakerPaths;
import org.usfirst.frc.team2791.robot.Constants;
import org.usfirst.frc.team2791.robot.commands.auto.bangbang.DriveEncoderBangBang;
import org.usfirst.frc.team2791.robot.commands.auto.bangbang.TurnGyroBangBang;
import org.usfirst.frc.team2791.robot.commands.drivetrain.PauseDrivetrain;
import org.usfirst.frc.team2791.robot.commands.lift.LowerLiftAfterDelay;
import org.usfirst.frc.team2791.robot.commands.lift.RaiseLiftToTopAfterDelay;
import org.usfirst.frc.team2791.robot.commands.lift.SetLiftHeightBangBang;
import org.usfirst.frc.team2791.robot.commands.lift.SetLiftHeightMagicMotionTopOrBottom;
import org.usfirst.frc.team2791.robot.commands.manipulator.IntakeCube;
import org.usfirst.frc.team2791.robot.commands.manipulator.SetManipulatorRetracted;
import org.usfirst.frc.team2791.robot.commands.manipulator.ShootCube;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CloseScaleGrrPath extends CommandGroup {
	
	Function<Double, Double> driveToScaleSpeedFunc = x -> {
		if (x < 0.15) {
			return 0.95;
		} else if (x < 0.85) {
			return 0.7;
		} else {
			return 0.30;
		}
	};
	
	Function<Double, Double> backupToScaleSpeedFunc = x -> {
		if (x < 0.15) {
			return -0.45;
		} else if (x < 0.7) {
			return -0.7;
		} else {
			return -0.35;
		}
	};
	
	public CloseScaleGrrPath(boolean onLeftSide) {
		addParallel(new SetLiftHeightBangBang(Constants.AUTON_SCALE_HEIGHT));
//		addParallel(new ExtendManipulatorAutonStart());
		addParallel(new SetManipulatorRetracted(true));
		if(onLeftSide) {
			addSequential(new RunPath(ShakerPaths.FROM_RIGHT.DriveIntoRightScale, driveToScaleSpeedFunc, RunPath.Direction.FORWARDS_MIRRORED));
		} else {
			addSequential(new RunPath(ShakerPaths.FROM_RIGHT.DriveIntoRightScale, driveToScaleSpeedFunc, RunPath.Direction.FORWARDS));
		}
		addSequential(new PauseDrivetrain(0.3));
		addSequential(new ShootCube(Constants.LARGE_OUTPUT_SPEED, 0.3));
		addParallel(new SetManipulatorRetracted(false));
		addParallel(new LowerLiftAfterDelay(0.8));
		if(onLeftSide) {
			addSequential(new RunPath(ShakerPaths.FROM_RIGHT.BackRightScale, -0.35, RunPath.Direction.BACKWARDS_MIRRORED));
		} else {
			addSequential(new RunPath(ShakerPaths.FROM_RIGHT.BackRightScale, -0.35, RunPath.Direction.BACKWARDS));
		}
		
		addParallel(new IntakeCube());
		// This path is from the far side 2 cube!
		if(onLeftSide) {
			addSequential(new RunPath(ShakerPaths.FROM_RIGHT.GetCubeFromRightScale, 0.3, RunPath.Direction.FORWARDS_MIRRORED));
		} else {
			addSequential(new RunPath(ShakerPaths.FROM_RIGHT.GetCubeFromRightScale, 0.3, RunPath.Direction.FORWARDS));
		}
//		addParallel(new RaiseLiftToTopAfterDelay(1)); // This hit the scale
		addParallel(new SetManipulatorRetracted(true));
		if(onLeftSide) {
			addSequential(new RunPath(ShakerPaths.FROM_RIGHT.BackUpToRightScale, backupToScaleSpeedFunc, RunPath.Direction.BACKWARDS_MIRRORED)); // this is not tested needs to be tested in order for it to be correct
		} else {
			addSequential(new RunPath(ShakerPaths.FROM_RIGHT.BackUpToRightScale, backupToScaleSpeedFunc, RunPath.Direction.BACKWARDS));
		}
		addSequential(new SetLiftHeightBangBang(Constants.AUTON_SCALE_HEIGHT));
		addParallel(new SetManipulatorRetracted(false));
		addSequential(new DriveEncoderBangBang(0.5, 0, 6));
		addSequential(new ShootCube(Constants.LARGE_OUTPUT_SPEED, 0.5));
		
		// backup, lower the lift and turn
//		addParallel(new SetManipulatorRetracted(false));
		addSequential(new DriveEncoderBangBang(-0.5, 0, -12));
		addParallel(new SetLiftHeightMagicMotionTopOrBottom(false));
		addSequential(new TurnGyroBangBang(30, 0.4));
		addSequential(new PauseDrivetrain(10));
	}

}
