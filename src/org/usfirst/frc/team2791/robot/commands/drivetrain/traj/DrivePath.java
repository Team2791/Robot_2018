package org.usfirst.frc.team2791.robot.commands.drivetrain.traj;


import edu.wpi.first.wpilibj.command.Command;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.followers.EncoderFollower;
import org.usfirst.frc.team2791.robot.Robot;
import org.usfirst.frc.team2791.robot.subsystems.ShakerDrivetrain.DrivetrainProfiling;


/**
 *
 */
public class DrivePath extends Command {

    Waypoint[] path;
    EncoderFollower[] followers;

    /**
     * 
     * @param path the array of Waypoints that you want to drive
     */
    public DrivePath(Waypoint[] path) {
        requires(Robot.drivetrain);
        this.path = path;
        setInterruptible(false); //not sure if this should be there
        followers = Robot.drivetrain.pathSetup(path);
        System.out.println("Finished constructor for DrivePath");
    }

    protected void initialize() {
    	System.out.println("Started Path Init!");
        Robot.drivetrain.setLeftRightMotorOutputs(0., 0.);
        Robot.drivetrain.resetForPath();
        followers[0].reset();
        followers[1].reset();
    }

    protected void execute() {
//    	System.out.println("Driving Path");
        Robot.drivetrain.pathFollow(followers, false);
    }

    protected boolean isFinished() {
        return Robot.drivetrain.getIsProfileFinished();
    }

    protected void end() {
    	System.out.println("Finished Driving Path");
        Robot.drivetrain.setLeftRightMotorOutputs(0., 0.);
    }

    protected void interrupted() {
        end();
    }

}
