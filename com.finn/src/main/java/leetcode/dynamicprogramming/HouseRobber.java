package leetcode.dynamicprogramming;

/**
 * @author maxjoker
 * @date 2022-04-20 21:55
 *
 * https://leetcode-cn.com/problems/house-robber/
 *
 * 198. 打家劫舍
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，
 * 如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 *
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 *
 *
 *
 * 示例 1：
 *
 * 输入：[1,2,3,1]
 * 输出：4
 * 解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
 *      偷窃到的最高金额 = 1 + 3 = 4 。
 * 示例 2：
 *
 * 输入：[2,7,9,3,1]
 * 输出：12
 * 解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
 *      偷窃到的最高金额 = 2 + 9 + 1 = 12 。
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 400
 *
 */
public class HouseRobber {
    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     * @param nums
     * @return
     */
    public static int rob(int[] nums) {
        int len = nums.length;

        if (len == 0) {
            return 0;
        }

        if (len == 1) {
            return nums[0];
        }

        // 状态定义 dp[i] 表示子区间 [0, i] 在不触发警报的情况下，偷窃到的最高金额
        // 状态转移方程：
        // (1) 偷 nums[i]
        // (2) 不偷 nums[i]
        int[] dp = new int[len];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < len; i++) {
            // 在 不偷 nums[i]（总金额为前k-1间房的金额） 与 偷 nums[i]（总金额为 前k-2间房金额 + i） 中选择一个最大值
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }

        return dp[len - 1];
    }

    /**
     * 滚动数组
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     * @param nums
     * @return
     */
    public static int rob2(int[] nums) {
        int len = nums.length;

        if (len == 0) {
            return 0;
        }

        if (len == 1) {
            return nums[0];
        }

        int first = nums[0];
        int second = Math.max(nums[0], nums[1]);

        for (int i = 2; i < len; i++) {
            int temp = second;
            second = Math.max(first + nums[i], second);
            first = temp;
        }

        return second;
    }

    /**
     * 有哨兵
     * @param nums
     * @return
     */
    public static int rob3(int[] nums) {
        int len = nums.length;

        if (len == 0) {
            return 0;
        }

        if (len == 1) {
            return nums[0];
        }

        // 0 - 不偷
        // 1 - 偷
        // len + 1 多加1天表示哨兵，相应地要做一些偏移（因为没有第0天这个说法）
        int[][] dp = new int[len + 1][2];
        for (int i = 1; i <= len; i++) {
            // 今天不偷，那么昨天可能偷，也可能不偷
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]);

            // 今天偷，昨天肯定不能偷
            // 这里要做偏移，dp中的第一天，对应nums中的i-1
            dp[i][1] = dp[i - 1][0] + nums[i - 1];
        }

        return Math.max(dp[len][0], dp[len][1]);
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2, 7, 9, 3, 1};
        System.out.println(rob3(nums));
    }
}
