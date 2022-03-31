package loadbalancer.code.consistenthash;

import java.util.Random;

/**
 * @author maxjoker
 * @date 2022-03-31 20:33
 *
 * 构造请求ip
 *
 */
public class IpAddressGenerate {

    private static final Random RANDOM = new Random();

    public static String[] getIpAddress(int num) {
        String[] res = new String[num];
        for (int i = 0; i < num; i++) {
            res[i] = String.valueOf(RANDOM.nextInt(256)) + "." +
                    String.valueOf(RANDOM.nextInt(256)) + "." +
                    String.valueOf(RANDOM.nextInt(256)) + "." +
                    String.valueOf(RANDOM.nextInt(256)) + ":" +
                    String.valueOf(RANDOM.nextInt(9999));

        }

        return res;
    }
}
