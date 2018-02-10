package org.usfirst.frc.team2791.robot.subsystems;

import static java.lang.StrictMath.max;
import static java.lang.StrictMath.min;

import org.usfirst.frc.team2791.robot.RobotMap;
import org.usfirst.frc.team2791.robot.commands.lift.StopLift;
import org.usfirst.frc.team2791.robot.util.Constants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.BaseMotorController;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ShakerLift extends Subsystem {
    DigitalInput topLimitSwitch, bottomLimitSwitch;
    Solenoid Break;
    AnalogPotentiometer potentiometer;
    BaseMotorController motorOne, motorTwo, motorThree;
    AnalogInput potAnalogInput;
    Timer breakReleaseTimer;
    boolean breakReleaseTimerStarted = false;

    public ShakerLift() {
        super("ShakerLift");
        topLimitSwitch = new DigitalInput(RobotMap.LIMIT_SWITCH_TOP);
        bottomLimitSwitch = new DigitalInput(RobotMap.LIMIT_SWITCH_BOTTOM);
        Break = new Solenoid(RobotMap.BREAK_SOLENOID);
        potAnalogInput = new AnalogInput(RobotMap.LIFT_POT_PORT);
        potentiometer = new AnalogPotentiometer(potAnalogInput, Constants.LIFT_POT_FULL_RANGE, Constants.LIFT_POT_OFFSET);
        breakReleaseTimer = new Timer();
        
        
        motorOne = new TalonSRX(RobotMap.LIFT_TALON_ONE);
        motorOne.setNeutralMode(NeutralMode.Brake);
        motorTwo = new VictorSPX(RobotMap.LIFT_VICTOR_TWO);
        motorTwo.setNeutralMode(NeutralMode.Brake);
        motorThree = new VictorSPX(RobotMap.LIFT_VICTOR_THREE);
        motorThree.setNeutralMode(NeutralMode.Brake);
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new StopLift());
    }

    /**
     * Returns the lift height in inches from the bottom.
     *
     * @return
     */
    public double getHeight() {
    	return potentiometer.get();
    }

    // this method is used to set the power of the lift and included saftey so the lift
    // is moving slowly near the top/bottom and once at the top/bottom can't break itself. 
    public void setPower(double power) {
    	// make sure the break is released before we let it move
    	if(breakReleaseTimer.get() < 0.15) {
    		setPowerUnsafe(0);
    		return;
    	}
    	
        if (atBottom()) {
            power = max(0, power);
        } else if (closeToBottom()) {
            power = max(-.2, power);
        } else if (atTop()) {
            power = min(0, power);
        } else if (closeToTop()) {
            power = min(0.2, power);
        }
        // now we use the internal method that has direct control to the motor
        // after we have made sure that power is a safe number.
        setPowerUnsafe(power);
    }

    public boolean atBottom() {
        return !bottomLimitSwitch.get();
    }

    public boolean closeToBottom() {
        return potentiometer.get() < Constants.LIFT_MIN_HEIGHT + Constants.CLOSE_TO_HARD_STOPS_DISTANCE;
    }

    public boolean atTop() {
        return !topLimitSwitch.get();
    }

    public boolean closeToTop() {
        return potentiometer.get() > Constants.LIFT_MAX_HEIGHT - Constants.CLOSE_TO_HARD_STOPS_DISTANCE;
    }

    private void setPowerUnsafe(double power) {
        motorOne.set(ControlMode.PercentOutput, power);
        motorTwo.set(ControlMode.PercentOutput, power);
        motorThree.set(ControlMode.PercentOutput, power);
    }

    public void setBreak(boolean breakOn){
        Break.set(breakOn);
        
        if(breakOn) {
        	// reset and stop the timer when we put the break on.
        	breakReleaseTimer.reset();
        	breakReleaseTimer.stop();
        	breakReleaseTimerStarted = false;
        } else {
        	// when we release the break start the timer.
        	// we need to check if we've already started the timer because the
        	// start timer method also resets it.
        	if(!breakReleaseTimerStarted) {
        		breakReleaseTimer.start();
        		breakReleaseTimerStarted = true;
        	}
        }
    }


    public void debug(){
        SmartDashboard.putBoolean("Lift - Top Limit Switch value", !topLimitSwitch.get());
        SmartDashboard.putBoolean("Lift - Bottom Limit Switch value", !bottomLimitSwitch.get());
        
        SmartDashboard.putBoolean("Lift - Close to top", closeToTop());
        SmartDashboard.putBoolean("Lift - Close to bottom", closeToBottom());
        
        SmartDashboard.putNumber("Lift - Potentiometer value", potentiometer.get());
        SmartDashboard.putNumber("Lift - Analog voltage value", potAnalogInput.getVoltage());
        
        SmartDashboard.putNumber("Lift - Motor One value", motorOne.getMotorOutputPercent());
        SmartDashboard.putNumber("Lift - Motor Two value", motorTwo.getMotorOutputPercent());
        SmartDashboard.putNumber("Lift - Motor Three value", motorThree.getMotorOutputPercent());
        SmartDashboard.putBoolean("Lift - Break value", Break.get());
        
        SmartDashboard.putNumber("Lift - break timer", breakReleaseTimer.get());
        
        
    }
}

