package org.usfirst.frc.team2791.robot.subsystems;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team2791.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Subsystem;
import static java.lang.StrictMath.max;
import static java.lang.StrictMath.min;
import static org.usfirst.frc.team2791.robot.util.Constants.*;


public class ShakerLift extends Subsystem {
    DigitalInput topLimitSwitch, bottomLimitSwitch;
    Solenoid Break;
    AnalogPotentiometer potentiometer;
    PWMVictorSPX motorOne, motorTwo, motorThree;

    public ShakerLift() {
        super("ShakerLift");
        topLimitSwitch = new DigitalInput(RobotMap.LIMIT_SWITCH_TOP);
        bottomLimitSwitch = new DigitalInput(RobotMap.LIMIT_SWITCH_BOTTOM);
        Break = new Solenoid(RobotMap.BOTTOM_SOLENOID);
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

    public double setPower(double power) {
        if (atBottom()) {
            power = max(0, power);
        } else if (closeToBottom()) {
            power = max(-.2, power);
        } else if (atTop()) {
            power = min(0, power);

        } else if (closeToTop()) {
            power = min(0.3, power);
        }
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


    public void debug(){
        SmartDashboard.putBoolean("Top Limit Switch value", topLimitSwitch.get());
        SmartDashboard.putBoolean("Bottom Limit Switch value", bottomLimitSwitch.get());
        SmartDashboard.putNumber("Potentiometer value",potentiometer.get());
        SmartDashboard.putNumber("Motor One value", motorOne.get());
        SmartDashboard.putNumber("Motor Two value", motorTwo.get());
        SmartDashboard.putNumber("Motor Three value", motorThree.get());
        SmartDashboard.putBoolean("Break value", Break.get());
    }
}

