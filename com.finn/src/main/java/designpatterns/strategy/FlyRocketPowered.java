package designpatterns.strategy;

/**
 * 具体策略类
 */
public class FlyRocketPowered implements FlyBehavior {
    public void fly() {
        System.out.println("冲冲冲");
    }
}
