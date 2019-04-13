<?php
/**
 * @author junhui.zhang
 * @date 2019-04-10 14:31
 * @desc 无重复字符的最长子串,给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 */

class Solution {

    /**
     * @param String $s
     * @return Integer
     * 暴力解法
     * 时间复杂度O(n^3)
     */
    function lengthOfLongestSubstring($s) {
	if($s == "") return 0;
        $str_arr = str_split($s);
	if(count($str_arr) == 1) return 1;
	$tmp = array();
	$tmp_count = array();
	for($i = 0; $i < count($str_arr); $i++){
		$tmp[] = $str_arr[$i];
		for($j = 0; $j < count($tmp) - 1; $j++){ //子数组跟目标数组比较，有重复的话，删掉重复之前的元素
			if($str_arr[$i] == $tmp[$j]){

				for($k = $j; $k >= 0; $k--){
					unset($tmp[$k]);
				}
				$tmp = array_values($tmp);//数组下标重排
			}
		}
		$tmp_count[] = count($tmp);
	}
	$count = max($tmp_count);

	return $count;
    }
/**
 * 2，3为滑动窗口解法
 * 2是记录符合要求的子串，并取这些字串中最大的长度
 * 3是记录符合要求的子串的最左位置（开始的位置），结束位置-开始位置+1 为符合要求子串的长度，取最大值
 */

    function solution2($s){
	if($s == "") return 0;
        $str_arr = str_split($s);
	if(count($str_arr) == 1) return 1;
	$tmp = array();
	$max = 0;

	for($i = 0; $i < count($str_arr); $i++){
		if(in_array($str_arr[$i], $tmp)){
			$index = array_search($str_arr[$i], $tmp);
			$tmp = array_splice($tmp, $index + 1);
		}
		$tmp[] = $str_arr[$i];
		$max = max($max, count($tmp));
	}

	return $max;
    }

    function solution3($s){

	$tmp = '';
	$left = 0;
	$max = 0;	
	for($i = 0; $i < strlen($s); $i++){
		if(stripos($tmp, $s[$i]) !== false){
			$left = max($left, strrpos($tmp, $s[$i]) + 1);
			//var_dump($left);
		}
		$tmp .= $s[$i];
		//var_dump($tmp);
		$max = max($max, $i - $left + 1);
	}

	return $max;
    }
}

$obj = new Solution();
//$str = "dvdfekkgquhc";
//$str = "dvdf";
$str = 'abcabcbb';
//$result = $obj->lengthOfLongestSubstring($str);
$result = $obj->solution3($str);
var_dump($result);
