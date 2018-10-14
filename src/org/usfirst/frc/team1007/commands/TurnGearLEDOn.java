package org.usfirst.frc.team1007.commands;

import org.usfirst.frc.team1007.robot.OI;
import org.usfirst.frc.team1007.subsystems.GearIntakeSubsystem;
import org.usfirst.frc.team1007.utils.Constants;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class TurnGearLEDOn extends Command {
	
	private double startTime;
	
	 public TurnGearLEDOn() {
	        // Use requires() here to declare subsystem dependencies
	        // eg. requires(chassis);
		 requires(GearIntakeSubsystem.getInstance());
	 }
	 
	// Called just before this Command runs the first time
	 protected void initialize() {
	   	if(GearIntakeSubsystem.getInstance().isGearIn()){
    		GearIntakeSubsystem.getInstance().setGearLED(false, true);
    	}
	   	else{
	   		GearIntakeSubsystem.getInstance().setGearLED(true, false);	
	   	}
	   	startTime = Timer.getFPGATimestamp();
     }
	 
	 // Called repeatedly when this Command is scheduled to run
	 protected void execute() {
		 
	 }

	  // Make this return true when this Command no longer needs to run execute()
	 protected boolean isFinished() {
        return ((Timer.getFPGATimestamp() - startTime) > Constants.GEAR_LIGHT_TIME) || OI.getGearIntakeButton() || OI.getDeployGearButton();
     }

   // Called once after isFinished returns true
	 protected void end() {
	   	GearIntakeSubsystem.getInstance().setGearLED(false, false);
    }
	 
	 // Called when another command which requires one or more of the same
   // subsystems is scheduled to run
	 protected void interrupted() {
	   	end();
    }
}

