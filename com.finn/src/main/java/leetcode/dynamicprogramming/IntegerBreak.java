package leetcode.dynamicprogramming;

import java.util.Arrays;

/**
 * @author maxjoker
 * @date 2022-05-08 09:41
 *
 * https://leetcode-cn.com/problems/integer-break/
 *
 * 343. 整数拆分
 * 给定一个正整数 n ，将其拆分为 k 个 正整数 的和（ k >= 2 ），并使这些整数的乘积最大化。
 *
 * 返回 你可以获得的最大乘积 。
 *
 *
 *
 * 示例 1:
 *
 * 输入: n = 2
 * 输出: 1
 * 解释: 2 = 1 + 1, 1 × 1 = 1。
 * 示例 2:
 *
 * 输入: n = 10
 * 输出: 36
 * 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36。
 *
 *
 * 提示:
 *
 * 2 <= n <= 58
 *
 */
public class IntegerBreak {
    private static int[] memory;

    /**
     * 递归
     * 会超时
     * @param n
     * @return
     */
    public static int integerBreak(int n) {
        return breakInteger(n);
    }

    public static int breakInteger(int n) {
        if (n == 1) {
            return n;
        }

        int res = 0;
        for (int i = 1; i < n; i++) {
            res = max3(res, i * (n - i), i * breakInteger(n - i));
        }
        return res;
    }

    public static int max3(int n1, int n2, int n3) {
        int temp = Integer.max(n1, n2);
        return Integer.max(temp, n3);
    }

    /**
     * 记忆化递归
     * AC
     * @param n
     * @return
     */
    public static int integerBreakWithMemory(int n ) {
        assert n >= 2;
        memory = new int[n + 1];
        Arrays.fill(memory, -1);
        memory[0] = 0;
        memory[1] = 1;

        return breakIntegerWithMemory(n);
    }

    private static int breakIntegerWithMemory(int n) {
        if (n == 1) {
            return 1;
        }

        if (memory[n] == -1) {
            int res = 0;
            for (int i = 1; i < n; i++) {
                res = max3(res, i * (n - i), i * breakIntegerWithMemory(n - i));
            }
            memory[n] = res;
        }

        return memory[n];
    }

    /**
     * 动态规格
     * @param n
     * @return
     */
    public static int integerBreakByDynamicProgram(int n) {
        if (n == 1) {
            return 1;
        }

        memory = new int[n + 1];
        memory[0] = 0;
        memory[1] = 1;

        for (int i = 2; i <= n; i++) {
            int maxValue = 0;
            for (int j = 1; j <= i - 1; j++) {
                maxValue = max3(maxValue, j * (i - j), j * memory[i - j]);
            }
            memory[i] = maxValue;
        }

        return memory[n];
    }

    /**
     * 数学方法
     * @param n
     * @return
     */
    public static int integerBreakByMath(int n) {
        if (n <= 3) {
            return n - 1;
        }
        int res = 1;
        while (n > 4) {
            res *= 3;
            n -= 3;
        }
        return res * n;
    }

    public static void main(String[] args) {
        int n = 10;
        System.out.println(integerBreak(n));
        System.out.println(integerBreakWithMemory(n));
        System.out.println(integerBreakByDynamicProgram(n));
        System.out.println(integerBreakByMath(n));
    }
}
