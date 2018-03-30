package org.usfirst.frc.team2791.robot.commands.lift;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team2791.robot.Constants;
import org.usfirst.frc.team2791.robot.Robot;
import org.usfirst.frc.team2791.robot.subsystems.ShakerLift;

import static java.lang.Math.abs;

public class SetLiftHeightMagicMotion extends Command {
    // Height measered inches
    private double targetHeight;
    private ShakerLift lift;
    public SetLiftHeightMagicMotion(double height) {
        super("GoToHeight");
        requires(Robot.lift);
        this.targetHeight = height;
        this.lift = Robot.lift;

    }


    /**
     * The initialize method is called just before the first time
     * this Command is run after being started.
     */
    @Override
    protected void initialize() {
        lift.setBreak(false);
        lift.setTargetMagicMotion(targetHeight);
    }

    @Override
    protected void execute() {
    }

    @Override
    public boolean isFinished() {
    	double diff = Robot.lift.getHeight() - targetHeight; // TODO make this a method
    	if(targetHeight <= 0.01) {
    		return abs(diff) < 0.1;
    	} else {
    		return Math.abs(diff) < Constants.CLOSE_DISTANCE;
    	}
   }
    @Override
    protected void end () {
    	Robot.lift.setPower(0);
    	Robot.lift.setBreak(true);
    }
    @Override
    protected void interrupted() {
        super.interrupted();
    }
}
