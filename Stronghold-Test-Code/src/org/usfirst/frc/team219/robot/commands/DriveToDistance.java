package org.usfirst.frc.team219.robot.commands;

/**
 *
 */
public class DriveToDistance extends CommandBase {
	private double distanceInInches, totalDistance;
	private double trimInches = 1.5;
	
    public DriveToDistance(double distanceInInches) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(drivetrain);
    	this.distanceInInches = distanceInInches;
    	totalDistance = this.distanceInInches - trimInches;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	drivetrain.resetEncoders();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	drivetrain.autonDrive(.4, -.4);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return drivetrain.isAtDistance(totalDistance);
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
