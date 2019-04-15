<?php
/**
 * @author junhui.zhang
 * @date 2019-04-16 00:00
 * @desc 缺失数字
 */

/**
 * 给定一个包含 0, 1, 2, ..., n 中 n 个数的序列，找出 0 .. n 中没有出现在序列中的那个数。
 *
 * 示例 1:
 *
 * 输入: [3,0,1]
 * 输出: 2
 */

class Solution {

    /**
     * @param Integer[] $nums
     * @return Integer
     */
    function missingNumber($nums) {
        $arr = array();
        foreach($nums as $v){
                $arr[$v] = $v;
        }

        for($i = 0; $i <= count($nums); $i++){
                if(!isset($arr[$i])){
                        return $i;
                }
        }
    }

    function solution2($nums){
	$count = count($nums);
	$sum = (1 + $count) * $count / 2;
	
	$result = 0;
	foreach($nums as $v){
		$result += $v;
	}

	$res = $sum - $result;

	return $res;
    }
}

$obj = new Solution();
$nums = array(9,6,4,2,3,5,7,0,1);
$result = $obj->solution2($nums);
var_dump($result);
