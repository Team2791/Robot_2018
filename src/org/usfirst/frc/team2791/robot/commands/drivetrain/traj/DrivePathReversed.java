package org.usfirst.frc.team2791.robot.commands.drivetrain.traj;

import edu.wpi.first.wpilibj.command.Command;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.followers.EncoderFollower;
import org.usfirst.frc.team2791.robot.Robot;


/**
 *
 */
public class DrivePathReversed extends Command {

    Waypoint[] path;
    EncoderFollower[] followers;

    public DrivePathReversed(Waypoint[] path) {
        requires(Robot.drivetrain);
        this.path = path;
        setInterruptible(false);
        followers = Robot.drivetrain.pathSetup(path);
    }

    protected void initialize() {
        Robot.drivetrain.resetForPath();
        Robot.drivetrain.pathFollow(followers, true);
    }

    protected void execute() {
        Robot.drivetrain.pathFollow(followers, true);
    }

    protected boolean isFinished() {
        return Robot.drivetrain.getIsProfileFinished();
    }

    protected void end() {

        Robot.drivetrain.setLeftRightMotorOutputs(0., 0.);
    }

    protected void interrupted() {
        end();
    }

}
