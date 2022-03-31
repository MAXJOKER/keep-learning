package loadbalancer.code;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author maxjoker
 * @date 2022-03-29 16:14
 *
 * 轮询
 *
 */
public class RoundRobin {
    /**
     * 服务器列表
     */
    private static String[] servers = {"192.168.0.1:90", "192.168.0.2:91", "192.168.0.3:92", "192.168.0.4:93", "192.168.0.5:94"};

    private static Integer count = servers.length - 1;

    /**
     * 这只是一个简单的例子，实际应用应该还需要考虑并发 （对count的操作）
     * @return
     */
    public static String roundRobin() {
        if (count < 0) {
            count = servers.length - 1;
        }

        String serverIp = servers[count];
        count--;

        return serverIp;

    }
}
