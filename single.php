<?php
/**
 * @author junhui.zhang
 * @date 2019-04-09 15:07
 * @desc 单例模式
 * 3个要点：
 * 	(1) 某个类只能有一个实例
 *	(2) 必须自行创建这个实例
 * 	(3) 必须自行向整个系统提供这个实例
 */

/**
 * 1.$instance必须声明为静态的私有变量
 * 2.构造方法必须声明为私有，防止外部使用new创建对象从而失去单例的意义
 * 3.getInstance()方法必须声明为公用，外部调用此方法返回实例
 * 4.私有__clone()方法防止克隆对象
 * 5.使用场景：
 * 	(1)数据库连接，使用单例可以避免大量的new操作
 *	(2)线程池，缓存，日志对象等等
 * 6.new对象消耗内存
 * 7.单例模式解决的是如何在整个项目中创建唯一对象实例的问题，
 *   工厂模式解决的是如何不通过new建立实例对象的方法
 */

class Single{
	private static $instance;

	//构造方法声明为私有，防止外部使用new来创建对象	
	private function __construct(){}
	
	//阻止用户复制对象
	private function __clone(){
		exit("Clone is not allow.");
	}

	//公有静态方法，返回实例对象
	public static function getInstance(){
		if(self::$instance == null){
			self::$instance = new self();
		}

		return self::$instance;
	}

	public function test(){
		echo "Tomorrow will be a brand new day.\n";
	}
}

$obj = Single::getInstance();
$obj->test();
