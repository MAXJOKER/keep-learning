package leetcode.binarysearch;

/**
 * @author maxjoker
 * @date 2022-04-10 14:20
 *
 * https://leetcode-cn.com/problems/valid-perfect-square/
 *
 * 367. 有效的完全平方数
 * 给定一个 正整数 num ，编写一个函数，如果 num 是一个完全平方数，则返回 true ，否则返回 false 。
 *
 * 进阶：不要 使用任何内置的库函数，如  sqrt 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：num = 16
 * 输出：true
 * 示例 2：
 *
 * 输入：num = 14
 * 输出：false
 *
 *
 * 提示：
 *
 * 1 <= num <= 2^31 - 1
 *
 */
public class ValidPerfectSquare {
    /**
     * 二分法
     * 时间复杂度：O(logN)
     * 空间复杂度：O(1)
     * @param num
     * @return
     */
    public static boolean isPerfectSquare(int num) {
        if (num == 0 || num == 1) {
            return true;
        }

        int left = 0;
        int right = num / 2;

        while (left < right) {
            int mid = left + (right - left + 1) / 2;

            if (num / mid < mid) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }

        return num / left == left && num % left == 0;
    }

    public static void main(String[] args) {
        int num = 42;
        System.out.println(isPerfectSquare(num));
    }
}
