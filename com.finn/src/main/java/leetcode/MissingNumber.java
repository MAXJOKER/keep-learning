package leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * 给定一个包含 [0, n]中n个数的数组 nums ，找出 [0, n] 这个范围内没有出现在数组中的那个数。
 *
 * 示例 1：
 *
 * 输入：nums = [3,0,1]
 * 输出：2
 * 解释：n = 3，因为有 3 个数字，所以所有的数字都在范围 [0,3] 内。2 是丢失的数字，因为它没有出现在 nums 中。
 * 示例 2：
 *
 * 输入：nums = [0,1]
 * 输出：2
 * 解释：n = 2，因为有 2 个数字，所以所有的数字都在范围 [0,2] 内。2 是丢失的数字，因为它没有出现在 nums 中。
 * 示例 3：
 *
 * 输入：nums = [9,6,4,2,3,5,7,0,1]
 * 输出：8
 * 解释：n = 9，因为有 9 个数字，所以所有的数字都在范围 [0,9] 内。8 是丢失的数字，因为它没有出现在 nums 中。
 * 示例 4：
 *
 * 输入：nums = [0]
 * 输出：1
 * 解释：n = 1，因为有 1 个数字，所以所有的数字都在范围 [0,1] 内。1 是丢失的数字，因为它没有出现在 nums 中。
 *
 * https://leetcode-cn.com/problems/missing-number/
 */
public class MissingNumber {
    public static int missingNumber(int[] nums) {
        int len = nums.length;

        if (len == 1 && nums[0] == 0) {
            return 1;
        }

        int[] temp = new int[len + 1];
        Arrays.fill(temp, -1);

        for (int i = 0; i < len; i++) {
            temp[nums[i]] = nums[i];
        }

        for (int j = 0; j < temp.length; j++) {
            if (temp[j] != j) {
                return j;
            }
        }

        return -1;
    }

    /**
     * 排序后比较
     * 时间复杂度：O(nlogn)
     * 空间复杂度：O(logn)
     * @param nums
     * @return
     */
    public static int missingNumber2(int[] nums) {
        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i) {
                return i;
            }
        }

        return -1;
    }

    /**
     * 哈希
     * @param nums
     * @return
     */
    public static int missingNumber3(int[] nums) {
        Set<Integer> set = new HashSet<>();

        for (int i = 1; i < nums.length; i++) {
            set.add(nums[i]);
        }

        for (int j = 0; j < nums.length; j++) {
            if (!set.contains(j)) {
                return j;
            }
        }

        return -1;
    }

    /**
     * 高斯求和
     * @param nums
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     * @return
     */
    public static int missingNumber4(int[] nums) {
        int len = nums.length;
        int sum = len * (len + 1) / 2;

        int sum2 = 0;
        for (int i = 0; i < nums.length; i++) {
            sum2 += nums[i];
        }

        return sum - sum2;
    }

    public static void main(String[] args) {
        int[] nums = {0,1};
        System.out.println(missingNumber(nums));
    }
}
