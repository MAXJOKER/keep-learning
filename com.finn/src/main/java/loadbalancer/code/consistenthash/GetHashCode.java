package loadbalancer.code.consistenthash;

/**
 * @author maxjoker
 * @date 2022-03-31 20:12
 *
 * 获取hashcode
 *
 */
public class GetHashCode {
    private static final long FNVI_32_INIT = 2166136261L;
    private static final int FNVI_32_PRIME = 16777619;

    /**
     * FNVI_32 hash
     * @param key
     * @return
     */
    public static int getHashCodeByFnvi32(String key) {
        int hash = (int) FNVI_32_INIT;
        for (int i = 0; i < key.length(); i++) {
            hash = (hash ^ key.charAt(i)) * FNVI_32_PRIME;
        }
        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;
        hash = Math.abs(hash);

        return hash;
    }

    // todo 其他的hashcode计算方法
}
