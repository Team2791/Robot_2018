package org.usfirst.frc.team2791.robot;

import org.usfirst.frc.team2791.robot.commands.drivetrain.limelightTarget.*;
import org.usfirst.frc.team2791.robot.commands.drivetrain.RunDrivetrainOnlyOneSide;
import org.usfirst.frc.team2791.robot.shakerJoystick.ShakerGamePad;



import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import org.usfirst.frc.team2791.robot.util.Constants;

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

	public static ShakerGamePad driver = new ShakerGamePad(0);
	public static ShakerGamePad operator = new ShakerGamePad(1);
	//public static DriveTowardLimelightTarget driveTowardLimelightTarget = new DriveTowardLimelightTarget();
	public OI() {
		initButtons();

		/********************************** Driver Button Assignments ****************************************/

//		driverY.whileHeld(new RunIntakeWithJoystick());

		driverA.whileHeld(new RunDrivetrainOnlyOneSide(true, Constants.SPEED_MULTIPLIER)); // true runs the left side
		driverB.whileHeld(new RunDrivetrainOnlyOneSide(false, Constants.SPEED_MULTIPLIER)); // flase runs the right side


		//driverX.whileHeld(new TurnTowardLimelightTarget());
		//driverY.whileHeld(new DriveTowardLimelightTargetTime(2 * 1e6));
		System.out.println("OI initialized");
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


}
