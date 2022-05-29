package leetcode.dynamicprogramming;

/**
 * @author maxjoker
 * @date 2022-05-29 14:52
 *
 * 647. 回文子串
 * 给你一个字符串 s ，请你统计并返回这个字符串中 回文子串 的数目。
 *
 * 回文字符串 是正着读和倒过来读一样的字符串。
 *
 * 子字符串 是字符串中的由连续字符组成的一个序列。
 *
 * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "abc"
 * 输出：3
 * 解释：三个回文子串: "a", "b", "c"
 * 示例 2：
 *
 * 输入：s = "aaa"
 * 输出：6
 * 解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa"
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 1000
 * s 由小写英文字母组成
 *
 */
public class PalindromicSubstrings {
    /**
     * 状态：dp[i][j] 表示字符串s在 [i, j]区间的子串是否为回文串
     * 状态转移方程：
     *      当 s[i] == s[j] && (j - i < 2 || dp[i + 1][j - 1]) 时，
     *      dp[i][j] = true
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(n^2)
     * @param s
     * @return
     */
    public static int solution(String s) {
        int len = s.length();
        if (len < 2) {
            return len;
        }

        boolean[][] dp = new boolean[len][len];
        int ans = 0;

        for (int j = 0; j < len; j++) {
            for (int i = 0; i <= j; i++) {
                if (s.charAt(i) == s.charAt(j) && (j - i < 2 || dp[i + 1][j - 1])) {
                    dp[i][j] = true;
                    ans++;
                }
            }
        }

        return ans;
    }

    /**
     * 中心扩展法
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(1)
     * @param s
     * @return
     */
    public static int solution2(String s) {
        int len = s.length();
        if (len < 2) {
            return len;
        }

        int ans = 0;

        for (int center = 0; center < 2 * len - 1; center++) {
            int left = center / 2;
            int right = left + center % 2;

            while (left >= 0 && right < len && s.charAt(left) == s.charAt(right)) {
                ans++;
                left--;
                right++;
            }
        }

        return ans;
    }
}
