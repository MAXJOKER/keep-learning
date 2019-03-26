<?php
/**
 * @desc: 只出现一次的数字
 * @date: 2019-03-26 16:30
 * @author: junhui.zhang
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 * 示例:
 * 输入: [2,2,1]
 * 输出: 1
 */

class Solution {

    /**
     * @param Integer[] $nums
     * @return Integer
     */
    function singleNumber($nums) {
        $sum1 = 0;
        $sum2 = 0;
        $tmp = array_unique($nums);
        foreach($nums as $v){
            $sum1 += $v;
        }
        foreach($tmp as $va){
            $sum2 += $va;
        }
        
        $value = 2 * $sum2 - $sum1;
        
        return $value;
    }

    //异或
    function singleNumber2($nums){
	$result = 0;
	foreach($nums as $v){
		$result ^= $v;
	}
	return $result;
    }
}

$obj = new Solution();
$nums = array(4, 1, 2,2,1);
$value = $obj->singleNumber2($nums);
var_dump($value);
