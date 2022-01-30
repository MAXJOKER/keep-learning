package designpatterns.state;

/**
 * @author maxjoker
 * @date 2022-01-28 17:13
 */
public class WinnerState implements State{

    GumballMachine gumballMachine;

    public WinnerState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    @Override
    public void insertQuarter() {
        System.out.println("unsupported operation");
    }

    @Override
    public void ejectQuarter() {
        System.out.println("unsupported operation");
    }

    @Override
    public void turnCrank() {
        System.out.println("unsupported operation");
    }

    @Override
    public void dispense() {
        System.out.println("You Are A Winner!!! You get two gumballs for your quarter.");
        gumballMachine.releaseBall();
        if (gumballMachine.getCount() > 0) {
            gumballMachine.releaseBall();
            if (gumballMachine.getCount() > 0) {
                gumballMachine.setState(gumballMachine.getNoQuarterState());
            } else {
                System.out.println("Oops, out of gumballs");
                gumballMachine.setState(gumballMachine.getSoldOutState());
            }
        } else {
            System.out.println("Oops, out of gumballs");
            gumballMachine.setState(gumballMachine.getSoldOutState());
        }
    }
}
