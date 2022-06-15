package leetcode.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author maxjoker
 * @date 2022-06-15 21:32
 *
 * https://leetcode.cn/problems/merge-intervals/
 *
 * 56. 合并区间
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 输出：[[1,6],[8,10],[15,18]]
 * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * 示例 2：
 *
 * 输入：intervals = [[1,4],[4,5]]
 * 输出：[[1,5]]
 * 解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。
 *
 *
 * 提示：
 *
 * 1 <= intervals.length <= 10^4
 * intervals[i].length == 2
 * 0 <= starti <= endi <= 10^4
 *
 */
public class MergeIntervals {
    /**
     * 时间复杂度：O(nlogn)
     * 空间复杂度：O(nlogn)
     * 因为排序了所以是 n * logn
     *
     * 表达式：
     *      if (current[0] > currentRes[1]) {
     *          res.add(current);
     *      } else {
     *          currentRes[1] = max(currentRes[1], current[1]);
     *      }
     *
     * @param intervals
     * @return
     */
    public static int[][] merge(int[][] intervals) {
        int len =  intervals.length;
        if (len == 1) {
            return intervals;
        }

        // 需要先排序
        // 如果不排序，[[1,4],[0,4]] 这个case返回的是 [1, 4]
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        List<int[]> res = new ArrayList<>();
        res.add(intervals[0]);

        for (int i = 1; i < len; i++) {
            int[] current = intervals[i];
            int[] currentRes = res.get(res.size() - 1);
            if (current[0] > currentRes[1]) {
                res.add(current);
            } else {
                // 注意这里要取最大的
                currentRes[1] = Math.max(currentRes[1], current[1]);
            }
        }

        return res.toArray(new int[res.size()][]);
    }

    public static void main(String[] args) {
        int[][] intervals = new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        merge(intervals);
    }
}
