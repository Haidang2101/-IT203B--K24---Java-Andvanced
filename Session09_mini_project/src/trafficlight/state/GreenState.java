package trafficlight.state;

import trafficlight.TrafficLight;

public class GreenState implements LightState {

    @Override
    public void next(TrafficLight light) {
        light.setState(new YellowState());
    }

    @Override
    public String getName() {
        return "Xanh";
    }
}
