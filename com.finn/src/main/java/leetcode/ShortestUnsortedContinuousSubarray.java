package leetcode;

/**
 * @author maxjoker
 * @date 2022-08-25 00:02
 *
 * https://leetcode.cn/problems/shortest-unsorted-continuous-subarray
 *
 * 581. 最短无序连续子数组
 * 给你一个整数数组 nums ，你需要找出一个 连续子数组 ，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。
 *
 * 请你找出符合题意的 最短 子数组，并输出它的长度。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [2,6,4,8,10,9,15]
 * 输出：5
 * 解释：你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。
 * 示例 2：
 *
 * 输入：nums = [1,2,3,4]
 * 输出：0
 * 示例 3：
 *
 * 输入：nums = [1]
 * 输出：0
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 10^4
 * -10^5 <= nums[i] <= 10^5
 *
 */
public class ShortestUnsortedContinuousSubarray {
    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     * @param nums
     * @return
     */
    public static int findUnsortedSubarray(int[] nums) {
        int len = nums.length;
        int left = 0;
        int right = 0;
        int max = nums[0];
        int min = nums[len - 1];

        for (int i = 0; i < len - 1; i++) {
            max = Math.max(nums[i], max);
            if (nums[i + 1] < max) {
                right = i + 1;
            }
        }

        for (int i = len - 1; i > 0; i--) {
            min = Math.min(nums[i], min);
            if (nums[i - 1] > min) {
                left = i - 1;
            }
        }

        return left == right ? 0 : right - left + 1;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2, 6, 4, 8, 10, 9, 15};
        System.out.println(findUnsortedSubarray(nums));
    }
}
