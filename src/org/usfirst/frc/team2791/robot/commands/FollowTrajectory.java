package org.usfirst.frc.team2791.robot.commands;
import com.ctre.phoenix.motion.MotionProfileStatus;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team2791.robot.Constants;
import org.usfirst.frc.team2791.robot.Robot;
import org.usfirst.frc.team2791.robot.commands.auto.ShakerSrx;
import com.ctre.phoenix.motion.SetValueMotionProfile;
import com.ctre.phoenix.motion.TrajectoryPoint;
import org.usfirst.frc.team2791.robot.util.SrxTrajectory;
import edu.wpi.first.wpilibj.Notifier;
import org.usfirst.frc.team2791.robot.util.SrxMotionProfile;

public class FollowTrajectory extends Command {
    private String trajectoryName = "";
    private SrxTrajectory trajectoryToFollow = null;
    private ShakerSrx leftTalon = Robot.drivetrain.leftDrive;
    private ShakerSrx rightTalon = Robot.drivetrain.rightDrive;
    private int kMinPointsInTalon = 5;


    private MotionProfileStatus rightStatus = new MotionProfileStatus();
    private MotionProfileStatus leftStatus = new MotionProfileStatus();

    private boolean hasPathStarted = false;

    private SetValueMotionProfile setValue = SetValueMotionProfile.Disable;

    public FollowTrajectory(String trajectoryName) {
        requires(Robot.drivetrain);
        this.trajectoryName = trajectoryName;
    }

    public FollowTrajectory(SrxTrajectory trajectoryToFollow) {
        requires(Robot.drivetrain);
        this.trajectoryToFollow = trajectoryToFollow;
    }
    @Override
    protected boolean isFinished() {
        return false;
    }

    private class BufferLoader implements java.lang.Runnable {
        private String trajectoryName;
        private int leftLastPointSent = 0;
        private int rightLastPointSent = 0;
        private boolean hasPathStarted;
        private SrxTrajectory trajectoryToFollow;

        public BufferLoader(String trajectoryName) {
            this.trajectoryName = trajectoryName;
            this.leftLastPointSent = 0;
            this.rightLastPointSent = 0;
        }


        public void run() {
            leftTalon.processMotionProfileBuffer();
            rightTalon.processMotionProfileBuffer();
            leftLastPointSent = manageBuffer(leftTalon, trajectoryToFollow.leftProfile, 2, leftLastPointSent);
            rightLastPointSent = manageBuffer(rightTalon, trajectoryToFollow.rightProfile, 2, rightLastPointSent);
        }

        private int manageBuffer(ShakerSrx talon, SrxMotionProfile prof, int pidfslot, int lastPointSent) {
            if (lastPointSent >= prof.numPoints) {
                return lastPointSent;
            }
            if(!talon.isMotionProfileTopLevelBufferFull() && lastPointSent < prof.numPoints){
                TrajectoryPoint point = new TrajectoryPoint();
                point.position = prof.points[lastPointSent][0];
                point.velocity = prof.points[lastPointSent][1];
                point.profileSlotSelect0 = pidfslot;
                point.profileSlotSelect1 = pidfslot;
                point.zeroPos = false;
                if(lastPointSent == 0) {
                    point.zeroPos = true;
                }
                point.isLastPoint = false;
                if((lastPointSent +1) == prof.numPoints) {
                    point.isLastPoint = true;
                }
                talon.pushMotionProfileTrajectory(point);
                lastPointSent++;
            }
            return lastPointSent;
        }
private TrajectoryPoint.TrajectoryDuration getTrajectoryDuration(int ms){
   TrajectoryPoint.TrajectoryDuration retval = TrajectoryPoint.TrajectoryDuration.Trajectory_Duration_0ms;

    retval= retval.valueOf(ms);
    return retval;
}
        private Notifier bufferNotifier;

        // Called just before this Command runs the first time
        protected void initialize() {
            this.hasPathStarted = false;
            setUpTalon(rightTalon);
            setUpTalon(leftTalon);

            setValue = SetValueMotionProfile.Disable;

            leftTalon.set(ControlMode.MotionProfile, setValue.value);
            rightTalon.set(ControlMode.MotionProfile, setValue.value);

            bufferNotifier = new Notifier(new BufferLoader());
            bufferNotifier.startPeriodic(.005);
        }

        // Called repeatedly when this Command is scheduled to run
        protected void execute() {

            rightTalon.getMotionProfileStatus(rightStatus);
            leftTalon.getMotionProfileStatus(leftStatus);

            if (rightStatus.isUnderrun || leftStatus.isUnderrun) {
                // if either MP has underrun, stop both
                System.out.println("Motion profile has underrun!");
                setValue = SetValueMotionProfile.Disable;
            } else if (rightStatus.btmBufferCnt > kMinPointsInTalon && leftStatus.btmBufferCnt > kMinPointsInTalon) {
                // if we have enough points in the talon, go.
                setValue = SetValueMotionProfile.Enable;
                hasPathStarted = true;
            } else if (rightStatus.activePointValid && rightStatus.isLast && leftStatus.activePointValid
                    && leftStatus.isLast) {
                // if both profiles are at their last points, hold the last point
                setValue = SetValueMotionProfile.Hold;
                // System.out.println("HOLDING LAST POINT!!!!!");
            }

            leftTalon.set(ControlMode.MotionProfile, setValue.value);
            rightTalon.set(ControlMode.MotionProfile, setValue.value);
        }

        // Make this return true when this Command no longer needs to run execute()
        protected boolean isFinished() {
            if (!hasPathStarted) {
                return false;
            }
            boolean leftComplete = leftStatus.activePointValid && leftStatus.isLast;
            boolean rightComplete = rightStatus.activePointValid && rightStatus.isLast;
            // System.out.println("Complete: " + leftComplete + "," + rightComplete);
            boolean trajectoryComplete = leftComplete && rightComplete;
            if (trajectoryComplete) {
                System.out.println("Finished Trajectory");
            }
            boolean isFinished = false;
            return trajectoryComplete || isFinished;
        }

        // Called once after isFinished returns true
        protected void end() {
            cleanUp();
        }

        // Called when another command which requires one or more of the same
        // subsystems is scheduled to run
        protected void interrupted() {
            cleanUp();
        }

        public void cleanUp() {
            resetTalon(rightTalon, ControlMode.PercentOutput, 0);
            resetTalon(leftTalon, ControlMode.PercentOutput, 0);
            bufferNotifier.stop();
        }

        // set up the talon for motion profile control
        public void setUpTalon(TalonSRX talon) {
            talon.clearMotionProfileTrajectories();
            talon.changeMotionControlFramePeriod(5);
        }

        // set the to the desired controlMode
        // used at the end of the motion profile
        public void resetTalon(TalonSRX talon, ControlMode controlMode, double setValue) {
            // System.out.println("Clearing MP Trajectories");
            talon.clearMotionProfileTrajectories();
            talon.set(ControlMode.MotionProfile, SetValueMotionProfile.Disable.value);
            talon.set(controlMode, setValue);
        }


    }
}