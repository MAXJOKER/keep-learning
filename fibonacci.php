<?php
/**
 * @author junhui.zhang
 * @date 2019-04-25 17:32
 * @desc 斐波那契数列
 */

class Fibonacci{
	
	public function __construct(){}
	
	public function fibonacci($k){
		return $this->fi($k);
	}

	/**
	 * 递归
	 */
	private function f($k){
	
		if($k == 0) return 0;
		if($k == 1) return 1;

		return $this->f($k - 1) + $this->f($k - 2);
	}

	/**
	 * 非递归
	 */
	private function fi($k){
		if($k == 0) return 0;
		if($k == 1) return 1;
		$f0 = 0;
		$f1 = 1;
		$sum = 0;

		for($i = 2; $i <= $k; $i++){
			$sum = $f0 + $f1;
			$f0 = $f1;
			$f1 = $sum;
		}
		return $sum;	
	}
}

$obj = new Fibonacci();
$k = 6;
$result = $obj->fibonacci($k);
var_dump($result);
