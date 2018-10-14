package org.usfirst.frc.team1007.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class DeployGear extends CommandGroup {
	
	public DeployGear() {
		addParallel(new ReverseGearIntakeCommand());
		addSequential(new WaitCommand(0.6));
		addSequential(new PistonOut());
	}
}
