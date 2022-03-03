package leetcode;

/**
 * @author maxjoker
 * @date 2022-03-02 21:38
 *
 * 1137. 第 N 个泰波那契数
 * 泰波那契序列 Tn 定义如下：
 *
 * T0 = 0, T1 = 1, T2 = 1, 且在 n >= 0 的条件下 Tn+3 = Tn + Tn+1 + Tn+2
 *
 * 给你整数 n，请返回第 n 个泰波那契数 Tn 的值。
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 4
 * 输出：4
 * 解释：
 * T_3 = 0 + 1 + 1 = 2
 * T_4 = 1 + 1 + 2 = 4
 * 示例 2：
 *
 * 输入：n = 25
 * 输出：1389537
 *
 *
 * 提示：
 *
 * 0 <= n <= 37
 * 答案保证是一个 32 位整数，即 answer <= 2^31 - 1。
 *
 */
public class NThTribonacciNumber {
    /**
     * 递归 （超时了）
     * @param n
     * @return
     */
    public static int tribonacci(int n) {
        if (n < 2) {
            return n;
        }

        if (n == 2) {
            return 1;
        }

        return tribonacci(n-3) + tribonacci(n-2) + tribonacci(n-1);
    }

    public static int tribonacciByLoop(int n) {
        if (n < 2) {
            return n;
        }

        if (n == 2) {
            return 1;
        }

        int p = 0;
        int q = 1;
        int t = 1;
        int sum = 0;

        for (int i = 3; i <= n; i++) {
            sum = p + q + t;
            p = q;
            q = t;
            t = sum;
        }

        return sum;
    }

    // todo 矩阵快速幂

    public static void main(String[] args) {
        System.out.println(tribonacciByLoop(35));
    }
}
