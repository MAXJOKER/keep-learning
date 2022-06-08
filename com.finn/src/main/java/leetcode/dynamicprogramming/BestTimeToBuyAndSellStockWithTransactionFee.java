package leetcode.dynamicprogramming;

/**
 * @author maxjoker
 * @date 2022-06-08 18:29
 *
 * 714. 买卖股票的最佳时机含手续费
 * 给定一个整数数组 prices，其中 prices[i]表示第 i 天的股票价格 ；整数 fee 代表了交易股票的手续费用。
 *
 * 你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
 *
 * 返回获得利润的最大值。
 *
 * 注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。
 *
 *
 *
 * 示例 1：
 *
 * 输入：prices = [1, 3, 2, 8, 4, 9], fee = 2
 * 输出：8
 * 解释：能够达到的最大利润:
 * 在此处买入 prices[0] = 1
 * 在此处卖出 prices[3] = 8
 * 在此处买入 prices[4] = 4
 * 在此处卖出 prices[5] = 9
 * 总利润: ((8 - 1) - 2) + ((9 - 4) - 2) = 8
 * 示例 2：
 *
 * 输入：prices = [1,3,7,5,10,3], fee = 3
 * 输出：6
 *
 *
 * 提示：
 *
 * 1 <= prices.length <= 5 * 104
 * 1 <= prices[i] < 5 * 104
 * 0 <= fee < 5 * 104
 *
 */
public class BestTimeToBuyAndSellStockWithTransactionFee {
    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     *
     * 状态转移方程：
     *
     *   dp[i][0] 今天不持有
     *      1. 昨天不持有 dp[i - 1][0]
     *      2. 昨天持有，今天卖出 dp[i - 1][1] + prices[i]
     *
     *   dp[i][1] 今天持有
     *      1. 昨天持有 dp[i - 1][1]
     *      2. 昨天不持有，今天买入 dp[i - 1][0] - prices[i]
     *
     *   妈了个鸡，终于自己做出来一道题了，加油，不要放弃
     *
     * @param prices
     * @param fee
     * @return
     */
    public static int maxProfit(int[] prices, int fee) {
        int len = prices.length;

        int[][] dp = new int[len][2];

        dp[0][0] = 0;
        dp[0][1] = -prices[0];

        for (int i = 1; i < len; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i] - fee);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }

        return dp[len - 1][0];
    }

    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     * 滚动数组
     * @param prices
     * @param fee
     * @return
     */
    public static int maxProfit2(int[] prices, int fee) {
        int len = prices.length;

        int[] dp = new int[2];

        dp[1] = -prices[0];

        for (int i = 1; i < len; i++) {
            dp[0] = Math.max(dp[0], dp[1] + prices[i] - fee);
            dp[1] = Math.max(dp[1], dp[0] - prices[i]);
        }

        return dp[0];
    }

    /**
     * 贪心算法
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     * 官方题解：https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/solution/mai-mai-gu-piao-de-zui-jia-shi-ji-han-sh-rzlz/
     *
     * @param prices
     * @param fee
     * @return
     */
    public static int maxProfit3(int[] prices, int fee) {
        int buy = prices[0] + fee;
        int profit = 0;

        for (int i = 1; i < prices.length; i++) {
            if (prices[i] + fee < buy) {
                buy = prices[i] + fee;
            } else if (prices[i] > buy) {
                profit += prices[i] - buy;
                buy = prices[i];
            }
        }

        return profit;
    }

    public static void main(String[] args) {
        int[] prices = new int[]{1, 3, 7, 5, 10, 3};
        int fee = 3;
        System.out.println(maxProfit(prices, fee));
        System.out.println(maxProfit2(prices, fee));
        System.out.println(maxProfit3(prices, fee));
    }
}
