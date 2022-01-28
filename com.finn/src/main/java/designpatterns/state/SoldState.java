package designpatterns.state;

/**
 * @author maxjoker
 * @date 2022-01-28 16:38
 * 具体角色类
 */
public class SoldState implements State {

    GumballMachine gumballMachine;

    public SoldState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    @Override
    public void insertQuarter() {
        System.out.println("Please wait, we're already giving you a gumball.");
    }

    @Override
    public void ejectQuarter() {
        System.out.println("Sorry, you're already turned a crank.");
    }

    @Override
    public void turnCrank() {
        System.out.println("Turning twice crank can't give you another gumball.");
    }

    @Override
    public void dispense() {
        gumballMachine.releaseBall();
        if (gumballMachine.getCount() > 0) {
            gumballMachine.setState(gumballMachine.getNoQuarterState());
        } else {
            System.out.println("Oops, out of gumballs.");
            gumballMachine.setState(gumballMachine.getSoldOutState());
        }
    }
}
