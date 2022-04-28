package leetcode.dynamicprogramming;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author maxjoker
 * @date 2022-04-28 23:11
 *
 * https://leetcode-cn.com/problems/word-break/
 *
 * 139. 单词拆分
 * 给你一个字符串 s 和一个字符串列表 wordDict 作为字典。请你判断是否可以利用字典中出现的单词拼接出 s 。
 *
 * 注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。
 *
 *
 *
 * 示例 1：
 *
 * 输入: s = "leetcode", wordDict = ["leet", "code"]
 * 输出: true
 * 解释: 返回 true 因为 "leetcode" 可以由 "leet" 和 "code" 拼接成。
 * 示例 2：
 *
 * 输入: s = "applepenapple", wordDict = ["apple", "pen"]
 * 输出: true
 * 解释: 返回 true 因为 "applepenapple" 可以由 "apple" "pen" "apple" 拼接成。
 *      注意，你可以重复使用字典中的单词。
 * 示例 3：
 *
 * 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出: false
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 300
 * 1 <= wordDict.length <= 1000
 * 1 <= wordDict[i].length <= 20
 * s 和 wordDict[i] 仅有小写英文字母组成
 * wordDict 中的所有字符串 互不相同
 *
 */
public class WordBreak {
    public static boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordSet = new HashSet<>(wordDict);
        int len = s.length();

        boolean[] dp = new boolean[len];
        for (int right = 0; right < len; right++) {
            if (wordSet.contains(s.substring(0, right + 1))) {
                dp[right] = true;
                continue;
            }

            for (int left = right - 1; left >= 0; left--) {
                if (wordSet.contains(s.substring(left + 1, right + 1)) && dp[left]) {
                    dp[right] = true;
                    break;
                }
            }
        }

        return dp[len - 1];
    }

    public static void main(String[] args) {
        String s = "leetcode";
        List<String> l = new ArrayList<>();
        l.add("leet");
        l.add("code");
        System.out.println(wordBreak(s, l));
    }
}
