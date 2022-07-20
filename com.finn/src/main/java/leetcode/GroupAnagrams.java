package leetcode;

import java.util.*;

/**
 * @author maxjoker
 * @date 2022-07-20 21:58
 *
 * https://leetcode.cn/problems/group-anagrams/
 *
 * 49. 字母异位词分组
 * 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
 *
 * 字母异位词 是由重新排列源单词的字母得到的一个新单词，所有源单词中的字母通常恰好只用一次。
 *
 *
 *
 * 示例 1:
 *
 * 输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
 * 输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
 * 示例 2:
 *
 * 输入: strs = [""]
 * 输出: [[""]]
 * 示例 3:
 *
 * 输入: strs = ["a"]
 * 输出: [["a"]]
 *
 *
 * 提示：
 *
 * 1 <= strs.length <= 10^4
 * 0 <= strs[i].length <= 100
 * strs[i] 仅包含小写字母
 *
 */
public class GroupAnagrams {
    /**
     * 时间复杂度：O(nklogk)
     * 空间复杂度：O(nk)
     * n 字符串数量，k字符串最大长度，
     * @param strs
     * @return
     */
    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            char[] c = s.toCharArray();
            Arrays.sort(c);
            String key = new String(c);
            List<String> list = map.getOrDefault(key, new ArrayList<>());
            list.add(s);
            map.put(key, list);
        }

        return new ArrayList<List<String>>(map.values());
    }

    /**
     * 时间复杂度：O(n(k+∣Σ∣))
     * 空间复杂度：O(n(k+∣Σ∣))
     * n 字符串数量，k字符串最大长度，∣Σ∣=26
     * @param strs
     * @return
     */
    public static List<List<String>> solution2(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            char[] c = s.toCharArray();
            int[] counts = new int[26];
            for (int i = 0; i < c.length; i++) {
                counts[c[i] - 'a']++;
            }

            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < 26; j++) {
                if (counts[j] != 0) {
                    sb.append((char) (j + 'a'));
                    sb.append(counts[j]);
                }
            }

            String key = sb.toString();
            List<String> list = map.getOrDefault(key, new ArrayList<>());
            list.add(s);
            map.put(key, list);
        }

        return new ArrayList<>(map.values());
    }

    public static void main(String[] args) {
        String[] strs = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(groupAnagrams(strs));
        System.out.println(solution2(strs));
    }
}
