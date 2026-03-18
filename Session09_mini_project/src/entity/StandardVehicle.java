package entity;

import engine.Intersection;
import trafficlight.TrafficLight;

public class StandardVehicle extends Vehicle {

    public StandardVehicle(String name, TrafficLight light, Intersection intersection) {
        super(name, 1, light, intersection);
    }

    @Override
    public boolean isPriority() {
        return false;
    }
}
