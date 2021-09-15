### 使用try-with-resources代替try-catch-finally

try-with-resources 是 JDK 7 中一个新的异常处理机制，它能够很容易地关闭在 try-catch 语句块中使用的资源。所谓的资源（resource）是指在程序完成后，必须关闭的对象。try-with-resources 语句确保了每个资源在语句结束时关闭。所有实现了 java.lang.AutoCloseable 接口（其中，它包括实现了 java.io.Closeable 的所有对象），可以使用作为资源。

来看看try-catch-finally的用法

```
try {
    BufferedReader br = new Buffered(new FileReader(filePath));
    return br.readLine();
} catch {
    // do something
} finally {
    if (br != null) {
        br.close();
    }
}
```

这个例子中，br.readLine() 和 br.close() 都会抛出异常。如果 finally 中出现异常，那么 try-catch 中抛出的异常将会被抑制，捕获不到try-catch中的异常。而且如果有多个资源需要被关闭，那么代码处理将会变得复杂。


参考文章：
https://www.baeldung.com/java-try-with-resources
https://www.cnblogs.com/xhj123/p/12578419.html

