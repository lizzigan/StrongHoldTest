package org.usfirst.frc.team219.robot.commands;

import org.usfirst.frc.team219.robot.OI;
import org.usfirst.frc.team219.robot.subsystems.Arm;
import org.usfirst.frc.team219.robot.subsystems.DriveTrain;
import org.usfirst.frc.team219.robot.subsystems.Vision;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The base for all commands. All atomic commands should subclass CommandBase.
 * CommandBase stores creates and stores each control system. To access a
 * subsystem elsewhere in your code in your code use CommandBase.exampleSubsystem
 * @author Mark Szewczuk, Jace Beaudoin, Justin Westley, Jason Tran
 */
public abstract class CommandBase extends Command {
	public static OI oi;
	//Create your subsystem declarations in this class
	//ex: public static DriveTrain drive = new DriveTrain();
	public static DriveTrain drivetrain = new DriveTrain(27);
//	public static Shooter shooter = new Shooter();
	public static Arm arm = new Arm();
	public static Vision vision = new Vision();

	
	
	public static void init(){
        // This MUST be here. If the OI creates Commands (which it very likely
        // will), constructing it during the construction of CommandBase (from
        // which commands extend), subsystems are not guaranteed to be
        // yet. Thus, their requires() statements may grab null pointers. Bad
        // news. Don't move it.
		oi = new OI();
		
		//Show what command your subsytem is running on the SmartDashboard
		SmartDashboard.putData("SchedularData",Scheduler.getInstance());
	}
	
	public CommandBase(String name){
		super(name);
	}
	
	public CommandBase(){
		super();
	}
}
