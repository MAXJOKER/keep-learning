package designpatterns.state;

/**
 * @author maxjoker
 * @date 2022-01-28
 * 抽象角色类
 *
 * 可以考虑写成抽象类，子类继承父类，子类只重写需要重写的方法就行了。
 */
public interface State {
    /**
     * 塞钱进机器
     */
    public void insertQuarter();

    /**
     * 退钱
     */
    public void ejectQuarter();

    /**
     * 扭动机器
     */
    public void turnCrank();

    /**
     * 吐出商品
     */
    public void dispense();
}
