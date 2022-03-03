package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author maxjoker
 * @date 2022-02-28 20:27
 *
 * 315. 计算右侧小于当前元素的个数
 * 给你一个整数数组 nums ，按要求返回一个新数组 counts 。数组 counts 有该性质： counts[i] 的值是  nums[i] 右侧小于 nums[i] 的元素的数量。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [5,2,6,1]
 * 输出：[2,1,1,0]
 * 解释：
 * 5 的右侧有 2 个更小的元素 (2 和 1)
 * 2 的右侧仅有 1 个更小的元素 (1)
 * 6 的右侧有 1 个更小的元素 (1)
 * 1 的右侧有 0 个更小的元素
 * 示例 2：
 *
 * 输入：nums = [-1]
 * 输出：[0]
 * 示例 3：
 *
 * 输入：nums = [-1,-1]
 * 输出：[0,0]
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 105
 * -104 <= nums[i] <= 104
 *
 *
 * 时间复杂度：O(NlogN)，数组的元素个数是 N，递归执行分治法，时间复杂度是对数级别的，因此时间复杂度是 O(NlogN)。
 * 空间复杂度：O(N)，需要 3 个数组，一个索引数组，一个临时数组用于索引数组的归并，还有一个结果数组，它们的长度都是 N，故空间复杂度是 O(N)。
 *
 *
 * 题解：https://leetcode-cn.com/problems/count-of-smaller-numbers-after-self/solution/gui-bing-pai-xu-suo-yin-shu-zu-python-dai-ma-java-/
 *
 * 题解最后几张图很好理解
 *
 */
public class CountOfSmallerNumbersAfterSelf {
    public static List<Integer> countSmaller(int[] nums) {
        int len = nums.length;
        List<Integer> list = new ArrayList<>();

        if (len <= 1) {
            return list;
        }

        // 索引数组
        int[] indexes = new int[len];
        for (int i = 0; i < len; i++) {
            indexes[i] = i;
        }

        int[] temp = new int[len];
        int[] res = new int[len];
        sortArray(nums, 0, len - 1, indexes, temp, res);

        for (int i = 0; i < len; i++) {
            list.add(res[i]);
        }

        return list;
    }

    private static void sortArray(int[] nums, int left, int right, int[] indexes, int[] temp, int[] res) {
        if (left == right) {
            return;
        }

        int mid = left + (right - left) / 2;
        sortArray(nums, left, mid, indexes, temp, res);
        sortArray(nums, mid + 1, right, indexes, temp, res);

        if (nums[indexes[mid]] <= nums[indexes[mid + 1]]) {
            return;
        }

        sortAndCount(nums, left, mid, right, indexes, temp, res);
    }

    private static void sortAndCount(int[] nums, int left, int mid, int right, int[] indexes, int[] temp, int[] res) {
        for (int i = left; i <= right; i++) {
            // 辅助数组初始化，存放的是索引，即我们归并的不是数组元素，而是数组索引，nums仅用来判断是否有逆序对
            temp[i] = indexes[i];
        }

        int i = left;
        int j = mid + 1;

        for (int k = left; k <= right; k++) {
            if (i == mid + 1) {
                // 左边元素用尽，取右边元素(说明数组都比较有序了，左边都是比较小的)
                indexes[k] = temp[j];
                j++;
            } else if (j == right + 1) {
                // 右边元素用尽，取左边元素(说明数组存在较多的逆序数组 即数组前面的元素比后面的元素大)
                indexes[k] = temp[i];
                i++;
                // indexes[k]可以和右边[mid + 1, right]构成逆序对，长度为right - (mid + 1) + 1，即right - mid
                res[indexes[k]] += (right - mid);
            } else if (nums[temp[i]] <= nums[temp[j]]) {
                indexes[k] = temp[i];
                i++;

                // indexes[k]可以和右边[mid + 1, j)构成逆序对，长度为j - (mid + 1)，即j - mid - 1
                // j++的条件是 num[temp[i]] > nums[temp[j]]，而 j - (mid + 1) 计算的就是 num[temp[i]] > nums[temp[j]] 的数量
                res[indexes[k]] += j - mid -1;
            } else {
                indexes[k] = temp[j];
                j++;
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{5,2,6,1};
        System.out.println(countSmaller(nums));
    }
}
