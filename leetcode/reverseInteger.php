<?php
/**
 * @author junhui.zhang
 * @date 2019-04-14 00:51
 * @desc 整数反转
 */
/**
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 * 示例 1:
 *
 * 输入: 123
 * 输出: 321
 * 示例 2:
 * 
 * 输入: -123
 * 输出: -321
 * 示例 3:
 *
 * 输入: 120
 * 输出: 21
 * 注意:
 *
 * 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−231,  231 − 1]。
 * 请根据这个假设，如果反转后整数溢出那么就返回 0。
 *
 */
class Solution {

    /**
     * @param Integer $x
     * @return Integer
     */
    function reverse($x) {
        $n = 1;
        if($x < 0) $n = -1;
        $a = $x * $n;
        $a = (string)$a;
        $re = strrev($a);

        $result = (int)$re;
        $result = $result * $n;	
	if( ($result > pow(2, 31) - 1) || ($result < -pow(2, 31)) ) $result = 0;

	return $result;	
    }
}

$obj = new Solution();
$result = $obj->reverse(1534236469);
var_dump($result);
