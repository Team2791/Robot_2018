package org.usfirst.frc.team2791.robot.commands.auto.GrrPaths;

import java.util.function.Function;

import org.usfirst.frc.team2791.pathing.RunPath;
import org.usfirst.frc.team2791.pathing.ShakerPaths;
import org.usfirst.frc.team2791.robot.Constants;
import org.usfirst.frc.team2791.robot.commands.drivetrain.PauseDrivetrain;
import org.usfirst.frc.team2791.robot.commands.lift.LowerLiftAfterDelay;
import org.usfirst.frc.team2791.robot.commands.lift.SetLiftHeightBangBang;
import org.usfirst.frc.team2791.robot.commands.manipulator.IntakeCube;
import org.usfirst.frc.team2791.robot.commands.manipulator.SetManipulatorRetracted;
import org.usfirst.frc.team2791.robot.commands.manipulator.ShootCube;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class SideScaleFarGrrPath extends CommandGroup {
	public SideScaleFarGrrPath(boolean onLeftSide) {
		addParallel(new SetManipulatorRetracted(true));
		
		Function<Double, Double> travelToScaleSF = x -> {
			if (x < 0.03) {
				return 0.45;
			} else if (x < 0.8) {
				return 0.85;
			} else {
				return 0.85;
			}
		};
		
		if(onLeftSide) {
			addSequential(new RunPath(ShakerPaths.FROM_RIGHT.TravelToLeftScale, travelToScaleSF, RunPath.Direction.FORWARDS_MIRRORED));
		} else {
			addSequential(new RunPath(ShakerPaths.FROM_RIGHT.TravelToLeftScale, travelToScaleSF));
		}
		
		Function<Double, Double> driveIntoScaleSF = x -> {
			if (x < 0.4) {
				return 0.65;
			} else {
				return 0.22;
			}
		};
		
		addParallel(new SetLiftHeightBangBang(Constants.AUTON_SCALE_HEIGHT));
//		addParallel(new SetLiftHeightMagicMotionTopOrBottom(true));
//		addParallel(new SetLiftHeightMagicMotion(10));
		addParallel(new SetManipulatorRetracted(false));
		if(onLeftSide) {
			addSequential(new RunPath(ShakerPaths.FROM_RIGHT.DriveIntoLeftScale, driveIntoScaleSF, RunPath.Direction.FORWARDS_MIRRORED));
		} else {
			addSequential(new RunPath(ShakerPaths.FROM_RIGHT.DriveIntoLeftScale, driveIntoScaleSF, RunPath.Direction.FORWARDS));
		}
		addSequential(new PauseDrivetrain(0.5)); // wait to drift into the null zone
		addSequential(new ShootCube(Constants.LARGE_OUTPUT_SPEED), 0.75);
		
		Function<Double, Double> backupFromScaleSF =  x -> {
			if (x < 0.15) {
				return -0.2;
			} else {
				return -0.35;
			}
		};
		
		// backup and lower the lift
		addParallel(new LowerLiftAfterDelay(1.5));
		if(onLeftSide) {
			addSequential(new RunPath(ShakerPaths.FROM_RIGHT.BackupFromLeftScale, backupFromScaleSF, RunPath.Direction.BACKWARDS_MIRRORED));
		} else {
			addSequential(new RunPath(ShakerPaths.FROM_RIGHT.BackupFromLeftScale, backupFromScaleSF, RunPath.Direction.BACKWARDS));
		}
		// pickup the next cube
//		addSequential(new SetManipulatorRetracted(true));
		addParallel(new IntakeCube(), 2.5);
		if(onLeftSide) {
			addSequential(new RunPath(ShakerPaths.FROM_RIGHT.GetCubeFromLeftScale, x -> 0.3, RunPath.Direction.FORWARDS_MIRRORED));
		} else {
			addSequential(new RunPath(ShakerPaths.FROM_RIGHT.GetCubeFromLeftScale, x -> 0.3, RunPath.Direction.FORWARDS));
		}
		// add these steps when successful
		addSequential(new PauseDrivetrain(1));
		if(onLeftSide) {
			addSequential(new RunPath(ShakerPaths.FROM_RIGHT.GetCubeFromLeftScale, x-> -0.3, RunPath.Direction.BACKWARDS_MIRRORED));
		} else {
			addSequential(new RunPath(ShakerPaths.FROM_RIGHT.GetCubeFromLeftScale, x-> -0.3, RunPath.Direction.BACKWARDS));
		}
		addParallel(new SetLiftHeightBangBang(Constants.AUTON_SCALE_HEIGHT));
		if(onLeftSide) {
			addSequential(new RunPath(ShakerPaths.FROM_RIGHT.GoToLeftScale, x-> 0.3, RunPath.Direction.FORWARDS_MIRRORED));
		} else {
			addSequential(new RunPath(ShakerPaths.FROM_RIGHT.GoToLeftScale, x-> 0.3, RunPath.Direction.FORWARDS));
		}
		addParallel(new PauseDrivetrain(.5));
		addSequential(new ShootCube(Constants.LARGE_OUTPUT_SPEED), 0.5);
	}
}
