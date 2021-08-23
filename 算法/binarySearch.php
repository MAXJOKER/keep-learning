<?php
/**
 * @author junhui.zhang
 * @date 2019-04-25 16:08
 * @desc 二分查找
 */

class BinarySearch{

	public function __construct(){}
	
	public function search($arr, $left, $right, $search){
		return $this->binarySearch($arr, $left, $right, $search);
	}
	
	/**
	 * 递归
	 */
	private function binarySearchByRecursive($arr, $left, $right, $search){
		
	
		if($left <= $right){	
			$mid_index = floor( ($left + $right) / 2);
			$mid = $arr[$mid_index];

			if($search == $mid){
				return $mid_index;
			}else if($search > $mid){
				return $this->binarySearch($arr, $mid_index + 1, $right, $search);
			}else{
				return $this->binarySearch($arr, $left, $mid_index - 1, $search);
			}
		}else{
			return false;
		}
	
	}

	/**
	 * 非递归
 	 */ 
	private function binarySearch($arr, $left, $right, $search){
		
		while($left <= $right){
			$mid_index = floor(($left + $right) / 2);
			$mid = $arr[$mid_index];

			if($mid == $search){
				return $mid_index;
			}else if($mid > $search){
				$right = $mid_index - 1;
			}else{
				$left = $mid_index + 1;
			}
		}
	}
}

$arr = array(1, 3, 6, 7, 9, 11, 16, 22, 36);

$obj = new BinarySearch();
$search = 3;
$result = $obj->search($arr, 0, count($arr) - 1, $search);

var_dump($result);
