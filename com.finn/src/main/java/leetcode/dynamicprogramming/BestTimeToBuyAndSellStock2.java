package leetcode.dynamicprogramming;

/**
 * 122. 买卖股票的最佳时机 II
 * 给定一个数组 prices ，其中 prices[i] 是一支给定股票第 i 天的价格。
 *
 * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
 *
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 *
 *
 * 示例 1:
 *
 * 输入: prices = [7,1,5,3,6,4]
 * 输出: 7
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 *      随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
 * 示例 2:
 *
 * 输入: prices = [1,2,3,4,5]
 * 输出: 4
 * 解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 *      注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
 * 示例 3:
 *
 * 输入: prices = [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 *
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/
 */
public class BestTimeToBuyAndSellStock2 {

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

        int[][] dp = new int[prices.length][2];
        /**
         * dp[i][0]规定了今天不持股，有以下两种情况：
         *
         * 1. 昨天不持股，今天什么都不做；
         * 2. 昨天持股，今天卖出股票（现金数增加）
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

        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            // 如果题目允许交易多次，就说明可以从直接从昨天的未持股状态变为今天的持股状态，
            // 因为昨天未持股状态可以代表之前买过又卖过后的状态，也就是之前交易过多次后的状态。
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }

        // 由于全部交易结束后，持有股票的收益一定低于不持有股票的收益，
        // 因此这时候 dp[n-1][0] 的收益必然是大于 dp[n−1][1] 的，
        // 最后的答案即为 dp[n−1][0]
        return dp[prices.length - 1][0];
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
        int[] prices = {7,1,5,3,6,4};
        System.out.println(maxProfit2(prices));
    }
}
