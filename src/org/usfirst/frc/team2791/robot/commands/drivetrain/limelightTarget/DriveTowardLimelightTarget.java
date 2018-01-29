package org.usfirst.frc.team2791.robot.commands.drivetrain.limelightTarget;


//import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import edu.wpi.first.wpilibj.Encoder;
import org.usfirst.frc.team2791.robot.commands.drivetrain.*;
import org.usfirst.frc.team2791.robot.util.Constants;
import org.usfirst.frc.team2791.robot.util.Limelight;
import org.usfirst.frc.team2791.robot.subsystems.ShakerDrivetrain;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Timer;


public class DriveTowardLimelightTarget extends Command {
    private Limelight limelight;
    private double horizontalOffset, verticalOffset, targetArea, targetSkew, targetLatency;
    private boolean targetValid;
    private ShakerDrivetrain drivetrain;
    private Encoder leftDrive, rightDrive;
    private double speedMultiplier = Constants.SPEED_MULTIPLIER;
    private double horizontalOffsetFraction;
    private Timer timer;
    private double distanceTravelled;


    public DriveTowardLimelightTarget() {
        setInterruptible(true);
        limelight = new Limelight();
        targetValid = limelight.targetValid();
        if(!targetValid){
            System.out.println("Target not found, exiting Drive toward target");
            end();
        }
        else{
            horizontalOffset = limelight.getHorizontalOffset();
            verticalOffset = limelight.getVerticalOffset();
            targetArea = limelight.getTargetArea();
            targetSkew = limelight.getTargetSkew();
            targetLatency = limelight.getLatency();
            drivetrain = new ShakerDrivetrain();
            leftDrive = drivetrain.leftDriveEncoder;
            rightDrive = drivetrain.rightDriveEncoder;
            horizontalOffsetFraction = (horizontalOffset / 27); // <-------- 27 is largest value that limelight can measure
        }
        timer.start();

    }

    public void driveAndTurn(){
        // Checks if Robot is lined up with the target
        // If the angle is between -TARGET_MARGIN_OF_ERROR and TARGET_MARGIN_OF_ERROR ---> Drive Forward
        if(horizontalOffset >= Constants.TARGET_MARGIN_OF_ERROR * -1 && horizontalOffset <= Constants.TARGET_MARGIN_OF_ERROR){
            drivetrain.setLeftRightMotorOutputs(1 * speedMultiplier, 1 * speedMultiplier);
        }
        // If the angle is less than -TARGET_MARGIN_OF_ERROR ---> turn Left by spinning left wheels backwards (-1) and right wheels forward (1)
        else if(horizontalOffset <= -1 * Constants.TARGET_MARGIN_OF_ERROR){
            drivetrain.setLeftRightMotorOutputs(1 * speedMultiplier, horizontalOffsetFraction * speedMultiplier);
        }
        // If the angle is greater than TARGET_MARGIN_OF_ERROR ---> turn Right by spinning right wheels backwards (-1) and left wheels forward (1)
        else if(horizontalOffset >= Constants.TARGET_MARGIN_OF_ERROR){
            drivetrain.setLeftRightMotorOutputs(horizontalOffsetFraction * speedMultiplier, 1 * speedMultiplier);
        }
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
    }

    @Override
    protected boolean isFinished(){
        return false;
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



    /**
     * Called once when the command ended peacefully; that is it is called once
     * after {@link #isFinished()} returns true. This is where you may want to
     * wrap up loose ends, like shutting off a motor that was being used in the
     * command.
     */
    @Override
    protected void end() {

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

