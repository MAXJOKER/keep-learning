package leetcode.binarysearch;

/**
 * @author maxjoker
 * @date 2022-04-07 21:39
 *
 * https://leetcode-cn.com/problems/find-peak-element/
 *
 * 162. 寻找峰值
 * 峰值元素是指其值严格大于左右相邻值的元素。
 *
 * 给你一个整数数组 nums，找到峰值元素并返回其索引。数组可能包含多个峰值，在这种情况下，返回 任何一个峰值 所在位置即可。
 *
 * 注意看题，任何一个峰值，即使没有左侧或者右侧都可以，只要是往上的
 *
 * 你可以假设 nums[-1] = nums[n] = -∞ 。
 *
 * 因为「两端」是负无穷，画个图，有凸起的的地方到「两端」一定会有转折，题目只要求返回任何一个峰值所在位置即可。
 *
 * 你必须实现时间复杂度为 O(log n) 的算法来解决此问题。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3,1]
 * 输出：2
 * 解释：3 是峰值元素，你的函数应该返回其索引 2。
 * 示例 2：
 *
 * 输入：nums = [1,2,1,3,5,6,4]
 * 输出：1 或 5
 * 解释：你的函数可以返回索引 1，其峰值元素为 2；
 *      或者返回索引 5， 其峰值元素为 6。
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 1000
 * -2^31 <= nums[i] <= 2^31 - 1
 * 对于所有有效的 i 都有 nums[i] != nums[i + 1]
 *
 */
public class FindPeakElement {
    /**
     * 暴力法
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     * @param nums
     * @return
     */
    public static int findPeakElement(int[] nums) {
        int len = nums.length;
        int idx = 0;
        for (int i = 1; i < len - 1; i++) {
            if (nums[i] > nums[idx]) {
                idx = i;
            }
        }

        return idx;
    }

    /**
     * 二分法
     * 时间复杂度：O(logN)
     * 空间复杂度：O(1)
     * @param nums
     * @return
     */
    public static int findPeakElementByBinarySearch(int[] nums) {
        int len = nums.length;
        int left = 0;
        int right = len - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] < nums[mid + 1]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3};
        System.out.println(findPeakElementByBinarySearch(nums));
    }
}
