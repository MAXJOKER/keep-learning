package leetcode.binarysearch;

/**
 * 69. Sqrt(x)
 * 给你一个非负整数 x ，计算并返回 x 的 算术平方根 。
 *
 * 由于返回类型是整数，结果只保留 整数部分 ，小数部分将被 舍去 。
 *
 * 注意：不允许使用任何内置指数函数和算符，例如 pow(x, 0.5) 或者 x ** 0.5 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：x = 4
 * 输出：2
 * 示例 2：
 *
 * 输入：x = 8
 * 输出：2
 * 解释：8 的算术平方根是 2.82842..., 由于返回类型是整数，小数部分将被舍去。
 *
 * https://leetcode-cn.com/problems/sqrtx/
 */
public class Sqrtx {

    /**
     * 暴力解法
     * 提交超时了
     * @param x
     * @return
     */
    public static int mySqrt(int x) {
        if (x == 0) {
            return 0;
        }

        if (x == 1 || x == 2) {
            return 1;
        }

        int middle = x / 2;
        int result = 0;
        for (int i = 1; i <= middle; i++) {
            long a = (long) i * i;
            if (a > 0 && a <= x && a < Integer.MAX_VALUE) {
                result = i;
            }
        }

        return result;
    }

    /**
     * 二分查找
     * @param x
     * @return
     */
    public static int mySqrt2(int x) {
        int left = 0;
        int right = x;
        int result = 0;

        while (left <= right) {
            int middle = (left + right) / 2;
            if ((long) middle * middle <= x) {
                result = middle;
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }

        return result;
    }

    public static int sqrt2(int x) {
        if (x == 0) {
            return 0;
        }

        if (x == 1) {
            return 1;
        }

        int left = 0;
        int right = x / 2;

        while (left < right) {
            // 如果mid向下取整，当区间只有2个数时，mid的值一直等于left，进入[mid, right]分支会出现死循环
            int mid = left + (right - left + 1) / 2;

            // mid * mid > x 的话，sqrt值绝不会是mid，值是向下取整的
            if (x / mid < mid) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }

        return left;
    }

    public static void main(String[] args) {
        int x = 8;
        System.out.println(mySqrt(x));
        System.out.println(mySqrt2(x));
        System.out.println(sqrt2(x));
    }
}
