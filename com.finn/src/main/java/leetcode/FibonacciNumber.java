package leetcode;

/**
 * @author maxjoker
 * @date 2022-03-02 17:33
 *
 * 509. 斐波那契数
 * 斐波那契数 （通常用 F(n) 表示）形成的序列称为 斐波那契数列 。该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。也就是：
 *
 * F(0) = 0，F(1) = 1
 * F(n) = F(n - 1) + F(n - 2)，其中 n > 1
 * 给定 n ，请计算 F(n) 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 2
 * 输出：1
 * 解释：F(2) = F(1) + F(0) = 1 + 0 = 1
 * 示例 2：
 *
 * 输入：n = 3
 * 输出：2
 * 解释：F(3) = F(2) + F(1) = 1 + 1 = 2
 * 示例 3：
 *
 * 输入：n = 4
 * 输出：3
 * 解释：F(4) = F(3) + F(2) = 2 + 1 = 3
 *
 *
 * 提示：
 *
 * 0 <= n <= 30
 *
 * https://leetcode-cn.com/problems/fibonacci-number/
 *
 * 剑指offer：https://leetcode-cn.com/problems/fei-bo-na-qi-shu-lie-lcof/
 *
 *
 */
public class FibonacciNumber {
    /**
     * 递归
     * 时间复杂度：O(logn)
     * 空间复杂度：O(1)
     *
     * @param n
     * @return
     */
    public static int fibByRecursion(int n) {
        if (n < 2) {
            return n;
        }

        return fibByRecursion(n - 1) + fibByRecursion(n - 2);
    }

    /**
     * 循环求解
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     * @param n
     * @return
     */
    public static int fibByLoop(int n) {
        if (n < 2) {
            return n;
        }

        int p = 0;
        int q = 1;
        int sum = 0;

        for (int i = 2; i <= n; i++) {
            sum = p + q;
            p = q;
            q = sum;
        }

        return sum;
    }

    /**
     * 矩阵快速幂
     *
     *
     *
     * 矩阵基础：https://www.cnblogs.com/cmmdc/p/6936196.html
     * 题解：https://leetcode-cn.com/problems/fibonacci-number/solution/fei-bo-na-qi-shu-by-leetcode-solution-o4ze/
     * 矩阵快速幂：https://blog.csdn.net/qq_40061421/article/details/82625338
     *
     * 时间复杂度：O(logn)
     * 空间复杂度：O(1)
     *
     * @param n
     * @return
     */
    public static int fibByMatrix(int n) {
        if (n < 2) {
            return n;
        }

        int[][] q = {{1, 1}, {1, 0}};
        int[][] res = pow(q, n - 1);
        return res[0][0];
    }

    private static int[][] pow(int[][] a, int n) {
        int[][] ret = {{1, 0}, {0, 1}};

        while (n > 0) {
            if ((n&1) == 1) {
                ret = multiply(ret, a);
            }

            n >>= 1;
            a = multiply(a, a);
        }

        return ret;
    }

    private static int[][] multiply(int[][] a, int[][] b) {
        int[][] c = new int[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                c[i][j] = a[i][0] * b[0][j] + a[i][1] * b[1][j];
            }
        }

        return c;
    }

    /**
     * 通项公式 （没太懂。。。）
     * @param n
     */
    public static int fibByFormula(int n) {
        double sqrt5 = Math.sqrt(5);
        double fibN = Math.pow((1 + sqrt5) / 2, n) - Math.pow((1 - sqrt5) / 2, n);
        return (int) Math.round(fibN / sqrt5);
    }

    public static void main(String[] args) {
        System.out.println(fibByRecursion(48));
        System.out.println(fibByLoop(48));
        System.out.println(fibByMatrix(4));
    }
}
