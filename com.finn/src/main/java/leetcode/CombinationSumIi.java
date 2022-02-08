package leetcode;

import java.util.*;

/**
 * @date 2022-02-05 15:00
 *
 * 40. 组合总和 II
 * 给定一个候选人编号的集合 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 *
 * candidates 中的每个数字在每个组合中只能使用 一次 。
 *
 * 注意：解集不能包含重复的组合。
 *
 *
 *
 * 示例 1:
 *
 * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
 * 输出:
 * [
 * [1,1,6],
 * [1,2,5],
 * [1,7],
 * [2,6]
 * ]
 * 示例 2:
 *
 * 输入: candidates = [2,5,2,1,2], target = 5,
 * 输出:
 * [
 * [1,2,2],
 * [5]
 * ]
 *
 *
 * 提示:
 * 1 <= candidates.length <= 100
 * 1 <= candidates[i] <= 50
 * 1 <= target <= 30
 *
 * https://leetcode-cn.com/problems/combination-sum-ii/
 */
public class CombinationSumIi {
    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        int len = candidates.length;
        if (len == 0) {
            return result;
        }

        Arrays.sort(candidates);

        Deque<Integer> path = new ArrayDeque<>();
        boolean[] used = new boolean[len];

        dfs(candidates, 0, len, target, path, result);

        return result;
    }

    private static void dfs(int[] candidates, int begin, int len, int target, Deque<Integer> path, boolean[] used, List<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<>(path));
            return;
        }

        //  i = begin，不重复使用前面的数字
        for (int i = begin; i < len; i++) {
            if (target - candidates[i] < 0) {
                break;
            }

            if (used[i]) {
                continue;
            }

            // 剪枝
            // 剪枝条件：i > 0 是为了保证 nums[i - 1] 有意义
            // 写 !used[i - 1] 是因为 nums[i - 1] 在深度优先遍历的过程中刚刚被撤销选择
            if (i > 0 && candidates[i] == candidates[i - 1] && !used[i - 1]) {
                continue;
            }

            path.addLast(candidates[i]);
            used[i] = true;

            System.out.println("递归前 => " + path + ", i => " + i);
            dfs(candidates, i, len, target - candidates[i], path, used, result);

            used[i] = false;
            path.removeLast();
            System.out.println("递归前 => " + path + ", i => " + i);
        }
    }

    private static void dfs(int[] candidates, int begin, int len, int target, Deque<Integer> path, List<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<>(path));
            return;
        }

        //  i = begin，不重复使用前面的数字
        for (int i = begin; i < len; i++) {
            // 大剪枝
            if (target - candidates[i] < 0) {
                System.out.println("大剪枝：i => " + i + ", begin => " + begin + ", target => " + target);
                break;
            }

            // 小剪枝
            // 剪枝条件：i > begin 是为了保证 nums[i - 1] 有意义
            // 写 !used[i - 1] 是因为 nums[i - 1] 在深度优先遍历的过程中刚刚被撤销选择
            if (i > begin && candidates[i] == candidates[i - 1]) {
                System.out.println("小剪枝：i => " + i + ", begin => " + begin + ", target => " + target);
                continue;
            }

            path.addLast(candidates[i]);

            System.out.println("递归前 => " + path + ", i => " + i + ", begin => " + begin + ", target => " + target);
            dfs(candidates, i + 1, len, target - candidates[i], path, result);

            path.removeLast();
            System.out.println("递归后 => " + path + ", i => " + i + ", begin => " + begin + ", target => " + target);
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,2,3};
        int target = 5;
        System.out.println(combinationSum2(nums, target).toString());
    }
}
