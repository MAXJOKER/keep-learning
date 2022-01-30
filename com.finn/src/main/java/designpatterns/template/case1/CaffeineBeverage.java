package designpatterns.template.case1;

/**
 * @author maxjoker
 * @date 2022-01-27 15:15
 * 抽象模板
 */
public abstract class CaffeineBeverage {
    /**
     * 模板方法, 把基本操作方法组合在一起形成一个总算法或一个总行为的方法。
     */
    final void prepareRecipe() {
        boilWater();
        pourInCup();
        brew();
        if (customerWantsCondiments()) {
            addCondiments();
        }
    }

    /**
     * 抽象方法：一个抽象方法由抽象类声明，由具体子类实现。
     */
    abstract void brew();

    abstract void addCondiments();

    /**
     * 具体方法：一个具体方法由抽象类声明并实现，而子类并不实现或置换。
     */
    void boilWater() {
        System.out.println("boiling water");
    }

    void pourInCup() {
        System.out.println("pour in cup");
    }

    /**
     * 钩子方法：一个钩子方法由抽象类声明并实现，而子类会加以扩展。通常抽象类给出的实现是一个空实现，作为方法的默认实现。
     * @return
     */
    boolean customerWantsCondiments() {
        return true;
    }
}
