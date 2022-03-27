package leetcode.binarytree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author maxjoker
 * @date 2022-03-22 10:05
 *
 * 时不我与啊，加油，不要再浪费时间了
 *
 * https://leetcode-cn.com/problems/merge-two-binary-trees/
 *
 * 617. 合并二叉树
 * 给你两棵二叉树： root1 和 root2 。
 *
 * 想象一下，当你将其中一棵覆盖到另一棵之上时，两棵树上的一些节点将会重叠（而另一些不会）。你需要将这两棵树合并成一棵新二叉树。合并的规则是：如果两个节点重叠，那么将这两个节点的值相加作为合并后节点的新值；否则，不为 null 的节点将直接作为新二叉树的节点。
 *
 * 返回合并后的二叉树。
 *
 * 注意: 合并过程必须从两个树的根节点开始。
 *
 *
 *
 * 示例 1：
 * https://assets.leetcode.com/uploads/2021/02/05/merge.jpg
 *
 * 输入：root1 = [1,3,2,5], root2 = [2,1,3,null,4,null,7]
 * 输出：[3,4,5,5,4,null,7]
 * 示例 2：
 *
 * 输入：root1 = [1], root2 = [1,2]
 * 输出：[2,2]
 *
 *
 * 提示：
 *
 * 两棵树中的节点数目在范围 [0, 2000] 内
 * -10^4 <= Node.val <= 10^4
 */
public class MergeTwoBinaryTrees {

    /**
     * 前序遍历 中左右
     * 时间复杂度：O(min(m, n))，m、n为两个二叉树结点个数
     * 空间复杂度：O(min(m, n)
     * @param node1
     * @param node2
     * @return
     */
    public static TreeNode5 merge(TreeNode5 node1, TreeNode5 node2) {
        if (node1 == null) {
            return node2;
        }

        if (node2 == null) {
            return node1;
        }

        // 合并根结点
        node1.val += node2.val;
        // 递归合并左右子节点
        node1.left = merge(node1.left, node2.left);
        node1.right = merge(node1.right, node2.right);

        return node1;
    }

    /**
     * 广度优先
     * 时间复杂度：O(min(m+n))
     * 空间复杂度：O(min(m+n))
     * @param node1
     * @param node2
     * @return
     */
    public static TreeNode5 mergeByCycle(TreeNode5 node1, TreeNode5 node2) {
        if (node1 == null) {
            return node2;
        }

        if (node2 == null) {
            return node1;
        }

        TreeNode5 merged = new TreeNode5(node1.val + node2.val);

        Queue<TreeNode5> q = new LinkedList<TreeNode5>();
        Queue<TreeNode5> temp1 = new LinkedList<TreeNode5>();
        Queue<TreeNode5> temp2 = new LinkedList<TreeNode5>();

        q.offer(merged);
        temp1.offer(node1);
        temp2.offer(node2);

        while (!temp1.isEmpty() && !temp2.isEmpty()) {
            TreeNode5 node = q.poll();
            TreeNode5 n1 = temp1.poll();
            TreeNode5 n2 = temp2.poll();

            TreeNode5 l1 = n1.left;
            TreeNode5 l2 = n2.left;
            TreeNode5 r1 = n1.right;
            TreeNode5 r2 = n2.right;

            if (l1 != null || l2 != null) {
                if (l1 != null && l2 != null) {
                    TreeNode5 left = new TreeNode5(l1.val + l2.val);
                    node.left = left;

                    q.offer(left);
                    temp1.offer(l1);
                    temp2.offer(l2);
                } else if (l1 != null) {
                    node.left = l1;
                } else if (l2 != null) {
                    node.left = l2;
                }
            }

            if (r1 != null || r2 != null) {
                if (r1 != null && r2 != null) {
                    TreeNode5 right = new TreeNode5(r1.val + r2.val);
                    node.right = right;

                    q.offer(right);
                    temp1.offer(r1);
                    temp2.offer(r2);
                } else if (r1 != null) {
                    node.right = r1;
                } else if (r2 != null) {
                    node.right = r1;
                }
            }
        }

        return merged;
    }

    public static void main(String[] args) {
        Trees5 obj = new Trees5();
        obj.addNode(6);
        obj.addNode(2);
        obj.addNode(10);
        TreeNode5 node1 = obj.root;

        Trees5 obj2 = new Trees5();
        obj2.addNode(3);
        obj2.addNode(2);
        obj2.addNode(5);
        obj2.addNode(1);
        TreeNode5 node2 = obj2.root;

        TreeNode5 res = mergeByCycle(node1, node2);
    }
}

class TreeNode5 {
    int val;
    TreeNode5 left;
    TreeNode5 right;

    public TreeNode5() {

    }

    public TreeNode5(int val) {
        this.val = val;
    }

    public TreeNode5(int val, TreeNode5 left, TreeNode5 right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Trees5 {
    public TreeNode5 root;

    public void addNode(int val) {
        root = addNode(val, root);
    }

    private TreeNode5 addNode(int val, TreeNode5 current) {
        if (current == null) {
            return new TreeNode5(val);
        }

        if (val > current.val) {
            current.right = addNode(val, current.right);
        } else if (val < current.val) {
            current.left = addNode(val, current.left);
        }

        return current;
    }
}
