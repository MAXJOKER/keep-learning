package loadbalancer.code.consistenthash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author maxjoker
 * @date 2022-03-31 21:00
 *
 * 一致性哈希 （不带虚拟节点） 测试
 *
 */
public class LoadBalancerWithoutVirtualNodeTest {

    private static LoadBalancerWithVirtualNode virtualNode = new LoadBalancerWithVirtualNode();

    /**
     * hash环
     */
    private static SortedMap<Integer, String> map;

    /**
     * 服务器数量
     */
    private static final int SERVER_NUM = 100;

    /**
     * 待加入hash环的服务器列表
     */
    private static ArrayList<String> serverList = new ArrayList<>();

    public static void main(String[] args) {
        // 构造服务器节点
        for (int i = 0; i < SERVER_NUM; i++) {
            String s = new StringBuilder().append("192.168.0.").append(String.valueOf(i)).toString();
            serverList.add(s);
        }

        // 构造hash环
        map = virtualNode.buildHashCircle(new TreeMap<Integer, String>());
        // 将服务器的虚拟节点加入hash环中
        for (String s : serverList) {
            virtualNode.addServerNode(s);
        }

        // <节点, 服务器>
        HashMap<String, String> m = new HashMap<>();

        // 生成请求ip
        String[] requestIps = IpAddressGenerate.getIpAddress(100);
        for (String requestIp : requestIps) {
            // 获取服务器ip
            String serverIp = virtualNode.selectServerNode(requestIp);
            m.put(requestIp, serverIp);
        }

        System.out.println(m.toString());
    }
}
