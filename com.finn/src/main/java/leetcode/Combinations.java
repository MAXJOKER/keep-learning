package leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @date 2022-02-05 15:00
 *
 * 77. 组合
 * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
 *
 * 你可以按 任何顺序 返回答案。
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 4, k = 2
 * 输出：
 * [
 *   [2,4],
 *   [3,4],
 *   [2,3],
 *   [1,2],
 *   [1,3],
 *   [1,4],
 * ]
 * 示例 2：
 *
 * 输入：n = 1, k = 1
 * 输出：[[1]]
 *
 *
 * 提示：
 *
 * 1 <= n <= 20
 * 1 <= k <= n
 *
 * https://leetcode-cn.com/problems/combinations/
 *
 * 题解：https://leetcode-cn.com/problems/combinations/solution/hui-su-suan-fa-jian-zhi-python-dai-ma-java-dai-ma-/
 */
public class Combinations {
    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        if (n == 0) {
            return result;
        }

        // 没有必要再初始化一个数组
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = i + 1;
        }

        Deque<Integer> path = new ArrayDeque<>();
        dfs(nums, k, 0, 0, path, result);

        return result;
    }

    private static void dfs(int[] nums, int k, int depth, int begin, Deque<Integer> path, List<List<Integer>> result) {
        // 改成 path.size() == k，节省空间
        if (depth == k) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i = begin; i < nums.length; i++) {
            path.addLast(nums[i]);
            dfs(nums, k, depth + 1, i + 1, path, result);
            path.removeLast();
        }
    }

    public static List<List<Integer>> combine2(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        if (n == 0) {
            return result;
        }

        Deque<Integer> path = new ArrayDeque<>();
        dfs2(n, k, 1, path, result);

        return result;
    }

    private static void dfs2(int n, int k, int begin, Deque<Integer> path, List<List<Integer>> result) {
        if (path.size() == k) {
            result.add(new ArrayList<>(path));
            return;
        }

//        for (int i = begin; i <= n; i++) {
        // 搜索起点的上界 耗时超级少
        for (int i = begin; i <= n - (k - path.size()) + 1; i++) {
            path.addLast(i);
            dfs2(n, k, i + 1, path, result);
            path.removeLast();
        }
    }

    public static void main(String[] args) {
        int n = 4, k = 2;
        System.out.println(combine2(n, k));
    }
}
