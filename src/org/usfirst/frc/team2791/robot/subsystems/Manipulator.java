package org.usfirst.frc.team2791.robot.subsystems;


import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.BaseMotorController;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Ultrasonic;
import org.usfirst.frc.team2791.robot.RobotMap;
import org.usfirst.frc.team2791.robot.util.Constants;
import org.usfirst.frc.team2791.robot.util.Util;

public class Manipulator extends Subsystem {

    private Spark leftMotor,rightMotor;
    private Ultrasonic iRSensorLeft,iRSensorRight;
    private Solenoid extender, grabber;



    public void Manipulator(){
        leftMotor = new Spark(RobotMap.INTAKE_SPARK_LEFT_PORT);
        rightMotor = new Spark(RobotMap.INTAKE_SPARK_RIGHT_PORT);
        iRSensorLeft = new Ultrasonic(1,1);
        iRSensorRight = new Ultrasonic(1, 1);
        extender = new Solenoid(RobotMap.INTAKE_EXTENDER_SOLENOID_PORT);
        grabber = new Solenoid(RobotMap.INTAKE_GRABBER_SOLENOID_PORT);


        // Turn on Distance sensors
        iRSensorRight.setEnabled(true);
        iRSensorLeft.setEnabled(true);
        // Make them work automatically
        iRSensorRight.setAutomaticMode(true);
        iRSensorLeft.setAutomaticMode(true);

    }

    public boolean isCubeInGripper(){
        double left = iRSensorLeft.getRangeInches();
        double right = iRSensorRight.getRangeInches();

        if(Util.average(left, right) <= Constants.MIN_SENSOR_RANGE_INCHES){
            return true;
        }
        return false;
    }

    // Don't know how to find out if cube is jammed
    // If difference between left and right sensors is large, then its jammed
    public boolean isCubeJammed(){
        double left = iRSensorLeft.getRangeInches();
        double right = iRSensorRight.getRangeInches();

        if(Math.abs(left - right) > Constants.SENSOR_LEFT_RIGHT_ACCEPTABLE_DIFFERENCE){
            return true;
        }
        return false;

    }

    public void setLeftRightMotorSpeed(double leftSpeed, double rightSpeed){
        leftMotor.set(leftSpeed);
        rightMotor.set(rightSpeed);
    }

    public boolean isRetracted(){
        // Not sure if extender.get returns true if extended or retracted
        return !extender.get();
    }

    public boolean areCubeArmsClosed(){
        //Not sure if grabber.get returns true if open or closed
        return grabber.get();
    }

    public void setCubeArmsClosed(boolean closed){
        grabber.set(closed);
    }

    public void setRetracted(boolean retract){
        extender.set(retract);
    }



    public void initDefaultCommand() {
        // TODO: Set the default command, if any, for a subsystem here. Example:
        //    setDefaultCommand(new MySpecialCommand());
    }
}

