package loadbalancer.code.consistenthash;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author maxjoker
 * @date 2022-03-31 19:53
 *
 * 一致性哈希 （带虚拟节点）
 *
 */
public class LoadBalancerWithVirtualNode implements LoadBalancer {

    private TreeMap<Integer, String> treeMapHash;

    @Override
    public void addServerNode(String serverNodeName) {
        int hash = GetHashCode.getHashCodeByFnvi32(serverNodeName);
        treeMapHash.put(hash, serverNodeName);
        System.out.println("添加节点成功, 当前节点总览：" + treeMapHash.toString());
    }

    @Override
    public void deleteServerNode(String serverNodeName) {
        int hash = GetHashCode.getHashCodeByFnvi32(serverNodeName);
        treeMapHash.remove(hash);
        System.out.println("删除节点成功, 当前节点总览：" + treeMapHash.toString());
    }

    @Override
    public String selectServerNode(String key) {
        int hash = GetHashCode.getHashCodeByFnvi32(key);
        // 返回具有最小键值（大于或等于指定键）的键值对，如果没有这样的键，则返回null。
        Map.Entry<Integer, String> entry = treeMapHash.ceilingEntry(hash);
        if (entry == null) {
            entry = treeMapHash.firstEntry();
        }

        String vnNode = entry.getValue();
        return vnNode.substring(0, vnNode.indexOf("&&"));
    }

    public SortedMap<Integer, String> buildHashCircle(TreeMap<Integer, String> map) {
        this.treeMapHash = map;
        return treeMapHash;
    }
}
