package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * 350. 两个数组的交集 II
 * 给你两个整数数组 nums1 和 nums2 ，请你以数组形式返回两数组的交集。返回结果中每个元素出现的次数，应与元素在两个数组中都出现的次数一致（如果出现次数不一致，则考虑取较小值）。可以不考虑输出结果的顺序。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出：[2,2]
 * 示例 2:
 *
 * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出：[4,9]
 *
 *
 * 提示：
 *
 * 1 <= nums1.length, nums2.length <= 1000
 * 0 <= nums1[i], nums2[i] <= 1000
 *
 *
 * 进阶：
 *
 * 如果给定的数组已经排好序呢？你将如何优化你的算法？
 * 如果 nums1 的大小比 nums2 小，哪种方法更优？
 * 如果 nums2 的元素存储在磁盘上，内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？
 *
 * https://leetcode-cn.com/problems/intersection-of-two-arrays-ii/
 */
public class IntersectionOfTwoArraysIi {
    /**
     * 哈希表
     * 由于同一个数字在两个数组中都可能出现多次，因此需要用哈希表存储每个数字出现的次数。
     * 对于一个数字，其在交集中出现的次数等于该数字在两个数组中出现次数的最小值。
     *
     * 首先遍历第一个数组，并在哈希表中记录第一个数组中的每个数字以及对应出现的次数，
     * 然后遍历第二个数组，对于第二个数组中的每个数字，如果在哈希表中存在这个数字，则将该数字添加到答案，并减少哈希表中该数字出现的次数。
     *
     * 为了降低空间复杂度，首先遍历较短的数组并在哈希表中记录每个数字以及对应出现的次数，然后遍历较长的数组得到交集。
     *
     * 时间复杂度：O(m+n)
     * 空间复杂度：O(min(m, n))
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public static int[] intersect(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return intersect(nums2,nums1);
        }

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums1.length; i++) {
            map.put(nums1[i], map.getOrDefault(nums1[i], 0) + 1);
        }

        int[] result = new int[nums1.length];
        int index = 0;

        for (int i = 0; i < nums2.length; i++) {
            int count = map.getOrDefault(nums2[i], 0);
            if (count > 0) {
                result[index++] = nums2[i];
                count --;
                if (count > 0) {
                    map.put(nums2[i], count);
                } else {
                    map.remove(nums2[i]);
                }
            }
        }

        return Arrays.copyOfRange(result, 0, index);
    }

    /**
     * 排序 + 双指针
     * 如果两个数组是有序的，则可以使用双指针的方法得到两个数组的交集。
     *
     * 首先对两个数组进行排序，然后使用两个指针遍历两个数组。
     *
     * 初始时，两个指针分别指向两个数组的头部。每次比较两个指针指向的两个数组中的数字，
     * 如果两个数字不相等，则将指向较小数字的指针右移一位，如果两个数字相等，将该数字添加到答案，
     * 并将两个指针都右移一位。当至少有一个指针超出数组范围时，遍历结束
     *
     * 时间复杂度：O(mlogm + nlogn) 排序+遍历
     * 空间复杂度：O(min(m, n))
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public static int[] intersect2(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int index1 = 0, index2 = 0, index = 0;
        int[] result = new int[Math.min(nums1.length, nums2.length)];
        while (index1 < nums1.length && index2 < nums2.length) {
            if (nums1[index1] < nums2[index2]) {
                index1++;
            } else if (nums1[index1] > nums2[index2]) {
                index2++;
            } else {
                result[index++] = nums1[index1];
                index1++;
                index2++;
            }
        }

        return Arrays.copyOfRange(result, 0, index);
    }

    public static void main(String[] args) {
        int[] nums1 = {4,9,5};
        int[] nums2 = {9,4,9,8,4};
        int[] result = intersect2(nums1, nums2);
        for (int r : result) {
            System.out.println(r);
        }
    }
}
