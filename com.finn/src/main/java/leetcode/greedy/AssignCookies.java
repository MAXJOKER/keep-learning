package leetcode.greedy;

import java.util.Arrays;

/**
 * @author maxjoker
 * @date 2022-06-20 20:13
 *
 * https://leetcode.cn/problems/assign-cookies/
 *
 * 455. 分发饼干
 * 假设你是一位很棒的家长，想要给你的孩子们一些小饼干。但是，每个孩子最多只能给一块饼干。
 *
 * 对每个孩子 i，都有一个胃口值 g[i]，这是能让孩子们满足胃口的饼干的最小尺寸；并且每块饼干 j，都有一个尺寸 s[j] 。
 * 如果 s[j] >= g[i]，我们可以将这个饼干 j 分配给孩子 i ，这个孩子会得到满足。你的目标是尽可能满足越多数量的孩子，
 * 并输出这个最大数值。
 *
 *
 * 示例 1:
 *
 * 输入: g = [1,2,3], s = [1,1]
 * 输出: 1
 * 解释:
 * 你有三个孩子和两块小饼干，3个孩子的胃口值分别是：1,2,3。
 * 虽然你有两块小饼干，由于他们的尺寸都是1，你只能让胃口值是1的孩子满足。
 * 所以你应该输出1。
 * 示例 2:
 *
 * 输入: g = [1,2], s = [1,2,3]
 * 输出: 2
 * 解释:
 * 你有两个孩子和三块小饼干，2个孩子的胃口值分别是1,2。
 * 你拥有的饼干数量和尺寸都足以让所有孩子满足。
 * 所以你应该输出2.
 *
 *
 * 提示：
 *
 * 1 <= g.length <= 3 * 10^4
 * 0 <= s.length <= 3 * 10^4
 * 1 <= g[i], s[j] <= 2^31 - 1
 *
 */
public class AssignCookies {
    /**
     * 暴力解法 也算是 排序 + 贪心算法 吧
     * 时间复杂度：O(logGLen * GLen + logSLen * SLen) // 排序 + 循环 开销
     * 空间复杂度：O(logGLen + logSLen) // 排序开销
     * @param g
     * @param s
     * @return
     */
    public static int findContentChildren(int[] g, int[] s) {
        int sLen = s.length;
        int gLen = g.length;
        if (sLen < 1) {
            return 0;
        }

        Arrays.sort(g);
        Arrays.sort(s);

        int count = 0;
        for (int i = gLen - 1; i >= 0; i--) {
            for (int j = sLen - 1; j >= 0; j--) {
                // 经过上面的Arrays.sort() 如果不满足当前孩子的胃口的话，说明再往前也不会有满足的了，直接break掉
                if (g[i] > s[j]) {
                    break;
                }

                count++;
                sLen--;
                break;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        int[] g = new int[]{1, 2, 3};
        int[] s = new int[]{1, 1};

        System.out.println(findContentChildren(g, s));
    }
}
