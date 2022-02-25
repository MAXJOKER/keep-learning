package leetcode;

/**
 * @author maxjoker
 * @date 2022-02-25 17:18
 *
 * 50. Pow(x, n)
 * 实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，xn ）。
 *
 *
 *
 * 示例 1：
 *
 * 输入：x = 2.00000, n = 10
 * 输出：1024.00000
 * 示例 2：
 *
 * 输入：x = 2.10000, n = 3
 * 输出：9.26100
 * 示例 3：
 *
 * 输入：x = 2.00000, n = -2
 * 输出：0.25000
 * 解释：2-2 = 1/22 = 1/4 = 0.25
 *
 *
 * 提示：
 *
 * -100.0 < x < 100.0
 * -2^31 <= n <= 2^31-1
 * -10^4 <= x^n <= 10^4
 *
 * https://leetcode-cn.com/problems/powx-n/
 *
 */
public class PowxN {
    /**
     * 此种解法会超时
     * @param x
     * @param n
     * @return
     */
    public static double myPow(double x, int n) {
        double result = 1;

        for (int i = 1; i <= Math.abs(n); i++) {
            result *= x;
        }

        return n > 0 ? result : 1 / result;
    }

    /**
     * 快速幂 + 递归
     * 时间复杂度：O(logn)  递归的层数
     * 空间复杂度：O(logn)  递归的层数
     * @param x
     * @param n
     * @return
     */
    public static double myPow2(double x, int n) {
        return n >= 0 ? quickMulti(x, n) : 1.0 / quickMulti(x, -n);
    }

    public static double quickMulti(double x, long m) {
        if (m == 0) {
            return 1.0;
        }

        double y = quickMulti(x, m / 2);
        return m % 2 == 0 ? y * y : y * y * x;
    }

    /**
     * 快速幂 + 迭代
     * 时间复杂度：O(logn) ，即为对 n 进行二进制拆分的时间复杂度。
     * 空间复杂度：O(1)
     * @param x
     * @param n
     * @return
     */
    public static double myPow3(double x, int n) {
        long m = n;
        return m >= 0 ? quickMulti3(x, m) : 1.0 / quickMulti(x, -m);
    }

    public static double quickMulti3(double x, long m) {
        if (m == 0) {
            return 1.0;
        }

        double result = 1.0;
        double pow = x;
        while (m > 0) {
            // 如果m是基数，可以先把最后要乘的x先乘了
            // 而最终的计算，m 肯定会 等于 1，这时候把累乘的pow乘上就可以得出结果了
            if (m % 2 == 1) {
                result *= pow;
            }

            pow *= pow;
            m = m / 2;
        }

        return result;
    }

    public static void main(String[] args) {
        double x = 2.00000;
        int n = -2147483648;
        System.out.println(myPow3(x, n));
    }
}
