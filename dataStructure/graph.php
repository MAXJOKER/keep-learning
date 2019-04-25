<?php
/**
 * @author junhui.zhang
 * @date 2019-04-24 16:52
 * @desc 图
 */

/**
 * 概念
 *
 */

/**
 * 邻接矩阵实现图
 */
class MGraph{
	
	public $vexs; //顶点表
	public $arc; //邻接矩阵
	public $numNodes; //图中当前的顶点数
	public $visited = array(); 

	public function __construct($vex){
		$this->vexs = $vex;
		$this->numNodes = count($this->vexs);

		for($i = 0; $i < $this->numNodes; $i++){
			for($j = 0; $j < $this->numNodes; $j++){
				$this->arc[$i][$j] = 0;
			}
		}
	}

	/**
	 * $a, $b为顶点下标，w为权
	 */
	public function addEdge($a, $b, $w){
		if($a == $b) return ;
		
		$this->arc[$a][$b] = $w;
		$this->arc[$b][$a] = $w;
	}

	/**
	 * 从第i个节点开始深度优先遍历
	 */
	public function traverse($i){
		$this->visited[$i] = 1;
		echo $this->vexs[$i] . PHP_EOL;
		for($j = 0; $j < $this->numNodes; $j++){
			if($this->arc[$i][$j] > 0 && $this->visited[$j] == 0){
				$this->traverse($j);
			}
		}
	}

	/**
	 * 递归
	 */
	public function dfs(){
		//初始化节点遍历标记
		for($i = 0; $i < $this->numNodes; $i++){
			$this->visited[$i] = 0;
		}

		//从没有被遍历的节点开始深度遍历
		for($i = 0; $i < $this->numNodes; $i++){
			if($this->visited[$i] == 0){
				$this->traverse($i);
			}
		}	
	}

	/**
	 * 非递归
	 */
	public function deepFirstSearch(){
		//初始化节点遍历标记
		for($i = 0; $i < $this->numNodes; $i++){
			$this->visited[$i] = 0;
		}

		$stack = array();
		for($i = 0; $i < $this->numNodes; $i++){
			if(!$this->visited[$i]){
				$stack[] = $i;
				while(!empty($stack)){
					//出栈
					$curr = array_pop($stack);
					//如果该节点还没被遍历，则遍历该节点并将子节点入栈
					if($this->visited[$curr] == 0){
						echo $this->vexs[$curr] . PHP_EOL;
						$this->visited[$curr] = 1;
		
						//子节点入栈
						for($j = $this->numNodes - 1; $j >= 0; $j--){
							if($this->arc[$curr][$j] > 0 && $this->visited[$j] == 0){
								$stack[] = $j;
							}
						}
					}
				}
			}
		}
	}

	/**
	 * 广度优先 - 邻接矩阵
	 */
	public function bfs(){

	}
	
}

$vertices = ['v1', 'v2', 'v3', 'v4', 'v5', 'v6', 'v7', 'v8'];
$graph = new MGraph($vertices);
$graph->addEdge(0, 1, 1); // v1 v2
$graph->addEdge(0, 2, 2); // v1 v3
$graph->addEdge(1, 3, 3); // v2 v4
$graph->addEdge(1, 4, 6); // v2 v5
$graph->addEdge(2, 5, 5); // v3 v6
$graph->addEdge(2, 6, 1); // v3 v7
$graph->addEdge(4, 7, 2); // v5 v8
$graph->addEdge(3, 7, 7); // v4 v8
$graph->dfs();
$graph->deepFirstSearch();

