package org.usfirst.frc.team2791.robot.commands.auto;

import org.usfirst.frc.team2791.robot.Constants;
import org.usfirst.frc.team2791.robot.commands.auto.bangbang.DriveEncoderBangBang;
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
    	addSequential(new DriveStraightEncoderGyro(221+8, 1.0)); // adjusting from 221 to 214 for Utica
    	// turn towards the switch
    	if(onLeftSide) {
    		addSequential(new StationaryGyroTurn(90, 0.5, 0.75));
    	} else {
    		addSequential(new StationaryGyroTurn(-90, 0.5, 0.75));
    	}
    	addParallel(new SetLiftHeight(11));
    	addSequential(new DriveStraightEncoderGyro(203, 1.0));

    	addParallel(new SetLiftHeight(Constants.AUTON_SCALE_HEIGHT));
    	if(onLeftSide) {
    		addSequential(new StationaryGyroTurn(-100, 0.5));
    	} else {
    		addSequential(new StationaryGyroTurn(100, 0.5));
    	}
    	addSequential(new DriveStraightEncoderGyro(40-8, 0.5, 3)); // adjusting from 57 to 40
    	addSequential(new ShootCube(Constants.SMALL_OUTPUT_SPEED));
    	addSequential(new DriveEncoderBangBang(-0.3, 0, -20));
    	addSequential(new SetLiftHeight(0));
    }
}
