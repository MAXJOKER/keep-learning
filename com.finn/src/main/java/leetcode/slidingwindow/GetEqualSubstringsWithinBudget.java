package leetcode.slidingwindow;

/**
 * @author maxjoker
 * @date 2022-07-12 21:32
 *
 * https://leetcode.cn/problems/get-equal-substrings-within-budget/
 *
 * 1208. 尽可能使字符串相等
 * 给你两个长度相同的字符串，s 和 t。
 *
 * 将 s 中的第 i 个字符变到 t 中的第 i 个字符需要 |s[i] - t[i]| 的开销（开销可能为 0），也就是两个字符的 ASCII 码值的差的绝对值。
 *
 * 用于变更字符串的最大预算是 maxCost。在转化字符串时，总开销应当小于等于该预算，这也意味着字符串的转化可能是不完全的。
 *
 * 如果你可以将 s 的子字符串转化为它在 t 中对应的子字符串，则返回可以转化的最大长度。
 *
 * 如果 s 中没有子字符串可以转化成 t 中对应的子字符串，则返回 0。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "abcd", t = "bcdf", maxCost = 3
 * 输出：3
 * 解释：s 中的 "abc" 可以变为 "bcd"。开销为 3，所以最大长度为 3。
 * 示例 2：
 *
 * 输入：s = "abcd", t = "cdef", maxCost = 3
 * 输出：1
 * 解释：s 中的任一字符要想变成 t 中对应的字符，其开销都是 2。因此，最大长度为 1。
 * 示例 3：
 *
 * 输入：s = "abcd", t = "acde", maxCost = 0
 * 输出：1
 * 解释：a -> a, cost = 0，字符串未发生变化，所以最大长度为 1。
 *
 *
 * 提示：
 *
 * 1 <= s.length, t.length <= 10^5
 * 0 <= maxCost <= 10^6
 * s 和 t 都只含小写英文字母。
 *
 */
public class GetEqualSubstringsWithinBudget {
    /**
     * 滑动窗口
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     * @param s
     * @param t
     * @param maxCost
     * @return
     */
    public static int equalSubstring(String s, String t, int maxCost) {
        int left = 0;
        int right = 0;
        int totalCost = 0;
        int sum = 0;
        int[] leftValue = new int[s.length()];

        // 预处理 把差值保存到数组中
        for (int i = 0; i < s.length(); i++) {
            leftValue[i] = Math.abs(s.charAt(i) - t.charAt(i));
        }

        while (right < s.length()) {
            int cost = Math.abs(s.charAt(right) - t.charAt(right));
            totalCost += cost;

            // 如果总消耗大于maxCost，left指针右移，同时totalCost需要减去对应的差值
            while (totalCost > maxCost) {
                totalCost -= leftValue[left];
                left++;
            }

            sum = Math.max(sum, right - left + 1);
            right++;
        }

        return sum;
    }

    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     * @param s
     * @param t
     * @param maxCost
     * @return
     */
    public static int solution2(String s, String t, int maxCost) {
        int left = 0;
        int right = 0;
        int cost = 0;

        while (right < s.length()) {
            cost += Math.abs(s.charAt(right) - t.charAt(right));
            if (cost > maxCost) {
                cost -= Math.abs(s.charAt(left) - t.charAt(left));
                left++;
            }

            right++;
        }

        return right - left;
    }

    public static void main(String[] args) {
        String s = "abcd";
        String t = "acde";
        int maxCost = 0;

        System.out.println(equalSubstring(s, t, maxCost));
        System.out.println(solution2(s, t, maxCost));
    }
}
