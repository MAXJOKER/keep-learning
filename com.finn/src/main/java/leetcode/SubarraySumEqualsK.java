package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author maxjoker
 * @date 2022-08-18 21:19
 *
 * https://leetcode.cn/problems/subarray-sum-equals-k/
 *
 * 560. 和为 K 的子数组
 * 给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的连续子数组的个数 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,1,1], k = 2
 * 输出：2
 * 示例 2：
 *
 * 输入：nums = [1,2,3], k = 3
 * 输出：2
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 2 * 10^4
 * -1000 <= nums[i] <= 1000
 * -107 <= k <= 10^7
 *
 */
public class SubarraySumEqualsK {
    /**
     * 暴力解法
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(1)
     * @param nums
     * @param k
     * @return
     */
    public static int subarraySum(int[] nums, int k) {
        int length = nums.length;
        int count = 0;

        for (int i = 0; i < length; i++) {
            int sum = 0;
            for (int j = i; j < length; j++) {
                sum += nums[j];
                if (sum == k) {
                    count++;
                }
            }
        }

        return count;
    }

    /**
     * 前缀和
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     * https://leetcode.cn/problems/subarray-sum-equals-k/solution/he-wei-kde-zi-shu-zu-by-leetcode-solution/
     * @param nums
     * @param k
     * @return
     */
    public static int solution(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int pre = 0;
        int count = 0;

        for (int num : nums) {
            pre += num;
            // 如果前面某些数字之和加上这个数字正好等于K（存在一个数字加上num结果为K说明找到了
            if (map.containsKey(pre - k)) {
                count += map.get(pre - k);
            }

            map.put(pre, map.getOrDefault(pre, 0) + 1);
        }

        return count;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3};
        int k = 3;

        System.out.println(subarraySum(nums, k));
    }
}
