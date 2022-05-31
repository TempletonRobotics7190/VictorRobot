
package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import frc.robot.sensors.LaserSensor;
import frc.robot.sensors.Sensor;
import frc.robot.sensors.UltrasonicSensor;

public class Robot extends TimedRobot {
  Sensor frontLeftSensor;
  Sensor frontRightSensor;
  Sensor frontMiddleSensor;
  Sensor backMiddleSensor;

  DriveTrain driveTrain = new DriveTrain();
  AutoPathFinder pathFinder;
  


  @Override
  public void robotInit() {
    this.frontLeftSensor = new UltrasonicSensor(0);
    this.frontRightSensor = new UltrasonicSensor(1);
    this.frontMiddleSensor = new LaserSensor(0);
    this.backMiddleSensor = new UltrasonicSensor(2);
    this.pathFinder = new AutoPathFinder(this);
  }

  @Override
  public void robotPeriodic() {
    // System.out.println("frontleft: " + Double.toString(this.frontLeftSensor.getDistance()));
    // System.out.println("frontmiddle: " + Double.toString(this.frontMiddleSensor.getDistance()));
    // System.out.println("frontright: " + Double.toString(this.frontRightSensor.getDistance()));
    // System.out.println("backmiddle: " + Double.toString(this.backMiddleSensor.getDistance()));
  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void autonomousInit() {
    pathFinder.init();
  }

  @Override
  public void autonomousPeriodic() {
    pathFinder.update();
  }

  @Override
  public void teleopInit() {
   
  }

  @Override
  public void teleopPeriodic() {
    if (driveTrain.stick.getY() > 0) {
      boolean stop = false;
      if (frontLeftSensor.getDistance() < 1) {
        stop = true; 
      }
      if (frontRightSensor.getDistance() < 1) {
        stop = true;
      }
      if (frontMiddleSensor.getDistance() < 50) {
        stop = true;
      }
      if (stop) {
        driveTrain.moveJoystickX();
        System.out.println("stopping forward");
      }
      else {
        driveTrain.moveJoystick();
      }
    }
    else if (driveTrain.stick.getY() < 0) {
      if (backMiddleSensor.getDistance() < 1) {
        System.out.println("stopping backward");
        driveTrain.moveJoystickX();
      }
      else {
        driveTrain.moveJoystick();
      }
    }
    else {
      driveTrain.move(0.0, 0.0);
    }
  }

  @Override
  public void testInit() {
  }

  @Override
  public void testPeriodic() {}
}
