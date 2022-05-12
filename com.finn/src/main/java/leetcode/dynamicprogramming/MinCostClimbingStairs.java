package leetcode.dynamicprogramming;

/**
 * @author maxjoker
 * @date 2022-05-12 21:41
 *
 * https://leetcode.cn/problems/min-cost-climbing-stairs/
 *
 * 746. 使用最小花费爬楼梯
 * 给你一个整数数组 cost ，其中 cost[i] 是从楼梯第 i 个台阶向上爬需要支付的费用。一旦你支付此费用，即可选择向上爬一个或者两个台阶。
 *
 * 你可以选择从下标为 0 或下标为 1 的台阶开始爬楼梯。
 *
 * 请你计算并返回达到楼梯顶部的最低花费。
 *
 *
 *
 * 示例 1：
 *
 * 输入：cost = [10,15,20]
 * 输出：15
 * 解释：你将从下标为 1 的台阶开始。
 * - 支付 15 ，向上爬两个台阶，到达楼梯顶部。
 * 总花费为 15 。
 * 示例 2：
 *
 * 输入：cost = [1,100,1,1,1,100,1,1,100,1]
 * 输出：6
 * 解释：你将从下标为 0 的台阶开始。
 * - 支付 1 ，向上爬两个台阶，到达下标为 2 的台阶。
 * - 支付 1 ，向上爬两个台阶，到达下标为 4 的台阶。
 * - 支付 1 ，向上爬两个台阶，到达下标为 6 的台阶。
 * - 支付 1 ，向上爬一个台阶，到达下标为 7 的台阶。
 * - 支付 1 ，向上爬两个台阶，到达下标为 9 的台阶。
 * - 支付 1 ，向上爬一个台阶，到达楼梯顶部。
 * 总花费为 6 。
 *
 *
 * 提示：
 *
 * 2 <= cost.length <= 1000
 * 0 <= cost[i] <= 999
 *
 */
public class MinCostClimbingStairs {
    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     * 状态转移方程：
     * dp[len] = min(dp[len - 1], dp[len - 2])
     * 但是这里还涉及到花费cost，所以最终的状态转移方程是
     * dp[len] = min(dp[len - 1] + cost[len - 1], dp[len - 2] + cost[len - 2])
     * 啊我好蠢啊，妈的
     * @param cost
     * @return
     */
    public static int minCost(int[] cost) {
        int len = cost.length;
        int[] dp = new int[len + 1];

        for (int i = 2; i <= len; i++) {
            dp[i] += Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        }

        return dp[len];
    }

    public static void main(String[] args) {
        int[] cost = new int[]{1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
        minCost(cost);
    }
}
