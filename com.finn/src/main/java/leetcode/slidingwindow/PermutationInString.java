package leetcode.slidingwindow;

import java.util.Arrays;

/**
 * @author maxjoker
 * @date 2022-07-06 21:50
 *
 * https://leetcode.cn/problems/permutation-in-string
 *
 * 567. 字符串的排列
 * 给你两个字符串 s1 和 s2 ，写一个函数来判断 s2 是否包含 s1 的排列。如果是，返回 true ；否则，返回 false 。
 *
 * 换句话说，s1 的排列之一是 s2 的 子串 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s1 = "ab" s2 = "eidbaooo"
 * 输出：true
 * 解释：s2 包含 s1 的排列之一 ("ba").
 * 示例 2：
 *
 * 输入：s1= "ab" s2 = "eidboaoo"
 * 输出：false
 *
 *
 * 提示：
 *
 * 1 <= s1.length, s2.length <= 104
 * s1 和 s2 仅包含小写字母
 *
 */
public class PermutationInString {
    /**
     * 滑动窗口
     * 时间复杂度：O(n+m+∣Σ∣) n、m 为 s1, s2 长度，∣Σ∣ = 26
     * 空间复杂度：O(∣Σ∣)
     * @param s1
     * @param s2
     * @return
     *
     * 由于排列不会改变字符串中每个字符的个数，所以只有当两个字符串每个字符的个数均相等时，
     * 一个字符串才是另一个字符串的排列。
     *
     * 根据这一性质，记 s1的长度为 n，我们可以遍历 s2 中的每个长度为 n 的子串，
     * 判断子串和 s1 中每个字符的个数是否相等，若相等则说明该子串是 s1的一个排列。
     *
     * 使用两个数组 cnt1  和 cnt2
     * cnt1 统计 s1中各个字符的个数，cnt2 统计当前遍历的子串中各个字符的个数。
     *
     * 由于需要遍历的子串长度均为 n，我们可以使用一个固定长度为 n 的滑动窗口来维护cnt2
     * 滑动窗口每向右滑动一次，就多统计一次进入窗口的字符，少统计一次离开窗口的字符。
     * 然后，判断 cnt1是否与 cnt2相等，若相等则意味着 s1的排列之一是 s2的子串。
     *
     */
    public static boolean checkInclusion(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();

        if (m < n) {
            return false;
        }

        int[] cnt1 = new int[26];
        int[] cnt2 = new int[26];

        for (int i = 0; i < n; i++) {
            cnt1[s1.charAt(i) - 'a']++;
            cnt2[s2.charAt(i) - 'a']++;
        }

        if (Arrays.equals(cnt1, cnt2)) {
            return true;
        }

        for (int i = n; i < m; i++) {
            cnt2[s2.charAt(i) - 'a']++;
            cnt2[s2.charAt(i - n) - 'a']--;

            if (Arrays.equals(cnt1, cnt2)) {
                return true;
            }
        }

        return false;
    }

    /**
     * https://leetcode.cn/problems/permutation-in-string/solution/zi-fu-chuan-de-pai-lie-by-leetcode-solut-7k7u/
     * @param s1
     * @param s2
     * @return
     */
    public static boolean checkInclusion2(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();

        if (m < n) {
            return false;
        }

        int[] cnt = new int[26];
        for (int i = 0; i < n; i++) {
            cnt[s1.charAt(i) - 'a']--;
        }

        int right = 0;
        int left = 0;

        for (right = 0; right < m; right++) {
            int x = s2.charAt(right) - 'a';
            cnt[x]++;

            while (cnt[x] > 0) {
                cnt[s2.charAt(left) - 'a']--;
                left++;
            }

            if (right - left + 1 == n) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        String s1 = "ab";
        String s2 = "adfadsfaefwaefasdfb";
        System.out.println(checkInclusion(s1, s2));
        System.out.println(checkInclusion2(s1, s2));
    }
}
