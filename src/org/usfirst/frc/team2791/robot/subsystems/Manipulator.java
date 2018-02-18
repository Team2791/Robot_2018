package org.usfirst.frc.team2791.robot.subsystems;


import org.usfirst.frc.team2791.robot.Robot;
import org.usfirst.frc.team2791.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
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
        extender = new Solenoid(RobotMap.PCM_CAN_ID, RobotMap.INTAKE_EXTENDER_SOLENOID_PORT);

        // Invert Motors
        leftMotor.setInverted(true);
        leftMotor.setNeutralMode(NeutralMode.Brake);
        leftMotor.enableVoltageCompensation(true);
        leftMotor.configVoltageCompSaturation(12, 20);
        rightMotor.setInverted(true);
        rightMotor.setNeutralMode(NeutralMode.Brake);
        rightMotor.enableVoltageCompensation(true);
        rightMotor.configVoltageCompSaturation(12, 20);
    }
    
    public void initDefaultCommand() {
        // TODO: Set the default command, if any, for a subsystem here. Example:
//            setDefaultCommand(new RunManipulatorWithJoystick());
    }

    public boolean isCubeInGripper(){
        boolean left = !iRSensorLeft.get();
        boolean right = !iRSensorRight.get();
        
        return left && right;
    }

    // Don't know how to find out if cube is jammed
    // If difference between left and right sensors is large, then its jammed
    public boolean isCubeJammed(){
    	// this code works as indended however sometimes when the cube is jammed neither sensor is triggered
//        boolean left = !iRSensorLeft.get();
//        boolean right = !iRSensorRight.get();
//
//        return left ^ right;
    	return getCurrentUsage() > 12.5;
    	
    }

    public void setLeftRightMotorSpeed(double leftSpeed, double rightSpeed) {
        leftMotor.set(ControlMode.PercentOutput, leftSpeed);
        rightMotor.set(ControlMode.PercentOutput, rightSpeed);
    }

    public boolean isRetracted(){
        // Not sure if extender.get returns true if extended or retracted
        return !extender.get();
    }


    public void setRetracted(boolean retract){
        extender.set(retract);
    }
    
    public double getCurrentUsage() {
    	return Robot.pdp.getCurrent(RobotMap.PDP_INTAKE_LEFT) + Robot.pdp.getCurrent(RobotMap.PDP_INTAKE_RIGHT); 	
    }


    public void debug(){
//        SmartDashboard.putString("Manipulator Left Motor Percent", Double.toString(leftMotor.getMotorOutputPercent()));
//        SmartDashboard.putString("Manipulator Right Motor Percent", Double.toString(rightMotor.getMotorOutputPercent()));
        SmartDashboard.putBoolean("Manipulator Left Sensor", !iRSensorLeft.get());
        SmartDashboard.putBoolean("Manipulator Right Sensor", !iRSensorRight.get());
        SmartDashboard.putBoolean("Manipulator Extender Solenoid", extender.get());
        SmartDashboard.putBoolean("Manipulator Cube in gripper", isCubeInGripper());
        SmartDashboard.putBoolean("Manipulator Cube jammed", isCubeJammed());
        SmartDashboard.putNumber("Manipulator - Current", getCurrentUsage());
    }
}

