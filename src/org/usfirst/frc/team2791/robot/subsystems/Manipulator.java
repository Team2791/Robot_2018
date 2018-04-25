package org.usfirst.frc.team2791.robot.subsystems;


import org.usfirst.frc.team2791.robot.Constants;
import org.usfirst.frc.team2791.robot.Robot;
import org.usfirst.frc.team2791.robot.RobotMap;
import org.usfirst.frc.team2791.robot.commands.manipulator.HoldCube;
import org.usfirst.frc.team2791.robot.util.DelayedBoolean;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Manipulator extends Subsystem {

    private VictorSPX leftMotor,rightMotor;
    private DigitalInput iRSensorLeft,iRSensorRight;
    private Solenoid extender;
    
    // cache the current so we don't keep hitting the CAN bus
    private double lastCurrent, lastMaxCurrent;
    private Timer currentTimer, maxCurrentTimer;
    
    private DelayedBoolean cubeInGripperDelayedBoolean;
    private DelayedBoolean cubeJammedDelayedBoolean;
    
    public Manipulator() {
        leftMotor = new VictorSPX(RobotMap.INTAKE_SPARK_LEFT_PORT);
        rightMotor = new VictorSPX(RobotMap.INTAKE_SPARK_RIGHT_PORT);
        iRSensorLeft = new DigitalInput(RobotMap.IR_SENSOR_LEFT);
        iRSensorRight = new DigitalInput(RobotMap.IR_SENSOR_RIGHT);
        extender = new Solenoid(RobotMap.PCM_CAN_ID, RobotMap.INTAKE_EXTENDER_SOLENOID_PORT);

        // Invert Motors
        leftMotor.setInverted(false);
        leftMotor.setNeutralMode(NeutralMode.Brake);
        leftMotor.enableVoltageCompensation(true);
        leftMotor.configVoltageCompSaturation(12, 20);
        rightMotor.setInverted(true);
        rightMotor.setNeutralMode(NeutralMode.Brake);
        rightMotor.enableVoltageCompensation(true);
        rightMotor.configVoltageCompSaturation(12, 20);
        
        currentTimer = new Timer();
        currentTimer.start();
        maxCurrentTimer = new Timer();
        maxCurrentTimer.start();
        
        cubeInGripperDelayedBoolean = new DelayedBoolean(0.5);
        cubeJammedDelayedBoolean = new DelayedBoolean(0.1);
    }

    public void initDefaultCommand() {
        // TODO: Set the default command, if any, for a subsystem here. Example:
            setDefaultCommand(new HoldCube());
    }

    public boolean isCubeInGripper(){
        boolean left = !iRSensorLeft.get();
        boolean right = !iRSensorRight.get();
        
//	    return cubeInGripperDelayedBoolean.update(left && right);
	    // with unplugged sensors this becomes return false;
        return false; // ensure that there is no noise from the claw
    }

    // Don't know how to find out if cube is jammed
    // If difference between left and right sensors is large, then its jammed
    public boolean isCubeJammed(){
    	// this code works as indended however sometimes when the cube is jammed neither sensor is triggered
//        boolean left = !iRSensorLeft.get();
//        boolean right = !iRSensorRight.get();

//        return left ^ right;
    	return cubeJammedDelayedBoolean.update(getMaxMotorCurrent() > Constants.INTAKE_CUBE_STALL_CURRENT && !isCubeInGripper());
    }

    public void setLeftRightMotorSpeed(double leftSpeed, double rightSpeed) {
//    	System.out.println("M: "+leftSpeed + " : " + rightSpeed);
    	SmartDashboard.putString("Manipulator left right speed", leftSpeed + " : " + rightSpeed);
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
    
    public boolean getRetracted(){
        return extender.get();
    }
    
    public double getCurrentUsage() {
    	if(currentTimer.get() > 0.1) {
    		lastCurrent = Robot.pdp.getCurrent(RobotMap.PDP_INTAKE_LEFT) + Robot.pdp.getCurrent(RobotMap.PDP_INTAKE_RIGHT);
    		currentTimer.reset();
    		currentTimer.start();
    	}
    	return lastCurrent;
    }
    
    public double getMaxMotorCurrent() {
    	if(maxCurrentTimer.get() > 0.05) {
    		lastMaxCurrent = Math.max(Robot.pdp.getCurrent(RobotMap.PDP_INTAKE_LEFT), Robot.pdp.getCurrent(RobotMap.PDP_INTAKE_RIGHT));
    		maxCurrentTimer.reset();
    		maxCurrentTimer.start();
    	}
    	return lastMaxCurrent;	
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
        SmartDashboard.putNumber("Manipulator - Max Current", getMaxMotorCurrent());
    }
    
    public void run() {
    	
    }
}

