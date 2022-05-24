package leetcode.dynamicprogramming;

/**
 * @author maxjoker
 * @date 2022-02-18 16:11
 *
 * 5. 最长回文子串
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "babad"
 * 输出："bab"
 * 解释："aba" 同样是符合题意的答案。
 * 示例 2：
 *
 * 输入：s = "cbbd"
 * 输出："bb"
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 1000
 * s 仅由数字和英文字母组成
 *
 * https://leetcode-cn.com/problems/longest-palindromic-substring/
 */
public class LongestPalindromicSubstring {

    /**
     * 暴力解法
     * 时间复杂度：O(n^3)
     * 空间复杂度：O(1)
     * @param s
     * @return
     */
    public static String longestPalindrome(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }

        int maxLen = 1;
        int begin = 0;
        char[] charArray = s.toCharArray();

        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                if (j - i + 1 > maxLen && isPalindromic(charArray, i, j)) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }

        return s.substring(begin, begin + maxLen);
    }

    /**
     * 验证子串s[left...right]是否为回文串
     * @param charArray
     * @param left
     * @param right
     * @return
     */
    private static boolean isPalindromic(char[] charArray, int left, int right) {
        while (left < right) {
            if (charArray[left] != charArray[right]) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }

    /**
     * 动态规划
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(n^2)
     *
     * 题解：https://leetcode-cn.com/problems/longest-palindromic-substring/solution/zui-chang-hui-wen-zi-chuan-by-leetcode-solution/
     * 看题解里面的表格法，很有帮助
     * @param s
     * @return
     */
    public static String longestPalindromeByDP(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }

        char[] charArray = s.toCharArray();
        // dp[i][j] 表示 s[i..j] 是否是回文串
        Boolean[][] dp = new Boolean[len][len];
        // 初始化：所有长度为 1 的子串都是回文串
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }

        int maxLen = 1;
        int begin = 0;

        // 这里往左侧开始逐渐缩小范围检测
        for (int j = 1; j < len; j++) {
            for (int i = 0; i < j; i++) {
                // left，right字符不等，肯定不是回文
                if (charArray[j] != charArray[i]) {
                    dp[i][j] = false;
                } else {
                    // dp[i][j] 相等，对比子串
                    // j - i < 3 由 j - 1 - (i + 1) + 1 < 2 得到
                    if (j - i < 3) {
                        // 子串长度严格小于2，且charArray[i] == charArray[j]，为回文串，这里其实就是 bab 这样的例子
                        dp[i][j] = true;
                    } else {
                        // 看子串是否为回文串
                        dp[i][j] = dp[i+1][j-1];
                    }
                }

                // 取最长子串
                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }

        return s.substring(begin, begin + maxLen);
    }

    /**
     * 中心扩散法
     * 遍历每一个下标，以这个下标为中心，利用「回文串」中心对称的特点，往两边扩散，看最多能扩散多远。
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(1_
     * @param s
     * @return
     */
    public static String longestPalindromeByExpandAroundCenter(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }

        int maxLen = 1;
        int begin = 0;

        for (int i = 0; i < len; i++) {
            int[] odd = expandAroundCenter(s, i, i);
            int[] even = expandAroundCenter(s, i, i+1);

            int[] max = odd[1] > even[1] ? odd : even;
            if (max[1] > maxLen) {
                maxLen = max[1];
                begin = max[0];
            }
        }

        return s.substring(begin, begin + maxLen);
    }

    private static int[] expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }

        // 最后一次循环，left--, right++，所以最后结果需要减2, (right - 1) - (left + 1) + 1;
        // 返回起始位置 和 长度
        return new int[]{left + 1, right - left - 1};
    }

    public static void main(String[] args) {
        System.out.println(longestPalindromeByExpandAroundCenter("abaadaa"));
    }

}
