package leetcode.binarysearch;

/**
 * @author maxjoker
 * @date 2022-04-19 13:12
 *
 * https://leetcode-cn.com/problems/find-the-smallest-divisor-given-a-threshold/
 *
 * 1283. 使结果不超过阈值的最小除数
 * 给你一个整数数组 nums 和一个正整数 threshold  ，你需要选择一个正整数作为除数，然后将数组里每个数都除以它，并对除法结果求和。
 *
 * 请你找出能够使上述结果小于等于阈值 threshold 的除数中 最小 的那个。
 *
 * 每个数除以除数后都向上取整，比方说 7/3 = 3 ， 10/2 = 5 。
 *
 * 题目保证一定有解。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,5,9], threshold = 6
 * 输出：5
 * 解释：如果除数为 1 ，我们可以得到和为 17 （1+2+5+9）。
 * 如果除数为 4 ，我们可以得到和为 7 (1+1+2+3) 。如果除数为 5 ，和为 5 (1+1+1+2)。
 * 示例 2：
 *
 * 输入：nums = [2,3,5,7,11], threshold = 11
 * 输出：3
 * 示例 3：
 *
 * 输入：nums = [19], threshold = 5
 * 输出：4
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 5 * 10^4
 * 1 <= nums[i] <= 10^6
 * nums.length <= threshold <= 10^6
 *
 */
public class FindTheSmallestDivisorGivenAThreshold {
    /**
     * 二分
     * 时间复杂度：O(Nlogmax(nums))
     * 空间复杂度：O(1)
     * @param nums
     * @param threshold
     * @return
     */
    public static int smallestDivisor(int[] nums, int threshold) {

        // 最小就是1了，所以left = 1
        int left = 1;
        int right = 0;

        for (int num : nums) {
            right = Math.max(num, right);
        }

        while (left < right) {
//            int mid = left + (right - left) / 2;
            int mid = (left + right) >>> 1;
            int sum = getSum(nums, mid);

            if (sum > threshold) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    public static int getSum(int[] nums, int divisor) {
        int sum = 0;

        for (int i = 0; i < nums.length; i++) {
            // 向上整除
            sum += (nums[i] + divisor - 1) / divisor;
        }

        return sum;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{21212,10101,12121};
        System.out.println(smallestDivisor(nums, 1000000));
//        int[] nums = new int[]{16, 79, 6, 43, 53};
//        System.out.println(smallestDivisor(nums, 172));
    }
}
