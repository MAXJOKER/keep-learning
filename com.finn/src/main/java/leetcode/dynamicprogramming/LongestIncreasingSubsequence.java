package leetcode.dynamicprogramming;

import java.util.Arrays;

/**
 * @author maxjoker
 * @date 2022-04-21 17:49
 *
 * https://leetcode-cn.com/problems/longest-increasing-subsequence/
 *
 * 300. 最长递增子序列
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 *
 * 子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
 *
 *
 * 示例 1：
 *
 * 输入：nums = [10,9,2,5,3,7,101,18]
 * 输出：4
 * 解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
 * 示例 2：
 *
 * 输入：nums = [0,1,0,3,2,3]
 * 输出：4
 * 示例 3：
 *
 * 输入：nums = [7,7,7,7,7,7,7]
 * 输出：1
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 2500
 * -104 <= nums[i] <= 104
 *
 *
 * 进阶：
 *
 * 你能将算法的时间复杂度降低到 O(n log(n)) 吗?
 *
 */
public class LongestIncreasingSubsequence {
    /**
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(n)
     * @param nums
     * @return
     */
    public static int search(int[] nums) {
        int len = nums.length;

        if (len < 2) {
            return len;
        }

        int[] dp = new int[len];
        Arrays.fill(dp, 1);
        for (int i = 1; i < len; i++) {
            // 看之前的，如果比i小，说明往后的数字可以形成更长的子序列
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    // 当前选择该数字，与不选择该数字，哪个大就选哪个
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
        }

        int res = dp[0];
        for (int d : dp) {
            res = Math.max(res, d);
        }

        return res;
    }

    /**
     * https://leetcode-cn.com/problems/longest-increasing-subsequence/solution/dong-tai-gui-hua-er-fen-cha-zhao-tan-xin-suan-fa-p/
     * (看动图看动图看动图)
     * 二分查找
     * 时间复杂度：O(nlogN)
     * 空间复杂度：O(n)
     * @param nums
     * @return
     */
    public static int searchByBinarySearch(int[] nums) {
        int len = nums.length;
        if (len < 2) {
            return len;
        }

        int[] tail = new int[len];
        tail[0] = nums[0];
        // end 表示有序数组 tail 的最后一个已经赋值元素的索引
        int end = 0;
        for (int i = 1; i < len; i++) {
            // 【逻辑 1】比 tail 数组实际有效的末尾的那个元素还大
            if (nums[i] > tail[end]) {
                // 直接添加在那个元素的后面，所以 end 先加 1
                end++;
                tail[end] = nums[i];
            } else {
                // 这部分看题解的动图 看动图 看动图
                // 使用二分查找法，在有序数组 tail 中
                // 找到第 1 个大于等于 nums[i] 的元素，尝试让那个元素更小（这句话也很重要）
                int l = 0;
                int r = end;
                while (l < r) {
                    int mid = l + (r - l) / 2;
                    if (tail[mid] < nums[i]) {
                        l = mid + 1;
                    } else {
                        r = mid;
                    }
                }
                // 走到这里是因为 【逻辑 1】 的反面，因此一定能找到第 1 个大于等于 nums[i] 的元素
                tail[l] = nums[i];
            }
        }

        // 此时 end 是有序数组 tail 最后一个元素的索引
        // 题目要求返回的是长度，因此 +1 后返回
        end++;
        return end;
    }

    public static void main(String[] args) {
//        int[] nums = new int[]{4, 10, 4, 3, 8, 9};
//        int[] nums = new int[]{10,9,2,5,3,7,101,18};
        int[] nums = new int[]{0, 1, 0, 3, 2, 3};
        System.out.println(search(nums));
        System.out.println(searchByBinarySearch(nums));
    }
}
