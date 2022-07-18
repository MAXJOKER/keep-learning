package leetcode.slidingwindow;

import java.util.Arrays;

/**
 * @author maxjoker
 * @date 2022-07-19 00:37
 *
 * https://leetcode.cn/problems/minimum-operations-to-reduce-x-to-zero/
 *
 * 1658. 将 x 减到 0 的最小操作数
 * 给你一个整数数组 nums 和一个整数 x 。每一次操作时，你应当移除数组 nums 最左边或最右边的元素，然后从 x 中减去该元素的值。请注意，需要 修改 数组以供接下来的操作使用。
 *
 * 如果可以将 x 恰好 减到 0 ，返回 最小操作数 ；否则，返回 -1 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,1,4,2,3], x = 5
 * 输出：2
 * 解释：最佳解决方案是移除后两个元素，将 x 减到 0 。
 * 示例 2：
 *
 * 输入：nums = [5,6,7,8,9], x = 4
 * 输出：-1
 * 示例 3：
 *
 * 输入：nums = [3,2,20,1,1,3], x = 10
 * 输出：5
 * 解释：最佳解决方案是移除后三个元素和前两个元素（总共 5 次操作），将 x 减到 0 。
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^4
 * 1 <= x <= 10^9
 *
 */
public class MinimumOperationsToReduceXToZero {
    /**
     * 滑动窗口
     * 不是固定长度，是固定保留值的滑动窗口
     *
     * 这里保留值 = sum(数组) - x
     *
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     * @param nums
     * @param x
     * @return
     */
    public static int minOperations(int[] nums, int x) {
        int sum = Arrays.stream(nums).sum() - x;
        if (sum < 0) {
            return -1;
        }

        int right = 0;
        int left = 0;
        int windowSum = 0;
        int maxLength = -1;

        while (right < nums.length) {
            windowSum += nums[right];

            while (windowSum > sum) {
                windowSum -= nums[left];
                left++;
            }

            if (windowSum == sum) {
                maxLength = Math.max(maxLength, right - left + 1);
            }

            right++;
        }

        if (maxLength == -1) {
            return -1;
        }

        return nums.length - maxLength;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,1,4,2,3};
        int x = 5;
        System.out.println(minOperations(nums, x));
    }
}
