package org.usfirst.frc.team1007.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.command.Subsystem;

public class CompressorSubsystem extends Subsystem {
	
	Compressor c;
	
	private static CompressorSubsystem instance = new CompressorSubsystem();
	
	public CompressorSubsystem() {
		super();
		c = new Compressor(0);
	}
	
	public static CompressorSubsystem getInstance(){
		return instance;
	}
	
	
	public void setClosedLoop(boolean val) {
		c.setClosedLoopControl(val);
	}
	
	
	public void startCompressor() {
		//if(!isEnabled()) {
			c.start();
		//}
	}
	
	public void stopCompressor() {
		//if(isEnabled()) {
			c.stop();
		//}
	}
	
	public boolean isClosedLoop() {
		return c.getClosedLoopControl();
	}
	
	public boolean isEnabled() {
		return c.enabled();
	}

	@Override
	protected void initDefaultCommand() {	
	}

}
