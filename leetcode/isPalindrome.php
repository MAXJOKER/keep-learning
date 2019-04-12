<?php
/**
 * @author junhui.zhang
 * @date 2019-04-08 14:06
 * @desc 回文数
 */

/**
 * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 *
 * 示例 1:
 *
 * 输入: 121
 * 输出: true
 *
 * 示例 2:
 *
 * 输入: -121
 * 输出: false
 * 解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
 *
 * 示例 3:
 *
 * 输入: 10
 * 输出: false
 * 解释: 从右向左读, 为 01 。因此它不是一个回文数。
 */

class Solution {

    /**
     * @param Integer $x
     * @return Boolean
     */
    function isPalindrome($x) {
        $a = (string)$x;
	$a = str_split($a);
	$b = array_reverse($a);
	$c = implode('', $b);
	$d = (int)$c;

	if($x == $d){
		return true;
	}

	return false;
    }

    /**
     * 其他解法
     */
    function isPalindrome2($x){
        $a = (string)$x;
	$a = str_split($a);

	for($i = 0, $j = count($a) - 1; $i <= $j; $i++, $j--){
		if($a[$i] != $a[$j]){
			return false;
		}
	}
	return true;
    }
}

$obj = new Solution();
$result = $obj->isPalindrome(12321);
$result1 = $obj->isPalindrome2(12321);
var_dump($result);
var_dump($result1);
