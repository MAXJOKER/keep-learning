package leetcode.slidingwindow;

/**
 * @author maxjoker
 * @date 2022-07-17 14:40
 *
 * https://leetcode.cn/problems/longest-turbulent-subarray/s
 *
 * 978. 最长湍流子数组
 * 给定一个整数数组 arr ，返回 arr 的 最大湍流子数组的长度 。
 *
 * 如果比较符号在子数组中的每个相邻元素对之间翻转，则该子数组是 湍流子数组 。
 *
 * 更正式地来说，当 arr 的子数组 A[i], A[i+1], ..., A[j] 满足仅满足下列条件时，我们称其为湍流子数组：
 *
 * 若 i <= k < j ：
 * 当 k 为奇数时， A[k] > A[k+1]，且
 * 当 k 为偶数时，A[k] < A[k+1]；
 * 或 若 i <= k < j ：
 * 当 k 为偶数时，A[k] > A[k+1] ，且
 * 当 k 为奇数时， A[k] < A[k+1]。
 *
 *
 * 示例 1：
 *
 * 输入：arr = [9,4,2,10,7,8,8,1,9]
 * 输出：5
 * 解释：arr[1] > arr[2] < arr[3] > arr[4] < arr[5]
 * 示例 2：
 *
 * 输入：arr = [4,8,12,16]
 * 输出：2
 * 示例 3：
 *
 * 输入：arr = [100]
 * 输出：1
 *
 *
 * 提示：
 *
 * 1 <= arr.length <= 4 * 104
 * 0 <= arr[i] <= 109
 *
 */
public class LongestTurbulentSubarray {
    /**
     * 滑动窗口
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     * @param nums
     * @return
     */
    public static int maxTurbulenceSize(int[] nums) {
        int len = nums.length;
        int left = 0;
        int right = 0;
        int res = 0;

        while (right < len - 1) {
            if (left == right) {
                if (nums[left] == nums[left + 1]) {
                    left++;
                }

                right++;
            } else {
                if (nums[right - 1] < nums[right] && nums[right] > nums[right + 1]) {
                    right++;
                } else if (nums[right - 1] > nums[right] && nums[right] < nums[right + 1]) {
                    right++;
                } else {
                    left = right;
                }
            }

            res = Math.max(res, right - left + 1);
        }

        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{9,4,2,10,7,8,8,1,9};
        System.out.println(maxTurbulenceSize(nums));
    }
}
