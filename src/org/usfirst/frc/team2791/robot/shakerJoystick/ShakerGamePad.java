package org.usfirst.frc.team2791.robot.shakerJoystick;

import org.usfirst.frc.team2791.robot.util.Constants;
import org.usfirst.frc.team2791.robot.util.Util;

import edu.wpi.first.wpilibj.Joystick;
public class ShakerGamePad extends Joystick {
	
	public ShakerGamePad(int port){
		super(port);
	}

//    public boolean getDpadUp() {
//        return super.getPOV(0) == 0;
//    }
//
//    public boolean getDpadUpRight() {
//        return super.getPOV(0) == 45;
//    }
//
//    public boolean getDpadRight() {
//        return super.getPOV(0) == 90;
//    }
//
//    public boolean getDpadDownRight() {
//        return super.getPOV(0) == 135;
//    }
//
//    public boolean getDpadDown() {
//        return super.getPOV(0) == 180;
//    }
//
//    public boolean getDpadDownLeft() {
//        return super.getPOV(0) == 225;
//    }
//
//    public boolean getDpadLeft() {
//        return super.getPOV(0) == 270;
//    }
//
//    public boolean getDpadUpLeft() {
//        return super.getPOV(0) == 315;
//    }
//
//    public boolean getButtonA() {
//        return super.getRawButton(1);
//    }
//
//    public boolean getButtonB() {
//        return super.getRawButton(2);
//    }
//
//    public boolean getButtonX() {
//        return super.getRawButton(3);
//    }
//
//    public boolean getButtonY() {
//        return super.getRawButton(4);
//    }
//
//    public boolean getButtonLB() {
//        return super.getRawButton(5);
//    }
//
//    public boolean getButtonRB() {
//        return super.getRawButton(6);
//    }
//
//    public boolean getButtonBack() {
//        return super.getRawButton(7);
//    }
//
//    public boolean getButtonSt() {
//        return super.getRawButton(8);
//    }
//
//    public boolean getButtonLS() {
//        return super.getRawButton(9);
//    }
//
//    public boolean getButtonRS() {
//        return super.getRawButton(10);
//    }
//
    public double getAxisLeftX() {
        return Util.deadzone(Constants.DEADZONE, super.getRawAxis(0), 1.0);
    }

    public double getAxisLeftY() {
        return Util.deadzone(Constants.DEADZONE, super.getRawAxis(1), 1.0);
    }

    public double getAxisLT() {
        return Util.deadzone(Constants.DEADZONE, super.getRawAxis(2), 1.0);
    }

    public double getAxisRT() {
        return Util.deadzone(Constants.DEADZONE, super.getRawAxis(3), 1.0);
    }

    public double getAxisRightX() {
        return Util.deadzone(Constants.DEADZONE, super.getRawAxis(4), 1.0);
    }

    public double getAxisRightY() {
        return Util.deadzone(Constants.DEADZONE, super.getRawAxis(5), 1.0);
    }
    
    //GTA Drive

    public double getGtaDriveLeft() {
        // Does the math to get Gta Drive Type on left motor
        double leftAxis;
        if (getAxisLeftX() < 0)
            leftAxis = -Math.pow(getAxisLeftX(), 2);
        else
            leftAxis = Math.pow(getAxisLeftX(), 2);
        double combined = leftAxis + getAxisRT() - getAxisLT();
        if (combined > 1.0)
            return 1.0;
        else if (combined < -1.0)
            return -1.0;
        return combined;
    }

    /**
     * Uses right trigger for drive speed
     * @return output value for right drive motors (double)
     */
    public double getGtaDriveRight() {
        // Does the math to get Gta Drive Type on right motor
        double leftAxis;
        if (getAxisLeftX() < 0)
            leftAxis = -Math.pow(getAxisLeftX(), 2);
        else
            leftAxis = Math.pow(getAxisLeftX(), 2);
        double combined = -leftAxis + getAxisRT() - getAxisLT();
        if (combined > 1.0)
            return 1.0;
        else if (combined < -1.0)
            return -1.0;
        return combined;
    }	
}
