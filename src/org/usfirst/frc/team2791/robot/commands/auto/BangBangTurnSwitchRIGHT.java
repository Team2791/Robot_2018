package org.usfirst.frc.team2791.robot.commands.auto;

import org.usfirst.frc.team2791.robot.Constants;
import org.usfirst.frc.team2791.robot.commands.auto.bangbang.DriveEncoderBangBang;
import org.usfirst.frc.team2791.robot.commands.auto.bangbang.TurnGyroBangBang;
import org.usfirst.frc.team2791.robot.commands.lift.GoToHeight;
import org.usfirst.frc.team2791.robot.commands.manipulator.SetManipulatorRetracted;
import org.usfirst.frc.team2791.robot.commands.manipulator.ShootCube;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class BangBangTurnSwitchRIGHT extends CommandGroup {

    public BangBangTurnSwitchRIGHT() {
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

    	addSequential(new DriveEncoderBangBang(0.3, 0,  8.8-2, 100));
    	addParallel(new SetManipulatorRetracted(true));
    	// turn towards the left side
    	addSequential(new TurnGyroBangBang((45-5), 0.3, 100));
    	// drive towards the left side
    	addParallel(new GoToHeight(8));
    	addSequential(new DriveEncoderBangBang(0.3, 0,  26.6-2, 100));
    	// turn to face the switch
    	addSequential(new TurnGyroBangBang(-(45-5), -0.3, 100));
    	// drive into the switch. Low power so we'll hit the wall and use the timeout to stop
    	addSequential(new DriveEncoderBangBang(0.2, 0,  8, 3));
    	// score
    	addSequential(new ShootCube(Constants.LARGE_OUTPUT_SPEED));
    }
}
