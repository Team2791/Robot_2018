package org.usfirst.frc.team2791.robot.util;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Button;

public Class DoubleButton extends Button{
    Joystick stick;
    int b1;
    int b2;
    public DoubleButton(Joystick joystick, int button1, int button2){
        stick = joystick;
        b1 = button1;
        b2 = button2;
    }
    
    @Override
    public boolean get(){
        return stick.getRawButton(b1) && stick.getRawButton(b2);
    }
}
