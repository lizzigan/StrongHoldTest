package org.usfirst.frc.team219.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {    
	//Our RobotMap will be used to declare port numberings for all devices on our robot
	//Declare your port numberings here:
	
	
	/**
	 * CAN Talon speed controller port declarations
	 */
	public static final int MOTOR_PORT_FL = 1;//1
	public static final int MOTOR_PORT_BL = 2;//8
	public static final int MOTOR_PORT_FR = 4; //3
	public static final int MOTOR_PORT_BR = 3;//2
	public static final int MOTOR_PORT_SHOOTER_LEFT = 9;//4
	public static final int MOTOR_PORT_SHOOTER_RIGHT = 7;
	public static final int MOTOR_PORT_ARM_LEFT = 5;
	public static final int MOTOR_PORT_ARM_RIGHT = 6;
	
	/**
	 * Gyro port declaration
	 */
	public static final int GYRO_PORT = 1;
	
	/**
	 * Pneumatics port declarations
	 */
	public static final int PCM_PORT = 0;
	public static final int SOLENOID1_FOWARD = 1;
	public static final int SOLENOID1_REVERSE = 2;
	public static final int SOLENOID2_FOWARD = 3;
	public static final int SOLENOID2_REVERSE = 4;
	
	public static final int SHOOTING_SOLENOID_FOWARD = 13;
	public static final int SHOOTING_SOLENOID_REVERSE = 14;

}


