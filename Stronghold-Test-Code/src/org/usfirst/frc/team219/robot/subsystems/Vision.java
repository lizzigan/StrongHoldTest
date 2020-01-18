package org.usfirst.frc.team219.robot.subsystems;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

/**
 *
 */
public class Vision extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	NetworkTable table;
	double[] defaultValue = new double[0];
	public static double[] centerXs;
	public static double[] centerYs;
	public static double[] heights;
	public static double[] widths;
	
	public Vision() {
		table = NetworkTable.getTable("GRIP/myContoursReport");
		getData();
	}
	
	public void getData(){
		centerXs = table.getNumberArray("centerX", defaultValue);
//		centerYs = table.getNumberArray("centerY", defaultValue);
//		heights = table.getNumberArray("height", defaultValue);
//		widths = table.getNumberArray("width", defaultValue);
//		System.out.println(centerXs[0]);
		Timer.delay(.1);
	}
	
	public double getCenterXs() {
		double cX = centerXs[0];
		return cX;
	}

	public double getCenterYs() {
		double cY = centerYs[0];
		return cY;
	}

	public double getHeights() {
		double h = heights[0];
		return h;
	}

	public double getWidths() {
		double w = widths[0];
		return w;
	}
	
	public double getDistanceFromCenter(double x){
		double dist = x;
		return (320-dist);
	}
	
	public double getAngleTo(double d){
		if(getDistanceFromCenter(d) > 0){
			return Math.atan((d/11.0));
		}
		else if(getDistanceFromCenter(d)<0){
			return -Math.atan((d/11.0));
		}
		return 0;
		
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}
