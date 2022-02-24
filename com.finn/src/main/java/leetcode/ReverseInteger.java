package leetcode;

/**
 * author zhangjunhui
 * date 2022-01-01 13:07
 * https://leetcode-cn.com/problems/reverse-integer/
 * 7. 整数反转
 * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
 *
 * 如果反转后整数超过 32 位的有符号整数的范围 [−231,  231 − 1] ，就返回 0。
 *
 * 假设环境不允许存储 64 位整数（有符号或无符号）。
 *
 *
 * 示例 1：
 *
 * 输入：x = 123
 * 输出：321
 * 示例 2：
 *
 * 输入：x = -123
 * 输出：-321
 * 示例 3：
 *
 * 输入：x = 120
 * 输出：21
 * 示例 4：
 *
 * 输入：x = 0
 * 输出：0
 */
public class ReverseInteger {
    public static int reverse(int x) {
        int y = 0;
        while (x != 0) {
            int tmp = x % 10;
            // 判断简化，因为x本身会被int限制，当x为正数并且位数和Integer.MAX_VALUE的位数相等时首位最大只能为2，
            // 所以逆转后不会出现res = Integer.MAX_VALUE / 10 && tmp > 2的情况，自然也不需要判断res==214748364 && tmp>7了
            // 反之负数情况也一样
            if (y > Integer.MAX_VALUE / 10 || y < Integer.MIN_VALUE / 10) {
                return 0;
            }

            y = y * 10 + tmp;
            x = x / 10;
        }

        return y;
    }

    public static void main(String[] args) {
        int x = 123456890;
        System.out.println(reverse(x));
    }
}
