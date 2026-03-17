package bt4;

public class Main {
    public static void main(String[] args) {
        TemperatureSensor sensor = new TemperatureSensor();
        sensor.attach(new Fan());

        sensor.setTemp(18);
        sensor.setTemp(30);
    }
}