package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author maxjoker
 * @date 2022-02-09 20:31
 *
 * 22. 括号生成
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 3
 * 输出：["((()))","(()())","(())()","()(())","()()()"]
 * 示例 2：
 *
 * 输入：n = 1
 * 输出：["()"]
 *
 *
 * 提示：
 *
 * 1 <= n <= 8
 *
 * https://leetcode-cn.com/problems/generate-parentheses/
 *
 * 题解：https://leetcode-cn.com/problems/generate-parentheses/solution/hui-su-suan-fa-by-liweiwei1419/
 */
public class GenerateParentheses {
    public static List<String> generateParentheses(int n) {
        List<String> res = new ArrayList<>();
        if (n == 0) {
            return res;
        }

        dfs("", 0, 0, n, res);

        return res;
    }

    /**
     *
     * @param cur 当前递归得到的结果
     * @param left 左括号已经用了几个
     * @param right 右括号已经用了几个
     * @param n 左、右括号得用几个
     * @param res 最终结果
     */
    private static void dfs(String cur, int left, int right, int n, List<String> res) {
        // 因为每一次尝试，都使用新的字符串变量，所以无需回溯
        if (left == n && right == n) {
            res.add(cur);
            return;
        }

        // 剪枝
        if (left < right) {
            return;
        }

        if (left < n) {
            dfs(cur + "(", left + 1, right, n, res);
        }

        if (right < n) {
            dfs(cur + ")", left, right + 1, n, res);
        }
    }

    public static void main(String[] args) {
        System.out.println(generateParentheses(2));
    }
}
