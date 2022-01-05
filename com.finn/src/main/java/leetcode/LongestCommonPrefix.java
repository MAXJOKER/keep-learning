package leetcode;

/**
 * @author zhangjunhui
 * @date 2021-01-05 21:45
 * https://leetcode-cn.com/problems/longest-common-prefix/
 *
 * 14. 最长公共前缀
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 *
 * 如果不存在公共前缀，返回空字符串 ""。
 *
 *
 *
 * 示例 1：
 *
 * 输入：strs = ["flower","flow","flight"]
 * 输出："fl"
 * 示例 2：
 *
 * 输入：strs = ["dog","racecar","car"]
 * 输出：""
 * 解释：输入不存在公共前缀。
 */
public class LongestCommonPrefix {
    /**
     * 思路
     * 标签：链表
     * 当字符串数组长度为 0 时则公共前缀为空，直接返回
     * 令最长公共前缀 ans 的值为第一个字符串，进行初始化
     * 遍历后面的字符串，依次将其与 ans 进行比较，两两找出公共前缀，最终结果即为最长公共前缀
     * 如果查找过程中出现了 ans 为空的情况，则公共前缀不存在直接返回
     * 时间复杂度：O(s)O(s)，s 为所有字符串的长度之和
     * @param strs
     * @return
     */
    public static String longestCommonPrefix(String[] strs) {
        String ans = strs[0];

        if (ans.length() == 0) {
            return "";
        }

        for (int i = 1; i < strs.length; i++) {
            int j = 0;
            for (; j < ans.length() && j < strs[i].length(); j++) {
                if (ans.charAt(j) != strs[i].charAt(j)) {
                    break;
                }
            }
            // 两两比对
            ans = ans.substring(0, j);
            if ("".equals(ans)) {
                return "";
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        String[] strs = new String[]{"flower", "flash", "flesh"};
        System.out.println(longestCommonPrefix(strs));
    }
}
