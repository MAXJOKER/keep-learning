package leetcode.dynamicprogramming;

/**
 * @author maxjoker
 * @date 2022-06-09 20:32
 *
 * 1025. 除数博弈
 * 爱丽丝和鲍勃一起玩游戏，他们轮流行动。爱丽丝先手开局。
 *
 * 最初，黑板上有一个数字 n 。在每个玩家的回合，玩家需要执行以下操作：
 *
 * 选出任一 x，满足 0 < x < n 且 n % x == 0 。
 * 用 n - x 替换黑板上的数字 n 。
 * 如果玩家无法执行这些操作，就会输掉游戏。
 *
 * 只有在爱丽丝在游戏中取得胜利时才返回 true 。假设两个玩家都以最佳状态参与游戏。
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 2
 * 输出：true
 * 解释：爱丽丝选择 1，鲍勃无法进行操作。
 * 示例 2：
 *
 * 输入：n = 3
 * 输出：false
 * 解释：爱丽丝选择 1，鲍勃也选择 1，然后爱丽丝无法进行操作。
 *
 *
 * 提示：
 *
 * 1 <= n <= 1000
 *
 *
 * 解决博弈类动态规划问题的方法：
 *      1. 多见一些问题；
 *      2. 画图分析，通常都是由一个最基本的情况导致了全局的情况，所以博弈类动态规划问题有这样的特点，
 *          其实输入的数据就决定了结果；
 *      3. 每一步通常有多种选择，所以先用「记忆化递归」，然后再改成「动态规划」可能是一个相对比较好的学习路径；
 *          还有一类博弈类问题的思路是这样的：考虑「自己受益最大化」等价于「自己的选择可以让对方的受益最小」，
 *          所以「对方获得」这件事情就相当于「自己失去」，因此在设计状态和设计递归函数的时候，
 *          最大化的目标值是「相对分数」，「力扣」上有一类问题叫做「极小化极大」大概是这个思路。
 *
 *
 */
public class DivisorGame {
    /**
     * 数学方法，用笔在纸上多写几个数字就能看出规律来了，多动手好吗
     * 时间复杂度：O(1)
     * 空间复杂度：O(1)
     * @param n
     * @return
     */
    public static boolean divisorGame(int n) {
        return n % 2 == 0;
    }

    /**
     * 动态规划
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     * @param n
     * @return
     */
    public static boolean divisorGameByDp(int n) {
        if (n == 1) {
            return false;
        }

        // dp[i]：数字为 i 时，当前做出选择的人是否会赢
        boolean[] dp = new boolean[n + 1];
        dp[1] = false;
        dp[2] = true;

        for (int i = 3; i <= n; i++) {
            for (int j = 1; j <= i / 2; j++) {
                // 只要做出的选择的其中之一，能让对方输，在当前这一步我们就可以赢
                // dp[i - j] 表示下一个数字
                if (i % j == 0 && !dp[i - j]) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[n];
    }

    public static void main(String[] args) {
        int n = 6;
        System.out.println(divisorGame(n));
        System.out.println(divisorGameByDp(n));
    }
}
