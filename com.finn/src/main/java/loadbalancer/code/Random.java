package loadbalancer.code;

/**
 * @author maxjoker
 * @date 2022-03-29 16:14
 *
 * 随机算法
 *
 */
public class Random {
    /**
     * 服务器列表
     */
    private static String[] servers = {"192.168.0.1:90", "192.168.0.2:91", "192.168.0.3:92", "192.168.0.4:93", "192.168.0.5:94"};

    private static final java.util.Random RANDOM = new java.util.Random();

    /**
     * @return
     */
    public static String randomServer() {
        int index = RANDOM.nextInt(servers.length);
        return servers[index];
    }
}
