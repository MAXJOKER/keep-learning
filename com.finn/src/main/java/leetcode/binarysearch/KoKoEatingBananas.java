package leetcode.binarysearch;

/**
 * @author maxjoker
 * @date 2022-04-14 21:34
 *
 * https://leetcode-cn.com/problems/koko-eating-bananas/
 *
 * 875. 爱吃香蕉的珂珂
 * 珂珂喜欢吃香蕉。这里有 N 堆香蕉，第 i 堆中有 piles[i] 根香蕉。警卫已经离开了，将在 H 小时后回来。
 *
 * 珂珂可以决定她吃香蕉的速度 K （单位：根/小时）。每个小时，她将会选择一堆香蕉，从中吃掉 K 根。如果这堆香蕉少于 K 根，她将吃掉这堆的所有香蕉，然后这一小时内不会再吃更多的香蕉。
 *
 * 珂珂喜欢慢慢吃，但仍然想在警卫回来前吃掉所有的香蕉。
 *
 * 返回她可以在 H 小时内吃掉所有香蕉的最小速度 K（K 为整数）。
 *
 *
 *
 * 示例 1：
 *
 * 输入: piles = [3,6,7,11], H = 8
 * 输出: 4
 * 示例 2：
 *
 * 输入: piles = [30,11,23,4,20], H = 5
 * 输出: 30
 * 示例 3：
 *
 * 输入: piles = [30,11,23,4,20], H = 6
 * 输出: 23
 *
 *
 * 提示：
 *
 * 1 <= piles.length <= 10^4
 * piles.length <= H <= 10^9
 * 1 <= piles[i] <= 10^9
 *
 */
public class KoKoEatingBananas {
    public static int minEatingSpeed(int[] piles, int h) {
        // 最小为1
        int left = 1;
        int right = 1;

        for (int pile : piles) {
            right = Math.max(right, pile);
        }

        while (left < right) {
            int mid = left + (right - left) / 2;
            int sum = countSum(piles, mid);

            if (sum > h) {
                // 耗时太多，速度太慢，mid取太小了
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    /**
     * 需要的小时数
     * @param piles
     * @param speed
     * @return
     */
    private static int countSum(int[] piles, int speed) {

        int sum = 0;

        for (int pile : piles) {
            // 写成 sum += pile / speed; 是下取整，为了把下取整改成上取整（根据题目要求需要上取整）。
            // 因此，我们需要在分子加上「分母的值 - 1」，这样就可以改变默认 / 下取整的行为。
            // 如果不能被整除，则时间 + 1
            sum += (pile + speed - 1) / speed;
        }

        return sum;
    }

    public static void main(String[] args) {
        int[] piles = new int[]{312884470};
        System.out.println(minEatingSpeed(piles, 968709470));
    }
}
