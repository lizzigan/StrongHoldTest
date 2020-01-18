package org.usfirst.frc.team219.robot.subsystems;

import org.usfirst.frc.team219.robot.RobotMap;
import org.usfirst.frc.team219.robot.commands.OpDrive;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**	
 *@author Mark Szewczuk
 *
 */
public class DriveTrain extends Subsystem {
	
	public CANTalon motorFL; //Front left CAN Talon speed controller
	public CANTalon motorBL; //Back left CAN Talon speed controller
	public CANTalon motorFR; //Front right CAN Talon speed controller
	public CANTalon motorBR; //Back right CAN Talon speed controller
	public AnalogGyro gyro; //Gyro in the altoids tin 
	
	private final double deltaSpeed = .05; //Essentially a P-value for a homemade PID loop
	private final double startAngle; //The angle that the robot starts with (changes when re-deployed -- maybe not when turned on?)
	private double currAngle, deltaAngle; //currAngle is the gyro's current angle; used in methods.  deltaAngle is the change in angle over time; used in methods
	private double addSpeed; //Percentage based speed modifier; used in adjusting the drive straight factor of the robot
	
	private double treadLength; //The tread length of the robot in inches; used for determining distance traveled for proper autonomous
	private final double TICK_RATE = 4096.0; //Final for amount of ticks per one encoder revolution (<-- fix wording)
	private double distanceInTicks; //The amount of inches to travel in ticks
	
	/**
	 * @param treadLength - the length of the treads in inches
	 */
	public DriveTrain(double treadLength) {
	   	motorFL = new CANTalon(RobotMap.MOTOR_PORT_FL);
		motorBL = new CANTalon(RobotMap.MOTOR_PORT_BL);
		motorFR = new CANTalon(RobotMap.MOTOR_PORT_FR);
		motorBR = new CANTalon(RobotMap.MOTOR_PORT_BR);
		gyro = new AnalogGyro(RobotMap.GYRO_PORT);
		startAngle = gyro.getAngle();
		this.treadLength = treadLength;
	}
	
	/**
	 * Sets the default command for the subsystem
	 */
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
//    	setDefaultCommand(new OpDrive());
    }
    
    /**
     * Custom tank drive for operator control.
     * @param leftspeed - Speed output given to the left side motor.
     * @param rightspeed - Speed output given to the right side motor.
     */
    public void tankDrive(double leftspeed, double rightspeed){
    	this.setLeftBrakes();
    	this.setRightBrakes();
    	SmartDashboard.putNumber("left enc", motorFL.getEncPosition());
    	SmartDashboard.putNumber("right enc", motorFR.getEncPosition());
    	this.setTalonSpeed(leftspeed, rightspeed);
    }
    
    /**
     * Drives the robot straight (or extremely close to straight) autonomously at a given speed.  This is setup similar to a PID loop, calculating change
     * in gyro angle over time and adjusting the speed to the right side motor appropriately.
     * Ideally, the speed given should be the same.  
     * 
     * TEST WITH ONE SPEED PARAMETER * 
     * 
     * @param leftspeed - Speed you want the left side motors to go
     * @param rightspeed - Speed you want the right side motors to go
     */
    public void autonDrive(double leftspeed, double rightspeed){
    	currAngle = gyro.getAngle();
    	deltaAngle = currAngle - startAngle;
    	if(Math.abs(deltaAngle) > .05){
        	addSpeed = 1 + deltaAngle * deltaSpeed;
    	}
    	else{
    		addSpeed = 1;
    	}
    	
    	this.tankDrive(leftspeed, rightspeed * addSpeed);
//    	System.out.println(startAngle);
//    	System.out.println(currAngle);
    }
    
    /**
     * Checks to see if the robot is at the correct angle
     * @param endAngle - The angle to end at.
     * @param rightOrLeft - Used to determine if the robot is autonomously turning right or left.  True means
     * 						turn right, false means turn left. 
     * @return - True or false depending on if the robot is now correctly positioned at the angle.
     */
    public boolean gyroAtAngle(double endAngle, Direction d){
    	currAngle = gyro.getAngle();
//    	System.out.println(currAngle);
    	switch(d){
	    	case Right:
	    		if(currAngle > endAngle){
	    			return true;
	    		}
	    		break;
	    	case Left:
	    		if(currAngle < endAngle){
	    			return true;
	    		}
	    		break;
    	}
    	return false;
    }
    
    /**
     * Checks to see if the robot has driven the correct distance
     * <p>CHECK FOR WHICH ENCODERS SPEW OUT NEGATIVE VALUES, THEN SWITCH LESS THAN/GREATER THAN</p>
     * @param distanceInInches - the distance the robot is supposed to drive in inches.
     * @return - true if the robot has driven that distance, false if it has not.
     */
    public boolean isAtDistance(double distanceInInches){
    	distanceInTicks = distanceInInches * (TICK_RATE/this.treadLength);
    	if(getEncoderPos(motorFL) > distanceInTicks && getEncoderPos(motorFR) > distanceInTicks){
    		return true;
    	}
    	return false;
    	
    }
    
    /**
     * Gets the amount of ticks on a CAN Talon with a quadrature encoder hooked up to it
     * @param motorWithEncoder - The motor with the quadrature encoder hooked up to it.
     * @return - The current encoder ticks.
     */
    public int getEncoderPos(CANTalon motorWithEncoder){
    	return motorWithEncoder.getEncPosition();
    }
    
    
    /**
     * Resets the ticks on each encoder you send back to 0
     * @param encoder1 - The CAN Talon with the encoder hooked up to it (Ideally, left side motor)
     * @param encoder2 - The CAN Talon with the encoder hooked up to it (Ideally, right side motor)
     */
    public void resetEncoders(){
    	motorFL.setPosition(0);
    	motorFR.setPosition(0);
    }
    
    
	//sets motors on both sides to a left speed and a right speed
	public void setTalonSpeed(double leftSpeed, double rightSpeed){
		motorFL.set(leftSpeed);
		motorBL.set(leftSpeed);
		motorFR.set(rightSpeed);
		motorBR.set(rightSpeed);
	}
	
	/**
	 * Enables break mode on the left side CAN Talon speed controllers
	 */
	public void setLeftBrakes(){
		motorFL.enableBrakeMode(true);
		motorBL.enableBrakeMode(true);
	}
	
	/**
	 * Enables break mode on the right side CAN Talon speed controllers
	 */
	public void setRightBrakes(){
		motorFR.enableBrakeMode(true);
		motorBR.enableBrakeMode(true);
	}
	
	/**
	 * Resets all talons accumulated integral error and temporarily disables them.
	 * POSSIBLY UNNECESSARY?
	 */
	public void reset(){
		motorFL.reset();
		motorFR.reset();
		motorBL.reset();
		motorBR.reset();
	}
	
	/**
	 * Changes all CAN talon speed controllers to PercentVbus mode.  (Changes amount of voltage across 
	 * controller; essentially how fast they move).
	 */
	public void setMode(){
		motorFL.changeControlMode(TalonControlMode.PercentVbus);
		motorBL.changeControlMode(TalonControlMode.PercentVbus);
		motorFR.changeControlMode(TalonControlMode.PercentVbus);
		motorBR.changeControlMode(TalonControlMode.PercentVbus);
		
	}
	
	/**
	 * Gets the gyro's current angle
	 * @return - the current angle of the gyro.
	 */
	public double getGyroAngle(){
		return gyro.getAngle();
	}


    
}

