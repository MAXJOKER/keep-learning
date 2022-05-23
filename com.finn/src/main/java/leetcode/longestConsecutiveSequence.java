package leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author maxjoker
 * @date 2022-05-24 00:11
 *
 * https://leetcode.cn/problems/longest-consecutive-sequence
 *
 * 128. 最长连续序列
 * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 *
 * 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [100,4,200,1,3,2]
 * 输出：4
 * 解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
 * 示例 2：
 *
 * 输入：nums = [0,3,7,2,5,8,4,6,0,1]
 * 输出：9
 *
 *
 * 提示：
 *
 * 0 <= nums.length <= 10^5
 * -10^9 <= nums[i] <= 10^9
 *
 */
public class longestConsecutiveSequence {
    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     * @param nums
     * @return
     */
    public static int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int longest = 0;

        for (int num : nums) {
            if (set.remove(num)) {
                int currentLongest = 1;
                int current = num;
                while (set.remove(current - 1)) {
                    current--;
                }
                currentLongest += num - current;

                current = num;
                while (set.remove(current + 1)) {
                    current++;
                }
                currentLongest += current - num;
                longest = Math.max(longest, currentLongest);
            }
        }

        return longest;
    }

    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     * @param nums
     * @return
     */
    public static int method2(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        int longest = 0;
        for (int num : nums) {
            if (!set.contains(num - 1)) {
                int current = num;
                int currentStreak = 1;

                while (set.contains(current + 1)) {
                    current++;
                    currentStreak++;
                }

                longest = Math.max(currentStreak, longest);
            }
        }

        return longest;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{100,4,200,1,3,2};
        System.out.println(longestConsecutive(nums));
        System.out.println(method2(nums));
    }
}
