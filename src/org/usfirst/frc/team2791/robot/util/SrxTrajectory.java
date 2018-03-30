package org.usfirst.frc.team2791.robot.util;

public class SrxTrajectory {
    public boolean flipped;
    public boolean highGear;
    public SrxMotionProfile leftProfile;
    public SrxMotionProfile centerProfile;
    public SrxMotionProfile rightProfile;

    public SrxTrajectory(){

    }
    public SrxTrajectory(SrxMotionProfile left, SrxMotionProfile center, SrxMotionProfile right){
        this.leftProfile = left;
        this.centerProfile = center;
        this.rightProfile = right;
    }

}
