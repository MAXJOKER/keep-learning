package leetcode;

/**
 * @author maxjoker
 * @date 2022-05-18 19:58
 *
 * https://leetcode.cn/problems/search-a-2d-matrix-ii/
 * 240. 搜索二维矩阵 II
 * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：
 *
 * 每行的元素从左到右升序排列。
 * 每列的元素从上到下升序排列。
 *
 * 示例1：https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2020/11/25/searchgrid2.jpg
 *
 * 输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 5
 * 输出：true
 *
 * 示例2：https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2020/11/25/searchgrid.jpg
 * 输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 20
 * 输出：false
 *
 * 提示：
 *
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= n, m <= 300
 * -10^9 <= matrix[i][j] <= 10^9
 * 每行的所有元素从左到右升序排列
 * 每列的所有元素从上到下升序排列
 * -10^9 <= target <= 10^9
 *
 * 做题总结：
 * 1、考虑暴力解法
 * 2、涉及查找，想想二分法
 *
 */
public class SearchMatrix2 {
    /**
     * 暴力法
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(1)
     * @param matrix
     * @param target
     * @return
     */
    public static boolean searchMatrix(int[][] matrix, int target) {
        for (int[] rows : matrix) {
            for (int element : rows) {
                if (element == target) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Z形查找
     * 时间复杂度：O(m+n)
     * 空间复杂度：O(1)
     * @param matrix
     * @param target
     * @return
     */
    public static boolean searchMatrix2(int[][] matrix, int target) {
        int x = matrix.length - 1;
        int y = 0;
        int yLen = matrix[0].length - 1;

        while (x >= 0 && y <= yLen) {
            int temp = matrix[x][y];

            if (target == temp) {
                return true;
            }

            if (target > temp) {
                y++;
            } else {
                x--;
            }
        }

        return false;
    }

    /**
     * 二分查找
     * 时间复杂度：O(mlogn), 每一行使用二分查找的时间复杂度为 logn，共有m行
     * 空间复杂度：O(1)
     * @param matrix
     * @param target
     * @return
     */
    public static boolean searchMatrix3(int[][] matrix, int target) {
        for (int[] rows : matrix) {
            boolean res = binarySearch(rows, target);
            if (res) {
                return true;
            }
        }

        return false;
    }

    private static boolean binarySearch(int[] rows, int target) {
        int len = rows.length;

        int left = 0;
        int right = len - 1;

        while (left <= right) {
            int mid = (right + left + 1) >> 1;
            if (rows[mid] > target) {
                right = mid - 1;
            } else if (rows[mid] < target) {
                left = mid + 1;
            } else {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
//        int[][] matrix = new int[][]{{1,4,7,11,15},{2,5,8,12,19},{3,6,9,16,22},{10,13,14,17,24},{18,21,23,26,30}};
        int[][] matrix = new int[][]{{-5}};
        int target = -5;
        System.out.println(searchMatrix(matrix, target));
        System.out.println(searchMatrix2(matrix, target));
        System.out.println(searchMatrix3(matrix, target));
    }
}
