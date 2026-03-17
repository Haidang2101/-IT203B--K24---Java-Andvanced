package bt2;

public class ThermometerAdapter implements TemperatureSensor {
    private OldThermometer old;

    public ThermometerAdapter(OldThermometer old) {
        this.old = old;
    }

    public double getTemperatureCelsius() {
        return (old.getTemperatureFahrenheit() - 32) * 5.0 / 9;
    }
}