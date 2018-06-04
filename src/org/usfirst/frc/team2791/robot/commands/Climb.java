package org.usfirst.frc.team2791.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team2791.robot.Constants;
import org.usfirst.frc.team2791.robot.Robot;
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



        drivetrain.setDriveOrRampMode(true);
        new SetLiftHeightMagicMotion((5 * 12) + 8);
        new RaiseLiftToTopAfterDelay(0.0 + debugPause);
        new GenericPause(1.0 + debugPause);
        drivetrain.setLeftRightMotorOutputs(leftBackupSpeed, rightBackupSpeed);
        new GenericPause(1.0 + debugPause); // Need to test if Robot moves backward even during pause
        drivetrain.setLeftRightMotorOutputs(0, 0);

        drivetrain.setDriveOrRampMode(false);
        drivetrain.setLeftRightMotorOutputs(Constants.leftClimbSpeed, Constants.rightClimbSpeed);
        new GenericPause(1.0 + debugPause); // Need to test if Robot moves backward even during pause

        new HoldClimb();

    }
}
