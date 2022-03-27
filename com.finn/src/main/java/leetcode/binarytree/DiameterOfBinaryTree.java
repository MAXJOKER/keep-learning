package leetcode.binarytree;

/**
 * @author maxjoker
 * @date 2022-03-22 21:15
 *
 * https://leetcode-cn.com/problems/diameter-of-binary-tree/solution/er-cha-shu-de-zhi-jing-by-leetcode-solution/
 *
 * 543. 二叉树的直径
 * 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。
 *
 *
 *
 * 示例 :
 * 给定二叉树
 *
 *           1
 *          / \
 *         2   3
 *        / \
 *       4   5
 * 返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。
 *
 *
 *
 * 注意：两结点之间的路径长度是以它们之间边的数目表示。
 */
public class DiameterOfBinaryTree {
    public static int ans = 0;

    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     * @param root
     * @return
     */
    public static int solution(TreeNode6 root) {
        ans = 1;
        diameterOfBinaryTree(root);

        return ans - 1;
    }

    public static int diameterOfBinaryTree(TreeNode6 root) {
        if (root == null) {
            return 0;
        }

        int l = diameterOfBinaryTree(root.left);
        int r = diameterOfBinaryTree(root.right);
        ans = Math.max(ans, l + r + 1);

        return Math.max(l, r) + 1;
    }

    public static void main(String[] args) {
        Trees6 obj = new Trees6();
        obj.addNode(2);
        obj.addNode(1);
        obj.addNode(3);
        obj.addNode(4);
        obj.addNode(5);

        int res = diameterOfBinaryTree(obj.root);
        System.out.println(res);
    }
}

class TreeNode6 {
    int val;
    TreeNode6 left;
    TreeNode6 right;

    public TreeNode6() {

    }

    public TreeNode6(int val) {
        this.val = val;
    }

    public TreeNode6(int val, TreeNode6 left, TreeNode6 right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Trees6 {
    public TreeNode6 root;

    public void addNode(int val) {
        root = addNode(val, root);
    }

    public TreeNode6 addNode(int val, TreeNode6 current) {
        if (current == null) {
            return new TreeNode6(val);
        }

        if (val > current.val) {
            current.right = addNode(val, current.right);
        } else if (val < current.val) {
            current.left = addNode(val, current.left);
        }

        return current;
    }
}
