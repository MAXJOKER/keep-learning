<?php
/**
 * @author junhui.zhang
 * @date 2019-04-16 17:18
 * @desc top k算法，（找出数组中，最大/最小的 K个数）
 */

class TopK{
	
	/**
  	 * @param array $arr
	 * @param integer $k
	 * @param string $type 最大-max，最小-min
	 */
	public function solution1($arr, $k, $type){
		
		if($type == 'min'){
			sort($arr); //sort 对数组进行由低到高的排序
		}else{
			rsort($arr); //rsort 对数组进行由高到低的排序
		}
		$result = array_splice($arr, 0, $k);

		return $result;
	}

	//冒泡排序
	public function solution2($arr, $k, $type){
		
		$count = count($arr);
		for($i = 0; $i < $count; $i++){
			for($j = 0; $j < $count - $i - 1; $j++){
				if($type == 'max'){
					if($arr[$j] > $arr[$j+1]){
						$tmp = $arr[$j+1];
						$arr[$j+1] = $arr[$j];
						$arr[$j] = $tmp;
					}
				}else{
					if($arr[$j] < $arr[$j+1]){
						$tmp = $arr[$j];
						$arr[$j] = $arr[$j+1];
						$arr[$j+1] = $tmp;
					}
					
				}
			}

			if($i == $k) break;
		}
		var_dump($arr);
		$result = array_splice($arr, $count - $k, $k);

		return $result;
	}

	//类似最小堆，tmp保存k个元素，跟arr对比
	public function solution3($arr, $k, $type){

		$tmp = array_splice($arr, 0, $k);
		$count = count($arr);

		for($i = 0; $i < $count; $i++){
			if($type == 'max'){
				sort($tmp);
				$max_min = $tmp[0];
				if($arr[$i] > $max_min){
					$tmp[0] = $arr[$i];
				}
			}else{
				rsort($tmp);
				$min_max = $tmp[0];
				if($arr[$i] < $min_max){
					$tmp[0] = $arr[$i];
				}
			}
		}
	
		return $tmp;		
	
	}

	//TODO 二分查找

	//TODO 快排
}

$obj = new TopK();

$arr = array(1, 2, 4, 5, 6, 9, 15, 12, 0, -1, 3);
$k = 3;
//$type = 'min';
$type = 'max';

$result = $obj->solution3($arr, $k, $type);
var_dump($result);
