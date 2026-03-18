package trafficlight.state;

import trafficlight.TrafficLight;

public interface LightState {
    void next(TrafficLight light);
    String getName();
}
