package leetcode.binarysearch;

import java.util.jar.JarEntry;

/**
 * @author maxjoker
 * @date 2022-04-05 12:32
 *
 * https://leetcode-cn.com/problems/search-in-rotated-sorted-array-ii/
 *
 * 81. 搜索旋转排序数组 II
 * 已知存在一个按非降序排列的整数数组 nums ，数组中的值不必互不相同。
 *
 * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转 ，
 * 使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。
 * 例如， [0,1,2,4,4,4,5,6,6,7] 在下标 5 处经旋转后可能变为 [4,5,6,6,7,0,1,2,4,4] 。
 *
 * 给你 旋转后 的数组 nums 和一个整数 target ，请你编写一个函数来判断给定的目标值是否存在于数组中。
 * 如果 nums 中存在这个目标值 target ，则返回 true ，否则返回 false 。
 *
 * 你必须尽可能减少整个操作步骤。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [2,5,6,0,0,1,2], target = 0
 * 输出：true
 * 示例 2：
 *
 * 输入：nums = [2,5,6,0,0,1,2], target = 3
 * 输出：false
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 5000
 * -10^4 <= nums[i] <= 10^4
 * 题目数据保证 nums 在预先未知的某个下标上进行了旋转
 * -10^4 <= target <= 10^4
 *
 *
 * 进阶：
 *
 * 这是 搜索旋转排序数组 的延伸题目，本题中的 nums  可能包含重复元素。
 * 这会影响到程序的时间复杂度吗？会有怎样的影响，为什么？
 */
public class SearchInRotatedSortedArrayIi {
    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     * @param nums
     * @param target
     * @return
     */
    public static boolean search(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                return true;
            }
        }

        return false;
    }

    /**
     * 二分法 比对right
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     * @param nums
     * @param target
     * @return
     */
    public static boolean binarySearch(int[] nums, int target) {
        int len = nums.length;

        if (len == 0) {
            return false;
        }

        int left = 0;
        int right = len - 1;

        while (left < right) {
            // 当看到边界设置行为是 left = mid 与 right = mid - 1 的时候，
            // 需要将 int mid 的下取整行为调整为上取整，
            // 以避免出现死循环 int mid = left + (right - left + 1) / 2;。
            int mid = left + (right - left + 1) / 2;

            // mid 到 right 有序
            if (nums[mid] < nums[right]) {
                if (nums[mid] <= target && target <= nums[right]) {
                    left = mid;
                } else {
                    right = mid - 1;
                }
            } else if (nums[mid] > nums[right]) { // nums[mid] > nums[right], left 到 mid 有序
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid;
                }
            } else {
                if (nums[right] == target) {
                    return true;
                }

                right = right - 1;
            }
        }

        return nums[left] == target;
    }

    /**
     * 二分法 比对left
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     * @param nums
     * @param target
     * @return
     */
    public static boolean binarySearch2(int[] nums, int target) {
        int len = nums.length;

        if (len == 0) {
            return false;
        }

        int left = 0;
        int right = len - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            // left 到 mid 有序
            if (nums[mid] > nums[left]) {
                if (nums[left] <= target && nums[mid] >= target) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            } else if (nums[mid] < nums[left]) { // mid 到 right 有序
                if (nums[right] >= target && nums[mid] < target) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            } else {
                if (nums[left] == target) {
                    return true;
                }

                left = left + 1;
            }
        }

        return nums[left] == target;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 0, 1, 1, 1};
        System.out.println(binarySearch2(nums, 0));
    }
}
