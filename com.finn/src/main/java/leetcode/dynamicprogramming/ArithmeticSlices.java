package leetcode.dynamicprogramming;

/**
 * @author maxjoker
 * @date 2022-05-11 20:52
 *
 * https://leetcode.cn/problems/arithmetic-slices/
 *
 * 413. 等差数列划分
 * 如果一个数列 至少有三个元素 ，并且任意两个相邻元素之差相同，则称该数列为等差数列。
 *
 * 例如，[1,3,5,7,9]、[7,7,7,7] 和 [3,-1,-5,-9] 都是等差数列。
 * 给你一个整数数组 nums ，返回数组 nums 中所有为等差数组的 子数组 个数。
 *
 * 子数组 是数组中的一个连续序列。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3,4]
 * 输出：3
 * 解释：nums 中有三个子等差数组：[1, 2, 3]、[2, 3, 4] 和 [1,2,3,4] 自身。
 * 示例 2：
 *
 * 输入：nums = [1]
 * 输出：0
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 5000
 * -1000 <= nums[i] <= 1000
 *
 * 题解：https://leetcode.cn/problems/arithmetic-slices/solution/hua-dong-chuang-kou-dong-tai-gui-hua-jav-3vpp/
 *
 */
public class ArithmeticSlices {
    /**
     * 动态规划
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     * @param nums
     * @return
     */
    public static int numberOfArithmeticSlices(int[] nums) {
        int len = nums.length;
        if (len < 3) {
            return 0;
        }

        int res = 0;
        // dp[i] 表示以：nums[i] 结尾的、且长度大于等于 3 的连续等差数列的个数
        int[] dp = new int[len];
        // 从下标 2 开始，才有可能构成长度至少大于等于 3 的等差数列
        for (int i = 2; i < len; i++) {
            if (nums[i] - nums[i - 1] == nums[i - 1] - nums[i - 2]) {
                dp[i] = dp[i - 1] + 1;
                res += dp[i];
            }
        }

        return res;
    }

    /**
     * 动态规划
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     * @param nums
     * @return
     */
    public static int numberOfArithmeticSlices2(int[] nums) {
        int len = nums.length;
        if (len < 3) {
            return 0;
        }

        int res = 0;
        int prev = 0;

        for (int i = 2; i < len; i++) {
            if (nums[i] - nums[i - 1] == nums[i - 1] - nums[i - 2]) {
                prev++;
                res += prev;
            } else {
                prev = 0;
            }
        }

        return res;
    }

    /**
     * 滑动窗口
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     * 题解：https://leetcode.cn/problems/arithmetic-slices/solution/hua-dong-chuang-kou-dong-tai-gui-hua-jav-3vpp/
     * @param nums
     * @return
     */
    public static int HOP(int[] nums) {
        int len = nums.length;
        if (len < 3) {
            return 0;
        }

        int L = 2;
        int preDiff = nums[1] - nums[0];
        int res = 0;
        for (int i = 2; i < len; i++) {
            int diff = nums[i] - nums[i - 1];
            if (diff == preDiff) {
                L++;
            } else {
                res += (L - 2) * (L - 1) / 2;
                L = 2;
                preDiff = diff;
            }
        }

        // debug一次就知道为什么最后要计算一次了
        res += (L - 2) * (L - 1) / 2;
        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4, 8, 9, 10};
        System.out.println(numberOfArithmeticSlices(nums));
        System.out.println(numberOfArithmeticSlices2(nums));
        System.out.println(HOP(nums));
    }
}
