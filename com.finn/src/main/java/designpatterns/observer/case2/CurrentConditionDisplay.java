package designpatterns.observer.case2;

import designpatterns.observer.case1.DisplayElement;

import java.util.Observable;
import java.util.Observer;

/**
 * 具体观察者
 */
public class CurrentConditionDisplay implements Observer, DisplayElement{
    private float temperature;
    private float humidity;
    private Observable weatherData;

    public CurrentConditionDisplay(Observable weatherData) {
        this.weatherData = weatherData;
        weatherData.addObserver(this);
    }

    public void display() {
        System.out.println("Current condition: \nTemperature: " + temperature + "\nHumidity: " + humidity);
    }

    public void update(float temp, float humidity, float pressure) {
        this.temperature = temp;
        this.humidity = humidity;
        display();
    }

    public void update(Observable o, Object arg) {
        if (o instanceof WeatherData) {
            // 拉数据
            this.temperature = ((WeatherData) o).getTemperature();
            this.humidity = ((WeatherData) o).getHumidity();
            display();
        }
    }
}
