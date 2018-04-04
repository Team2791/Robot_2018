package org.usfirst.frc.team2791.robot.commands.auto;


import org.usfirst.frc.team2791.robot.Constants;
import org.usfirst.frc.team2791.robot.commands.auto.bangbang.DriveEncoderBangBang;
import org.usfirst.frc.team2791.robot.commands.auto.bangbang.TurnGyroBangBang;
import org.usfirst.frc.team2791.robot.commands.auto.pid.DriveStraightEncoderGyro;
import org.usfirst.frc.team2791.robot.commands.auto.pid.StationaryGyroTurn;
import org.usfirst.frc.team2791.robot.commands.lift.SetLiftHeightBangBang;
import org.usfirst.frc.team2791.robot.commands.manipulator.SetManipulatorRetracted;
import org.usfirst.frc.team2791.robot.commands.manipulator.ShootCube;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PIDSideScaleClose extends CommandGroup {

    public PIDSideScaleClose(boolean leftSide) {
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
    	addSequential(new DriveStraightEncoderGyro(210, 1, 10, 1.5));
    	addParallel(new SetLiftHeightBangBang(13));
    	if(leftSide) {
    		addSequential(new StationaryGyroTurn(22, 0.5, 1.5));
    	} else {
    		addSequential(new StationaryGyroTurn(-22, 0.5, 1.5));
    	}
    	addSequential(new SetLiftHeightBangBang(Constants.AUTON_SCALE_HEIGHT));
    	// drive to scale.
    	addSequential(new DriveStraightEncoderGyro(63, 0.6, 10, 4.5)); // 1.5 to 4.5. Also adding a extra power
    	addSequential(new ShootCube(Constants.SMALL_OUTPUT_SPEED, 0.5));
    	addSequential(new DriveEncoderBangBang(-0.5, 0, -20));
    	addSequential(new SetLiftHeightBangBang(0));
    	// turn and backup from scale to let 340 in.
    	if(leftSide) {
    		addSequential(new TurnGyroBangBang(-8, -0.6, 2));
    	} else {
    		addSequential(new TurnGyroBangBang(8, 0.6, 2));
    	}
    	addSequential(new DriveEncoderBangBang(-0.7, 0, -30, 3.5));
    }
}
