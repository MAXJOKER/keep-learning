package leetcode;


import java.util.Arrays;

/**
 * @author zhangjunhui
 * @date 2022-01-03 21:16
 */
public class MedianOfTwoSortedArrays {
    /**
     * 暴力解法
     * @param nums1
     * @param nums2
     * @return
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] nums3 = new int[nums1.length + nums2.length];
        System.arraycopy(nums1, 0, nums3, 0, nums1.length);
        System.arraycopy(nums2, 0, nums3, nums1.length, nums2.length);
        Arrays.sort(nums3);

        double middle = 0.0;
        if (nums3.length % 2 != 0) {
            middle = (double) nums3[nums3.length / 2];
        } else {
            middle = (double) (nums3[nums3.length / 2] + nums3[nums3.length / 2 - 1]) / 2;
        }

        return middle;
    }

    public static void main(String[] args) {
        int[] nums1 = new int[0];
        int[] nums2 = new int[]{2};
        System.out.println(findMedianSortedArrays(nums1, nums2));
    }
}
