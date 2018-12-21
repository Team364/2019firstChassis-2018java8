package frc.team364.robot.commands.auto.drive;

import edu.wpi.first.wpilibj.command.Command;
import frc.team364.robot.Robot;


public class StopMotors extends Command {


    public StopMotors() {
        requires(Robot.driveSystem);
        setTimeout(0.1);
    }

    @Override
    protected void initialize() {
        
    }

    @Override
    protected void execute() {
        Robot.driveSystem.stop();
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
