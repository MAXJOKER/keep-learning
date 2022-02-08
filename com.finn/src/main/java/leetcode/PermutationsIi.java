package leetcode;

import java.util.*;

/**
 * @date 2022-02-01 16:30
 * 47. 全排列 II
 * 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,1,2]
 * 输出：
 * [[1,1,2],
 *  [1,2,1],
 *  [2,1,1]]
 * 示例 2：
 *
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 8
 * -10 <= nums[i] <= 10
 *
 * https://leetcode-cn.com/problems/permutations-ii
 */
public class PermutationsIi {
    /**
     * 回溯法 + 深度优先 + 剪枝
     * 题解：https://leetcode-cn.com/problems/permutations-ii/solution/hui-su-suan-fa-python-dai-ma-java-dai-ma-by-liwe-2/
     * @param nums
     * @return
     */
    public static List<List<Integer>> permute(int[] nums) {
        int len = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        if (len == 0) {
            return res;
        }

        // 排序（升序或者降序都可以），排序是剪枝的前提
        Arrays.sort(nums);

        boolean[] used = new boolean[len];
        Deque<Integer> path = new ArrayDeque<>(len);

        dfs(nums, len, 0, path, used,res);

        return res;
    }

    private static void dfs(int[] nums, int len, int depth, Deque<Integer> path, boolean[] used, List<List<Integer>> res) {
        if (depth == len) {
            // 耗时高 570ms
           /* if (res.contains(new ArrayList<>(path))) {
                return;
            }*/
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < len; i++) {
            if (used[i]) {
                continue;
            }

            // 剪枝
            // 剪枝条件：i > 0 是为了保证 nums[i - 1] 有意义
            // 写 !used[i - 1] 是因为 nums[i - 1] 在深度优先遍历的过程中刚刚被撤销选择
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }

            used[i] = true;
            path.addLast(nums[i]);

            System.out.println("递归之前 => " + path + ", i = " + i);
            dfs(nums, len, depth + 1, path, used, res);

            // 回溯部分的代码，和 dfs 之前的代码是对称的
            used[i] = false;
            path.removeLast();
            System.out.println("递归之后 => " + path + ", i = " + i);
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, 3};
        List<List<Integer>> res = permute(nums);
        System.out.println(res.toString());
    }

}
