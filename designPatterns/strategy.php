<?php
/**
 * @author junhui.zhang
 * @date 2019-04-09 21:17
 * @desc 策略模式
 */ 

/**
 * 创建一个能够根据所传递对象的不同而具有不同行为的方法被称为策略设计模式.
 * 这类方法包含所要执行的算法中固定不变的部分，而“策略”包含变化的部分。
 * 策略就是传递进去的参数对象，它包含要执行的代码。
 *
 * 策略模式的三个角色：
 * 1．抽象策略角色
 * 2．具体策略角色
 * 3．环境角色（对抽象策略角色的引用）
 *
 * 实现步骤：
 * 1．定义抽象角色类（定义好各个实现的共同抽象方法）
 * 2．定义具体策略类（具体实现父类的共同方法）
 * 3．定义环境角色类（私有化申明抽象角色变量，重载构造方法，执行抽象方法）
 *
 * 其实有点类似if else，代码解耦，程序更加灵活，易于维护
 */

abstract class baseAgent{ //抽象策略类
	abstract function printPage();
}

class IeAgent extends baseAgent{ //客户端是IE时调用的类(环境角色)
	public function printPage(){
		echo "This is ie browser.\n";
	}
}

class ChromeAgent extends baseAgent{ //客户端是Chrome时调用的类(环境角色)
	public function printPage(){
		echo "This is chrome browser.\n";
	}
}

class Browser{ //具体策略角色
	public function call($obj){
		return $obj->printPage();
	}
}

$obj = new Browser();
$obj->call(new IeAgent());
$obj->call(new ChromeAgent());

