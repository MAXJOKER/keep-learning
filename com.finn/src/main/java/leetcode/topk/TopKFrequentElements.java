package leetcode.topk;

import java.util.*;

/**
 *
 * @author maxjoker
 * @date 2022-03-13 23:52
 *
 * 347. 前 K 个高频元素
 * 给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。
 *
 *
 *
 * 示例 1:
 *
 * 输入: nums = [1,1,1,2,2,3], k = 2
 * 输出: [1,2]
 * 示例 2:
 *
 * 输入: nums = [1], k = 1
 * 输出: [1]
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 105
 * k 的取值范围是 [1, 数组中不相同的元素的个数]
 * 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的
 *
 *
 * 进阶：你所设计算法的时间复杂度 必须 优于 O(n log n) ，其中 n 是数组大小。
 *
 * https://leetcode-cn.com/problems/top-k-frequent-elements/
 */
public class TopKFrequentElements {
    /**
     * 时间复杂度：O(n + klogk)
     * 空间复杂度：O(n + k)
     * @param nums
     * @param k
     * @return
     */
    public static int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }

        List<Integer> list = new ArrayList<>(map.keySet());
        list.sort((a, b) -> map.get(b) - map.get(a));

        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = list.get(i);
        }

        return result;

    }

    /**
     * 堆排序
     * 时间复杂度：O(nlogk)
     * 空间复杂度：O(n)
     * @param nums
     * @param k
     * @return
     */
    public static int[] topKFrequentByHeap(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }

        // int[] 的第一个元素代表数组的值，第二个元素代表了该值出现的次数
        PriorityQueue<int[]> queue = new PriorityQueue<int[]>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (queue.size() == k) {
                if (queue.peek()[1] < entry.getValue()) {
                    queue.poll();
                    queue.offer(new int[]{entry.getKey(), entry.getValue()});
                }
            } else {
                queue.offer(new int[]{entry.getKey(), entry.getValue()});
            }
        }

        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = queue.poll()[0];
        }

        return result;
    }

    /**
     * 快排
     * 时间复杂度：平均O(n)
     * 空间复杂度：O(n)
     *
     *
     * 记录每个数字出现的次数
     * 对每个数及对应次数通过快排进行排序，返回前k个
     * 快排步骤
     * i. 随机选一个中间值作为基准，并把它挪到最左侧
     * ii. 从第2个元素开始遍历，遍历过程中，要把比基准大的挪到左边，比基准小的挪到右边
     * iii. i 指向比基准大的元素，只要 j 指向的元素比基准小，就把 j 位置的元素和i后面一个位置的元素对调
     * 并且i后移一位，这样 i 指向的元素以及i之前的元素都是比基准大的元素（基准本身除外）
     * iv. j遍历到末尾后，此时i指向的就是排序后的列表中比基准大的最后一个元素，将该元素和基准对调，即
     * num_cnt[low], num_cnt[i] = num_cnt[i], num_cnt[low]
     * 这样排序后的列表就是在i位置前的都比 i 大，i 位置后的都比 i 小
     * 接下来就是分治部分了
     * i. 如果 i == k - 1，也就是i及之前的元素恰好组成了我们想要的topK，直接返回前k个元素
     * ii. 如果 i > k - 1, 也就是i及之前的元素组成了top(K+N)，要对前 i 个元素再进行一次快排，从top(K+N)里选出topK
     * findTopK(num_cnt, k, low, i - 1)
     * iii. 如果 i < k - 1，也就是i及之前的元素组成了top(K-N)，要对i之后的元素再进行快排，在之后的元素中选出topN
     * findTopK(num_cnt, k, i + 1, high)
     * 返回快排结果中的数字部分
     *
     * @param nums
     * @param k
     * @return
     */
    public static int[] topKFrequentByQuickSort(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : nums) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        List<int[]> list = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            list.add(new int[]{entry.getKey(), entry.getValue()});
        }

        List<int[]> result = findTopKByQuickSort(list, k, 0, map.size() - 1);
        int[] res = new int[k];

        for (int i = 0; i < k; i++) {
            res[i] = result.get(i)[0];
        }

        return res;
    }

    private static List<int[]> findTopKByQuickSort(List<int[]> list, int k, int left, int right) {
        int randomIndex = left + new Random().nextInt(right - left + 1);
        Collections.swap(list, left, randomIndex);

        int pivot = list.get(left)[1];
        int lt = left;

        for (int j = left + 1; j <= right; j++) {
            if (list.get(j)[1] > pivot) {
                lt++;
                Collections.swap(list, lt, j);
            }
        }

        Collections.swap(list, lt, left);

        if (lt == k - 1) {
            return list.subList(0, k);
        } else if (lt > k - 1) {
           return findTopKByQuickSort(list, k, left, lt - 1);
        } else {
            return findTopKByQuickSort(list, k, lt + 1, right);
        }
    }

    /**
     * 桶排序
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     *
     * @param nums
     * @param k
     * @return
     */
    public static int[] findTopKByBucketSort(int[] nums, int k) {
        int len = nums.length;
        // 使用字典，统计每个元素出现的次数，元素为键，元素出现的次数为值
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        //桶排序
        //将频率作为数组下标，对于出现频率不同的数字集合，存入对应的数组下标
        List<Integer>[] list = new List[len + 1];
        for(int key : map.keySet()) {
            // 获取出现的次数作为下标
            int i = map.get(key);
            if (list[i] == null) {
                list[i] = new ArrayList<>();
            }

            list[i].add(key);
        }

        List<Integer> res = new ArrayList<>();
        // 倒序遍历数组获取出现顺序从大到小的排列
        for (int i = list.length - 1; i >= 0 && res.size() < k; i--) {
            if (list[i] == null) {
                continue;
            }

            res.addAll(list[i]);
        }

        return res.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,1,1,2,2,3,3,3,3};
        int[] result = topKFrequent(nums, 2);
        int[] result2 = topKFrequentByHeap(nums, 3);
        int[] result3 = topKFrequentByQuickSort(nums, 1);
        int[] result4 = findTopKByBucketSort(nums, 2);
        for (int i : result) {
            System.out.println(i);
        }

        for (int i : result2) {
            System.out.println(i);
        }

        for (int i : result3) {
            System.out.println(i);
        }

        for (int i : result4) {
            System.out.println(i);
        }
    }
}
