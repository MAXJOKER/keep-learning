package designpatterns.strategy;

/**
 * 具体策略类
 */
public class Quack implements QuackBehavior{
    public void quack() {
        System.out.println("Quack");
    }
}
