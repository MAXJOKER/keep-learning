<?php
namespace DataStructure\Queue;

use DataStructure\Lists\Lists;
/**
 * @author junhui.zhang
 * @date 2019-04-14 23:31
 * @desc 队列(先进先出FIFO)
 * 队列只允许在一端插入，另一端删除。队头进行删除操作，队尾进行插入操作
 */

interface QueueInterface{
	public function enQueue($item); //入队
	public function deQueue(); //出队
	public function isEmpty(); //是否为空
	public function head(); //返回队头
	public function clear(); //清空队列
	public function showQueue(); //返回队列 
}

//数组实现
class QueueByArray implements QueueInterface{
	
	private $queue;

	public function __construct(){
		$this->queue = array();
	}

	//入队
	public function enQueue($item){

		array_push($this->queue, $item); //array_push  将一个或多个单元压入数组的末尾
	}

	//出队
	public function deQueue(){

		if(!empty($this->queue)){
			$result = array_shift($this->queue); //array_shift 将数组开头的元素移出数组
			return $result;
		}

		return false;;
	}

	//检测队列是否为空
	public function isEmpty(){

		return empty($this->queue);
	}

	//返回队列头部元素
	public function head(){
	
		if(!empty($this->queue)){
			return current($this->queue);
		}	

		return false;
	}

	//清空队列
	public function clear(){

		unset($this->queue);
	}

	//返回队列
	public function showQueue(){
		
		if(!empty($this->queue)){
			return $this->queue;
		}

		return false;
	}	
}
/*
class QueueByList implements QueueInterface{
	
	private $queue;
	
	public function __construct(){
		$this->queue = new Lists();
	}
	
}
*/

$obj = new QueueByArray();
for($i = 0; $i < 10; $i++){
	$obj->enQueue($i);
}
echo "enQueue result: \n";
$result = $obj->showQueue();
var_dump($result);

echo "\ndeQueue: \n";
$res = $obj->deQueue();
var_dump($res);

//$obj->clear();
echo "\nget queue head: \n";
$re = $obj->head();
var_dump($re);

echo "\nshow final queue: \n";
$result = $obj->showQueue();
var_dump($result);
