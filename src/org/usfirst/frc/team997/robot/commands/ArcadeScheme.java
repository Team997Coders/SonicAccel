package org.usfirst.frc.team997.robot.commands;

public class ArcadeScheme implements ControlScheme {
	public double getLeft(double left, double right) {
		return left - right;
	}

	public double getRight(double left, double right) {
		return left + right;
	}
}
