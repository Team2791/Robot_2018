package org.usfirst.frc.team2791.robot.util;

// This is a class with commonly used methods

public class Util {

	private Util() {}
	
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
}
