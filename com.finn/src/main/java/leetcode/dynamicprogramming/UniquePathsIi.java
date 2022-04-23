package leetcode.dynamicprogramming;

/**
 * @author maxjoker
 * @date 2022-04-24 00:21
 *
 * https://leetcode-cn.com/problems/unique-paths-ii/
 * 63. 不同路径 II
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
 *
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish”）。
 *
 * 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
 *
 * 网格中的障碍物和空位置分别用 1 和 0 来表示。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
 * 输出：2
 * 解释：3x3 网格的正中间有一个障碍物。
 * 从左上角到右下角一共有 2 条不同的路径：
 * 1. 向右 -> 向右 -> 向下 -> 向下
 * 2. 向下 -> 向下 -> 向右 -> 向右
 * 示例 2：
 *
 *
 * 输入：obstacleGrid = [[0,1],[0,0]]
 * 输出：1
 *
 *
 * 提示：
 *
 * m == obstacleGrid.length
 * n == obstacleGrid[i].length
 * 1 <= m, n <= 100
 * obstacleGrid[i][j] 为 0 或 1
 *
 */
public class UniquePathsIi {
    /**
     * 动态规划
     *
     * 时间复杂度：O(m*n)
     * 空间复杂度：O(m*n)
     * @return
     */
    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        // dp[i][j] 为所有路径
        int[][] dp = new int[m+1][n+1];
        dp[0][1] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    continue;
                }
                // 计算所有路径
                dp[i+1][j+1] = dp[i][j+1] + dp[i+1][j];
            }
        }

        return dp[m][n];
    }

    public static void main(String[] args) {
        int[][] nums = new int[][]{{0, 0, 0}, {0, 1, 0}, {0, 0, 0 }};
        System.out.println(uniquePathsWithObstacles(nums));
    }
}
