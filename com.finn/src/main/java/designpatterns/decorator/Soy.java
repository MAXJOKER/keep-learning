package designpatterns.decorator;

/**
 * 具体装饰者
 */
public class Soy extends CondimentDecorator{
    private Beverage beverage;

    public Soy(Beverage beverage) {
        this.beverage = beverage;
    }

    public String getDescription() {
        return beverage.getDescription() + ", Soy";
    }

    public double cost() {
        return 0.1 + beverage.cost();
    }
}
