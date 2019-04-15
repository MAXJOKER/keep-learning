<?php
/**
 * @author junhui.zhang
 * @date 2019-04-15 10:58
 * @desc 栈
 */

interface StackInterface{
	public function push($item);
	public function pop();
	public function isEmpty();
	public function top();
}

//数组实现
class StackByArray implements StackInterface{
	
	private $stack;

	public function __construct(){
		$this->stack = array();
	}	

	public function push($item){
		array_push($this->stack, $item);
	}

	public function pop(){
		if(!empty($this->stack)){
			return array_pop($this->stack);
		}

		return false;
	}

	public function isEmpty(){
		return empty($this->stack);
	}	

	public function top(){
		if(!empty($this->stack)){
			return end($this->stack);
		}
		
		return false;
	}

	public function showStack(){
		if(!empty($this->stack)){
			return $this->stack;
		}

		return false;
	}	
}

$obj = new StackByArray();
for($i = 0; $i < 10; $i++){
	$obj->push($i);
}

$result = $obj->showStack();
var_dump($result);

$top = $obj->top();
var_dump($top);

$pop = $obj->pop();
var_dump($pop);

var_dump($obj->showStack());

