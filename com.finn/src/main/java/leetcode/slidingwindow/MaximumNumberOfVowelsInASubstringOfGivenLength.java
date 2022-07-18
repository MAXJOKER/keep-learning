package leetcode.slidingwindow;

/**
 * @author maxjoker
 * @date 2022-07-18 22:55
 *
 * https://leetcode.cn/problems/maximum-number-of-vowels-in-a-substring-of-given-length/
 *
 * 1456. 定长子串中元音的最大数目
 * 给你字符串 s 和整数 k 。
 *
 * 请返回字符串 s 中长度为 k 的单个子字符串中可能包含的最大元音字母数。
 *
 * 英文中的 元音字母 为（a, e, i, o, u）。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "abciiidef", k = 3
 * 输出：3
 * 解释：子字符串 "iii" 包含 3 个元音字母。
 * 示例 2：
 *
 * 输入：s = "aeiou", k = 2
 * 输出：2
 * 解释：任意长度为 2 的子字符串都包含 2 个元音字母。
 * 示例 3：
 *
 * 输入：s = "leetcode", k = 3
 * 输出：2
 * 解释："lee"、"eet" 和 "ode" 都包含 2 个元音字母。
 * 示例 4：
 *
 * 输入：s = "rhythms", k = 4
 * 输出：0
 * 解释：字符串 s 中不含任何元音字母。
 * 示例 5：
 *
 * 输入：s = "tryhard", k = 4
 * 输出：1
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 10^5
 * s 由小写英文字母组成
 * 1 <= k <= s.length
 *
 */
public class MaximumNumberOfVowelsInASubstringOfGivenLength {
    /**
     * 滑动窗口
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     * @param s
     * @param k
     * @return
     */
    public static int maxVowels(String s, int k) {
        int len = s.length();
        int left = 0;
        int right = 0;
        int count = 0;
        int res = 0;

        while (right < len) {
            if (isVowel(s.charAt(right))) {
                count++;
            }

            while (right - left + 1 > k) {
                if (isVowel(s.charAt(left))) {
                    count--;
                }
                left++;

            }

            res = Math.max(res, count);
            right++;
        }

        return res;
    }

    public static void main(String[] args) {
        String s = "abciiidef";
        int k = 3;
        System.out.println(maxVowels(s, k));
    }

    private static boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}
