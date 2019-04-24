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
	public function insert($val){
		$newNode = new Node($val);
		if($this->root == null){
			$this->root = $newNode;
		}else{
			$this->insertNode($this->root, $newNode);
		}

	}
	
	private function insertNode($node, $newNode){
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
	 * 查询值是否在二叉树中
	 */
	public function search($val){
		return $this->searchNode($this->root, $val);
	}

	private function searchNode($node, $val){
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

	private function removeNode($node, $val){
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
	private function findMinNode($node){
		if($node == null) return null;
		
		while($node && $node->left != null){
			$node = $node->left;
		}	

		return $node;	
	}

	/**
	 * 获取二叉树中最小的值
	 */
	public function min(){
		return $this->minNode($this->root);
	}

	private function minNode($node){
		if($node == null) return null;

		while($node && $node->left != null){
			$node = $node->left;
		}

		return $node->val;
	}

	/**
	 * 获取二叉树中最大的值
	 */
	public function max(){
		return $this->maxNode($this->root);
	}

	private function maxNode($node){
		if($node == null) return null;

		while($node && $node->right != null){
			$node = $node->right;
		}

		return $node->val;
	}

	/**
	 * 前缀遍历 中-左-右
	 */
	public function preOrderTraverse(){
		$tmp = array();
		$this->preOrderTraverseNode($this->root, $tmp);

		return $tmp;
	}
	
	private function preOrderTraverseNode($node, &$tmp){
		if($node == null) return null;
		$tmp[] = $node->val;
		$this->preOrderTraverseNode($node->left, $tmp);
		$this->preOrderTraverseNode($node->right, $tmp);
		
		return $tmp;		
	}

	/**
	 * 中缀遍历 左-中-右
	 */
	public function midOrderTraverse(){
		$tmp = array();
		$this->midOrderTraverseNode($this->root, $tmp);

		return $tmp;
	}

	private function midOrderTraverseNode($node, &$tmp){
		if($node == null) return null;
		$this->midOrderTraverseNode($node->left, $tmp);
		$tmp[] = $node->val;
		$this->midOrderTraverseNode($node->right, $tmp);

		return $tmp;
	}

	/**
	 * 后缀遍历 左-右-中
	 */
	public function postOrderTraverse(){
		$tmp = array();
		$this->postOrderTraverseNode($this->root, $tmp);
		
		return $tmp;
	}

	private function postOrderTraverseNode($node, &$tmp){
		if($node == null) return null;

		$this->postOrderTraverseNode($node->left, $tmp);
		$this->postOrderTraverseNode($node->right, $tmp);
		$tmp[] = $node->val;

		return $tmp;
	}

	/**
	 * 置空二叉树
	 */
	public function unsetBinaryTree(){
		$this->root = null;
	}

	/**
	 * 二叉树叶子节点个数
	 * 左叶子结点+右叶子结点
	 */
	public function countLeafNode(){
		return $this->leafNum($this->root);
	}

	private function leafNum($node){
		if($node == null) return 0;
		if($node->left == null && $node->right == null) return 1;
		$leftLeaf = $this->leafNum($node->left);
		$rightLeaf = $this->leafNum($node->right);

		return $leftLeaf + $rightLeaf;
	}

	/**
	 * 二叉树节点数
	 */
	public function getNodes(){
		return $this->getNodesNum($this->root);		
	}

	public function getNodesNum($node){
		if($node == null) return 0;

		return $this->getNodesNum($node->left) + $this->getNodesNum($node->right) + 1;	
	}

	/**
	 * 二叉树深度
	 */
	public function maxDepth(){
		return $this->getBinaryTreeDepth($this->root);
	}

	private function getBinaryTreeDepth($node){
		
		if($node == null) return 0;
		
		$left = $this->getBinaryTreeDepth($node->left);
		$right = $this->getBinaryTreeDepth($node->right);

		return $right > $left ? $right + 1 : $left + 1;
	}

	/**
	 * 二叉树最小深度
	 */
	public function minDepth(){
		return $this->getBinaryTreeMinDepth($this->root);
	}

	private function getBinaryTreeMinDepth($node){
		if($node == null) return 0;

		if($node->left == null) return $this->getBinaryTreeMinDepth($node->right) + 1;
		if($node->right == null) return $this->getBinaryTreeMinDepth($node->left) + 1;

		$right = $this->getBinaryTreeMinDepth($node->right);
		$left = $this->getBinaryTreeMinDepth($node->left);

		return $right < $left ? $right + 1 : $left + 1;
	}
}

$nodes = [8, 3, 10, 1, 6, 14, 4, 7, 13];

$bTree = new BinaryTree();
foreach ($nodes as $node) {
    $bTree->insert($node);
}
$result = $bTree->postOrderTraverse();
echo "\npost order traverse:\n";
var_dump($result);
$result = $bTree->minDepth();
echo "\nmin depth:\n";
var_dump($result);
echo "\nleaf node numbers:\n";
$result = $bTree->countLeafNode();
var_dump($result);
echo "\nall nodes number:\n";
$result = $bTree->getNodes();
var_dump($result);
echo "\nunset binary tree:\n";
$bTree->unsetBinaryTree();
$result = $bTree->postOrderTraverse();
var_dump($result);

