package leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author maxjoker
 * @date 2022-02-08 14:43
 *
 * 93. 复原 IP 地址
 * 有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
 *
 * 例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是 无效 IP 地址。
 * 给定一个只包含数字的字符串 s ，用以表示一个 IP 地址，返回所有可能的有效 IP 地址，这些地址可以通过在 s 中插入 '.' 来形成。你不能重新排序或删除 s 中的任何数字。你可以按 任何 顺序返回答案。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "25525511135"
 * 输出：["255.255.11.135","255.255.111.35"]
 * 示例 2：
 *
 * 输入：s = "0000"
 * 输出：["0.0.0.0"]
 * 示例 3：
 *
 * 输入：s = "1111"
 * 输出：["1.1.1.1"]
 * 示例 4：
 *
 * 输入：s = "010010"
 * 输出：["0.10.0.10","0.100.1.0"]
 * 示例 5：
 *
 * 输入：s = "101023"
 * 输出：["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
 *
 *
 * 提示：
 *
 * 0 <= s.length <= 20
 * s 仅由数字组成
 *
 * https://leetcode-cn.com/problems/restore-ip-addresses/
 *
 * 题解：https://leetcode-cn.com/problems/restore-ip-addresses/solution/hui-su-suan-fa-hua-tu-fen-xi-jian-zhi-tiao-jian-by/
 */
public class RestoreIpAddresses {
    public static List<String> restoreIpAddresses(String s) {
        int len = s.length();
        List<String> res = new ArrayList<>();

        // 如果当前字符长度大于12或者小于4都不满足
        if (len > 12 || len < 4) {
            return res;
        }

        Deque<String> path = new ArrayDeque<>();
        dfs(s, len, 0, 4, path, res);

        return res;
    }

    /**
     *
     * @param s
     * @param len
     * @param begin
     * @param residue ip段数
     * @param path
     * @param res
     */
    private static void dfs(String s, int len, int begin, int residue, Deque<String> path, List<String> res) {
        // 如果字符串已经遍历到最后了，并且已经切分为4段了
        // 就把当前路径上的元素加入到返回的结果集中
        if (begin == len) {
            if (residue == 0) {
                res.add(String.join(".", path));
            }

            return;
        }

        for (int i = begin; i < begin + 3; i++) {
            // 如果超出字符串的长度，就直接退出
            // begin，每次选择都是从之前选择的元素的下一个元素开始
            if (i >= len) {
                break;
            }

            // 剩余字符大于ip最多能容纳的个数，剪枝
            if (residue * 3 < len - i) {
                continue;
            }

            // 判断当前截取字符是否是小于0或者大于255
            // 这里的begin和i，代表的是，这时候截取了几个字符
            // begin从哪里开始，i到哪里结束
            if (judgeIpSegment(s, begin, i)) {
                // 保留当前截取字符
                String currentIpSegment = s.substring(begin, i + 1);
                // 将当前路径上的元素加入到路径队列中
                path.addLast(currentIpSegment);
                // 递归下一层
                dfs(s, len, i + 1, residue - 1, path, res);
                path.removeLast();
            }
        }
    }

    private static boolean judgeIpSegment(String s, int left, int right) {
        // 定义一个表示整个字符的长度
        int len = right - left + 1;
        // 如果截取的大于等于2的字符的开头为0，就直接false
        if (len > 1 && s.charAt(left) == '0') {
            return false;
        }

        int res = 0;
        // 拼接字符
        while (left <= right) {
            // res*10 是为了将先加的字符默认比后面的字符大10倍，也就是位数是从小到大
            res = res * 10 + s.charAt(left) - '0';
            left++;
        }

        return res >= 0 && res <= 255;
    }

    public static void main(String[] args) {
        String s = "25525511135";
        System.out.println(restoreIpAddresses(s));
    }
}
