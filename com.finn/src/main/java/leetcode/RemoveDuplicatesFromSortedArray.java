package leetcode;

import java.util.Arrays;

/**
 * @author zhangjunhui
 * @Date 2021-12-27 09:43
 * @Description leetcode 26. 删除有序数组中的重复项 https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/
 */
public class RemoveDuplicatesFromSortedArray {
    /**
     * 双指针解法
     * 一个指针 i 进行数组遍历，另外一个指针 j 指向有效数组的最后一个位置。
     * 只有当 i 所指向的值和 j 不一致（不重复），才将 i 的值添加到 j 的下一位置。
     *
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * @param nums
     * @return
     */
    public static int removeDuplicates(int[] nums) {
        int j = 0;
        int length = nums.length;
        for (int i = 0; i <= length - 1; i++) {
            if (nums[i] != nums[j]) {
                nums[++j] = nums[i];
            }
        }

        return j + 1;
    }

    /**
     * 为了让解法更具有一般性，我们将原问题的「最多保留 1 位」修改为「最多保留 k 位」。
     *
     * 对于此类问题，我们应该进行如下考虑：
     *
     * 由于是保留 k 个相同数字，对于前 k 个数字，我们可以直接保留。
     * 对于后面的任意数字，能够保留的前提是：与当前写入的位置前面的第 k 个元素进行比较，不相同则保留。
     * 举个🌰，我们令 k=1，假设有样例：[3,3,3,3,4,4,4,5,5,5]
     *
     * 设定变量 idx，指向待插入位置。idx 初始值为 0，目标数组为 []
     *
     * 首先我们先让第 1 位直接保留（性质 1）。idx 变为 1，目标数组为 [3]
     *
     * 继续往后遍历，能够保留的前提是与 idx 的前面 1 位元素不同（性质 2），因此我们会跳过剩余的 3，将第一个 4 追加进去。idx 变为 2，目标数组为 [3,4]
     *
     * 继续这个过程，跳过剩余的 4，将第一个 5 追加进去。idx 变为 3，目标数组为 [3,4,5]
     *
     * 当整个数组被扫描完，最终我们得到了目标数组 [3,4,5] 和 答案 idx 为 3。
     *
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     * @param nums
     * @return
     */
    public static int removeDuplicates2(int[] nums) {
        return process(nums, 1);
    }

    public static int process(int[] nums, int k) {
        int idx = 0;
        for (int i : nums) {
            if (idx < k || nums[idx - k] != i) {
                nums[idx++] = i;
            }
        }

        return idx;
    }


    public static void main(String[] args) {
        int[] nums = {0,0,1,1,1,2,2,3,3,4};
        int size = removeDuplicates(nums);
        for (int i = 0; i < size; i++) {
            System.out.println(nums[i]);
        }
        System.out.println("size: " + size);

        int[] nums2 = {0,0,1,1,1,2,2,3,3,4};
        int size2 = removeDuplicates2(nums2);
        for (int j = 0; j < size2; j++) {
            System.out.println(nums[j]);
        }
        System.out.println("size2: " + size2);
    }
}
