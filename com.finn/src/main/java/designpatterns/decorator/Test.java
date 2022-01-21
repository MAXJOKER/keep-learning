package designpatterns.decorator;

public class Test {
    public static void main(String[] args) {
        Beverage espresso = new Espresso();
        espresso = new Mocha(espresso);
        espresso = new Soy(espresso);
        System.out.println(espresso.getDescription() + ". Cost: $ " + espresso.cost());
    }
}
