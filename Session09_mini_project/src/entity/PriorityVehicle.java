package entity;

import engine.Intersection;
import trafficlight.TrafficLight;

public class PriorityVehicle extends Vehicle {

    public PriorityVehicle(String name, TrafficLight light, Intersection intersection) {
        super(name, 2, light, intersection);
    }

    @Override
    public boolean isPriority() {
        return true;
    }
}