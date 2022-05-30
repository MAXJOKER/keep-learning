package leetcode.dynamicprogramming;

import leetcode.binarytree.TreeNode;

/**
 * @author maxjoker
 * @date 2022-05-30 23:50
 *
 * 337. 打家劫舍 III
 * 小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为 root 。
 *
 * 除了 root 之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果 两个直接相连的房子在同一天晚上被打劫 ，房屋将自动报警。
 *
 * 给定二叉树的 root 。返回 在不触动警报的情况下 ，小偷能够盗取的最高金额 。
 *
 *
 *
 * 示例 1:
 *
 *  https://assets.leetcode.com/uploads/2021/03/10/rob1-tree.jpg
 *
 *
 * 输入: root = [3,2,3,null,3,null,1]
 * 输出: 7
 * 解释: 小偷一晚能够盗取的最高金额 3 + 3 + 1 = 7
 * 示例 2:
 *
 * https://assets.leetcode.com/uploads/2021/03/10/rob2-tree.jpg
 *
 *
 * 输入: root = [3,4,5,1,3,null,1]
 * 输出: 9
 * 解释: 小偷一晚能够盗取的最高金额 4 + 5 = 9
 *
 *
 * 提示：
 *
 * 树的节点数在 [1, 10^4] 范围内
 * 0 <= Node.val <= 10^4
 *
 */
public class HouseRobber3 {
    /**
     * dp[node][j] ：这里 node 表示一个结点，以 node 为根结点的树，并且规定了 node 是否偷取能够获得的最大价值。
     *      j = 0 表示 node 结点不偷取；
     *      j = 1 表示 node 结点偷取。
     *
     * 根据当前结点偷或者不偷，就决定了需要从哪些子结点里的对应的状态转移过来。
     *      如果当前结点不偷，左右子结点偷或者不偷都行，选最大者；
     *      如果当前结点偷，左右子结点均不能偷。
     *
     * @param root
     * @return
     */
    public static int rob(TreeNode root) {
        int[] res = dfs(root);

        return Math.max(res[0], res[1]);
    }

    private static int[] dfs(TreeNode node) {
        if (node == null) {
            return new int[]{0, 0};
        }

        // 分类讨论的标准是：当前结点偷或者不偷
        // 由于需要后序遍历，所以先计算左右子结点，然后计算当前结点的状态值
        int[] left = dfs(node.left);
        int[] right = dfs(node.right);

        int[] dp = new int[2];

        // dp[0]：以当前 node 为根结点的子树能够偷取的最大价值，规定 node 结点不偷
        dp[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        // dp[1]：以当前 node 为根结点的子树能够偷取的最大价值，规定 node 结点偷
        dp[1] = node.val + left[0] + right[0];

        return dp;
    }
}
