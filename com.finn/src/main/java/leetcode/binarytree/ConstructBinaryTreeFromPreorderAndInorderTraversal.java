package leetcode.binarytree;

/**
 * @author maxjoker
 * @date 2022-03-27 21:58
 *
 * https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 *
 * 105. 从前序与中序遍历序列构造二叉树
 *
 * 给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历， inorder 是同一棵树的中序遍历，请构造二叉树并返回其根节点。
 *
 *
 *
 * 示例 1:
 *
 * https://assets.leetcode.com/uploads/2021/02/19/tree.jpg
 *
 *
 * 输入: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
 * 输出: [3,9,20,null,null,15,7]
 * 示例 2:
 *
 * 输入: preorder = [-1], inorder = [-1]
 * 输出: [-1]
 *
 *
 * 提示:
 *
 * 1 <= preorder.length <= 3000
 * inorder.length == preorder.length
 * -3000 <= preorder[i], inorder[i] <= 3000
 * preorder 和 inorder 均 无重复 元素
 * inorder 均出现在 preorder
 * preorder 保证 为二叉树的前序遍历序列
 * inorder 保证 为二叉树的中序遍历序列
 *
 */
public class ConstructBinaryTreeFromPreorderAndInorderTraversal {

    /**
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(1)
     * @param preorder
     * @param inorder
     * @return
     */
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        int preLen = preorder.length;
        int inLen = inorder.length;

        if (preLen != inLen) {
            throw new RuntimeException("invalid input");
        }

        return buildTree(preorder, 0, preLen - 1, inorder, 0, inLen - 1);
    }

    /**
     *
     * @param preorder 二叉树前序遍历结果
     * @param preLeft 二叉树前序遍历结果的左边界
     * @param preRight 二叉树前序遍历结果的右边界
     * @param inorder 二叉树中序遍历结果
     * @param inLeft 二叉树中序遍历结果的左边界
     * @param inRight 二叉树中序遍历结果的右边界
     *
     *  1、前序遍历结果数组中 第一个元素是根节点
     *  2、通过 1 中的第一个元素，查找根节点在中序遍历结果数组中的位置，其左边为左子树，右边为右子树
     *  3、递归 1、2
     *
     *  题解：https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/solution/qian-xu-bian-li-python-dai-ma-java-dai-ma-by-liwei/
     *  （看题解里面的图比较清晰明了）
     *
     * @return
     */
    public static TreeNode buildTree(
            int[] preorder,
            int preLeft,
            int preRight,
            int[] inorder,
            int inLeft,
            int inRight) {
        // 递归终止条件
        if (preLeft > preRight || inLeft > inRight) {
            return null;
        }

        // 通过前序遍历数组结果找到root （也就是第一个元素）
        int pivot = preorder[preLeft];
        TreeNode root = new TreeNode(pivot);
        // 找出根节点在中序遍历中的位置
        int pivotIndex = inLeft;
        // 优化，这里可以把 inorder 存到全局hashmap中，直接get就可以
        // 优化后，时间复杂度为：O(n)，空间复杂度为：O(n)
        while (inorder[pivotIndex] != pivot) {
            pivotIndex++;
        }

        // pivotIndex - inLeft + preLeft, 其中 pivotIndex - inLeft 为 左子树的长度,
        // 加上 preLeft 后为左子树最右结点的位置
        root.left = buildTree(
                preorder,
                preLeft + 1,
                pivotIndex - inLeft + preLeft,
                inorder,
                inLeft,
                pivotIndex - 1
        );

        root.right = buildTree(
                preorder,
                pivotIndex - inLeft + preLeft + 1,
                preRight,
                inorder,
                pivotIndex + 1,
                inRight
        );

        return root;
    }

    public static void main(String[] args) {
        int[] n1 = new int[]{3, 9, 20, 15, 7};
        int[] n2 = new int[]{9, 3, 15, 20, 7};

        TreeNode root = buildTree(n1, n2);
    }
}
