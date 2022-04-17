package leetcode.binarysearch;

/**
 * @author maxjoker
 * @date 2022-04-13 20:00
 *
 * https://leetcode-cn.com/problems/sum-of-mutated-array-closest-to-target/
 *
 * 1300. 转变数组后最接近目标值的数组和
 * 给你一个整数数组 arr 和一个目标值 target ，请你返回一个整数 value ，使得将数组中所有大于 value 的值变成 value 后，数组的和最接近  target （最接近表示两者之差的绝对值最小）。
 *
 * 如果有多种使得和最接近 target 的方案，请你返回这些整数中的最小值。
 *
 * 请注意，答案不一定是 arr 中的数字。
 *
 *
 *
 * 示例 1：
 *
 * 输入：arr = [4,9,3], target = 10
 * 输出：3
 * 解释：当选择 value 为 3 时，数组会变成 [3, 3, 3]，和为 9 ，这是最接近 target 的方案。
 * 示例 2：
 *
 * 输入：arr = [2,3,5], target = 10
 * 输出：5
 * 示例 3：
 *
 * 输入：arr = [60864,25176,27249,21296,20204], target = 56803
 * 输出：11361
 *
 *
 * 提示：
 *
 * 1 <= arr.length <= 10^4
 * 1 <= arr[i], target <= 10^5
 *
 */
public class SumOfMutatedArrayClosestToTarget {
    /**
     * 时间复杂度：O(NlogN)
     * 空间复杂度：O(1)
     * @param arr
     * @param target
     * @return
     */
    public static int findBestValue(int[] arr, int target) {
        int left = 0;
        int right = 0;

        for (int num : arr) {
            right = Math.max(num, right);
        }

        while (left < right) {
            int mid = left + (right - left) / 2;
            int sum = countSum(arr, mid);

            // 计算第 1 个使得转变后数组的和大于等于 target 的阈值 threshold
            if (sum < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        int sum1 = countSum(arr, left - 1);
        int sum2 = countSum(arr, left);

        // sum2是大于等于target的
        if (target - sum1 <= sum2 - target) {
            return left - 1;
        }

        return left;
    }

    private static int countSum(int[] arr, int mid) {
        int sum = 0;

        for (int num : arr) {
            sum += Math.min(num, mid);
        }

        return sum;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{9, 3, 4};
        System.out.println(findBestValue(arr, 10));
    }
}
