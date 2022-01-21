package designpatterns.factory.abstractfactory;

/**
 * 抽象工厂
 */
public interface PizzaIngredientFactory {
    public Dough createDough();
    public Cheese createCheese();
    public Sauce createSauce();
}
