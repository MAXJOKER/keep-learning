package designpatterns.factory.simplefactory;

public class Test {
    public static void main(String[] args) {
        PizzaStore store = new PizzaStore(new PizzaFactory());
        store.orderPizza("cheese");
    }
}
