package leetcode.dynamicprogramming;

/**
 * @author maxjoker
 * @date 2022-05-08 17:51
 *
 * https://leetcode-cn.com/problems/wiggle-subsequence/
 *
 * 376. 摆动序列
 * 如果连续数字之间的差严格地在正数和负数之间交替，则数字序列称为 摆动序列 。第一个差（如果存在的话）可能是正数或负数。仅有一个元素或者含两个不等元素的序列也视作摆动序列。
 *
 * 例如， [1, 7, 4, 9, 2, 5] 是一个 摆动序列 ，因为差值 (6, -3, 5, -7, 3) 是正负交替出现的。
 *
 * 相反，[1, 4, 7, 2, 5] 和 [1, 7, 4, 5, 5] 不是摆动序列，第一个序列是因为它的前两个差值都是正数，第二个序列是因为它的最后一个差值为零。
 * 子序列 可以通过从原始序列中删除一些（也可以不删除）元素来获得，剩下的元素保持其原始顺序。
 *
 * 给你一个整数数组 nums ，返回 nums 中作为 摆动序列 的 最长子序列的长度 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,7,4,9,2,5]
 * 输出：6
 * 解释：整个序列均为摆动序列，各元素之间的差值为 (6, -3, 5, -7, 3) 。
 * 示例 2：
 *
 * 输入：nums = [1,17,5,10,13,15,10,5,16,8]
 * 输出：7
 * 解释：这个序列包含几个长度为 7 摆动序列。
 * 其中一个是 [1, 17, 10, 13, 10, 16, 8] ，各元素之间的差值为 (16, -7, 3, -3, 6, -8) 。
 * 示例 3：
 *
 * 输入：nums = [1,2,3,4,5,6,7,8,9]
 * 输出：2
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 1000
 * 0 <= nums[i] <= 1000
 *
 *
 * 进阶：你能否用 O(n) 时间复杂度完成此题?
 *
 */
public class WiggleSubsequence {
    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     * 动态规划
     * 题解：
     * https://leetcode-cn.com/problems/wiggle-subsequence/solution/bai-dong-xu-lie-by-leetcode-solution-yh2m/
     * https://leetcode-cn.com/problems/wiggle-subsequence/solution/zheng-ming-wei-shi-yao-dong-tai-gui-hua-97d4m/
     * @param nums
     * @return
     */
    public static int solution(int[] nums) {
        int len = nums.length;

        if (len < 2) {
            return len;
        }

        int[] up = new int[len];
        int[] down = new int[len];

        up[0] = 1;
        down[0] = 1;

        for (int i = 1; i < len; i++) {
            if (nums[i] > nums[i - 1]) {
                up[i] = Math.max(up[i - 1], down[i - 1] + 1);
                down[i] = down[i - 1];
            } else if (nums[i] < nums[i - 1]) {
                up[i] = up[i - 1];
                down[i] = Math.max(up[i - 1] + 1, down[i - 1]);
            } else {
                up[i] = up[i - 1];
                down[i] = down[i - 1];
            }
        }

        return Math.max(up[len - 1], down[len - 1]);
    }

    /**
     * 动态规划优化
     * @param nums
     * @return
     */
    public static int dynamicProgramSolutionOptimize(int[] nums) {
        int len = nums.length;
        if (len < 2) {
            return len;
        }

        int down = 1;
        int up = 1;

        for (int i = 1; i < len; i++) {
            if (nums[i] > nums[i - 1]) {
                up = Math.max(up, down + 1);
                // up = down + 1; // 因为 down + 1 >= up
            } else if (nums[i] < nums[i - 1]) {
                down = Math.max(down, up + 1);
                // down = up + 1; // 因为 up + 1 >= down
            }
        }

        return Math.max(up, down);
    }

    /**
     * 贪心算法
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     * @param nums
     * @return
     */
    public static int solutionByGreedy(int[] nums) {
        int len = nums.length;
        if (len < 2) {
            return len;
        }

        int prev = 0;
        int res = 1;

        for (int i = 1; i < len; i++) {
            int diff = nums[i] - nums[i - 1];

            if ((diff > 0 && prev <= 0) || (diff < 0 && prev >= 0)) {
                res++;
                prev = diff;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3,2,5,6,7,8,9};
        System.out.println(solution(nums));
        System.out.println(solutionByGreedy(nums));
    }
}
