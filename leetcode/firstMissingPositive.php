<?php
/**
 * @author junhui.zhang
 * @date 2019-04-15 22:51
 * @desc 缺失的第一个正数
 */

/**
 * 给定一个未排序的整数数组，找出其中没有出现的最小的正整数。
 *
 * 示例 1:
 *
 * 输入: [1,2,0]
 * 输出: 3
 *
 * 你的算法的时间复杂度应为O(n)，并且只能使用常数级别的空间。
 */

class Solution {

    /**
     * @param Integer[] $nums
     * @return Integer
     */
    function firstMissingPositive($nums) {
    	$i = 1;
	while(true){
		if(in_array($i, $nums)){
			$i++;
		}else{
			return $i;
			break;
		}
	}
    }

    function solution2($nums){
	
	$arr = array();
	foreach($nums as $v){
		if($v <= 0) continue;
		$arr[$v] = $v;
	}

	$j = 1;
	for($i = 1; $i <= count($nums); $i++){
		if(isset($arr[$i])){
			$j++;
		}else{
			break;
		}
	}

	return $j;	
    }
}

$obj = new Solution();
$arr = array(0, 1, 2);
//$result = $obj->firstMissingPositive($arr);
$result = $obj->solution2($arr);
var_dump($result);
