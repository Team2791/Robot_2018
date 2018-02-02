package org.usfirst.frc.team2791.robot.commands.intakeclaw;

import org.usfirst.frc.team2791.robot.subsystems.Manipulator;
import org.usfirst.frc.team2791.robot.util.Constants;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Timer;


public class ShootCube extends Command {
    private Manipulator manipulator;
    private Timer timer;
    public ShootCube() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        manipulator = new Manipulator();
        timer = new Timer();

    }


    /**
     * The initialize method is called just before the first time
     * this Command is run after being started.
     */
    @Override
    protected void initialize() {
        timer.start();
        if (!manipulator.areCubeArmsClosed()) {
            manipulator.setCubeArmsClosed(false);
        }

        manipulator.setLeftRightMotorSpeed(-Constants.INTAKE_SPEED, Constants.INTAKE_SPEED);

    }


    /**
     * The execute method is called repeatedly when this Command is
     * scheduled to run until this Command either finishes or is canceled.
     */
    @Override
    protected void execute() {
        if (timer.get() >= 250000) {
            manipulator.setLeftRightMotorSpeed(-Constants.INTAKE_SPEED, Constants.INTAKE_SPEED);
            if (manipulator.isCubeJammed()) {
                manipulator.setLeftRightMotorSpeed(-Constants.INTAKE_SPEED, 2 * Constants.INTAKE_SPEED);
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
        // TODO: Make this return true when this Command no longer needs to run execute()
        return !manipulator.isCubeInGripper();
    }


    /**
     * Called once when the command ended peacefully; that is it is called once
     * after {@link #isFinished()} returns true. This is where you may want to
     * wrap up loose ends, like shutting off a motor that was being used in the
     * command.
     */
    @Override
    protected void end() {
        manipulator.setLeftRightMotorSpeed(0, 0);
        timer.stop();
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
