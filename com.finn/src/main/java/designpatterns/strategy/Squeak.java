package designpatterns.strategy;

/**
 * 具体策略类
 */
public class Squeak implements QuackBehavior{
    public void quack() {
        System.out.println("Squeak");
    }
}
