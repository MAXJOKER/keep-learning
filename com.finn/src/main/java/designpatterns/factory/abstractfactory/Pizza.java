package designpatterns.factory.abstractfactory;

public abstract class Pizza {
    String name;
    Dough dough;
    Cheese cheese;
    Sauce sauce;

    abstract void prepare();

    void bake() {
        System.out.println("bake pizza");
    }

    void cut() {
        System.out.println("cut pizza");
    }

    void box() {
        System.out.println("box pizza");
    }

    void setName(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }
}
