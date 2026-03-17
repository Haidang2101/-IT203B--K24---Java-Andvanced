package bt1;

public class Main {
    public static void main(String[] args) {
        HardwareConnection.getInstance();

        DeviceFactory factory = new LightFactory();
        Device d = factory.createDevice();
        d.turnOn();
        d.turnOff();
    }
}