package org.usfirst.frc.team2791.robot.commands.auto.spline;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team2791.robot.commands.drivetrain.PauseDrivetrain;
import org.usfirst.frc.team2791.robot.commands.drivetrain.traj.DrivePath;
import org.usfirst.frc.team2791.robot.util.Paths;

public class splinetest extends CommandGroup{
    public splinetest(){
        addSequential(new PauseDrivetrain(.2));
        addSequential(new DrivePath(Paths.test));
    }
}
