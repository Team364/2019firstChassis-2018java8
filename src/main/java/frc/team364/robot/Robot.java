package frc.team364.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team364.robot.subsystems.*;

public class Robot extends TimedRobot {

    public static DriveSystem driveSystem;

    public static OI oi;

    public UsbCamera camera;

    /**
     * robotInit()
     * Note the setPeriod(0.05) function. This is a function with the
     * TimedRobot class that sets the robot loop period (50ms in this case).
     * This will allow the motion profiling code to run at a constant rate without
     * fluctuation. 
     */
	@Override
    public void robotInit() {
        setPeriod(0.02);
	    driveSystem = new DriveSystem();
	    oi = new OI();
        camera = CameraServer.getInstance().startAutomaticCapture("Video", 0);
        camera.setResolution(320, 240);
        driveSystem.resetEncoders();
        driveSystem.resetHeading();
    }

    @Override
    public void disabledInit() { }

    @Override
    public void autonomousInit() {
        Scheduler.getInstance().removeAll();
        driveSystem.resetHeading();
        driveSystem.resetEncoders();
    }

    @Override
    public void autonomousPeriodic() {
        putSmartDashVars();
        Scheduler.getInstance().run();
    }

    @Override
    public void teleopInit() {
        Scheduler.getInstance().removeAll();
        driveSystem.leftTop.configOpenloopRamp(0, 0);
        driveSystem.rightTop.configOpenloopRamp(0, 0); 
    }

    @Override
    public void testInit() { 

    }

    @Override
    public void disabledPeriodic() {
        putSmartDashVars();
    }

    @Override
    public void teleopPeriodic() {
	    Scheduler.getInstance().run();
        putSmartDashVars();
    }

    @Override
    public void testPeriodic() { 

    }

    private void putSmartDashVars() {
        SmartDashboard.putNumber("Gyro Angle", driveSystem.getGyroAngle());
    }
}
