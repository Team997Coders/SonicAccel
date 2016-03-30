
package org.usfirst.frc.team997.robot;

import org.usfirst.frc.team997.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class Robot extends IterativeRobot {

	public static DriveTrain driveTrain = new DriveTrain();
	public static OI oi;

	public void robotInit() {
		oi = new OI();
    }

	public void disabledInit() {}
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

    public void autonomousInit() {}

    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
    }

    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }
    
    public void testPeriodic() {
        LiveWindow.run();
    }

	public static double deadband(double rightRawY) {
		if (Math.abs(rightRawY) < 0.1) {
			return 0;
		} else {
			return rightRawY;
		}
	}
}
