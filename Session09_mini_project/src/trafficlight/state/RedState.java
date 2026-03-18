package trafficlight.state;

import trafficlight.TrafficLight;

public class RedState implements LightState {

    @Override
    public void next(TrafficLight light) {
        light.setState(new GreenState());
    }

    @Override
    public String getName() {
        return "Đỏ";
    }
}