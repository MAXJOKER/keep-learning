package leetcode.dynamicprogramming;

/**
 *
 * @author maxjoker
 * @date 2022-06-08 00:32
 *
 * 309. 最佳买卖股票时机含冷冻期
 * 给定一个整数数组prices，其中第  prices[i] 表示第 i 天的股票价格 。​
 *
 * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
 *
 * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 *
 *
 * 示例 1:
 *
 * 输入: prices = [1,2,3,0,2]
 * 输出: 3
 * 解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
 * 示例 2:
 *
 * 输入: prices = [1]
 * 输出: 0
 *
 *
 * 提示：
 *
 * 1 <= prices.length <= 5000
 * 0 <= prices[i] <= 1000
 */
public class BestTimeToBuyAndSellStockWithCooldown {

    /**
     * 动态规划
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     * @param prices
     * @return
     */
    public static int maxProfit(int[] prices) {
        if (prices.length < 2) {
            return 0;
        }

        int[][] dp = new int[prices.length][3];
        /**
         * dp[i][0] 手上不持有股票，并且今天不是由于卖出股票而不持股，我们拥有的现金数
         *
         * 1. 昨天不持股，今天什么都不做；
         * 2. 昨天是冷冻期，今天什么也不做
         */
        dp[0][0] = 0;
        /**
         * dp[i][1]
         * 规定了今天持股，有以下两种情况：
         *
         * 1. 昨天持股，今天什么都不做；
         * 2. 昨天不持股，今天买入
         */
        dp[0][1] = -prices[0];

        /**
         * dp[i][2] 今天不持股，并且今天是由于卖出了股票而不持股
         */
        dp[0][2] = 0;

        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][2]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
            dp[i][2] = dp[i - 1][1] + prices[i];
        }

        // 由于全部交易结束后，持有股票的收益一定低于不持有股票的收益，
        // 因此这时候 dp[n-1][0] 的收益必然是大于 dp[n−1][1] 的，
        // 最后的答案即为 dp[n−1][0], dp[n - 1][2] 中的最大值
        return Math.max(dp[prices.length - 1][0], dp[prices.length - 1][2]);
    }

    /**
     * 贪心算法
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * 贪心算法: 在每一步总是做出在当前看来最好的选择。
     * 这道题 「贪心」 的地方在于，对于 「今天的股价 - 昨天的股价」，
     * 得到的结果有 3 种可能：
     *      ① 正数
     *      ② 0
     *      ③负数
     * 贪心算法的决策是： 只加正数
     *
     * @param prices
     * @return
     */
    public static int maxProfit2(int[] prices) {
        int ans = 0;
        for (int i = 1; i < prices.length; i++) {
            ans += Math.max(0, prices[i] - prices[i - 1]);
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] prices = {1, 2, 3, 0, 2};
        System.out.println(maxProfit(prices));
    }
}
