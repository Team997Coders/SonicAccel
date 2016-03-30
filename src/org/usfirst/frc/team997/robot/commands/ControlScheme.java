package org.usfirst.frc.team997.robot.commands;

public interface ControlScheme {
	double getLeft(double left, double right);
	double getRight(double left, double right);
}
