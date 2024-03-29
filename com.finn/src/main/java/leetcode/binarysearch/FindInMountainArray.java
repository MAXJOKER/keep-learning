package leetcode.binarysearch;

/**
 * @author maxjoker
 * @date 2022-04-07 22:30
 *
 * https://leetcode-cn.com/problems/find-in-mountain-array/
 *
 * 1095. 山脉数组中查找目标值
 * （这是一个 交互式问题 ）
 *
 * 给你一个 山脉数组 mountainArr，请你返回能够使得 mountainArr.get(index) 等于
 * target 最小 的下标 index 值。
 *
 * 如果不存在这样的下标 index，就请返回 -1。
 *
 *
 *
 * 何为山脉数组？如果数组 A 是一个山脉数组的话，那它满足如下条件：
 *
 * 首先，A.length >= 3
 *
 * 其次，在 0 < i < A.length - 1 条件下，存在 i 使得：
 *
 * A[0] < A[1] < ... A[i-1] < A[i]
 * A[i] > A[i+1] > ... > A[A.length - 1]
 *
 *
 * 你将 不能直接访问该山脉数组，必须通过 MountainArray 接口来获取数据：
 *
 * MountainArray.get(k) - 会返回数组中索引为k 的元素（下标从 0 开始）
 * MountainArray.length() - 会返回该数组的长度
 *
 *
 * 注意：
 *
 * 对 MountainArray.get 发起超过 100 次调用的提交将被视为错误答案。
 * 此外，任何试图规避判题系统的解决方案都将会导致比赛资格被取消。
 *
 * 为了帮助大家更好地理解交互式问题，我们准备了一个样例
 * “答案”：https://leetcode-cn.com/playground/RKhe3ave，请注意这 不是一个正确答案。
 *
 *
 *
 * 示例 1：
 *
 * 输入：array = [1,2,3,4,5,3,1], target = 3
 * 输出：2
 * 解释：3 在数组中出现了两次，下标分别为 2 和 5，我们返回最小的下标 2。
 * 示例 2：
 *
 * 输入：array = [0,1,2,4,2,1], target = 3
 * 输出：-1
 * 解释：3 在数组中没有出现，返回 -1。
 *
 *
 * 提示：
 *
 * 3 <= mountain_arr.length() <= 10000
 * 0 <= target <= 10^9
 * 0 <= mountain_arr.get(index) <= 10^9
 *
 * 思路：
 * 第 1 步：先找到山顶元素 mountaintop 所在的下标；
 * 第 2 步：在前有序且升序数组中找 target 所在的下标，如果找到了，就返回，如果没有找到，才执行第
 * 3 步； 第 3 步：如果步骤 2 找不到，就在后有序且降序数组中找 target 所在的下标。
 *
 */
public class FindInMountainArray {
    public static int get(int[] nums, int index) {
        return nums[index];
    }

    public static int length(int[] nums) {
        return nums.length;
    }

    /**
     * 时间复杂度：O(logN)
     * 空间复杂度：O(1)
     * @param target
     * @param nums
     * @return
     */
    public static int findInMountainArray(int target, int[] nums) {
        int peakIndex = findPeakIndex(nums);
        int right = length(nums) - 1;
        int result = -1;

        result = findTargetInLeft(nums, 0, peakIndex, target);

        if (result == -1) {
            result = findTargetInRight(nums, peakIndex, right, target);
        }

        return result;
    }

    /**
     * 右边查找target
     * @param nums
     * @param peakIndex
     * @param right
     * @param target
     * @return
     */
    private static int findTargetInRight(int[] nums, int peakIndex, int right, int target) {
        int left = peakIndex;

        while (left < right) {
            int mid = (left + right) / 2;
            if (get(nums, mid) > target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        if (get(nums, right) == target) {
            return right;
        }

        return -1;
    }

    /**
     * 左边查找target
     * @param nums
     * @param left
     * @param peakIndex
     * @param target
     * @return
     */
    private static int findTargetInLeft(int[] nums, int left, int peakIndex, int target) {
        int right = peakIndex;

        while (left < right) {
            int mid = (left + right) / 2;
            if (get(nums, mid) < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        if (get(nums, left) == target) {
            return left;
        }

        return -1;
    }

    /**
     * 找出山顶元素
     * @param nums
     * @return
     */
    public static int findPeakIndex(int[] nums) {
        int len = length(nums);
        int left = 0;
        int right = len - 1;

        while (left < right) {
            int mid = (left + right) / 2;

            if (get(nums, mid) > get(nums, mid + 1)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{0, 1, 2, 4, 2, 1};
        System.out.println(findInMountainArray(3, nums));
    }

    /**
     * // This is MountainArray's API interface.
     * // You should not implement it, or speculate about its implementation
     * interface MountainArray {
     *     public int get(int index) {}
     *     public int length() {}
     * }
     */

/*
// leetcode ac 代码
class Solution {
        public int findInMountainArray(int target, MountainArray mountainArr) {
            int peakIndex = findPeakIndex(mountainArr);
            int right = mountainArr.length() - 1;
            int result = -1;

            result = findTargetInLeft(mountainArr, 0, peakIndex, target);

            if (result == -1) {
                result = findTargetInRight(mountainArr, peakIndex, right, target);
            }

            return result;
        }

        private int findTargetInRight(MountainArray nums, int peakIndex, int right, int target) {
            int left = peakIndex;

            while (left < right) {
                int mid = (left + right) / 2;
                if (nums.get(mid) > target) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }

            if (nums.get(right) == target) {
                return right;
            }

            return -1;
        }

        private int findTargetInLeft(MountainArray nums, int left, int peakIndex, int target) {
            int right = peakIndex;

            while (left < right) {
                int mid = (left + right) / 2;
                if (nums.get(mid) < target) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }

            if (nums.get(left) == target) {
                return left;
            }

            return -1;
        }

        public int findPeakIndex(MountainArray nums) {
            int len = nums.length();
            int left = 0;
            int right = len - 1;

            while (left < right) {
                int mid = (left + right) / 2;

                if (nums.get(mid) > nums.get(mid + 1)) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }

            return left;
        }
    }*/
}


