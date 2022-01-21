package designpatterns.strategy;

/**
 * 具体策略角色
 */
public class NormalDuck extends Duck {
    public NormalDuck() {
        quackBehavior = new Quack();
        flyBehavior = new FlyWings();
    }
    public void display() {
        System.out.println("This is normal duck.");
    }
}
