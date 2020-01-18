package org.usfirst.frc.team219.robot;

import org.usfirst.frc.team219.robot.commands.ResetEncoders;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
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
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
    private Joystick driveController;
    private Joystick armController;
    
	public OI(){
		  driveController = new Joystick(0);
		  armController = new Joystick(1);
		  JoystickButton a = new JoystickButton(driveController,1);
		  a.whenPressed(new ResetEncoders());
	}
	
	/**
	 * 
	 * @return - Driver controller's left joystick Y-axis tilt
	 */
	public double getLeftYDrive(){
		return driveController.getRawAxis(1);
	}
	
	/**
	 * 
	 * @return - Driver controller's right joystick Y-axis tilt
	 */
	public double getRightYDrive(){
		return driveController.getRawAxis(5);
	}
	
	/**
	 * 
	 * @return - Co-Driver controller's joystick Y-axis tilt
	 */
	public double getYArm()
	{
		return armController.getY();
	}	
    
}

