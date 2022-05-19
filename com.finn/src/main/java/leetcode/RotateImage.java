package leetcode;

import java.util.Arrays;
import java.util.List;

/**
 * @author maxjoker
 * @date 2022-05-19 10:18
 *
 * https://leetcode.cn/problems/rotate-image/
 * 48. 旋转图像
 * 给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
 *
 * 你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
 *
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[[7,4,1],[8,5,2],[9,6,3]]
 *
 * 提示：
 *
 * n == matrix.length == matrix[i].length
 * 1 <= n <= 20
 * -1000 <= matrix[i][j] <= 1000
 *
 * newMatrix[col][n - row - 1] = matrix[row][col]
 *
 * 题解：https://leetcode.cn/problems/rotate-image/solution/xuan-zhuan-tu-xiang-by-leetcode-solution-vu3m/
 *
 */
public class RotateImage {
    /**
     * 使用了辅助数组
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(n^2)
     * @param matrix
     */
    public static void rotate(int[][] matrix) {
        int n = matrix.length;

        int[][] newMatrix = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                newMatrix[j][n - i - 1] = matrix[i][j];
            }
        }

        for (int i = 0; i < n; i++) {
            System.arraycopy(newMatrix[i], 0, matrix[i], 0, n);
        }
    }

    /**
     * 原地旋转
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(1)
     *
     * 动手画图好理解一点
     *
     * @param matrix
     */
    public static void rotate2(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n / 2; ++i) {
            for (int j = 0; j < (n + 1) / 2; ++j) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - j - 1][i];
                matrix[n - j - 1][i] = matrix[n - i - 1][n - j - 1];
                matrix[n - i - 1][n - j - 1] = matrix[j][n - i - 1];
                matrix[j][n - i - 1] = temp;
            }
        }

        System.out.println(matrix);
    }

    /**
     * 水平翻转 然后 对角线互换
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(1)
     * @param matrix
     */
    public static void roll(int[][] matrix) {
        int n = matrix.length;
        // 翻转
        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - i - 1][j];
                matrix[n - i - 1][j] = temp;
            }
        }

        // 对角线互换
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        System.out.println(matrix);
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
//        rotate(matrix);
//        rotate2(matrix);
        roll(matrix);
    }
}
