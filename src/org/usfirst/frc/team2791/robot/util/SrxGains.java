package org.usfirst.frc.team2791.robot.util;

public class SrxGains {
    public double P, I, D, F;
    public int parameterSlot, iZone;

    public SrxGains(int slot, double p, double i, double d, double f, int iZone){
        this.parameterSlot = slot;
        this.P = p;
        this.I = i;
        this.D = d;
        this.F = f;
        this.iZone= iZone;
    }
}