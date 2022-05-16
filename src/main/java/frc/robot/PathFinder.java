package frc.robot;

import frc.robot.sensors.LaserSensor;
import frc.robot.sensors.Sensor;
import frc.robot.sensors.UltrasonicSensor;

public class PathFinder {
    private DriveTrain driveTrain;

    private Sensor leftSensor;
    private Sensor rightSensor;
    private Sensor middleSensor;

    private String state = "start";

    public PathFinder(DriveTrain driveTrain) {
        this.driveTrain = driveTrain;
    }

    public void init() {
        leftSensor = new UltrasonicSensor(0);
        rightSensor = new UltrasonicSensor(1);
        middleSensor = new LaserSensor(0);
    }

    public void update() {
        System.out.println(Double.toString(middleSensor.getDistance()) + state);
        switch(state) {
            case "start":
                if (middleSensor.getDistance() < 150) {
                    state = "turn";
                    break;
                }
                driveTrain.move(0.3, 0.0);
                break;
            
            case "turn":
                if (middleSensor.getDistance() > 300) {
                    state = "start";
                    break;
                }
                driveTrain.move(0.0, 0.4);
                break;
        }
    }
    
}

// amongus