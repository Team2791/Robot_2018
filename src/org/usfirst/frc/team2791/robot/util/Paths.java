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

	public static Waypoint[] driveForward = convertPath_f2m(new Waypoint[]{
            new Waypoint(0.0, 0.0, Pathfinder.d2r(0.0)),
            new Waypoint(20,0,0),
   //         new Waypoint(1.524, 0.0, Pathfinder.d2r(0.0)),
  //          new Waypoint(2.4383 ,-2.4383, Pathfinder.d2r(90.0)),
//            new Waypoint(1.524, 0.0, 0.0)
	});

//	public static Waypoint[] testDrive = convertPath_f2m(new Waypoint[] {
//			new Waypoint(1.0, 0.0, Pathfinder.d2r(0.0))
//	});
//	
	public static Waypoint[] turn = convertPath_f2m(new Waypoint[] {
			new Waypoint (0,0,0),
			new Waypoint (4,4,-90),
	});
//
//	public static Waypoint[] forwardAndTurn = convertPath_f2m(new Waypoint[] {
//			new Waypoint(0,0,0),
//			new Waypoint(3,3,0),
//	});
//
	public static Waypoint[] StartLeftGoToLeftScale = convertPath_f2m(new Waypoint[] {
			new Waypoint(0,0,0),
			new Waypoint(1.0, 0.0, Pathfinder.d2r(0.0))
//			new Waypoint(1.0, 0.25, Pathfinder.d2r(0.0)),
	});

    public static Waypoint[] nearScaleRightScore = convertPath_f2m(new Waypoint[] {
    	new Waypoint(0, 0, 0),
    	new Waypoint(13.25, 0, 0),
		new Waypoint(22.25, 2, 20)
    });

    
    
    public static Waypoint[] closeScale2Cube1stCubeStartRIGHT = convertPath_f2m(new Waypoint[] {
    	new Waypoint(0, 0, 0), // start on the right side
    	new Waypoint(13, 0, 0),
    	new Waypoint(19, 2.5, 25),
		new Waypoint(23.5, -1.1, 90) // end in the center of the null zone facing the scale.
    });
    
//    
//    public static Waypoint[] nearScaleRightScoreReverse = convertPath_f2m(new Waypoint[] {
//    	new Waypoint(0, 0, 0),
//    	new Waypoint(13.25, 0, 0),
//		new Waypoint(22.25, -2, -20)
//    });
    
    /*public static Waypoint[] farScaleRightScore = convertPath_f2m(new Waypoint[] {
        	new Waypoint(0, 0, 0),
        	new Waypoint(10., 0, 0),
    		new Waypoint(18.25, 8, 90),
    		new Waypoint(18.25, 16, 90)
    });*/
	
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
			System.out.println("Waypoint(" + x_m +", " + y_m + ", " + theta_r + ")");
		}
		
		return standardPath;
	}
}
