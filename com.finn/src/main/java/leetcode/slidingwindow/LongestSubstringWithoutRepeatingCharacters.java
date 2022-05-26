package leetcode.slidingwindow;

import java.util.Arrays;

/**
 * @author maxjoker
 * @date 2022-05-26 20:46
 *
 * 3. 无重复字符的最长子串
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 *
 *
 * 示例 1:
 *
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 *
 * 输入: s = "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 *
 * 输入: s = "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 *
 *
 * 提示：
 *
 * 0 <= s.length <= 5 * 10^4
 * s 由英文字母、数字、符号和空格组成
 *
 */
public class LongestSubstringWithoutRepeatingCharacters {
    public static int solution(String s) {
        // 记录字符上一次出现的位置
        int[] last = new int[128];
        Arrays.fill(last, -1);

        int start = 0;
        int res = 0;

        for (int i = 0; i < s.length(); i++) {
            // 如 s 的第一位是 a，则 s.charAt(0) = 97, a 出现的位置在 97
            int index = s.charAt(i);
            // 起始位置，假设 abca 这个字符串，遍历到 第二个 a，此时 last[index] = 0，为了使子字符串没有重复的字符，因此起始位置是
            // 上一次出现位置 + 1
            start = Math.max(start, last[index] + 1);
            // i - start + 1 = 子字符串长度
            res = Math.max(res, i - start + 1);
            last[index] = i;
        }

        return res;
    }

    public static void main(String[] args) {
        String s = "abcadscad";
        System.out.println(solution(s));
    }
}
