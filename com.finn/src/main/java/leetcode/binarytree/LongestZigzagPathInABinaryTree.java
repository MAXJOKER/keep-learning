package leetcode.binarytree;

/**
 * @author maxjoker
 * @date 2022-06-04 13:19
 *
 * https://leetcode.cn/problems/longest-zigzag-path-in-a-binary-tree/
 *
 * 1372. 二叉树中的最长交错路径
 * 给你一棵以 root 为根的二叉树，二叉树中的交错路径定义如下：
 *
 * 选择二叉树中 任意 节点和一个方向（左或者右）。
 * 如果前进方向为右，那么移动到当前节点的的右子节点，否则移动到它的左子节点。
 * 改变前进方向：左变右或者右变左。
 * 重复第二步和第三步，直到你在树中无法继续移动。
 * 交错路径的长度定义为：访问过的节点数目 - 1（单个节点的路径长度为 0 ）。
 *
 * 请你返回给定树中最长 交错路径 的长度。
 *
 *
 *
 * 示例 1：
 *
 * https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2020/03/07/sample_1_1702.png
 *
 * 输入：root = [1,null,1,1,1,null,null,1,1,null,1,null,null,null,1,null,1]
 * 输出：3
 * 解释：蓝色节点为树中最长交错路径（右 -> 左 -> 右）。
 * 示例 2：
 *
 * https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2020/03/07/sample_2_1702.png
 *
 * 输入：root = [1,1,1,null,1,null,null,1,1,null,1]
 * 输出：4
 * 解释：蓝色节点为树中最长交错路径（左 -> 右 -> 左 -> 右）。
 * 示例 3：
 *
 * 输入：root = [1]
 * 输出：0
 *
 *
 * 提示：
 *
 * 每棵树最多有 50000 个节点。
 * 每个节点的值在 [1, 100] 之间。
 *
 */
public class LongestZigzagPathInABinaryTree {
    private static int res;

    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     * @param root
     * @return
     */
    public static int solution(TreeNode root) {
        res = 0;
        dfs(root);
        return res;
    }

    private static int[] dfs(TreeNode node) {
        if (node == null) {
            return new int[2];
        }

        // dp[0]：从孩子到父亲的方向为左
        // dp[1]：从孩子到父亲的方向为右
        int[] dp = new int[2];

        int[] left = dfs(node.left);
        int[] right = dfs(node.right);

        // 当前节点下一步向左走带来的最大收益等于左子节点向右走的最大收益+1
        if (node.left != null) {
            dp[0] = left[1] + 1;
        }

        // 当前节点下一步向右走带来的最大收益等于右子节点向左走的最大收益+1
        if (node.right != null) {
            dp[1] = right[0] + 1;
        }

        res = Math.max(res, Math.max(dp[0], dp[1]));

        return dp;
    }
}
