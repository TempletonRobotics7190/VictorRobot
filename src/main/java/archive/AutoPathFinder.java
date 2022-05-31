package frc.robot;

import edu.wpi.first.wpilibj.Timer;

public class AutoPathFinder {
    private Robot robot;
    private String state;
    private double longestDist;
    private Timer turnTimer;

    public AutoPathFinder(Robot robot) {
        this.robot = robot;
        this.turnTimer = new Timer();
        this.turnTimer.start();
        this.longestDist = 0.0;
        
    }

    public void init() {
        this.turnTimer.reset();
        this.state = "turning";
        
    }

    public void update() {
        System.out.println(state);
        switch(state) {
            case "turning":
                if (turnTimer.get() < 7.0) {
                    double dist = robot.frontMiddleSensor.getDistance();
                    if (dist > longestDist) {
                        longestDist = dist;
                        System.out.print("BEST DIST: ");
                        System.out.println(robot.frontMiddleSensor.getDistance());
                    }
                    robot.driveTrain.move(0.0, 0.35);
                }
                else {
                    if (longestDist-robot.frontMiddleSensor.getDistance() < 20) {
                        state = "forward";
                        robot.driveTrain.move(0, 0);
                    }
                    else {
                        robot.driveTrain.move(0.0, 0.3);
                        System.out.print("SEARCHING: ");
                        System.out.println(robot.frontMiddleSensor.getDistance());
                    }
                }
                break;
            
            case "forward":
                robot.driveTrain.move(0.3, 0.0);
                if (robot.frontLeftSensor.getDistance() <= 1 || robot.frontRightSensor.getDistance() <= 1) {
                    state = "tweakangle";
                }
                break;
            
            case "tweakangle":
                if (robot.frontLeftSensor.getDistance() <= 1) {
                    robot.driveTrain.move(0.0, 0.3);
                }
                else if (robot.frontRightSensor.getDistance() <= 1) {
                    robot.driveTrain.move(0.0, -0.3);
                }
                else {
                    state = "forward";
                }
                break;
        }
    }
    
}

// amongus