/*
Name: Limelight.java
Author: Sam O
Date: 1/18/2018

Info:
This is the limelight class to control robots limelight.
More info: http://docs.limelightvision.io/en/latest/

*/


package org.usfirst.frc.team2791.robot.util;




import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Limelight {
    NetworkTable table;
    private NetworkTableEntry camMode, ledMode, tx, ty, ta, tv, ts, tl;
    private double horizontalOffset, verticalOffset, validTarget, targetArea, targetSkew, latency;


    public void Limelight(){
        // Set table to limelight
        table = NetworkTableInstance.getDefault().getTable("limelight");

        // Get stats
        NetworkTableEntry tx = table.getEntry("tx");
        NetworkTableEntry ty = table.getEntry("ty");
        NetworkTableEntry ta = table.getEntry("ta");
        NetworkTableEntry tv = table.getEntry("tv");
        NetworkTableEntry ts = table.getEntry("ts");
        NetworkTableEntry tl = table.getEntry("tl");

        ledMode = table.getEntry("ledMode");
        camMode = table.getEntry("camMode");

        horizontalOffset = tx.getDouble(100.0);
        verticalOffset = ty.getDouble(0.0);
        validTarget = tv.getDouble(0.0);
        targetArea = ta.getDouble(0.0);
        targetSkew = ts.getDouble(0.0);
        latency = tl.getDouble(0.0);



    }

    public void refresh(){
        ledMode = table.getEntry("ledMode");
        camMode = table.getEntry("camMode");

        horizontalOffset = tx.getDouble(100.0);
        verticalOffset = ty.getDouble(0.0);
        validTarget = tv.getDouble(0.0);
        targetArea = ta.getDouble(0.0);
        targetSkew = ts.getDouble(0.0);
        latency = tl.getDouble(0.0);
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
    	if(validTarget == 1.0) {
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
            ledMode.setNumber(0);

        }
        else if(mode.equals("off")){
            ledMode.setNumber(1);
        }
        else if(mode.equals("blink")){
            ledMode.setNumber(2);
        }
        else{
            System.out.println("Limelight:   setLed(String mode) -----> mode not recognised\nSetting Leds to blink.");
            ledMode.setNumber(2);
        }
    }
    // Sets the camera to a operation mode
    // String mode must be either "vision" or "driver"
    public void setCameraMode(String mode){
        if(mode.equals("vision")){
            camMode.setNumber(0);
        }
        else if(mode.equals("driver")){
            camMode.setNumber(1);
        }
        else{
            System.out.println("Limelight:   setCameraMode(String mode) -----> mode not recognised\nSetting camera to vision.");
            camMode.setNumber(0);
        }

    }
}
