package leetcode.slidingwindow;

/**
 * @author maxjoker
 * @date 2022-06-28 21:13
 *
 * https://leetcode.cn/problems/minimum-size-subarray-sum/
 *
 * 209. 长度最小的子数组
 * 给定一个含有 n 个正整数的数组和一个正整数 target 。
 *
 * 找出该数组中满足其和 ≥ target 的长度最小的 连续子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，
 * 并返回其长度。如果不存在符合条件的子数组，返回 0 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：target = 7, nums = [2,3,1,2,4,3]
 * 输出：2
 * 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
 * 示例 2：
 *
 * 输入：target = 4, nums = [1,4,4]
 * 输出：1
 * 示例 3：
 *
 * 输入：target = 11, nums = [1,1,1,1,1,1,1,1]
 * 输出：0
 *
 *
 * 提示：
 *
 * 1 <= target <= 10^9
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^5
 *
 *
 * 进阶：
 *
 * 如果你已经实现 O(n) 时间复杂度的解法, 请尝试设计一个 O(n log(n)) 时间复杂度的解法。
 *
 */
public class MinimumSizeSubarraySum {
    /**
     * 暴力解法
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(1)
     * 超时了
     * @param target
     * @param nums
     * @return
     */
    public static int minSubArrayLen(int target, int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return 0;
        }

        int res = Integer.MAX_VALUE;
        for (int i = 0; i < len; i++) {
            int sum = nums[i];
            if (sum >= target) {
                return 1;
            }
            for (int j = i + 1; j < len; j++) {
                sum += nums[j];
                if (sum >= target) {
                    res = Math.min(res, j - i + 1);
                    break;
                }
            }
        }

        return res == Integer.MAX_VALUE ? 0 : res;
    }

    /**
     * 滑动窗口
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     * @param target
     * @param nums
     * @return
     */
    public static int solution2(int target, int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return 0;
        }

        int left = 0;
        int right = 0;
        int sum = 0;

        int minLen = Integer.MAX_VALUE;

        while (right < len) {
            sum += nums[right];
            right++;

            // 窗口计算
            while (sum >= target) {
                sum -= nums[left];
                minLen = Math.min(minLen, right - left);
                left++;
            }
        }

        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }

    /**
     * 前缀和 + 二分查找
     * 时间复杂度：O(nLogn)
     * 空间复杂度：O(n)
     *
     * https://leetcode.cn/problems/minimum-size-subarray-sum/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-43/
     *
     * @param target
     * @param nums
     * @return
     */
    public static int solution3(int target, int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return 0;
        }

        // preSum 表示 sum(nums[0..i))
        int[] preSum = new int[len + 1];
        preSum[0] = 0;
        for (int i = 0; i < len + 1; i++) {
            preSum[i + 1] = preSum[i] + nums[i];
        }

        int minLen = Integer.MAX_VALUE;
        // 遍历一次，找到和大于等于 s 的最大下标
        for (int i = 0; i < len; i++) {
            // 对于前缀和数组来说，有 1 个位置的偏移，找使得区间和 sum[left..right] >= s 的最大的 left
            int left = 0;
            int right = i;
            while (left < right) {
                int mid = (left + right + 1) >> 1;
                // 什么时候解我们不需要呢，sum(nums[mid..i]) < s
                if (preSum[right] - preSum[mid] < target) {
                    right = mid - 1;
                } else {
                    left = mid;
                }
            }

            if (preSum[i + 1] - preSum[left] >= target) {
                minLen = Math.min(minLen, i - left + 1);
            }
        }

        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }

    public static void main(String[] args) {
        int target = 6;
        int[] nums = new int[]{1, 2, 3, 4, 5};

        System.out.println(minSubArrayLen(target, nums));
        System.out.println(solution2(target, nums));
    }
}
