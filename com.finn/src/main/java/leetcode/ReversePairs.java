package leetcode;

/**
 * @author maxjoker
 * @date 2022-02-27 16:53
 *
 * 剑指 Offer 51. 数组中的逆序对
 * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。
 *
 *
 *
 * 示例 1:
 *
 * 输入: [7,5,6,4]
 * 输出: 5
 *
 *
 * 限制：
 *
 * 0 <= 数组长度 <= 50000
 *
 * https://leetcode-cn.com/problems/shu-zu-zhong-de-ni-xu-dui-lcof/
 */
public class ReversePairs {
    /**
     * 暴力解法 （超时了）
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(1)
     *
     * @param nums
     * @return
     */
    public static int reversePairs(int[] nums) {
        if (nums.length <= 1) {
            return 0;
        }

        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] > nums[j]) {
                    count++;
                }
            }
        }

        return count;
    }

    /**
     * 归并排序
     * 思路：
     * 关键在于「合并两个有序数组」的步骤，利用数组的部分有序性，一下子计算出一个数之前或者之后元素的逆序的个数；
     * 前面「分」的时候什么都不做，「合」的过程中计算「逆序对」的个数；
     *
     * 思想是「分治算法」，所有的「逆序对」来源于 3 个部分：
     *
     * 左边区间的逆序对；
     * 右边区间的逆序对；
     * 横跨两个区间的逆序对。
     *
     * 时间复杂度：O(nlogn)
     * 空间复杂度：O(n)
     *
     * @param nums
     * @return
     */
    public static int reversePairsByMergeSort(int[] nums) {
        if (nums.length <= 1) {
            return 0;
        }

        int[] temp = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            temp[i] = nums[i];
        }

        return mergeSort(nums, 0, nums.length - 1, temp);
    }

    private static int mergeSort(int[] nums, int left, int right, int[] temp) {
        if (left == right) {
            return 0;
        }

        int mid = left + (right - left) / 2;
        int leftPairs = mergeSort(nums, left, mid, temp);
        int rightPairs = mergeSort(nums, mid + 1, right, temp);

        if (nums[mid] <= nums[mid + 1]) {
            return leftPairs + rightPairs;
        }

        int crossPairs = mergeAndCount(nums, left, mid, right, temp);
        return leftPairs + rightPairs + crossPairs;
    }

    private static int mergeAndCount(int[] nums, int left, int mid, int right, int[] temp) {
        for (int i = left; i <= right; i++) {
            temp[i] = nums[i];
        }

        int i = left;
        int j = mid + 1;

        int count = 0;
        for (int k = left; k <= right; k++) {
            if (i == mid + 1) {
                nums[k] = temp[j];
                j++;
            } else if (j == right + 1) {
                nums[k] = temp[i];
                i++;
            } else if (temp[i] <= temp[j]) {
                nums[k] = temp[i];
                i++;
            } else {
                nums[k] = temp[j];
                j++;

                // 统计逆序对  因为进入这里的判断条件是 temp[i] > temp[j]，符合逆序对要求
                // 减去i是因为 i++ 的条件是 temp[i] <= temp[j]，不符合逆序对要求
                count += mid - i + 1;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{7,5,6,4};
        System.out.println(reversePairsByMergeSort(nums));
    }
}
