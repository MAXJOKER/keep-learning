package sort;

/**
 * @author maxjoker
 * @date 2022-02-21 13:13
 *
 *  选择排序
 *  算法思想：减治思想
 *
 *  首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置，
 *  然后，再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。
 *  以此类推，直到所有元素均排序完毕。
 *  时间复杂度 o(n^2)
 *  空间复杂度 o(1)
 *
 *  不稳定排序
 *
 *  https://suanfa8.com/algorithm-basic/basic-sort/selection/
 */
public class SelectSort {
    public static void selectSort(int[] nums) {
        int len = nums.length;
        for (int i = 0; i < len - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < len; j++) {
                // 找到最小数字的位置
                if (nums[minIndex] > nums[j]) {
                   minIndex = j;
                }
            }

            if (minIndex != i) {
                int temp = nums[minIndex];
                nums[minIndex] = nums[i];
                nums[i] = temp;
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{0,2,1,6,3,10,9,7,8};
        selectSort(nums);
        for (int i : nums) {
            System.out.println(i);
        }
    }
}
