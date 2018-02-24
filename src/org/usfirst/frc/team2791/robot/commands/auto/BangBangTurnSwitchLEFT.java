package org.usfirst.frc.team2791.robot.commands.auto;

import org.usfirst.frc.team2791.robot.Constants;
import org.usfirst.frc.team2791.robot.commands.auto.bangbang.DriveEncoderBangBang;
import org.usfirst.frc.team2791.robot.commands.auto.bangbang.TurnGyroBangBang;
import org.usfirst.frc.team2791.robot.commands.auto.pid.DriveEncoderBangBangGyroPID;
import org.usfirst.frc.team2791.robot.commands.lift.SetLiftHeight;
import org.usfirst.frc.team2791.robot.commands.manipulator.SetManipulatorRetracted;
import org.usfirst.frc.team2791.robot.commands.manipulator.ShootCube;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class BangBangTurnSwitchLEFT extends CommandGroup {

    public BangBangTurnSwitchLEFT() {
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
    	
    	// drive off the wall
    	// 3rd arg is drive distance 
    	
    	// NOTE. ALl bang bang distances have a little removed to compensate for overshoot.

    	addSequential(new DriveEncoderBangBangGyroPID(0.4, 3 * 1.57, 100));
    	addParallel(new SetManipulatorRetracted(true));
    	// turn towards the left side
    	addSequential(new TurnGyroBangBang(-60, -0.3, 100));
    	// drive towards the left side
    	addParallel(new SetLiftHeight(8)); // was 13
    	addSequential(new DriveEncoderBangBangGyroPID(0.4, 23 *1.57, 100));
    	// turn to face the switch
    	addSequential(new TurnGyroBangBang(60, 0.3, 100)); // there is only a weak drive after this turn so it overshoots more
    	// drive into the switch. Low power so we'll hit the wall and use the timeout to stop
    	addSequential(new DriveEncoderBangBangGyroPID(0.4, 10*1.57, 3.5));
    	addSequential(new DriveEncoderBangBangGyroPID(0.2, 14*1.57, 3.5));
    	// score
    	addSequential(new ShootCube(Constants.LARGE_OUTPUT_SPEED));
    }
}
