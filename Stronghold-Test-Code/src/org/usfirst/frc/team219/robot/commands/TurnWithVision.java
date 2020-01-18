package org.usfirst.frc.team219.robot.commands;

import org.usfirst.frc.team219.robot.subsystems.Direction;
import org.usfirst.frc.team219.robot.subsystems.Vision;

/**
 *
 */
public class TurnWithVision extends CommandBase {

	private double angle;
	private Direction d;
	private double speed = .2;
	
    public TurnWithVision() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(vision);
    }

    // Called just before this Command runs the first time
    protected void initialize() {

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	vision.getData();
    	angle = vision.getAngleTo(vision.getDistanceFromCenter(Vision.centerXs[0]));
    	System.out.println("ANGLE: "+angle);
    	if(angle > 0 )
    		d = Direction.Right;
    	else if(angle < 0)
    		d = Direction.Left;
    	drivetrain.tankDrive(speed, speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return drivetrain.gyroAtAngle(Math.abs(angle), d);
    }

    // Called once after isFinished returns true
    protected void end() {
    	drivetrain.tankDrive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
