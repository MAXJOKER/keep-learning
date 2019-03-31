<?php
/**
 * @desc: php排序算法
 * @date: 2019-03-03 20:50:00
 * @author: junhui.zhang
 */

/**
 * 冒泡排序
 * 相邻两个数比较，大数放后面，小的放前，一轮下来后，最大的数放在最后
 * 类似从后往前，从大到小
 * 时间复杂度o(n^2)
 * 空间复杂度o(1)
 */
function bubleSort($arr){
	for($i = 0; $i < count($arr) - 1; $i++){ //冒泡的次数
		for($j = 0; $j < count($arr) - $i - 1; $j++){ //比较的次数
			if($arr[$j] > $arr[$j+1]){
				$tmp = $arr[$j + 1];
				$arr[$j + 1] = $arr[$j];
				$arr[$j] = $tmp;
			}
		}
	}
	return $arr;
}

/**
 * 选择排序
 * 首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置，
 * 然后，再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。
 * 以此类推，直到所有元素均排序完毕。
 * 时间复杂度 o(n^2) 
 * 空间复杂度 o(1)
 */
function selectSort($arr){
	for($i = 0; $i < count($arr) - 1; $i++){
		$index = $i;
		for($j = $i + 1; $j < count($arr); $j++){
			if($arr[$index] > $arr[$j]){
				$index = $j;
			}
		}
		$tmp = 0;
		if($index != $i){
			$tmp = $arr[$i];
			$arr[$i] = $arr[$index];
			$arr[$index] = $tmp;
		}
	}
	return $arr;	
}

/**
 * 插入排序
 * 通过构建有序序列，对于未排序数据，在已排序序列中从后向前扫描，
 * 找到相应位置并插入。
 * 时间复杂度 o(n^2) 
 * 空间复杂度 o(1)
 */
function insertSort($arr){
	$count = count($arr);
	for($i = 1; $i < count($arr); $i++){
		$pre = $i - 1;
		$cur = $arr[$i];
		while($pre >= 0 && $arr[$pre] > $cur){
			$arr[$pre + 1] = $arr[$pre];
			$pre--; ////将下标往前挪，准备与前一个进行比较
			
		}
		if($pre + 1 != $i){
			$arr[$pre + 1] = $cur;
		} 
	}

	return $arr;	
}


/**
 * 快速排序的思想是分冶法。
 * 取数组中间值=>遍历数组剩余元素=>小于中间值的放左边，
 * 大于中间值的放右边=>将左右循环如此直至不可再分->将已排好的合并
 * 时间复杂度 o(nlog2(n))
 * 空间复杂度 o(nlog2(n))
 */
function quickSort($arr){

    if (count($arr) <= 1) return $arr;
    $index = (int)floor(count($arr) / 2);
    $value = $arr[$index];
    array_splice($arr, $index, 1);
    $left = $right = [];
    for ($i = 0; $i < count($arr); $i++) {
        if ($arr[$i] < $value) {
            array_push($left, $arr[$i]);
        } else {
            array_push($right, $arr[$i]);
        }
    }
    $left = quickSort($left);
    $right = quickSort($right);
    array_push($left, $value);
    return array_merge($left, $right);
}

/**
 * 堆排序
 */

/**
 * 希尔排序
 */
function shellSort($arr) {

  $count = count($arr);

  $inc = $count;  //增量

  do {

    //计算增量

    //$inc = floor($inc / 3) + 1;

    $inc = ceil($inc / 2);

    for ($i = $inc; $i < $count; $i++) {

      $temp = $arr[$i];  //设置哨兵

      //需将$temp插入有序增量子表

      for ($j = $i - $inc; $j >= 0 && $arr[$j + $inc] < $arr[$j]; $j -= $inc) {

        $arr[$j + $inc] = $arr[$j]; //记录后移

      }

      //插入

      $arr[$j + $inc] = $temp;

    }

    //增量为1时停止循环

  } while ($inc > 1);

  return $arr;
}

$arr = [9, 2, 1, 3, 5, 7, 6, 10, 8, 666];
//$result = quickSort($arr);
//$result = shellSort($arr);
//$result = bubleSort($arr);
//$result = selectSort($arr);
$result = insertSort($arr);
var_dump($result);
//var_dump(array_reverse($result));
