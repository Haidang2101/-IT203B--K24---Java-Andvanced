package bt2;

public class SmartHomeFacade {
    public void leaveHome() {
        System.out.println("Tắt đèn, quạt, điều hòa");
    }

    public void sleepMode() {
        System.out.println("Điều hòa 28°C, quạt thấp");
    }

    public void showTemp(TemperatureSensor sensor) {
        System.out.println("Nhiệt độ: " + sensor.getTemperatureCelsius());
    }
}