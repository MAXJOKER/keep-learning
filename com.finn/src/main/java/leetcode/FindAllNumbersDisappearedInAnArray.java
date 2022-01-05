package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * author zhangjunhui
 * date 2022-01-02 14:00
 * https://leetcode-cn.com/problems/find-all-numbers-disappeared-in-an-array/
 *
 * 448. 找到所有数组中消失的数字
 * 给你一个含 n 个整数的数组 nums ，其中 nums[i] 在区间 [1, n] 内。请你找出所有在 [1, n] 范围内但没有出现在 nums 中的数字，并以数组的形式返回结果。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [4,3,2,7,8,2,3,1]
 * 输出：[5,6]
 * 示例 2：
 *
 * 输入：nums = [1,1]
 * 输出：[2]
 */
public class FindAllNumbersDisappearedInAnArray {

    /**
     * 核心思想：原地哈希后检查，nums[i] = 3，则 3 重新存放在nums[i - 1] 中
     * @param nums
     * @return
     */
    public static List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> result = new ArrayList<Integer>();
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            while (nums[nums[i] - 1] != nums[i]) {
                swap(nums, nums[i] - 1, i);
            }
        }

        for (int i = 0; i < length; i++) {
            if (nums[i] != i + 1) {
                result.add(i+1);
            }
        }

        return result;
    }

    public static void swap(int[] nums, int idx1, int idx2) {
        int temp = nums[idx1];
        nums[idx1] = nums[idx2];
        nums[idx2] = temp;
    }

    /**
     * 思路
     *
     * 遍历每个元素，对索引进行标记，将对应索引位置的值变为负数；
     * 遍历下索引，看看哪些索引位置上的数不是负数的。位置上不是负数的索引，对应的元素就是不存在的。
     *
     * @param nums
     * @return
     */
    public static List<Integer> findDisappearedNumbers2(int[] nums) {
        List<Integer> result = new ArrayList<Integer>();
        for (int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i]) - 1;
            if (nums[index] > 0) nums[index] = -nums[index];
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                result.add(i+1);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{4,3,2,7,8,2,3,1};
        System.out.println(findDisappearedNumbers2(nums));
    }
}
