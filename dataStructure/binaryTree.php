<?php
/**
 * @author junhui.zhang
 * @date 2019-04-22 18:44
 * @desc 二叉树
 */

class Node{

	/**
	 * 节点值
	 */
	public $val;

	
	/**
	 * 左子树
	 */
	public $left;
	
	/**
	 * 右子树
	 */
	public $right;

	public function __construct($val){
		$this->val = $val;
		$this->left = null;
		$this->right = null;
	}
}

class BinaryTree{
	
	/**
	 * 根节点
	 */
	private $root = null;
	
	/**
	 * 插入节点
	 */
	public function insertNode($node, $newNode){
		if($newNode->val < $node->val){
			if($node->left == null){
				$node->left = $newNode;
			}else{
				$this->insertNode($node->left, $newNode);
			}
		}else{
			if($node->right == null){
				$node->right = $newNode;
			}else{
				$this->insertNode($node->right, $newNode);
			}
		}			
	}

	/**
	 * 插入节点
	 */
	public function insert($val){
		$newNode = new Node($val);
		if($this->root == null){
			$this->root = $newNode;
		}else{
			$this->insertNode($this->root, $newNode);
		}

	}

	/**
	 * 查询值是否在二叉树中
	 */
	public function search($val){
		return $this->searchNode($this->root, $val);
	}

	public function searchNode($node, $val){
		if($node == null){
			return false;
		}
		if($val < $node->val){

			return $this->searchNode($node->left, $val);

		}else if($val > $node->val){
		
			return $this->searchNode($node->right, $val);

		}else{ // $val == $node->val

			return true;

		}
	}
	
	/**
	 * 删除指定值节点并返回新树
	 */
	public function remove($val){
		return $this->removeNode($this->root, $val);
	}

	public function removeNode($node, $val){
		if($node == null) return null;

		if($val < $node->val){
			
			$node->left = $this->removeNode($node->left, $val);
			return $node;

		}else if($val > $node->val){

			$node->right = $this->removeNode($node->right, $val);
			return $node;

		}else{
			if($node->left == null && $node->rigth == null){

				$node = null;
				return $node;

			}else if($node->left == null){
				
				$node = $node->right;
				return $node;

			}else if($node->right == null){
				
				$node = $node->left;
				return $node;
			}else{
				
				$tmp = $this->findMinNode($node->right);//从右子节点查找数据来代替当前节点
				$node->val = $tmp->val;
				$node->right = $this->removeNode($node->right, $tmp->val);//删除tmp

				return $node;
			}
		}
	}

	/**
	 * 获取值最小的节点
 	 */
	public function findMinNode($node){
		if($node == null) return null;
		
		while($node && $node->left != null){
			$node = $node->left;
		}	

		return $node;	
	}
}

$nodes = [8, 3, 10, 1, 6, 14, 4, 7, 13];

$bTree = new BinaryTree();
foreach ($nodes as $node) {
    $bTree->insert($node);
}
$result = $bTree->search(6);
var_dump($result);
