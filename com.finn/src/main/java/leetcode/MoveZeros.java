package leetcode;

/**
 * 283. 移动零
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 *
 * 示例:
 *
 * 输入: [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 * 说明:
 *
 * 必须在原数组上操作，不能拷贝额外的数组。
 * 尽量减少操作次数。
 *
 * https://leetcode-cn.com/problems/move-zeroes/
 */
public class MoveZeros {
    /**
     * 把非0元素往前移，然后再把0给补上
     * @param nums
     */
    public static void moveZeros(int[] nums) {
        int ans = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[ans++] = nums[i];
            }
        }

        while (ans < nums.length) {
            nums[ans++] = 0;
        }
    }

    /**
     * 双指针解法
     * @param nums
     */
    public static void moveZeros2(int[] nums) {
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                // 如果 i > j，说明 j 位置肯定是0
                if (i > j) {
                    nums[j] = nums[i];
                    nums[i] = 0;
                }
                // 只有 num[i] != 0 时，j才会递增
                j++;
            }
        }
    }

    public static void main(String[] args) {
        int[] a = {0,1,0,3,12};
        moveZeros2(a);
        for (int i : a) {
            System.out.println(i);
        }
    }
}
