package trafficlight.state;

import trafficlight.TrafficLight;

public class YellowState implements LightState {

    @Override
    public void next(TrafficLight light) {
        light.setState(new RedState());
    }

    @Override
    public String getName() {
        return "Vàng";
    }
}