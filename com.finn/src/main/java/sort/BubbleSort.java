package sort;

/**
 * @author maxjoker
 * @date 2022-02-21 10:56
 *
 * 冒泡排序
 * 相邻两个数比较，大数放后面，小的放前，一轮下来后，最大的数放在最后
 * 类似从后往前，从大到小
 * 时间复杂度o(n^2)
 * 空间复杂度o(1)
 *
 * https://suanfa8.com/algorithm-basic/basic-sort/bubble/
 *
 */
public class BubbleSort {
    public static void bubbleSort(int[] nums) {
        int len = nums.length;
        for(int i = 0; i < len - 1; i++) {
            for (int j = 0; j < len - i - 1; j++) {
                if (nums[j] > nums[j + 1]) {
                    int temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                }
            }
        }
    }

    /**
     * 优化
     * 如果在一次循环中，都没有「冒泡」行为发生，整个排序任务就可以 提前终止。
     * 可以设置布尔变量 sorted，假设每一轮循环开始假设数组是有序的；
     * 一旦在比较的过程中执行了交换，说明数组不是有序的，将 sorted 设置为 false；
     * 如果在一次循环中，都没有「冒泡」行为发生，才可以认为剩下的部分是有序的。
     *
     * @param nums
     */
    public static void bubbleSort2(int[] nums) {
        int len = nums.length;
        for(int i = 0; i < len - 1; i++) {
            // 先默认数组是有序的，只要发生一次交换，就必须进行下一轮比较
            boolean sorted = true;
            for (int j = 0; j < len - i - 1; j++) {
                if (nums[j] > nums[j + 1]) {
                    int temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;

                    sorted = false;
                }
            }

            // 如果在内层循环中，都没有执行一次交换操作，说明此时数组已经是升序数组
            if (sorted) {
                break;
            }
        }
    }


    public static void main(String[] args) {
        int[] nums = new int[]{0,2,1,6,3,10,9,7,8};
        bubbleSort(nums);
        for (int i : nums) {
            System.out.println(i);
        }
    }
}
