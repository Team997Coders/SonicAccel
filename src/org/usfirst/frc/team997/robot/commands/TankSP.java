package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class TankSP extends Command {
	private static final double maxAccel = .025;
	
	private double lastLeft = 0, lastRight = 0;
	
	private ControlScheme controlMap = new TankScheme();

    public TankSP() {
    	requires(Robot.driveTrain);
    }
    
    protected void initialize() {}
    protected void execute() {
    	double l = Robot.deadband(Robot.oi.controller.getLeftRawY());
    	double r = Robot.deadband(Robot.oi.controller.getRightRawY/*getRightRawX*/());
    	double left = controlMap.getLeft(l, r);
    	double right = controlMap.getRight(l, r);

    	SmartDashboard.putNumber("Input Left", left);
    	SmartDashboard.putNumber("Input Right", right);

    	if (left == 0) {
    		lastLeft = 0;
    		SmartDashboard.putBoolean("Max out Left", false);
    	} else if (left - lastLeft > maxAccel) {
    		lastLeft += maxAccel;
    		left = lastLeft;
    		SmartDashboard.putBoolean("Max out Left", true);
    	} else if (lastLeft - left > maxAccel) {
    		lastLeft -= maxAccel;
    		left = lastLeft;
    		SmartDashboard.putBoolean("Max out Left", true);
    	} else if ((lastLeft < 0 && left > 0) || (lastLeft > 0 && left < 0)) {
    		lastLeft = left;
    		SmartDashboard.putBoolean("Max out Left", true);
    	} else {
    		lastLeft = left;
    		SmartDashboard.putBoolean("Max out Left", false);
    	}
    	if (right == 0) {
    		lastRight = 0;
    		SmartDashboard.putBoolean("Max out Right", false);
    	} else if (right - lastRight > maxAccel) {
    		lastRight += maxAccel;
    		right = lastRight;
    		SmartDashboard.putBoolean("Max out Right", true);
    	} else if (lastRight - right> maxAccel) {
    		lastRight -= maxAccel;
    		right = lastRight;
    		SmartDashboard.putBoolean("Max out Right", true);
    	} else if ((lastRight < 0 && right > 0) || (lastRight > 0 && right < 0)) {
    		lastRight = right;
    		SmartDashboard.putBoolean("Max out Right", true);
    	} else {
    		lastRight = right;
    		SmartDashboard.putBoolean("Max out Right", false);
    	}
    	Robot.driveTrain.set(left, right);
    }
    protected boolean isFinished() {return false;}
    protected void end() {}
    protected void interrupted() {}
}
