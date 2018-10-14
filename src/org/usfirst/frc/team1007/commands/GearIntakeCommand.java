package org.usfirst.frc.team1007.commands;

import org.usfirst.frc.team1007.subsystems.GearIntakeSubsystem;
import org.usfirst.frc.team1007.utils.Constants;
import org.usfirst.frc.team1007.robot.OI;
import org.usfirst.frc.team1007.robot.Robot;
import org.usfirst.frc.team1007.robot.RobotState;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class GearIntakeCommand extends Command {
	private double startTime;

	public GearIntakeCommand() {
		// Use requires() here to declare subsystem dependencies
		//requires(Robot.exampleSubsystem);
		requires(GearIntakeSubsystem.getInstance());
	}
	
	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		startTime = Timer.getFPGATimestamp();
//		if(OI.getGearIntakeButton()) {
//			GearIntakeSubsystem.getInstance().setGearIntakeMotorPower(-1.0);
//			RobotState.getInstance().setGearIntakeRunning(true);
//		}
//		else{
//    		GearIntakeSubsystem.getInstance().setGearIntakeMotorPower(1.0);
//    		RobotState.getInstance().setGearIntakeRunning(true);
//    	}
	}
	
	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {	
		GearIntakeSubsystem.getInstance().setGearIntakeMotorPower(-1.0);
		RobotState.getInstance().setGearIntakeRunning(true);
	}
	
	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return GearIntakeSubsystem.getInstance().isGearIn() ;//|| !OI.getGearIntakeButton(); //false
	}
	
	// Called once after isFinished returns true
	@Override
	protected void end() {
		RobotState.getInstance().setGearIntakeRunning(false);
    	GearIntakeSubsystem.getInstance().setGearIntakeMotorPower(0.0);
	}
	
	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		end();
	}
}

