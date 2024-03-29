package leetcode.dynamicprogramming;

/**
 * 70. 爬楼梯
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 *
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 *
 * 注意：给定 n 是一个正整数。
 *
 * 示例 1：
 *
 * 输入： 2
 * 输出： 2
 * 解释： 有两种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶
 * 2.  2 阶
 * 示例 2：
 *
 * 输入： 3
 * 输出： 3
 * 解释： 有三种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶 + 1 阶
 * 2.  1 阶 + 2 阶
 * 3.  2 阶 + 1 阶
 *
 * https://leetcode-cn.com/problems/climbing-stairs/
 */
public class ClimbingStairs {
    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     * @param n
     * @return
     */
    public static int climbStairs(int n) {
        int[] methods = new int[n + 1];

        if (n == 1) {
            return 1;
        }

        if (n == 2) {
            return 2;
        }

        methods[1] = 1;
        methods[2] = 2;
        for (int i = 3; i <= n; i++) {
            methods[i] = methods[i - 1] + methods[i - 2];
        }

        return methods[n];
    }

    /**
     * 递归实现
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     * 会出现超时
     * @return
     */
    public static int climbStairsByRecursion(int n) {
        if (n <= 2) {
            return n;
        }

        return climbStairsByRecursion(n - 1) + climbStairsByRecursion(n - 2);
    }

    public static void main(String[] args) {
        int n = 45;
        System.out.println(climbStairs(n));
        System.out.println(climbStairsByRecursion(n));
    }
}
