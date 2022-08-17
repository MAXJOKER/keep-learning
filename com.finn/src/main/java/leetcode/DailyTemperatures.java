package leetcode;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @author maxjoker
 * @date 2022-08-17 22:03
 *
 * https://leetcode.cn/problems/daily-temperatures/
 *
 * 739. 每日温度
 * 给定一个整数数组 temperatures ，表示每天的温度，返回一个数组 answer ，
 * 其中 answer[i] 是指对于第 i 天，下一个更高温度出现在几天后。
 * 如果气温在这之后都不会升高，请在该位置用 0 来代替。
 *
 *
 *
 * 示例 1:
 *
 * 输入: temperatures = [73,74,75,71,69,72,76,73]
 * 输出: [1,1,4,2,1,1,0,0]
 * 示例 2:
 *
 * 输入: temperatures = [30,40,50,60]
 * 输出: [1,1,1,0]
 * 示例 3:
 *
 * 输入: temperatures = [30,60,90]
 * 输出: [1,1,0]
 *
 *
 * 提示：
 *
 * 1 <= temperatures.length <= 10^5
 * 30 <= temperatures[i] <= 100
 *
 */
public class DailyTemperatures {
    /**
     * 暴力解法
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(n)
     * @param temperatures
     * @return
     */
    public static int[] dailyTemperatures(int[] temperatures) {
        int[] result = new int[temperatures.length];
        Arrays.fill(result, 0);

        for (int i = 0; i < temperatures.length; i++) {
            for (int j = i + 1;j < temperatures.length; j++) {
                if (temperatures[j] > temperatures[i]) {
                    result[i] = j - i;
                    break;
                }
            }
        }

        return result;
    }

    /**
     * 单调栈
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     * @param temperature
     * @return
     */
    public static int[] solution(int[] temperature) {
        int length = temperature.length;
        int[] ans = new int[length];

        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i < length; i++) {
            int temp = temperature[i];
            while (!stack.isEmpty() && temp > temperature[stack.peek()]) {
                int prev = stack.pop();
                ans[prev] = i - prev;
            }

            stack.push(i);
        }

        return ans;
    }

    public static void main(String[] args) {
//        int[] t = new int[]{73,74,75,71,69,72,76,73};
        int[] t = new int[]{30, 40, 50, 60};
        int[] result = dailyTemperatures(t);

        for (int i : result) {
            System.out.println(i);
        }
    }
}
