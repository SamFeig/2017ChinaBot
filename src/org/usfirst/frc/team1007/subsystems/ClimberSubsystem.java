package org.usfirst.frc.team1007.subsystems;

import org.usfirst.frc.team1007.robot.Robot;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
/**
 *
 */
public class ClimberSubsystem extends Subsystem {
    // Put methods for controlling this subsystem
    public VictorSP victor1; 
    
	public ClimberSubsystem(){
		super();
		victor1 = new VictorSP(Robot.bot.climberMotor);
	}
	private static ClimberSubsystem instance = new ClimberSubsystem();
	
	public static ClimberSubsystem getInstance(){
		return instance;
	}
	public void setPower(double power){
		victor1.set(power);
	}
    public void initDefaultCommand(){
    }
}