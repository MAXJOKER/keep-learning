package designpatterns.template.case1;

/**
 * @author maxjoker
 * @date 2022-01-27 15:15
 * 具体模板
 */
public class Coffee extends CaffeineBeverage {
    @Override
    void brew() {
        System.out.println("brew coffee");
    }

    @Override
    void addCondiments() {
        System.out.println("add some milk and sugar");
    }
}
