package designpatterns.observer.case1;

/**
 * 抽象观察者角色
 */
public interface Observer {
    public void update(float temp, float humidity, float pressure);
}
