package org.usfirst.frc.team1007.commands;

import org.usfirst.frc.team1007.robot.OI;
import org.usfirst.frc.team1007.subsystems.GearIntakeSubsystem;

import edu.wpi.first.wpilibj.RobotState;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class ReverseGearIntakeCommand extends Command {
	 
	private double startTime;
    public ReverseGearIntakeCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	startTime = Timer.getFPGATimestamp();
    	if(OI.getDeployGearButton()){
    		GearIntakeSubsystem.getInstance().setGearIntakeMotorPower(1.0);
    	}    	
    	else{
    		GearIntakeSubsystem.getInstance().setGearIntakeMotorPower(0.0);
    	}
    	/*if(GearIntakeSubsystem.getInstance().isGearIn()){
    		GearIntakeSubsystem.getInstance().setGearLED(true);
    	}
    	else{
    		GearIntakeSubsystem.getInstance().setGearLED(false);	
    	}*/
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(RobotState.isAutonomous()) {
    	GearIntakeSubsystem.getInstance().setGearIntakeMotorPower(1.0);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
//    	System.out.println("Reverse Finished: " + (Timer.getFPGATimestamp() - startTime > 1.0));
//    	System.out.println(Timer.getFPGATimestamp() - startTime);
        return (Timer.getFPGATimestamp() - startTime > 1.0);
    }

    // Called once after isFinished returns true
    protected void end() {
    	GearIntakeSubsystem.getInstance().setGearIntakeMotorPower(0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
