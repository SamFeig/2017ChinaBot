package org.usfirst.frc.team1007.commands;

import org.usfirst.frc.team1007.subsystems.GearPlacerSubsystem;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PistonIn extends Command {
	public PistonIn() {
		// Use requires() here to declare subsystem dependencies
		requires(GearPlacerSubsystem.getInstance());
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		GearPlacerSubsystem.getInstance().retractPiston();
		SmartDashboard.putBoolean("piston out", false);
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}
}

