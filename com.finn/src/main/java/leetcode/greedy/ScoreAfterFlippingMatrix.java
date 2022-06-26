package leetcode.greedy;

/**
 * @author maxjoker
 * @date 2022-06-26 17:47
 *
 * https://leetcode.cn/problems/score-after-flipping-matrix/
 *
 * 861. 翻转矩阵后的得分
 * 有一个二维矩阵 A 其中每个元素的值为 0 或 1 。
 *
 * 移动是指选择任一行或列，并转换该行或列中的每一个值：将所有 0 都更改为 1，将所有 1 都更改为 0。
 *
 * 在做出任意次数的移动后，将该矩阵的每一行都按照二进制数来解释，矩阵的得分就是这些数字的总和。
 *
 * 返回尽可能高的分数。
 *
 *
 *
 * 示例：
 *
 * 输入：[[0,0,1,1],[1,0,1,0],[1,1,0,0]]
 * 输出：39
 * 解释：
 * 转换为 [[1,1,1,1],[1,0,0,1],[1,1,1,1]]
 * 0b1111 + 0b1001 + 0b1111 = 15 + 9 + 15 = 39
 *
 *
 * 提示：
 *
 * 1 <= A.length <= 20
 * 1 <= A[0].length <= 20
 * A[i][j] 是 0 或 1
 *
 */
public class ScoreAfterFlippingMatrix {
    /**
     * 时间复杂度：O(mn)
     * 空间复杂度：O(1)
     *
     * 1、确保最左边的列都为1（此时进行 行转换）
     * 2、确保每一列中的1尽量多，如果0比1多，进行 列转换
     * 3、计算分数时，不用额外空间保存计算，直接原地计算
     *      j列中有 k 个 1，则该列分数为 k * (1 << (n - j - 1))
     *
     * @param grid
     * @return
     */
    public static int matrixScore(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // 使最左列都为1
        int res = m * (1 << (n - 1));

        for (int j = 1; j < n; j++) {
            int nOnes = 0;
            for (int i = 0; i < m; i++) {
                // 考虑行翻转情况
                if (grid[i][0] == 1) {
                    nOnes += grid[i][j];
                } else {
                    nOnes += 1 - grid[i][j];
                }
            }

            // nOnes是一列中1的数量，如果是0的数量多(m-nOnes），则翻转列
            nOnes = Math.max(nOnes, m - nOnes);
            res += nOnes * (1 << n - j - 1);
        }

        return res;
    }

    public static void main(String[] args) {
        int[][] grid = new int[][]{{0, 0, 1, 1}, {1,0,1,0},{1,1,0,0}};
        System.out.println(matrixScore(grid));
    }
}
