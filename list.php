<?php
/**
 * 单向链表翻转
 * 2019-03-02 17:30:00

 * 单向链表：
	单向链表的链表对象维护了一个 first引用，该引用指向节点链表中的第一个节点对象，每个节点对象维护一个 next 引用，next引用指向下一个节点对象；（这里注意：是引用指向的是节点对象：节点对象包含存储的数据和next引用）
	图片地址：https://img-blog.csdn.net/20180523102549287?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3psYl9sb3Zlcg==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70
	相关文章：https://blog.csdn.net/zlb_lover/article/details/80415875
 *
 */

class Node{

	public $next = null;
	public $data;

	public function __construct($data){
		$this->data = $data;
	}

}

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
//var_dump($new);	
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

//测试
function test(){
	$list = new Node(null);
	addNode($list, 1);
	addNode($list, 2);
	addNode($list, 3);
	addNode($list, 4);
	addNode($list, 5);

	showNode($list);

	echo "\nNew NODE\n";
	
	//$new = reverse($list);
	//$new = reverseRecrusion($list);

	//insertNode($list, 9, 3);
	deleteNode($list, 2); 

	showNode($list);
	
}

test();
