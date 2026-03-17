package bt4;

import java.util.*;

public class TemperatureSensor implements Subject {
    private List<Observer> list = new ArrayList<>();
    private int temp;

    public void setTemp(int t) {
        this.temp = t;
        notifyObservers();
    }

    public void attach(Observer o) {
        list.add(o);
    }

    public void notifyObservers() {
        for (Observer o : list) {
            o.update(temp);
        }
    }
}