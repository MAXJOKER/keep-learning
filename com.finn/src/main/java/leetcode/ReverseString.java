package leetcode;

/**
 * 344. 反转字符串
 * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 s 的形式给出。
 *
 * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = ["h","e","l","l","o"]
 * 输出：["o","l","l","e","h"]
 * 示例 2：
 *
 * 输入：s = ["H","a","n","n","a","h"]
 * 输出：["h","a","n","n","a","H"]
 *
 * https://leetcode-cn.com/problems/reverse-string/
 */
public class ReverseString {
    public static void reverseString(char[] s) {
        for (int i = 0; i < s.length / 2; i++) {
            if (i == s.length - i - 1) {
                continue;
            }

            if (s[i] == s[s.length - i - 1]) {
                continue;
            }

            char temp = s[i];
            s[i] = s[s.length - i - 1];
            s[s.length - i - 1] = temp;
        }
    }

    public static void main(String[] args) {
        char[] s = new char[]{'h','a','n','n','a','h'};
        reverseString(s);
        for (int i = 0; i < s.length; i++) {
            System.out.println(s[i]);
        }
    }
}
