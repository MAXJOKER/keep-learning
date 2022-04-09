package leetcode.binarysearch;

/**
 * @author maxjoker
 * @date 2022-04-09 14:43
 *
 * https://leetcode-cn.com/problems/guess-number-higher-or-lower/
 *
 * 374. 猜数字大小
 * 猜数字游戏的规则如下：
 *
 * 每轮游戏，我都会从 1 到 n 随机选择一个数字。 请你猜选出的是哪个数字。
 * 如果你猜错了，我会告诉你，你猜测的数字比我选出的数字是大了还是小了。
 * 你可以通过调用一个预先定义好的接口 int guess(int num) 来获取猜测结果，返回值一共有 3 种可能的情况（-1，1 或 0）：
 *
 * -1：我选出的数字比你猜的数字小 pick < num
 * 1：我选出的数字比你猜的数字大 pick > num
 * 0：我选出的数字和你猜的数字一样。恭喜！你猜对了！pick == num
 * 返回我选出的数字。
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 10, pick = 6
 * 输出：6
 * 示例 2：
 *
 * 输入：n = 1, pick = 1
 * 输出：1
 * 示例 3：
 *
 * 输入：n = 2, pick = 1
 * 输出：1
 * 示例 4：
 *
 * 输入：n = 2, pick = 2
 * 输出：2
 *
 *
 * 提示：
 *
 * 1 <= n <= 231 - 1
 * 1 <= pick <= n
 *
 */
public class GuessNumberHigherOrLower extends GuessGame{
    /**
     * 二分法
     * 时间复杂度：O(logN)
     * 空间复杂度：O(1)
     * @param n
     * @return
     */
    public static int guessNumber(int n) {
        // 注意 left 的取值，pick范围是 [1, n]
        int left = 1;
        int right = n;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int result = guess(mid);

            if (result == 0) {
                return mid;
            } else if (result == 1) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }

    public static int guessNumber2(int n) {
        int left = 1;
        int right = n;

        while (left < right) {
            // 无符号右移
            // left + right 在发生整型溢出以后，会变成负数，此时如果除以 2 ，
            // mid 是一个负数，但是经过无符号右移，可以得到在不溢出的情况下正确的结果。
            // 右移运算符 >> 在右移时，丢弃右边指定位数，左边补上符号位；
            // 无符号右移运算符 >>> 在右移时，丢弃右边指定位数，左边补上 0，
            // 也就是说，对于正数来说，二者一样，而负数通过 >>> 后能变成正数。
            int mid = (left + right + 1) >>> 1;
            if (guess(mid) == -1) {
                right = mid - 1;
            } else  {
                left = mid;
            }
        }

        return left;
    }

    public static void main(String[] args) {
        GuessGame guessGame = new GuessGame(6);
        int n = 10;
        System.out.println(guessNumber(n));
        System.out.println(guessNumber2(n));
    }
}

class GuessGame {
    public static int pick;

    public GuessGame() {

    }

    public GuessGame(int pick) {
        this.pick = pick;
    }

    public static int guess(int num) {
        return Integer.compare(pick, num);
    }
}
