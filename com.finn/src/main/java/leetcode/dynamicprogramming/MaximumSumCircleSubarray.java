package leetcode.dynamicprogramming;

/**
 * @author maxjoker
 * @date 2022-05-13 20:24
 *
 * https://leetcode.cn/problems/maximum-sum-circular-subarray/
 *
 * 918. 环形子数组的最大和
 * 给定一个长度为 n 的环形整数数组 nums ，返回 nums 的非空 子数组 的最大可能和 。
 *
 * 环形数组 意味着数组的末端将会与开头相连呈环状。形式上， nums[i] 的下一个元素是 nums[(i + 1) % n] ， nums[i] 的前一个元素是 nums[(i - 1 + n) % n] 。
 *
 * 子数组 最多只能包含固定缓冲区 nums 中的每个元素一次。形式上，对于子数组 nums[i], nums[i + 1], ..., nums[j] ，不存在 i <= k1, k2 <= j 其中 k1 % n == k2 % n 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,-2,3,-2]
 * 输出：3
 * 解释：从子数组 [3] 得到最大和 3
 * 示例 2：
 *
 * 输入：nums = [5,-3,5]
 * 输出：10
 * 解释：从子数组 [5,5] 得到最大和 5 + 5 = 10
 * 示例 3：
 *
 * 输入：nums = [3,-2,2,-3]
 * 输出：3
 * 解释：从子数组 [3] 和 [3,-2,2] 都可以得到最大和 3
 *
 *
 * 提示：
 *
 * n == nums.length
 * 1 <= n <= 3 * 10^4
 * -3 * 104 <= nums[i] <= 3 * 10^4
 *
 */
public class MaximumSumCircleSubarray {
    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     * 题解：https://leetcode.cn/problems/maximum-sum-circular-subarray/solution/wo-hua-yi-bian-jiu-kan-dong-de-ti-jie-ni-892u/
     * @param nums
     * @return
     */
    public static int maxSubarraySumCircular(int[] nums) {
        int total = 0; // 数组总和
        int maxSum = nums[0]; // 最大子数组和
        int currMax = 0; // 包含当前元素的最大子数组和
        int minSum = nums[0]; // 最小子数组和
        int currMin = 0; // 包含当前元素的最小子数组和

        for (int i : nums) {
            currMax = Math.max(currMax + i, i);
            maxSum = Math.max(maxSum, currMax);
            currMin = Math.min(currMin + i, i);
            minSum = Math.min(minSum, currMin);
            total += i;
        }

        return maxSum > 0 ? Math.max(maxSum, total - minSum) : maxSum;
    }

    public static void main(String[] args) {
//        int[] nums = new int[]{5, -3, 5};
        int[] nums = new int[]{1, -2, 3, -2};
        System.out.println(maxSubarraySumCircular(nums));
    }
}
