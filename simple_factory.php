<?php
/**
 * @author junhui.zhang
 * @date 2019-04-09 12:18
 * @desc 工厂模式
 * 工厂方法或者类生成对象，而不是在代码中直接生成
 * 使用工厂模式，可以避免当改变某个类的名字或者方法之后，在调用这个类的所有代码中都要修改
 */

/**
 * 1.抽象基类：类中定义抽象方法，用以在子类中实现
 * 2.继承自抽象基类的子类：实现基类的抽象方法
 * 3.工厂类：用以实例化所有相对应的子类
 */

//抽象类
abstract class Human{
	abstract public function say();
}

//子类
class Man extends Human{
	public function say(){
		echo "create man\n";
	}
}

//子类
class Woman extends Human{
	public function say(){
		echo "create woman\n";
	}
}

//工厂类
class Factory{
	static function createMan(){
		return new Man();
	}

	static function createWoman(){
		return new Woman;
	}
}

$man = Factory::createMan();
$man->say();

$woman = Factory::createWoman();
$woman->say();

