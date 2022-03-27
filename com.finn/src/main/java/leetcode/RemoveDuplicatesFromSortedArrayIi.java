package leetcode;

/**
 * @author maxjoker
 * @date 2022-03-27 18:08
 *
 * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array-ii/submissions/
 *
 * 80. 删除有序数组中的重复项 II
 * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使每个元素 最多出现两次 ，返回删除后数组的新长度。
 *
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 *
 *
 *
 * 说明：
 *
 * 为什么返回数值是整数，但输出的答案是数组呢？
 *
 * 请注意，输入数组是以「引用」方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。
 *
 * 你可以想象内部操作如下:
 *
 * // nums 是以“引用”方式传递的。也就是说，不对实参做任何拷贝
 * int len = removeDuplicates(nums);
 *
 * // 在函数里修改输入数组对于调用者是可见的。
 * // 根据你的函数返回的长度, 它会打印出数组中 该长度范围内 的所有元素。
 * for (int i = 0; i < len; i++) {
 *     print(nums[i]);
 * }
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,1,1,2,2,3]
 * 输出：5, nums = [1,1,2,2,3]
 * 解释：函数应返回新长度 length = 5, 并且原数组的前五个元素被修改为 1, 1, 2, 2, 3 。 不需要考虑数组中超出新长度后面的元素。
 * 示例 2：
 *
 * 输入：nums = [0,0,1,1,1,1,2,3,3]
 * 输出：7, nums = [0,0,1,1,2,3,3]
 * 解释：函数应返回新长度 length = 7, 并且原数组的前五个元素被修改为 0, 0, 1, 1, 2, 3, 3 。 不需要考虑数组中超出新长度后面的元素。
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 3 * 104
 * -104 <= nums[i] <= 104
 * nums 已按升序排列
 *
 */
public class RemoveDuplicatesFromSortedArrayIi {
    /**
     * 为了让解法更具有一般性，我们将原问题的「最多保留 1 位」修改为「最多保留 k 位」。
     *
     * 对于此类问题，我们应该进行如下考虑：
     *
     * 由于是保留 k 个相同数字，对于前 k 个数字，我们可以直接保留。
     * 对于后面的任意数字，能够保留的前提是：与当前写入的位置前面的第 k 个元素进行比较，不相同则保留。
     * 举个例子，我们令 k=1，假设有样例：[3,3,3,3,4,4,4,5,5,5]
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
                nums[idx] = i;
                idx++;
            }
        }

        return idx;
    }


    public static void main(String[] args) {
        int[] nums2 = {0,0,1,1,1,2,2,3,3,4};
        int size2 = removeDuplicates2(nums2);
        for (int j = 0; j < size2; j++) {
            System.out.println(nums2[j]);
        }
        System.out.println("size2: " + size2);
    }
}
