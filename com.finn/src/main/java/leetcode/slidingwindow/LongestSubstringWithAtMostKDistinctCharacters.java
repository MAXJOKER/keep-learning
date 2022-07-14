package leetcode.slidingwindow;

/**
 * @author maxjoker
 * @date 2022-07-14 22:06
 *
 * https://leetcode.cn/problems/longest-substring-with-at-most-k-distinct-characters/
 *
 * 第 340 题：至多包含 K 个不同字符的最长子串（中等）
 * 给定一个字符串 s ，找出 至多 包含 k 个不同字符的最长子串 T。 示例 1： 输入: s = "eceba", k = 2
 *
 * 输出: 3
 *
 * 解释: 则 T 为 "ece"，所以长度为 3。
 *  12345示例 2： 输入: s = "aa", k = 1
 *
 * 输出: 2
 *
 * 解释: 则 T 为 "aa"，所以长度为 2。
 *  12345Constraints: 1 <= s.length <= 5 * 10^4 0 <= k <= 50
 *
 */
public class LongestSubstringWithAtMostKDistinctCharacters {
    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     * @param s
     * @param k
     * @return
     */
    public static int solution(String s, int k) {
        int left = 0;
        int right = 0;
        int count = 0;
        int max = 0;

        int[] frequency = new int[128];

        while (right < s.length()) {
            if (frequency[s.charAt(right)] == 0) {
                count++;
            }

            frequency[s.charAt(right)]++;

            while (count > k) {
                frequency[s.charAt(left)]--;
                if (frequency[s.charAt(left)] == 0) {
                    count--;
                }

                left++;
            }

            max = Math.max(max, right - left + 1);

            right++;
        }

        return max;
    }

    public static void main(String[] args) {
        String s = "eceba";
        int k = 2;
        System.out.println(solution(s, k));
    }
}
