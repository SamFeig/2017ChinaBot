package org.usfirst.frc.team1007.robot;

import org.usfirst.frc.team1007.commands.ClimbCommand;
import org.usfirst.frc.team1007.commands.ClimbFasterCommand;
import org.usfirst.frc.team1007.commands.ExampleCommand;
import org.usfirst.frc.team1007.commands.GearIntakeCommand;
import org.usfirst.frc.team1007.commands.IntakeGear;
import org.usfirst.frc.team1007.commands.RaiseGearPlacer;
import org.usfirst.frc.team1007.commands.ReverseGearIntakeCommand;
import org.usfirst.frc.team1007.commands.LowerGearPlacer;
import org.usfirst.frc.team1007.commands.DeployGear;
//import org.usfirst.frc.team1007.commands.PlaceGearCommand;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
	
	private static Joystick driverJoystick = new Joystick(0);
	private static Joystick operatorJoystick = new Joystick(1);
	
	//button declarations
	private static JoystickButton gearDown = new JoystickButton(driverJoystick, 1);
	private static JoystickButton gearUp = new JoystickButton(driverJoystick, 2);
	private static JoystickButton climb = new JoystickButton(driverJoystick, 5);
	private static JoystickButton climbFaster = new JoystickButton(driverJoystick, 6);
	private static JoystickButton intakeGear = new JoystickButton(driverJoystick, 3);
	private static JoystickButton deployGear = new JoystickButton(driverJoystick, 4);
	
	public static void initialize() { 
		//call commands when pressed
		//intakeButton.whenPressed(new ToggleIntakeCommand());
		gearDown.whenPressed(new LowerGearPlacer());
		gearUp.whenPressed(new RaiseGearPlacer());
		climb.whenPressed(new ClimbCommand());
		climbFaster.whenPressed(new ClimbFasterCommand());
//		gear.toggleWhenPressed(new GearIntakeMotorCommand());
//		revGear.toggleWhenPressed(new ReverseGearIntakeCommand());
//		gear.whenPressed(new IntakeGear());
		intakeGear.toggleWhenPressed(new IntakeGear());
		deployGear.toggleWhenPressed(new DeployGear());
	}
	
	//Driver
	public static double getDriverRightXValue(){
		return driverJoystick.getRawAxis(4);
	}
	
	public static double getDriverLeftXValue(){
		return driverJoystick.getRawAxis(0);
	}
	
	public static double getDriverRightYValue(){
		return driverJoystick.getRawAxis(5);
	}
	
	public static double getDriverLeftYValue(){
		return driverJoystick.getRawAxis(1);
	}
	
	public static boolean getClimbButton(){
		return climb.get();
	}
	
	public static boolean getClimbFastButton(){
		return climbFaster.get();
	}
	
	public static boolean getGearIntakeButton() {
		return intakeGear.get();
	}
	
	public static boolean getDeployGearButton() {
		return deployGear.get();
	}
	
	
	//Operator
	public static double getOperatorRightYValue(){
		return operatorJoystick.getRawAxis(5);
	}
	
	public static double getOperatorLeftYValue(){
		return operatorJoystick.getRawAxis(1);
	}
	
	public static double getOperatorRightXValue(){
		return operatorJoystick.getRawAxis(4);
	}
	
	public static double getOperatorLeftXValue(){
		return operatorJoystick.getRawAxis(0);
	}

}
