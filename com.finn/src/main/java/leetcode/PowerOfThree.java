package leetcode;

/**
 * 326. 3 的幂
 * 给定一个整数，写一个函数来判断它是否是 3 的幂次方。如果是，返回 true ；否则，返回 false 。
 *
 * 整数 n 是 3 的幂次方需满足：存在整数 x 使得 n == 3x
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 27
 * 输出：true
 * 示例 2：
 *
 * 输入：n = 0
 * 输出：false
 * 示例 3：
 *
 * 输入：n = 9
 * 输出：true
 * 示例 4：
 *
 * 输入：n = 45
 * 输出：false
 *
 * https://leetcode-cn.com/problems/power-of-three/
 */
public class PowerOfThree {
    /**
     * 暴力除法
     * 时间复杂度：O(log n)
     * 空间复杂度：O(1)
     * @param n
     * @return
     */
    public static boolean isPowerOfThree(int n) {
        while (n != 0 && n % 3 == 0) {
            n /= 3;
        }

        return n == 1;
    }

    /**
     * 判断是否为最大 33 的幂的约数
     * 我们还可以使用一种较为取巧的做法。
     *
     * 在题目给定的 3232 位有符号整数的范围内，最大的 3 的幂为 3^{19} = 11622614673
     *  我们只需要判断 nn 是否是 3^{19}的约数即可。
     *
     * 与方法一不同的是，这里需要特殊判断 nn 是负数或 00 的情况。
     *
     * @param n
     * @return
     */
    public static boolean isPowerOfThree2(int n) {
        return n > 0 && 1162261467 % n == 0;
    }

    public static void main(String[] args) {
        System.out.println(isPowerOfThree2(27));
    }
}
