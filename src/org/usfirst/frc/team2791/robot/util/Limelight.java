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

    public void Limelight(){
        // Set table to limelight
        table = NetworkTable.getTable("limelight");
    }

    // Methods to get information
    public double getHorizontalOffset(){
    	return table.getNumber("tx", 0);
    }
    public double getVerticalOffset(){
        return table.getNumber("ty", 0);
    }
    public double getTargetArea(){
        return table.getNumber("ta", 0);
    }
    public double getTargetSkew(){
        return table.getNumber("ts", 0);
    }
    public double getLatency(){
        return table.getNumber("tl", 0);
    }
    public boolean targetValid(){
    	double tv = table.getNumber("tv", 0);
    	if(tv == 1.0) {
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
    	// TODO change this to an enum instead of a string
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
    	// TODO change this to an enum instead of a string
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
