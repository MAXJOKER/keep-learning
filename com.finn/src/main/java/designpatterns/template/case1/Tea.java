package designpatterns.template.case1;

/**
 * @author maxjoker
 * @date 2022-01-27 15:15
 * 具体模板
 */
public class Tea extends CaffeineBeverage {
    @Override
    void brew() {
        System.out.println("brew tea");
    }

    @Override
    void addCondiments() {
        System.out.println("add lemon");
    }
}
