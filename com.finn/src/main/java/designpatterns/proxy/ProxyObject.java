package designpatterns.proxy;

/**
 * 代理对象角色
 */
public class ProxyObject extends AbstractObject {
    // 目标对象
    RealObject object = new RealObject();

    @Override
    public void operation() {
        System.out.println("before");
        long startTime = System.currentTimeMillis();
        object.operation();
        long endTime = System.currentTimeMillis();
        System.out.println("after");
        System.out.println("cost: " + String.valueOf(endTime - startTime) + " ms");
    }
}
