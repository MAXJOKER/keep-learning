package sort;

/**
 * @author maxjoker
 * @date 2022-02-21 17:59
 *
 * 插入排序有个特点非常重要：数据接近有序的时候，插入排序可以很快完成。
 * 「接近有序」的意思是：每个元素和它排序以后最终所在的位置不远。这一点需要和「选择排序」进行比较。
 *
 * 「插入排序」是稳定排序，在数组的值 接近有序 的情况下，表现优异，时间复杂度可以达到 O(N)
 *
 * 在小区间内执行排序任务的时候，可以转向使用「插入排序」。
 *
 * https://suanfa8.com/algorithm-basic/basic-sort/insertion/
 */
public class InsertSort {
    /**
     * 逐个交换：待插入元素逐个交换到前面
     * @param nums
     *
     * 动图地址：https://tva1.sinaimg.cn/large/008i3skNgy1gwyurfs2f8g30g003n19k.gif
     *
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(1)
     *
     */
    public static void insertSort(int[] nums) {
        int len = nums.length;
        for (int i = 1; i < len; i++) {
            for (int j = i; j > 0; j--) {
                // 两两交换
                if (nums[j] < nums[j - 1]) {
                    int temp = nums[j];
                    nums[j] = nums[j - 1];
                    nums[j - 1] = temp;
                } else {
                    // 前面的数字是有序的，如果 nums[j] >= nums[j - 1]，说明 nums[j] 比前面的数字都大，提前终结循环
                    break;
                }
            }
        }
    }

    /**
     * 先暂存再后移：先暂存待插入元素，然后前面比暂存元素严格大的后移
     * 动图地址：https://tva1.sinaimg.cn/large/008i3skNgy1gwyurgwqeng30af04odkr.gif
     * @param nums
     *
     * 时间复杂度 O(n^2)
     * 空间复杂度 O(1)
     */
    public static void insertSort2(int[] nums) {
        int len = nums.length;
        for (int i = 1; i < len; i++) {
            // 先暂存这个元素，然后之前元素逐个后移，留出空位
            int temp = nums[i];
            int j = i;

            while (j > 0 && nums[j - 1] > temp) {
                // 元素后移 不清楚为什么这么做可以debug一遍就十分清晰了
                nums[j] = nums[j - 1];
                j--;
            }

            nums[j] = temp;
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{0,2,1,6,3,10,9,7,8};
        insertSort2(nums);
        for (int i : nums) {
            System.out.println(i);
        }
    }
}
