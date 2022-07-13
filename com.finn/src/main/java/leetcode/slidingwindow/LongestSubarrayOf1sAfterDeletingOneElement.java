package leetcode.slidingwindow;

/**
 * @author maxjoker
 * @date 2022-07-13 23:13
 *
 * https://leetcode.cn/problems/longest-subarray-of-1s-after-deleting-one-element/
 *
 * 1493. 删掉一个元素以后全为 1 的最长子数组
 * 给你一个二进制数组 nums ，你需要从中删掉一个元素。
 *
 * 请你在删掉元素的结果数组中，返回最长的且只包含 1 的非空子数组的长度。
 *
 * 如果不存在这样的子数组，请返回 0 。
 *
 *
 *
 * 提示 1：
 *
 * 输入：nums = [1,1,0,1]
 * 输出：3
 * 解释：删掉位置 2 的数后，[1,1,1] 包含 3 个 1 。
 * 示例 2：
 *
 * 输入：nums = [0,1,1,1,0,1,1,0,1]
 * 输出：5
 * 解释：删掉位置 4 的数字后，[0,1,1,1,1,1,0,1] 的最长全 1 子数组为 [1,1,1,1,1] 。
 * 示例 3：
 *
 * 输入：nums = [1,1,1]
 * 输出：2
 * 解释：你必须要删除一个元素。
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 105
 * nums[i] 要么是 0 要么是 1 。
 *
 */
public class LongestSubarrayOf1sAfterDeletingOneElement {
    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     * @param nums
     * @return
     */
    public static int longestSubarray(int[] nums) {
        int right = 0;
        int left = 0;
        int max = 0;
        int zeros = 0;

        while (right < nums.length) {
            if (nums[right] == 0) {
                zeros++;
            }

            // 0 在区间中只能出现1次，因此需要把zeros的值控制为1
            while (zeros > 1) {
                if (nums[left] == 0) {
                    zeros--;
                }
                left++;
            }

            max = Math.max(max, right - left + 1);
            right++;
        }

        // 上面是把 0 变成 1，而题目是求只有1的子数组长度，所以要减1
        return max - 1;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{0,1,1,1,0,1,1,0,1};
        System.out.println(longestSubarray(nums));
    }
}
