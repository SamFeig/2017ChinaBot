package org.usfirst.frc.team1007.commands;

import org.usfirst.frc.team1007.subsystems.GearIntakeSubsystem;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class IntakeGear extends CommandGroup {
	public IntakeGear() {
		//addSequential(new PistonOut());
		addSequential(new GearIntakeCommand());
		if(GearIntakeSubsystem.getInstance().isGearIn()) {
			addSequential(new PistonIn());
		}
		addParallel(new TurnGearLEDOn());
	}
}
