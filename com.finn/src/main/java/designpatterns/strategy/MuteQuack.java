package designpatterns.strategy;

/**
 * 具体策略类
 */
public class MuteQuack implements QuackBehavior {
    public void quack() {
        System.out.println("<<Silence>>");
    }
}
