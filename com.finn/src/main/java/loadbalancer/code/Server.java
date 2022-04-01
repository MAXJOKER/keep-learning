package loadbalancer.code;

/**
 * @author maxjoker
 * @date 2022-04-01 21:46
 */
public class Server {
    private String ip;
    private int weight;
    private int currentWeight;

    public Server(String ip, int weight) {
        this.ip = ip;
        this.weight = weight;
    }

    public Server(String ip, int weight, int currentWeight) {
        this.ip = ip;
        this.weight = weight;
        this.currentWeight = currentWeight;
    }

    public String getIp() {
        return ip;
    }

    public int getWeight() {
        return weight;
    }

    public int getCurrentWeight() {
        return currentWeight;
    }

    public void setCurrentWeight(int weight) {
        this.currentWeight = weight;
    }
}
