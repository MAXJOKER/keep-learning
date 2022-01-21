package designpatterns.observer.case2;

import designpatterns.observer.case2.CurrentConditionDisplay;
import designpatterns.observer.case2.WeatherData;

public class Test {
    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();
        CurrentConditionDisplay currentConditionDisplay = new CurrentConditionDisplay(weatherData);
        weatherData.setMeasurements(1, 2, 3);
    }
}
