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

public class RightSidenearScale2CubeScore extends CommandGroup {
    public RightSidenearScale2CubeScore(){
        addSequential(new PauseDrivetrain(.25));
        addParallel(new SetLiftAndManipulator(Constants.AUTON_SCALE_HEIGHT, true));
        addSequential(new DrivePath(Paths.nearScaleRight));
        addSequential(new ShootCube(Constants.SMALL_OUTPUT_SPEED));
        addSequential(new TurnTime(-1.5, .23));
        addSequential(new PauseDrivetrain(.2));
        addSequential(new SetLiftAndManipulator(Constants.LIFT_MIN_HEIGHT, false));
        addSequential(new PauseDrivetrain(.1));
        addSequential(new DrivePath(Paths.nearSwitchRightTurnAround));
        addSequential(new IntakeAndHoldCube());
        addParallel(new SetLiftHeight(Constants.AUTON_EXTENDED_SWITCH_HEIGHT));
        addSequential(new TurnTime(2, .3));
        addSequential(new ShootCube(Constants.SMALL_OUTPUT_SPEED));
    }
}
