我们先来看下maven中的配置
```
<dependency>
    <groupId>com.a.b</groupId>
    <artifactId>aaa</artifactId>
    <version>1.0.0</version>
</dependency>
```

可配参数 scope 定义依赖引入包的生命周期，
  * 默认值为 compile，表示在编译、测试、运行 整个生命周期都可以使用。而且这个dependency会传递到依赖的项目中。
  * provided，表示只在编译、测试时使用，没有传递性。比如你提供了一个api，api要依赖某个包，但这个包在使用你api的一方是必须引用的，为了方便测试以及避免包版本冲突，可以在api的pom文件中把scope设置为provided。
  * runtime 表示在 测试、运行时使用。
  * test，只在测试阶段使用，用于编译、运行测试代码，不会随项目发布。
  * system，编译、测试阶段有效，和provided的区别是，使用system范围的依赖，必须通过systemPath元素显式地指定依赖文件路径。此类依赖不是通过maven仓库解析的，与本机系统绑定，可能造成构建不可移植，谨慎使用。
    ```
    <dependency>
        <groupId>com.a.b</groupId>
        <artifactId>aaa</artifactId>
        <version>1.0.0</version>
        <scope>system</scope>
        <systemPath>${java.homt}/lib/a.jar</systemPath>
    </dependency>
    ```
