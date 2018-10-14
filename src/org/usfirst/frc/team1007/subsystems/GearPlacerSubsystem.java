package org.usfirst.frc.team1007.subsystems;

import org.usfirst.frc.team1007.robot.Robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class GearPlacerSubsystem extends Subsystem {
	
	
	private DoubleSolenoid piston;
	
	public GearPlacerSubsystem() {
		super();
		piston = new DoubleSolenoid(Robot.bot.pistonSolonoidID1, Robot.bot.pistonSolonoidID2);
	}
	
	private static GearPlacerSubsystem instance = new GearPlacerSubsystem();
	
	public static GearPlacerSubsystem getInstance() {
		return instance;
	}
	
	public void extendPiston() {
		piston.set(DoubleSolenoid.Value.kForward);
	}
	
	public void retractPiston() {
		piston.set(DoubleSolenoid.Value.kReverse);
	}

	@Override
	protected void initDefaultCommand() {	
	}

}
