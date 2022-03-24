package leetcode;

/**
 * @author maxjoker
 * @date 2022-03-24 21:15
 *
 * https://leetcode-cn.com/problems/invert-binary-tree/
 *
 * 226. 翻转二叉树
 * 给你一棵二叉树的根节点 root ，翻转这棵二叉树，并返回其根节点。
 *
 *
 *
 * 示例 1：
 *
 * https://assets.leetcode.com/uploads/2021/03/14/invert1-tree.jpg
 *
 * 输入：root = [4,2,7,1,3,6,9]
 * 输出：[4,7,2,9,6,3,1]
 * 示例 2：
 *
 * https://assets.leetcode.com/uploads/2021/03/14/invert2-tree.jpg
 *
 * 输入：root = [2,1,3]
 * 输出：[2,3,1]
 * 示例 3：
 *
 * 输入：root = []
 * 输出：[]
 *
 * 提示：
 *
 * 树中节点数目范围在 [0, 100] 内
 * -100 <= Node.val <= 100
 *
 */
public class InvertBinaryTree {
    /**
     * 递归
     * 时间复杂度：O(n)，遍历每一个结点
     * 空间复杂度：O(n)，取决于递归栈的深度，平均情况下，二叉树高度与结点个数为对数关系：O(logN)
     * 最坏情况下，树形成链状，空间复杂度为 O(n)
     * @param root
     * @return
     */
    public static TreeNode7 invertTree(TreeNode7 root) {
        if (root == null) {
            return null;
        }

        return invertTreeByRecursion(root);
    }

    private static TreeNode7 invertTreeByRecursion(TreeNode7 root) {
        if (root == null) {
            return null;
        }

        TreeNode7 newTree = new TreeNode7(root.val);
        newTree.left = invertTree(root.right);
        newTree.right = invertTree(root.left);

        return newTree;
    }

    public static void main(String[] args) {
        Trees7 obj = new Trees7();
        obj.addNode(1);
        obj.addNode(-1);
        obj.addNode(2);
        TreeNode7 result = invertTree(obj.root);
    }
}

class Trees7 {
    public TreeNode7 root;

    public void addNode(int val) {
        root = addNode(val, root);
    }

    public TreeNode7 addNode(int val, TreeNode7 current) {
        if (current == null) {
            return new TreeNode7(val);
        }

        if (val > current.val) {
            current.right = addNode(val, current.right);
        } else if (val < current.val) {
            current.left = addNode(val, current.left);
        }

        return current;
    }
}

class TreeNode7 {
    int val;
    TreeNode7 left;
    TreeNode7 right;

    public TreeNode7() {

    }

    public TreeNode7(int val) {
        this.val = val;
    }

    public TreeNode7(int val, TreeNode7 left, TreeNode7 right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
