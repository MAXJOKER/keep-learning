package leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * 387. 字符串中的第一个唯一字符
 * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
 *
 *
 *
 * 示例：
 *
 * s = "leetcode"
 * 返回 0
 *
 * s = "loveleetcode"
 * 返回 2
 *
 *
 * 提示：你可以假定该字符串只包含小写字母。
 * https://leetcode-cn.com/problems/first-unique-character-in-a-string/
 */
public class FirstUniqueCharacterInAString {
    /**
     * 时间复杂度：O(n)
     * @param s
     * @return
     */
    public static int firstUniqChar(String s) {
        HashMap<Character, Integer> charMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            charMap.put(s.charAt(i), charMap.getOrDefault(s.charAt(i), 0) + 1);
        }

        int index = -1;
        for (int i = 0; i < s.length(); i++) {
            if (charMap.get(s.charAt(i)) == 1) {
                index = i;
                break;
            }
        }

        return index;
    }

    public static int firstUniqChar2(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (s.indexOf(s.charAt(i)) == s.lastIndexOf(s.charAt(i))) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        String s = "leetcode";
        System.out.println(firstUniqChar2(s));
    }
}
