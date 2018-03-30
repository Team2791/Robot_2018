package org.usfirst.frc.team2791.robot.commands;
import com.ctre.phoenix.motion.MotionProfileStatus;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team2791.robot.Constants;
import org.usfirst.frc.team2791.robot.Robot;
import org.usfirst.frc.team2791.robot.RobotMap;
import org.usfirst.frc.team2791.robot.commands.auto.ShakerSrx;
import org.usfirst.frc.team2791.robot.commands.lift.StopLift;
import com.ctre.phoenix.motion.SetValueMotionProfile;
import com.ctre.phoenix.motion.TrajectoryPoint;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.BaseMotorController;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import org.usfirst.frc.team2791.robot.subsystems.ShakerLift;
import org.usfirst.frc.team2791.robot.subsystems.ShakerDrivetrain;
import org.usfirst.frc.team2791.robot.util.SrxTrajectory;
import edu.wpi.first.wpilibj.Notifier;
import org.usfirst.frc.team2791.robot.util.SrxMotionProfile;

public class FollowTrajectory extends Command {
    private ShakerSrx leftTalon = Robot.drivetrain.leftDrive;
    private ShakerSrx rightTalon = Robot.drivetrain.rightDrive;
    private String tragectoryName = "";
    private int kMinPointsInTalon = 5;

    private SrxTrajectory trajectorytoFollow = null;

    private MotionProfileStatus rightStatus = new MotionProfileStatus();
    private MotionProfileStatus leftStatus = new MotionProfileStatus();

    private boolean hasPathStarted = false;

    private SetValueMotionProfile setValue = SetValueMotionProfile.Disable;

    private class BufferLoader implements java.lang.Runnable {
        private int leftLastPointSent = 0;
        private int rightLastPointSent = 0;

        public BufferLoader() {
            this.leftLastPointSent = 0;
            this.rightLastPointSent = 0;
        }


        public void run() {
            leftTalon.processMotionProfileBuffer();
            rightTalon.processMotionProfileBuffer();
            leftLastPointSent = manageBuffer(leftTalon, trajectorytoFollow.leftProfile, Robot.drivetrain.getGyroRate(), leftLastPointSent);
            rightLastPointSent = manageBuffer(rightTalon, trajectorytoFollow.rightProfile, Robot.drivetrain.getGyroRate(), rightLastPointSent);
        }
        private int manageBuffer()
    }
    @Override
    protected boolean isFinished() {
        return false;
    }
}
