package designpatterns.observer.case2;

import designpatterns.observer.case1.Observer;
import designpatterns.observer.case1.Subject;

import java.util.ArrayList;
import java.util.Observable;

/**
 * 具体主题角色
 */
public class WeatherData extends Observable {

    private float temperature;

    private float humidity;

    private float pressure;

    public WeatherData() {}

    public void measurementsChanged() {
        setChanged();
        notifyObservers();
    }

    public void setMeasurements(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementsChanged();
    }

    // 如果观察者是通过pull 拉数据，就需要调用
    public float getTemperature() {
        return temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public float getPressure() {
        return pressure;
    }
}
