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