package first;

import org.wpilib.command3.button.CommandNiDsPS5Controller;
import org.wpilib.opmode.OpMode;
import org.wpilib.opmode.Teleop;

import first.mechanisms.Mechanisms;
import first.robot.Robot;

@Teleop(name="Drive", group = "Drive")
public class DriveTeleop implements OpMode {
    
    public DriveTeleop(Robot robot) {
        
        robot.getDriveController().square().whileTrue(Mechanisms.spindexer().spin());
    }
}
