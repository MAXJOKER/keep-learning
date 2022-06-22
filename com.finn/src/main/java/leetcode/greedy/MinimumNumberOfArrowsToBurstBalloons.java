package leetcode.greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author maxjoker
 * @date 2022-06-22 23:11
 */
public class MinimumNumberOfArrowsToBurstBalloons {
    /**
     * 时间复杂度：o(nlogn)
     * 空间复杂度：o(logn)
     * @param points
     * @return
     */
    public static int findMinArrowShots(int[][] points) {
        int len = points.length;
        if (len < 2) {
            return len;
        }

        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] > o2[1]) {
                    return 1;
                } else if (o1[1] < o2[1]) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });

        int minCount = 1;
        // 第 1 个区间的末尾，目前线段能够达到的最远位置
        int end = points[0][1];
        // 贪心法，基于上一个箭，记录当前能够射穿的所有的区间
        for (int i = 1; i < len; i++) {
            if (points[i][0] > end) {
                end = points[i][1];
                minCount++;

            }
        }

        return minCount;
    }

    public static void main(String[] args) {
        int[][] points = new int[][]{{1, 2}, {3, 4}};
        System.out.println(findMinArrowShots(points));
    }
}
