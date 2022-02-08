package leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @date 2022-02-07 18:51
 * 60. 排列序列
 * 给出集合 [1,2,3,...,n]，其所有元素共有 n! 种排列。
 *
 * 按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下：
 *
 * "123"
 * "132"
 * "213"
 * "231"
 * "312"
 * "321"
 * 给定 n 和 k，返回第 k 个排列。
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 3, k = 3
 * 输出："213"
 * 示例 2：
 *
 * 输入：n = 4, k = 9
 * 输出："2314"
 * 示例 3：
 *
 * 输入：n = 3, k = 1
 * 输出："123"
 *
 *
 * 提示：
 *
 * 1 <= n <= 9
 * 1 <= k <= n!
 *
 * https://leetcode-cn.com/problems/permutation-sequence/
 */
public class PermutationSequence {
    public static String getPermutation(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();

        Deque<Integer> path = new ArrayDeque<>();
        boolean[] used = new boolean[n+1];
        dfs(n, k, used, path, result);

        List<Integer> res = result.get(k - 1);
        StringBuilder sb = new StringBuilder();
        for (int i : res) {
            sb.append(i);
        }

        return sb.toString();
    }

    /**
     * 效率不高，耗时很高
     * @param n
     * @param k
     * @param used
     * @param path
     * @param result
     */
    private static void dfs(int n, int k, boolean[] used, Deque<Integer> path, List<List<Integer>> result) {
        if (path.size() == n) {
            result.add(new ArrayList<>(path));
            return;
        }

        if (result.size() == k) {
            return;
        }

        for (int i = 1; i <= n; i++) {
            if (used[i]) {
                continue;
            }

            used[i] = true;
            path.addLast(i);
            dfs(n, k, used, path, result);
            used[i] = false;
            path.removeLast();
        }
    }

    /**
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(n)
     * @param n
     * @param k
     * @return
     */
    public static String getPermutation2(int n, int k) {
        final int[] arr = new int[]{1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880};
        boolean[] used = new boolean[n+1];
        StringBuilder permutation = new StringBuilder();

        for (int i = n - 1; i >= 0; i--) {
            int cnt = arr[i];
            for (int j = 1; j <= n; j++) {
                if (used[j]) {
                    continue;
                }

                // 剪枝
                // 题解：https://leetcode-cn.com/problems/permutation-sequence/solution/hui-su-jian-zhi-python-dai-ma-java-dai-ma-by-liwei/
                if (k > cnt) {
                    k -= cnt;
                    continue;
                }

                used[j] = true;
                permutation.append(j);
                break;
            }
        }

        return permutation.toString();
    }

    public static void main(String[] args) {
        System.out.println(getPermutation2(4,13));
    }
}
