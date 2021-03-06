package frc.robot;

import edu.wpi.first.wpilibj.Timer;

public class AutoPathFinder {

    private Robot robot;
    private String state;
    private Timer searchingTimer;

    public AutoPathFinder(Robot robot) {
        this.robot = robot;
        this.searchingTimer = new Timer();
        
    }

    public void init() {
        state = "forward";
        this.searchingTimer.start();
        
    }

    public void update() {
        System.out.println(state);
        double leftDist = robot.frontLeftSensor.getDistance();
        double rightDist = robot.frontRightSensor.getDistance();
        double middleDist = robot.frontMiddleSensor.getDistance();
        switch(state) {
            case "forward":
                if (leftDist < 1 || rightDist < 1 || middleDist < 50) {
                    robot.driveTrain.move(0.0, 0.0);
                    state = "backward";
                }
                else {
                    robot.driveTrain.move(Constants.AUTO_Y_SPEED, 0.0);
                }
                break;
            case "backward":
                if (leftDist < 1 || rightDist < 1 || middleDist < 50) {
                    robot.driveTrain.move(-Constants.AUTO_Y_SPEED, 0.0);
                }
                else {
                    robot.driveTrain.move(0.0, 0.0);
                    state = "searching";
                    searchingTimer.reset();
                }
                break;
            case "searching":
                if (searchingTimer.get() < 5) {
                    robot.driveTrain.move(0.0, Constants.AUTO_X_SPEED);
                }
                else {
                    robot.driveTrain.move(0.0, 0.0);
                    state = "forward";
                }
                break;
        }
    }
    
}

// amongus