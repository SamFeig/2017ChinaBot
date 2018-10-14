package org.usfirst.frc.team1007.subsystems;
//package org.usfirst.frc.team1007.robot.subsystems;

import org.usfirst.frc.team1007.robot.Robot;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/**
*
*/
public class DrivetrainSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
	private SpeedController leftMaster;
	private SpeedController leftSlave;
	private SpeedController rightMaster;
	private SpeedController rightSlave;
  
	public DrivetrainSubsystem(){
		super();
		leftMaster = new Victor(Robot.bot.rightMasterID);
		leftSlave = new Victor(Robot.bot.rightSlaveID);
		rightMaster = new Victor(Robot.bot.leftMasterID);
		rightSlave = new Victor(Robot.bot.leftSlaveID);
	}
	private static DrivetrainSubsystem instance = new DrivetrainSubsystem();
	
	public static DrivetrainSubsystem getInstance(){
		return instance;
	}
	public void setPower(double power){
		leftMaster.set(power);
		leftSlave.set(power);
		rightMaster.set(power);
		rightSlave.set(power);
	}
	public void setMotorOutputs(double leftSpeed, double rightSpeed, boolean reverse){		
		if(reverse){
			leftMaster.set(-leftSpeed);
			leftSlave.set(-leftSpeed);
			rightMaster.set(rightSpeed);
			rightSlave.set(rightSpeed);
			SmartDashboard.putNumber("Left motor power", leftSpeed);
			SmartDashboard.putNumber("Right motor power", rightSpeed);
			
		}
		else{
			leftMaster.set(leftSpeed); 
			leftSlave.set(leftSpeed);
			rightMaster.set(-rightSpeed); 
			rightSlave.set(-rightSpeed);
			SmartDashboard.putNumber("Left motor power", leftSpeed);
			SmartDashboard.putNumber("Right motor power", rightSpeed);
		}
	}
	
	
   private static double limit(double num) {
       if (num > 1.0) {
           num= 1.0;
       }
       else if (num < -1.0) {
           num= -1.0;
       }
       	return num;
   }
   
    public void backwardsFullVelocity(){
		setMotorOutputs(1,1,false);
	}
	public void forwardsFullVelocity(){
		setMotorOutputs(-1,-1,false);
	}
   	
	public void arcadeDrive(double moveValue, double rotateValue, boolean reverse) {
       double leftMotorSpeed;
       double rightMotorSpeed;

       moveValue = limit(moveValue);
       rotateValue = limit(rotateValue);
       SmartDashboard.putNumber("moveValue", moveValue);
       SmartDashboard.putNumber("rotateValue", rotateValue);

       if (moveValue > 0.0) {
           if (rotateValue > 0.0) {
               leftMotorSpeed = Math.max(moveValue, rotateValue);
               rightMotorSpeed = moveValue - rotateValue;
           } else {
               leftMotorSpeed = moveValue + rotateValue;
               rightMotorSpeed = Math.max(moveValue, -rotateValue);
           }
       } else {
           if (rotateValue > 0.0) {
               leftMotorSpeed = moveValue + rotateValue;
               rightMotorSpeed = -Math.max(-moveValue, rotateValue);
           } else {
               leftMotorSpeed = -Math.max(-moveValue, -rotateValue);
               rightMotorSpeed = moveValue - rotateValue;
           }
       }
       
       setMotorOutputs(leftMotorSpeed, rightMotorSpeed, reverse);       
   }
	
   public void tankDrive(double leftValue, double rightValue, boolean reverse) {

       leftValue = limit(leftValue);
       rightValue = limit(rightValue);

       setMotorOutputs(leftValue, rightValue, reverse);
   }
   
	@Override
	protected void initDefaultCommand() {
	}   
}