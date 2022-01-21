package designpatterns.factory.abstractfactory;

/**
 * 具体工厂角色
 */
public class NYCPizzaStore extends PizzaStore {
    @Override
    protected Pizza createPizza(String type) {
        Pizza pizza = null;
        PizzaIngredientFactory factory = new NYCPizzaIngredientFactory();
        if (type.equals("cheese")) {
            pizza = new CheesePizza(factory);
            pizza.setName("NYC cheese pizza");
        }

        return pizza;
    }
}
