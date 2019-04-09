<?php
/**
 * @author junhui.zhang
 * @date 2019-04-09 22:32
 * @desc 适配器模式
 */

/**
 * 将各种截然不同的函数接口封装成统一的API。
 * PHP中的数据库操作有MySQL,MySQLi,PDO三种，可以用适配器模式统一成一致，
 * 使不同的数据库操作，统一成一样的API。类似的场景还有cache适配器，
 * 可以将memcache,redis,file,apc等不同的缓存函数，统一成一致。 
 *
 * 首先定义一个接口(有几个方法，以及相应的参数)。然后，有几种不同的情况，
 * 就写几个类实现该接口。将完成相似功能的函数，统一成一致的方法。
 */

interface base{

	public function connect($host, $user, $password, $dbname);
	
	public function query($sql);

	public function close();
}

class Mysql implements base{
	
	protected $conn;

	public function connect($host, $user, $password, $dbname){
		$conn = mysql_connect($host, $user, $password);
		mysql_select_db($dbname);
		$this->conn = $conn;
	}

	public function query($sql){
		$res = mysql_query($sql);
		$result = array();
		while($row = mysql_fetch_assoc($res)){
			$result[] = $row;
		}

		return $result;
	}

	public function close(){
		mysql_close($this->conn);	
	}
}

class Mysqli implements base{

	protected $conn;

	public function connect($host, $user, $password, $dbname){
		$conn = mysqli_connect($host, $user, $password, $dbname);
		$this->conn = $conn;
	}

	public function query($sql){
		$res = mysqli_query($sql);
		$result = array();
		while($row = $res->fetch_assoc()){
			$result[] = $row;
		}

		return $result;
	}	

	public function close(){
		mysqli_close($this->conn);	
	}	
}
