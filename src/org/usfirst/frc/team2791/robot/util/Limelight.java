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
    private boolean validTarget;
    private double targetArea;
    private double targetSkew;
    private double latency;


    @SuppressWarnings("deprecation")
	public void Limelight(){
        // Set table to limelight
        table = NetworkTable.getTable("limelight");

        // Get stats
        this.horizontalOffset = this.table.getNumber("tx", 0);
        this.verticalOffset = this.table.getNumber("ty", 0);
        this.targetArea = this.table.getNumber("ta", 0);
        this.targetSkew = this.table.getNumber("ts", 0);
        this.latency = this.table.getNumber("tl", 0);
        this.validTarget = this.table.getNumber("tv", 0) == 1.0;

    }
    
    public void updateInformation() {
        horizontalOffset = table.getNumber("tx", 0);
        verticalOffset = table.getNumber("ty", 0);
        targetArea = table.getNumber("ta", 0);
        targetSkew = table.getNumber("ts", 0);
        latency = table.getNumber("tl", 0);
        validTarget = table.getNumber("tv", 0) == 1.0;
    }

    // Methods to get information
    public double getHorizontalOffset(){
        return horizontalOffset;
    }
    public double getVerticalOffset(){
        return verticalOffset;
    }
    public double getTargetArea(){
        return targetArea;
    }
    public double getTargetSkew(){
        return targetSkew;
    }
    public double getLatency(){
        return latency;
    }
    public boolean targetValid(){
        return validTarget;
    }

    // Methods to set Camera settings

    // Controls Leds
    // String mode must be either "on" or "off" or "blink"
    public void setLed(String mode){
        if(mode.equals("on")){
            table.putNumber("ledMode", 0);
        }
        else if(mode.equals("off")){
            table.putNumber("ledMode", 1);
        }
        else if(mode.equals("blink")){
            table.putNumber("ledMode", 2);
        }
        else{
            System.out.println("Limelight:   setLed(String mode) -----> mode not recognised\nSetting Leds to blink.");
            table.putNumber("ledMode", 2);
        }
    }
    
    // Sets the camera to a operation mode
    // String mode must be either "vision" or "driver"
    public void setCameraMode(String mode){
        if(mode.equals("vision")){
            table.putNumber("camMode", 0);
        }
        else if(mode.equals("driver")){
            table.putNumber("camMode", 1);
        }
        else{
            System.out.println("Limelight:   setCameraMode(String mode) -----> mode not recognised\nSetting camera to vision.");
            table.putNumber("camMode", 0);
        }
    }
}
