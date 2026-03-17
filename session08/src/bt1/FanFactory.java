package bt1;

public class FanFactory extends DeviceFactory {
    public Device createDevice() {
        return new Fan();
    }
}