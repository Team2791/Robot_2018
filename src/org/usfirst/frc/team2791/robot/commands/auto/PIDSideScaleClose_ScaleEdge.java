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
public class PIDSideScaleClose_ScaleEdge extends CommandGroup {

    public PIDSideScaleClose_ScaleEdge(boolean leftSide) {
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
    	addParallel(new SetLiftHeight(13));
    	addSequential(new DriveStraightEncoderGyro(305, 0.7));
    	addSequential(new SetLiftHeight(Constants.AUTON_SCALE_HEIGHT));
    	if(leftSide) {
    		addSequential(new StationaryGyroTurn(90, 0.5));
    	} else {
    		addSequential(new StationaryGyroTurn(-90, 0.5));
    	}
    	addSequential(new ShootCube(Constants.SMALL_OUTPUT_SPEED+.1));
    	// backup and lower the lift
    	addSequential(new DriveEncoderBangBang(-0.3, 0, -20));
    	addParallel(new SetLiftHeight(0));
    	// poke a toe out of our zone
    	if(leftSide) {
    		addSequential(new StationaryGyroTurn(-70, 0.5));
    	} else {
    		addSequential(new StationaryGyroTurn(70, 0.5));
    	}
    }
}
