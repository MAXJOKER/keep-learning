package sort;

/**
 * @author maxjoker
 * @date 2022-02-21 19:43
 *
 * 希尔排序
 *
 * https://suanfa8.com/algorithm-basic/basic-sort/shell/
 *
 * 开始的时候逐渐让数组变得基本有序，最后使用一次使用「插入排序」就变得高效了。
 * 「逐渐让数组变得基本有序」的方法是让移动的「步幅」增大，不是一步一步挪过去，而是「大步流星」、「连蹦带跳」走过去；
 * 「逐渐缩小增量」「分组实施插入排序让数组变得逐渐接近有序」「最后执行一次标准的插入排序」（ 最后一轮就是「原汁原味」的插入排序。
 *
 * 希尔排序是 「分组插入排序」 或者 「带间隔的插入排序」
 *
 */
public class ShellSort {
    /**
     * 图解：
     * https://img2018.cnblogs.com/blog/1469176/201903/1469176-20190325113805282-73705243.png
     * https://img2018.cnblogs.com/blog/1469176/201903/1469176-20190325113811163-1661570987.gif
     * @param nums
     */
    public static void shellSort(int[] nums) {
        int len = nums.length;

        // 计算步长，不断除以2
        for (int step = len / 2; step > 0; step /= 2) {
            // 对步长区间进行比较
            for (int i = step; i < len; i++) {
                int temp = nums[i];
                int j;

                // 对步长区间中的具体元素进行比较、交换，中间 j >= 0 && nums[j] > temp 判断条件十分有必要，否则排序会出错
                for (j = i - step; j >= 0 && nums[j] > temp; j -= step) {
                    nums[j + step] = nums[j];
                }

                nums[j + step] = temp;
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{0,2,1,6,3,10,9,7,8};
        shellSort(nums);
        for (int i : nums) {
            System.out.println(i);
        }
    }
}
