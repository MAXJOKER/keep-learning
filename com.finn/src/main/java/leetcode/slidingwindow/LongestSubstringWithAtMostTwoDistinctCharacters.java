package leetcode.slidingwindow;

/**
 * @author maxjoker
 * @date 2022-07-14 10:00
 *
 * https://leetcode.cn/problems/longest-substring-with-at-most-two-distinct-characters/
 *
 * 会员题）「力扣」第 159 题：至多包含两个不同字符的最长子串（中等）
 *
 * 题目描述 给定一个字符串 s ，找出 至多 包含两个不同字符的最长子串 t ，并返回该子串的长度。 示例 1： 输入: "eceba"
 *
 * 输出: 3
 *
 * 解释: t 是 "ece"，长度为 3。
 *  12345示例 2: 输入: "ccaabbb"
 *
 * 输出: 5
 *
 * 解释: t 是 "aabbb"，长度为 5。
 *  Constraints: 1 <= s.length <= 10^4 s consists of English letters.
 *
 *  思路分析 如果刚好包含 3 种不同字符，右边界不需要继续向右边扩展，此时应该把左边界向右边扩展。
 *  因为题目只问 至多 包含 2 种不同字符，还得找最长的。
 *
 */
public class LongestSubstringWithAtMostTwoDistinctCharacters {
    public static int solution(String s) {
        int len = s.length();
        char[] array = s.toCharArray();

        int right = 0;
        int left = 0;
        int count = 0;
        int res = 0;
        int[] frequency = new int[128];

        while (right < len) {
            if (frequency[array[right]] == 0) {
                count++;
            }

            frequency[array[right]]++;

            // 窗口滑动判断，保持窗口固定大小
            while (count > 2) {
                // 减去最左边元素
                frequency[array[left]]--;
                // 如果最左边元素为0，count要减1
                if (frequency[array[left]] == 0) {
                    count--;
                }
                left++;
            }

            res = Math.max(res, right - left + 1);
            right++;
        }

        return res;
    }

    public static void main(String[] args) {
        String s = "abccce";
        System.out.println(solution(s));
    }
}
