package autoModes;

import org.usfirst.frc.team2791.pathing.RunPath;
import org.usfirst.frc.team2791.pathing.ShakerPaths;
import org.usfirst.frc.team2791.pathing.ShakerPaths.FROM_CENTER;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ScoreSwitch extends CommandGroup {
	public ScoreSwitch(boolean isRight) {
//		addSequential(new SetLiftHeight(Constants.AUTON_EXTENDED_SWITCH_HEIGHT));
		if(isRight) {
			addSequential(new RunPath(ShakerPaths.FROM_CENTER.SWITCH_RIGHT, x -> {
				if (x < 0.25) {
					return 0.4;
				} else if (x < 0.75) {
					return 0.7;
				} else {
					return 0.20;
				}
			}), 2.5);
			// replace lambda with
			// Lambda getSpeedFunction([[0, 0.4] [0.25, 0.7], [0.75, 0.2]]);
			addSequential(new RunPath(ShakerPaths.FROM_CENTER.SWITCH_RIGHT, x -> {
				if (x < 0.25) {
					return -0.4;
				} else if (x < 0.75) {
					return -0.7;
				} else {
					return -0.20;
				}
			}), 2.5);
		}
		else {
			addSequential(new RunPath(ShakerPaths.FROM_CENTER.SWITCH_LEFT, x -> {
				if (x < 0.25) {
					return 0.4;
				} else if (x < 0.75) {
					return 0.75;
				} else {
					return 0.20;
				}
			}), 2.6);
			addSequential(new RunPath(ShakerPaths.FROM_CENTER.SWITCH_LEFT, x -> {
				if (x < 0.25) {
					return -0.4;
				} else if (x < 0.75) {
					return -0.75;
				} else {
					return -0.20;
				}
			}), 2.6);
		}
		addSequential(new RunPath(ShakerPaths.straightLength(58), x-> {
			if(x < 0.75) return 0.55;
			else return 0.25;
			}), 1.1);
		addSequential(new RunPath(ShakerPaths.straightLength(58), x-> {
			if(x < 0.75) return -0.55;
			else return -0.25;
			}), 1.1);
		
		if(isRight) {
			addSequential(new RunPath(ShakerPaths.FROM_CENTER.SWITCH_RIGHT, x -> {
				if (x < 0.25) {
					return 0.4;
				} else if (x < 0.75) {
					return 0.7;
				} else {
					return 0.20;
				}
			}), 2.5);
			addSequential(new RunPath(ShakerPaths.FROM_CENTER.SWITCH_RIGHT, x -> {
				if (x < 0.25) {
					return -0.4;
				} else if (x < 0.75) {
					return -0.7;
				} else {
					return -0.20;
				}
			}), 2.5);
		}
		else {
			addSequential(new RunPath(ShakerPaths.FROM_CENTER.SWITCH_LEFT, x -> {
				if (x < 0.25) {
					return 0.4;
				} else if (x < 0.75) {
					return 0.75;
				} else {
					return 0.20;
				}
			}), 2.6);
			addSequential(new RunPath(ShakerPaths.FROM_CENTER.SWITCH_LEFT, x -> {
				if (x < 0.25) {
					return -0.4;
				} else if (x < 0.75) {
					return -0.75;
				} else {
					return -0.20;
				}
			}), 2.6);
		}
		addSequential(new RunPath(ShakerPaths.straightLength(58), x-> {
			if(x < 0.75) return 0.55;
			else return 0.25;
			}), 1.1);
		
		
		// Max S stuff
//		addSequential(new RunPath(GrrPaths.FROM_RIGHT_PORTAL.TEST_SCALE_RIGHT, x -> {
//			return -0.75 * Math.max(x, 0.2);
//		}), 3);
//		
//		addSequential(new RunPath(GrrPaths.FROM_RIGHT.TEST_SCALE_RIGHT_FINISH, x -> {
//			if (x < 0.8) return -0.75;
//			else return -3.25 * Math.max(0.2, 1 - x);
//		}), 7);
		
//		addParallel(new SetManipulatorRetracted(true));
//		addSequential(new SetLiftHeight(Constants.AUTON_SCALE_HEIGHT));
//		
//		addSequential(new ShootCube(-Constants.SMALL_OUTPUT_SPEED));
	}
}
