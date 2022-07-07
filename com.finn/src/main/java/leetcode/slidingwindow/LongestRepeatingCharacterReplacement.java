package leetcode.slidingwindow;

/**
 * @author maxjoker
 * @date 2022-07-07 21:00
 *
 * https://leetcode.cn/problems/longest-repeating-character-replacement/
 *
 * 424. 替换后的最长重复字符
 * 给你一个字符串 s 和一个整数 k 。你可以选择字符串中的任一字符，并将其更改为任何其他大写英文字符。该操作最多可执行 k 次。
 *
 * 在执行上述操作后，返回包含相同字母的最长子字符串的长度。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "ABAB", k = 2
 * 输出：4
 * 解释：用两个'A'替换为两个'B',反之亦然。
 * 示例 2：
 *
 * 输入：s = "AABABBA", k = 1
 * 输出：4
 * 解释：
 * 将中间的一个'A'替换为'B',字符串变为 "AABBBBA"。
 * 子串 "BBBB" 有最长重复字母, 答案为 4。
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 105
 * s 仅由大写英文字母组成
 * 0 <= k <= s.length
 *
 */
public class LongestRepeatingCharacterReplacement {
    /**
     * 题解：https://leetcode.cn/problems/longest-repeating-character-replacement/solution/tong-guo-ci-ti-liao-jie-yi-xia-shi-yao-shi-hua-don/
     *
     * 时间复杂度：O(n)
     * 空间复杂度：O(∣Σ∣), 26个字符长度
     *
     * 如果当前字符串中的出现次数最多的字母个数 + K 大于串长度，那么这个串就是满足条件的
     *
     * @param s
     * @param k
     * @return
     */
    public static int characterReplacement(String s, int k) {
        int[] map = new int[26];
//        char[] chars = s.toCharArray();
        int left = 0;
        int right = 0;
        // 滑动窗口历史上在一个窗口内出现次数最多的字母的次数（仅限在当时的窗口内出现）
        int historyCharMax = 0;

//        for (right = 0; right < chars.length; right++) {
        for (right = 0; right < s.length(); right++) {
            // 将右指针右移后对应的新元素添加到map中
//            int index = chars[right] - 'A';
            int index = s.charAt(right) - 'A';
            map[index]++;
            // 更新历史最大出现次数
            historyCharMax = Math.max(historyCharMax, map[index]);
            // 如果新加元素（即新加元素就是当下出现次数最多的字母）使得当下historyCharMax增加了1，则扩大窗口
            // 否则，不扩窗，窗口滑动（右指针已经右移一位了，需要把左指针左移，执行if语句）
            if (right - left + 1 > historyCharMax + k) {
//                map[chars[left] - 'A']--;
                map[s.charAt(left) - 'A']--;
                left++;
            }
        }

        // 即right - left最终窗口的长度 右指针right最终一定会移到chars.length即数组的末端
//        return chars.length - left;
        return s.length() - left;
    }

    public static void main(String[] args) {
        String s = "SDFAFAFAFWEFA";
        int k = 2;
        System.out.println(characterReplacement(s, k));
    }
}
