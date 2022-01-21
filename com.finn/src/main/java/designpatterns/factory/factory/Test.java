package designpatterns.factory.factory;

public class Test {
    public static void main(String[] args) {
        PizzaStore store = new NYCPizzaStore();
        store.orderPizza("cheese");
    }
}
