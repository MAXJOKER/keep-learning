<?php
/**
 * @author junhui.zhang
 * @date 2019-04-08 22:04
 * @desc 两数相加
 */

/**
 * 伪代码如下：
 *
 * 将当前结点初始化为返回列表的哑结点。
 * 将进位 carry 初始化为 0。
 * 将 p 和 q 分别初始化为列表 l1 和 l2 的头部。
 * 遍历列表 l1 和 l2 直至到达它们的尾端。
 * 将 x 设为结点 p 的值。如果 p 已经到达 l1 的末尾，则将其值设置为 0。
 * 将 y 设为结点 q 的值。如果 q 已经到达 l2 的末尾，则将其值设置为 0。
 * 设定 sum = x + y + carry。
 * 更新进位的值，carry = sum / 10。
 * 创建一个数值为 (sum mod 10) 的新结点，并将其设置为当前结点的下一个结点，然后将当前结点前进到下一个结点。
 * 同时，将 p 和 q 前进到下一个结点。
 * 检查 carry=1 是否成立，如果成立，则向返回列表追加一个含有数字 1 的新结点。
 * 返回哑结点的下一个结点。
 */

/**
 * 复杂度分析
 * 时间复杂度 O(max(m,n))，假设 m 和 n 分别表示 l1 和 l2 的长度，上面的算法最多重复 max(m,n) 次。
 * 空间复杂度 O(max(m,n))， 新列表的长度最多为 max(m,n)+1。
 */

/**
 * Definition for a singly-linked list.
 */
class ListNode {
    public $val = 0;
    public $next = null;
    function __construct($val) { $this->val = $val; }

}

class Solution {

    /**
     * @param ListNode $l1
     * @param ListNode $l2
     * @return ListNode
     */
    function addTwoNumbers($l1, $l2) {

	$re = new ListNode(0);
	$cur = $re;
	$p = $l1;
	$q = $l2;
	$carry = 0;
	
	while($p != null || $q != null){
		$x = ($p != null) ? $p->val : 0;
		$y = ($q != null) ? $q->val : 0;

		$sum = $x + $y + $carry;
		$carry = (int)($sum / 10);

		$cur->next = new ListNode($sum % 10);
		$cur = $cur->next;

		if($p != null) $p = $p->next;
		if($q != null) $q = $q->next;
	}

	if($carry > 0){ //最后可能会出现额外的进位,如最后一个是9+1+carry
		$cur->next = new ListNode($carry);
	}

	return $re->next;
    }
     
    function addNode(&$list, $data){
	if($list == null){
		$list = new ListNode($data);
	}else{
		$cur = $list;
		while($cur->next != null){
			$cur = $cur->next;
		}
		$node = new ListNode($data);
		$cur->next = $node;
	}
     }
}

$obj = new Solution();

$array1 = array(2, 4, 3);
$array2 = array(5, 6, 7);

$l1 = null;
$l2 = null;

foreach($array1 as $v){
	$obj->addNode($l1, $v);	
}

foreach($array2 as $v){
	$obj->addNode($l2, $v);	
}

$result = $obj->addTwoNumbers($l1, $l2);
var_dump($result);
