package org.usfirst.frc.team2791.robot.commands.auto.spline;

import org.usfirst.frc.team2791.robot.Constants;
import org.usfirst.frc.team2791.robot.commands.drivetrain.PauseDrivetrain;
import org.usfirst.frc.team2791.robot.commands.drivetrain.traj.DrivePath;
import org.usfirst.frc.team2791.robot.commands.drivetrain.traj.DrivePathReversed;
import org.usfirst.frc.team2791.robot.commands.lift.SetLiftHeight;
import org.usfirst.frc.team2791.robot.commands.manipulator.SetManipulatorRetracted;
import org.usfirst.frc.team2791.robot.commands.manipulator.ShootCube;

import org.usfirst.frc.team2791.robot.util.Paths;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class RightSideNearScale extends CommandGroup {
    public RightSideNearScale() {
        addSequential(new PauseDrivetrain(0.25));
        addSequential(new DrivePath(Paths.scaleInitialDrive));
        addSequential(new SetManipulatorRetracted(true));
        addParallel(new DrivePath(Paths.nearScaleRightScore));
        addSequential(new ShootCube(Constants.SMALL_OUTPUT_SPEED));
    }
}
