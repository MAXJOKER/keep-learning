### Maven ###
简化和标准化项目构建过程。

maven项目的结构和内容在XML文件中生命，pom.xml中

<b>setting.xml</b> 可以指定本地仓库目录
```
<!-- 本地仓库设置 -->
<localRepository>/path/to/local/repo</localRepository>

<!-- 远程仓库设置 下面两个id要保持一致-->
<!-- 账密 -->
<server>
    <id>abc</id>
    <username>ddd<username>
    <password>eee<password>
</server>
<!-- 仓库地址 -->
<mirror>
    <id>abc</id>
    <mirrorOf>*</mirrorOf>
    <url>xxxxxxx</url> // 远程仓库url
</mirror>
```

Group Id, Artifact Id 保证项目的唯一性。

Group Id 一般分为多段，用.分隔。 第一段为 域，org，com等，第二段为公司名称，第三段为组名... 
Artifact Id 为项目的唯一标识符。

#### 工程目录 ####
```
    src // 源码
        main // 存放主程序
            java // java源代码
            resources // 存放框架或者其他工具的配置文件
        test // 存放测试程序
            java // 测试代码
            resources // 测试配置文件
    pom.xml  // 核心配置文件
```

pom.xml Project Object Model 项目对象模型，Maven的核心配置文件，与构建过程相关的一切设置都在这个文件进行设置。

#### 常用命令 ####
```
mvn compile // 编译，将java源代码编译成class字节码文件

mvn test // 测试，并生成测试报告

mvn clean // 将编译的class字节码文件删除

mvn package // 打包，动态web工程打 war包，java工程打 jar包

mvn install // 将项目生成的jar包放在仓库中，以便别的模块调用
```

#### 引入依赖 & 排除依赖 ####
```
<dependency> // 依赖引入
    <groupId>com.a.b</groupId>
    <artifactId>abc</artifactId>
    <version>1.0-SNAPSHOT</version>
    <optional>true</optional> // 　Optional标签标示该依赖是否可选，默认是false。可以理解为，如果为true，则表示该依赖不会传递下去，如果为false，则会传递下去。
    <exclusions> // 依赖排除
        <exclusion>
            <artifactId>aaa</artifactId>
            <groupId>bbb</groupId>
        </exclusion>
    </exclusions>
</dependency>
```

#### maven 生命周期 ####
<b>在maven中，只要在同一个生命周期，你执行后面的阶段，那么前面的阶段也会被执行，而且不需要额外去输入前面的阶段。</b>

1. <b>Clean Lifecycle</b>：在进行真正的构建前进行一些清理操作
   ```
   pre-clean 执行一些需要在clean前完成的工作
   clean 移除所有上一次构建生成的文件
   post-clean 执行一些需要在clean后完成的工作
   ```
2. <b>Default Lifecycle</b>：核心的构建部分，编译、测试、打包、安装、部署等
   ```
    validate
    generate-sources
    process-sources
    generate-resources 复制并处理资源文件至目标目录，准备打包
    process-resources
    compile 编译项目源代码
    process-classes
    generate-test-sources
    process-test-sources
    generate-test-resources
    process-test-resources 复制并处理资源文件至目标测试目录
    test-compile 编译测试源代码
    process-test-classes
    test 使用合适的单元测试框架运行测试，测试代码不会被打包或部署
    prepare-package
    package 接收编译好的代码，打包成可发布的格式，如 jar
    pre-integration-test
    integration-test
    post-integration-test
    verify
    install 将安装包移至本地仓库
    deploy 将最终的包复制到远程，让其他开发人员可以使用
   ```
3. <b>Site Lifecycle</b>：生成项目报告，站点，发布站点，现在已有专门的工具生成文档或报表，功能比较鸡肋。
   ```
    pre-site 执行一些需要在生成站点文档前完成的工作
    site 生成项目的站点文档
    post-site 执行一些需要在生成站点后完成的工作
   ```

这三个是互相独立的，仅能调用clean进行清理工作目录，仅能调用site来生成站点，也可以直接运行 mvn clean install site 运行所有这三套生命周期。


#### 继承与聚合 ####
如果项目中有多个模块，多个模块都依赖了某个jar包，那么可以将对应的依赖统一到父工程中，在父工程中指定要用的依赖及版本
```
    <parent>
        <groupId>com.a.b</groupId>
        <artifactId>parent</artifactId>
        <version>1.0.0</version>
    </parent>

    然后在父工程 parent 的 pom.xml 文件中 引入共同用到依赖
    
    <properties> // properties中指定变量
        <version>1.0.0</version>
    </properties>


    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>com.b.c</groupId>
                <artifactId>ddd</artifactId>
                <version>${version}</version>
            </dependency>
        </dependencies>
    </dependencyManagement> // dependencyManagement标签管理的依赖，其实没有真正依赖，它只是管理依赖的版本。


    // 多个模块聚合
    <modules>
        <module>a</module>
        <module>b</module>
        <module>c</module>
    </modules>
    
```