package leetcode.dynamicprogramming;

/**
 * @author maxjoker
 * @date 2022-04-29 23:56
 *
 * https://leetcode-cn.com/problems/maximum-product-subarray/
 *
 * 152. 乘积最大子数组
 * 给你一个整数数组 nums ，请你找出数组中乘积最大的非空连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
 *
 * 测试用例的答案是一个 32-位 整数。
 *
 * 子数组 是数组的连续子序列。
 *
 *
 *
 * 示例 1:
 *
 * 输入: nums = [2,3,-2,4]
 * 输出: 6
 * 解释: 子数组 [2,3] 有最大乘积 6。
 * 示例 2:
 *
 * 输入: nums = [-2,0,-1]
 * 输出: 0
 * 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
 *
 *
 * 提示:
 *
 * 1 <= nums.length <= 2 * 10^4
 * -10 <= nums[i] <= 10
 * nums 的任何前缀或后缀的乘积都 保证 是一个 32-位 整数
 *
 */
public class MaximumProductSubarray {
    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     *
     * 题解
     *
     * 状态转移方程：dp[i] = Math.max(nums[i], dp[i - 1] * nums[i]);
     *
     * 但 nums[i] 可能为负数
     * 所以需要维护两个状态
     * 用 0 表示遍历的过程中得到的以 nums[i] 结尾的连续子序列的乘积的最小值；
     * 用 1 表示遍历的过程中得到的以 nums[i] 结尾的连续子序列的乘积的最大值。
     *
     * @param nums
     * @return
     */
    public static int maxProduct(int[] nums) {
        int len = nums.length;

        if (len == 1) {
            return nums[0];
        }
        int[][] dp = new int[len][2];
        dp[0][0] = nums[0];
        dp[0][1] = nums[0];
        for (int i = 1; i < len; i++) {
            if (nums[i] >= 0) {
                dp[i][1] = Math.max(nums[i], dp[i - 1][1] * nums[i]);
                dp[i][0] = Math.min(nums[i], dp[i - 1][0] * nums[i]);
            } else {
                dp[i][1] = Math.max(nums[i], dp[i - 1][0] * nums[i]);
                dp[i][0] = Math.min(nums[i], dp[i - 1][1] * nums[i]);
            }
        }

        int result = dp[0][1];
        for (int i = 0; i < len; i++) {
            result = Math.max(result, dp[i][1]);
        }

        return result;
    }

    public static void main(String[] args) {
//        int[] nums = new int[]{2, -3, 2, 4};
        int[] nums = new int[]{-2, 3, -4};
        System.out.println(maxProduct(nums));
    }
}
