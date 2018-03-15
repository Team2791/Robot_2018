package org.usfirst.frc.team2791.robot.commands.auto;

import org.usfirst.frc.team2791.robot.Constants;
import org.usfirst.frc.team2791.robot.commands.auto.bangbang.DriveEncoderBangBang;
import org.usfirst.frc.team2791.robot.commands.auto.pid.DriveStraightEncoderGyro;
import org.usfirst.frc.team2791.robot.commands.auto.pid.StationaryGyroTurn;
import org.usfirst.frc.team2791.robot.commands.drivetrain.PauseDrivetrain;
import org.usfirst.frc.team2791.robot.commands.lift.SetLiftHeight;
import org.usfirst.frc.team2791.robot.commands.manipulator.IntakeCube;
import org.usfirst.frc.team2791.robot.commands.manipulator.SetManipulatorRetracted;
import org.usfirst.frc.team2791.robot.commands.manipulator.ShootCube;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PIDTurnSwitchLEFT_2Cube extends CommandGroup {

    public PIDTurnSwitchLEFT_2Cube() {
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
//    	addSequential(new DriveStraightEncoderGyro(12, 0.7));
    	addSequential(new DriveEncoderBangBang(0.4, 0, 12)); // power, turn, distance
    	// turn towards the left side
    	addSequential(new StationaryGyroTurn(-60, 0.5));
    	// drive towards the left side
    	addParallel(new SetLiftHeight(Constants.AUTON_EXTENDED_SWITCH_HEIGHT));
    	
    	addSequential(new DriveStraightEncoderGyro(66, 0.9)); // removing 4 in after Utica Q1 (70->66)
    	// turn to face the switch
    	addSequential(new StationaryGyroTurn(60, 0.5));
    	addParallel(new SetManipulatorRetracted(false));
    	// drive into the switch. Low power so we'll hit the wall and use the timeout to stop
    	addSequential(new DriveStraightEncoderGyro(48+3, 0.5, 3.5, 1));  // 3 overshoot to ensure we hit the wall
    	// score
    	addSequential(new ShootCube(Constants.SMALL_OUTPUT_SPEED, 0.75));
    	// back up from the switch and point at the next cube
    	addSequential(new DriveStraightEncoderGyro(-75, 0.7, 99, 1));
    	addParallel(new SetLiftHeight(0));
    	
    	addSequential(new StationaryGyroTurn(50, 0.5)); // adding angle after 2nd qual match utica 35->50
    	// grab the next cube then back away
    	addParallel(new IntakeCube());
    	addSequential(new DriveStraightEncoderGyro(33, 0.6, 99, .75));
    	addSequential(new DriveEncoderBangBang(0.35, 0, 6+6)); // power, turn, distance. adding 6in after UTC Q1
    	addSequential(new PauseDrivetrain(0.5));
    	
    	// removing backup to be closer to cube
//    	addSequential(new DriveStraightEncoderGyro(-39, 0.8, 99, .75));
//    	addParallel(new SetLiftHeight(Constants.AUTON_EXTENDED_SWITCH_HEIGHT));
//    	// drive to the switch and score
//    	addSequential(new StationaryGyroTurn(-45, 0.5));
////    	addSequential(new DriveStraightEncoderGyro(75, 0.5, 3, 1));
//    	addSequential(new ShootCube(Constants.SMALL_OUTPUT_SPEED, 0.75));
//    	// back up and lower lift
//    	addSequential(new DriveEncoderBangBang(-0.4, 0, -20)); // power, turn, distance
//    	addSequential(new SetLiftHeight(0));
    }
}
