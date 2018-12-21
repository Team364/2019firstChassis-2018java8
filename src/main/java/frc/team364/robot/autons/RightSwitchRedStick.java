package frc.team364.robot.autons;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team364.robot.commands.auto.drive.*;
import frc.team364.robot.commands.auto.misc.*;

public class RightSwitchRedStick extends CommandGroup {

    public RightSwitchRedStick() {
       addSequential(new DriveStraightForCounts(1500, false, false));//1
    }
}
