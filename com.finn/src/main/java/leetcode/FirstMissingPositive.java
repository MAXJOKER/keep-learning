package leetcode;

/**
 * author: zhangjunhui
 * date: 2022-01-02 11:53
 * https://leetcode-cn.com/problems/first-missing-positive/
 * 41. 缺失的第一个正数
 * 给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。
 *
 * 请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,0]
 * 输出：3
 * 示例 2：
 *
 * 输入：nums = [3,4,-1,1]
 * 输出：2
 * 示例 3：
 *
 * 输入：nums = [7,8,9,11,12]
 * 输出：1
 */
public class FirstMissingPositive {
    /**
     * 核心思想：原地哈希，比如数值 3 ，保存到 index = 3 - 1 的下标 arr[2] = 3
     * for循环比对时，arr[i] == i + 1，如果不等，说明最小正整数便是 i + 1
     * @param nums
     * @return
     */
    public static int firstMissingPositive(int[] nums) {
        int length = nums.length;

        for (int i = 0; i < length; i++) {
            while(nums[i] > 0 && nums[i] <= length && nums[nums[i] - 1] != nums[i]) {
                swap(nums, nums[i] - 1, i);
            }
        }

        for (int i = 0; i < length; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }

        return length + 1;
    }

    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] nums = new int[] {1, -1, 4, 6, 9, 2};

        System.out.println(firstMissingPositive(nums));
    }
}
