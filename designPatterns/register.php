<?php
/**
 * @author junhui.zhang
 * @date 2019-04-09
 * @desc 注册模式
 */ 

/**
 * 注册模式，解决全局共享和交换对象。已经创建好的对象，挂到某个全局可以使用的数组上，
   在需要使用的时候，直接从该数组使用即可。
 */

class Register{
	protected static $objects;
	
	function set($alias, $object){//将对象注册到全局树上
		self::$objects[$alias] = $object;	
	}

	static function get($alias){ //获取对象
		return self::$objects[$alias];	
	}

	function unset($alias){ //移除对象
		unset(self::$objects[$alias]);
	}
}

class Test{
	function t(){
		echo "Fall in love with the process of becoming the very best version of yourself.\n";
	}
}

$obj = new Register();
$obj->set('test', new Test());
$obj->get('test')->t();
