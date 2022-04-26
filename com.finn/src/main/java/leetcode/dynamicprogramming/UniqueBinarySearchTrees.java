package leetcode.dynamicprogramming;

/**
 * @author maxjoker
 * @date 2022-04-26 20:39
 *
 * https://leetcode-cn.com/problems/unique-binary-search-trees
 *
 * 96. 不同的二叉搜索树
 * 给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？返回满足题意的二叉搜索树的种数。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：n = 3
 * 输出：5
 * 示例 2：
 *
 * 输入：n = 1
 * 输出：1
 *
 *
 * 提示：
 *
 * 1 <= n <= 19
 *
 */
public class UniqueBinarySearchTrees {
    /**
     * 动态规划
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(n)
     *
     * 题解1：https://leetcode-cn.com/problems/unique-binary-search-trees/solution/bu-tong-de-er-cha-sou-suo-shu-by-leetcode-solution/
     * 题解2：https://leetcode-cn.com/problems/unique-binary-search-trees/solution/hua-jie-suan-fa-96-bu-tong-de-er-cha-sou-suo-shu-b/
     *
     * @param n
     * @return
     */
    public static int numTrees(int n) {
        int[] G = new int[n + 1];
        G[0] = 1;
        G[1] = 1;

        for (int i = 2; i <=n; i++) {
            for (int j = 1; j <= i; j++) {
                G[i] += G[j - 1] * G[i - j];
            }
        }

        return G[n];
    }

    /**
     * 卡特兰数
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * 题解：https://leetcode-cn.com/problems/unique-binary-search-trees/solution/bu-tong-de-er-cha-sou-suo-shu-by-leetcode-solution/
     *
     * @param n
     * @return
     */
    public static int numTreesByCatalan(int n) {
        long C = 1;
        for (int i = 0; i < n; i++) {
            C = C * 2 * (2 * i + 1) / (i + 2);
        }

        return (int) C;
    }

    public static void main(String[] args) {
        System.out.println(numTrees(3));
        System.out.println(numTreesByCatalan(3));
    }
}
