package designpatterns.template.case1;

/**
 * @author maxjoker
 * @date 2022-01-27 15:15
 */
public class Test {
    public static void main(String[] args) {
        Tea tea = new Tea();
        tea.prepareRecipe();

        Coffee coffee = new Coffee();
        coffee.prepareRecipe();
    }
}
