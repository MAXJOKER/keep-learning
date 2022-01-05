package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * author zhangjunhui
 * date 2022-01-02 13:23
 * https://leetcode-cn.com/problems/find-all-duplicates-in-an-array/
 *
 * 442. 数组中重复的数据
 * 给你一个长度为 n 的整数数组 nums ，其中 nums 的所有整数都在范围 [1, n] 内，且每个整数出现 一次 或 两次 。请你找出所有出现 两次 的整数，并以数组形式返回。
 *
 * 你必须设计并实现一个时间复杂度为 O(n) 且仅使用常量额外空间的算法解决此问题。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [4,3,2,7,8,2,3,1]
 * 输出：[2,3]
 * 示例 2：
 *
 * 输入：nums = [1,1,2]
 * 输出：[1]
 * 示例 3：
 *
 * 输入：nums = [1]
 * 输出：[]
 */
public class FindAllDuplicatesInAnArray {
    /**
     * 解题思路
     * 找到数字i时，将位置i-1处的数字翻转为负数。
     * 如果位置i-1 上的数字已经为负，则i是出现两次的数字。
     * @param nums
     * @return
     */
    public static List<Integer> findDuplicates(int[] nums) {
        List<Integer> result = new ArrayList<Integer>();

        for (int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i]) - 1;
            if (nums[index] < 0) {
                result.add(Math.abs(nums[i]));
            } else {
                nums[index] = -nums[index];
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,1,2};
        System.out.println(findDuplicates(nums));
    }
}
