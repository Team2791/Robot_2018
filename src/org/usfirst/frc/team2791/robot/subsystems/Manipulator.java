package org.usfirst.frc.team2791.robot.subsystems;


import org.usfirst.frc.team2791.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Manipulator extends Subsystem {

    private VictorSPX leftMotor,rightMotor;
    private DigitalInput iRSensorLeft,iRSensorRight;
    private Solenoid extender;



    public Manipulator(){
        leftMotor = new VictorSPX(RobotMap.INTAKE_SPARK_LEFT_PORT);
        rightMotor = new VictorSPX(RobotMap.INTAKE_SPARK_RIGHT_PORT);
        iRSensorLeft = new DigitalInput(RobotMap.IR_SENSOR_LEFT);
        iRSensorRight = new DigitalInput(RobotMap.IR_SENSOR_RIGHT);
        extender = new Solenoid(RobotMap.INTAKE_EXTENDER_SOLENOID_PORT);
        //grabber = new Solenoid(RobotMap.INTAKE_GRABBER_SOLENOID_PORT);

        // Invert Motors
        leftMotor.setInverted(false);
        rightMotor.setInverted(true);


    }

    public boolean isCubeInGripper(){
        boolean left = iRSensorLeft.get();
        boolean right = iRSensorRight.get();
        if(left && right){
            return true;
        }
        return false;
    }

    // Don't know how to find out if cube is jammed
    // If difference between left and right sensors is large, then its jammed
    public boolean isCubeJammed(){
        boolean left = iRSensorLeft.get();
        boolean right = iRSensorRight.get();

        return left ^ right;
    }

    public void setLeftRightMotorSpeed(double leftSpeed, double rightSpeed){
        leftMotor.set(ControlMode.Velocity, leftSpeed);
        rightMotor.set(ControlMode.Velocity, rightSpeed);
    }

    public boolean isRetracted(){
        // Not sure if extender.get returns true if extended or retracted
        return !extender.get();
    }

//    public boolean areCubeArmsClosed(){
//        //Not sure if grabber.get returns true if open or closed
//        return grabber.get();
//    }

//    public void setCubeArmsClosed(boolean closed){
//        grabber.set(closed);
//    }

    public void setRetracted(boolean retract){
        extender.set(retract);
    }



    public void initDefaultCommand() {
        // TODO: Set the default command, if any, for a subsystem here. Example:
        //    setDefaultCommand(new MySpecialCommand());
    }

    public void debug(){
        SmartDashboard.putString("Manipulator Left Motor Percent", Double.toString(leftMotor.getMotorOutputPercent()));
        SmartDashboard.putString("Manipulator Right Motor Percent", Double.toString(rightMotor.getMotorOutputPercent()));
//        SmartDashboard.putBoolean("Manipulator Left Sensor", iRSensorLeft.get());
//        SmartDashboard.putBoolean("Manipulator Right Sensor", iRSensorRight.get());
        SmartDashboard.putBoolean("Manipulator Extender Solenoid", extender.get());
//        SmartDashboard.putBoolean("Manipulator Grabber Solenoid", grabber.get());


    }
}

