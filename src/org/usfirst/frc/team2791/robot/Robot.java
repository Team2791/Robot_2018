
package org.usfirst.frc.team2791.robot;

import org.usfirst.frc.team2791.robot.commands.auto.DoNothing;
import org.usfirst.frc.team2791.robot.subsystems.IntakeClaw;
import org.usfirst.frc.team2791.robot.subsystems.ShakerDrivetrain;
import org.usfirst.frc.team2791.robot.subsystems.ShakerLift;
import org.usfirst.frc.team2791.robot.subsystems.ShakerRamp;
import org.usfirst.frc.team2791.robot.subsystems.Manipulator;
import org.usfirst.frc.team2791.robot.util.Limelight;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */


public class Robot extends IterativeRobot {
	
	public static PowerDistributionPanel pdp; //CAN ID has to be 0 for current sensing
	public static OI oi;
	
	public static ShakerDrivetrain drivetrain;
	public static IntakeClaw intakeClaw;
	public static ShakerRamp ramps; 
	public static Manipulator manipulator;
	public static Limelight limelight;
    public static ShakerLift lift;

    Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		System.out.println("Starting to init my systems.");
		
		pdp = new PowerDistributionPanel(RobotMap.PDP); //CAN id has to be 0
		drivetrain = new ShakerDrivetrain();
		//intakeClaw = new IntakeClaw();
		manipulator = new Manipulator();
		ramps = new ShakerRamp();
		lift = new ShakerLift();
		limelight = new Limelight();


		// Set up our auton chooser
		chooser.addDefault("Default Auto - Do Nothing", new DoNothing());
//		chooser.addObject("My Auto", new MyAutoCommand());
		SmartDashboard.putData("Auto mode", chooser);
		
		oi = new OI();
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		debug();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		autonomousCommand = chooser.getSelected();

		// schedule the autonomous command
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		debug();
	}

	@Override
	public void teleopInit() {
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		//debug();
	}
	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
		debug();
	}


	public void debug(){
		limelight.debug();
		drivetrain.debug();
		ramps.debug();
		manipulator.debug();
		lift.debug();
	}
}
