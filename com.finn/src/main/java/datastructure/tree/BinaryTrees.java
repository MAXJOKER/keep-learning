package datastructure.tree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author maxjoker
 * @date 2022-03-25 13:21
 *
 * 二叉树操作
 *
 */
public class BinaryTrees {
    public TreeNode root;

    /**
     * 插入结点
     * @param val
     */
    public void insertNode(int val) {
        root = insertNode(val, root);
    }

    public TreeNode insertNode(int val, TreeNode current) {
        if (current == null) {
            return new TreeNode(val);
        }

        if (val > current.val) {
            current.right = insertNode(val, current.right);
        } else if (val < current.val) {
            current.left = insertNode(val, current.left);
        }

        return current;
    }

    /**
     * 删除结点
     * @param val
     */
    public void deleteNode(int val) {
        root = deleteNode(val, root);
    }


    public TreeNode deleteNode(int val, TreeNode root) {
        if (root == null) {
            return null;
        }

        if (val > root.val) {
            deleteNode(val, root.right);
        } else if (val < root.val) {
            deleteNode(val, root.left);
        } else {
            // 相等，找到要删除的位置了

            // 情况1：结点为叶结点，直接置空即可
            if (root.left == null && root.right == null) {
                return null;
            } else if (root.left == null) {
                // 情况2：左节点为空，右结点替换删除结点
                return root.right;
            } else if (root.right == null) {
                // 情况3：右结点为空，左节点替换删除结点
                return root.left;
            } else {
                // 情况4：左右结点都不为空
                // 从右边找到最小结点代替删除结点
                TreeNode minNode = findMinNode(root.right);
                root.val = minNode.val;
                // 递归删除minNode，因为已经被用来代替删除结点了
                root.right = deleteNode(minNode.val, root.right);
                return root;
            }
        }

        return null;
    }

    /**
     * 查找最小结点
     * @param root
     * @return
     */
    public TreeNode findMinNode(TreeNode root) {
        return root.left == null ? root : findMinNode(root.left);
    }

    /**
     * 查找最大结点
     * @param root
     * @return
     */
    public TreeNode findMaxNode(TreeNode root) {
        return root.right == null ? root : findMaxNode(root.right);
    }


    /**
     * 搜索结点是否存在
     * @param val
     * @return
     */
    public Boolean search(int val) {
        return searchNode(val, root);
    }

    public Boolean searchNode(int val, TreeNode root) {
        if (root == null) {
            return false;
        }

        if (val > root.val) {
            searchNode(val, root.right);
        } else if (val < root.val) {
            searchNode(val, root.left);
        } else {
            return true;
        }

        return false;
    }

    /**
     * 前序遍历 中-左-右
     * @return
     */
    public List<Integer> preorderTraversal() {
        List<Integer> result = new ArrayList<>();
        return preorderTraversal(root, result);
    }

    public List<Integer> preorderTraversal(TreeNode root, List<Integer> result) {
        if (root == null) {
            return null;
        }

        result.add(root.val);
        preorderTraversal(root.left, result);
        preorderTraversal(root.right, result);

        return result;
    }

    /**
     * 中序遍历 左-中-右
     * @return
     */
    public List<Integer> inorderTraversal() {
        List<Integer> result = new ArrayList<>();
        return inorderTraversal(root, result);
    }

    public List<Integer> inorderTraversal(TreeNode root, List<Integer> result) {
        if (root == null) {
            return null;
        }

        inorderTraversal(root.left, result);
        result.add(root.val);
        inorderTraversal(root.right, result);

        return result;
    }

    /**
     * 后序遍历 左-右-中
     * @return
     */
    public List<Integer> postorderTraversal() {
        List<Integer> result = new ArrayList<>();
        return postorderTraversal(root, result);
    }

    public List<Integer> postorderTraversal(TreeNode root, List<Integer> result) {
        if (root == null) {
            return null;
        }

        postorderTraversal(root.left, result);
        postorderTraversal(root.right, result);
        result.add(root.val);

        return result;
    }

    /**
     * 层序遍历 一层一层遍历
     * @return
     */
    public List<List<Integer>> levelOrderTraversal() {
        List<List<Integer>> result = new ArrayList<>();
        return levelOrderTraversal(root, result, 1);
    }

    public List<List<Integer>> levelOrderTraversal(TreeNode root, List<List<Integer>> result, int depth) {
        if (result.size() < depth) {
            result.add(new ArrayList<>());
        }

        // 将当前节点的值加入到result中，depth代表当前层
        result.get(depth - 1) .add(root.val);

        // 递归的处理左子树，右子树，同时将层数index+1
        while (root.left != null) {
            levelOrderTraversal(root.left, result, depth + 1);
        }

        while (root.right != null) {
            levelOrderTraversal(root.right, result, depth + 1);
        }

        return result;
    }

    /**
     * 合并二叉树，对应位置的val相加
     * @param t1
     * @param t2
     * @return
     */
    public TreeNode mergeTwoTreeNodeByVal(TreeNode t1, TreeNode t2) {
        if (t1 == null) {
            return t2;
        }

        if (t2 == null) {
            return t1;
        }

        // 合并根结点
        t1.val += t2.val;
        // 递归合并左右子节点
        t1.left = mergeTwoTreeNodeByVal(t1.left, t2.left);
        t1.right = mergeTwoTreeNodeByVal(t1.right, t2.right);

        return t1;
    }

    /**
     * 翻转二叉树
     * @param root
     * @return
     */
    public TreeNode reverseTreeNode(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode newNode = new TreeNode(root.val);
        newNode.left = reverseTreeNode(root.right);
        newNode.right = reverseTreeNode(root.left);

        return newNode;
    }

    /**
     * 第k层结点个数
     * @return
     */
    public int kLevelNodeNums(int k) {
        return kLevelNodeNums(root, k);
    }

    public int kLevelNodeNums(TreeNode root, int k) {
        if (root == null || k < 1) {
            return 0;
        }

        if (k == 1) {
            return 1;
        }

        // 通过 k - 1层 去 递归找 k 层的节点数，k - 1层为k层的根节点
        int left = kLevelNodeNums(root.left, k - 1);
        int right = kLevelNodeNums(root.right, k - 1);

        return left + right;
    }

    /**
     * 二叉树深度
     * @return
     */
    public int depth() {
        return depth(root);
    }

    public int depth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = depth(root.left);
        int right = depth(root.right);

        return right > left ? right + 1 : left + 1;
    }

    /**
     * 二叉树最小深度
     * @return
     */
    public int minDepth() {
        return minDepth(root);
    }

    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        if (root.left == null && root.right == null) {
            return 1;
        }

        // 状态转移方程
        //              / dp[右孩子] + 1 ，左孩子为null
        // dp[非叶子] =    dp[左孩子] + 1 ，右孩子为null
        //              \ min(dp[左孩子], dp[右孩子]) + 1 ，左右孩子都不为null

        if (root.left == null) {
            return minDepth(root.right) + 1;
        }

        if (root.right == null) {
            return minDepth(root.left) + 1;
        }

        int left = minDepth(root.left);
        int right = minDepth(root.right);

        return right < left ? right + 1 : left + 1;
    }

    /**
     * 广度优先
     * @param root
     * @return
     */
    public int minDepthByCycle(TreeNode root) {
        if (root == null) {
            return 0;
        }

        if (root.left == null && root.right == null) {
            return 1;
        }

        Deque<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        int minDepth = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node == null || (node.left == null && node.right == null)) {
                    return minDepth;
                }

                if (node.left != null) {
                    queue.offer(node.left);
                }

                if (node.right != null) {
                    queue.offer(node.right);
                }
            }

            minDepth++;
        }

        return minDepth;
    }

    /**
     * 二叉树节点数
     * @return
     */
    public int treeNodeNums() {
        return treeNodeNums(root);
    }

    public int treeNodeNums(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return treeNodeNums(root.left) + treeNodeNums(root.right) + 1;
    }

    /**
     * 二叉树叶子结点数
     * @return
     */
    public int leafNodeNums() {
        return leafNodeNums(root);
    }

    public int leafNodeNums(TreeNode root) {
        if (root == null) {
            return 0;
        }

        if (root.left == null && root.right == null) {
            return 1;
        }

        return leafNodeNums(root.left) + leafNodeNums(root.right);
    }

    /**
     * 是否为对称树
     * @return
     */
    public boolean isSymmetricTree() {
        if (root == null) {
            return true;
        }
        return isSymmetricTree(root.left, root.right);
    }

    public boolean isSymmetricTree(TreeNode left, TreeNode right) {
       if (left == null) {
           return right == null;
       }

       if (right == null) {
           return false;
       }

       if (left.val != right.val) {
           return false;
       }

       return isSymmetricTree(left.left, right.right) && isSymmetricTree(left.right, right.left);
    }
}
