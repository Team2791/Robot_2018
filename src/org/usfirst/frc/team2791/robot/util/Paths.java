package org.usfirst.frc.team2791.robot.util;

import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Waypoint;

/**
 * 
 * ******If a path is not a function (non-reticulatable), the robot code MAY STILL BUILD
 * ******Make sure the path works in the GUI if you want to use it
 */
public class Paths {

	public static Waypoint[] driveForward = new Waypoint[]{
            new Waypoint(0.0, 0.0, Pathfinder.d2r(0.0)),
            new Waypoint(2.4383 ,-2.4383, Pathfinder.d2r(90.0))
    };
	
	
	
	
	/**
	 * Didn't test this though
	 * Converts the given path from SI to metrics so the Pathfinder library can read it.
	 * @param standardPath an array of Waypoint where each parameter of the Waypoints are defined as: (x_inFeet, y_inFeet, angle_inDegrees)
	 * @return the given path where each Waypoint is defined as (x_inMeters, y_inMeters, angle_inRadians)
	 */
	private Waypoint[] convertPath_f2m(Waypoint[] standardPath) {
		for(int i = 0; i < standardPath.length; i++) {
			double x_m = standardPath[i].x * 0.3048;
			double y_m = standardPath[i].y * 0.3048;
			double theta_r = Pathfinder.d2r(standardPath[i].angle);
			standardPath[i] = new Waypoint(x_m, y_m, theta_r);
		}
		
		return standardPath;
	}
}
