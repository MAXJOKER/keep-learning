package leetcode.dynamicprogramming;

import java.util.Arrays;

/**
 * @author maxjoker
 * @date 2022-05-20 20:33
 *
 * 322. 零钱兑换
 * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
 *
 * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
 *
 * 你可以认为每种硬币的数量是无限的。
 *
 *
 *
 * 示例 1：
 *
 * 输入：coins = [1, 2, 5], amount = 11
 * 输出：3
 * 解释：11 = 5 + 5 + 1
 * 示例 2：
 *
 * 输入：coins = [2], amount = 3
 * 输出：-1
 * 示例 3：
 *
 * 输入：coins = [1], amount = 0
 * 输出：0
 *
 *
 * 提示：
 *
 * 1 <= coins.length <= 12
 * 1 <= coins[i] <= 2^31 - 1
 * 0 <= amount <= 10^4
 *
 */
public class CoinChange {
    /**
     * 动态规划
     * 时间复杂度：O(Sn) S是金额，n是硬币数
     * 空间复杂度：O(S)
     *
     * 状态转移方程：
     *
     * dp[i] = dp[i - cj] + 1
     *
     * cj 代表的是第 j 枚硬币的面值
     *
     * 题解：https://leetcode.cn/problems/coin-change/solution/322-ling-qian-dui-huan-by-leetcode-solution/
     *
     * @param coins
     * @param amount
     * @return
     */
    public static int coinChange(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }

        int len = coins.length;
        if (len == 0) {
            return -1;
        }

        int max = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);
        dp[0] = 0;

        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }

        return dp[amount] > amount ? -1 : dp[amount];
    }

    /**
     * 记忆化递归
     * 时间复杂度：O(Sn)
     * 空间复杂度：O(S)
     * @param coins
     * @param amount
     * @return
     */
    public static int coinChange2(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }

        int len = coins.length;
        if (len == 0) {
            return -1;
        }

        return coinChange22(coins, amount, new int[amount + 1]);
    }

    private static int coinChange22(int[] coins, int amount, int[] count) {
        if (amount < 0) {
            return -1;
        }

        if (amount == 0) {
            return 0;
        }

        if (count[amount - 1] != 0) {
            return count[amount - 1];
        }

        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int res = coinChange22(coins, amount - coin, count);
            if (res >= 0 && res < min) {
                min = res + 1;
            }
        }

        count[amount - 1] = (min == Integer.MAX_VALUE) ? -1 : min;
        return count[amount - 1];
    }

    public static void main(String[] args) {
        int[] coins = new int[]{1, 2, 3};
        int amount = 4;
        System.out.println(coinChange(coins, amount));
        System.out.println(coinChange2(coins, amount));
    }
}
