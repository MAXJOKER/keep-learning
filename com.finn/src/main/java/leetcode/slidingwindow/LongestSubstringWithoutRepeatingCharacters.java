package leetcode.slidingwindow;

import java.util.Arrays;

/**
 * @author maxjoker
 * @date 2022-05-26 20:46
 *
 *
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
