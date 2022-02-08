package designpatterns.proxy;

/**
 * 目标对象角色
 */
public class RealObject extends AbstractObject {
    @Override
    public void operation() {
        int sum = 0;
        System.out.println("real object do something");
        try {
            for (int i = 0; i < 1024; i++) {
                sum += i;
                Thread.sleep(1);
            }
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        }
    }
}
