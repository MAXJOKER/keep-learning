package designpatterns.strategy;

/**
 * 具体策略角色
 * 模型鸭
 */
public class ModelDuck extends Duck {
    public ModelDuck() {
        quackBehavior = new Quack();
        flyBehavior = new FlyRocketPowered();
    }
    public void display() {
        System.out.println("This is model duck.");
    }
}
