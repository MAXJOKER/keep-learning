package leetcode.dynamicprogramming;

/**
 * @author maxjoker
 * @date 2022-06-12 13:06
 *
 * https://leetcode.cn/problems/stone-game/
 *
 * 877. 石子游戏
 * Alice 和 Bob 用几堆石子在做游戏。一共有偶数堆石子，排成一行；每堆都有 正 整数颗石子，数目为 piles[i] 。
 *
 * 游戏以谁手中的石子最多来决出胜负。石子的 总数 是 奇数 ，所以没有平局。
 *
 * Alice 和 Bob 轮流进行，Alice 先开始 。 每回合，玩家从行的 开始 或 结束 处取走整堆石头。
 * 这种情况一直持续到没有更多的石子堆为止，此时手中 石子最多 的玩家 获胜 。
 *
 * 假设 Alice 和 Bob 都发挥出最佳水平，当 Alice 赢得比赛时返回 true ，当 Bob 赢得比赛时返回 false 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：piles = [5,3,4,5]
 * 输出：true
 * 解释：
 * Alice 先开始，只能拿前 5 颗或后 5 颗石子 。
 * 假设他取了前 5 颗，这一行就变成了 [3,4,5] 。
 * 如果 Bob 拿走前 3 颗，那么剩下的是 [4,5]，Alice 拿走后 5 颗赢得 10 分。
 * 如果 Bob 拿走后 5 颗，那么剩下的是 [3,4]，Alice 拿走后 4 颗赢得 9 分。
 * 这表明，取前 5 颗石子对 Alice 来说是一个胜利的举动，所以返回 true 。
 * 示例 2：
 *
 * 输入：piles = [3,7,2,3]
 * 输出：true
 *
 *
 * 提示：
 *
 * 2 <= piles.length <= 500
 * piles.length 是 偶数
 * 1 <= piles[i] <= 500
 * sum(piles[i]) 是 奇数
 *
 * 这道题和 486. 预测赢家 一模一样
 *
 */
public class StoneGame {
    /**
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(n^2）
     * 状态转移方程分析：
     *
     * 甲乙比赛，甲先手面对区间[i...j]时，dp[i][j]表示甲对乙的净胜分。
     *
     * 最终求的就是，甲先手面对区间[0...n-1]时，甲对乙的净胜分dp[0][n-1]是否>0。
     *
     * 甲先手面对区间[i...j]时，
     *
     * 如果甲拿nums[i]，那么变成乙先手面对区间[i+1...j]，这段区间内乙对甲的净胜分为dp[i+1][j]；
     * 那么甲对乙的净胜分就应该是nums[i] - dp[i+1][j]。
     *
     * 如果甲拿nums[j]，同理可得甲对乙的净胜分为是nums[j] - dp[i][j-1]。
     *
     * 以上两种情况二者取大即可。
     * @param piles
     * @return
     */
    public static boolean stoneGame(int[] piles) {
        int len = piles.length;

        int[][] dp = new int[len][len];
        for (int i = 0; i < len; i++) {
            dp[i][i] = piles[i];
        }

        for (int i = len - 2; i >= 0; i--) {
            for (int j = i + 1; j < len; j++) {
                dp[i][j] = Math.max(piles[i] - dp[i + 1][j], piles[j] - dp[i][j - 1]);
            }
        }

        return dp[0][len - 1] > 0;
    }

    /**
     * 数学证明方法
     * 因为题目说了，石子的堆数为偶数，而且石子的总和是奇数
     * 所以可以通过分治法，把石子堆不断细分，直到分成两堆
     * 由于是Alice先手，所以Alice总能先取最大的那一堆，
     * 最终合起来的结果是Alice拿到的石子数量总是最大的
     * @param piles
     * @return
     */
    public static boolean stoneGame2(int[] piles) {
        return true;
    }

    public static void main(String[] args) {
        int[] piles = new int[]{5, 5, 5 ,5};
        System.out.println(stoneGame(piles));
    }
}
