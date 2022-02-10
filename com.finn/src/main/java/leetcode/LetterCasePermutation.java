package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author maxjoker
 * @date 2022-02-10
 *
 * 784. 字母大小写全排列
 * 给定一个字符串S，通过将字符串S中的每个字母转变大小写，我们可以获得一个新的字符串。返回所有可能得到的字符串集合。
 *
 *
 *
 * 示例：
 * 输入：S = "a1b2"
 * 输出：["a1b2", "a1B2", "A1b2", "A1B2"]
 *
 * 输入：S = "3z4"
 * 输出：["3z4", "3Z4"]
 *
 * 输入：S = "12345"
 * 输出：["12345"]
 *
 *
 * 提示：
 *
 * S 的长度不超过12。
 * S 仅由数字和字母组成。
 *
 * https://leetcode-cn.com/problems/letter-case-permutation/
 */
public class LetterCasePermutation {
    public static List<String> letterCasePermutation(String s) {
        List<String> result = new ArrayList<>();
        if (s.length() == 0) {
            return result;
        }

        int len = s.length();
        StringBuilder path = new StringBuilder();
        dfs(s, len, 0, path, result);

        return result;
    }

    /**
     * 回溯
     * @param s
     * @param len
     * @param depth
     * @param path
     * @param result
     */
    private static void dfs(String s, int len, int depth, StringBuilder path, List<String> result) {
        if (depth == len) {
            result.add(path.toString());
            return;
        }

        path.append(s.charAt(depth));
        dfs(s, len, depth + 1, path, result);
        //剪枝
        //从后向前删除单个字符
        path.deleteCharAt(path.length() - 1);

        if (!Character.isDigit(s.charAt(depth))) {
            //如果是小写字符就，转化为大写
            //如果是大写就，转化为小写
            path.append(s.charAt(depth) - 'a' >= 0 ? (char) (s.charAt(depth) - 32) : (char) (s.charAt(depth) + 32));
            dfs(s, len, depth + 1, path, result);
            path.deleteCharAt(path.length() - 1);
        }
    }

    public static List<String> letterCasePermutation2(String S) {
        List<StringBuilder> ans = new ArrayList();
        ans.add(new StringBuilder());

        for (char c: S.toCharArray()) {
            int n = ans.size();
            if (Character.isLetter(c)) {
                for (int i = 0; i < n; ++i) {
                    ans.add(new StringBuilder(ans.get(i)));
                    ans.get(i).append(Character.toLowerCase(c));
                    ans.get(n+i).append(Character.toUpperCase(c));
                }
            } else {
                for (int i = 0; i < n; ++i) {
                    ans.get(i).append(c);
                }
            }
        }

        List<String> finalans = new ArrayList();
        for (StringBuilder sb: ans) {
            finalans.add(sb.toString());
        }
        return finalans;
    }

    public static void main(String[] args) {
        System.out.println(letterCasePermutation2("ab1"));
    }
}
