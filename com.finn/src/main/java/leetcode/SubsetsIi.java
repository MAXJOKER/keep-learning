package leetcode;

import java.util.*;

/**
 * @date 2022-02-05
 * 90. 子集 II
 * 给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。
 *
 * 解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,2]
 * 输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]
 * 示例 2：
 *
 * 输入：nums = [0]
 * 输出：[[],[0]]
 *
 *
 * 提示：
 *
 * https://leetcode-cn.com/problems/subsets-ii/
 *
 * 1 <= nums.length <= 10
 * -10 <= nums[i] <= 10
 *
 * 时间复杂度：O(n * 2^n)
 * 空间复杂度: O(n)
 */
public class SubsetsIi {
    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        int len = nums.length;

        if (len == 0) {
            return result;
        }

        Arrays.sort(nums);

        Deque<Integer> path = new ArrayDeque<>();
        dfs(nums, path, 0, len, result);
        return result;
    }

    private static void dfs(int[] nums, Deque<Integer> path, int begin, int len, List<List<Integer>> result) {
        result.add(new ArrayList<>(path));

        for (int i = begin; i < len; i++) {
            if (i > begin && nums[i] == nums[i - 1]) {
                continue;
            }
            path.addLast(nums[i]);
            dfs(nums, path, i + 1, len, result);
            path.removeLast();
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,2};
        System.out.println(subsetsWithDup(nums));
    }
}
