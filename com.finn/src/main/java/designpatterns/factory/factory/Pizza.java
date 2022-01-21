package designpatterns.factory.factory;

/**
 * 抽象产品角色
 */
public abstract class Pizza {
    String name;
    String dough;
    String sauce;

    public void prepare() {
        System.out.println("pizza name: " + name);
        System.out.println("Tossing dough...");
        System.out.println("Add sauce...");
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

    public String getName() {
        return name;
    }
}
