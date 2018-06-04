package org.usfirst.frc.team2791.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team2791.robot.Constants;
import org.usfirst.frc.team2791.robot.Robot;
import org.usfirst.frc.team2791.robot.commands.lift.LowerLiftAfterDelay;
import org.usfirst.frc.team2791.robot.commands.lift.RaiseLiftToTopAfterDelay;
import org.usfirst.frc.team2791.robot.commands.lift.SetLiftHeightMagicMotion;
import org.usfirst.frc.team2791.robot.subsystems.ShakerDrivetrain;
import org.usfirst.frc.team2791.robot.subsystems.ShakerLift;


public class Climb extends CommandGroup {
    private ShakerDrivetrain drivetrain;
    private double leftBackupSpeed = 5;
    private double rightBackupSpeed = 5;
    private double debugPause = 5;

    private double leftClimbSpeed = 10;
    private double rightClimbSpeed = 10;

    public Climb() {
        this.drivetrain = Robot.drivetrain;

        // Set Drive mode
        drivetrain.setDriveOrRampMode(true);
        // Raise lift to get hook above bar
        new SetLiftHeightMagicMotion((5 * 12) + 8);
        // Lower lift to bottom
        new LowerLiftAfterDelay(0.0 + debugPause);
        // Pause (for Debug)
        new GenericPause(1.0 + debugPause);

        // Backup
        drivetrain.setLeftRightMotorOutputs(leftBackupSpeed, rightBackupSpeed);
        // Pause
        new GenericPause(1.0 + debugPause); // Need to test if Robot moves backward even during pause
        // Stop backing up
        drivetrain.setLeftRightMotorOutputs(0, 0);

        // Switch gears
        drivetrain.setDriveOrRampMode(false);
        // Climb
        drivetrain.setLeftRightMotorOutputs(Constants.leftClimbSpeed, Constants.rightClimbSpeed);
        // Pause whiling climbing
        new GenericPause(1.0 + debugPause); // Need to test if Robot moves backward even during pause
        // Hold the climb
        new HoldClimb();

    }
}
