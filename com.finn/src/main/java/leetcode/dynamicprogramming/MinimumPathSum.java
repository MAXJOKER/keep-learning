package leetcode.dynamicprogramming;

/**
 * @author maxjoker
 * @date 2022-04-24 21:48
 *
 * https://leetcode-cn.com/problems/minimum-path-sum/
 *
 * 64. 最小路径和
 * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 *
 * 说明：每次只能向下或者向右移动一步。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：grid = [[1,3,1],[1,5,1],[4,2,1]]
 * 输出：7
 * 解释：因为路径 1→3→1→1→1 的总和最小。
 * 示例 2：
 *
 * 输入：grid = [[1,2,3],[4,5,6]]
 * 输出：12
 *
 *
 * 提示：
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 200
 * 0 <= grid[i][j] <= 100
 *
 */
public class MinimumPathSum {
    /**
     * 题解：https://leetcode-cn.com/problems/minimum-path-sum/solution/zui-xiao-lu-jing-he-by-leetcode-solution/
     * 看动图 看动图 看动图
     *
     * 时间复杂度：O(m*n)
     * 空间复杂度：O(1)
     *
     * @param grid
     * @return
     */
    public static int minimumPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // 初始化
        for (int i = 1; i < m; i++) {
            grid[i][0] += grid[i - 1][0];
        }

        // 初始化
        for (int j = 1; j < n; j++) {
            grid[0][j] += grid[0][j - 1];
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                grid[i][j] += Math.min(grid[i - 1][j], grid[i][j - 1]);
            }
        }

        return grid[m-1][n-1];
    }

    public static void main(String[] args) {
//        int[][] grid = new int[][]{{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        int[][] grid = new int[][]{{1, 2, 3}, {4, 5, 6}};
        System.out.println(minimumPathSum(grid));
    }
}
