package frc.robot.sensors;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.math.filter.MedianFilter;

public class UltrasonicSensor implements Sensor {
    private final MedianFilter filter = new MedianFilter(10);
    private final AnalogInput analogInput;

    public UltrasonicSensor(int port) {
        analogInput = new AnalogInput(port);
    }

    public double getDistance() {
        return filter.calculate(analogInput.getValue()-236);
    }
}
