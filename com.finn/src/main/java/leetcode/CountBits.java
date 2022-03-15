package leetcode;

/**
 * @author maxjoker
 * @date 2022-03-15 21:26
 *
 * https://leetcode-cn.com/problems/counting-bits/
 *
 * 338. 比特位计数
 * 给你一个整数 n ，对于 0 <= i <= n 中的每个 i ，计算其二进制表示中 1 的个数 ，返回一个长度为 n + 1 的数组 ans 作为答案。
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 2
 * 输出：[0,1,1]
 * 解释：
 * 0 --> 0
 * 1 --> 1
 * 2 --> 10
 * 示例 2：
 *
 * 输入：n = 5
 * 输出：[0,1,1,2,1,2]
 * 解释：
 * 0 --> 0
 * 1 --> 1
 * 2 --> 10
 * 3 --> 11
 * 4 --> 100
 * 5 --> 101
 *
 *
 * 提示：
 *
 * 0 <= n <= 105
 *
 *
 * 进阶：
 *
 * 很容易就能实现时间复杂度为 O(n log n) 的解决方案，你可以在线性时间复杂度 O(n) 内用一趟扫描解决此问题吗？
 * 你能不使用任何内置函数解决此问题吗？（如，C++ 中的 __builtin_popcount ）
 *
 */
public class CountBits {
    /**
     * 时间复杂度：O(nlogn)
     * 空间复杂度：O(1)
     * @param n
     * @return
     */
    public static int[] countBits(int n) {
        int[] result = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            if (i <= 1) {
                result[i] = i;
                continue;
            }

            result[i] = countBit2(i);
        }

        return result;
    }

    private static int countBit(int i) {
        int count = 0;
        while (i != 0) {
            i = i & (i - 1);
            count++;
        }

        return count;
    }

    private static int countBit2(int i) {
        int count = 0;
        while (i != 0) {
            count += i & 1;
            i = i >> 1;
        }

        return count;
    }

    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     * @param n
     * @return
     */
    public static int[] countBitsByDp(int n) {
        int[] result = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            result[i] = result[i >> 1] + (i & 1);
        }

        return result;
    }

    public static void main(String[] args) {
        int[] result = countBitsByDp(5);
        for (int i : result) {
            System.out.println(i);
        }
    }
}
