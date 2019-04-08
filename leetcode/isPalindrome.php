<?php
/**
 * @author junhui.zhang
 * @date 2019-04-08 14:06
 * @desc 回文数
 */

class Solution {

    /**
     * @param Integer $x
     * @return Boolean
     */
    function isPalindrome($x) {
        $a = (string)$x;
	$a = str_split($a);
	//var_dump($a);
	$b = array_reverse($a);
	//var_dump($b);
	$c = implode('', $b);
	//var_dump($c);
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
