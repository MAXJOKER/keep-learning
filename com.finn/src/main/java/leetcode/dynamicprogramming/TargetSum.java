package leetcode.dynamicprogramming;

/**
 * @author maxjoker
 * @date 2022-05-20 00:39
 *
 * https://leetcode.cn/problems/target-sum/
 *
 * 494. 目标和
 * 给你一个整数数组 nums 和一个整数 target 。
 *
 * 向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：
 *
 * 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
 * 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,1,1,1,1], target = 3
 * 输出：5
 * 解释：一共有 5 种方法让最终目标和为 3 。
 * -1 + 1 + 1 + 1 + 1 = 3
 * +1 - 1 + 1 + 1 + 1 = 3
 * +1 + 1 - 1 + 1 + 1 = 3
 * +1 + 1 + 1 - 1 + 1 = 3
 * +1 + 1 + 1 + 1 - 1 = 3
 * 示例 2：
 *
 * 输入：nums = [1], target = 1
 * 输出：1
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 20
 * 0 <= nums[i] <= 1000
 * 0 <= sum(nums[i]) <= 1000
 * -1000 <= target <= 1000
 *
 */
public class TargetSum {
    public static int findTargetSumWays(int[] nums, int target) {
        //先进行公式的推导
        // 设正数集合为left 负数集合为right
        // left + right = target
        // left - right = sum
        // left = (target + sum) /2;
        // 整道题变成了求值等于left的数量

        int len = nums.length;

        if (len == 0) {
            return 0;
        }

        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        if (sum < target) {
            return 0;
        }

        int res = sum + target;
        // 因为我们的正数集合为(sum+target)/2
        // 正数集合的个数显然没有小数
        // 所以，一旦不为偶数或者res为负数，就是错的
        if (res % 2 != 0 || res < 0) {
            return 0;
        }
        res /= 2;

        // dp[i]表示，在背包容积为i的情况的下，装满背包的所有情况有dp[i]种
        int[] dp = new int[res + 1];
        // 所有的遍历都会回到dp[0]，我们把这个起始点的值设置为1;
        dp[0] = 1;

        for (int num : nums) {
            for (int i = res; i >= num; i--) {
                // 经典滚动数组从后往前遍历为了避免遇到同层的元素
                // if(i < num),说明无论如何，num是放不进去我们的背包的
                // dp[i] = d[i-1],值保持不变
                // if(i> = num)
                // 两种情况，一种是不考虑num dp[i] = dp[i-1]
                // 另一种情况是考虑num,我们就要给num留位置
                // dp[i] = dp[i-num]
                // 两种可能我们都要考虑
                // 故 dp[i] = dp[i-1] + dp[i-num]
                // 而 dp[i] = dp[i-1]
                // 得出 dp[i] = dp[i] + dp[i-num]
                dp[i] += dp[i - num];
            }
        }

        return dp[res];
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,1,1,1,1};
        int target = 3;

        System.out.println(findTargetSumWays(nums, target));
    }
}
