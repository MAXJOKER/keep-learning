package leetcode;

import java.util.Random;

/**
 * @author maxjoker
 * @date 2022-02-22 21:10
 * 215. 数组中的第K个最大元素
 * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
 *
 * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 *
 *
 *
 * 示例 1:
 *
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 * 示例 2:
 *
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 *
 *
 * 提示：
 *
 * 1 <= k <= nums.length <= 104
 * -104 <= nums[i] <= 104
 *
 * https://leetcode-cn.com/problems/kth-largest-element-in-an-array/
 */
public class KthLargestElementInAnArray {
    private static final Random RANDOM = new Random();

    public static int findKthLargest(int[] nums, int k) {
        sortArray(nums, 0, nums.length - 1);
        return nums[k-1];
    }

    /**
     * 快速排序
     */
    public static void sortArray(int[] nums, int left, int right) {
        if (left < right) {
            int pIndex = partition(nums, left, right);
            sortArray(nums, left, pIndex - 1);
            sortArray(nums, pIndex + 1, right);
        }
    }

    public static int partition(int[] nums, int left, int right) {
        int randomIndex = left + RANDOM.nextInt(right - left + 1);
        swap(nums, left, randomIndex);

        int pivot = nums[left];
        int lt = left + 1;
        int gt = right;

        while (true) {
            while (lt <= right && nums[lt] > pivot) {
                lt++;
            }

            while (gt >= 0 && nums[gt] < pivot) {
                gt--;
            }

            if (lt > gt) {
                break;
            }

            swap(nums, lt, gt);
            lt++;
            gt--;
        }

        swap(nums, left, gt);
        return gt;
    }

    public static void swap(int[] nums, int index1, int index2) {
        if (index1 == index2) {
            return;
        }

        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }

    public static int findKthLargestByMergeSort(int[] nums, int k) {
        int len = nums.length;
        int[] temp = new int[len];

        sortArrayByMergeSort(nums, 0, len - 1, temp);
        return nums[len - k];
    }

    /**
     * 归并排序
     * @param nums
     * @param left
     * @param right
     * @param temp
     */
    public static void sortArrayByMergeSort(int[] nums, int left, int right, int[] temp) {
        if (left >= right) {
            return;
        }

        int mid = (left + right) / 2;
        sortArrayByMergeSort(nums, left, mid, temp);
        sortArrayByMergeSort(nums, mid + 1, right, temp);

        if (nums[mid] <= nums[mid + 1]) {
            return;
        }

        merge(nums, left, mid, right, temp);
    }

    private static void merge(int[] nums, int left, int mid, int right, int[] temp) {
        for (int i = left; i <= right; i++) {
            temp[i] = nums[i];
        }

        int i = left;
        int j = mid + 1;
        int k = left;

        while (i <= mid && j <= right) {
            if (temp[i] <= temp[j]) {
                nums[k] = temp[i];
                i++;
                k++;
            } else {
                nums[k] = temp[j];
                j++;
                k++;
            }
        }

        while (i <= mid) {
            nums[k] = temp[i];
            i++;
            k++;
        }

        while (j <= right) {
            nums[k] = temp[j];
            j++;
            k++;
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3,2,3,1,2,4,5,5,6};
        System.out.println(findKthLargest(nums, 1));
        System.out.println(findKthLargestByMergeSort(nums, 3));
    }
}
