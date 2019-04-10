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
     */
    function lengthOfLongestSubstring($s) {
	if($s == "") return 0;
        $str_arr = str_split($s);
	if(count($str_arr) == 1) return 1;
	$tmp = array();
	$tmp_count = array();
	for($i = 0; $i < count($str_arr); $i++){
		$tmp[] = $str_arr[$i];
		for($j = 0; $j < count($tmp) - 1; $j++){
			if($str_arr[$i] == $tmp[$j]){

				for($k = $j; $k >= 0; $k--){
					unset($tmp[$k]);
				}
				$tmp = array_values($tmp);
			}
		}
		$tmp_count[] = count($tmp);
	}
	$count = max($tmp_count);

	return $count;
    }
}

$obj = new Solution();
$str = "dvdfekkgquhc";
//$str = "dvdf";
$result = $obj->lengthOfLongestSubstring($str);
var_dump($result);
