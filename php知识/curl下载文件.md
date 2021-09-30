### php使用curl下载文件

linux中可以使用wget进行文件下载，php中则可以使用curl下载文件。代码如下：

```
function download() {
    $curl = curl_init();

    // 设置代理，外网被屏蔽的情况下
    // curl_setopt($curl, CURLOPT_PROXY, 'http://proxyhost:port');

    // 设置要下载的文件
    curl_setopt($curl, CURLOPT_URL, 'http://xxx.pdf');

    // 设置header
    curl_setopt($curl, CURLOPT_HEADER, 1);

    // 设置获取的信息以文件流的形式返回，非直接输出
    curl_setopt($curl, CURLOPT_RETURNTRANSFER, 1);

    $data = curl_exec($curl);

    /** 
     * 获取的data包含 header 和 body 内容
     * 需要计算header长度，减去header部分才是文件内容
     */
    $headerSize = curl_getinfo($curl, CURLINFO_HEADER_SIZE);
    $body = substr($data, $headerSize);

    // 文件保存路径
    $path = "/data/xxx.pdf";
    file_put_contents($path, $body) 

    curl_close($curl);
}
```