package leetcode;

import java.util.Random;

/**
 * 398. 随机数索引
 * 给定一个可能含有重复元素的整数数组，要求随机输出给定的数字的索引。 您可以假设给定的数字一定存在于数组中。
 *
 * 注意：
 * 数组大小可能非常大。 使用太多额外空间的解决方案将不会通过测试。
 *
 * 示例:
 *
 * int[] nums = new int[] {1,2,3,3,3};
 * Solution solution = new Solution(nums);
 *
 * // pick(3) 应该返回索引 2,3 或者 4。每个索引的返回概率应该相等。
 * solution.pick(3);
 *
 * // pick(1) 应该返回 0。因为只有nums[0]等于1。
 * solution.pick(1);
 *
 * https://leetcode-cn.com/problems/random-pick-index/
 *
 * 同类型：返回最大值的索引，概率相等
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 *
 */
public class RandomPickIndex {
    private static int[] nums;

    public RandomPickIndex(int[] nums) {
        this.nums = nums;
    }

    public static int pick(int target) {
        int count = 0;
        int index = 0;
        Random random = new Random();

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                // 计算target个数
                count++;
                // 我们以同一个数字的频数1/count的概率选出其中一个索引
                if (random.nextInt(count) == 0) {
                    index = i;
                }
            }
        }

        return index;
    }

    public static int pickMax() {
        int count = 0;
        int index = 0;
        int max = nums[0];
        Random random = new Random();

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > max) {
                max = nums[i];
                index = i;
            } else if (nums[i] == max) {
                // 计算target个数
                count++;
                // 我们以同一个数字的频数1/count的概率选出其中一个索引
                if (random.nextInt(count) == 0) {
                    index = i;
                }
            }
        }

        return index;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3,3,3};
        RandomPickIndex randomPickIndex = new RandomPickIndex(nums);
        System.out.println(pickMax());
    }
}
