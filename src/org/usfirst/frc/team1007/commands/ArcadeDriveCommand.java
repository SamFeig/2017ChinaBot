package org.usfirst.frc.team1007.commands;

import org.usfirst.frc.team1007.robot.OI;
import org.usfirst.frc.team1007.subsystems.DrivetrainSubsystem;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ArcadeDriveCommand extends Command {
	
	public ArcadeDriveCommand() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
	requires(DrivetrainSubsystem.getInstance());
	}

	// Called just before this Command runs the first time
	protected void initialize() { 
		//DrivetrainSubsystem.getInstance().percentVoltageMode();
	}
	
	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		DrivetrainSubsystem.getInstance().arcadeDrive(OI.getDriverLeftYValue(), OI.getDriverLeftXValue(),false);
		SmartDashboard.putNumber("LeftY", OI.getDriverLeftYValue());
	    SmartDashboard.putNumber("LeftX", OI.getDriverLeftXValue());
	}
	
	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
	    return !DriverStation.getInstance().isOperatorControl();
	}
	
	// Called once after isFinished returns true
	protected void end() {
		DrivetrainSubsystem.getInstance().tankDrive(0, 0, false);
	}
	
	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}

}
