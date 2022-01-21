package designpatterns.factory.abstractfactory;

public class NYCPizzaIngredientFactory implements PizzaIngredientFactory {
    @Override
    public Dough createDough() {
        return new NYCDough();
    }

    @Override
    public Cheese createCheese() {
        return new NYCCheese();
    }

    @Override
    public Sauce createSauce() {
        return new NYCSauce();
    }
}
