package org.usfirst.frc.team2791.robot;

import org.usfirst.frc.team2791.robot.commands.Climb;
import org.usfirst.frc.team2791.robot.commands.HoldClimb;
import org.usfirst.frc.team2791.robot.commands.SetSpeedModifier;
import org.usfirst.frc.team2791.robot.commands.drivetrain.SwitchDrivetrainShifterMode;
import org.usfirst.frc.team2791.robot.commands.drivetrain.Creep;
import org.usfirst.frc.team2791.robot.commands.drivetrain.SetDrivetrainShifterMode;
import org.usfirst.frc.team2791.robot.commands.lift.RunLiftWithJoystick;
import org.usfirst.frc.team2791.robot.commands.lift.SetLiftHeightBangBang;
import org.usfirst.frc.team2791.robot.commands.lift.SetLiftHeightMagicMotion;
import org.usfirst.frc.team2791.robot.commands.lift.SetLiftHeightMagicMotionTopOrBottom;
import org.usfirst.frc.team2791.robot.commands.manipulator.IntakeCube;
import org.usfirst.frc.team2791.robot.commands.manipulator.RunManipulatorWithJoystick;
import org.usfirst.frc.team2791.robot.commands.manipulator.SetManipulatorRetracted;
import org.usfirst.frc.team2791.robot.commands.manipulator.ShootCube;
import org.usfirst.frc.team2791.robot.commands.manipulator.ToggleManipulator;
import org.usfirst.frc.team2791.robot.commands.ramps.DropRamps;
import org.usfirst.frc.team2791.robot.commands.ramps.LOWERRamps;
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

	public Button driverA, driverB, driverX, driverY, driverLB, driverRB, driverBack, driverStart, driverLS, driverRS;
	public Button operatorA, operatorB, operatorX, operatorY, operatorLB, operatorRB, operatorBack, operatorStart,
			operatorLS, operatorRS;

	public Button driverDpadUp, driverDpadUpRight, driverDpadRight, driverDpadDownRight, driverDpadDown,
			driverDpadDownLeft, driverDpadLeft, driverDpadUpLeft;
	public Button operatorDpadUp, operatorDpadUpRight, operatorDpadRight, operatorDpadDownRight,
			operatorDpadDown, operatorDpadDownLeft, operatorDpadLeft, operatorDpadUpLeft;

	protected Button operatorLeftJoystickUsed, operatorRightJoystickUsed;

	protected Button operatorRAnalogTrigger, operatorLAnalogTrigger;

	protected Button climbButton;
	
	// these can be double buttons later
	protected Button dropRampsButton, raiseRampsDoubleButton, raiseRampsLeftButton, raiseRampsRightButton,
		lowerRampsLeftButton, lowerRampsRightButton, lowerRampsDoubleButton;
	
	public static Button driverSlowButton;


	public static ShakerGamePad driver = new ShakerGamePad(0);
	public static ShakerGamePad operator = new ShakerGamePad(1);

	public OI() {
		initCustomStuff();
		initButtons();
		initDpad();
		System.out.println(Constants.msg);

		/********************************** Driver Button Assignments ****************************************/
		driverA.whileHeld(new ShootCube(0.6)); // was 0.45
		driverA.whileHeld(new Creep(-0.32));
		driverB.whenPressed(new ShootCube(Constants.LARGE_OUTPUT_SPEED)); // is 1
		driverY.whenPressed(new DropRamps());
		// THIS IS HACKY BUT YOLO!!
		driverSlowButton = driverX;
		


//		raiseRampsLeftButton = driverStart;
//		raiseRampsRightButton = driverBack;
//		raiseRampsDoubleButton = new DoubleButton(raiseRampsLeftButton, raiseRampsRightButton, "OR");
//		raiseRampsDoubleButton.whileHeld(new RaiseRamps(raiseRampsLeftButton, raiseRampsRightButton));

		driverLB.whileHeld(new Creep(-0.22));
		driverRB.whileHeld(new Creep(0.22));
		
		// this is not used in teleop. It's just nice to have for resetting the robot.
		
//		driverDpadUp.whenPressed(new SetDrivetrainShifterMode(true));
		driverDpadUp.whenPressed(new SwitchDrivetrainShifterMode());

//		driverDpadUp.whenPressed(new SetRampDeploy(false));
		
//		lowerRampsRightButton = driverDpadRight;
//		lowerRampsLeftButton = driverDpadLeft;
//		lowerRampsDoubleButton = new DoubleButton(driverDpadLeft, driverDpadRight, "OR");
//		lowerRampsDoubleButton.whileHeld(new LOWERRamps(driverDpadLeft, driverDpadRight));

		climbButton = driverStart;
		climbButton.whileHeld(new HoldClimb());
		
		/********************************** Operator Button Assignments ****************************************/

//		operatorA.whenPressed(new SetLiftHeightBangBang(0)); // go to bottom //Same as X and dPadUp
		operatorA.whenPressed(new SetLiftHeightMagicMotionTopOrBottom(false));
		operatorA.whenPressed(new SetManipulatorRetracted(false));
		operatorB.whenPressed(new SetLiftHeightMagicMotion(5.25)); //middle of power cube pile //Was 12 Is now 7.25
		operatorX.whenPressed(new SetLiftHeightMagicMotion(10)); //Top of power cube pile OR Portal//Was 12.5 now 12 //I wish it was SetLiftHeightandRetract but it cancels with the intake
		operatorY.whenPressed(new SetLiftHeightMagicMotion(13.5)); //Set height to scoring switch
		
		operatorDpadDown.whenPressed(new SetLiftHeightMagicMotion(28.0)); //Set height to scoring lowest scale
		operatorDpadRight.whenPressed(new SetLiftHeightMagicMotion(32.0)); //Set height to scoring even scale
		operatorDpadUp.whenPressed(new SetManipulatorRetracted(true));
		operatorDpadUp.whenPressed(new SetLiftHeightBangBang(38.75)); //Set height to scoring highest scale //Same as A and x :(
//		operatorDpadUp.whenPressed(new SetLiftHeightMagicMotionTopOrBottom(true));
		
		//operatorStart.whenPressed(new ToggleManipulator());
		operatorBack.whenPressed(new ToggleManipulator());

		operatorLeftJoystickUsed.whenPressed(new RunLiftWithJoystick(operatorLeftJoystickUsed));
		operatorRightJoystickUsed.whenPressed(new RunManipulatorWithJoystick());

//		operatorRAnalogTrigger.whenPressed(new IntakeAndHoldCube());
		operatorRAnalogTrigger.whileHeld(new IntakeCube());
		operatorLAnalogTrigger.whenPressed(new ShootCube(Constants.LARGE_OUTPUT_SPEED));
		operatorLB.whenPressed(new ShootCube(Constants.SMALL_OUTPUT_SPEED));
		operatorRB.whenPressed(new ShootCube(0));
		operatorStart.whenPressed(new SetSpeedModifier(0.5));
		operatorStart.whenReleased(new SetSpeedModifier(1));
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
