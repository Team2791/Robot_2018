package org.usfirst.frc.team2791.robot.util;

import java.lang.reflect.Array;

public class SpeedFunction {
    public double getSpeed(double distanceTraveled){
        if (distanceTraveled < 0.25 ){
            return .4;
        }
        else if (distanceTraveled < 0.75){
            return .7;
        }
        else{
            return .2;

        }

    }

}