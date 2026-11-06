// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package first.robot;

import org.wpilib.command3.Scheduler;
import org.wpilib.command3.button.CommandNiDsPS5Controller;
import org.wpilib.epilogue.Epilogue;
import org.wpilib.epilogue.Logged;
import org.wpilib.framework.OpModeRobot;

import first.Constants;
import first.mechanisms.Mechanisms;

@Logged
public class Robot extends OpModeRobot {
  private final CommandNiDsPS5Controller driveController;

  public Robot() {
    driveController = new CommandNiDsPS5Controller(Constants.ControllerConstants.DRIVE_PORT);
    Mechanisms.init();
  }

  public CommandNiDsPS5Controller getDriveController() {
    return driveController;
  }

  @Override
  public void robotPeriodic() {
    Scheduler.getDefault().run();
    Epilogue.update(this);
    Mechanisms.drivetrain().periodic();
  }
}
