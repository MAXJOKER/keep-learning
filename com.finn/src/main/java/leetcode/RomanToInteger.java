package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangjunhui
 * @date 2021-12-29 09:41
 * 13. 罗马数字转整数
 * https://leetcode-cn.com/problems/roman-to-integer/
 * 本质：小值放在大值左边，做减法，否则做加法
 */
public class RomanToInteger {

    public static final Map<String, Integer> map = new HashMap<String, Integer>();

    static {
        map.put("I", 1);
        map.put("V", 5);
        map.put("X", 10);
        map.put("L", 50);
        map.put("C", 100);
        map.put("D", 500);
        map.put("M", 1000);
    }

    /**
     * 暴力解法
     * @param s
     * @return
     */
    public static int romanToInt(String s) {
        char[] array = s.toCharArray();
        if (array.length == 0) {
            return 0;
        }

        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            String current = String.valueOf(array[i]);
            boolean hasNext = i + 1 < array.length;

            if (hasNext) {
                String next = String.valueOf(array[i + 1]);
                if ("I".equals(current)) {
                    if ("V".equals(next)) {
                        sum += 4;
                        i++;
                        continue;
                    }

                    if ("X".equals(next)) {
                        sum += 9;
                        i++;
                        continue;
                    }

                    if ("I".equals(next)) {
                        sum += 2;
                        i++;
                        continue;
                    }
                }

                if ("X".equals(current)) {
                    if ("L".equals(next)) {
                        sum += 40;
                        i++;
                        continue;
                    }

                    if ("C".equals(next)) {
                        sum += 90;
                        i++;
                        continue;
                    }

                    if ("X".equals(next)) {
                        sum += 20;
                        i++;
                        continue;
                    }
                }

                if ("C".equals(current)) {
                    if ("D".equals(next)) {
                        sum += 400;
                        i++;
                        continue;
                    }

                    if ("M".equals(next)) {
                        sum += 900;
                        i++;
                        continue;
                    }

                    if ("C".equals(next)) {
                        sum += 200;
                        i++;
                        continue;
                    }
                }

                sum += map.get(current);
                continue;
            }

            sum += map.get(current);
        }

        return sum;
    }

    /**
     * 简单解法，小值位于大值右边，相减，否则相加
     * @param s
     * @return
     */
    public static int romanToInt2(String s) {
        int preNum = getValue(s.charAt(0));
        int sum = 0;
        for (int i = 1; i < s.length(); i++) {
            int current = getValue(s.charAt(i));
            if (preNum < current) {
                sum -= preNum;
            } else {
                sum += preNum;
            }

            preNum = current;
        }

        // 最后一位没得对比，要加上
        sum += preNum;

        return sum;
    }

    public static int getValue(char c) {
        switch (c) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                return 0;
        }
    }

    public static void main (String[] args) {
        String s = "IV";
//        int sum = romanToInt(s);
        int sum = romanToInt2(s);
        System.out.println(sum);
    }
}
