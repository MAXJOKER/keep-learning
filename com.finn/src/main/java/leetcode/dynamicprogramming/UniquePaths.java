package leetcode.dynamicprogramming;

/**
 * @author maxjoker
 * @date 2022-04-22 20:47
 *
 * https://leetcode-cn.com/problems/unique-paths/
 *
 * 62. 不同路径
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
 *
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
 *
 * 问总共有多少条不同的路径？
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：m = 3, n = 7
 * 输出：28
 * 示例 2：
 *
 * 输入：m = 3, n = 2
 * 输出：3
 * 解释：
 * 从左上角开始，总共有 3 条路径可以到达右下角。
 * 1. 向右 -> 向下 -> 向下
 * 2. 向下 -> 向下 -> 向右
 * 3. 向下 -> 向右 -> 向下
 * 示例 3：
 *
 * 输入：m = 7, n = 3
 * 输出：28
 * 示例 4：
 *
 * 输入：m = 3, n = 3
 * 输出：6
 *
 *
 * 提示：
 *
 * 1 <= m, n <= 100
 * 题目数据保证答案小于等于 2 * 109
 *
 */
public class UniquePaths {
    /**
     * 数学方法：组合 C
     *
     * 用组合数来求解，走到坐标为 (m, n) 的地方，向下走 m - 1 格，向右边走 n - 1 格。一共走 m + n - 2 格。
     * 即：机器人一定会走 m+n−2 步，即从 m+n−2 中挑出 m−1 步向下走即可
     * @param m
     * @param n
     * @return
     */
    public static int uniquePaths(int m, int n) {
        long ans = 1;
        // (m+n−2)(m+n−3)⋯n / (m - 1)!    这里阶乘的计算是从 最小的开始乘起来
        for (int i = n, j = 1; j < m; i++, j++) {
            ans = (ans * i) / j;
        }

        System.out.println(ans);
        return (int) ans;
    }

    /**
     * 动态规划
     *
     * 时间复杂度：O(m*n)
     * 空间复杂度：O(m*n)
     * @param m
     * @param n
     * @return
     */
    public static int uniquePathsByDp(int m, int n) {
        // dp[i][j] 为所有路径
        int[][] dp = new int[m+1][n+1];
        dp[0][1] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 计算所有路径
                dp[i+1][j+1] = dp[i][j+1] + dp[i+1][j];
            }
        }

        return dp[m][n];
    }

    // TODO 空间优化 O(2n) , O(n)做法

    public static void main(String[] args) {
        System.out.println(uniquePaths(59, 5));
    }
}
