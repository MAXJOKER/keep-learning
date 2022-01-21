package designpatterns.factory.simplefactory;

/**
 * 工厂，创建具体实例
 */
public class PizzaFactory {
    public Pizza createPizza(String type) {
        Pizza pizza = null;

        if ("cheese".equals(type)) {
            pizza = new CheesePizza();
        } else if ("veggie".equals(type)) {
            pizza = new VeggiePizza();
        }

        return pizza;
    }
}
