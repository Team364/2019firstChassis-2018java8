package frc.team364.robot.commands.auto.drive;

import edu.wpi.first.wpilibj.command.Command;
import frc.team364.robot.Robot;

public class ResetHeading extends Command {


    public ResetHeading() {
        setTimeout(0.1);
    }

    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
        Robot.driveSystem.resetHeading();
    }

    @Override
    protected boolean isFinished() {
        return isTimedOut();
    }

    @Override
    protected void end() {
        Robot.driveSystem.resetEncoders();
        Robot.driveSystem.resetHeading();
        Robot.driveSystem.stop();
        Robot.driveSystem.pidLeft.resetPID();
        Robot.driveSystem.pidRight.resetPID();
        Robot.driveSystem.pidNavX.resetPID();
        Robot.driveSystem.pidNavX.setPIDParameters(0.1, 0.1, 0, 0);
    }

    @Override
    protected void interrupted() {
        super.interrupted();
    }
}
