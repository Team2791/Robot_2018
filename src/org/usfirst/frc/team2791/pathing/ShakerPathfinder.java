/*
Name:
Author:
Date:

Info:



*/


package org.usfirst.frc.team2791.pathing;

import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Waypoint;
import org.usfirst.frc.team2791.robot.Constants;
import org.usfirst.frc.team2791.robot.Robot;
import org.usfirst.frc.team2791.robot.util.Util;

public class ShakerPathfinder {
    // Generate Trajectory that Drives straight
    public static Trajectory straightTrajectory(int length){
        double angle = Robot.drivetrain.getGyroAngleInRadians();
        Trajectory trajectory = Pathfinder.generate(new Waypoint[]{new Waypoint(0, 0, 0), new Waypoint((int)(length * Math.cos(angle)), (int)(length * Math.sin(angle)), angle)}, config);
        return trajectory;
    }
    public static Trajectory.Config config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH, Constants.DELTA_TIME, 1.7, 2.0, 60.0);
    public static Trajectory driveForward = Pathfinder.generate(new Waypoint[]{new Waypoint(0, 0, 0), new Waypoint(5, 8, 0)}, config);

}