package org.usfirst.frc.team2791.robot.util;

import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Waypoint;

/**
 * 
 * ******If a path is not a function (non-reticulatable), the robot code MAY STILL BUILD
 * ******Make sure the path works in the GUI if you want to use it
 */
public class Paths {
	/**
	 * Waypoint is (dx in meters forwards, dy in meters left?, angle in radians clockwise?)
	 */

	public static Waypoint[] driveForward = new Waypoint[]{
            new Waypoint(0.0, 0.0, Pathfinder.d2r(0.0)),
            new Waypoint(1.524, 0.0, Pathfinder.d2r(0.0)),
            new Waypoint(2.4383 ,-2.4383, Pathfinder.d2r(90.0)),
//            new Waypoint(1.524, 0.0, 0.0)
    };
	
	public static Waypoint[] testDrive = new Waypoint[] {
			new Waypoint(1.0, 0.0, Pathfinder.d2r(0.0))
	};
	
	public static Waypoint[] farScale = convertPath_f2m(new Waypoint[] {
			new Waypoint(0,3.0,0),
			new Waypoint(8.0, 3.0,0),
			new Waypoint(18.0, 8.0, 90.),
			new Waypoint(18.0, 14.0, 90.),
			new Waypoint(23.0, 20.0, 0),
	});
	
	//public static Waypoint[] switchLeft = convertPath_f2m(new Waypoint[] {
		//new Waypoint()
	//});
	
	/*public static Waypoint[] switchRight = convertPath_f2m(new Waypoint[] {
			new Waypoint(8.33, -5, 0.0)
	});
	*/
	/**
	 * Didn't test this though
	 * Converts the given path from SI to metrics so the Pathfinder library can read it.
	 * @param standardPath an array of Waypoint where each parameter of the Waypoints are defined as: (x_inFeet, y_inFeet, angle_inDegrees)
	 * @return the given path where each Waypoint is defined as (x_inMeters, y_inMeters, angle_inRadians)
	 */
	private static Waypoint[] convertPath_f2m(Waypoint[] standardPath) {
		for(int i = 0; i < standardPath.length; i++) {
			double x_m = standardPath[i].x * 0.3048;
			double y_m = standardPath[i].y * 0.3048;
			double theta_r = Pathfinder.d2r(standardPath[i].angle);
			standardPath[i] = new Waypoint(x_m, y_m, theta_r);
		}
		
		return standardPath;
	}
}
