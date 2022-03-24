package sort;

/**
 * @author maxjoker
 * @date 2022-02-22 21:56
 *
 * 归并排序
 *
 * 分治思想（分而治之）把一个规模较大的问题拆分成为若干个规模较小的相同类型的子问题，
 * 然后对这些子问题递归求解，等待每一个子问题完成以后，再得到原问题的解；
 *
 * 分治算法可以并行执行，但是在基础算法领域，分治算法是以 深度优先遍历 的方式执行的。
 *
 * https://suanfa8.com/merge-sort/merge-sort/
 *
 * 稳定排序
 *
 */
public class MergeSort {

    private static final Integer THRESHOLD = 7;

    public static void sortArray(int[] nums) {
        int len = nums.length;
        mergeSort1(nums, 0, len - 1);
    }

    public static void mergeSort1(int[] nums, int left, int right) {

        // 优化一：在「小区间」里转向使用「插入排序」
        if (nums.length <= THRESHOLD) {
            insertSort(nums, left, right);
        }

        // 递归终止条件：当排序部分只有一个元素时，递归终止
        if (left >= right) {
            return;
        }

        // 分治，不断一分为二
        int mid = left + (right - left) / 2;
        mergeSort1(nums, left, mid);
        mergeSort1(nums, mid + 1, right);

        // 优化二：如果数组有序，无需在合并，mid 左边和右边 都是归并拍好序的了
        if (nums[mid] <= nums[mid + 1]) {
            return;
        }

        mergeSortOp1(nums, left, mid, right);
    }

    private static void insertSort(int[] nums, int left, int right) {
        int len = nums.length;
        for (int i = left + 1; i < right; i++) {
            int j = i;
            int temp = nums[i];

            while (j > left && nums[j - 1] > temp) {
                nums[j] = nums[j - 1];
                j--;
            }

            nums[j] = temp;
        }
    }

    /**
     * 合并操作
     * @param nums
     * @param left
     * @param mid
     * @param right
     */
    public static void mergeSortOp1(int[] nums, int left, int mid, int right) {
        // 计算的是被拆分后的长度
        int len = right - left + 1;
        int[] temp = new int[len];

        for (int k = 0; k < len; k++) {
            temp[k] = nums[left + k];
        }

        int i = 0;
        int j = mid - left + 1;

        for (int k = 0; k < len; k++) {
            // 左边数据已全部用完
            if (i > mid - left) {
                nums[k + left] = temp[j];
                j++;
            } else if (j > len - 1) {
                // 右边数据已全部用完
                nums[k + left] = temp[i];
                i++;
            } else if (temp[i] > temp[j]) {
                nums[k + left] = temp[j];
                j++;
            } else {
                nums[k + left] = temp[i];
                i++;
            }
        }
    }

    public static void sortArray2(int[] nums) {
        int len = nums.length;
        int[] temp = new int[len];
        mergeSort2(nums, 0, len - 1, temp);
    }

    public static void mergeSort2(int[] nums, int left, int right, int[] temp) {
        // 递归终止条件
        if (left >= right) {
            return;
        }

        // (left + right)/2 这种写法在大整数的时候，会发生溢出
//        int mid = (left + right) / 2;
        int mid = left + (right - left) / 2;
        mergeSort2(nums, left, mid, temp);
        mergeSort2(nums, mid + 1, right, temp);

        if (nums[mid] <= nums[mid + 1]) {
            return;
        }

        mergeSortOp2(nums, left, mid, right, temp);
    }

    /**
     * 合并操作
     * 增加全局辅助数组，避免多次创建和销毁
     * @param nums
     * @param left
     * @param mid
     * @param right
     * @param temp
     */
    public static void mergeSortOp2(int[] nums, int left, int mid, int right, int[] temp) {
        for (int i = left; i <= right; i++) {
            temp[i] = nums[i];
        }

        int i = left;
        int j = mid + 1;
        int k = left;

        while (i <= mid && j <= right) {
            if (temp[i] <= temp[j]) {
                nums[k] = temp[i];
                k++;
                i++;
            } else {
                nums[k] = temp[j];
                k++;
                j++;
            }
        }

        while (i <= mid) {
            nums[k] = temp[i];
            k++;
            i++;
        }

        while (j <= right) {
            nums[k] = temp[j];
            k++;
            j++;
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{9,6,2};
        sortArray2(nums);
        for (int num : nums) {
            System.out.println(num);
        }
    }
}
