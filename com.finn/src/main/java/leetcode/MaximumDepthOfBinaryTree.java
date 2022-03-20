package leetcode;

/**
 * @author maxjoker
 * @date 2022-03-20 23:10
 *
 * https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/
 *
 * 104. 二叉树的最大深度
 * 给定一个二叉树，找出其最大深度。
 *
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例：
 * 给定二叉树 [3,9,20,null,null,15,7]，
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回它的最大深度 3 。
 *
 */
public class MaximumDepthOfBinaryTree {
    /**
     * 时间复杂度：O(n)，每个结点都被遍历了一次
     * 空间复杂度：O(height)，height为二叉树高度，栈的深度
     * @param root
     * @return
     */
    public static int maxDepth(TreeNode3 root) {
        if (root == null) {
            return 0;
        }

        return getTreeDepth(root);
    }

    private static int getTreeDepth(TreeNode3 root) {
        if (root == null) {
            return 0;
        }

        int left = getTreeDepth(root.left);
        int right = getTreeDepth(root.right);

        return right > left ? right + 1 : left + 1;
    }

    public static void main(String[] args) {
        Trees3 obj = new Trees3();
        obj.addNode(1);
        obj.addNode(2);
        obj.addNode(3);
        obj.addNode(-1);

        System.out.println(maxDepth(obj.root));
    }
}

class TreeNode3 {
    int val;
    TreeNode3 left;
    TreeNode3 right;

    public TreeNode3() {

    }

    public TreeNode3(int val) {
        this.val = val;
    }

    public TreeNode3(int val, TreeNode3 left, TreeNode3 right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Trees3 {
    public TreeNode3 root;

    public void addNode(int val) {
        root =   addNode(val, root);
    }

    public TreeNode3 addNode(int val, TreeNode3 current) {
        if (current == null) {
            return new TreeNode3(val);
        }

        if (val > current.val) {
            current.right = addNode(val, current.right);
        } else if (val < current.val) {
            current.left = addNode(val, current.left);
        }

        return current;
    }
}
