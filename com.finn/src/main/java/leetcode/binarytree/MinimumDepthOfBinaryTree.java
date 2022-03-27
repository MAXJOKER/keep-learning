package leetcode.binarytree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author maxjoker
 * @date 2022-03-25 21:35
 *
 * https://leetcode-cn.com/problems/minimum-depth-of-binary-tree/
 *
 * 111. 二叉树的最小深度
 * 给定一个二叉树，找出其最小深度。
 *
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 *
 * 说明：叶子节点是指没有子节点的节点。
 *
 *
 *
 * 示例 1：
 * https://assets.leetcode.com/uploads/2020/10/12/ex_depth.jpg
 *
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：2
 * 示例 2：
 *
 * 输入：root = [2,null,3,null,4,null,5,null,6]
 * 输出：5
 *
 *
 * 提示：
 *
 * 树中节点数的范围在 [0, 105] 内
 * -1000 <= Node.val <= 1000
 *
 */
public class MinimumDepthOfBinaryTree {
    /**
     * 广度优先
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     * @param root
     * @return
     */
    public int minDepthByBfs(TreeNode1 root) {
        if (root == null) {
            return 0;
        }

        if (root.left == null && root.right == null) {
            return 1;
        }

        Queue<TreeNode1> queue = new LinkedList<TreeNode1>();
        int level = 1;
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                TreeNode1 node = queue.poll();
                if (node == null || (node.left == null && node.right == null)) {
                    return level;
                }

                if (node.left != null) {
                    queue.offer(node.left);
                }

                if (node.right != null) {
                    queue.offer(node.right);
                }
            }

            level++;
        }

        return level;
    }

    /**
     * 深度优先
     * 时间复杂度：O(n)
     * 空间复杂度：O(height) 空间复杂度主要取决于递归时栈空间的开销，最坏情况下，树呈现链状，空间复杂度为 O(N)。
     * 平均情况下树的高度与节点数的对数正相关，空间复杂度为 O(logN)。
     * @param root
     * @return
     */
    public int minDepthByDfs(TreeNode1 root) {
        if (root == null) {
            return 0;
        }

        if (root.left == null && root.right == null) {
            return 1;
        }

        // 状态转移方程
        //              / dp[右孩子] + 1 ，左孩子为null
        // dp[非叶子] =    dp[左孩子] + 1 ，右孩子为null
        //              \ min(dp[左孩子], dp[右孩子]) + 1 ，左右孩子都不为null

        if (root.left == null) {
           return minDepthByDfs(root.right) + 1;
        }

        if (root.right == null) {
            return minDepthByDfs(root.left) + 1;
        }

        int left = minDepthByDfs(root.left);
        int right = minDepthByDfs(root.right);

        return right > left ? right + 1 : left + 1;
    }
}
