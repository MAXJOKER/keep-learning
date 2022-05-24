package leetcode.dynamicprogramming;

/**
 * @author maxjoker
 * @date 2022-05-24 21:17
 */
public class LongestPalindromicSubsequence {
    /**
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(n^2)
     *
     * d[i][j] 表示 s[i...j]里面最长的回文子序列
     *
     * 例如 字符串 s = "bbbad"，此时 i = 0，j = 4
     *
     * 假如 charArray = s.toCharArray();
     * (1) charArray[i] == charArray[j]，此时我们继续缩小范围，
     * 看看 charArray[i + 1], charArray[j - 1]是否相等
     * 也就是 dp[i][j] = dp[i + 1][j - 1]
     *
     * (2) 假如 charArray[i] != charArray[j]
     * 那么有 dp[i][j] = dp[i + 1][j]，往右缩小范围看看 charArray[i + 1] 与 charArray[j] 是否相等
     * 同时也有 dp[i][j] = dp[i][j - 1]，往左缩小范围看看 charArray[i] 与 charArray[j - 1] 是否相等
     * 那么以上取哪一个值呢？当然是取最大值啦
     * 因此有：
     * dp[i][j] = max(dp[i][j - 1], dp[i + 1][j])
     *
     * 得到状态转移方程为：
     * if charArray[i] == charArray[j]
     *      dp[i][j] = dp[i + 1][j - 1]
     * else
     *      dp[i][j] = max(dp[i][j - 1], dp[i + 1][j])
     *
     *
     * 多思考啊，总结规律，看清考题本质
     * 拿这道题来说，状态转移方程和回文字符有关，首先想到回文字符是怎样的，要怎么比较是否是回文字符
     * 不要总想着一步到位，试着把问题拆分开成子问题，子问题解决后，题目解决
     *
     * 假如 char[i] == char[j]，那么我们下一步是不是要继续判断 char[i + 1] char[j - 1] ？
     * 假如 char[i] != char[j]，那么是不是就会有两种情况
     *      （1）判断 char[i] char[j - 1]
     *      （2）判断 char[i + 1] char[j]
     *
     * 拜托多动动脑子吧
     *
     * @param s
     * @return
     */
    public static int solution(String s) {
        int len = s.length();
        if (len < 2) {
            return len;
        }

        char[] charArray = s.toCharArray();

        int[][] dp = new int[len][len];

        // 对角线表示区间长度为1，肯定是回文数，因此初始化为1
        for (int i = 0; i < len; i++) {
            dp[i][i] = 1;
        }

        // 这里从右侧开始逐渐扩大范围检测
        for (int i = len - 2; i >= 0; i--) {
            for (int j = i + 1; j < len; j++) {
                if (charArray[i] == charArray[j]) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }

        // d[i][j] 表示 s[i...j]里面最长的回文子序列
        return dp[0][len - 1];
    }

    public static void main(String[] args) {
        String s = "bbbab";
        System.out.println(solution(s));
    }
}
