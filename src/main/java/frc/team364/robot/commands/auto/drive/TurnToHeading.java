package frc.team364.robot.commands.auto.drive;

import edu.wpi.first.wpilibj.command.Command;
import frc.team364.robot.Robot;


public class TurnToHeading extends Command {

    private double wantedHeading;

    public TurnToHeading(double heading) {
        requires(Robot.driveSystem);
        wantedHeading = heading;
        setTimeout(2);
    }

    @Override
    protected void initialize() {
        Robot.driveSystem.pidNavX.setPIDParameters(1, 0.01, 0, 1); // Robot.driveSystem.pidNavX.setPIDParameters(0.05, 0.01, 0, 0);
        Robot.driveSystem.stop();
        Robot.driveSystem.resetHeading();
        Robot.driveSystem.pidNavX.resetPID();
        Robot.driveSystem.pidLeft.resetPID();
        Robot.driveSystem.pidRight.resetPID();
    }

    @Override
    protected void execute() {
        Robot.driveSystem.turnToHeading(wantedHeading);
    }

    @Override
    protected boolean isFinished() {
        return Robot.driveSystem.reachedHeading(wantedHeading) || isTimedOut();
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
