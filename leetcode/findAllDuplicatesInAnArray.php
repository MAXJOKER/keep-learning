<?php
/**
 * @author junhui.zhang
 * @date 2019-04-17 15:11
 * @desc 442. 数组中重复的数据
 */

/**
 * 给定一个整数数组 a，其中1 ≤ a[i] ≤ n （n为数组长度）, 其中有些元素出现两次而其他元素出现一次。
 * 
 * 找到所有出现两次的元素。
 *
 * 你可以不用到任何额外空间并在O(n)时间复杂度内解决这个问题吗？
 * 
 * 示例：
 *
 * 输入:
 * [4,3,2,7,8,2,3,1]
 *
 * 输出:
 * [2,3]
 */

class Solution {

    /**
     * @param Integer[] $nums
     * @return Integer[]
     * 通过计算下标对比
     */
    function findDuplicates($nums) {
	$result = array();
	for($i = 0; $i < count($nums); $i++){
		$index = abs($nums[$i]) - 1;
		if($nums[$index] < 0){
			$result[] = abs($nums[$i]);
		}else{
			$nums[$index] = -$nums[$index];
		}
	}

	return $result;        
    }

    //直接利用php里面的函数
    function solution2($nums){
	$nums = array_count_values($nums); //统计数组中所有的值，返回一个数组，数组的键是nums中的值，数组的值是nums中的值出现的次数
        foreach($nums as $k => $value){
            if($value < 2) unset($nums[$k]);
        }
        
        return array_keys($nums); //返回数组中的键名
    }
}

$obj = new Solution();
$nums = array(4,3,2,7,8,2,3,1,1);

$result = $obj->findDuplicates($nums);
var_dump($result);
