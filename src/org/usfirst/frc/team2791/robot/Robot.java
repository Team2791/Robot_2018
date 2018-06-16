
package org.usfirst.frc.team2791.robot;

import org.usfirst.frc.team2791.robot.commands.auto.BangBangTurnSwitchLEFT;
import org.usfirst.frc.team2791.robot.commands.auto.BangBangTurnSwitchRIGHT;
import org.usfirst.frc.team2791.robot.commands.auto.DoNothing;
import org.usfirst.frc.team2791.robot.commands.auto.PIDSideScaleClose_ScaleEdge;
import org.usfirst.frc.team2791.robot.commands.auto.PIDSideScaleFar;
import org.usfirst.frc.team2791.robot.commands.auto.PIDSideScaleFarONLYCROSS;
import org.usfirst.frc.team2791.robot.commands.auto.PIDSideSwitchClose;
import org.usfirst.frc.team2791.robot.commands.auto.PIDSideSwitchFar;
import org.usfirst.frc.team2791.robot.commands.auto.PIDTurnSwitchLEFT;
import org.usfirst.frc.team2791.robot.commands.auto.PIDTurnSwitchLEFT_2Cube;
import org.usfirst.frc.team2791.robot.commands.auto.PIDTurnSwitchRIGHT;
import org.usfirst.frc.team2791.robot.commands.auto.PIDTurnSwitchRIGHT_2Cube;
import org.usfirst.frc.team2791.robot.commands.auto.TimeOnlyDriveStraightToSwitch;
import org.usfirst.frc.team2791.robot.commands.auto.TimeOnlyStraightSwitchCubeSCORE;
import org.usfirst.frc.team2791.robot.commands.auto.GrrPaths.CloseScaleGrrPath;
import org.usfirst.frc.team2791.robot.commands.auto.GrrPaths.SideScaleFarGrrPath;
import org.usfirst.frc.team2791.robot.commands.auto.GrrPaths.TurnSwitch2CubeGrrPath;
import org.usfirst.frc.team2791.robot.commands.auto.pid.DriveEncoderBangBangGyroPID;
import org.usfirst.frc.team2791.robot.commands.auto.pid.DriveStraightEncoderGyro;
import org.usfirst.frc.team2791.robot.commands.auto.pid.StationaryGyroTurn;
import org.usfirst.frc.team2791.robot.commands.auto.timeonly.DriveForwardTime;
import org.usfirst.frc.team2791.robot.subsystems.Manipulator;
import org.usfirst.frc.team2791.robot.subsystems.ShakerDrivetrain;
import org.usfirst.frc.team2791.robot.subsystems.ShakerLift;
import org.usfirst.frc.team2791.robot.subsystems.ShakerRamp;
import org.usfirst.frc.team2791.robot.util.Limelight;
import org.usfirst.frc.team2791.robot.util.autonChoosers.AutonCommandChooser;
import org.usfirst.frc.team2791.robot.util.autonChoosers.NearSwitchAutonChooser;
import org.usfirst.frc.team2791.robot.util.autonChoosers.NoChoiceChooser;
import org.usfirst.frc.team2791.robot.util.autonChoosers.ScaleAutonChooser;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
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


public class Robot extends TimedRobot {

	public static String data;
	public static boolean weOwnLeftSideNearSwitch, weOwnLeftSideScale, weOwnLeftSideFarSwitch;
	public static DriverStation station;
	public static PowerDistributionPanel pdp; //CAN ID has to be 0 for current sensing
	public static OI oi;
	public static Compressor compressor;
	
	public static ShakerDrivetrain drivetrain;
	public static ShakerRamp ramps; 
	public static Manipulator manipulator;
	public static Limelight limelight;
    public static ShakerLift lift;
    public static UsbCamera driver_cam;
    public static UsbCamera down_cam;
    
    private Timer teleopTimer;

    Command autonomousCommand;
    AutonCommandChooser autonCommandChooser;
	SendableChooser<AutonCommandChooser> chooser = new SendableChooser<>();
	AutonCommandChooser DEFAULT_AUTO;
	String DEFAULT_AUTO_NAME;
	
	boolean DEBUG = false;

	/**
	 * This function is run when the robot is first started up and 2should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		compressor = new Compressor(RobotMap.PCM_CAN_ID);
		compressor.start();
		pdp = new PowerDistributionPanel(RobotMap.PDP); //CAN id has to be 0
		drivetrain = new ShakerDrivetrain();
		manipulator = new Manipulator();
		ramps = new ShakerRamp();
		lift = new ShakerLift();
		limelight = new Limelight();
		
		teleopTimer = new Timer();

		driver_cam = CameraServer.getInstance().startAutomaticCapture("Driver Cam", 0);
		down_cam = CameraServer.getInstance().startAutomaticCapture("down_cam", 1);
		ShakerDrivetrain.putPIDGainsOnSmartDash();

		updateGameData(false);

//		autonomousCommand = new RunPath(GrrPaths.FROM_CENTER.SWITCH_RIGHT, x -> {
//			if (x < 0.2) {
//				return 0.3;
//			} else if (x < 0.75) {
//				return 0.6;
//			} else {
//				return 0.2;
//			}
//		});		
		
		// Testing multiple 340 paths at once.
	//	autonomousCommand = new TurnSwitch2CubeGrrPath(true);
//		autonomousCommand = new SideScaleFarGrrPath(false); //Noah made this false because he is awesome and whatever
//		autonomousCommand = new RunPath(ShakerPaths.FROM_RIGHT.SamsPath, 0.3);
//		autonomousCommand = new CloseScaleGrrPath(true);
		
//		DEBUG = false;
//		autonomousCommand = new SideScaleFarGrrPath(true);

////		// Set up our auton chooser
//		DEFAULT_AUTO_NAME = "D: Center switch Grr path 2.5 cube";
//		DEFAULT_AUTO = new NearSwitchAutonChooser(
//			new TurnSwitch2CubeGrrPath(false),
//			new TurnSwitch2CubeGrrPath(true)
//		);

//		DEFAULT_AUTO_NAME = "D: side scale grr pathing LEFT - PID";
//		DEFAULT_AUTO = new ScaleAutonChooser(
//				new CloseScaleGrrPath(true),
//				new SideScaleFarGrrPath(true)
//		);

		DEFAULT_AUTO_NAME = "D: side scale grr pathing RIGHT - PID";
		DEFAULT_AUTO = new ScaleAutonChooser(
				new SideScaleFarGrrPath(false),
				new CloseScaleGrrPath(false)
		);
		
//		DEFAULT_AUTO_NAME = "D: side scale tip or cross LEFT - PID";
//		DEFAULT_AUTO = new ScaleAutonChooser(
//			new PIDSideScaleClose_ScaleEdge(true),
//			new PIDSideScaleFarONLYCROSS(true)
//		);
//	
//		DEFAULT_AUTO_NAME = "D: side scale tip or cross  RIGHT - PID";
//		DEFAULT_AUTO = new ScaleAutonChooser(
//			new PIDSideScaleFarONLYCROSS(false),
//			new PIDSideScaleClose_ScaleEdge(false)
//		);
		
//		DEFAULT_AUTO_NAME = "D: Center switch 1.5 cube";
//		DEFAULT_AUTO = new NearSwitchAutonChooser(
//			new PIDTurnSwitchLEFT_2Cube(),
//			new PIDTurnSwitchRIGHT_2Cube()
//		);

//		DEFAULT_AUTO_NAME = "D: side scale LEFT - PID";
//		DEFAULT_AUTO = new ScaleAutonChooser(
////				new PIDSideScaleClose(true),
//			new PIDSideScaleClose_ScaleEdge(true),
//			new PIDSideScaleFar(true)
//		);
//	
//		DEFAULT_AUTO_NAME = "D: side scale RIGHT - PID";
//		DEFAULT_AUTO = new ScaleAutonChooser(
//			new PIDSideScaleFar(false),
////				new PIDSideScaleClose(false)
//			new PIDSideScaleClose_ScaleEdge(false)
//		);

//		DEFAULT_AUTO_NAME = "D: Do Nothing";
//		DEFAULT_AUTO = new NoChoiceChooser(new DoNothing());

		chooser.addDefault(DEFAULT_AUTO_NAME, DEFAULT_AUTO);
		chooser.addObject("Do Nothing", new NoChoiceChooser(new DoNothing()));
		System.out.println("DEFAULT AUTO IS: = " + DEFAULT_AUTO_NAME);
		
		
		chooser.addObject("center switch 2.5 cube - Grr Path", new NearSwitchAutonChooser(
				new TurnSwitch2CubeGrrPath(false),
				new TurnSwitch2CubeGrrPath(true)
			));
		
		chooser.addObject("side scale grr pathing LEFT - PID", new ScaleAutonChooser(
				new CloseScaleGrrPath(true),
				new SideScaleFarGrrPath(true)
		));

		chooser.addObject("side scale grr pathing RIGHT - PID", new ScaleAutonChooser(
				new SideScaleFarGrrPath(false),
				new CloseScaleGrrPath(false)
		));

		chooser.addObject("side scale tip or cross LEFT - PID", new ScaleAutonChooser(
			new PIDSideScaleClose_ScaleEdge(true),
			new PIDSideScaleFarONLYCROSS(true)
		));
	
		chooser.addObject("side scale tip or cross  RIGHT - PID", new ScaleAutonChooser(
			new PIDSideScaleFarONLYCROSS(false),
			new PIDSideScaleClose_ScaleEdge(false)
		));		
		
		chooser.addObject("center switch x1.5 cube - PID", new NearSwitchAutonChooser(
			new PIDTurnSwitchLEFT_2Cube(),
			new PIDTurnSwitchRIGHT_2Cube()
		));
		
		chooser.addObject("side scale LEFT - PID", new ScaleAutonChooser(
//			new PIDSideScaleClose(true),
			new PIDSideScaleClose_ScaleEdge(true),
			new PIDSideScaleFar(true)
		));

		chooser.addObject("side scale RIGHT - PID", new ScaleAutonChooser(
			new PIDSideScaleFar(false),
//			new PIDSideScaleClose(false)
			new PIDSideScaleClose_ScaleEdge(false)
		));
		
		chooser.addObject("TEST - side scale edge LEFT", new NoChoiceChooser(new PIDSideScaleClose_ScaleEdge(true)));
		chooser.addObject("TEST - side scale edge RIGHT", new NoChoiceChooser(new PIDSideScaleClose_ScaleEdge(false)));
		chooser.addObject("TEST - far side scale LEFT", new NoChoiceChooser(new PIDSideScaleFar(true)));
		chooser.addObject("TEST - far side scale RIGHT", new NoChoiceChooser(new PIDSideScaleFar(false)));
		chooser.addObject("TEST - turn switch x1.5 LEFT", new NoChoiceChooser(new PIDTurnSwitchLEFT_2Cube()));
		chooser.addObject("TEST - turn switch x1.5 RIGHT", new NoChoiceChooser(new PIDTurnSwitchRIGHT_2Cube()));
		
		chooser.addObject("DEBUG - Long bang bang + gyro drive", new NoChoiceChooser(new DriveEncoderBangBangGyroPID(0.4, 15*12, 100)));
		chooser.addObject("TEST - gyro pid 90 rotation", new NoChoiceChooser(new StationaryGyroTurn(90, 0.5, 1.5, 50)));
		chooser.addObject("TEST - gyro pid -90 rotation", new NoChoiceChooser(new StationaryGyroTurn(-90, 0.5, 1.5, 50)));
		chooser.addObject("TEST - gyro pid 45 rotation", new NoChoiceChooser(new StationaryGyroTurn(45, 0.5, 1.5, 50)));
		chooser.addObject("TEST - gyro pid 180 rotation", new NoChoiceChooser(new StationaryGyroTurn(180, 0.5, 1.5, 50)));
		chooser.addObject("TEST - gyro + encoder pid 10'' drive", new NoChoiceChooser(new DriveStraightEncoderGyro(10, 0.7, 100, .1)));
		chooser.addObject("TEST - gyro + encoder pid -10'' drive", new NoChoiceChooser(new DriveStraightEncoderGyro(-10, 0.7, 100, .1)));
		chooser.addObject("TEST - gyro + encoder pid 50'' drive", new NoChoiceChooser(new DriveStraightEncoderGyro(50, 0.7, 100, .1)));
		chooser.addObject("TEST - gyro + encoder pid -50'' drive", new NoChoiceChooser(new DriveStraightEncoderGyro(-50, 0.7, 100, .1)));
		chooser.addObject("TEST - gyro + encoder pid 120'' drive", new NoChoiceChooser(new DriveStraightEncoderGyro(120, 0.7, 100, .1)));
		chooser.addObject("TEST - gyro + encoder pid -120'' drive", new NoChoiceChooser(new DriveStraightEncoderGyro(-120, 0.7, 100, .1)));
		
		chooser.addObject("Cross line - time only", new NoChoiceChooser(new DriveForwardTime(0.33, 3.5)));
		
		chooser.addObject("LEFT side Straight Switch - time only", new NearSwitchAutonChooser(
			new TimeOnlyStraightSwitchCubeSCORE(), // this will run when we are on the left side of the switch
			new TimeOnlyDriveStraightToSwitch() // this will run when we are on the right side of the switch
		));

		chooser.addObject("RIGHT side Straight Switch - time only", new NearSwitchAutonChooser(
			new TimeOnlyDriveStraightToSwitch(), // this will run when we are on the left side of the switch
			new TimeOnlyStraightSwitchCubeSCORE() // this will run when we are on the right side of the switch
		));
		
		chooser.addObject("Turn Switch - Bang Bang", new NearSwitchAutonChooser(
			new BangBangTurnSwitchLEFT(), // this will run when we are on the left side of the switch
			new BangBangTurnSwitchRIGHT() // this will run when we are on the right side of the switch
		));
		
		chooser.addObject("side switch LEFT - PID", new NearSwitchAutonChooser(
		new PIDSideSwitchClose(true),
		new PIDSideSwitchFar(true)
	));

	chooser.addObject("side switch RIGHT - PID", new NearSwitchAutonChooser(
		new PIDSideSwitchFar(false),
		new PIDSideSwitchClose(false)
	));

		
		SmartDashboard.putData("Auto mode", chooser);
		oi = new OI();
	
//		try {
//			driver_cam = CameraServer.getInstance().startAutomaticCapture("Driver Camera", 0);
//			driver_cam.setPixelFormat(PixelFormat.kMJPEG);
////			driver_cam.setFPS(10);
////			if(!driver_cam.setResolution(240, 180)){
////				driver_cam.setResolution(240, 180); 
////				System.out.println("******Desired resolution FAILED for GEAR Camera******");
////			}
//		} catch(Exception e) {
//			System.out.println("*****Driver Camera FAILED*****");
//			e.printStackTrace();
//		}
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
		Robot.drivetrain.setVoltageRampRate(0);
		Robot.drivetrain.setBrakeMode(false);
		Robot.ramps.setRampsDown(false);
		updateGameData(true);
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		debug();
		updateGameData(false);
		
		// reset the encoders and gyor when we hit driver start
		if(OI.driver.getRawButton(8)) {
			Robot.drivetrain.resetEncoders();
	    	Robot.drivetrain.resetGyro();
		}
		
		Robot.lift.updateMagicMotionPIDGains();
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
		Robot.drivetrain.setBrakeMode(true);
		updateGameData(true);
		Robot.drivetrain.resetEncoders();
		Robot.drivetrain.resetGyro();
		// schedule the autonomous command

		autonCommandChooser = chooser.getSelected();

		//&& DEBUG == false
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
		updateGameData(false);
	}

	@Override
	public void teleopInit() {
		Robot.drivetrain.setBrakeMode(false);
		Robot.drivetrain.setVoltageRampRate(0);
		updateGameData(true);
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
//		System.out.println(drivetrain.isDrivetrainInDriveMode());
		
		Scheduler.getInstance().run();
//		debug();
		lift.debug();
		
		if(Robot.oi.operatorRS.get()) {
			Constants.LIFT_POT_OFFSET = -(Robot.lift.getHeight() - Constants.LIFT_POT_OFFSET);
			System.out.println("Lift pot offset set to " + Constants.LIFT_POT_OFFSET);
		}
	}
	
	
	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
		debug();
		updateGameData(false);
	}


	public void updateGameData(boolean retry){
		String data = DriverStation.getInstance().getGameSpecificMessage();
		if(retry) {
			int retries = 50;
			// Retry code taken from 5687! Thanks!
			// https://www.chiefdelphi.com/forums/showpost.php?p=1735952&postcount=22
			data = DriverStation.getInstance().getGameSpecificMessage();
	        while (data.length() < 2 && retries > 0) {
	            retries--;
	            try {
	                Thread.sleep(5);
	            } catch (InterruptedException ie) {
	                // Just ignore the interrupted exception
	            }
	            data = DriverStation.getInstance().getGameSpecificMessage();
	        }
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
		drivetrain.debug();
		ramps.debug();
		manipulator.debug();
		lift.debug();
		
		
		if(Robot.oi.operatorRS.get()) {
			Constants.LIFT_POT_OFFSET = -(Robot.lift.getHeight() - Constants.LIFT_POT_OFFSET);
			System.out.println("Lift pot offset set to "+Constants.LIFT_POT_OFFSET);
		}
		
//		System.out.println("opereator LS" + Robot.oi.operatorLS.get());
		
		boolean timerBlink = false;
		// start blinkning in the last 45 seconds.
		if(teleopTimer.get() > 135-45) {
			// blink on and off every second
			timerBlink = ((int) 2 * teleopTimer.get()) % 2 == 0;
		}
		SmartDashboard.putBoolean("GAME ENDING SOON", timerBlink);
	}
	
	public void run() {
		manipulator.run();
	}
}
