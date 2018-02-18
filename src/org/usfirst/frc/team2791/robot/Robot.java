
package org.usfirst.frc.team2791.robot;

import org.usfirst.frc.team2791.robot.commands.auto.DoNothing;
import org.usfirst.frc.team2791.robot.commands.auto.DriveForwardTime;
import org.usfirst.frc.team2791.robot.commands.auto.TimeOnlyDriveStraightToSwitch;
import org.usfirst.frc.team2791.robot.commands.auto.TimeOnlyStraightSwitchCubeSCORE;
import org.usfirst.frc.team2791.robot.subsystems.Manipulator;
import org.usfirst.frc.team2791.robot.subsystems.ShakerDrivetrain;
import org.usfirst.frc.team2791.robot.subsystems.ShakerLift;
import org.usfirst.frc.team2791.robot.subsystems.ShakerRamp;
import org.usfirst.frc.team2791.robot.util.Limelight;
import org.usfirst.frc.team2791.robot.util.autonChoosers.AutonCommandChooser;
import org.usfirst.frc.team2791.robot.util.autonChoosers.NearSwitchAutonChooser;
import org.usfirst.frc.team2791.robot.util.autonChoosers.NoChoiceChooser;

import edu.wpi.first.wpilibj.DriverStation;
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

	public static String data;
	public static boolean weOwnLeftSideNearSwitch, weOwnLeftSideScale, weOwnLeftSideFarSwitch;
	public static DriverStation station;
	public static PowerDistributionPanel pdp; //CAN ID has to be 0 for current sensing
	public static OI oi;
	
	public static ShakerDrivetrain drivetrain;
	public static ShakerRamp ramps; 
	public static Manipulator manipulator;
	public static Limelight limelight;
    public static ShakerLift lift;

    Command autonomousCommand;
    AutonCommandChooser autonCommandChooser;
	SendableChooser<AutonCommandChooser> chooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		System.out.println("Starting to init my systems.");
		
		pdp = new PowerDistributionPanel(RobotMap.PDP); //CAN id has to be 0
		drivetrain = new ShakerDrivetrain();
		manipulator = new Manipulator();
		ramps = new ShakerRamp();
		lift = new ShakerLift();
		limelight = new Limelight();

		updateGameData();

		// Set up our auton chooser
		chooser.addDefault("Default Auto - Do Nothing", new NoChoiceChooser(new DoNothing()));
		chooser.addObject("Cross line - time only", new NoChoiceChooser(new DriveForwardTime(0.33, 3.5)));
		
		chooser.addObject("LEFT side Straight Switch - time only", new NearSwitchAutonChooser(
			new TimeOnlyStraightSwitchCubeSCORE(), // this will run when we are on the left side of the switch
			new TimeOnlyDriveStraightToSwitch() // this will run when we are on the right side of the switch
		));

		chooser.addObject("RIGHT side Straight Switch - time only", new NearSwitchAutonChooser(
			new TimeOnlyDriveStraightToSwitch(), // this will run when we are on the left side of the switch
			new TimeOnlyStraightSwitchCubeSCORE() // this will run when we are on the right side of the switch
		));
		
		// this one is not working yet
//		chooser.addObject("Turn Switch HIGH drop - time only", chooser.addObject("RIGHT side Straight Switch - time only", new NearSwitchAutonChooser(
//			new TimeOnlyTurnSwitchHighDropLEFT(), // this will run when we are on the left side of the switch
//			new TimeOnlyTurnSwitchHighDropRIGHT() // this will run when we are on the right side of the switch
//		));
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
		Robot.ramps.setRampsDown(false);
		updateGameData();
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		debug();
		updateGameData();
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
		updateGameData();
		// schedule the autonomous command
		
		autonCommandChooser = chooser.getSelected();

		if (autonCommandChooser != null) {
			autonomousCommand = autonCommandChooser.getCommand(weOwnLeftSideNearSwitch, weOwnLeftSideScale, weOwnLeftSideFarSwitch);
		}
		if (autonomousCommand != null) {
			autonomousCommand.start();
		}
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
		updateGameData();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		debug();
		updateGameData();
	}
	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
		debug();
		//updateGameData();
	}


	public void updateGameData(){	
		int retries = 100;
		// Retry code taken from 5687! Thanks!
		// https://www.chiefdelphi.com/forums/showpost.php?p=1735952&postcount=22
        String data = DriverStation.getInstance().getGameSpecificMessage();
        while (data.length() < 2 && retries > 0) {
            retries--;
            try {
                Thread.sleep(5);
            } catch (InterruptedException ie) {
                // Just ignore the interrupted exception
            }
            data = DriverStation.getInstance().getGameSpecificMessage();
        }
		
		if(data.length() > 0){
			weOwnLeftSideNearSwitch = data.charAt(0) == 'L';
			weOwnLeftSideScale = data.charAt(1) == 'L';
			weOwnLeftSideFarSwitch = data.charAt(2) == 'L';

			SmartDashboard.putBoolean("leftSwitchNear", weOwnLeftSideNearSwitch);
			SmartDashboard.putBoolean("leftScale", weOwnLeftSideScale);
			SmartDashboard.putBoolean("leftSwitchFar", weOwnLeftSideFarSwitch);
		}
	}

	public void debug(){
		limelight.debug();
		drivetrain.debug();
		ramps.debug();
		manipulator.debug();
		lift.debug();
	}
}
