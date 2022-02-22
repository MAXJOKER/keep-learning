package sort;

import java.util.Random;

/**
 * @author maxjoker
 * @date 2022-02-22 11:22
 *
 * 理解循环不变量：https://suanfa8.com/loop-invariant/intro/
 * 讲解：https://suanfa8.com/quick-sort/quick-sort-optimize/
 *
 * 快速排序的基本想法 快速排序每一次都排定一个元素（这个元素呆在了它最终应该呆的位置），
 * 然后递归地去排它左边的部分和右边的部分，依次进行下去，直到数组有序。
 *
 * # 快速排序的算法思想 分而治之（分治思想），与「归并排序」不同，「快速排序」在「分」这件事情上不想「归并排序」无脑地一分为二，
 * 而是采用了 partition 的方法，因此就没有「合」的过程。
 *
 * # 与归并排序比较 归并排序不管数组的内容是什么，归并排序总是一分为二地去做排序运算，然后再归并起来。
 *
 */
public class QuickSort {

    /**
     * 列表大小等于或小于该大小，将优先于 quickSort 使用插入排序
     */
    private static final int INSERTION_SORT_THRESHOLD = 7;

    private static final Random RANDOM = new Random();

    /**
     * 基本快速排序
     * @param nums
     * @return
     */
    public static int[] quickSort1(int[] nums) {
        int len = nums.length;
        quickSort1(nums, 0, len - 1);
        return nums;
    }

    private static void quickSort1(int[] nums, int left, int right) {
        // 优化点：小区间使用插入排序
        if (right - left <= INSERTION_SORT_THRESHOLD) {
            insertionSort(nums, left, right);
            return;
        }

        // 要加 left < right 条件，不然partition方法 random.nextInt会出现负数范围，原因为 pIndex - 1 小于0
        if (left < right) {
            int pIndex = partition(nums, left, right);
            quickSort1(nums, left, pIndex - 1);
            quickSort1(nums, pIndex + 1, right);
        }
    }

    private static void insertionSort(int[] nums, int left, int right) {
        int len = nums.length;
        for (int i = 1; i < len; i++) {
            int temp = nums[i];
            int j = i;

            while (j > 0 && nums[j - 1] > temp) {
                nums[j] = nums[j - 1];
                j--;
            }

            nums[j] = temp;
        }
    }

    /**
     * 切分
     * https://suanfa8.com/quick-sort/quick-sort-basic/
     * 动图：https://tva1.sinaimg.cn/large/008i3skNgy1gxnaqevqq3g30u00gw7q0.gif
     * （1）把一个区间按照数值大小进行划分
     * （2）排定第一个元素的位置
     * @param nums
     * @param left
     * @param right
     * @return
     */
    private static int partition(int[] nums, int left, int right) {
        // 优化点：随机选择标定点元素，降低递归树结构不平衡的情况
        int randomIndex = left + RANDOM.nextInt(right - left + 1);
        swap(nums, left, randomIndex);

        // 基准值
        int pivot = nums[left];
        int lt = left;

        // 循环不变量
        // all in [left + 1, lt] < pivot
        // all in [lt + 1, i] >= pivot
        // i <= right 不然有可能会越界
        for (int i = left + 1; i <= right; i++) {
            if (nums[i] < pivot) {
                lt++;
                swap(nums, i, lt);
            }
        }

        swap(nums, left, lt);
        return lt;
    }



    /**
     * 双指针（指针对撞）快速排序
     * @param nums
     * @return
     */
    public static int[] quickSort2(int[] nums) {
        int len = nums.length;
        quickSort2(nums, 0, len - 1);
        return nums;
    }

    private static void quickSort2(int[] nums, int left, int right) {
        // 优化点：小区间使用插入排序
        if (right - left <= INSERTION_SORT_THRESHOLD) {
            insertionSort(nums, left, right);
            return;
        }

        // 要加 left < right 条件，不然partition方法 random.nextInt会出现负数范围，原因为 pIndex - 1 小于0
        if (left < right) {
            int pIndex = partition2(nums, left, right);
            quickSort1(nums, left, pIndex - 1);
            quickSort1(nums, pIndex + 1, right);
        }
    }

    /**
     * https://suanfa8.com/quick-sort/quick-sort-two-ways/
     * @param nums
     * @param left
     * @param right
     * @return
     */
    private static int partition2(int[] nums, int left, int right) {
        int randomIndex = left + RANDOM.nextInt(right - left + 1);
        swap(nums, left, randomIndex);
        int pivot = nums[left];
        int lt = left + 1;
        int gt = right;

        while (true) {
            while (lt <= right && nums[lt] < pivot) {
                lt++;
            }

            while (gt >= 0 && nums[gt] > pivot) {
                gt--;
            }

            if (lt > gt) {
                break;
            }

            // nums[lt] == pivot || nums[gt] == pivot 的情况下，使重复的值均衡分布在pivot两边，使得递归平衡
            swap(nums, lt, gt);
            lt++;
            gt--;
        }

        swap(nums, left, gt);
        // 为什么返回gt？ 看看上面循环里面的 gt--
        return gt;
    }

    private static void swap(int[] nums, int index1, int index2) {
        if (index1 == index2) {
            return;
        }
        int temp = nums[index2];
        nums[index2] = nums[index1];
        nums[index1] = temp;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{0,2,1,6,3,10};
        quickSort2(nums);
        for (int i : nums) {
            System.out.println(i);
        }
    }
}
