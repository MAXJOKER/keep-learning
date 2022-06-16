package leetcode.greedy;

import netscape.security.UserTarget;

import java.util.ArrayList;
import java.util.List;

/**
 * @author maxjoker
 * @date 2022-06-16 20：38
 *
 * https://leetcode.cn/problems/insert-interval/
 * 57. 插入区间
 * 给你一个 无重叠的 ，按照区间起始端点排序的区间列表。
 *
 * 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
 *
 *
 *
 * 示例 1：
 *
 * 输入：intervals = [[1,3],[6,9]], newInterval = [2,5]
 * 输出：[[1,5],[6,9]]
 * 示例 2：
 *
 * 输入：intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * 输出：[[1,2],[3,10],[12,16]]
 * 解释：这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。
 * 示例 3：
 *
 * 输入：intervals = [], newInterval = [5,7]
 * 输出：[[5,7]]
 * 示例 4：
 *
 * 输入：intervals = [[1,5]], newInterval = [2,3]
 * 输出：[[1,5]]
 * 示例 5：
 *
 * 输入：intervals = [[1,5]], newInterval = [2,7]
 * 输出：[[1,7]]
 *
 *
 * 提示：
 *
 * 0 <= intervals.length <= 10^4
 * intervals[i].length == 2
 * 0 <= intervals[i][0] <= intervals[i][1] <= 10^5
 * intervals 根据 intervals[i][0] 按 升序 排列
 * newInterval.length == 2
 * 0 <= newInterval[0] <= newInterval[1] <= 10^5
 *
 */
public class InsertInterval {
    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * 分段处理
     *
     * @param intervals
     * @param newInterval
     * @return
     */
    public static int[][] insert(int[][] intervals, int[] newInterval) {
        int len = intervals.length;
        if (len < 1) {
            return new int[][]{newInterval};
        }

        int newIntervalLen = newInterval.length;
        if (newIntervalLen < 1) {
            return intervals;
        }


        List<int[]> res = new ArrayList<>();

        int left = newInterval[0];
        int right = newInterval[1];
        boolean hasInsert = false;

        for (int i = 0; i < len; i++) {
            int[] current = intervals[i];

            // 1. 在当前结点左侧 且 无交集
            if (current[0] > right) {
                // 判断是否已经插入过，不要重复插入
                if (!hasInsert) {
                    res.add(new int[]{left, right});
                    hasInsert = true;
                }

                res.add(current);
            } else if (current[1] < left) { // 2. 在当前节点右侧 且 无交集，插入当前结点，然后继续循环比较
                res.add(current);
            } else { // 3. 有交集的情况
                left = Math.min(left, current[0]);
                right = Math.max(right, current[1]);
            }
        }

        if (!hasInsert) {
            res.add(new int[]{left, right});
        }

        return res.toArray(new int[res.size()][]);
    }

    public static void main(String[] args) {
//        int[][] intervals = new int[][]{{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}};
        int[][] intervals = new int[][]{{1, 5}};
        int[] newInterval = new int[]{2, 3};

        insert(intervals, newInterval);
    }
}
