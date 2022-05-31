package leetcode.binarytree;

/**
 * @author maxjoker
 * @date 2022-05-31 21:16
 *
 * 687. 最长同值路径
 * 给定一个二叉树的 root ，返回 最长的路径的长度 ，这个路径中的 每个节点具有相同值 。 这条路径可以经过也可以不经过根节点。
 *
 * 两个节点之间的路径长度 由它们之间的边数表示。
 *
 *
 *
 * 示例 1:
 *
 * https://assets.leetcode.com/uploads/2020/10/13/ex1.jpg
 *
 *
 * 输入：root = [5,4,5,1,1,5]
 * 输出：2
 * 示例 2:
 *
 *
 *
 * 输入：root = [1,4,5,4,4,5]
 * 输出：2
 *
 * https://assets.leetcode.com/uploads/2020/10/13/ex2.jpg
 * 提示:
 *
 * 树的节点数的范围是 [0, 10^4]
 * -1000 <= Node.val <= 1000
 * 树的深度将不超过 1000
 *
 */
public class LongestUnivaluePath {
    private static int res;

    /**
     * 时间复杂度：O(n) 遍历了每一个结点
     * 空间复杂度：O(height) 树的高度
     * @param root
     * @return
     */
    public static int solution(TreeNode root) {
        res = 0;
        dfs(root);
        return res;
    }

    private static int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int left = dfs(node.left);
        int right = dfs(node.right);

        int leftPath = 0;
        // 相等 边数+1
        if (node.left != null && node.left.val == node.val) {
            leftPath += left + 1;
        }

        int rightPath = 0;
        // 相等 边数+1
        if (node.right != null && node.right.val == node.val) {
            rightPath += right + 1;
        }

        // 注意：两个节点之间的路径长度由它们之间的边数表示
        res = Math.max(res, leftPath + rightPath);

        return Math.max(leftPath, rightPath);
    }

    public static void main(String[] args) {
        Trees trees = new Trees();
        trees.addNode(4);
        trees.addNode(5);
        trees.addNode(4);
        trees.addNode(4);
        trees.addNode(5);

        System.out.println(solution(trees.root));
    }
}
