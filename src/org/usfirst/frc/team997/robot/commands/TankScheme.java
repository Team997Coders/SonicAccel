package org.usfirst.frc.team997.robot.commands;

public class TankScheme implements ControlScheme {
	public double getLeft(double left, double right) {
		return left;
	}

	public double getRight(double left, double right) {
		return right;
	}
}
