package leetcode;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 169. 多数元素
 * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
 *
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 *
 *
 *
 * 示例 1：
 *
 * 输入：[3,2,3]
 * 输出：3
 * 示例 2：
 *
 * 输入：[2,2,1,1,1,2,2]
 * 输出：2
 *
 *
 * 进阶：
 *
 * 尝试设计时间复杂度为 O(n)、空间复杂度为 O(1) 的算法解决此问题。
 *
 * https://leetcode-cn.com/problems/majority-element/
 */
public class MajorityElement {

    /**
     * 暴力法解答， 枚举数组中的每个元素，然后再遍历数组统计元素出现的次数
     * 时间复杂度 O(n*n)
     */

    /**
     * 哈希，把数字及其出现的次数保存到hash中，再对比
     * 时间复杂度 O(n)
     * 空间复杂度 O(n)
     */
    public static int majorityElementByHash(int[] nums) {
        HashMap<Integer, Integer> hash = new HashMap<>(nums.length);

        for (int num : nums) {
            hash.put(num, hash.getOrDefault(num, 0) + 1);

            if (hash.get(num) > nums.length / 2) {
                return num;
            }
        }

        return 0;
    }

    /**
     * 排序
     * 题目假设必定存在 n/2 的众数
     * 因此，现将数组排序，然后 nums[n/2]位置上的数肯定是想要的结果
     * 时间复杂度：O(nlogn)
     * 空间复杂度：O(logn)
     */
    public static int majorityElementBySort(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length/2];
    }

    /**
     * 摩尔投票法
     * @param nums
     * @return
     */
    public static int majorityElement(int[] nums) {
        // 假设第一个元素的票数
        int count = 1;
        // 假设第一个元素为当选人
        int ans = nums[0];

        for (int i = 1; i < nums.length; i++) {
            //如果数组遍历过程中，有元素和当选人元素一样，就加一
            if (ans == nums[i]) {
                count ++;
            } else {
                //如果没有就减一
                count --;
            }

            //如果票数为0的话就更换当选人
            if (count <= 0) {
                //票数重新置为1
                count = 1;
                //更换当选人
                ans = nums[i];
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] a = {6,5,5};
        System.out.println(majorityElement(a));
    }
}
