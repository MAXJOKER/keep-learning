package designpatterns.observer.case1;

import java.util.ArrayList;

/**
 * 具体主题角色
 */
public class WeatherData implements Subject {

    private ArrayList observers;

    private float temperature;

    private float humidity;

    private float pressure;

    public WeatherData() {
        observers = new ArrayList();
    }

    public void registerObserver(Observer ob) {
        observers.add(ob);
    }

    public void removeObserver(Observer ob) {
        int index = observers.indexOf(ob);
        if (index >= 0) {
            observers.remove(ob);
        }
    }

    public void notifyObservers() {
        for (int i = 0; i < observers.size(); i++) {
            Observer ob = (Observer) observers.get(i);
            ob.update(temperature, humidity, pressure);
        }
    }

    public void measurementsChanged() {
        notifyObservers();
    }

    public void setMeasurements(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementsChanged();
    }
}
