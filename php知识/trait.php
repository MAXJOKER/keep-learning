<?php
/**
 * @author junhui.zhang
 * @date 2019-04-08 11:06
 * @desc php trait
 */

/**
 * 官方解释:
 * 自 PHP 5.4.0 起，PHP 实现了一种代码复用的方法，称为 trait。
 * Trait 是为类似 PHP 的单继承语言而准备的一种代码复用机制(减少代码重复，增加代码复用性)。
 * Trait 为了减少单继承语言的限制，使开发人员能够自由地在不同层次结构内独立的类中复用 method。
 * Trait 和 Class 组合的语义定义了一种减少复杂性的方式，避免传统多继承和 Mixin 类相关典型问题。
 * Trait 和 Class 相似，但仅仅旨在用细粒度和一致的方式来组合功能。 无法通过 trait 自身来实例化。
 * 
 * 优先级
 * 从基类继承的成员会被 trait 插入的成员所覆盖。
 * 优先顺序是来自当前类的成员覆盖了 trait 的方法，而 trait 则覆盖了被继承的方法。
 * 即 自身方法 > trait方法 > 继承的方法
 *
 * 调用
 * 1.声明一个trait
 * 2.在类中使用use引入trait
 * 3.使用多个trait，用逗号,隔开
 *
 * 冲突
 * 不同的trait，有同名的方法或属性，在同时引入是会产生冲突
 * 可以使用insteadof或as来解决
 * insteadof是替代，as是取别名
 * as还可以用来修改方法的访问控制
 *
 * 其他
 * trait可以互相组合，一个trait中可以引入其他trait(也是使用use)
 * trait可以使用抽象方法，静态方法，静态属性等
 */

trait Hello{
	public function sayHello(){
		echo "trait Hello: Hello ";
	}
	
}

trait World{
	public function sayHello(){
		echo "trait World : Hello ";
	}
	
	public function sayWorld(){
		echo "world";
	}
	
	abstract public function getName();
	
	public static function eat(){
		echo "\neat: eat lunch\n";
	}
	
}

trait Access{
	protected function accessControll(){
		echo "access controll\n";
	}
}

class Test{
	use Hello, World, Access{
		Hello::sayHello insteadof World;
		World::sayHello as say;
		Access::accessControll as public;
		
	}

	public function sentenseEnd(){
		echo "!\n";
	}
	
	public function getName(){
		echo "getName\n";
	}
}

$obj = new Test();
$obj->sayHello();
$obj->sayWorld();
$obj->sentenseEnd();
$obj->say();
Test::eat();
$obj->getName();
Test::accessControll();
echo "\n";
Hello::sayHello();//直接调用
