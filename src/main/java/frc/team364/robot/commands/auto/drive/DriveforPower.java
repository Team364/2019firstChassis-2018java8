package frc.team364.robot.commands.auto.drive;

import edu.wpi.first.wpilibj.command.Command;
import frc.team364.robot.Robot;


public class DriveforPower extends Command {

    public double power;
    public double timeOut;

    public DriveforPower(double power, double timeOut) {
        requires(Robot.driveSystem);
        setTimeout(timeOut);
    }

    @Override
    protected void initialize() {
        
    }

    @Override
    protected void execute() {
        Robot.driveSystem.driveForPower(power);
    }

    @Override
    protected boolean isFinished() {
        return isTimedOut();
    }

    @Override
    protected void end() {
        Robot.driveSystem.stop();
        Robot.driveSystem.resetHeading();
        System.out.println("Reached target heading." + Robot.driveSystem.getGyroAngle());
    }

    @Override
    protected void interrupted() {
        super.interrupted();
    }
}
