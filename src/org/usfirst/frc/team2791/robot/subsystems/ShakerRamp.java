package org.usfirst.frc.team2791.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team2791.robot.RobotMap;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ShakerRamp extends Subsystem {
    DigitalInput leftLimitSwitchOne, leftLimitSwitchTwo, rightLimitSwitchOne, rightLimitSwitchTwo;
    Solenoid rampDeploySolenoid;

    public ShakerRamp() {
        super("shakerRamp");
        // Defines the limit switch sensors
        leftLimitSwitchOne = new DigitalInput(RobotMap.LIMIT_SWITCH_LEFT_1);
        leftLimitSwitchTwo = new DigitalInput(RobotMap.LIMIT_SWITCH_LEFT_2);
        rightLimitSwitchOne = new DigitalInput(RobotMap.LIMIT_SWITCH_RIGHT_1);
        rightLimitSwitchTwo = new DigitalInput(RobotMap.LIMIT_SWITCH_RIGHT_2);
        // Define the solenoids to control ramps
        rampDeploySolenoid = new Solenoid(RobotMap.RAMP_DEPLOY_SOLENOID);
    }

    public void setRampsDown(boolean setDown) {
        rampDeploySolenoid.set(setDown);
    }

    // Checks if LeftRamp is up
    public boolean isLeftRampUp() {
        return leftLimitSwitchOne.get() || leftLimitSwitchTwo.get() || isLeftRampCurrentHigh();
    }

    // Checks if RightRamp is up
    public boolean isRightRampUp() {
        return rightLimitSwitchOne.get() || rightLimitSwitchTwo.get() || isRightRampCurrentHigh();
    }

    private boolean isLeftRampCurrentHigh() {
        // TODO implement this method!!
        return false;
    }

    private boolean isRightRampCurrentHigh() {
        // TODO implement this method!!
        return false;
    }

    @Override
    protected void initDefaultCommand() {
        // There is no default command for the ramps.
    }

    //does this subsystem need a debug? -JY
    public void debug() {
        SmartDashboard.putBoolean("Getting the Left Limit Switch One value", leftLimitSwitchOne.get());
        SmartDashboard.putBoolean("Getting the Left Limit Switch Two value", leftLimitSwitchTwo.get());
        SmartDashboard.putBoolean("Getting the Right Limit Switch One value" rightLimitSwitchOne.get());
        SmartDashboard.putBoolean("Getting the Right Limit Switch Two value", rightLimitSwitchTwo.get());
        SmartDashboard.putBoolean("Getting the ramp Deploy value", rampDeploySolenoid.get());
    }
}