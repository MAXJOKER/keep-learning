package designpatterns.strategy;

/**
 * 具体策略类
 */
public class FlyWings implements FlyBehavior{
    public void fly() {
        System.out.println("I'm flying!");
    }
}
