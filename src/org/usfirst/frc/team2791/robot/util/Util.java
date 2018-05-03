package org.usfirst.frc.team2791.robot.util;


// This is a class with commonly used methods

import org.usfirst.frc.team2791.robot.Robot;

public class Util {

	private Util() {}
	
	
    /**
     * This returns 0 if the value is between the min and the max and the val otherwise.
     * @param min
     * @param val
     * @param max
     * @return
     */
	public static double deadzone(double min, double val, double max) {
		double absVal = Math.abs(val);
		double absMin = Math.abs(min);
		double absMax = Math.abs(max);
		
		if (absMin <= absVal && absVal <= absMax) {
			return val;
		} else if (absVal <= absMin) {
			return 0.0;
		} else {
			return val < 0 ? -absMax : absMax;
		}
	}
	
	
    /**
     * @param encoderTicks count on encoder
     * @param wheelDiameterInInches
     * @return number of feet traveled based on encoder ticks read
     */
    public static double tickToFeet(double encoderTicks, double wheelDiameterInInches) {
        return (wheelDiameterInInches * Math.PI / encoderTicks);
    }

    public static double average(double... numbers){
    	double total = 0;
    	for(int i = 0; i < numbers.length; i++){
    		total += numbers[i];
		}
		return total / numbers.length;
	}


    
//    public static double generatePathHashCode(Waypoint[] path) {
//        double hash = 1.0;
//        for(int i = 0; i < path.length; i ++) {
//            hash =  ((path[i].x * 3) + (path[i].y * 7) + (path[i].angle * 11));
//        }
//        return (int)Math.abs(hash * 1000);
//    }
}
