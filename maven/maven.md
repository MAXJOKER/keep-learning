### Maven ###
简化和标准化项目构建过程。

maven项目的结构和内容在XML文件中生命，pom.xml

setting.xml 可以指定本地仓库目录
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
    <url>xxxxxxx</url>
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