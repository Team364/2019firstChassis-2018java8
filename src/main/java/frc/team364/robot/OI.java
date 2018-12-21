package frc.team364.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {

    public Joystick operationStation;
    public Joystick controller;
    public Joystick driverController;
    
    public JoystickButton autoSelectorButton;

    public JoystickButton shiftLow;
    public JoystickButton shiftHigh;

    public OI() {

        controller = new Joystick(0);
        driverController = new Joystick(1);
        operationStation = new Joystick(2); ///Changing Main Buttons to Gamepad--all thats left is the auto switch and it's literally in a cardboard box

        shiftLow = new JoystickButton(driverController, 5);
        shiftHigh = new JoystickButton(driverController, 6);
        autoSelectorButton = new JoystickButton(operationStation, 10);
       // secondClawButton = new JoystickButton(leftStick, 11);

    }
}
