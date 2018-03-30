package org.usfirst.frc.team2791.robot.commands.auto;

import org.usfirst.frc.team2791.robot.util.SrxGains;

public class MotionParameters {
    public int getAcceleration() {
        return acceleration;
    }

    public int getCruiseVelocity() {
        return cruiseVelocity;
    }

    public SrxGains getGains() {
        return gains;
    }
}
