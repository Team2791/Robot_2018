package org.usfirst.frc.team2791.robot.commands.auto;


import org.usfirst.frc.team2791.robot.Constants;
import org.usfirst.frc.team2791.robot.Robot;
import org.usfirst.frc.team2791.robot.commands.lift.GoToHeight;
import org.usfirst.frc.team2791.robot.commands.manipulator.SetManipulatorRetracted;
import org.usfirst.frc.team2791.robot.commands.manipulator.ShootCube;
import org.usfirst.frc.team2791.robot.util.DelayedCommandGroup;

/**
 *
 */
public class TimeOnlyTurnSwitchHighDrop extends DelayedCommandGroup {
    public TimeOnlyTurnSwitchHighDrop() {
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
    }
    
    // we need to call this NOT during the constructor because the switch side could change.
    public void initCode() {
    	addParallel(new SetManipulatorRetracted(true));
    	addParallel(new GoToHeight(16));
    	
    	if(Robot.leftSwitchNear) {
    		// score on the left side
    		addSequential(new DriveForwardTime(0.33, 1.5));
    		addSequential(new TurnTime(-0.3, .8));
    		addSequential(new DriveForwardTime(0.2, 3.5));
    	} else {
    		// score on the right side
    		addSequential(new DriveForwardTime(0.33, 2.5));
    		addSequential(new TurnTime(0.3, .6));
    		addSequential(new DriveForwardTime(0.2, 1.2));
    	}

    	addSequential(new ShootCube(1.0));
    }
}
