package leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @date 2022-02-05 16:00
 *
 * 78. 子集
 * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 *
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3]
 * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 * 示例 2：
 *
 * 输入：nums = [0]
 * 输出：[[],[0]]
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 10
 * -10 <= nums[i] <= 10
 * nums 中的所有元素 互不相同
 *
 * https://leetcode-cn.com/problems/subsets/
 */
public class Subsets {
    /**
     * 回溯法
     * @param nums
     * @return
     */
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        int len = nums.length;

        if (len == 0) {
            return result;
        }

        Deque<Integer> path = new ArrayDeque<>();
        dfs(nums, path, 0, len, result);

        return result;
    }

    private static void dfs(int[] nums, Deque<Integer> path, int begin, int len, List<List<Integer>> result) {
        result.add(new ArrayList<>(path));

        for (int i = begin; i < len; i++) {
            path.addLast(nums[i]);

            dfs(nums, path, i+1, len, result);

            path.removeLast();
        }
    }

    /**
     * 暴力解法
     * @param nums
     * @return
     */
    public static List<List<Integer>> subsets2(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());
        for (int n : nums) {
            int size = result.size();
            for (int i = 0; i < size; i++) {
                List<Integer> newSub = new ArrayList<>(result.get(i));
                newSub.add(n);
                result.add(newSub);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 2};
        System.out.println(subsets(nums));
        System.out.println(subsets2(nums));
    }
}
