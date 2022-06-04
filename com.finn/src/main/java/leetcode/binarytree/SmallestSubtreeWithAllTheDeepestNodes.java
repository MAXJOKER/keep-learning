package leetcode.binarytree;

/**
 * @author maxjoker
 * @date 2022-06-04 23:20
 *
 * 865. 具有所有最深节点的最小子树
 * 给定一个根为 root 的二叉树，每个节点的深度是 该节点到根的最短距离 。
 *
 * 返回包含原始树中所有 最深节点 的 最小子树 。
 *
 * 如果一个节点在 整个树 的任意节点之间具有最大的深度，则该节点是 最深的 。
 *
 * 一个节点的 子树 是该节点加上它的所有后代的集合。
 *
 *
 *
 * 示例 1：
 *
 * https://s3-lc-upload.s3.amazonaws.com/uploads/2018/07/01/sketch1.png
 *
 * 输入：root = [3,5,1,6,2,0,8,null,null,7,4]
 * 输出：[2,7,4]
 * 解释：
 * 我们返回值为 2 的节点，在图中用黄色标记。
 * 在图中用蓝色标记的是树的最深的节点。
 * 注意，节点 5、3 和 2 包含树中最深的节点，但节点 2 的子树最小，因此我们返回它。
 * 示例 2：
 *
 * 输入：root = [1]
 * 输出：[1]
 * 解释：根节点是树中最深的节点。
 * 示例 3：
 *
 * 输入：root = [0,1,3,null,2]
 * 输出：[2]
 * 解释：树中最深的节点为 2 ，有效子树为节点 2、1 和 0 的子树，但节点 2 的子树最小。
 *
 *
 * 提示：
 *
 * 树中节点的数量在 [1, 500] 范围内。
 * 0 <= Node.val <= 500
 * 每个节点的值都是 独一无二 的。
 *
 * 这道题题目描述十分奇怪。。。
 *
 * 其实就是 最深叶子节点的最近公共祖先，先找到最深的叶子结点，然后找到他们的parent
 * 题目本质上是让我们求那些「最深」的叶子节点的最近公共祖先
 * 你想想，一个节点需要知道哪些信息，才能确定自己是最深叶子节点的最近公共祖先？
 * 1. 它需要知道自己的左右子树的最大深度
 * 2. 如果左右子树一样深，那么当前节点就是最近公共祖先；
 * 3. 如果左右子树不一样深，那么最深叶子节点的最近公共祖先肯定在左右子树上。
 *
 *
 */
public class SmallestSubtreeWithAllTheDeepestNodes {
    private static TreeNode res = null;
    private static int maxLevel = Integer.MIN_VALUE;

    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     * @param root
     * @return
     */
    public static TreeNode solution(TreeNode root) {
        dfs(root, 1);
        return res;
    }

    private static int dfs(TreeNode node, int level) {
        if (node == null) {
            return level - 1;
        }

        // 左子树最大深度
        int left = dfs(node.left, level + 1);
        // 右子树最大深度
        int right = dfs(node.right, level + 1);

        int currentDeep = Math.max(left, right);
        maxLevel = Math.max(maxLevel, currentDeep);

        if (left == right && left == maxLevel) {
            res = node;
        }

        return currentDeep;
    }

    // 思路：从每个树开始，获得当前节点的左右子树的最大深度
    // 深度相同，说明最深的节点在这个节点两边，那这个节点就是结果
    // 如果深度不相同，则去深度大的子树继续判断，最终就能得到结果

    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     * @param root
     * @return
     */
    public static TreeNode solution2(TreeNode root) {
        if (root == null) {
            return root;
        }

        // 获取当前节点的左右子树的最大深度
        int left = getMaxDepth(root.left);
        int right = getMaxDepth(root.right);

        // 如果两边最大深度相同，则这个节点就是结果
        if (left == right) {
            return root;
        }

        // 不相等，那就去深度大的子树那边继续找
        if (left > right) {
            return solution2(root.left);
        }

        return solution2(root.right);
    }

    private static int getMaxDepth(TreeNode node) {
        if (node == null) {
            return 0;
        }

        return Math.max(getMaxDepth(node.left), getMaxDepth(node.right)) + 1;
    }
}
