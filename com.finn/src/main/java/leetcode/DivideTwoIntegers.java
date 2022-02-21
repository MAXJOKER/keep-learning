package leetcode;

/**
 * @author maxjoker
 * @date 2022-02-17 23:04
 *
 * 29. 两数相除
 * 给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
 *
 * 返回被除数 dividend 除以除数 divisor 得到的商。
 *
 * 整数除法的结果应当截去（truncate）其小数部分，例如：truncate(8.345) = 8 以及 truncate(-2.7335) = -2
 *
 *
 *
 * 示例 1:
 *
 * 输入: dividend = 10, divisor = 3
 * 输出: 3
 * 解释: 10/3 = truncate(3.33333..) = truncate(3) = 3
 * 示例 2:
 *
 * 输入: dividend = 7, divisor = -3
 * 输出: -2
 * 解释: 7/-3 = truncate(-2.33333..) = -2
 *
 * https://leetcode-cn.com/problems/divide-two-integers/
 */
public class DivideTwoIntegers {
    /**
     * 暴力解法
     * @param dividend
     * @param divisor
     * @return
     */
    public static int divide(int dividend, int divisor) {
        int res = 0;

        if (dividend == 0) {
            return res;
        }

        if (divisor == 1) {
            return dividend;
        }

        if (divisor == -1) {
            if (dividend > Integer.MIN_VALUE) {
                return -dividend;
            }

            return Integer.MAX_VALUE;
        }

        int sign;
        if ((dividend < 0 && divisor < 0) || (dividend > 0 && divisor > 0)) {
            sign = 1;
        } else {
            sign = -1;
        }

        long a = dividend;
        long b = divisor;

        a = a > 0 ? a : -a;
        b = b > 0 ? b : -b;

        while (a >= b) {
            a = a - b;
            res++;
        }

        if (sign > 0) {
            return Math.min(res, Integer.MAX_VALUE);
        }

        return -res;
    }

    public static void main(String[] args) {
        System.out.println(divide(-2147483648, 2));
    }
}
