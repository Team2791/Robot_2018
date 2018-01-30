/*
Name: Limelight.java
Author: Sam O
Date: 1/18/2018

Info:
This is the limelight class to control robots limelight.
More info: http://docs.limelightvision.io/en/latest/

*/


package org.usfirst.frc.team2791.robot.util;



import org.usfirst.frc.team2791.robot.util.Constants;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class Limelight {

    private NetworkTable table;
    private double horizontalOffset;
    private double verticalOffset;
    private double validTarget;
    private double targetArea;
    private double targetSkew;
    private double latency;


    public void Limelight(){
        // Set table to limelight
        this.table = NetworkTable.getTable("limelight");

        // Get stats
        this.horizontalOffset = this.table.getNumber("tx", 0);
        this.verticalOffset = this.table.getNumber("ty", 0);
        this.targetArea = this.table.getNumber("ta", 0);
        this.targetSkew = this.table.getNumber("ts", 0);
        this.latency = this.table.getNumber("tl", 0);
        this.validTarget = this.table.getNumber("tv", 0);

    }

    // Methods to get information
    public double getHorizontalOffset(){
        return this.horizontalOffset;
    }
    public double getVerticalOffset(){
        return this.verticalOffset;
    }
    public double getTargetArea(){
        return this.targetArea;
    }
    public double getTargetSkew(){
        return this.targetSkew;
    }
    public double getLatency(){
        return this.latency;
    }
    public boolean targetValid(){
    	if(this.validTarget == 1.0) {
    		return true;
    	}
    	else {
    		return false;
    	}
        
    }

    // Methods to set Camera settings

    // Controls Leds
    // String mode must be either "on" or "off" or "blink"
    public void setLed(String mode){
        if(mode.equals("on")){
            this.table.putNumber("ledMode", 0);
        }
        else if(mode.equals("off")){
            this.table.putNumber("ledMode", 1);
        }
        else if(mode.equals("blink")){
            this.table.putNumber("ledMode", 2);
        }
        else{
            System.out.println("Limelight:   setLed(String mode) -----> mode not recognised\nSetting Leds to blink.");
            this.table.putNumber("ledMode", 2);
        }
    }
    // Sets the camera to a operation mode
    // String mode must be either "vision" or "driver"
    public void setCameraMode(String mode){
        if(mode.equals("vision")){
            this.table.putNumber("camMode", 0);
        }
        else if(mode.equals("driver")){
            this.table.putNumber("camMode", 1);
        }
        else{
            System.out.println("Limelight:   setCameraMode(String mode) -----> mode not recognised\nSetting camera to vision.");
            this.table.putNumber("camMode", 0);
        }

    }
}
