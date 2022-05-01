package leetcode.dynamicprogramming;

/**
 * @author maxjoker
 * @date 2022-05-01 16:36
 *
 * https://leetcode-cn.com/problems/product-of-array-except-self/
 *
 * 238. 除自身以外数组的乘积
 * 给你一个整数数组 nums，返回 数组 answer ，其中 answer[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积 。
 *
 * 题目数据 保证 数组 nums之中任意元素的全部前缀元素和后缀的乘积都在  32 位 整数范围内。
 *
 * 请不要使用除法，且在 O(n) 时间复杂度内完成此题。
 *
 *
 *
 * 示例 1:
 *
 * 输入: nums = [1,2,3,4]
 * 输出: [24,12,8,6]
 * 示例 2:
 *
 * 输入: nums = [-1,1,0,-3,3]
 * 输出: [0,0,9,0,0]
 *
 *
 * 提示：
 *
 * 2 <= nums.length <= 10^5
 * -30 <= nums[i] <= 30
 * 保证 数组 nums之中任意元素的全部前缀元素和后缀的乘积都在  32 位 整数范围内
 *
 *
 * 进阶：你可以在 O(1) 的额外空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组不被视为额外空间。）
 *
 * 主要是想清楚，分两段计算，i位置的乘积等于 i 之前元素的乘积 * i 之后元素的乘积
 *
 */
public class ProductOfArrayExceptSelf {
    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     * @param nums
     * @return
     */
    public static int[] productExceptSelf(int [] nums) {
        int len = nums.length;
        int[] L = new int[len];
        int[] R = new int[len];

        // [0, i - 1] 乘积
        L[0] = 1;
        for (int i = 1; i < len; i++) {
            L[i] = nums[i - 1] * L[i - 1];
        }

        // [i + 1, len - 1] 乘积
        R[len - 1] = 1;
        for (int i = len - 2; i >= 0; i--) {
            R[i] = nums[i + 1] * R[i + 1];
        }

        int[] result = new int[len];
        for (int i = 0; i < len; i++) {
            result[i] = L[i] * R[i];
        }

        return result;
    }

    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(1_
     * @param nums
     * @return
     */
    public static int[] method2(int[] nums) {
        int len = nums.length;
        int[] result = new int[len];

        result[0] = 1;
        for (int i = 1; i < len; i++) {
            result[i] = nums[i - 1] * result[i - 1];
        }

        int R = 1;
        for (int i = len - 1; i >= 0; i--) {
            result[i] = result[i] * R;
            R *= nums[i];
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4};
        System.out.println(productExceptSelf(nums));
    }
}
