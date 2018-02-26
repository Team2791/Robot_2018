package org.usfirst.frc.team2791.robot.commands.auto;

import org.usfirst.frc.team2791.robot.Constants;
import org.usfirst.frc.team2791.robot.commands.auto.pid.DriveEncoderBangBangGyroPID;
import org.usfirst.frc.team2791.robot.commands.auto.pid.DriveStraightEncoderGyro;
import org.usfirst.frc.team2791.robot.commands.auto.pid.StationaryGyroTurn;
import org.usfirst.frc.team2791.robot.commands.lift.SetLiftHeight;
import org.usfirst.frc.team2791.robot.commands.manipulator.SetManipulatorRetracted;
import org.usfirst.frc.team2791.robot.commands.manipulator.ShootCube;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PIDSideScaleFar extends CommandGroup {

    public PIDSideScaleFar(boolean onLeftSide) {
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
    	addSequential(new DriveStraightEncoderGyro(218, 1.0));
    	// turn towards the switch
    	if(onLeftSide) {
    		addSequential(new StationaryGyroTurn(90, 0.5));
    	} else {
    		addSequential(new StationaryGyroTurn(-90, 0.5));
    	}
    	addParallel(new SetLiftHeight(11));
    	addSequential(new DriveStraightEncoderGyro(208, 1.0));
    	
    	addParallel(new SetLiftHeight(Constants.AUTON_SCALE_HEIGHT));
    	if(onLeftSide) {
    		addSequential(new StationaryGyroTurn(-100, 0.5));
    	} else {
    		addSequential(new StationaryGyroTurn(100, 0.5));
    	}
    	addSequential(new DriveStraightEncoderGyro(60, 0.5, 3));
    	addSequential(new ShootCube(Constants.LARGE_OUTPUT_SPEED));
    }
}
