package leetcode.dynamicprogramming;

/**
 * @author maxjoker
 * @date 2022-06-05 00:21
 *
 * 1143. 最长公共子序列
 * 给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。
 *
 * 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
 *
 * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
 * 两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。
 *
 *
 *
 * 示例 1：
 *
 * 输入：text1 = "abcde", text2 = "ace"
 * 输出：3
 * 解释：最长公共子序列是 "ace" ，它的长度为 3 。
 * 示例 2：
 *
 * 输入：text1 = "abc", text2 = "abc"
 * 输出：3
 * 解释：最长公共子序列是 "abc" ，它的长度为 3 。
 * 示例 3：
 *
 * 输入：text1 = "abc", text2 = "def"
 * 输出：0
 * 解释：两个字符串没有公共子序列，返回 0 。
 *
 *
 * 提示：
 *
 * 1 <= text1.length, text2.length <= 1000
 * text1 和 text2 仅由小写英文字符组成。
 *
 */
public class LongestCommonSubsequence {
    /**
     * 这种做法没有考虑全顺序问题。。。 ac不了
     * @param s1
     * @param s2
     * @return
     */
    public static int solution(String s1, String s2) {
        if (s1.length() == 0 || s2.length() == 0) {
            return 0;
        }

        int length1 = s1.length();
        int length2 = s2.length();

        int res = 0;
        if (length1 > length2) {
            for (int i = 0; i < length2; i++) {
                if (s1.indexOf(s2.charAt(i)) != -1) {
                    res++;
                }
            }
        } else {
            for (int j = 0; j < length1; j++) {
                if (s2.indexOf(s1.charAt(j)) != -1) {
                    res++;
                }
            }
        }

        return res;
    }

    /**
     * 状态定义：dp[i][j] 表示长度为 i 的 text1 的前缀子串与长度为 j 的 text2
     *          的前缀子串的“最长公共子串”的长度。
     * 状态转移方程：
     *              dp[i][j] = dp[i - 1][j - 1], text1.charAt(i) == text2.charAt(j)
     *              dp[i][j] = max(dp[i][j - 1], dp[i - 1][j])
     *
     *  表格：https://pic.leetcode-cn.com/1617411822-KhEKGw-image.png
     *
     * @param text1
     * @param text2
     * @return
     */
    public static int solution2(String text1, String text2) {
        int len1 = text1.length();
        int len2 = text2.length();

        int[][] dp = new int[len1 + 1][len2 + 1];

        // 表格 dp 的第 1 行和第 1 列均为 0。
        for (int i = 0; i < len1; i++) {
            dp[i][0] = 0;
        }

        for (int j = 0; j < len2; j++) {
            dp[0][j] = 0;
        }

        for (int i = 0; i < len1; i++) {
            for (int j = 0; j < len2; j++) {
                if (text1.charAt(i) == text2.charAt(j)) {
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                } else {
                    dp[i + 1][j + 1] = Math.max(dp[i][j + 1], dp[i + 1][j]);
                }
            }
        }

        return dp[len1][len2];
    }

    public static void main(String[] args) {
        String s1 = "ezupkr";
        String s2 = "ubmrapg";

        System.out.println(solution2(s1, s2));
    }
}
