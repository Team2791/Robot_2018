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
public class TimeOnlyStraightSwitchCube extends DelayedCommandGroup {
	boolean robotOnLeftSide;

    public TimeOnlyStraightSwitchCube(boolean robotOnLeftSide) {
    	this.robotOnLeftSide = robotOnLeftSide;
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
    	addParallel(new GoToHeight(8)); // was 13
    	addSequential(new DriveForwardTime(0.33, 3.5));
    	
    	if(Robot.leftSwitchNear == robotOnLeftSide) {
    		System.out.println("Robot on our side of the switch.");
    		addSequential(new ShootCube(Constants.LARGE_OUTPUT_SPEED));
    	}
    }
}
