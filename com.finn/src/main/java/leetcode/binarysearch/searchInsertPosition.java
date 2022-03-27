package leetcode.binarysearch;

/**
 * @author maxjoker
 * @date 2022-03-27 21:09
 *
 * https://leetcode-cn.com/problems/search-insert-position/
 *
 * 35. 搜索插入位置
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 *
 * 请必须使用时间复杂度为 O(log n) 的算法。
 *
 *
 *
 * 示例 1:
 *
 * 输入: nums = [1,3,5,6], target = 5
 * 输出: 2
 * 示例 2:
 *
 * 输入: nums = [1,3,5,6], target = 2
 * 输出: 1
 * 示例 3:
 *
 * 输入: nums = [1,3,5,6], target = 7
 * 输出: 4
 *
 * 提示:
 *
 * 1 <= nums.length <= 104
 * -104 <= nums[i] <= 104
 * nums 为无重复元素的升序排列数组
 * -104 <= target <= 104
 *
 */
public class searchInsertPosition {
    /**
     * 二分查找
     * 时间复杂度：O(logN)
     * 空间复杂度：O(1)
     * @param nums
     * @param target
     * @return
     */
    public static int searchInsert(int[] nums, int target) {
        int len = nums.length;

        int left = 0;
        int right = len - 1;
        // 如果下面循环都没进入过 num[mid] >= target，说明 target 比nums所有元素都大，直接插入末尾
        int ans = len;

        while (left <= right) {
            int mid = (right + left) / 2;
            if (nums[mid] >= target) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return ans;
    }

    /**
     * 暴力解法
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     * @param nums
     * @param target
     * @return
     */
    public static int searchInsert2(int[] nums, int target) {
        int len = nums.length;

        for (int i = 0; i < len; i++) {
            if (nums[i] >= target) {
                return i;
            }
        }

        return len;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, 5, 6};
        System.out.println(searchInsert(nums, 2));
    }
}
