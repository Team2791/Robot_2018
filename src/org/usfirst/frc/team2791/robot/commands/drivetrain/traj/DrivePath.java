package org.usfirst.frc.team2791.robot.commands.drivetrain.traj;

//
//import edu.wpi.first.wpilibj.command.Command;
//import jaci.pathfinder.Waypoint;
//import jaci.pathfinder.followers.EncoderFollower;
//import org.usfirst.frc.team2791.robot.Robot;
//
//
///**
// *
// */
//public class DrivePath extends Command {
//
//    Waypoint[] path;
//    EncoderFollower[] followers;
//
//    public DrivePath(Waypoint[] path) {
//        requires(Robot.drivetrain);
//        this.path = path;
//        setInterruptible(false); //not sure if this should be there
//        followers = Robot.drivetrain.pathSetup(path);
//    }
//
//    protected void initialize() {
//        Robot.drivetrain.resetForPath();
//        Robot.drivetrain.pathFollow(followers, false);
//    }
//
//    protected void execute() {
//        Robot.drivetrain.pathFollow(followers, false);
//    }
//
//    protected boolean isFinished() {
//        return Robot.drivetrain.getIsProfileFinished();
//    }
//
//    protected void end() {
//
//        Robot.drivetrain.setLeftRightMotorOutputs(0., 0.);
//
//    }
//
//    protected void interrupted() {
//        end();
//    }
//
//}
