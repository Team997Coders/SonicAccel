package org.usfirst.frc.team997.robot.subsystems;

import org.usfirst.frc.team997.robot.RobotMap;
import org.usfirst.frc.team997.robot.commands.TankSP;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveTrain extends Subsystem {
    private VictorSP left, right;
//    private Gyro gyro;

    public void initDefaultCommand() {
    	setDefaultCommand(new TankSP());
    	left = new VictorSP(RobotMap.Ports.leftDrive);
    	right = new VictorSP(RobotMap.Ports.rightDrive);
//    	gyro = new AnalogGyro(RobotMap.Ports.gyro);
    }

    public void set(double left, double right) {
    	SmartDashboard.putNumber("Left Raw", -left);
    	SmartDashboard.putNumber("Right Raw", right);
    	this.left.set(-left);
    	this.right.set(right);
    }

//    /** degrees per second */
//    public double getVelocity() {
//    	return gyro.getRate();
//    }
}

