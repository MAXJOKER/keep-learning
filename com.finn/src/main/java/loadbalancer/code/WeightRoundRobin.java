package loadbalancer.code;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @author maxjoker
 * @date 2022-03-29 16:14
 *
 * 加权轮询
 *
 * 思路：
 *
 * 1、Supposing that there is a server set S = {S0, S1, …, Sn-1}; W(Si) indicates the weight of Si;
 *
 * 2、i indicates the server selected last time, and i is initialized with -1; i为上一次选择的服务器index，数组S中的index
 *
 * 3、cw is the current weight in scheduling, and cw is initialized with zero; cw为current_weight
 *
 * 4、max(S) is the maximum weight of all the servers in S;
 *
 * 5、gcd(S) is the greatest common divisor of all server weights in S; （最大公约数）
 *
 * while (true) {
 *     i = (i + 1) mod n;
 *     if (i == 0) {
 *         cw = cw - gcd(S);
 *         if (cw <= 0) { // 如果 数组S很大的话，这里要计算好久才能小于等于0，开销太大了
 *             cw = max(S);
 *             if (cw == 0)
 *             return NULL;
 *         }
 *     }
 *     if (W(Si) >= cw)
 *         return Si;
 * }
 *
 */
public class WeightRoundRobin {

    private List<Server> servers = new ArrayList<>();

    private int currentIndex;
    private int totalServer;
    private int currentWeight;
    private int maxWeight;
    private int gcdWeight;

    public WeightRoundRobin() {
        servers.add(new Server("192.168.0.1", 1));
        servers.add(new Server("192.168.0.2", 2));
        servers.add(new Server("192.168.0.3", 3));
        servers.add(new Server("192.168.0.4", 4));
        servers.add(new Server("192.168.0.5", 5));

        totalServer = servers.size();
        currentIndex = totalServer - 1;
        maxWeight = maxWeight();
        gcdWeight = serverGcd();
    }

    /**
     * 服务器权重最大公约数
     * @return
     */
    private int serverGcd() {
        int comDivisor = 0;
        for (int i = 0; i < totalServer - 1; i++) {
            if (comDivisor == 0) {
                comDivisor = gcd(servers.get(i).getWeight(), servers.get(i+1).getWeight());
            } else {
                comDivisor = gcd(comDivisor, servers.get(i+1).getWeight());
            }
        }

        return comDivisor;
    }

    /**
     * 计算两个数的最大公约数
     * @param comDivisor
     * @param weight
     * @return
     */
    private int gcd(int comDivisor, int weight) {
        BigInteger a = new BigInteger(String.valueOf(comDivisor));
        BigInteger b = new BigInteger(String.valueOf(weight));

        return a.gcd(b).intValue();
    }

    /**
     * 获取最大权重值
     * @return
     */
    private int maxWeight() {
        int max = servers.get(0).getWeight();
        for (int i = 1; i < servers.size(); i++) {
            max = Math.max(max, servers.get(i).getWeight());
        }

        return max;
    }

    /**
     * 加权轮询
     * @return
     */
    public String weightRoundRobin() {
        while (true) {
            currentIndex = (currentIndex + 1) % totalServer;
            if (currentIndex == 0) {
                currentWeight = currentWeight - gcdWeight;
                if (currentWeight <= 0) {
                    currentWeight = maxWeight;
                    if (currentWeight == 0) {
                        return null;
                    }
                }
            }

            if (servers.get(currentIndex).getWeight() >= currentWeight) {
                return servers.get(currentIndex).getIp();
            }
        }
    }
}
