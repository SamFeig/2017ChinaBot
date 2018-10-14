package org.usfirst.frc.team1007.robot;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team1007.auton.CenterPeg;
import org.usfirst.frc.team1007.commands.ArcadeDriveCommand;
import org.usfirst.frc.team1007.subsystems.CompressorSubsystem;
import org.usfirst.frc.team1007.subsystems.DrivetrainSubsystem;
import org.usfirst.frc.team1007.subsystems.GearIntakeSubsystem;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	
	public static RobotHardwareChinaBot bot = null;
	private Command autonCommand = null; 
	private double startTime;
	
	public static DrivetrainSubsystem drivetrain;
	public static GearIntakeSubsystem gear;
	public static OI oi;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		NetworkTable.globalDeleteAll(); //Removes unused garbage from SmartDashboard
        NetworkTable.initialize();      //Initialize Network Tables
		bot = new RobotHardwareChinaBot();
		bot.initialize();
		OI.initialize();
		RobotState.getInstance().setState(RobotState.State.DISABLED);
		CameraServer.getInstance().startAutomaticCapture();
		
		// instantiate the command used for the autonomous period
		autonCommand = new CenterPeg();

		// Show what command your subsystem is running on the SmartDashboard
//		SmartDashboard.putData(drivetrain);
//		SmartDashboard.putData(elevator);
//		SmartDashboard.putData(wrist);
//		SmartDashboard.putData(claw);
	}
	
	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
		DrivetrainSubsystem.getInstance().tankDrive(0.0,0.0,false);
		RobotState.getInstance().setState(RobotState.State.DISABLED);
		CompressorSubsystem.getInstance().stopCompressor();

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		SmartDashboard.putBoolean("Compressor Enabled", CompressorSubsystem.getInstance().isEnabled());
		SmartDashboard.putNumber("Gear Motor Current", GearIntakeSubsystem.getInstance().getMotorCurrent());
		
	}

	@Override
	public void autonomousInit() {
		startTime = Timer.getFPGATimestamp();
		RobotState.getInstance().setState(RobotState.State.AUTON);
		autonCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		LiveWindow.setEnabled(false);
		RobotState.getInstance().setState(RobotState.State.TELEOP);
		startTime = Timer.getFPGATimestamp();
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
//		if (autonomousCommand != null)
//			autonomousCommand.cancel();
		CompressorSubsystem.getInstance().startCompressor();
		(new ArcadeDriveCommand()).start();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		SmartDashboard.putNumber("Time Remaining", 150 - (Timer.getFPGATimestamp() - startTime));
		SmartDashboard.putBoolean("Compressor Enabled", CompressorSubsystem.getInstance().isEnabled());
		SmartDashboard.putNumber("Gear Motor Current", GearIntakeSubsystem.getInstance().getMotorCurrent());
		SmartDashboard.putBoolean("Gear In", GearIntakeSubsystem.getInstance().isGearIn());
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
