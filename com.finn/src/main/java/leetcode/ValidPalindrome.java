package leetcode;

/**
 * 125. 验证回文串
 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 *
 * 说明：本题中，我们将空字符串定义为有效的回文串。
 *
 *
 *
 * 示例 1:
 *
 * 输入: "A man, a plan, a canal: Panama"
 * 输出: true
 * 解释："amanaplanacanalpanama" 是回文串
 * 示例 2:
 *
 * 输入: "race a car"
 * 输出: false
 * 解释："raceacar" 不是回文串
 *
 * https://leetcode-cn.com/problems/valid-palindrome/
 */
public class ValidPalindrome {
    public static boolean isPalindrome(String s) {
        if (s == null) {
            return true;
        }
        s = s.toLowerCase();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if ((c >= 'a' && c <= 'z') || (c >= '0' && c <= '9')) {
                sb.append(s.charAt(i));
            }
        }

        return sb.toString().equals(sb.reverse().toString());
    }

    public static void main(String[] args) {
        String s = " race a car";
        System.out.println(isPalindrome(s));
    }
}
