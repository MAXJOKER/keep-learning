package designpatterns.adapter;

public class Test {
    public static void main(String[] args) {
        WildTurkey wildTurkey = new WildTurkey();
        wildTurkey.fly();
        wildTurkey.gobble();

        TurkeyAdapter adapter = new TurkeyAdapter(wildTurkey);
        adapter.quack();
        adapter.fly();
    }
}
