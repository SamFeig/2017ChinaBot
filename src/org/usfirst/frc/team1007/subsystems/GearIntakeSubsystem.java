package org.usfirst.frc.team1007.subsystems;

import org.usfirst.frc.team1007.robot.Robot;
import org.usfirst.frc.team1007.subsystems.GearIntakeSubsystem;
import org.usfirst.frc.team1007.utils.Constants;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GearIntakeSubsystem extends Subsystem {
	
	private VictorSP gearMotor;
	private static PowerDistributionPanel p;
	double startTime;
	private Solenoid gearRedLED;
	private Solenoid gearGreenLED;
	//private static int i = 0;
	
	public GearIntakeSubsystem() {
		super();
		p = new PowerDistributionPanel();
		gearMotor = new VictorSP(Robot.bot.gearMotor);
		
		if(Robot.bot.hasGearLED()){
			gearRedLED = new Solenoid(Robot.bot.gearRedLEDPort);
			gearGreenLED = new Solenoid(Robot.bot.gearGreenLEDPort);
		}
	}    
	
    private static GearIntakeSubsystem instance = new GearIntakeSubsystem();                                        
    
	public static GearIntakeSubsystem getInstance(){                                          
		return instance;
	}

	public void setGearIntakeMotorPower(double power){
		startTime = Timer.getFPGATimestamp();
		gearMotor.set(power);
	}
	
	public void setGearLED(boolean red, boolean green){
		if(red || green) {
			if(green) {
				gearRedLED.set(false);
				gearGreenLED.set(true);
				System.out.println("RED OFF GREEN ON");
			}
			else {
				gearRedLED.set(true);
				gearGreenLED.set(false);
				System.out.println("RED ON GREEN OFF");
			}
		}
		else {
			gearRedLED.set(false);
			gearGreenLED.set(false);
			System.out.println("RED OFF GREEN OFF");
		}
	}
	
	public double getMotorCurrent(){
		//System.out.println(p.getCurrent(Robot.bot.gearMotorPDPPort));
		return p.getCurrent(Robot.bot.gearMotorPDPPort);
	}
	
	public boolean isGearIn(){
		System.out.println("Current: " + getMotorCurrent() + "status: " + (getMotorCurrent() > Constants.GEAR_CURRENT_THRESHOLD));
		System.out.println("Time " + (Timer.getFPGATimestamp() - startTime) + "status " + ((Timer.getFPGATimestamp() - startTime) > Constants.GEAR_INTAKE_TIME));
		if((getMotorCurrent() > Constants.GEAR_CURRENT_THRESHOLD) && ((Timer.getFPGATimestamp() - startTime) > Constants.GEAR_INTAKE_TIME)) {
			SmartDashboard.putBoolean("Gear In", true);
			
			System.out.println("Gear In: " + true);
			setGearLED(false, true);
			return true;
		}
		setGearLED(true, false);
		return false;
//		return true;
	}
	
    public void initDefaultCommand() {
    }   
}
