package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author maxjoker
 * @date 2022-02-10 18:26
 *
 * 17. 电话号码的字母组合
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 *
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 *
 * 示例 1：
 *
 * 输入：digits = "23"
 * 输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
 * 示例 2：
 *
 * 输入：digits = ""
 * 输出：[]
 * 示例 3：
 *
 * 输入：digits = "2"
 * 输出：["a","b","c"]
 *
 * https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/
 *
 * 题解：https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/solution/hui-su-sou-suo-wu-xian-shi-hui-su-yan-du-you-xian-/
 */
public class LetterCombinationsOfAPhoneNumber {
    public static List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits.length() == 0) {
            return res;
        }
        String[] map = new String[]{"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        dfs(digits, map, 0, "", res);

        return res;
    }

    private static void dfs(String digits, String[] map, int index, String path, List<String> res) {
        if (index == digits.length()) {
            res.add(path);
            return;
        }

        // 因为电话号码是从2开始有字母的
        String curr = map[digits.charAt(index) - '2'];
        int len = curr.length();
        for (int i = 0; i < len; i++) {
            dfs(digits, map, index + 1, path + curr.charAt(i), res);
        }
    }

    public static void main(String[] args) {
        System.out.println(letterCombinations("23"));
    }
}
