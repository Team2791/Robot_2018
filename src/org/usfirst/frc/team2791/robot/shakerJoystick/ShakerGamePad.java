package org.usfirst.frc.team2791.robot.shakerJoystick;

import org.usfirst.frc.team2791.robot.Constants;
import org.usfirst.frc.team2791.robot.util.Util;

import edu.wpi.first.wpilibj.Joystick;
public class ShakerGamePad extends Joystick {
    int port;
	public ShakerGamePad(int port){
		super(port);
		this.port = port;
	}

    public boolean getDpadUp() {
        return super.getPOV(0) == 0;
    }

    public boolean getDpadUpRight() {
        return super.getPOV(0) == 45;
    }

    public boolean getDpadRight() {
        return super.getPOV(0) == 90;
    }

    public boolean getDpadDownRight() {
        return super.getPOV(0) == 135;
    }

    public boolean getDpadDown() {
        return super.getPOV(0) == 180;
    }

    public boolean getDpadDownLeft() {
        return super.getPOV(0) == 225;
    }

    public boolean getDpadLeft() {
        return super.getPOV(0) == 270;
    }

    public boolean getDpadUpLeft() {
        return super.getPOV(0) == 315;
    }

    public boolean getButtonStatus() {
        return super.getRawButton(this.port);
    }



    public double getAxisLeftX() {
        return Util.deadzone(Constants.DEADZONE, super.getRawAxis(0), 1.0);
    }

    public double getAxisLeftY() {
        return -1 * Util.deadzone(Constants.DEADZONE, super.getRawAxis(1), 1.0);
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
        return -1 * Util.deadzone(Constants.DEADZONE, super.getRawAxis(5), 1.0);
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
