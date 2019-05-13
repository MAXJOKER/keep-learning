<?php
/**
 * @author junhui.zhang
 * @date 2019-04-12 20:29
 * @desc 鸡蛋掉落
 */

class Solution {

    /**
     * @param Integer $K
     * @param Integer $N
     * @return Integer
     * // TODO 待解决
     */
    function superEggDrop($K, $N) {
	if($K == 1 || $N == 1) return $N;

	$i = 1;
	$re = 0;
	$r = 0;
	$j = 1;

	while(1){
		$re = pow($K, $i);
		if($re > $N) break;	
		$i++;
	}

	$gap1 = abs(pow($K, $i - 1) - $N);
	$gap2 = pow($K, $i - 1);
	$mg = max($gap1, $gap2);

	var_dump($mg);
	var_dump($i);
	if($mg > 0){
		while(1){
			$r = pow($K, $j);
			if($r >= $mg) break;
			$j++;
		}
	}
	var_dump($r);
	var_dump($j);


	$result = $gap1 > $gap2 ? $i - 1 + $j : $i + $j;
	
	return $result;        
    }
}

$obj = new Solution();
$result = $obj->superEggDrop(3, 14);
var_dump($result);

