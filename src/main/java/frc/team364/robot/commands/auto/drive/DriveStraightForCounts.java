package frc.team364.robot.commands.auto.drive;

import edu.wpi.first.wpilibj.command.Command;
import frc.team364.robot.Robot;

public class DriveStraightForCounts extends Command {

    private int driveCounts;
    private boolean driveBackwards;
    private boolean driveWithGyro;

    public DriveStraightForCounts(int counts, boolean backwards, boolean useGyro) {
        requires(Robot.driveSystem);
        driveCounts = counts;
        driveBackwards = backwards;
        driveWithGyro = useGyro;
        System.out.println("Backwards: " + backwards);
        setTimeout(5);
        Robot.driveSystem.resetEncoders();
        Robot.driveSystem.resetHeading();
        Robot.driveSystem.stop();
        Robot.driveSystem.pidLeft.resetPID();
        Robot.driveSystem.pidRight.resetPID();
        Robot.driveSystem.pidNavX.resetPID();
        Robot.driveSystem.pidNavX.setPIDParameters(0.1, 0.1, 0, 0); //Robot.driveSystem.pidNavX.setPIDParameters(0.1, 0.1, 0, 0);
    }

    @Override
    protected void initialize() {
        Robot.driveSystem.resetEncoders();
        Robot.driveSystem.resetHeading();
        Robot.driveSystem.stop();
        Robot.driveSystem.pidLeft.resetPID();
        Robot.driveSystem.pidRight.resetPID();
        Robot.driveSystem.pidNavX.resetPID();
        Robot.driveSystem.pidNavX.setPIDParameters(0.1, 0.1, 0, 0);
    }

    @Override
    protected void execute() {
        Robot.driveSystem.driveStraightToEncoderCounts(driveCounts, driveBackwards, driveWithGyro);
    }

    @Override
    protected boolean isFinished() {
        return Robot.driveSystem.withinEncoderCountRange(driveCounts) || isTimedOut();
    }

    @Override
    protected void end() {
        Robot.driveSystem.resetEncoders();
        Robot.driveSystem.resetHeading();
        Robot.driveSystem.stop();
        System.out.println("Reached target distance.\nWanted: " + driveCounts + "Actual: " + Robot.driveSystem.getLeftEncoderPosition());
    }

    @Override
    protected void interrupted() {
        super.interrupted();
    }
}
