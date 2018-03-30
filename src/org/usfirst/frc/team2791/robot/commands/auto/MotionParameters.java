package org.usfirst.frc.team2791.robot.commands.auto;

import org.usfirst.frc.team2791.robot.util.SrxGains;

public class MotionParameters {
    private int _acceleration = 0;
    private int _cruiseVelocity = 0;
    private SrxGains _gains = new SrxGains(0, 0, 0, 0, 0, 0);

    public MotionParameters(int acceleration, int cruiseVelocity, SrxGains gains) {
        _acceleration = acceleration;
        _cruiseVelocity = cruiseVelocity;
        _gains = gains;
    }

    public MotionParameters() {
    }

    public SrxGains getGains() {
        return _gains;
    }

    public int getAcceleration() {
        return _acceleration;
    }

    public int getCruiseVelocity() {
        return _cruiseVelocity;
    }

    public void setSRXGains(SrxGains gains) {
        _gains = gains;
    }

    public void setAcceleration(int acceleration) {
        _acceleration = acceleration;
    }

    public void setCruiseVelocity(int cruiseVelocity) {
        _cruiseVelocity = cruiseVelocity;
    }
}
