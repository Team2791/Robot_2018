package org.usfirst.frc.team2791.robot.commands.auto;

import edu.wpi.first.wpilibj.command.Command;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.modifiers.TankModifier;
import org.usfirst.frc.team2791.robot.Constants;
import org.usfirst.frc.team2791.robot.Robot;
import org.usfirst.frc.team2791.robot.subsystems.ShakerDrivetrain;


public class RunTrajectory extends Command {
    public Trajectory trajectory;
    public int count;
    private Trajectory left;
    private Trajectory right;
    private ShakerDrivetrain drivetrain;
    private TankModifier modifier;

    public RunTrajectory(Trajectory trajectory, int count) {
        this.trajectory = trajectory;
        this.drivetrain = Robot.drivetrain;
        this.modifier = new TankModifier(trajectory);
        modifier.modify(Constants.WHEELBASE_WIDTH);
        this.left = modifier.getLeftTrajectory();
        this.right = modifier.getLeftTrajectory();
        this.count = count;
    }


    /**
     * The initialize method is called just before the first time
     * this Command is run after being started.
     */
    @Override
    protected void initialize() {
        drivetrain.clearTrajectories();
        drivetrain.setTrajectory(left, count, right, count);
        drivetrain.enableTrajectory();
    }


    /**
     * The execute method is called repeatedly when this Command is
     * scheduled to run until this Command either finishes or is canceled.
     */
    @Override
    protected void execute() {


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
        boolean isLeftCompleted = drivetrain.getPercentTrajectoryCompleted(count)[0] == 1.0;
        boolean isRightCompleted = drivetrain.getPercentTrajectoryCompleted(count)[1] == 1.0;
        return isLeftCompleted && isRightCompleted;
    }


    /**
     * Called once when the command ended peacefully; that is it is called once
     * after {@link #isFinished()} returns true. This is where you may want to
     * wrap up loose ends, like shutting off a motor that was being used in the
     * command.
     */
    @Override
    protected void end() {
        drivetrain.clearTrajectories();
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
