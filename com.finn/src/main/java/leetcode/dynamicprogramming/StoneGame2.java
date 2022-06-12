package leetcode.dynamicprogramming;

/**
 * @author maxjoker
 * @date 2022-06-12 18:02
 *
 * https://leetcode.cn/problems/stone-game-ii/
 * 题解：https://leetcode.cn/problems/stone-game-ii/solution/java-dong-tai-gui-hua-qing-xi-yi-dong-17xing-by-lg/
 *
 * 1140. 石子游戏 II
 * 爱丽丝和鲍勃继续他们的石子游戏。许多堆石子 排成一行，每堆都有正整数颗石子 piles[i]。游戏以谁手中的石子最多来决出胜负。
 *
 * 爱丽丝和鲍勃轮流进行，爱丽丝先开始。最初，M = 1。
 *
 * 在每个玩家的回合中，该玩家可以拿走剩下的 前 X 堆的所有石子，其中 1 <= X <= 2M。然后，令 M = max(M, X)。
 *
 * 游戏一直持续到所有石子都被拿走。
 *
 * 假设爱丽丝和鲍勃都发挥出最佳水平，返回爱丽丝可以得到的最大数量的石头。
 *
 *
 *
 * 示例 1：
 *
 * 输入：piles = [2,7,9,4,4]
 * 输出：10
 * 解释：如果一开始Alice取了一堆，Bob取了两堆，然后Alice再取两堆。爱丽丝可以得到2 + 4 + 4 = 10堆。如果Alice一开始拿走了两堆，那么Bob可以拿走剩下的三堆。在这种情况下，Alice得到2 + 7 = 9堆。返回10，因为它更大。
 * 示例 2:
 *
 * 输入：piles = [1,2,3,4,5,100]
 * 输出：104
 *
 *
 * 提示：
 *
 * 1 <= piles.length <= 100
 * 1 <= piles[i] <= 10^4
 *
 */
public class StoneGame2 {
    /**
     * 定义状态
     *      f[i][m] 表示当在[i...n-1]这个石子的区间范围内，M取m时，当前玩家能获取到的最多的石子数量
     *
     * 转移方程
     *      如果当前玩家从[i...i+x-1]选了这个范围的石子，留给另外一个玩家的选择范围是[i+x...max(m,x)]，而
     *
     *      如果i+2M 到达了石子堆的末尾，即i+2M>=n的时候，对于当前玩家，最好的方案是全部取走石子，
     *      即f[i][M]=sum[i:n-1]
     *
     *      如果i+2M<n,则f[i][M]=max{f[i][M],sum[i:n-1]-f[i+x][max(M,x)]} 这里的x在[1:2M]范围内
     * @param piles
     * @return
     */
    public static int stoneGameII(int[] piles) {
        int len = piles.length;
        int sum = 0;
        int[][] dp = new int[len][len + 1];

        for (int i = len - 1; i >= 0; i--) {
            sum += piles[i];
            for (int j = 1; j <= len; j++) {
                if (i + 2 * j >= len) {
                    dp[i][j] = sum;
                } else {
                    for (int x = 1; x <= 2 * j; x++) {
                        dp[i][j] = Math.max(dp[i][j], sum - dp[i + x][Math.max(j, x)]);
                    }
                }
            }
        }

        return dp[0][1];
    }

    public static void main(String[] args) {
        int[] piles = new int[]{2, 7, 9, 4, 4};
        System.out.println(stoneGameII(piles));
    }
}
