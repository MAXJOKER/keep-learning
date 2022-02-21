package leetcode;

/**
 * @author maxjoker
 * @date 2022-02-20
 *
 * 75. 颜色分类
 * 给定一个包含红色、白色和蓝色、共 n 个元素的数组 nums ，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 *
 * 我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 *
 * 必须在不使用库的sort函数的情况下解决这个问题。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [2,0,2,1,1,0]
 * 输出：[0,0,1,1,2,2]
 * 示例 2：
 *
 * 输入：nums = [2,0,1]
 * 输出：[0,1,2]
 *
 *
 * 提示：
 *
 * n == nums.length
 * 1 <= n <= 300
 * nums[i] 为 0、1 或 2
 *
 *
 * https://leetcode-cn.com/problems/sort-colors/
 */
public class SortColors {
    /**
     * 冒泡排序
     *  * 相邻两个数比较，大数放后面，小的放前，一轮下来后，最大的数放在最后
     *  * 类似从后往前，从大到小
     *  * 时间复杂度o(n^2)
     *  * 空间复杂度o(1)
     * @param nums
     */
    public static void sortColorsByBubbleSort(int[] nums) {
        for(int i = 0; i < nums.length - 1; i++) {
            for (int j = 0; j < nums.length - i - 1; j++) {
                if (nums[j] > nums[j + 1]) {
                    int temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                }
            }
        }

        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);
        }

    }

    /**
     * 快速排序
     * @param nums
     *
     *  * 快速排序的思想是分冶法。
     *  * 取数组中间值=>遍历数组剩余元素=>小于中间值的放左边，
     *  * 大于中间值的放右边=>将左右循环如此直至不可再分->将已排好的合并
     *  * 时间复杂度 o(nlogn) 最坏情况o(n^2)
     *  * 空间复杂度 o(nlogn)
     */
    public static void sortColorsByQuickSort(int[] nums) {
        quickSort(nums, 0, nums.length - 1);

        for (int a = 0; a < nums.length; a++) {
            System.out.println(nums[a]);
        }
    }

    private static void quickSort(int[] nums, int start, int end) {
        if (start > end) {
            return;
        }
        // base 为 基准
        int base = nums[start];
        int i = start;
        int j = end;

        while (i < j) {
            while (i < j && nums[j] >= base) {
                j--;
            }
            while (i < j && nums[i] <= base) {
                i++;
            }

            if (i < j) {
                int temp = nums[j];
                nums[j] = nums[i];
                nums[i] = temp;
            }
        }

        nums[start] = nums[i];
        nums[i] = base;

        quickSort(nums, start, j - 1);
        quickSort(nums, j + 1, end);
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2,0,1};
        sortColorsByQuickSort(nums);
    }
}
