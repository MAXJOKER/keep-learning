package leetcode.greedy;

/**
 * @author maxjoker
 * @date 2022-07-27 00:30
 *
 * https://leetcode.cn/problems/jump-game/
 *
 * 55. 跳跃游戏
 * 给定一个非负整数数组 nums ，你最初位于数组的 第一个下标 。
 *
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 *
 * 判断你是否能够到达最后一个下标。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [2,3,1,1,4]
 * 输出：true
 * 解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。
 * 示例 2：
 *
 * 输入：nums = [3,2,1,0,4]
 * 输出：false
 * 解释：无论怎样，总会到达下标为 3 的位置。但该下标的最大跳跃长度是 0 ， 所以永远不可能到达最后一个下标。
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 3 * 10^4
 * 0 <= nums[i] <= 10^5
 *
 */
public class JumpGame {
    /**
     * https://leetcode.cn/problems/jump-game/solution/tiao-yue-you-xi-by-leetcode-solution/
     * 贪心算法
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     * @param nums
     * @return
     */
    public static boolean canJump(int[] nums) {
        int len = nums.length;

        // 最远可到达的点
        int rightMost = 0;
        for (int i = 0; i < len; i++) {
            if (i <= rightMost) {
                rightMost = Math.max(rightMost, i + nums[i]);
            }

            if (rightMost >= len - 1) {
                return true;
            }
        }

        return false;
    }
}
