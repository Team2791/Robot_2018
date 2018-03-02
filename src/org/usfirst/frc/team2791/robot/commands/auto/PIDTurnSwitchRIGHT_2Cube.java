package org.usfirst.frc.team2791.robot.commands.auto;

import org.usfirst.frc.team2791.robot.Constants;
import org.usfirst.frc.team2791.robot.commands.auto.bangbang.DriveEncoderBangBang;
import org.usfirst.frc.team2791.robot.commands.auto.pid.DriveEncoderBangBangGyroPID;
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
public class PIDTurnSwitchRIGHT_2Cube extends CommandGroup {

    public PIDTurnSwitchRIGHT_2Cube() {
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
    	addSequential(new DriveStraightEncoderGyro(12, 0.7));
    	// turn towards the left side
    	addSequential(new StationaryGyroTurn(60, 0.5));
    	// drive towards the left side
    	addParallel(new SetLiftHeight(Constants.AUTON_RETRACTED_SWITCH_HEIGHT)); // was 13
    
    	addSequential(new DriveStraightEncoderGyro(56, 0.7));
    	// turn to face the switch
    	addSequential(new StationaryGyroTurn(-60, 0.5)); // there is only a weak drive after this turn so it overshoots more
    	// drive into the switch. Low power so we'll hit the wall and use the timeout to stop
    	addSequential(new DriveStraightEncoderGyro(61+4, 0.5, 3.5, 1));
    	// score
    	addSequential(new ShootCube(Constants.SMALL_OUTPUT_SPEED));
    	// back up from the switch and point at the next cube
    	addSequential(new DriveStraightEncoderGyro(-75, 0.7, 99, 1));
    	addParallel(new SetLiftHeight(0));
    	addParallel(new SetManipulatorRetracted(true));
    	addSequential(new StationaryGyroTurn(-35, 0.5));
    	// grab the next cube then back away
    	addParallel(new IntakeCube());
    	addSequential(new DriveStraightEncoderGyro(33, 0.6, 99, .75));
    	addSequential(new DriveEncoderBangBang(0.35, 0, 6)); // power, turn, distance
    	addSequential(new PauseDrivetrain(0.5));
    	addSequential(new DriveStraightEncoderGyro(-39, 0.8, 99, .75));
    	addParallel(new SetLiftHeight(Constants.AUTON_EXTENDED_SWITCH_HEIGHT));
    	// drive to the switch and score
    	addSequential(new StationaryGyroTurn(45, 0.5));
//    	addSequential(new DriveStraightEncoderGyro(75, 0.5, 3, 1));
//    	addSequential(new ShootCube(Constants.SMALL_OUTPUT_SPEED, 0.75));
//    	// back up and lower lift
//    	addSequential(new DriveEncoderBangBang(-0.4, 0, -20)); // power, turn, distance
//    	addSequential(new SetLiftHeight(0));
    }
}
