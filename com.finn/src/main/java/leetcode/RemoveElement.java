package leetcode;

/**
 * @author maxjoker
 * @date 2022-03-37 18:13
 *
 * https://leetcode-cn.com/problems/remove-element/
 *
 */
public class RemoveElement {
    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     * @param nums
     * @param val
     * @return
     */
    public static int removeElement(int[] nums, int val) {
        int len = nums.length;
        if (len == 0) {
            return 0;
        }

        int idx = 0;
        for (int i = 0; i < len; i++) {
            if (nums[i] != val) {
                nums[idx] = nums[i];
                idx++;
            }
        }

        return idx;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4, 5, 1, 1};
        System.out.println(removeElement(nums, 1));
    }
}
