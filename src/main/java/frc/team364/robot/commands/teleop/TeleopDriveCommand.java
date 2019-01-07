/*
 *  George and Keanu:
 *  This is the TeleopDriveCommand. This runs whenever there isn't another command
 *  running that requries the DriveSystem class. In Execute, the drive motors are
 *  given an output from the joysticks using a variable in the OI (Operator Interface) class.
 *  The shifters are also set using the triggers from each joystick.
 */

package frc.team364.robot.commands.teleop;

import edu.wpi.first.wpilibj.command.Command;
import frc.team364.robot.Robot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TeleopDriveCommand extends Command {

    public double leftControllerInput;
    public double rightControllerInput;
    public static Command RampDown;
    public boolean rampDownSequence;
    public boolean forward;
    public double leftVelocity;
    public double rightVelocity;
    static enum DriveStates {STATE_NOT_MOVING, STATE_DIRECT_DRIVE, STATE_RAMP_DOWN, STATE_DRIVE_STRAIGHT}
    public DriveStates driveState;
    public double tankLeft;
    public double tankRight;
    public boolean CancelRamp;
    /**
     * Command used for teleop control specific to the drive system
     */
    public TeleopDriveCommand() {
        requires(Robot.driveSystem);
        RampDown = new RampDown();
        CancelRamp = false;
    }

    @Override
    protected void initialize() {
        driveState = DriveStates.STATE_NOT_MOVING;
        rampDownSequence = false;

    }

    @Override
    protected void end() {
        // This will probably never be called.
        Robot.driveSystem.stop();
    }

    @Override
    protected void execute() {
        rightControllerInput = Robot.oi.driverController.getRawAxis(1);
        leftControllerInput = Robot.oi.driverController.getRawAxis(5);
        rightVelocity = Robot.driveSystem.rightRear.getSelectedSensorVelocity(0);
        leftVelocity = Robot.driveSystem.leftRear.getSelectedSensorVelocity(0);
        SmartDashboard.putBoolean("RampDown: ", rampDownSequence);
        SmartDashboard.putData("RampDownStatus: ", RampDown);
        SmartDashboard.putNumber("Velocity: ", Robot.driveSystem.leftRear.getSelectedSensorVelocity(0));// Velocity in
                                                                                                        // feet

        // normal tank drive control

        if (driveState == DriveStates.STATE_NOT_MOVING) {
            tankLeft = 0;
            tankRight = 0;
            
            if(Robot.oi.driverController.getRawButton(10)){
                System.out.println("STATE_NOT_MOVING->STATE_DRIVE_STRAIGHT");
                driveState = DriveStates.STATE_DRIVE_STRAIGHT;
            } else if ((Math.abs(leftControllerInput) >= 0.25) || (Math.abs(rightControllerInput) >= 0.25)) {
                System.out.println("STATE_NOT_MOVING->STATE_DIRECT_DRIVE");
                driveState = DriveStates.STATE_DIRECT_DRIVE;
            }

        } else if (driveState == DriveStates.STATE_DIRECT_DRIVE) {
            tankLeft = leftControllerInput;
            tankRight = rightControllerInput;
            if ((Math.abs(leftControllerInput) < 0.2) && (Math.abs(rightControllerInput) < 0.2)) {
                System.out.println("STATE_DIRECT_DRIVE->STATE_RAMP_DOWN");
                driveState = DriveStates.STATE_RAMP_DOWN;
            }

        } else if (driveState == DriveStates.STATE_RAMP_DOWN) {
            RampDown.start();
            if ((Math.abs(leftControllerInput) > 0.25) && (Math.abs(rightControllerInput) > 0.25)) {
                driveState = DriveStates.STATE_DIRECT_DRIVE;
                System.out.println("STATE_RAMP_DOWN->STATE_DIRECT_DRIVE");
            } else if (Robot.driveSystem.leftRear.getMotorOutputPercent() <= 0.1) {
                driveState = DriveStates.STATE_NOT_MOVING;
                System.out.println("STATE_RAMP_DOWN->STATE_NOT_MOVING");
            }
            
        }
        else if (driveState == DriveStates.STATE_DRIVE_STRAIGHT) {
            tankLeft = leftControllerInput;
            tankRight = leftControllerInput;
            if (!(Robot.oi.driverController.getRawButton(10))) {
                System.out.println("STATE_DIRECT_DRIVE->STATE_RAMP_DOWN");
                driveState = DriveStates.STATE_RAMP_DOWN;
            }
            
        } else {
            // This condition should never happen!
            driveState = DriveStates.STATE_NOT_MOVING;
        }

        Robot.driveSystem.tankDrive(tankLeft, tankRight);
        /*//Executing ramping down command
        if(rampDownSequence){
            if((leftControllerInput <= 0.5) && (rightControllerInput <= 0.5) && (Math.abs(leftControllerInput) <= 0.5)){
                RampDown.start();
                forward = true;
            } else if((leftControllerInput <= -0.5) && (rightControllerInput <= -0.5) && (Math.abs(leftControllerInput) <= 0.5)){
                RampDown.start();
                forward = false;
            } else{
                // RampDown.cancel();
            }
        } else if((Math.abs(leftControllerInput) >= 0.5) && (Math.abs(rightControllerInput) >= 0.5)){
            rampDownSequence = true;
        }
        //These will turn off the sequence before it is attempted to be executed
        //IF turning, deactivate sequence
        if(Math.abs(leftControllerInput - rightControllerInput) >= 0.3){
            rampDownSequence = false;
        }*/

        if (Robot.oi.shiftHigh.get()) {
            Robot.driveSystem.shiftHigh();
        } else if (Robot.oi.shiftLow.get()) {
            Robot.driveSystem.shiftLow();
        } else {
            Robot.driveSystem.noShiftInput();
        }

        SmartDashboard.putNumber("GetLeftRear: ", Robot.driveSystem.leftRear.getMotorOutputPercent());
        SmartDashboard.putNumber("GetRightRear: ", Robot.driveSystem.rightRear.getMotorOutputPercent());
        SmartDashboard.putNumber("GetLeftContr: ", leftControllerInput);
        SmartDashboard.putNumber("GetRightContr: ", rightControllerInput);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

}
