package leetcode.binarytree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author maxjoker
 * @date 2022-03-16 18:09
 *
 * https://leetcode-cn.com/problems/binary-tree-inorder-traversal/
 *
 * 94. 二叉树的中序遍历
 * 给定一个二叉树的根节点 root ，返回它的 中序 遍历。
 *
 * 输入：root = [1,null,2,3]
 * 输出：[1,3,2]
 *
 */
public class BinaryTreeInorderTraversal {
    /**
     * 时间复杂度：O(n)，其中 n 为二叉树节点的个数。
     * 空间复杂度：O(n)，空间复杂度取决于递归的栈深度
     * @param root
     * @return
     */
    public static List<Integer> inorderTraversal(TreeNode1 root) {
        if (root == null) {
            return null;
        }

        List<Integer> result = new ArrayList<>();

        inorderTraversal(root, result);

        return result;
    }

    /**
     * 中序遍历：左中右
     * @param root
     * @param result
     */
    public static void inorderTraversal(TreeNode1 root, List<Integer> result) {
        if (root == null) {
            return;
        }

        inorderTraversal(root.left, result);
        result.add(root.val);
        inorderTraversal(root.right, result);
    }

    /**
     * 迭代，使用栈模拟递归
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     * @param root
     * @return
     */
    public static List<Integer> inorderTraversalByCycle(TreeNode1 root) {
        if (root == null) {
            return new ArrayList<>();
        }

        List<Integer> result = new ArrayList<>();
        Deque<TreeNode1> stack = new LinkedList<TreeNode1>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }

            root = stack.pop();
            result.add(root.val);
            root = root.right;
        }

        return result;
    }

    /**
     * 莫里斯遍历
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * 题解：https://leetcode-cn.com/problems/binary-tree-inorder-traversal/solution/dong-hua-yan-shi-94-er-cha-shu-de-zhong-xu-bian-li/
     * (看动图）
     *
     * 将二叉树转换成链表，然后逐个输出
     *
     * @param root
     * @return
     */
    public static List<Integer> inorderTraversalByMorris(TreeNode1 root) {
        List<Integer> result = new ArrayList<>();
        TreeNode1 predecessor = null;

        while (root != null) {
            if (root.left != null) {
                predecessor = root.left;
                // predecessor 节点就是当前 root 节点向左走一步，然后一直向右走至无法走为止
                while (predecessor.right != null && predecessor.right != root) {
                    predecessor = predecessor.right;
                }

                // 让 predecessor 的右指针指向 root，继续遍历左子树
                if (predecessor.right == null) {
                    predecessor.right = root;
                    root = root.left;
                } else {
                    // 说明左子树已经访问完了，我们需要断开链接
                    result.add(root.val);
                    predecessor.right = null;
                    root = root.right;
                }
            } else {
                // 如果没有左孩子，则直接访问右孩子
                result.add(root.val);
                root = root.right;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Tree1 obj = new Tree1();
        obj.addTreeNode(6);
        obj.addTreeNode(2);
        obj.addTreeNode(1);
        obj.addTreeNode(3);
        obj.addTreeNode(7);
        obj.addTreeNode(5);
        obj.addTreeNode(8);

        List<Integer> result = inorderTraversalByMorris(obj.root);
        System.out.println(result.toString());
    }
}

class TreeNode1 {
    int val;
    TreeNode1 left;
    TreeNode1 right;

    TreeNode1() {

    }

    TreeNode1(int val) {
        this.val = val;
    }

    TreeNode1(int val, TreeNode1 left, TreeNode1 right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Tree1 {
    TreeNode1 root;

    public void addTreeNode(int val) {
        root = addTreeNode(root, val);
    }

    public TreeNode1 addTreeNode(TreeNode1 current, int val) {
        if (current == null) {
             return new TreeNode1(val);
        }

        if (val < current.val) {
            current.left = addTreeNode(current.left, val);
        } else if (val > current.val) {
            current.right = addTreeNode(current.right, val);
        } else {

        }

        return current;
    }
}
