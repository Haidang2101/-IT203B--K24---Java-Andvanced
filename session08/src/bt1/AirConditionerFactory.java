package bt1;

public class AirConditionerFactory extends DeviceFactory {
    public Device createDevice() {
        return new AirConditioner();
    }
}