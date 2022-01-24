package leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

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
