package bt2;

public class Main {
    public static void main(String[] args) {
        OldThermometer old = new OldThermometer();
        TemperatureSensor adapter = new ThermometerAdapter(old);

        SmartHomeFacade facade = new SmartHomeFacade();
        facade.showTemp(adapter);
        facade.leaveHome();
        facade.sleepMode();
    }
}