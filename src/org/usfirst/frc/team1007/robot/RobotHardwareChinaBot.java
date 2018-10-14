package org.usfirst.frc.team1007.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RobotHardwareChinaBot extends RobotHardware {
	
	public final int leftMasterID = 0;  //front left
	public final int leftSlaveID = 1;	//back left
	public final int rightMasterID = 2;	//front right
	public final int rightSlaveID  = 3; //back right
	
	public final int pistonSolonoidID1 = 6;//0
	public final int pistonSolonoidID2 = 7;//1
	
	public final int gearMotor = 4;
	public final int gearMotorPDPPort = 14;
	public final int climberMotor = 5;
	
	public final int gearGreenLEDPort = 5;
	public final int gearRedLEDPort = 4;
	
	@Override
	public boolean hasGearLED(){
		return true;
	}
	
	@Override
	public void initialize() {
		SmartDashboard.putString("Robot", getName());
	}

	@Override
	public String getName() {
		return "ChinaBot";
	}
}
