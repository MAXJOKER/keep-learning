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
            temp[i] = indexes[i];
        }

        int i = left;
        int j = mid + 1;

        for (int k = left; k <= right; k++) {
            if (i == mid + 1) {
                indexes[k] = temp[j];
                j++;
            } else if (j == right + 1) {
                indexes[k] = temp[i];
                i++;
                res[indexes[k]] += (right - mid);
            } else if (nums[temp[i]] <= nums[temp[j]]) {
                indexes[k] = temp[i];
                i++;
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
