package designpatterns.factory.factory;

/**
 * 具体工厂角色
 */
public class NYCPizzaStore extends PizzaStore {
    @Override
    protected Pizza createPizza(String type) {
        if (type.equals("cheese")) {
            return new NYCStyleCheesePizza();
        }

        return null;
    }
}
