package designpatterns.state;

/**
 * @author maxjoker
 * @date 2022-01-28 17:03
 */
public class SoldOutState implements State {

    GumballMachine gumballMachine;

    public SoldOutState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    @Override
    public void insertQuarter() {
        System.out.println("Sold out.");
    }

    @Override
    public void ejectQuarter() {
        System.out.println("You haven't insert a quarter");
    }

    @Override
    public void turnCrank() {
        System.out.println("You turned, but there's no gumball.");
    }

    @Override
    public void dispense() {
        System.out.println("Oops, out of gumballs");
    }
}
