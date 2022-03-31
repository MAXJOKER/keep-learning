package loadbalancer.code;

import loadbalancer.code.consistenthash.GetHashCode;

/**
 * @author maxjoker
 * @date 2022-03-29 16:14
 *
 * 哈希算法
 *
 */
public class Hash {
    /**
     * 服务器列表
     */
    private static String[] servers = {"192.168.0.1:90", "192.168.0.2:91", "192.168.0.3:92", "192.168.0.4:93", "192.168.0.5:94"};

    /**
     * 计算hash值后取余，获取服务器ip
     * @param key 请求key，用来计算hashcode的
     */
    public static String  hashChoose(String key) {
        int hash = GetHashCode.getHashCodeByFnvi32(key);
        int mod = hash % servers.length;
        return servers[mod];
    }
}
