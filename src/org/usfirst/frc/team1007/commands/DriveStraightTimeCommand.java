package org.usfirst.frc.team1007.commands;

import org.usfirst.frc.team1007.robot.OI;
import org.usfirst.frc.team1007.subsystems.DrivetrainSubsystem;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveStraightTimeCommand extends Command {
	private Timer timer;
	private double time;
	
	public DriveStraightTimeCommand(double time) {
		requires(DrivetrainSubsystem.getInstance());
		this.time = time;
	}
	
	protected void initialize() { 
		timer = new Timer();
    	timer.start();
    	System.out.println("Auton Starting");
	}
	
	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		DrivetrainSubsystem.getInstance().setMotorOutputs(-0.35, -0.3, false);
	}
	
	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
	    return timer.get() > time;
	}
	
	// Called once after isFinished returns true
	protected void end() {
		DrivetrainSubsystem.getInstance().tankDrive(0, 0, false);
		System.out.println("Auton Finished");
	}
	
	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}


}
