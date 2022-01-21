package leetcode;

import javax.crypto.spec.PSource;
import java.util.Arrays;

/**
 * 88. 合并两个有序数组
 * https://leetcode-cn.com/problems/merge-sorted-array/
 * 给你两个按 非递减顺序 排列的整数数组 nums1 和 nums2，另有两个整数 m 和 n ，分别表示 nums1 和 nums2 中的元素数目。
 *
 * 请你 合并 nums2 到 nums1 中，使合并后的数组同样按 非递减顺序 排列。
 *
 * 注意：最终，合并后数组不应由函数返回，而是存储在数组 nums1 中。为了应对这种情况，nums1 的初始长度为 m + n，其中前 m 个元素表示应合并的元素，后 n 个元素为 0 ，应忽略。nums2 的长度为 n 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
 * 输出：[1,2,2,3,5,6]
 * 解释：需要合并 [1,2,3] 和 [2,5,6] 。
 * 合并结果是 [1,2,2,3,5,6] ，其中斜体加粗标注的为 nums1 中的元素。
 * 示例 2：
 *
 * 输入：nums1 = [1], m = 1, nums2 = [], n = 0
 * 输出：[1]
 * 解释：需要合并 [1] 和 [] 。
 * 合并结果是 [1] 。
 * 示例 3：
 *
 * 输入：nums1 = [0], m = 0, nums2 = [1], n = 1
 * 输出：[1]
 * 解释：需要合并的数组是 [] 和 [1] 。
 * 合并结果是 [1] 。
 * 注意，因为 m = 0 ，所以 nums1 中没有元素。nums1 中仅存的 0 仅仅是为了确保合并结果可以顺利存放到 nums1 中。
 *
 *
 * 提示：
 *
 * nums1.length == m + n
 * nums2.length == n
 * 0 <= m, n <= 200
 * 1 <= m + n <= 200
 * -109 <= nums1[i], nums2[j] <= 109
 */
public class MergeSortedArray {

    /**
     * 直接合并后排序
     * 时间复杂度：O((m+n)log(m+n))
     * 空间复杂度：O(log(m+n))
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
//        for (int i = 0; i != n; ++i) {
//            nums1[m + i] = nums2[i];
//        }
        System.arraycopy(nums2, 0, nums1, m, n);
        Arrays.sort(nums1);
    }

    /**
     * 双指针
     * 这一方法将两个数组看作队列，每次从两个数组头部取出比较小的数字放到结果中。
     * 时间复杂度：O(m+n)
     * 指针移动单调递增，最多移动 m+n 次，因此时间复杂度为 O(m+n)
     *
     * 空间复杂度：O(m+n)
     * 需要建立长度为 m+n 的中间数组 sorted。
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public static void merge2(int[] nums1, int m, int[] nums2, int n) {
        int p = 0 , q = 0;
        int cur;
        int[] sorted = new int[m + n];
        while (p < m || q < n) {
            if (p == m) {
                cur = nums2[q++];
            } else if (q == n) {
                cur = nums2[p++];
            } else if (nums1[p] > nums2[q]) {
                cur = nums2[q++];
            } else {
                cur = nums1[p++];
            }

            sorted[p + q - 1] = cur;
        }

        for (int i =0; i < sorted.length; i++) {
            nums1[i] = sorted[i];
        }
    }

    /**
     * 逆向双指针
     * 观察可知，nums1 的后半部分是空的，nums1的长度是 m + n，可以直接覆盖而不会影响结果。因此可以指针设置为从后向前遍历，每次取两者之中的较大者放进nums1的最后面。
     * 时间复杂度：O(m+n)
     * 指针移动单调递减，最多移动 m+n次，因此时间复杂度为 O(m+n)
     *
     * 空间复杂度：O(1)
     * 直接对数组nums1原地修改，不需要额外空间。
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public static void merge3(int[] nums1, int m, int[] nums2, int n) {
        int p = m - 1;
        int q = n - 1;
        int cur;
        int tail = m + n - 1;

        while (p >= 0 || q >= 0) {
            if (p == -1) {
                cur = nums2[q--];
            } else if (q == -1) {
                cur = nums1[p--];
            } else if (nums1[p] > nums2[q]) {
                cur = nums1[p--];
            } else {
                cur = nums2[q--];
            }

            nums1[tail--] = cur;
        }
    }

    public static void main(String[] args) {
        int[] a = {1,2,3,0,0,0};
        int[] b = {2,5,6};
        merge3(a, 3, b, 3);
        for (int i : a) {
            System.out.println(i);
        }
    }
}
