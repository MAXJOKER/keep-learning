package leetcode.binarytree;

/**
 * @author maxjoker
 * @date 2022-05-31 21:18
 *
 * 树的操作
 *
 */
public class Trees {
    public TreeNode root;

    public void addNode(int val) {
        root = add(root, val);
    }

    private TreeNode add(TreeNode current, int val) {
        if (current == null) {
            return new TreeNode(val);
        }

        if (val > current.val) {
            current.right = add(current.right, val);
        } else {
            current.left = add(current.left, val);
        }

        return current;
    }
}
