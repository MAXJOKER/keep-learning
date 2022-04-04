package leetcode.binarysearch;

import java.util.Arrays;

/**
 * @author maxjoker
 * @date 2022-04-02 21:37
 *
 * https://leetcode-cn.com/problems/valid-triangle-number/
 *
 * 611. 有效三角形的个数
 * 给定一个包含非负整数的数组 nums ，返回其中可以组成三角形三条边的三元组个数。
 *
 *
 *
 * 示例 1:
 *
 * 输入: nums = [2,2,3,4]
 * 输出: 3
 * 解释:有效的组合是:
 * 2,3,4 (使用第一个 2)
 * 2,3,4 (使用第二个 2)
 * 2,2,3
 * 示例 2:
 *
 * 输入: nums = [4,2,3,4]
 * 输出: 4
 *
 *
 * 提示:
 *
 * 1 <= nums.length <= 1000
 * 0 <= nums[i] <= 1000
 *
 */
public class ValidTriangleNumber {
    /**
     * 题解：https://www.suanfa8.com/binary-search/solutions/find-index/0611-valid-triangle-number/
     * 时间复杂度：O(N^2logN) 这里 N 是输入数组的长度。
     * 对输入数组排序 O(NlogN)，枚举第一条边 O(N)，枚举第二条边 O(N)
     * 二分查找第三条边的边界 O(logN), 综合以上 O(Nlog N + N^2log N) = O(N^2logN)
     *
     * 空间复杂度：O(1)
     *
     * @param nums
     * @return
     */
    public static int triangleNumber(int[] nums) {
        Arrays.sort(nums);

        int len = nums.length;
        int count = 0;

        for (int i = 0; i < len - 2; i++) {
            for (int j = i + 1; j < len - 1; j++) {
                // 找到第 1 个大于等于两边之和的下标
                int left = j + 1;
                int right = len;

                while (left < right) {
                    int mid = (left + right) / 2;
                    if (nums[mid] < nums[i] + nums[j]) {
                        left = mid + 1;
                    } else {
                        right = mid;
                    }
                }

                count += (left - j - 1);
            }
        }

        return count;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2, 2, 3, 4};
        System.out.println(triangleNumber(nums));
    }
}
