package leetcode.greedy;

/**
 * @author maxjoker
 * @date 2022-06-26 18:31
 *
 *
 * https://leetcode.cn/problems/construct-k-palindrome-strings
 *
 * 1400. 构造 K 个回文字符串
示例 1：

输入：s = "annabelle", k = 2
输出：true
解释：可以用 s 中所有字符构造 2 个回文字符串。
一些可行的构造方案包括："anna" + "elble"，"anbna" + "elle"，"anellena" + "b"
示例 2：

输入：s = "leetcode", k = 3
输出：false
解释：无法用 s 中所有字符构造 3 个回文串。
示例 3：

输入：s = "true", k = 4
输出：true
解释：唯一可行的方案是让 s 中每个字符单独构成一个字符串。
示例 4：

输入：s = "yzyzyzyzyzyzyzy", k = 2
输出：true
解释：你只需要将所有的 z 放在一个字符串中，所有的 y 放在另一个字符串中。那么两个字符串都是回文串。
示例 5：

输入：s = "cr", k = 7
输出：false
解释：我们没有足够的字符去构造 7 个回文串。

提示：

1 <= s.length <= 10^5
s中所有字符都是小写英文字母。
1 <= k <= 10^5
 *
 */
public class ConstructKPalindromeStrings {
    /**
     * 题解：https://leetcode.cn/problems/construct-k-palindrome-strings/solution/gou-zao-k-ge-hui-wen-zi-fu-chuan-by-leetcode-solut/
     *
     * 如果字符数量小于 k 那直接返回 false 否则统计一下出现次数为奇数的字符的种类，所得的回文串至少要有这么多
     * 因为奇数字符的话只能放在回文串的正中心，而这样的位置一个回文串只有一个
     *
     * 时间复杂度：O(N+∣Σ∣)，其中 N 是字符串 s 的长度，Σ 是字符集（即字符串中可能出现的字符种类数），
     * 在本题中字符串只会包含小写字母，因此 ∣Σ∣=26。我们需要对字符串 s 进行一次遍历，得到每个字符出现的次数，
     * 时间复杂度为 O(N)。
     * 在这之后，我们需要遍历每一种字符，统计出现奇数次的字符数量，时间复杂度为 O(∣Σ∣)。
     *
     * 空间复杂度：O(∣Σ∣)。我们需要使用数组或哈希表存储每个字符出现的次数。
     *
     * @param s
     * @param k
     * @return
     */
    public static boolean canConstruct(String s, int k) {
        int len = s.length();
        if (len < k) {
            return false;
        }

        int[] c = new int[26];
        for (int i = 0; i < len; i++) {
            c[s.charAt(i) - 'a']++;
        }

        int odd = 0;
        for (int i : c) {
            if (i == 0) {
                continue;
            }
            if (i % 2 != 0) {
                odd++;
            }
        }

        return odd <= k;
    }

    public static void main(String[] args) {
        String s = "sss";
        int k = 2;
        System.out.println(canConstruct(s, k));
    }
}
