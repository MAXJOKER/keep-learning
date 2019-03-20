<?php
/**
 * @desc: 77 - 组合
 *        给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 *        示例:
 *           输入: n = 4, k = 2
 *           输出:
 *           [
 *             [2,4],
 *             [3,4],
 *             [2,3],
 *             [1,2],
 *             [1,3],
 *             [1,4],
 *           ]
 * @date: 2019-03-20 10:38
 * @author: junhui.zhang
 */

class Solution{

    private $res = array();
     
    /**
     * @param Integer $n
     * @param Integer $k
     * @return Integer
     */
    function combine($n, $k){
        $number = $this->cal($n) / ($this->cal($k) * $this->cal($n - $k)); //组合总个数

        return $number;
    }

    function cal($v){
        if($v == 1 || $v == 0){
            return 1;
        }

        return $v * $this->cal($v-1);
    }

    /**
     * @param Integer $n
     * @param Integer $k
     * @return Integer[][]
     */
    function combined($n, $k){
        $arr = array();
        $this->findCombineNum(1, $n, $k, $arr);
        
        return $this->res;    
    }

    function findCombineNum($start, $n, $k, $arr){
        $len = count($arr);
        if($len == $k){
            $this->res[] = $arr;
            return ;
        }

        $end = $n - ($k - $len) + 1;
        for($i = $start; $i <= $end; $i++){
            $arr[] = $i;
            $this->findCombineNum($i+1, $n, $k, $arr);
            array_pop($arr);
        } 
    }
}

$obj = new Solution();
$result = $obj->combine(4, 3);
$resulted = $obj->combined(4, 3);
var_dump($result);
var_dump($resulted);
