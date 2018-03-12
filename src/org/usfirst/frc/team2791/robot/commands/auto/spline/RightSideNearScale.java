package org.usfirst.frc.team2791.robot.commands.auto.spline;

import org.usfirst.frc.team2791.robot.Constants;
import org.usfirst.frc.team2791.robot.commands.SetLiftAndManipulator;
import org.usfirst.frc.team2791.robot.commands.drivetrain.PauseDrivetrain;
import org.usfirst.frc.team2791.robot.commands.drivetrain.traj.DrivePath;
import org.usfirst.frc.team2791.robot.commands.drivetrain.traj.DrivePathReversed;
import org.usfirst.frc.team2791.robot.commands.manipulator.ShootCube;

import org.usfirst.frc.team2791.robot.util.Paths;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class RightSideNearScale extends CommandGroup {
    public RightSideNearScale() {
        
        addSequential(new DrivePathReversed(Paths.nearScaleRightScoreReverse));
        addSequential(new PauseDrivetrain(0.1));
        addSequential(new SetLiftAndManipulator(Constants.LIFT_MAX_HEIGHT, true));
        addSequential(new ShootCube(Constants.SMALL_OUTPUT_SPEED));
    }
}
