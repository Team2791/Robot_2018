package org.usfirst.frc.team2791.robot.commands.lift;

import org.usfirst.frc.team2791.robot.Robot;
import org.usfirst.frc.team2791.robot.subsystems.ShakerLift;
import org.usfirst.frc.team2791.robot.util.DelayedBoolean;

import edu.wpi.first.wpilibj.command.Command;

public class SetLiftHeightMagicMotion extends Command {
    // Height measered inches
    private double targetHeight;
    private ShakerLift lift;
    private DelayedBoolean finishDelayedBoolean;
    
    
    public SetLiftHeightMagicMotion(double height) {
        super("GoToHeight");
        requires(Robot.lift);
        this.targetHeight = height;
        this.lift = Robot.lift;
        finishDelayedBoolean = new DelayedBoolean(0.3);
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
    	double diff = Robot.lift.getHeight() - targetHeight;
    	return finishDelayedBoolean.update(Math.abs(diff) < 0.6);
//    	if(targetHeight <= 0.01) {
//    		return abs(diff) < 0.1;
//    	} else {
//    		return Math.abs(diff) < 0.25;
//    	}
   }

    @Override
    protected void end () {
    	System.out.println("Lift magic motion done!");
    	Robot.lift.setPower(0);
    	Robot.lift.setBreak(true);
    }

    @Override
    protected void interrupted() {
        end();
    }
}
