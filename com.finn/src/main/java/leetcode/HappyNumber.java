package leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * 202. 快乐数
 * 编写一个算法来判断一个数 n 是不是快乐数。
 *
 * 「快乐数」 定义为：
 *
 * 对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和。
 * 然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。
 * 如果这个过程 结果为 1，那么这个数就是快乐数。
 * 如果 n 是 快乐数 就返回 true ；不是，则返回 false 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 19
 * 输出：true
 * 解释：
 * 1^2 + 9^2 = 82
 * 8^2 + 2^2 = 68
 * 6^2 + 8^2 = 100
 * 1^2 + 0^2 + 0^2 = 1
 *
 * https://leetcode-cn.com/problems/happy-number/
 *
 */
public class HappyNumber {
    private static int getNext(int n) {
        int sum = 0;
        while (n > 0) {
            int d = n % 10;
            n = n / 10;
            sum += d * d;
        }

        return sum;
    }

    /**
     * 用哈希集合检测循环
     * 时间复杂度：O(logn)
     * 空间复杂度：O(logn)
     * @param n
     * @return
     */
    public static boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();
        while (n != 1 && !set.contains(n)) {
            set.add(n);
            n = getNext(n);
        }

        return n == 1;
    }

    /**
     * 快慢指针
     * 时间复杂度：O(logn)
     * 空间复杂度：O(1)
     * @param n
     * @return
     */
    public static boolean isHappy2(int n) {
        int slowRunner = 0;
        int fastRunner = getNext(n);

        while (n != 1 && slowRunner != fastRunner) {
            slowRunner = getNext(n);
            fastRunner = getNext(getNext(n));
        }

        return fastRunner == 1;
    }

    public static void main(String[] args) {
        int n = 999;
        System.out.println(isHappy(n));
    }
}
