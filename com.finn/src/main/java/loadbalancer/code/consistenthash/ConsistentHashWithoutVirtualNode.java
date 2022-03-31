package loadbalancer.code.consistenthash;

import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author maxjoker
 * @date 2022-03-29 16:14
 *
 * 一致性哈希 （无虚拟节点）（版本1）发现有更好的版本，这个就留着吧，不删了
 *
 * 网上看到有博主说使用String的hashCode方法，计算的hash值聚集在某个区间，可能会导致某台服务器负载会特别大
 * 重新hash的算法：
 * FNV1_32_HASH 计算效率高
 * KETAMA_HASH 是redis推荐的一致性哈希算法
 * CRC32_HASH
 */
public class ConsistentHashWithoutVirtualNode {
    /**
     * 加入hash环的服务器列表
     */
    private static String[] servers = {"192.168.0.1:90", "192.168.0.2:91", "192.168.0.3:92", "192.168.0.4:93", "192.168.0.5:94"};

    /**
     * key表示服务器的hash值，value表示服务器ip
     */
    private static SortedMap<Integer, String> sortedMap = new TreeMap<>();

    /**
     * 初始化数据
     */
    static {
        for (int i = 0; i < servers.length; i++) {
            int hash = Math.abs(servers[i].hashCode());
            sortedMap.put(hash, servers[i]);
            System.out.println("server: " + servers[i] + " is put into hash circle, it's hash code is " + hash);
        }
    }

    /**
     * 获取请求分发到的服务器
     * @param key
     * @return
     */
    private static String getServer(String key) {
        // 计算请求key的hash值
        int keyHash = Math.abs(key.hashCode());
        // 获取比 keyHash 大的子集
        SortedMap<Integer, String> subMap = sortedMap.tailMap(keyHash);

        // 如果子集为空，说明没有比 keyHash 大的，则从第一个节点开始
        if (subMap.isEmpty()) {
            int i = sortedMap.firstKey();
            // 返回对应的服务器
            return sortedMap.get(i);
        } else {
            // 子集中的第一个节点就是离keyHash最近的节点
            int i = subMap.firstKey();
            return subMap.get(i);
        }
    }

    public static void main(String[] args) {
        String[] keys = new String[]{"茉莉乌龙", "雪梨海底椰", "阿华田", "旺仔牛奶", "正宗椰汁", "怡泉"};
        for (int i = 0; i < keys.length; i++) {
            System.out.println("key: " + keys[i] + "\nkeyHash: " + keys[i].hashCode() + "\ngerServer: " + getServer(keys[i]) + "\n\n");
        }
    }
}
