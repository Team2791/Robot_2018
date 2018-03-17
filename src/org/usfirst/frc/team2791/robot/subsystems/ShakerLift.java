package org.usfirst.frc.team2791.robot.subsystems;

import static java.lang.StrictMath.max;
import static java.lang.StrictMath.min;

import org.usfirst.frc.team2791.robot.Constants;
import org.usfirst.frc.team2791.robot.RobotMap;
import org.usfirst.frc.team2791.robot.commands.lift.StopLift;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
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
    Solenoid breakSolenoid;
    AnalogPotentiometer potentiometer;
    TalonSRX leaderTalon;
    VictorSPX followerVictor;
    BaseMotorController[] motorControllers;
    AnalogInput potAnalogInput;
    Timer breakReleaseTimer;
    boolean breakReleaseTimerStarted = false;

    public ShakerLift() {
        super("ShakerLift");
        topLimitSwitch = new DigitalInput(RobotMap.LIMIT_SWITCH_TOP);
        bottomLimitSwitch = new DigitalInput(RobotMap.LIMIT_SWITCH_BOTTOM);
        potAnalogInput = new AnalogInput(RobotMap.LIFT_POT_PORT);
        potentiometer = new AnalogPotentiometer(potAnalogInput, Constants.LIFT_POT_FULL_RANGE, Constants.LIFT_POT_OFFSET);
        
        breakSolenoid = new Solenoid(RobotMap.PCM_CAN_ID, RobotMap.BREAK_SOLENOID);
        breakReleaseTimer = new Timer();

        leaderTalon = new TalonSRX(RobotMap.LIFT_TALON_ONE);
        leaderTalon.configSelectedFeedbackSensor(FeedbackDevice.Analog, 0, 0);
        followerVictor = new VictorSPX(RobotMap.LIFT_VICTOR_TWO);
        
        // vv removed motorOne from here because we're not using it.
        motorControllers = new BaseMotorController[] {  leaderTalon, followerVictor };
        
        for(int i=0; i<motorControllers.length; i++) {
        	motorControllers[i].setNeutralMode(NeutralMode.Brake);
        	// this will limit the motor controllers from shocking the lift with full power
        	// it will take them 0.5s to ramp up to full power.
        	motorControllers[i].configOpenloopRamp(0.25, 10); 
        }

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
    	// From the TalonSRX software manual
    	// - Analog-In Position, Analog-In Velocity, 10bit ADC Value, 
    	// The value can be positive or negative so only divide by 2**9
    	// THIS DOES NOT WORK!
    	double potVoltage = leaderTalon.getSelectedSensorPosition(0) / Math.pow(2, 9);
    	return potVoltage * Constants.LIFT_POT_FULL_RANGE/5.00 - Constants.LIFT_POT_OFFSET;
//    	return potentiometer.get();
    }

    // this method is used to set the power of the lift and included saftey so the lift
    // is moving slowly near the top/bottom and once at the top/bottom can't break itself. 
    public void setPower(double power) {
    	// make sure the break is released before we let it move
    	if(breakReleaseTimer.get() < 0.12) {
    		setPowerUnsafe(0);
    		return;
    	}
    	
        if (atBottom()) {
            power = max(0, power);
        } else if (closeToBottom()) {
            power = max(0, power); // was 0.2 without manipulator, if it needs to be something it can be -0.05 or something very small
        } else if (atTop()) {
            power = min(0, power);
        } else if (closeToTop()) {
            power = min(0.35, power);
        }

        // increasing speed from -0.45 to -.6 before Utica.
        power = max(-.60, power);
        
        // now we use the internal method that has direct control to the motor
        // after we have made sure that power is a safe number.
        setPowerUnsafe(power);
    }

    public boolean atBottom() {
        return !bottomLimitSwitch.get() || getHeight() < Constants.LIFT_MIN_HEIGHT - 0.1;
    }

    public boolean closeToBottom() {
        return getHeight() < Constants.LIFT_MIN_HEIGHT + Constants.BOTTOM_SAFTEY_DISTANCE;
    }

    public boolean atTop() {
        return !topLimitSwitch.get() || getHeight() > Constants.LIFT_MAX_HEIGHT + 0.1;
    }

    public boolean closeToTop() {
        return getHeight() > Constants.LIFT_MAX_HEIGHT - Constants.TOP_SAFTEY_DISTANCE;
    }

    private void setPowerUnsafe(double power) {
    	for(int i=0; i<motorControllers.length; i++) {
        	motorControllers[i].set(ControlMode.PercentOutput, power); 
        }
    }

    public void setBreak(boolean breakOn){
        breakSolenoid.set(!breakOn); // Solenoid is default on. True means the break will be off
        
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
        
        SmartDashboard.putNumber("Lift - Height", getHeight());
        SmartDashboard.putNumber("Lift - Analog voltage value", potAnalogInput.getVoltage());
        
//        SmartDashboard.putNumber("Lift - Motor One value", motorOne.getMotorOutputPercent());
//        SmartDashboard.putNumber("Lift - Motor Two value", motorTwo.getMotorOutputPercent());
//        SmartDashboard.putNumber("Lift - Motor Three value", motorThree.getMotorOutputPercent());
        SmartDashboard.putBoolean("Lift - Break value", !breakSolenoid.get());
        
        SmartDashboard.putNumber("Lift - break timer", breakReleaseTimer.get());
    }
}

