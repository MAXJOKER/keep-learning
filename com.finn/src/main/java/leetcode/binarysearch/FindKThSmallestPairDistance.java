package leetcode.binarysearch;

import java.util.Arrays;

/**
 * @author maxjoker
 * @date 2022-04-12 18:24
 *
 * https://leetcode-cn.com/problems/find-k-th-smallest-pair-distance/
 *
 * 719. 找出第 k 小的距离对
 * 给定一个整数数组，返回所有数对之间的第 k 个最小距离。一对 (A, B) 的距离被定义为 A 和 B 之间的绝对差值。
 *
 * 示例 1:
 *
 * 输入：
 * nums = [1,3,1]
 * k = 1
 * 输出：0
 * 解释：
 * 所有数对如下：
 * (1,3) -> 2
 * (1,1) -> 0
 * (3,1) -> 2
 * 因此第 1 个最小距离的数对是 (1,1)，它们之间的距离为 0。
 * 提示:
 *
 * 2 <= len(nums) <= 10000.
 * 0 <= nums[i] < 1000000.
 * 1 <= k <= len(nums) * (len(nums) - 1) / 2
 *
 */
public class FindKThSmallestPairDistance {
    public static int smallestPairDistance(int[] nums, int k) {
        Arrays.sort(nums);
        int len = nums.length;
        int left = 0;
        int right = nums[len - 1] - nums[0];

        while (left < right) {
            int mid = left + (right - left) / 2;
            int count = countLessEquals(nums, mid);
            System.out.println("count: " + count);

            if (count < k) {
                // 说明mid选得太小了
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    /**
     * 统计距离小于等于mid的个数
     * @param nums
     * @param mid
     * @return
     */
    private static int countLessEquals(int[] nums, int mid) {
        int count = 0;
        int len = nums.length;
        int left = 0;

        for (int right = 0; right < len; right++) {
            // 滑动窗口
            while (nums[right] - nums[left] > mid) {
                left++;
            }

            count += right - left;
        }

        return count;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{38,33,57,65,13,2,86,75,4,56};
        System.out.println(smallestPairDistance(nums, 26));
    }
}
