package org.usfirst.frc.team2791.robot.commands.auto;


import org.usfirst.frc.team2791.robot.Constants;
import org.usfirst.frc.team2791.robot.Robot;
import org.usfirst.frc.team2791.robot.commands.auto.timeonly.DriveForwardTime;
import org.usfirst.frc.team2791.robot.commands.auto.timeonly.TurnTime;
import org.usfirst.frc.team2791.robot.commands.lift.GoToHeight;
import org.usfirst.frc.team2791.robot.commands.manipulator.SetManipulatorRetracted;
import org.usfirst.frc.team2791.robot.commands.manipulator.ShootCube;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class TimeOnlyTurnSwitchHighDropLEFT extends CommandGroup {
    public TimeOnlyTurnSwitchHighDropLEFT() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	
    	addParallel(new SetManipulatorRetracted(true));
    	addParallel(new GoToHeight(16));
		// score on the left side
		addSequential(new DriveForwardTime(0.33, 1.5));
		addSequential(new TurnTime(-0.3, .8));
		addSequential(new DriveForwardTime(0.2, 3.5));

    	addSequential(new ShootCube(Constants.LARGE_OUTPUT_SPEED));
    }
}
