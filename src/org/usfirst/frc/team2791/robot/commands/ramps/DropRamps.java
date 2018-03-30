package org.usfirst.frc.team2791.robot.commands.ramps;

import org.usfirst.frc.team2791.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team2791.robot.commands.drivetrain.SetDrivetrainShifterMode;
import org.usfirst.frc.team2791.robot.commands.lift.SetLiftHeightBangBang;


public class DropRamps extends CommandGroup {

    public DropRamps() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the arm.
    	
    	// we were going to shift but now wait until we want to raise the ramps so we can adjust
//        addParallel(new SetDrivetrainShifterMode(false));
        addParallel(new SetRampDeploy(true));
        // get the gripper out of the way
        addParallel(new SetLiftHeightBangBang(18.0));
    }
}
