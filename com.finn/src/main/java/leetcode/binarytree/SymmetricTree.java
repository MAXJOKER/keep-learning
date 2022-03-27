package leetcode.binarytree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author maxjoker
 * @date 2022-03-17 21:28
 *
 * https://leetcode-cn.com/problems/symmetric-tree/
 *
 * 101. 对称二叉树
 * 给你一个二叉树的根节点 root ， 检查它是否轴对称。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：root = [1,2,2,3,4,4,3]
 * 输出：true
 * 示例 2：
 *
 *
 * 输入：root = [1,2,2,null,3,null,3]
 * 输出：false
 *
 *
 * 提示：
 *
 * 树中节点数目在范围 [1, 1000] 内
 * -100 <= Node.val <= 100
 *
 *
 * 进阶：你可以运用递归和迭代两种方法解决这个问题吗？
 *
 */
public class SymmetricTree {
    /**
     * 递归
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     * @param root
     * @return
     */
    public static boolean isSymmetricTree(TreeNode2 root) {
        if (root == null) {
            return true;
        }

        return judgeSymmetricTree(root.left, root.right);
    }

    private static boolean judgeSymmetricTree(TreeNode2 left, TreeNode2 right) {
        if (left == null) {
            return right == null;
        }

        if (right == null) {
            return false;
        }

        if (left.val != right.val) {
            return false;
        }

        return judgeSymmetricTree(left.left, right.right) && judgeSymmetricTree(left.right, right.left);
    }

    /**
     * 迭代
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     * @param root
     * @return
     */
    public static boolean isSymmetric(TreeNode2 root) {
        if (root == null) {
            return true;
        }

        return checkSymmetric(root, root);
    }

    private static boolean checkSymmetric(TreeNode2 root, TreeNode2 root1) {
        Queue<TreeNode2> queue = new LinkedList<TreeNode2>();
        queue.offer(root);
        queue.offer(root1);

        while (!queue.isEmpty()) {
            root = queue.poll();
            root1 = queue.poll();

            if (root == null && root1 == null) {
                continue;
            }

            if ((root == null || root1 == null) && (root.val != root1.val)) {
                return false;
            }

            queue.offer(root.left);
            queue.offer(root1.right);

            queue.offer(root.right);
            queue.offer(root1.left);
        }

        return true;
    }

    public static void main(String[] args) {

    }
}

class TreeNode2 {
    int val;
    TreeNode2 left;
    TreeNode2 right;
    TreeNode2() {}
    TreeNode2(int val) {
        this.val = val;
    }
    TreeNode2(int val, TreeNode2 left, TreeNode2 right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Tree2 {
    TreeNode2 root;

    public TreeNode2 addNode(int val) {
        return addNode(root, val);
    }

    /**
     * todo 实现一个对称二叉树
     * @param root
     * @param val
     * @return
     */
    private TreeNode2 addNode(TreeNode2 root, int val) {
        if (root == null) {
            return new TreeNode2(val);
        }

        if (val < root.val) {
            root.left = addNode(root.left, val);
        } else if (val > root.val) {
            root.right = addNode(root.right, val);
        }

        return root;
    }
}
