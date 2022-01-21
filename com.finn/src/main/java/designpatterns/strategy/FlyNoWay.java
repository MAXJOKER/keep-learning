package designpatterns.strategy;

/**
 * 具体策略类
 */
public class FlyNoWay implements FlyBehavior{
    public void fly() {
        System.out.println("I can't fly!");
    }
}
