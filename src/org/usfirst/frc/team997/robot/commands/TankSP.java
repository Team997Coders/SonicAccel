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
    
    private double algorithm(final double current, final DoubleReference last, final String side) {
    	boolean maxedOut = true;
    	double ret = current;
    	if (current == 0) {
    		last.value = 0;
    		maxedOut = false;
    	} else if (current - last.value > maxAccel) {
    		last.value += maxAccel;
    		ret = last.value;
    	} else if (last.value - current > maxAccel) {
    		last.value -= maxAccel;
    		ret = last.value;
    	} else {
    		if ((last.value < 0 && current > 0) || (last.value > 0 && current < 0)) {
    			maxedOut = true;
    		} else {
    			maxedOut = false;
    		}
    		last.value = current;
    	}
		SmartDashboard.putBoolean("Max out " + side, maxedOut);
    	return ret;
    }

    protected void initialize() {}
    protected void execute() {
    	double left = controlMap.getLeft(Robot.oi.controller);
    	double right = controlMap.getRight(Robot.oi.controller);

    	SmartDashboard.putNumber("Input Left", left);
    	SmartDashboard.putNumber("Input Right", right);

    	DoubleReference lastL = new DoubleReference(lastLeft);
    	left = algorithm(left, lastL, "Left");
    	lastLeft = lastL.value;
    	DoubleReference lastR = new DoubleReference(lastRight);
    	right = algorithm(right, lastR, "Right");
    	lastRight = lastR.value;

    	Robot.driveTrain.set(left, right);
    }
    protected boolean isFinished() {return false;}
    protected void end() {}
    protected void interrupted() {}
}
