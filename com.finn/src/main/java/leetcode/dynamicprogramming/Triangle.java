package leetcode.dynamicprogramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author maxjoker
 * @date 2022-04-27 11:53
 *
 * https://leetcode-cn.com/problems/triangle/
 *
 * 120. 三角形最小路径和
 * 给定一个三角形 triangle ，找出自顶向下的最小路径和。
 *
 * 每一步只能移动到下一行中相邻的结点上。相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。
 * 也就是说，如果正位于当前行的下标 i ，那么下一步可以移动到下一行的下标 i 或 i + 1 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
 * 输出：11
 * 解释：如下面简图所示：
 *    2
 *   3 4
 *  6 5 7
 * 4 1 8 3
 * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
 * 示例 2：
 *
 * 输入：triangle = [[-10]]
 * 输出：-10
 *
 *
 * 提示：
 *
 * 1 <= triangle.length <= 200
 * triangle[0].length == 1
 * triangle[i].length == triangle[i - 1].length + 1
 * -10^4 <= triangle[i][j] <= 10^4
 *
 *
 * 进阶：
 *
 * 你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题吗？
 *
 */
public class Triangle {
    /**
     * 自下而上
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(n)
     * 自己手动debug一遍就相当明白了
     * @param triangle
     * @return
     */
    public static int minimumTotal(List<List<Integer>> triangle) {
        int len = triangle.size();
        if (len == 0) {
            return 0;
        }

        // len + 1 防止越界
        int[] dp = new int[len + 1];
        for(int i = len - 1; i >= 0; i--) {
            for (int j = 0; j < i + 1; j++) {
                dp[j] = Math.min(dp[j], dp[j + 1]) + triangle.get(i).get(j);
            }
        }

        return dp[0];
    }

    public static void main(String[] args) {
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> a = new ArrayList<>();
        a.add(2);
        list.add(a);

        List<Integer> b = new ArrayList<>();
        b.add(4);
        b.add(3);
        list.add(b);

        List<Integer> c = new ArrayList<>();
        c.add(6);
        c.add(7);
        c.add(5);
        list.add(c);

        List<Integer> d = new ArrayList<>();
        d.add(8);
        d.add(6);
        d.add(1);
        d.add(9);
        list.add(d);

        System.out.println(minimumTotal(list));
    }
}
