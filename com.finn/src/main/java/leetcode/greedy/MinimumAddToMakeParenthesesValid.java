package leetcode.greedy;

/**
 * @author maxjoker
 * @date 2022-06-26 18:14
 *
 * https://leetcode.cn/problems/minimum-add-to-make-parentheses-valid/
 *
 * 921. 使括号有效的最少添加
 * 只有满足下面几点之一，括号字符串才是有效的：
 *
 * 它是一个空字符串，或者
 * 它可以被写成 AB （A 与 B 连接）, 其中 A 和 B 都是有效字符串，或者
 * 它可以被写作 (A)，其中 A 是有效字符串。
 * 给定一个括号字符串 s ，移动N次，你就可以在字符串的任何位置插入一个括号。
 *
 * 例如，如果 s = "()))" ，你可以插入一个开始括号为 "(()))" 或结束括号为 "())))" 。
 * 返回 为使结果字符串 s 有效而必须添加的最少括号数。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "())"
 * 输出：1
 * 示例 2：
 *
 * 输入：s = "((("
 * 输出：3
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 1000
 * s 只包含 '(' 和 ')' 字符。
 *
 */
public class MinimumAddToMakeParenthesesValid {
    /**
     * 时间复杂度：O(N)
     * 空间复杂度：O(1)
     *
     * 平衡法
     *
     * @param s
     * @return
     */
    public static int minAddToMakeValid(String s) {
        int len = s.length();
        if (len < 2) {
            return len;
        }

        int res = 0;
        int need = 0;
        for (int i = 0; i < len; i++) {
            need += s.charAt(i) == '(' ? 1 : -1;
            // need == -1 说明 s.charAt(i) = ')'
            // 此时需要在前面补一个 '(', 因此need++，置为0
            // 最终结果res++
            if (need == -1) {
                need++;
                res++;
            }
        }

        // 需要 + need 是因为，最后几个可能都是 '(' ,需要给加上
        return res + need;
    }

    public static void main(String[] args) {
        String s = "()))";
        System.out.println(minAddToMakeValid(s));
    }
}
