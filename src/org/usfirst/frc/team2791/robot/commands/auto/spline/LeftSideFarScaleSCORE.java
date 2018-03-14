package org.usfirst.frc.team2791.robot.commands.auto.spline;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team2791.robot.Constants;
import org.usfirst.frc.team2791.robot.commands.SetLiftAndManipulator;
import org.usfirst.frc.team2791.robot.commands.drivetrain.PauseDrivetrain;
import org.usfirst.frc.team2791.robot.commands.drivetrain.traj.DrivePath;
import org.usfirst.frc.team2791.robot.commands.manipulator.ShootCube;
import org.usfirst.frc.team2791.robot.util.Paths;

public class LeftSideFarScaleSCORE extends CommandGroup {
    public LeftSideFarScaleSCORE(){
        addSequential(new PauseDrivetrain(.2));
        addParallel(new SetLiftAndManipulator(Constants.AUTON_SCALE_HEIGHT, true));
        addSequential(new DrivePath(Paths.farLefttScale));
        addSequential(new ShootCube(Constants.SMALL_OUTPUT_SPEED));

    }
}
