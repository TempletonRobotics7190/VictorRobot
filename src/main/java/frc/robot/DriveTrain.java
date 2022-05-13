package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class DriveTrain {
  private final WPI_VictorSPX topleft = new WPI_VictorSPX(Constants.TOP_LEFT_MOTOR);
  private final WPI_VictorSPX bottomleft = new WPI_VictorSPX(Constants.BOTTOM_LEFT_MOTOR);
  private final WPI_VictorSPX bottomright = new WPI_VictorSPX(Constants.BOTTOM_RIGHT_MOTOR);
  private final WPI_VictorSPX topright = new WPI_VictorSPX(Constants.TOP_RIGHT_MOTOR);
  private final SpeedControllerGroup leftside = new SpeedControllerGroup(topleft, bottomleft);
  private final SpeedControllerGroup rightside = new SpeedControllerGroup(topright, bottomright);
  private final DifferentialDrive robotDrive
      = new DifferentialDrive(leftside, rightside);
  private final Joystick stick = new Joystick(0);
 
  public void move(double moveY, double moveX) {
      robotDrive.arcadeDrive(moveY, moveX);
  }

  public void moveJoystick() {
    robotDrive.arcadeDrive(-stick.getY(), stick.getX());
  }
}