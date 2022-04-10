package leetcode.binarysearch;

/**
 * @author maxjoker
 * @date 2022-04-09 15:31
 *
 * https://leetcode-cn.com/problems/find-the-duplicate-number/
 *
 * 287. 寻找重复数
 * 给定一个包含 n + 1 个整数的数组 nums ，其数字都在 [1, n] 范围内（包括 1 和 n），可知至少存在一个重复的整数。
 *
 * 假设 nums 只有 一个重复的整数 ，返回 这个重复的数 。
 *
 * 你设计的解决方案必须 不修改 数组 nums 且只用常量级 O(1) 的额外空间。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,3,4,2,2]
 * 输出：2
 * 示例 2：
 *
 * 输入：nums = [3,1,3,4,2]
 * 输出：3
 *
 *
 * 提示：
 *
 * 1 <= n <= 105
 * nums.length == n + 1
 * 1 <= nums[i] <= n
 * nums 中 只有一个整数 出现 两次或多次 ，其余整数均只出现 一次
 *
 *
 * 进阶：
 *
 * 如何证明 nums 中至少存在一个重复的数字?
 * 你可以设计一个线性级时间复杂度 O(n) 的解决方案吗？
 *
 */
public class FindTheDuplicateNumber {
    /**
     * 二分法
     * 时间复杂度：O(NlogN)
     * 空间复杂度：O(1)
     *
     * https://www.suanfa8.com/binary-search/solutions/find-integer/0287-find-the-duplicate-number/
     *
     * 每一次猜一个数，然后 遍历整个输入数组，进而缩小搜索区间，最后确定重复的是哪个数；
     * 把 n + 1 个整数，放在长度为 n 的数组里，根据「抽屉原理」，至少会有 1 个整数是重复的。
     *
     * 二分查找的思路是先猜一个数（有效范围 [left..right] 里位于中间的数 mid），
     * 然后统计原始数组中 小于等于 mid 的元素的个数 cnt：如果 cnt 严格大于 mid。
     * 根据抽屉原理，重复元素就在区间 [left..mid] 里；
     * 否则，重复元素就在区间 [mid + 1..right] 里。
     * @param nums
     * @return
     */
    public static int findDuplicate(int[] nums) {
        int left = 1;
        int right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            int cnt = 0;
            for (int num : nums) {
                if (num <= mid) {
                    cnt++;
                }
            }

            if (cnt > mid) {
                right =  mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    /**
     * 快慢指针法
     * https://leetcode-cn.com/problems/find-the-duplicate-number/solution/xun-zhao-zhong-fu-shu-by-leetcode-solution/
     * @param nums
     * @return
     */
    public static int findDuplicateByDoublePointer(int[] nums) {
        return 0;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 2, 3, 4};
        System.out.println(findDuplicate(nums));
    }
}
