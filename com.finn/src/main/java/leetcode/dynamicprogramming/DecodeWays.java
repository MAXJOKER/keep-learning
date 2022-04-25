package leetcode.dynamicprogramming;

import org.apache.commons.lang3.StringUtils;

/**
 * @author maxjoker
 * @date 2022-04-25 21:32
 *
 * https://leetcode-cn.com/problems/decode-ways
 *
 * 91. 解码方法
 * 一条包含字母 A-Z 的消息通过以下映射进行了 编码 ：
 *
 * 'A' -> "1"
 * 'B' -> "2"
 * ...
 * 'Z' -> "26"
 * 要 解码 已编码的消息，所有数字必须基于上述映射的方法，反向映射回字母（可能有多种方法）。例如，"11106" 可以映射为：
 *
 * "AAJF" ，将消息分组为 (1 1 10 6)
 * "KJF" ，将消息分组为 (11 10 6)
 * 注意，消息不能分组为  (1 11 06) ，因为 "06" 不能映射为 "F" ，这是由于 "6" 和 "06" 在映射中并不等价。
 *
 * 给你一个只含数字的 非空 字符串 s ，请计算并返回 解码 方法的 总数 。
 *
 * 题目数据保证答案肯定是一个 32 位 的整数。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "12"
 * 输出：2
 * 解释：它可以解码为 "AB"（1 2）或者 "L"（12）。
 * 示例 2：
 *
 * 输入：s = "226"
 * 输出：3
 * 解释：它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
 * 示例 3：
 *
 * 输入：s = "0"
 * 输出：0
 * 解释：没有字符映射到以 0 开头的数字。
 * 含有 0 的有效映射是 'J' -> "10" 和 'T'-> "20" 。
 * 由于没有字符，因此没有有效的方法对此进行解码，因为所有数字都需要映射。
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 100
 * s 只包含数字，并且可能包含前导零。
 *
 */
public class DecodeWays {
    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     * @param s
     * @return
     */
    public static int numDecoding(String s) {
        if (StringUtils.isBlank(s)) {
            return 0;
        }

        char[] c = s.toCharArray();
        if (c[0] == '0') {
            return 0;
        }

        // dp[i] 以 s[i]结尾的前缀子串有多少种解法
        // if s[i] != '0', dp[i] = dp[i - 1] * 1
        // if 10 <= s[i - 1, i] <= 26, dp[i] += dp[i - 2] * 1
        int[] dp = new int[s.length()];
        dp[0] = 1;

        for (int i = 1; i < s.length(); i++) {
            // 到 dp[i] 有多少种解法  dp[i] 单个数字组合
            if (c[i] != '0') {
                dp[i] = dp[i - 1];
            }

            // 和前一位组合
            // 这里是 10 <= s[i - 1, i] <= 26, 乘以 10 是因为 c[i-1] - '0' 是 十位
            int num = 10 * (c[i - 1] - '0') + (c[i] - '0');
            if (num >= 10 && num <= 26) {
                if (i == 1) {
                    dp[i]++;
                } else {
                    dp[i] += dp[i - 2];
                }
            }
        }

        return dp[s.length() - 1];
    }

    public static void main(String[] args) {
        String s = "226";
        System.out.println(numDecoding(s));
    }
}
