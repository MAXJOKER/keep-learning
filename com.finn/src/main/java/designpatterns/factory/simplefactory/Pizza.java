package designpatterns.factory.simplefactory;

/**
 * 抽象产品
 */
public abstract class Pizza {
    public void prepare() {
        System.out.println("prepare to cook pizza");
    }

    public void bake() {
        System.out.println("start to bake pizza");
    }

    public void cut() {
        System.out.println("time to cut pizza");
    }

    public void box() {
        System.out.println("ok, boxing pizza.");
    }
}
