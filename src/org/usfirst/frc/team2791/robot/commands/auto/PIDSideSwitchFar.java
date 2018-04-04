package org.usfirst.frc.team2791.robot.commands.auto;

import org.usfirst.frc.team2791.robot.Constants;
import org.usfirst.frc.team2791.robot.commands.auto.bangbang.DriveEncoderBangBang;
import org.usfirst.frc.team2791.robot.commands.auto.pid.DriveEncoderBangBangGyroPID;
import org.usfirst.frc.team2791.robot.commands.auto.pid.DriveStraightEncoderGyro;
import org.usfirst.frc.team2791.robot.commands.auto.pid.StationaryGyroTurn;
import org.usfirst.frc.team2791.robot.commands.lift.SetLiftHeightBangBang;
import org.usfirst.frc.team2791.robot.commands.manipulator.SetManipulatorRetracted;
import org.usfirst.frc.team2791.robot.commands.manipulator.ShootCube;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PIDSideSwitchFar extends CommandGroup {

    public PIDSideSwitchFar(boolean onLeftSide) {
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
    	addSequential(new DriveStraightEncoderGyro(221, 1.0));
    	addParallel(new SetManipulatorRetracted(false));
    	// turn towards the switch
    	if(onLeftSide) {
    		addSequential(new StationaryGyroTurn(90, 0.5));
    	} else {
    		addSequential(new StationaryGyroTurn(-90, 0.5));
    	}
    	addParallel(new SetLiftHeightBangBang(Constants.AUTON_EXTENDED_SWITCH_HEIGHT));
    	addSequential(new DriveStraightEncoderGyro(179, 1.0));
    	
    	if(onLeftSide) {
    		addSequential(new StationaryGyroTurn(90, 0.5));
    	} else {
    		addSequential(new StationaryGyroTurn(-90, 0.5));
    	}
    	
    	// drive into the cubes near the switch. Low power so we'll hit the them and use the timeout to stop
    	addSequential(new DriveStraightEncoderGyro(12, 0.4, 1.25)); // timeout default = 2
    	addSequential(new ShootCube(Constants.SMALL_OUTPUT_SPEED));
    	addParallel(new SetManipulatorRetracted(true));
    	addSequential(new DriveEncoderBangBang(-0.3, 0, -20));
    	addSequential(new SetLiftHeightBangBang(0));
    }
}
