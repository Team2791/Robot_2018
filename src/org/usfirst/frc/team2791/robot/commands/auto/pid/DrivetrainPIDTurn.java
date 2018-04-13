package org.usfirst.frc.team2791.robot.commands.auto.pid;

import org.usfirst.frc.team2791.robot.Constants;
import org.usfirst.frc.team2791.robot.Robot;
import org.usfirst.frc.team2791.robot.util.BasicPID;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *Uses BasicPID util class to create a PID for auto-turning. PID makes sure that the angle is correct
 *@see BasicPID
 */
public abstract class DrivetrainPIDTurn extends Command {
	
	private final double MIN_POWER_TO_TURN = 0;
	protected double errorThreshold = 1;
	protected BasicPID stationaryAnglePID;
	
	/**
	 * @param errorThreshold the angle in degrees you would like to turn, ***negative if counterclockwise*** *
	 * @param maxOutput the maximum output you would like the motors to receive (up to 1.0)
	 */
    public DrivetrainPIDTurn(double maxOutput, double errorThreshold) {
    	super("Turning Base Class");
    	assert(maxOutput > 0);

        requires(Robot.drivetrain);
        this.errorThreshold = errorThreshold;
        
		stationaryAnglePID = new BasicPID(Constants.STATIONARY_ANGLE_P, Constants.STATIONARY_ANGLE_I, Constants.STATIONARY_ANGLE_D);
		stationaryAnglePID.setIZone(10);
		stationaryAnglePID.setMaxOutput(maxOutput - MIN_POWER_TO_TURN);
		stationaryAnglePID.setMinOutput(-maxOutput + MIN_POWER_TO_TURN);
		
		stationaryAnglePID.setInvertOutput(true);
		
		setInterruptible(true);
    }
    
    /**
     * This function returns the realtime varaible that is used as in input to the PID.
     */
    protected abstract double getProcessVaraible();
    
    protected void execute() {
    	// Uncomment for debugging
    	updatePIDGains();
    	
    	double PIDOutput = stationaryAnglePID.updateAndGetOutput(getProcessVaraible());
    	setLeftRightMotorOutputsPIDTurning(PIDOutput, -PIDOutput);
    	
    	debug();
    }

    protected boolean isFinished() {
        return Math.abs(stationaryAnglePID.getError()) < errorThreshold;
    }

    protected void end() {
    	setLeftRightMotorOutputsPIDTurning(0.0, 0.0);
    	System.out.println("Ending drive train PID turn");
    }

    protected void interrupted() {
    	System.out.println("drive train PID trun interrupted");
    }
    
    public void setLeftRightMotorOutputsPIDTurning(double left, double right){
		if(left < 0) {
			left -= MIN_POWER_TO_TURN;
		} else {
			left += MIN_POWER_TO_TURN;
		}
		if(right < 0) {
			right -= MIN_POWER_TO_TURN;
		} else {
			right += MIN_POWER_TO_TURN;
		}
		SmartDashboard.putNumber("Stationary Angle PID Output", left);
		Robot.drivetrain.setLeftRightMotorOutputs(left, right);
	}
    public double getThreshold(){
    	return this.errorThreshold;
    }
    public void updatePIDGains() {
    	// get new values from smart dash
    	Constants.STATIONARY_ANGLE_P = SmartDashboard.getNumber("PID - Stat Angle P", Constants.STATIONARY_ANGLE_P );
		Constants.STATIONARY_ANGLE_I = SmartDashboard.getNumber("PID - Stat Angle I", Constants.STATIONARY_ANGLE_I );
		Constants.STATIONARY_ANGLE_D = SmartDashboard.getNumber("PID - Stat Angle D", Constants.STATIONARY_ANGLE_D );
		
		stationaryAnglePID.changeGains(Constants.STATIONARY_ANGLE_P, Constants.STATIONARY_ANGLE_I,
				Constants.STATIONARY_ANGLE_D);
	}
    
    public void debug() {
        SmartDashboard.putNumber("PID - Stationary Angle Error", stationaryAnglePID.getError());
        SmartDashboard.putNumber("PID - Stationary Angle Output", stationaryAnglePID.getOutput());
    }
}
