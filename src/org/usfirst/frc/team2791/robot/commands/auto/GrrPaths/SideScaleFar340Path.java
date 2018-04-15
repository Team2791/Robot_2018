package org.usfirst.frc.team2791.robot.commands.auto.GrrPaths;

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

public class SideScaleFar340Path extends CommandGroup {
	public SideScaleFar340Path(boolean leftSide) {
		addParallel(new SetManipulatorRetracted(true));
		addSequential(new RunPath(ShakerPaths.FROM_RIGHT.TravelToLeftScale, x -> {
			if (x < 0.03) {
				return 0.45;
			} else if (x < 0.8) {
				return 0.85;
			} else {
				return 0.85;
			}
		}));
		addParallel(new SetLiftHeightBangBang(Constants.AUTON_SCALE_HEIGHT));
//		addParallel(new SetLiftHeightMagicMotionTopOrBottom(true));
//		addParallel(new SetLiftHeightMagicMotion(10));
		addParallel(new SetManipulatorRetracted(false));
		addSequential(new RunPath(ShakerPaths.FROM_RIGHT.DriveIntoLeftScale, x -> {
			if (x < 0.4) {
				return 0.65;
			} else {
				return 0.22;
			}
		}));
		addSequential(new PauseDrivetrain(0.5)); // wait to drift into the null zone
		addSequential(new ShootCube(Constants.LARGE_OUTPUT_SPEED), 0.75);
		
		// backup and lower the lift
		addParallel(new LowerLiftAfterDelay(1.5));
		addSequential(new RunPath(ShakerPaths.FROM_RIGHT.BackupFromLeftScale, x -> {
			if (x < 0.15) {
				return -0.2;
			} else {
				return -0.35;
			}
		}, RunPath.Direction.BACKWARDS));		
		// pickup the next cube
		//addSequential(new SetManipulatorRetracted(true));
		addSequential(new RunPath(ShakerPaths.FROM_RIGHT.GetCube, x -> 0.3));
		addParallel(new IntakeCube());
		addSequential(new RunPath(ShakerPaths.FROM_RIGHT.GetCube, x -> 0.3, RunPath.Direction.BACKWARDS));
	}
}
