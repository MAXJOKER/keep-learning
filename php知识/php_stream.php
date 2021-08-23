<?php
/**
 * @desc: PHP统一资源处理API--流(Stream)的概述
 * @date: 2019-03-11 15:56
 * @author: junhui.zhang
 */


/*************************************************************************
一.解析
    流的作用是提供统一的公共函数来处理文件、网络和数据压缩等操作。简单而言，
  流是具有流式行为的资源对象。流可以线性读写，并且可以通过fseek()之类的函数
  定位到流中的任何位置。简化一点，其实流的作用是在出发地和目的地之间传输数
  据。出发地和目的地可以是文件、命令行进程、网络连接、zip或tar压缩文件、临时
  内存、标准输入输出，或者是通过PHP流封装协议实现的任何其他资源。

    比如你读写了文件，其实就已经使用了流。流为PHP的很多IO函数提供了底层实现，
  如file_get_contents，fopen，fread，fwrite等等。PHP的流函数提供了不同资源的
  统一接口。

    我们可以把流比作管道，把水(资源数据)从一个地方引到另一个地方。从水的出发
  地到目的地的过程中，我们可以过滤水，可以改变水质，可以添加水，可以排出水。

二.流封装协议
    流式数据种类各异，每种类型需要独特的协议，以便数据读写，我们把这些协议称
  为流封装协议。
    每个流都有一个协议和一个目标。指定协议和目标的方法是使用流标识符：
	<scheme>://<target>
    <scheme>是流的封装协议，<target>是流的数据源

    1.http://流封装协议
	file_get_contents("http://www.google.com");
      以上是使用了http流封装协议创建了一个与google的通信http流，file_get_contents
    函数的字符串参数其实是一个流标识符。http协议会让PHP使用http流封装协议，在
    这个参数中，http之后就是流的目标。
    注意：普通的URL其实是PHP流封装协议标识符的伪装。

    2.file://流封装协议
      常用的fopen，fwrite，fclose等函数，使用的都是PHP流。PHP默认使用的流封装
    协议是file://。
	$handle = fopen("file:///etc/hosts", "rb");
      通常会省略file://，因为这个是默认值。

    3.php://流封装协议
      · php://stdin 只读PHP流，其中的数据来自标准输入。可使用它来接受命令行传入
    的数据。
      · php://stdout 把数据写入当前的缓冲区，只写。
      · php://input 访问请求的原始数据的只读流。当enctype="multipart/form-data"
    时，php://input无效。

    4.更多数据流参考官网：http://php.net/manual/zh/wrappers.php.php

三.流上下文
    有些PHP流能接受一系列的可选参数，这些参数称为流上下文，用于指定流的行为。
  不同流封装协议使用的流上下文有所不同。流上下文使用stream_context_create()
  函数创建。

	stream_context_create ([ array $options [, array $params ]] ) : resource
    参数：
	options 必须是一个二维关联数组，格式如：$arr['wrapper']['option'] = $value
		默认是个空数组
	params 必须是$arr['parameter'] = $value格式的关联数组
    返回：
	上下文资源流，类型为resource
    For Example：
	$opts = array(
		'http' => array(
			'method' => 'GET',
			'header' => 'Accept-language:zh\r\n',
		),
	);
	$context = stream_context_create($opts);
	$fp = file_get_contents("http://google.com", false, $context);
    流上下文是个关联数组，最外层是流封装协议的名称，不同流协议封装，流上下文数组
  中的值不同。

四.流过滤器
    PHP流真正强大的地方在于过滤、转换、添加或删除流中传输的数据。PHP所有可用的
  的流过滤器可参考官方文档：http://php.net/manual/zh/filters.php
    如果想把过滤器附加到现有的流上，要使用stream_filter_append()函数，下面以
  string.toupper过滤器为例子：
	$handle = fopen("test.txt", "rb");
	stream_filter_append($handle, "string.toupper");
	while(feof($handle) !== true){
		echo fget($handle);
	}	
	fclose($handle);

五.自定义流过滤器
    我们可以编写自定义的流过滤器。自定义的流过滤器是个PHP类，需要继承php_user_filter
  类，并且要实现filter()，onCreate()，onClose()方法，最后使用stream_filter_register()
  函数注册自定义的流过滤器。
    注：PHP流会把数据分成按次序排序的桶，一个桶盛放的流数据是固定的，如果还用管道
  比喻，就是把水放到一个个水桶内，顺着管道从出发地漂流到目的地，在漂流过程中会进过
  过滤器，过滤器一次可以处理一个或多个桶，一定时间内过滤器接收到的桶叫桶排序。桶队
  列中的每个桶对象有两个公共属性，data和datalen，表示桶的内容和长度。以下为一个例子：


**************************************************************************/


/**
 * 脏词过滤器
 * 1.php自定义过滤器必须继承内置php_user_filter类
 * 2.必须实现filter方法
 * 3.必须使用stream_filter_register注册自定义过滤器
 */
class DirtyWordsFilter extends php_user_filter{
   /** 
    * @desc: 过滤方法
    * @param resource $in 流入的桶队列
    * @param resource $out 流出的桶队列
    * @param int &$consumed 处理的字节数
    * @param bool $closing 是否最后一个桶队列
    */
    public function filter($in, $out, &$consumed, $closing){
        $words = array('fuck', 'asshole', 'bitch');
        $wordData = array();

        foreach($words as $word){
            $replacement = array_fill(0, strlen($word), '*');
            $wordData[$word] = implode('', $replacement);
        }   

        $bad_word = array_keys($wordData);
        $replace_str = array_values($wordData);

        while($bucket = stream_bucket_make_writeable($in)){ //迭代每一个流入的桶
            $bucket->data = str_replace($bad_word, $replace_str, $bucket->data);//替换bad word
            $consumed += $bucket->datalen; //增加已处理的数据量
            stream_bucket_append($out, $bucket); //将该桶对象放入流向下游的队列   
        }   

        return PSFS_PASS_ON; //过滤器处理成功，输出流有可用数据返回 (PSFS_FEED_ME-过滤器处理成功，但没有数据返回; PSFS_ERR_FATAL-默认返回值，有错误，不返回
    }
}

stream_filter_register('dirty_words_filter', 'DirtyWordsFilter');//注册流过滤器

//TEST CODE
$handle = fopen('bad_word.txt', 'rb');
stream_filter_append($handle, 'dirty_words_filter');
while(feof($handle) !== true){
    echo fgets($handle);
}
fclose($handle);
