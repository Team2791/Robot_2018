package org.usfirst.frc.team2791.robot.commands.drivetrain.limelightTarget;


import org.usfirst.frc.team2791.robot.Robot;
//import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import org.usfirst.frc.team2791.robot.commands.drivetrain.*;
import org.usfirst.frc.team2791.robot.util.Constants;
import org.usfirst.frc.team2791.robot.util.Limelight;
import org.usfirst.frc.team2791.robot.subsystems.ShakerDrivetrain;
import edu.wpi.first.wpilibj.smartdashboard.*;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Timer;


public class TurnTowardLimelightTarget extends Command {

    private Limelight limelight;
    private double horizontalOffset, verticalOffset, targetArea, targetSkew, targetLatency;
    private boolean targetValid;
    private ShakerDrivetrain drivetrain;
    private double speedMultiplier = Constants.SPEED_MULTIPLIER;

    public TurnTowardLimelightTarget() {
        setInterruptible(true);

        	
        }

    


    /**
     * The initialize method is called just before the first time
     * this Command is run after being started.
     */
    @Override
    protected void initialize() {

    }


    /**
     * The execute method is called repeatedly when this Command is
     * scheduled to run until this Command either finishes or is canceled.
     */

    @Override
    protected void execute() {
    	targetValid = Robot.limelight.targetValid();
    	SmartDashboard.putString("LimeLightDebug", "Limelight Pressed");
        SmartDashboard.putString("HorizOffset", Double.toString(horizontalOffset));
        SmartDashboard.putBoolean("ValidTarget", Robot.limelight.targetValid());
        if(targetValid){
        	SmartDashboard.putString("LimeLightDebug", "Lime should be turning");
        	//System.out.println("Lime should be turning");
        	
            horizontalOffset = Robot.limelight.getHorizontalOffset();
            verticalOffset = Robot.limelight.getVerticalOffset();
            targetArea = Robot.limelight.getTargetArea();
            targetSkew = Robot.limelight.getTargetSkew();
            targetLatency = Robot.limelight.getLatency();
            
            // Checks if Robot is lined up with the target
            // If the angle is less than -TARGET_MARGIN_OF_ERROR ---> turn Left by spinning left wheels backwards (-1) and right wheels forward (1)
            if(horizontalOffset <= -1 * Constants.TARGET_MARGIN_OF_ERROR){
                Robot.drivetrain.setLeftRightMotorOutputs(-1 * speedMultiplier, 1 * speedMultiplier);
            }
            // If the angle is greater than TARGET_MARGIN_OF_ERROR ---> turn Right by spinning right wheels backwards (-1) and left wheels forward (1)
            else if(horizontalOffset >= Constants.TARGET_MARGIN_OF_ERROR){
                Robot.drivetrain.setLeftRightMotorOutputs(1 * speedMultiplier, -1 * speedMultiplier);
        }
        else{
        	SmartDashboard.putString("LimeLightDebug", "Target not found, exiting Drive toward target");
            //System.out.println("Target not found, exiting Drive toward target");
        }
    	
        
    }
    }


    /**
     * <p>
     * Returns whether this command is finished. If it is, then the command will be removed and
     * {@link #end()} will be called.
     * </p><p>
     * It may be useful for a team to reference the {@link #isTimedOut()}
     * method for time-sensitive commands.
     * </p><p>
     * Returning false will result in the command never ending automatically. It may still be
     * cancelled manually or interrupted by another command. Returning true will result in the
     * command executing once and finishing immediately. It is recommended to use
     * {@link edu.wpi.first.wpilibj.command.InstantCommand} (added in 2017) for this.
     * </p>
     *
     * @return whether this command is finished.
     * @see Command#isTimedOut() isTimedOut()
     */
    @Override
    protected boolean isFinished() {

        if(horizontalOffset >= -1 * Constants.TARGET_MARGIN_OF_ERROR && horizontalOffset <= 1 * Constants.TARGET_MARGIN_OF_ERROR){
            return true;
        }
        else {
            return false;
        }
    }


    /**
     * Called once when the command ended peacefully; that is it is called once
     * after {@link #isFinished()} returns true. This is where you may want to
     * wrap up loose ends, like shutting off a motor that was being used in the
     * command.
     */
    @Override
    protected void end() {
    	SmartDashboard.putString("LimeLightDebug", "Limelight Released");
    }


    /**
     * <p>
     * Called when the command ends because somebody called {@link #cancel()} or
     * another command shared the same requirements as this one, and booted it out. For example,
     * it is called when another command which requires one or more of the same
     * subsystems is scheduled to run.
     * </p><p>
     * This is where you may want to wrap up loose ends, like shutting off a motor that was being
     * used in the command.
     * </p><p>
     * Generally, it is useful to simply call the {@link #end()} method within this
     * method, as done here.
     * </p>
     */
    @Override
    protected void interrupted() {
        super.interrupted();
    }
}
