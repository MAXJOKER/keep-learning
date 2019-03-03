<?php
/**
 * @desc: php排序算法
 * @date: 2019-03-03 20:50:00
 * @author: junhui.zhang
 */


/**
 * 快速排序的思想是分冶法。
 * 取数组中间值=>遍历数组剩余元素=>小于中间值的放左边，
 * 大于中间值的放右边=>将左右循环如此直至不可再分->将已排好的合并
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
$result = shellSort($arr);
var_dump($result);
//var_dump(array_reverse($result));
