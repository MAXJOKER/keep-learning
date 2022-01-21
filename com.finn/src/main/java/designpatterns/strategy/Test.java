package designpatterns.strategy;

public class Test {
    public static void main(String[] args) {
        Duck duck = new NormalDuck();
        duck.performFly();
        duck.performQuack();
        duck.display();

        Duck modelDuck = new ModelDuck();
        modelDuck.performQuack();
        modelDuck.setFlyBehavior(new FlyWings());
        modelDuck.performFly();
    }
}
