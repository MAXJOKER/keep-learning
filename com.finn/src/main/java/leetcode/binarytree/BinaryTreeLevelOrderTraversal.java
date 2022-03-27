package leetcode.binarytree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author maxjoker
 * @date 2022-03-21 00:05
 *
 * https://leetcode-cn.com/problems/binary-tree-level-order-traversal/
 *
 * 102. 二叉树的层序遍历
 * 给你二叉树的根节点 root ，返回其节点值的 层序遍历 。 （即逐层地，从左到右访问所有节点）。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：[[3],[9,20],[15,7]]
 *
 */
public class BinaryTreeLevelOrderTraversal {
    /**
     * 利用队列，每次都把同一层的node塞进队列中，然后循环逐个读取，debug一下就明白了
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     * @param root
     * @return
     */
    public static List<List<Integer>> levelOrder(TreeNode4 root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Queue<TreeNode4> q = new LinkedList<TreeNode4>();
        q.add(root);

        while (!q.isEmpty()) {
            List<Integer> levelRes = new ArrayList<>();
            int currentLevelSize = q.size();
            for (int i = 0; i < currentLevelSize; i++) {
                TreeNode4 temp = q.poll();
                levelRes.add(temp.val);

                if (temp.left != null) {
                    q.offer(temp.left);
                }

                if (temp.right != null) {
                    q.offer(temp.right);
                }
            }

            res.add(levelRes);
        }

        return res;
    }

    /**
     * 深度优先
     *
     * 深度优先遍历，每次遍历带当前结点对应的层数，层数对应结果数组中的index，这样就能把同一层的结点保存到同一个list中
     *
     * 时间复杂度：O(n)
     * 空间复杂度：O(height) ，height为树的高度
     *
     * 题解：https://leetcode-cn.com/problems/binary-tree-level-order-traversal/solution/die-dai-di-gui-duo-tu-yan-shi-102er-cha-shu-de-cen/
     * @param root
     * @return
     */
    public static List<List<Integer>> levelOrderByDfs(TreeNode4 root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        dfs(1, root, res);
        return res;
    }

    private static void dfs(int index, TreeNode4 root, List<List<Integer>> res) {
        // 假设res是[ [1],[2,3] ]， index是3，就再插入一个空list放到res中
        if (res.size() < index) {
            res.add(new ArrayList<Integer>());
        }

        // 将当前节点的值加入到res中，index代表当前层，假设index是3，节点值是99
        // res是[ [1],[2,3] [4] ]，加入后res就变为 [ [1],[2,3] [4,99] ]
        res.get(index - 1).add(root.val);

        // 递归的处理左子树，右子树，同时将层数index+1
        if (root.left != null) {
            dfs(index + 1, root.left, res);
        }

        if (root.right != null) {
            dfs(index + 1, root.right, res);
        }
    }

    public static void main(String[] args) {
        Trees4 obj = new Trees4();
        obj.addNode(1);
        obj.addNode(-1);
        obj.addNode(0);
        obj.addNode(2);
        obj.addNode(3);
        obj.addNode(4);

        System.out.println(levelOrder(obj.root).toString());
    }
}

class TreeNode4 {
    int val;
    TreeNode4 left;
    TreeNode4 right;

    public TreeNode4() {

    }

    public TreeNode4(int val) {
        this.val = val;
    }

    public TreeNode4(int val, TreeNode4 left, TreeNode4 right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Trees4 {
    public TreeNode4 root;

    public void addNode(int val) {
        root = addNode(val, root);
    }

    private TreeNode4 addNode(int val, TreeNode4 current) {
        if (current == null) {
            return new TreeNode4(val);
        }

        if (val > current.val) {
            current.right = addNode(val, current.right);
        } else if (val < current.val) {
            current.left = addNode(val, current.left);
        }

        return current;
    }
}
