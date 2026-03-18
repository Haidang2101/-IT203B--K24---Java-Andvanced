package factory;

import entity.*;
import engine.Intersection;
import trafficlight.TrafficLight;

import java.util.Random;

public class VehicleFactory {

    private static final Random random = new Random();

    public static Vehicle createVehicle(int id, TrafficLight light, Intersection intersection) {

        if (random.nextInt(10) < 2) {
            return new PriorityVehicle("🚑 Xe cứu thương #" + id, light, intersection);
        }

        return new StandardVehicle("🚗 Xe thường #" + id, light, intersection);
    }
}