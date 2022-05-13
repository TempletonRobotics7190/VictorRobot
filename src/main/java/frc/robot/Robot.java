
package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;

public class Robot extends TimedRobot {
  

  DriveTrain driveTrain = new DriveTrain();
  PathFinder pathFinder = new PathFinder(driveTrain);


  @Override
  public void robotInit() {
    pathFinder.init();
  }

  @Override
  public void robotPeriodic() {
    // System.out.println(middleSensor.getDistance());
  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void autonomousInit() {
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
    driveTrain.moveJoystick();
  }

  @Override
  public void testInit() {
  }

  @Override
  public void testPeriodic() {}
}
