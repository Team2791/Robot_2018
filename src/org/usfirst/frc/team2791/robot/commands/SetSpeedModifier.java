package org.usfirst.frc.team2791.robot.commands;

import org.usfirst.frc.team2791.robot.subsystems.ShakerLift;

import edu.wpi.first.wpilibj.command.Command;

public class SetSpeedModifier extends Command {
	double speed;
	
	public SetSpeedModifier(double speed) {
		this.speed = speed;
	}
	
	@Override
    protected void execute() {
		ShakerLift.speedModifier = speed;
	}
	
	
	@Override
	protected boolean isFinished() {
		return true;
	}

}
