package org.usfirst.frc.team2791.robot.commands.auto;

import org.usfirst.frc.team2791.robot.Constants;
import org.usfirst.frc.team2791.robot.commands.auto.bangbang.TurnGyroBangBang;
import org.usfirst.frc.team2791.robot.commands.auto.pid.DriveEncoderBangBangGyroPID;
import org.usfirst.frc.team2791.robot.commands.drivetrain.PauseDrivetrain;
import org.usfirst.frc.team2791.robot.commands.drivetrain.SetDrivetrainBreakMode;
import org.usfirst.frc.team2791.robot.commands.lift.SetLiftHeightBangBang;
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
    	addSequential(new SetDrivetrainBreakMode(true));
    	addSequential(new DriveEncoderBangBangGyroPID(0.3, 3*1.571, 100));
    	addParallel(new SetManipulatorRetracted(true));
    	// turn towards the left side
    	addSequential(new TurnGyroBangBang(60-10, 0.3, 100)); // want to turn 60 degrees 
    	addParallel(new SetLiftHeightBangBang(8));
    	// stop any momentum we have so the next drive start cleanly
    	addSequential(new PauseDrivetrain(0.5));
    	// drive towards the left side
    	addSequential(new DriveEncoderBangBangGyroPID(0.3, 16.9*1.571, 100)); // was 16.9, made 32 but I wanted to add 18 in. // made 22.5 and overshot 1/2 a robot
    	// stop any momentum we have so the next drive start cleanly
    	addSequential(new PauseDrivetrain(0.75));
    	// turn to face the switch
    	addSequential(new TurnGyroBangBang(-(60-5), -0.35, 100)); // robot has more trouble turning counter clockwise
    	// drive into the switch. Low power so we'll hit the wall and use the timeout to stop
    	addSequential(new DriveEncoderBangBangGyroPID(0.4, 14*1.571, 3.5));
    	addSequential(new DriveEncoderBangBangGyroPID(0.2, 14*1.571, 3.5));
    	// score
    	addSequential(new ShootCube(Constants.LARGE_OUTPUT_SPEED));
    }
}
