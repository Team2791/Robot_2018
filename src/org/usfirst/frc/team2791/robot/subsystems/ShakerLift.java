package org.usfirst.frc.team2791.robot.subsystems;

import static java.lang.StrictMath.max;
import static java.lang.StrictMath.min;
import static org.usfirst.frc.team2791.robot.util.Constants.CLOSE_TO_BOTTOM_DISTANCE;
import static org.usfirst.frc.team2791.robot.util.Constants.CLOSE_TO_TOP_DISTANCE;
import static org.usfirst.frc.team2791.robot.util.Constants.offset;
import static org.usfirst.frc.team2791.robot.util.Constants.ratio;

import org.usfirst.frc.team2791.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ShakerLift extends Subsystem {
    DigitalInput topLimitSwitch, bottomLimitSwitch;
    Solenoid Break;
    AnalogPotentiometer potentiometer;
    VictorSPX motorOne, motorTwo, motorThree;

    public ShakerLift() {
        super("ShakerLift");
        topLimitSwitch = new DigitalInput(RobotMap.LIMIT_SWITCH_TOP);
        bottomLimitSwitch = new DigitalInput(RobotMap.LIMIT_SWITCH_BOTTOM);
        Break = new Solenoid(RobotMap.BREAK_SOLENOID);
        potentiometer = new AnalogPotentiometer(0, 3600, 30);
    }

    @Override
    protected void initDefaultCommand() {
        // do nothing by default.
    }

    /**
     * Returns the lift height in inches from the bottom.
     *
     * @return
     */
    public double getHeight() {
        return (potentiometer.get() - offset) * ratio;
    }

    // this method is used to set the power of the lift and included saftey so the lift
    // is moving slowly near the top/bottom and once at the top/bottom can't break itself. 
    public void setPower(double power) {
        if (atBottom()) {
            power = max(0, power);
        } else if (closeToBottom()) {
            power = max(-.2, power);
        } else if (atTop()) {
            power = min(0, power);
        } else if (closeToTop()) {
            power = min(0.3, power);
        }
        // now we use the internal method that has direct control to the motor
        // after we have made sure that power is a safe number.
        setPowerUnsafe(power);
    }

    public boolean atBottom() {
        return bottomLimitSwitch.get();
    }

    public boolean closeToBottom() {
        return potentiometer.get() > CLOSE_TO_BOTTOM_DISTANCE;
    }

    public boolean atTop() {
        return topLimitSwitch.get();
    }

    public boolean closeToTop() {
        return potentiometer.get() > CLOSE_TO_TOP_DISTANCE;
    }

    private void setPowerUnsafe(double power) {
        motorOne.set(ControlMode.PercentOutput, power);
        motorTwo.set(ControlMode.PercentOutput, power);
        motorThree.set(ControlMode.PercentOutput, power);
    }
    public void setBreak(boolean breakOn){
        Break.set(breakOn);
    }


    public void debug(){
        SmartDashboard.putBoolean("Top Limit Switch value", topLimitSwitch.get());
        SmartDashboard.putBoolean("Bottom Limit Switch value", bottomLimitSwitch.get());
        SmartDashboard.putNumber("Potentiometer value",potentiometer.get());
        SmartDashboard.putNumber("Motor One value", motorOne.getMotorOutputPercent());
        SmartDashboard.putNumber("Motor Two value", motorTwo.getMotorOutputPercent());
        SmartDashboard.putNumber("Motor Three value", motorThree.getMotorOutputPercent());
        SmartDashboard.putBoolean("Break value", Break.get());
    }
}

