package org.usfirst.frc.team2791.robot.commands.auto.spline;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team2791.robot.Constants;
import org.usfirst.frc.team2791.robot.commands.SetLiftAndManipulator;
import org.usfirst.frc.team2791.robot.commands.auto.timeonly.DriveForwardTime;
import org.usfirst.frc.team2791.robot.commands.drivetrain.PauseDrivetrain;
import org.usfirst.frc.team2791.robot.commands.drivetrain.traj.DrivePath;
import org.usfirst.frc.team2791.robot.commands.manipulator.ShootCube;
import org.usfirst.frc.team2791.robot.util.Paths;

public class LeftSidenearSwitchSCORE  extends CommandGroup{
    public LeftSidenearSwitchSCORE(){
        addSequential(new PauseDrivetrain(.25));
        addParallel(new SetLiftAndManipulator(Constants.AUTON_EXTENDED_SWITCH_HEIGHT, true));
        addSequential(new DrivePath(Paths.nearLeftSwitch));
        addSequential(new ShootCube(Constants.SMALL_OUTPUT_SPEED));
        addSequential(new DriveForwardTime(4, .6));

    }
}
