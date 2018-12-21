package frc.team364.robot.commands.teleop;

import edu.wpi.first.wpilibj.command.Command;
import frc.team364.robot.Robot;


public class RampDown extends Command {

    public boolean cancelRamp;
    public double rightControllerInput;
    public double leftControllerInput;

    public RampDown() {
        requires(Robot.driveSystem);
        setTimeout(2);
    }

    @Override
    protected void initialize() {
        cancelRamp = false;
        Robot.driveSystem.pidRampDown.setPIDParameters(-0.00000001, 0, 0, 0); // Robot.driveSystem.pidNavX.setPIDParameters(0.05, 0.01, 0, 0);
        Robot.driveSystem.stop();
        Robot.driveSystem.resetHeading();
        Robot.driveSystem.pidRampDown.resetPID();
        Robot.driveSystem.pidLeft.resetPID();
        Robot.driveSystem.pidRight.resetPID();
    }

    @Override
    protected void execute() {
        rightControllerInput =  Robot.oi.driverController.getRawAxis(1);
        leftControllerInput = Robot.oi.driverController.getRawAxis(5);
      if((Math.abs(rightControllerInput) >= 0.1 || (Math.abs(leftControllerInput)) >=0.1)){
          cancelRamp = true;
      }
        Robot.driveSystem.rampDown();
    }

    @Override
    protected boolean isFinished() {

        return cancelRamp || isTimedOut();//Robot.driveSystem.reachedSmoothStop() ||
    }

    @Override
    protected void end() {
        Robot.driveSystem.stop();
        Robot.driveSystem.resetHeading();
    }

    @Override
    protected void interrupted() {
        super.interrupted();
    }
}
