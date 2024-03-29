package leetcode.dynamicprogramming;

/**
 * 121. 买卖股票的最佳时机
 * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
 *
 * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
 *
 * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：[7,1,5,3,6,4]
 * 输出：5
 * 解释：在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 *      注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
 * 示例 2：
 *
 * 输入：prices = [7,6,4,3,1]
 * 输出：0
 * 解释：在这种情况下, 没有交易完成, 所以最大利润为 0。
 *
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/
 */
public class BestTimeToBuyAndSellStock {

    /**
     * 超时了
     * @param prices
     * @return
     */
    public static int maxProfit(int[] prices) {
        int result = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                int ans = prices[j] - prices[i];
                if (ans < 0) {
                    continue;
                }

                result = Math.max(ans, result);
            }
        }

        return result;
    }

    /**
     * 动态规划
     * 1、记录【今天之前买入的最小值】
     * 2、计算【今天之前最小值买入，今天卖出的获利】，也即【今天卖出的最大获利】
     * 3、比较【每天的最大获利】，取最大值即可
     * @param prices
     * @return
     */
    public static int maxProfit2(int[] prices) {
        int maxProfit = 0;
        int minPrice = prices[0];

        for (int i = 1; i < prices.length; i++) {
            maxProfit = Math.max(maxProfit, prices[i] - minPrice);
            minPrice = Math.min(minPrice, prices[i]);
        }

        return maxProfit;
    }

    /**
     * 状态：
     * dp[i][0] 今天不持股
     *      （1）昨天不持股
     *      （2）昨天买入，今天卖出
     *
     * dp[i][1] 今天持股
     *      （1）昨天不持股，今天买入
     *      （2）昨天买入
     *
     * 状态转移方程：
     *      dp[i][0] = max(dp[i - 1][0], dp[i - 1][1] + price[i])
     *      dp[i][1] = max(dp[i - 1][1], -price[i])
     *
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     * @param prices
     * @return
     */
    public static int maxProfit3(int[] prices) {
        int len = prices.length;
        int[][] dp = new int[len][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];

        for (int i = 1; i < len; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(-prices[i], dp[i - 1][1]);
        }

        return dp[len - 1][0];
    }

    public static void main(String[] args) {
        int[] prices = {1,6,4,3,1};
        System.out.println(maxProfit2(prices));
        System.out.println(maxProfit3(prices));
    }
}
