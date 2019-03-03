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
