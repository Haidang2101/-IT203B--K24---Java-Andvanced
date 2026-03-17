package bt1;

public class LightFactory extends DeviceFactory {
    public Device createDevice() {
        return new Light();
    }
}