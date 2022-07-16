package leetcode.slidingwindow;

/**
 * @author maxjoker
 * @date 2022-07-17 00:28
 *
 * https://leetcode.cn/problems/maximum-average-subarray-i/
 *
 * 643. 子数组最大平均数 I
 * 给你一个由 n 个元素组成的整数数组 nums 和一个整数 k 。
 *
 * 请你找出平均数最大且 长度为 k 的连续子数组，并输出该最大平均数。
 *
 * 任何误差小于 10-5 的答案都将被视为正确答案。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,12,-5,-6,50,3], k = 4
 * 输出：12.75
 * 解释：最大平均数 (12-5-6+50)/4 = 51/4 = 12.75
 * 示例 2：
 *
 * 输入：nums = [5], k = 1
 * 输出：5.00000
 *
 *
 * 提示：
 *
 * n == nums.length
 * 1 <= k <= n <= 105
 * -104 <= nums[i] <= 104
 *
 */
public class MaximumAverageSubarrayI {
    /**
     * 滑动窗口
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     * @param nums
     * @param k
     * @return
     */
    public static double findMaxAverage(int[] nums, int k) {
        int left = 0;
        int right = 0;
        double average = Integer.MIN_VALUE;
        int sum = 0;
        int count = 0;

        while (right < nums.length) {
            sum += nums[right];
            count++;

            while (count > k) {
                sum -= nums[left];
                left++;
                count--;
            }

            double ave = Integer.MIN_VALUE;
            // 子数组长度要等于k
            if (count == k) {
                ave = (sum * 1.0) / k;
            }
            average = Math.max(average, ave);

            right++;
        }

        return average;
    }

    /**
     * 滑动窗口
     * 先求出滑动窗口内 和 最大的子数组，再除以k
     * @param nums
     * @param k
     * @return
     */
    public static double solution2(int[] nums, int k) {
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }

        int res = sum;

        for (int i = k; i < nums.length; i++) {
            sum = sum + nums[i] - nums[i - k];
            res = Math.max(res, sum);
        }

        return (double) res / k;
    }

    public static void main(String[] args) {
//        int[] nums = new int[]{1,12,-5,-6,50,3};
        int[] nums = new int[]{-6662,5432,-8558,-8935,8731,-3083,4115,9931,-4006,-3284,-3024,1714,-2825,-2374,-2750,-959,6516,9356,8040,-2169,-9490,-3068,6299,7823,-9767,5751,-7897,6680,-1293,-3486,-6785,6337,-9158,-4183,6240,-2846,-2588,-5458,-9576,-1501,-908,-5477,7596,-8863,-4088,7922,8231,-4928,7636,-3994,-243,-1327,8425,-3468,-4218,-364,4257,5690,1035,6217,8880,4127,-6299,-1831,2854,-4498,-6983,-677,2216,-1938,3348,4099,3591,9076,942,4571,-4200,7271,-6920,-1886,662,7844,3658,-6562,-2106,-296,-3280,8909,-8352,-9413,3513,1352,-8825};
        int k = 90;
        System.out.println(findMaxAverage(nums, k));
        System.out.println(solution2(nums, k));
    }
}
