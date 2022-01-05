package leetcode;


import java.util.Stack;

/**
 * author zhangjunhui
 * date 2022-01-01 12:14
 * 20. 有效的括号
 * https://leetcode-cn.com/problems/valid-parentheses/
 *
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 *  
 *
 * 示例 1：
 *
 * 输入：s = "()"
 * 输出：true
 * 示例 2：
 *
 * 输入：s = "()[]{}"
 * 输出：true
 */
public class ValidParentheses {
    public static boolean isValid(String s) {
        if (s.length() == 0 || s.length() % 2 != 0) {
            return false;
        }

        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(')');
            } else if (c == '[') {
                stack.push(']');
            } else if (c == '{') {
                stack.push('}');
            } else if (stack.isEmpty() || c != stack.pop()) {
                return false;
            }
        }

        if (stack.isEmpty()) return true;

        return false;
    }

    public static void main(String[] args) {
        String s = "]({[]})[";
        boolean result = isValid(s);
        System.out.println(result);
    }
}
