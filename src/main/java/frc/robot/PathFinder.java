package frc.robot;

import edu.wpi.first.wpilibj.Timer;
import frc.robot.sensors.LaserSensor;
import frc.robot.sensors.Sensor;
import frc.robot.sensors.UltrasonicSensor;

public class PathFinder {
    private DriveTrain driveTrain;

    private Sensor leftSensor;
    private Sensor rightSensor;
    private Sensor middleSensor;

    private String state;

    private double longestDist;
    private Timer turnTimer;

    public PathFinder(DriveTrain driveTrain) {
        this.driveTrain = driveTrain;
        this.turnTimer = new Timer();
        this.turnTimer.start();
        
        
    }

    public void init() {
        this.leftSensor = new UltrasonicSensor(0);
        this.rightSensor = new UltrasonicSensor(1);
        this.middleSensor = new LaserSensor(0);
        this.turnTimer.reset();
        this.state = "turning";
        
    }

    public void update() {
        System.out.println(state);
        switch(state) {
            case "turning":
                if (turnTimer.get() < 7.0) {
                    double dist = middleSensor.getDistance();
                    if (dist > longestDist) {
                        longestDist = dist;
                        System.out.print("BEST DIST: ");
                        System.out.println(middleSensor.getDistance());
                    }
                    driveTrain.move(0.0, 0.35);
                }
                else {
                    if (this.longestDist-middleSensor.getDistance() < 20) {
                        state = "forward";
                        driveTrain.move(0, 0);
                    }
                    else {
                        driveTrain.move(0.0, 0.3);
                        System.out.print("SEARCHING: ");
                        System.out.println(middleSensor.getDistance());
                    }
                }
                break;
            
            case "forward":
                driveTrain.move(0.3, 0.0);
                if (leftSensor.getDistance() <= 1 || rightSensor.getDistance() <= 1) {
                    state = "tweakangle";
                }
                break;
            
            case "tweakangle":
                if (leftSensor.getDistance() <= 1) {
                    driveTrain.move(0.0, 0.3);
                }
                else if (rightSensor.getDistance() <= 1) {
                    driveTrain.move(0.0, -0.3);
                }
                else {
                    state = "forward";
                }
                break;
        }
    }
    
}

// amongus