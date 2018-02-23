package org.usfirst.frc.team2791.robot;

import org.usfirst.frc.team2791.robot.commands.drivetrain.Creep;
import org.usfirst.frc.team2791.robot.commands.drivetrain.SetDrivetrainShifterMode;
import org.usfirst.frc.team2791.robot.commands.drivetrain.limelightTarget.DriveTowardLimelightTargetStopWithDistance;
import org.usfirst.frc.team2791.robot.commands.lift.GoToHeight;
import org.usfirst.frc.team2791.robot.commands.lift.RunLiftWithJoystick;
import org.usfirst.frc.team2791.robot.commands.manipulator.IntakeAndHoldCube;
import org.usfirst.frc.team2791.robot.commands.manipulator.RunManipulatorWithJoystick;
import org.usfirst.frc.team2791.robot.commands.manipulator.SetManipulatorRetracted;
import org.usfirst.frc.team2791.robot.commands.manipulator.ShootCube;
import org.usfirst.frc.team2791.robot.commands.ramps.DropRamps;
import org.usfirst.frc.team2791.robot.commands.ramps.RaiseRamps;
import org.usfirst.frc.team2791.robot.commands.ramps.SetRampDeploy;
import org.usfirst.frc.team2791.robot.shakerJoystick.ShakerGamePad;
import org.usfirst.frc.team2791.robot.util.DoubleButton;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

	protected Button driverA, driverB, driverX, driverY, driverLB, driverRB, driverBack, driverStart, driverLS, driverRS;
	protected Button operatorA, operatorB, operatorX, operatorY, operatorLB, operatorRB, operatorBack, operatorStart,
			operatorLS, operatorRS;

	protected Button driverDpadUp, driverDpadUpRight, driverDpadRight, driverDpadDownRight, driverDpadDown,
			driverDpadDownLeft, driverDpadLeft, driverDpadUpLeft;
	protected Button operatorDpadUp, operatorDpadUpRight, operatorDpadRight, operatorDpadDownRight,
			operatorDpadDown, operatorDpadDownLeft, operatorDpadLeft, operatorDpadUpLeft;

	protected Button operatorLeftJoystickUsed, operatorRightJoystickUsed;

	protected Button operatorRAnalogTrigger, operatorLAnalogTrigger;
	
	// these can be double buttons later
	protected Button dropRampsButton, raiseRampsDoubleButton, raiseRampsLeftButton, raiseRampsRightButton;


	public static ShakerGamePad driver = new ShakerGamePad(0);
	public static ShakerGamePad operator = new ShakerGamePad(1);

	public OI() {
		initCustomStuff();
		initButtons();
		initDpad();

		/********************************** Driver Button Assignments ****************************************/
		driverA.whenPressed(new SetDrivetrainShifterMode(true)); // use A to shift back into drive
		driverB.whenPressed(new SetDrivetrainShifterMode(false));
		driverX.whenPressed(new SetRampDeploy(false));
		driverY.whenPressed(new DropRamps());
		
		
//		driverStart.whileHeld(new RunDrivetrainOnlyOneSide(true, Constants.RAISE_RAMPS_SPEED)); // true runs the left side
//		driverBack.whileHeld(new RunDrivetrainOnlyOneSide(false, Constants.RAISE_RAMPS_SPEED)); // flase runs the right side
		raiseRampsLeftButton = driverStart;
		raiseRampsRightButton = driverBack;
		raiseRampsDoubleButton = new DoubleButton(raiseRampsLeftButton, raiseRampsRightButton, "OR");
		raiseRampsDoubleButton.whileHeld(new RaiseRamps(raiseRampsLeftButton, raiseRampsRightButton));
		
		driverLB.whileHeld(new Creep(-0.22));
		driverRB.whileHeld(new Creep(0.22));

		
		/********************************** Operator Button Assignments ****************************************/

		operatorA.whenPressed(new GoToHeight(0)); // go to bottom
		operatorB.whenPressed(new GoToHeight(3));// go to the 2nd level of the power cube pile height
		operatorX.whenPressed(new GoToHeight(6));// go to the 3rd level of the power cube pile height
		operatorY.whenPressed(new GoToHeight(12)); // go to score on switch height
		
		operatorDpadDown.whenPressed(new GoToHeight(25)); // go to scoring on the switch when it's in our favor
		operatorDpadRight.whenPressed(new GoToHeight(30)); // go to scoring on the switch when it's balanced
		operatorDpadUp.whenPressed(new GoToHeight(36.5)); // go to max height
		
		operatorStart.whenPressed(new SetManipulatorRetracted(true));
		operatorBack.whenPressed(new SetManipulatorRetracted(false));

		
		operatorLeftJoystickUsed.whenPressed(new RunLiftWithJoystick(operatorLeftJoystickUsed));
		operatorRightJoystickUsed.whenPressed(new RunManipulatorWithJoystick());

		operatorRAnalogTrigger.whenPressed(new IntakeAndHoldCube());
		operatorLAnalogTrigger.whenPressed(new ShootCube(Constants.LARGE_OUTPUT_SPEED));
		operatorLB.whenPressed(new ShootCube(Constants.SMALL_OUTPUT_SPEED));


		
		
	
		// Commenting out until lime light is finished.
//		.whileHeld(new DriveTowardLimelightTargetStopWithDistance(Constants.SPEED_MULTIPLIER, 1));
//		.whileHeld(new TurnTowardLimelightTarget());
		//.whileHeld(new DriveTowardLimelightTargetTime(2));
	}


	private void initCustomStuff() {
		operatorLeftJoystickUsed = new Button() {
			@Override
			public boolean get() {
				return Math.abs(operator.getAxisLeftY()) > 0.08;
			}
		};

		operatorRightJoystickUsed = new Button() {
			@Override
			public boolean get() {
				return Math.abs(operator.getAxisRightY()) > 0.08;
			}
		};

		operatorRAnalogTrigger = new Button() {
			@Override
			public boolean get() {
				return operator.getAxisRT() > 0.8;
			}
		};
		operatorLAnalogTrigger= new Button(){
			@Override
			public boolean get(){
				return operator.getAxisLT() > 0.8;
			}
		};

	}




	private void initButtons() {
		try {
			driverA = new JoystickButton(driver, 1);
			driverB = new JoystickButton(driver, 2);
			driverX = new JoystickButton(driver, 3);
			driverY = new JoystickButton(driver, 4);
			driverLB = new JoystickButton(driver, 5);
			driverRB = new JoystickButton(driver, 6);
			driverBack = new JoystickButton(driver, 7);
			driverStart = new JoystickButton(driver, 8);
			driverLS = new JoystickButton(driver, 9);
			driverRS = new JoystickButton(driver, 10);

			operatorA = new JoystickButton(operator, 1);
			operatorB = new JoystickButton(operator, 2);
			operatorX = new JoystickButton(operator, 3);
			operatorY = new JoystickButton(operator, 4);
			operatorLB = new JoystickButton(operator, 5);
			operatorRB = new JoystickButton(operator, 6);
			operatorBack = new JoystickButton(operator, 7);
			operatorStart = new JoystickButton(operator, 8);
			operatorLS = new JoystickButton(operator, 9);
			operatorRS = new JoystickButton(operator, 10);
		}
		catch (Exception error){  //Display error if it happens ----> using Exception is very general and a bad idea.
			System.out.println("Error Initiating buttons");
			error.printStackTrace();
		}
	}

	/**
	 * Initializes the Dpad
	 */
	private void initDpad(){
		driverDpadUp = new Button(){
			@Override
			public boolean get(){
				return driver.getDpadUp();
			}
		};

		driverDpadUpRight = new Button(){
			@Override
			public boolean get(){
				return driver.getDpadUpRight();
			}
		};

		driverDpadRight = new Button(){
			@Override
			public boolean get(){
				return driver.getDpadRight();
			}
		};

		driverDpadDownRight = new Button(){
			@Override
			public boolean get(){
				return driver.getDpadDownRight();
			}
		};

		driverDpadDown = new Button(){
			@Override
			public boolean get(){
				return driver.getDpadDown();
			}
		};

		driverDpadDownLeft = new Button(){
			@Override
			public boolean get(){
				return driver.getDpadDownLeft();
			}
		};

		driverDpadLeft = new Button(){
			@Override
			public boolean get(){
				return driver.getDpadLeft();
			}
		};

		driverDpadUpLeft = new Button(){
			@Override
			public boolean get(){
				return driver.getDpadUpLeft();
			}
		};

		operatorDpadUp = new Button(){
			@Override
			public boolean get(){
				return operator.getDpadUp();
			}
		};

		operatorDpadUpRight = new Button(){
			@Override
			public boolean get(){
				return operator.getDpadUpRight();
			}
		};

		operatorDpadRight = new Button(){
			@Override
			public boolean get(){
				return operator.getDpadRight();
			}
		};
		operatorDpadDownRight = new Button(){
			@Override
			public boolean get(){
				return operator.getDpadDownRight();
			}
		};
		operatorDpadDown = new Button(){
			@Override
			public boolean get(){
				return operator.getDpadDown();
			}
		};
		operatorDpadDownLeft = new Button(){
			@Override
			public boolean get(){
				return operator.getDpadDownLeft();
			}
		};
		operatorDpadLeft = new Button(){
			@Override
			public boolean get(){
				return operator.getDpadLeft();
			}
		};
		operatorDpadUpLeft = new Button(){
			@Override
			public boolean get(){
				return operator.getDpadUpLeft();
			}
		};
	}


}
