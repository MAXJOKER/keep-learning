package loadbalancer.code;

import java.util.ArrayList;
import java.util.List;

/**
 * @author maxjoker
 * @date 2022-03-29 16:14
 *
 * 平滑加权轮询
 *
 * 问题：
 * 对加权轮询算法的优化，加权轮询算法中，短时间内请求的分配不一定是均匀的，比如 A、B服务器的权重为 5、2，
 * 短时间如果有7次请求，那么请求分发的顺序为 [A，A，A，A，A，B，B]，这样服务器A短时间内收到大量请求，
 * 压力陡增，而B却处于空闲状态。而我们期望的应该是[A，A，B，A，A，B，A]，不同服务器可以穿插获取请求。
 *
 * 思路：
 *
 * 首先，每个服务器都有两个权重，
 * （1）weight，配置文件中指定的服务器权重，固定不变；
 * （2）current_weight，服务器当前权重，一开始为0，动态调整。
 *
 * 然后，当请求到来，会遍历数组中所有的服务器，对于每个服务器，让它的current_weight加上weight，同时累加所有服务器的weight，保存为total。
 *
 * 遍历处理完成后，如果某一服务器的current_weight是最大的，则选取该服务器处理本次请求。最后该服务器的current_weight要减去total。
 *
 * 比如有A、B、C 三台服务器，权重为：
 * A：4
 * B：2
 * C：1
 *
 * total：7
 * current_weight += weight（每次请求处理前都要加上weight）
 *
 * 请求1：
 * 	current_weight：A：4，B：2，C：1，此时选择A处理请求，减去total后，current_weight：A：-3，B：2，C：1
 *
 * 请求2：
 * 	current_weight：A：1，B：4，C：2，此时选择B处理请求，减去total后，current_weight：A：1，B：-3，C：2
 *
 * 请求3：
 * 	current_weight：A：5，B：-1，C：3，此时选择A处理请求，减去total后，current_weight：A：-2，B：-1，C：3
 *
 * 请求4：
 * 	current_weight：A：2，B：1，C：4，此时选择C处理请求，减去total后，current_weight：A：2，B：1，C：-3
 *
 * 请求5：
 * 	current_weight：A：6，B：3，C：-2，此时选择A处理请求，减去total后，current_weight：A：-1，B：3，C：-2
 *
 * 请求6：
 * 	current_weight：A：3，B：5，C：-1，此时选择B处理请求，减去total后，current_weight：A：3，B：-2，C：-1
 *
 * 请求7：
 * 	current_weight：A：7，B：0，C：0，此时选择A处理请求，减去total后，current_weight：A：0，B：0，C：0
 *
 */
public class SmoothWeightRoundRobin {
    private List<Server> servers = new ArrayList<>();
    private int totalWeight = 0;

    public SmoothWeightRoundRobin() {
        servers.add(new Server("192.168.0.1", 1, 0));
        servers.add(new Server("192.168.0.2", 2, 0));
        servers.add(new Server("192.168.0.3", 3, 0));
        servers.add(new Server("192.168.0.4", 4, 0));
        servers.add(new Server("192.168.0.5", 5, 0));

        totalWeight = getTotalWeight();
    }

    private int getTotalWeight() {
        for (Server server : servers) {
            totalWeight += server.getWeight();
        }

        return totalWeight;
    }

    public String smoothWeightRoundRobin() {
        int maxCurrentWeight = 0;
        int index = 0;
        for (int i = 0; i < servers.size(); i++) {
            int serverWeight = servers.get(i).getWeight();
            int currentWeight = servers.get(i).getCurrentWeight();
            servers.get(i).setCurrentWeight(currentWeight + serverWeight);

            if (maxCurrentWeight < currentWeight) {
                maxCurrentWeight = currentWeight;
                index = i;
            }
        }

        servers.get(index).setCurrentWeight(servers.get(index).getCurrentWeight() - totalWeight);

        return servers.get(index).getIp();
    }
}
