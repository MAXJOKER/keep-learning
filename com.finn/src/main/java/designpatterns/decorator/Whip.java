package designpatterns.decorator;

/**
 * 具体装饰者
 */
public class Whip extends CondimentDecorator{
    private Beverage beverage;

    public Whip(Beverage beverage) {
        this.beverage = beverage;
    }

    public String getDescription() {
        return beverage.getDescription() + ", Whip";
    }

    public double cost() {
        return 0.3 + beverage.cost();
    }
}
