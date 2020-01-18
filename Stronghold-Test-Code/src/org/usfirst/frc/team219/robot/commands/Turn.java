package org.usfirst.frc.team219.robot.commands;

import org.usfirst.frc.team219.robot.subsystems.Direction;

/**
 *
 */
public class Turn extends CommandBase {
	private double targetAngle; //The target angle that the robot is supposed to turn
	private double startAngle; //The robots starting angle, so the robot turns an exact amount relative to its current positioning
	private double endAngle; //The robots desired end angle.  This is calculated by taking the target angle + start angle - trim
	private double trim = 0.8; //This is a P-value for a homemade PID; subtracts trim to get a close-to-exact turn
	private double speed = 0.5; /*
								* Set speed value for turning slow and precise
								* TEST DIFFERENT SPEED VALUES FOR FASTER TURNS (MAY NEED TO ADJUST TRIM)
								*/
	Direction rightOrLeft; //The robots direction, turning either left or right

	/**
	 * Turns the robot a desired amount left or right
	 * @param targetAngle - The desired angle to turn
	 * @param right - Whether or not the robot turns right or left, right = true, left = false
	 */
    public Turn(double targetAngle, Direction d) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(drivetrain);
  		this.targetAngle = targetAngle;
  		rightOrLeft = d;
    	switch(d){
    	case Right:
    		startAngle = drivetrain.getGyroAngle();
    		endAngle = this.targetAngle + startAngle - trim;
    		break;
    	case Left:
    		speed = -speed;
    		endAngle = (startAngle - targetAngle + trim);
    		break;
    	}
    }


	// Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	drivetrain.tankDrive(speed,speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
//    	System.out.println(startAngle);
//    	System.out.println(endAngle);
        return drivetrain.gyroAtAngle(endAngle, rightOrLeft);
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
