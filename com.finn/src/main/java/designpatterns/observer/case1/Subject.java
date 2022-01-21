package designpatterns.observer.case1;

/**
 * 抽象主题角色
 */
public interface Subject {
    public void registerObserver(Observer ob);

    public void removeObserver(Observer ob);

    public void notifyObservers();
}
