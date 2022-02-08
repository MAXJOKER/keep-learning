package designpatterns.adapter;

/**
 * 适配器类
 */
public class TurkeyAdapter implements Duck {

    Turkey turkey;

    public TurkeyAdapter(Turkey turkey) {
        this.turkey = turkey;
    }

    @Override
    public void quack() {
        turkey.gobble();
    }

    @Override
    public void fly() {
        for (int i = 0; i < 5; i++) {
            turkey.fly();
        }
    }

    @Override
    public void swim() {
        // 假设Turkey不支持swim，适配器可以直接抛个不支持异常
        throw new UnsupportedOperationException();
    }
}
