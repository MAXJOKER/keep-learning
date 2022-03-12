package leetcode;

/**
 * @author maxjoker
 * @date 2022-03-12 00:54
 *
 * https://leetcode-cn.com/problems/hamming-distance
 *
 * 题解：https://leetcode-cn.com/problems/hamming-distance/solution/yi-ming-ju-chi-by-leetcode-solution-u1w7/
 *
 */
public class HammingDistance {
    /**
     * 内置函数
     * 时间复杂度：O(1)
     * 空间复杂度：O(1)
     * @param x
     * @param y
     * @return
     */
    public static int hammingDistance(int x, int y) {
        return Integer.bitCount(x^y);
    }

    /**
     * 向右位移一位，然后最低位和1进行与运算& ，循环计算 1 的个数
     * 与运算：同为1才是1，否则为0
     *
     * 时间复杂度：O(logC)，c为元素的取值范围 logC = log2^31 = 31
     * 空间复杂度：O(1)
     *
     * @param x
     * @param y
     * @return
     */
    public static int hammingDistanceByDisplacement(int x, int y) {
        int s = x ^ y;
        int ret = 0;

        while (s != 0) {
            ret += s & 1;
            s = s >> 1;
        }

        return ret;
    }

    /**
     * 假设 f(x) = x & (x - 1)
     * 具体看图：https://assets.leetcode-cn.com/solution-static/461/3.png
     *
     * x & (x - 1) 每次的结果都会把最右侧的1删掉，因为每次 x & (x - 1) 时，1的个数加1，直到 x = 0
     *
     * @param x
     * @param y
     * @return
     */
    public static int hammingDistanceByBrianKernighan(int x, int y) {
        int s = x ^ y;
        int ret = 0;

        while (s != 0) {
            s = s & (s - 1);
            ret++;
        }

        return ret;
    }

    public static void main(String[] args) {
        int x = 1;
        int y = 4;
        System.out.println(hammingDistance(x, y));
        System.out.println(hammingDistanceByDisplacement(x, y));
        System.out.println(hammingDistanceByBrianKernighan(x, y));
    }
}
