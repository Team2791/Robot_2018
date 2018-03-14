package org.usfirst.frc.team2791.robot.commands.auto.spline;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team2791.robot.Constants;
import org.usfirst.frc.team2791.robot.commands.SetLiftAndManipulator;
import org.usfirst.frc.team2791.robot.commands.auto.timeonly.TurnTime;
import org.usfirst.frc.team2791.robot.commands.drivetrain.PauseDrivetrain;
import org.usfirst.frc.team2791.robot.commands.drivetrain.traj.DrivePath;
import org.usfirst.frc.team2791.robot.commands.lift.SetLiftHeight;
import org.usfirst.frc.team2791.robot.commands.manipulator.IntakeAndHoldCube;
import org.usfirst.frc.team2791.robot.commands.manipulator.ShootCube;
import org.usfirst.frc.team2791.robot.util.Paths;
//TODO THESE VALUES ARE NOT CORRECT AND NEED TO BE CHANGED AND TESTED BEFORE USED
public class RightSidenearScaleSCORE extends CommandGroup {
    public RightSidenearScaleSCORE(){
        addSequential(new PauseDrivetrain(.25));
        addParallel(new SetLiftAndManipulator(Constants.LIFT_MAX_HEIGHT, true));
        addSequential(new DrivePath(Paths.nearScaleRight));
        addSequential(new ShootCube(Constants.SMALL_OUTPUT_SPEED));


    }
}
