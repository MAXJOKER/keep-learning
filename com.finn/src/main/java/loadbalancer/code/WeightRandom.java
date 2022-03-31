package loadbalancer.code;

import java.util.*;
import java.util.Random;

/**
 * @author maxjoker
 * @date 2022-03-29 16:14
 *
 * 加权随机
 *
 */
public class WeightRandom {
    /**
     * 服务器列表
     */
    private static List<String> servers = new ArrayList<>();

    /**
     * 服务器&权重配置
     */
    private static HashMap<Integer, String> map = new HashMap<>();

    /**
     * 总权重值
     */
    private static Integer total = 0;

    private static final Random RANDOM = new Random();

    static {
        map.put(1, "192.168.0.1:90");
        map.put(2, "192.168.0.1:92");
        map.put(3, "192.168.0.1:93");
        map.put(4, "192.168.0.1:94");

        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            total += entry.getKey();
            for (int i = 0; i < entry.getKey(); i++) {
                servers.add(entry.getValue());
            }
        }
    }

    public static String weightRandom() {
        int random = RANDOM.nextInt(total);
        return servers.get(random);
    }

    public static void main(String[] args) {
        System.out.println(weightRandom());
    }
}
