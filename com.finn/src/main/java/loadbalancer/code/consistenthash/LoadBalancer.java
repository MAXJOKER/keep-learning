package loadbalancer.code.consistenthash;

/**
 * @author maxjoker
 * @date 2022-03-31 20:10
 *
 * 参考文章：https://gongfukangee.github.io/2019/05/19/Hash/
 *
 * 通用接口 （其实可以把它改成抽象类，父类实现通用方法，从两个例子来看，这几个方法都一样的）
 *
 */
public interface LoadBalancer {
    /**
     * 添加服务节点
     * @param serverNodeName
     */
    public void addServerNode(String serverNodeName);

    /**
     * 删除服务节点
     * @param serverNodeName
     * @return
     */
    public void deleteServerNode(String serverNodeName);

    /**
     * 选择服务节点
     * @param key
     * @return
     */
    public String selectServerNode(String key);
}
