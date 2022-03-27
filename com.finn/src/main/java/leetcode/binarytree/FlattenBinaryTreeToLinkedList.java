package leetcode.binarytree;

/**
 * @author maxjoker
 * @date 2022-03-27 23:32
 *
 * https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list/
 *
 * 114. 二叉树展开为链表
 * 给你二叉树的根结点 root ，请你将它展开为一个单链表：
 *
 * 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
 * 展开后的单链表应该与二叉树 先序遍历 顺序相同。
 *
 *
 * 示例 1：
 *
 * https://assets.leetcode.com/uploads/2021/01/14/flaten.jpg
 *
 *
 * 输入：root = [1,2,5,3,4,null,6]
 * 输出：[1,null,2,null,3,null,4,null,5,null,6]
 * 示例 2：
 *
 * 输入：root = []
 * 输出：[]
 * 示例 3：
 *
 * 输入：root = [0]
 * 输出：[0]
 *
 *
 * 提示：
 *
 * 树中结点数在范围 [0, 2000] 内
 * -100 <= Node.val <= 100
 *
 *
 * 进阶：你可以使用原地算法（O(1) 额外空间）展开这棵树吗？
 *
 */
public class FlattenBinaryTreeToLinkedList {
    /**
     * 1、将左子树插入到右子树的地方
     * 2、将原来的右子树接到左子树的最右边节点
     * 3、考虑新的右子树的根节点，一直重复上边的过程，直到新的右子树为 null
     *
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     *
     *
     * @param root
     */
    public static void flatten(TreeNode root) {
        while (root != null) {
            // 左子树为 null，直接考虑下一个节点
            if (root.left == null) {
                root = root.right;
            } else {
                // 找左子树最右边的节点
                TreeNode pre = root.left;
                while (pre.right != null) {
                    pre = pre.right;
                }

                // 将原来的右子树接到左子树的最右边节点
                pre.right = root.right;
                // 将左子树插入到右子树的地方
                root.right = root.left;
                // 左子树用完了，置空
                root.left = null;
                // 考虑下一个节点
                root = root.right;
            }
        }
    }

    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     * @param root
     */
    public static void flatten2(TreeNode root) {
        TreeNode curr = root;
        while (curr != null) {
            if (curr.left != null) {
                TreeNode next = curr.left;
                TreeNode predecessor = next;

                while (predecessor.right != null) {
                    predecessor = predecessor.right;
                }

                predecessor.right = curr.right;
                curr.left = null;
                curr.right = next;
            }

            curr = curr.right;
        }
    }
}
