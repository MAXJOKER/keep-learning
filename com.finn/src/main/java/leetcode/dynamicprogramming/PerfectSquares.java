package leetcode.dynamicprogramming;

/**
 * @author maxjoker
 * @date 2022-05-04 10:57
 *
 * https://leetcode-cn.com/problems/perfect-squares/
 *
 * 279. 完全平方数
 * 给你一个整数 n ，返回 和为 n 的完全平方数的最少数量 。
 *
 * 完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 12
 * 输出：3
 * 解释：12 = 4 + 4 + 4
 * 示例 2：
 *
 * 输入：n = 13
 * 输出：2
 * 解释：13 = 4 + 9
 *
 * 提示：
 *
 * 1 <= n <= 10^4
 *
 */
public class PerfectSquares {
    /**
     * 时间复杂度：O(n根号n)
     * 空间复杂度：O(n)
     * 题解：https://leetcode-cn.com/problems/perfect-squares/solution/wan-quan-ping-fang-shu-by-leetcode-solut-t99c/
     * 题解中还有个数学定理，四平方和定理
     * 四平方和定理证明了任意一个正整数都可以被表示为至多四个正整数的平方和。
     * 
     * @param n
     * @return
     */
    public static int perfectSquares(int n) {
        int[] dp = new int[n+1];

        for (int i = 1; i <= n; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 1; j * j <= i; j++) {
                min = Math.min(min, dp[i - j * j]);
            }
            dp[i] = min + 1; // 加1表示 j * j 的个数
        }

        return dp[n];
    }

    public static void main(String[] args) {
        int n = 4;
        System.out.println(perfectSquares(n));
    }
}
