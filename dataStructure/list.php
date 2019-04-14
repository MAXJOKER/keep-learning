<?php
/**
 * @desc: 链表
 * @date: 2019-03-02 17:30:00
 * @author: junhui.zhang
 *
 * 链表是线性表的一种，所谓的线性表包含顺序线性表和链表，顺序线性表是用
 * 数组实现的，在内存中有顺序排列，通过改变数组大小实现。而链表不是用顺
 * 序实现的，用指针实现，在内存中不连续。意思就是说，链表就是将一系列不
 * 连续的内存联系起来，将那种碎片内存进行合理的利用，解决空间的问题。
 *
 * 所以，链表允许插入和删除表上任意位置上的节点，但是不允许随即存取。
 * 链表有很多种不同的类型：单向链表、双向链表及循环链表
 *
 * 链表本质上就是一种数据结构，主要用来动态放置数据。也可用来构建许多数
 * 据结构，比如堆栈、队列及它们的派生。
 *
 *
 * 单向链表：
 *	单向链表的链表对象维护了一个 first引用，该引用指向节点链表中的第一个节点对象，
 *	每个节点对象维护一个 next 引用，next引用指向下一个节点对象；（这里注意：是引
 *	用指向的是节点对象：节点对象包含存储的数据和next引用）
 *
 *	图片地址：https://img-blog.csdn.net/20180523102549287?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3psYl9sb3Zlcg==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70
 *	相关文章：https://blog.csdn.net/zlb_lover/article/details/80415875
 *
 *	
 */

class Node{

	public $next = null;
	public $data;

	public function __construct($data){
		$this->data = $data;
	}

}

class Lists{

	//新增
	function addNode($list, $data){
		$cur = $list;
		while($cur->next != null){
			$cur = $cur->next;//确保下一节点为空
		}
		$node = new Node($data);
		$cur->next = $node;

	}

	//遍历
	function showNode($list){
		$cur = $list;
		while($cur != null){
			echo $cur->data . "\n";
			$cur = $cur->next;
		}
	}

	//遍历法
	function reverse($list){
		if($list == null){
			return $list;
		}

		$cur = $list;
		$new = null;
		
		while($cur != null){
			$temp = $cur->next; //保存下一个节点
			$cur->next = $new; //断开旧节点
			$new = $cur; //新节点
			$cur = $temp; //下一个节点继续
		}
		//var_dump($cur);	
		return $new;	
	}

	//递归
	function reverseRecrusion($list){
		if($list->next == null){
			return $list;
		}

		$plist = reverseRecrusion($list->next);
		$list->next->next = $list;
		$list->next = null;
		
		return $plist;	
	}

	//链表长度
	function countNode($list){
		$i = 0;
		$cur = $list;
		while($cur->next != null){
			$i++;
			$cur = $cur->next;
		}

		return $i;
	}

	//插入
	function insertNode($list, $data, $no){
		if($no > countNode($list)){
			return false;
		}

		$cur = $list;
		for($i = 0; $i < $no; $i++){
			//if($i==0) var_dump($cur->data);//有0节点，但其实为null
			$cur = $cur->next;
		}	
		$new = new Node($data);
		$new->next = $cur->next;
		$cur->next = $new;
	}

	//删除
	function deleteNode($list, $no){
		if($no > countNode($list)){
			return false;
		}

		$cur = $list;
		for($i = 0; $i < $no-1; $i++){
			$cur = $cur->next;	
		}

		$cur->next = $cur->next->next;	
	}

	//搜索特定位置的节点
	function searchNode($list, $no){
		if($no < 0 || $no > $this->countNode($list) || $list->next == null) return false;
		$cur = $list;
		
		$count = 0;
		while($cur->next != null){
			if($count == $no){
				return $cur;
			}
			$count++;
			$cur = $cur->next;
		}		
	}
}

//测试
function test(){
	$obj = new Lists();
	$list = new Node(null);
	$obj->addNode($list, 1);
	$obj->addNode($list, 2);
	$obj->addNode($list, 3);
	$obj->addNode($list, 4);
	$obj->addNode($list, 5);

	$obj->showNode($list);
	
	echo "\nsearch node: \n";
	$re = $obj->searchNode($list, 3);
	var_dump($re->data);

	echo "\nNew NODE\n";
	
	$new = $obj->reverse($list);
	//$new = reverseRecrusion($list);

	//insertNode($list, 9, 3);
	//deleteNode($list, 2); 

	//showNode($list);
	$obj->showNode($new);
	
}

test();
