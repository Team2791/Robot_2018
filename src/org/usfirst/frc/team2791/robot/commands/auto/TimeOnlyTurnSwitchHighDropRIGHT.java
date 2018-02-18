package org.usfirst.frc.team2791.robot.commands.auto;


import org.usfirst.frc.team2791.robot.commands.lift.GoToHeight;
import org.usfirst.frc.team2791.robot.commands.manipulator.SetManipulatorRetracted;
import org.usfirst.frc.team2791.robot.commands.manipulator.ShootCube;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class TimeOnlyTurnSwitchHighDropRIGHT extends CommandGroup {
    public TimeOnlyTurnSwitchHighDropRIGHT() {
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
		// score on the right side
		addSequential(new DriveForwardTime(0.33, 2.5));
		addSequential(new TurnTime(0.3, .6));
		addSequential(new DriveForwardTime(0.2, 1.2));
    	addSequential(new ShootCube(1.0));
    }
}
