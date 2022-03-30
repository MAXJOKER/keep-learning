package leetcode.binarysearch;

import java.util.ArrayList;
import java.util.List;

/**
 * @author maxjoker
 * @date 2022-03-30 00:23
 *
 * https://leetcode-cn.com/problems/find-k-closest-elements/
 *
 * 658. 找到 K 个最接近的元素
 * 给定一个 排序好 的数组 arr ，两个整数 k 和 x ，从数组中找到最靠近 x（两数之差最小）的 k 个数。返回的结果必须要是按升序排好的。
 *
 * 整数 a 比整数 b 更接近 x 需要满足：
 *
 * |a - x| < |b - x| 或者
 * |a - x| == |b - x| 且 a < b
 *
 *
 * 示例 1：
 *
 * 输入：arr = [1,2,3,4,5], k = 4, x = 3
 * 输出：[1,2,3,4]
 * 示例 2：
 *
 * 输入：arr = [1,2,3,4,5], k = 4, x = -1
 * 输出：[1,2,3,4]
 *
 *
 * 提示：
 *
 * 1 <= k <= arr.length
 * 1 <= arr.length <= 10^4
 * arr 按 升序 排列
 * -10^4 <= arr[i], x <= 10^4
 *
 * 排除法很关键 （要排除多少个元素，size - k)
 *
 */
public class FindKClosestElements {
    /**
     * 双指针
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     * @param arr
     * @param k
     * @param x
     * @return
     */
    public static List<Integer> findClosestElements(int[] arr, int k, int x) {
        int size = arr.length;
        int left = 0;
        int right = size - 1;
        int removeNums = size - k;
        List<Integer> res = new ArrayList<>();

        while (removeNums > 0) {
            if (x - arr[left] <= arr[right] - x) {
                right--;
            } else {
                left++;
            }

            removeNums--;
        }

        for (int i = left; i < left + k; i++) {
            res.add(arr[i]);
        }

        return res;
    }

    /**
     * 二分查找
     * 时间复杂度：O(logN)
     * 空间复杂度：O(1)
     * @param arr
     * @param k
     * @param x
     * @return
     */
    public static List<Integer> findClosestElementsByBinarySearch(int[] arr, int k,int x) {
        int len = arr.length;
        int left = 0;
        int right = len - k;
        List<Integer> res = new ArrayList<>();

        while (left < right) {
            int mid = (right + left) / 2;
            if (x - arr[mid] > arr[mid + k] - x) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        for (int i = left; i < left + k; i++) {
            res.add(arr[i]);
        }

        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4, 5};
        System.out.println(findClosestElementsByBinarySearch(nums, 4, 3).toString());
    }
}
