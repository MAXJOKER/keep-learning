package designpatterns.adapter;

public class WildTurkey implements Turkey {
    @Override
    public void gobble() {
        System.out.println("gobble gobble gobble");
    }

    @Override
    public void fly() {
        System.out.println("Turkey fly a short distance");
    }
}
