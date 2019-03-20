<?php
/**
 * @desc: 两数之和
 * @date: 2019-03-20 23:52
 * @author: junhui.zhang
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 * 示例:
 * 给定 nums = [2, 7, 11, 15], target = 9
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 */

class Solution {

    /**
     * @param Integer[] $nums
     * @param Integer $target
     * @return Integer[]
     */
    function twoSum($nums, $target) {
        $count = count($nums);
        $result = array();
        for($i = 0; $i < $count; $i++){
            for($j = $i + 1; $j < $count; $j++){
                if($nums[$i] + $nums[$j] == $target){
                    $result = array($i, $j);
		    return $result;
                }
            }

        }
        
        return ;
    }

    function twoSum2($nums, $target){
        $count = count($nums);
	for($i = 0; $i < $count; $i++){
	    $needed = $target - $nums[$i];
	    if(in_array($needed, $nums)){
		$index = array_search($needed, $nums);
		if($index == $i) continue;
		return array($i, $index);	
	    }
	}
	return ;	
    }
}

$obj = new Solution();
$nums = array(3, 2, 4);
$target = 6;
$result = $obj->twoSum($nums, $target);
$result2 = $obj->twoSum2($nums, $target);
var_dump($result);
var_dump($result2);

