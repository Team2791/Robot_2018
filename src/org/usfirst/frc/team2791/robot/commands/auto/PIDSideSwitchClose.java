package org.usfirst.frc.team2791.robot.commands.auto;

import org.usfirst.frc.team2791.robot.Constants;
import org.usfirst.frc.team2791.robot.commands.auto.bangbang.DriveEncoderBangBang;
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
public class PIDSideSwitchClose extends CommandGroup {

    public PIDSideSwitchClose(boolean onLeftSide) {
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
    	addParallel(new SetLiftHeight(Constants.AUTON_RETRACTED_SWITCH_HEIGHT)); // was 13
    	addSequential(new DriveStraightEncoderGyro(148, 0.7));
    	// turn towards the switch
    	if(onLeftSide) {
    		addSequential(new StationaryGyroTurn(90, 0.5));
    	} else {
    		addSequential(new StationaryGyroTurn(-90, 0.5));
    	}
    	// drive into the switch. Low power so we'll hit the wall and use the timeout to stop
    	addSequential(new DriveStraightEncoderGyro(19, 0.5, 1.25)); // 4 short so we do the last part of the drive with bang bang // timeout default = 2.5
    	// score
    	addSequential(new ShootCube(Constants.SMALL_OUTPUT_SPEED));
    	addSequential(new DriveEncoderBangBang(-0.3, 0, -20));
    	addSequential(new SetLiftHeight(0));
    }
}
