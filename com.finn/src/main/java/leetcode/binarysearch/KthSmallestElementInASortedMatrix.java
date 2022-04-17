package leetcode.binarysearch;

import java.util.Random;

/**
 * @author maxjoker
 * @date 2022-04-10 11:14
 *
 * https://leetcode-cn.com/problems/kth-smallest-element-in-a-sorted-matrix/
 *
 * 378. 有序矩阵中第 K 小的元素
 * 给你一个 n x n 矩阵 matrix ，其中每行和每列元素均按升序排序，找到矩阵中第 k 小的元素。
 * 请注意，它是 排序后 的第 k 小元素，而不是第 k 个 不同 的元素。
 *
 * 你必须找到一个内存复杂度优于 O(n^2) 的解决方案。
 *
 *
 *
 * 示例 1：
 *
 * 输入：matrix = [[1,5,9],[10,11,13],[12,13,15]], k = 8
 * 输出：13
 * 解释：矩阵中的元素为 [1,5,9,10,11,12,13,13,15]，第 8 小元素是 13
 * 示例 2：
 *
 * 输入：matrix = [[-5]], k = 1
 * 输出：-5
 *
 *
 * 提示：
 *
 * n == matrix.length
 * n == matrix[i].length
 * 1 <= n <= 300
 * -109 <= matrix[i][j] <= 109
 * 题目数据 保证 matrix 中的所有行和列都按 非递减顺序 排列
 * 1 <= k <= n2
 *
 *
 * 进阶：
 *
 * 你能否用一个恒定的内存(即 O(1) 内存复杂度)来解决这个问题?
 * 你能在 O(n) 的时间复杂度下解决这个问题吗?这个方法对于面试来说可能太超前了，
 * 但是你会发现阅读这篇文章（ this paper ）很有趣。
 *
 */
public class KthSmallestElementInASortedMatrix {

    /**
     * 把矩阵变成一维数组，然后排序
     * 时间复杂度：O(n^2logN)
     * 空间复杂度：O(n^2)
     * @param matrix
     * @param k
     * @return
     */
    public static int kthSmallest(int[][] matrix, int k) {
        int len = matrix.length * matrix.length;
        int[] arr = new int[len];
        int count = 0;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                arr[count] = matrix[i][j];
                count++;
            }
        }

        quicksort(arr, 0, arr.length - 1);

        return arr[k - 1];
    }

    private static void quicksort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int randomIndex = left + new Random().nextInt(right - left + 1);
        swap(arr, left, randomIndex);

        int pivot = arr[left];
        int lt = left;
        int gt = right + 1;

        int i = left + 1;

        while (i < gt) {
            if (arr[i] < pivot) {
                lt++;
                swap(arr, i, lt);
                i++;
            } else if (arr[i] == pivot) {
                i++;
            } else {
                gt--;
                swap(arr, i, gt);
            }
        }

        swap(arr, left, lt);
        quicksort(arr, left, lt - 1);
        quicksort(arr, gt, right);
    }

    private static void swap(int[] arr, int idx1, int idx2) {
        if (idx1 == idx2) {
            return;
        }

        int temp = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = temp;
    }

    /**
     * 二分查找
     *
     * https://www.suanfa8.com/binary-search/solutions/find-integer/0378-kth-smallest-element-in-a-sorted-matrix/
     * https://leetcode-cn.com/problems/kth-smallest-element-in-a-sorted-matrix/solution/you-xu-ju-zhen-zhong-di-kxiao-de-yuan-su-by-leetco/
     *
     * 时间复杂度：O(nlog(right-left)) 二分查找次数为 log(right - left)，每次操作时间复杂度为 O(n)
     * 空间复杂度：O(1)
     *
     * @param matrix
     * @param k
     * @return
     */
    public static int kthSmallestByBinarySearch(int[][] matrix, int k) {
        int n = matrix.length;
        int left = matrix[0][0];
        int right = matrix[n-1][n-1];

        while (left < right) {
            int mid = left + (right - left) / 2;
            int count = lessEquals(matrix, mid);

            if (count < k) {
                // 如果数量少于 k，那么说明最终答案 大于 mid
                left = mid + 1;
            } else {
                // 如果数量不少于kk，那么说明最终答案 不大于 mid
                right = mid;
            }
        }

        return left;
    }

    /**
     * 计算小于等于mid元素的个数，从矩阵左下角开始
     *
     * 初始位置在 matrix[n - 1][0]（即左下角）；
     *
     * 设当前位置为 matrix[i][j]。若 matrix[i][j]≤mid，则将当前所在列的不大于 mid 的数的数量（即 i+1）累加到答案中，并向右移动，否则向上移动；
     *
     * 不断移动直到走出格子为止。
     * @param matrix
     * @param mid
     * @return
     */
    private static int lessEquals(int[][] matrix, int mid) {
        int n = matrix.length;
        int i = n - 1;
        int j = 0;
        int count = 0;

        while (i >= 0 && j < n) {
            if (matrix[i][j] <= mid) {
                count += i + 1;
                j++;
            } else {
                i--;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{-5, -4},{-5, -4}};
        System.out.println(kthSmallest(matrix, 2));
        System.out.println(kthSmallestByBinarySearch(matrix, 2));
    }
}
