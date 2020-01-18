package org.usfirst.frc.team219.robot.subsystems;

import org.usfirst.frc.team219.robot.RobotMap;
import org.usfirst.frc.team219.robot.commands.OpControlArm;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 *@author Jace Beaudoin, Mark Szewczuk, Jason Tran
 */
public class Arm extends Subsystem {

	private CANTalon armLeft;
	private CANTalon armRight;
	private double adjustSpeed;
	private double deltaPosition;
	private double deltaSpeed = .05;
	
	
	public Arm() {
		armLeft = new CANTalon(RobotMap.MOTOR_PORT_ARM_LEFT);
		armRight = new CANTalon(RobotMap.MOTOR_PORT_ARM_RIGHT);
		armLeft.setPosition(0);
	}

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	



	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		//setDefaultCommand(new MySpecialCommand());
//		setDefaultCommand(new OpControlArm());
	}
	
	/**
	 * 
	 * @param speed - Implemented in the OpControlArm Command, used to control the talon's speed output based on joystick Y-axis tilt
	 */
	public void controlArm(double speed){
		armLeft.set(speed);
		armRight.set(speed);
	}
	
	/**
	 * 
	 * @param speed - Speed that you want the arm to travel at.  <-- Should probably be a high value due to gearbox reduction on motor
	 * @param type - The enum type of what position to set the arm to
	 * @see {@link ArmPosition}
	 */
	public void setArmPos(double speed, ArmPosition type){
		double endPos = type.getPosition();
		/*
		 * This needs to be logic checked for going from one position to the next...  Sort of same issue with last years lift
		 */
		deltaPosition = endPos - armLeft.getEncPosition();
		adjustSpeed = (deltaSpeed * deltaPosition);
		armLeft.set(speed*adjustSpeed);
		armRight.set(speed*adjustSpeed);
	}
	
	/**
	 * 
	 * @param type - The enum type of what position to set the arm to
	 * @return - True or false depending on if the robot has reached its required desination
	 * @see {@link Position}
	 */
	public boolean isAtPos(ArmPosition type){
		double endPos = type.getPosition();
		/*
		 * This needs to be logic checked for going from one position to the next...  Sort of same issue with last years lift
		 */
		if(armLeft.getEncPosition() > endPos){
			return true;
		}
		return false;
	}
	
	public double getEncPos(){
		return armLeft.getEncPosition();
	}
	
	



}

