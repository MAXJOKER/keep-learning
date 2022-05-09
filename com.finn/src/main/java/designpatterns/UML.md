### UML(Unified Modeling Language)统一建模语言

#### 基本构件

UML 建模的核心是模型，模型是现实的简化、真实系统的抽象。UML 提供了系统的设计蓝图。当给软件系统建模时，需要采用通用的符号语言，
这种描述模型所使用的语言被称为建模语言。在 UML 中，所有的描述由事物、关系和图这些构件组成。

##### 事物
事物是抽象化的最终结果，分为结构事物、行为事物、分组事物和注释事物。

1. 结构事物
   
    结构事物是模型中的静态部分，用以呈现概念或实体的表现元素，如下表所示。
<html>
 <head></head>
 <body>
  <table> 
   <tbody> 
    <tr> 
     <th> 事物</th> 
     <th> 解释</th> 
     <th> 图例</th> 
    </tr> 
    <tr> 
     <td> 类（Class）</td> 
     <td> 具有相同属性、方法、关系和语义的对象集合</td> 
     <td> <img alt="" src="http://c.biancheng.net/uploads/allimg/200901/5-200Z1100S62Y.jpg" /></td> 
    </tr> 
    <tr> 
     <td> 接口（Interface）</td> 
     <td> 指一个类或构件的一个服务的操作集合，它仅仅定义了一组操作的规范，并没有给出这组操作的具体实现</td> 
     <td> <img alt="" src="http://c.biancheng.net/uploads/allimg/200901/5-200Z1101120163.jpg" /></td> 
    </tr> 
    <tr> 
     <td> 用例（User Case）</td> 
     <td> 指对一组动作序列的描述，系统执行这些动作将产生一个对特定的参与者（Actor）有价值且可观察的结果</td> 
     <td> <img alt="" src="http://c.biancheng.net/uploads/allimg/200901/5-200Z1101355293.jpg" /></td> 
    </tr> 
    <tr> 
     <td> 协作（Collaboration）</td> 
     <td> 定义元素之间的相互作用</td> 
     <td> <img alt="" src="http://c.biancheng.net/uploads/allimg/200901/5-200Z1101541915.jpg" /></td> 
    </tr> 
    <tr> 
     <td> 组件（Component）</td> 
     <td> 描述物理系统的一部分</td> 
     <td> <img alt="" src="http://c.biancheng.net/uploads/allimg/200901/5-200Z1102250437.jpg" /></td> 
    </tr> 
    <tr> 
     <td> 活动类（Active Class）</td> 
     <td> 指对象有一个或多个进程或线程。活动类和类很相象，只是它的对象代表的元素的行为和其他元素是同时存在的</td> 
     <td> <img alt="" src="http://c.biancheng.net/uploads/allimg/200901/5-200Z1104602M2.jpg" /></td> 
    </tr> 
    <tr> 
     <td> 节点（Node）</td> 
     <td> 定义为运行时存在的物理元素</td> 
     <td> <img alt="" src="http://c.biancheng.net/uploads/allimg/200901/5-200Z11046434W.jpg" /></td> 
    </tr> 
   </tbody> 
  </table>
 </body>
</html>

2. 行为事物 
   
行为事物指 UML 模型中的动态部分，如下表所示。
<html>
 <head></head>
 <body>
  <table> 
   <tbody> 
    <tr> 
     <th> 事物</th> 
     <th> 解释</th> 
     <th> 用例</th> 
    </tr> 
    <tr> 
     <td> 交互（Interaction）</td> 
     <td> 包括一组元素之间的消息交换</td> 
     <td> <img alt="" src="http://c.biancheng.net/uploads/allimg/200901/5-200Z1105G5541.jpg" /></td> 
    </tr> 
    <tr> 
     <td> 状态机（State Machine）</td> 
     <td> 由一系列对象的状态组成</td> 
     <td> <img alt="" src="http://c.biancheng.net/uploads/allimg/200901/5-200Z1105K5108.jpg" /></td> 
    </tr> 
   </tbody> 
  </table>
 </body>
</html>

3. 分组事物
   
   目前只有一种分组事物，即包。包纯碎是概念上的，只存在于开发阶段，结构事物、行为事物甚至分组事物都有可能放在一个包中，如下表所示。
<html>
 <head></head>
 <body>
  <table> 
   <tbody> 
    <tr> 
     <th> 事物</th> 
     <th> 解释</th> 
     <th> 用例</th> 
    </tr> 
    <tr> 
     <td> 包（Package）</td> 
     <td> UML中唯一的组织机制</td> 
     <td> <img alt="" src="http://c.biancheng.net/uploads/allimg/200901/5-200Z1105Z4P0.jpg" /></td> 
    </tr> 
   </tbody> 
  </table>
 </body>
</html>

4. 注释事物
   
   注释事物是解释 UML 模型元素的部分，如下表所示。
<html>
 <head></head>
 <body>
  <table> 
   <tbody> 
    <tr> 
     <th> 事物</th> 
     <th> 解释</th> 
     <th> 用例</th> 
    </tr> 
    <tr> 
     <td> 注释（Note）</td> 
     <td> 用于解析说明 UML 元素</td> 
     <td> <img alt="" src="http://c.biancheng.net/uploads/allimg/200901/5-200Z1110006149.jpg" /></td> 
    </tr> 
   </tbody> 
  </table>
 </body>
</html>


##### 图
UML2.0 一共有 13 种图（UML1.5 定义了 9 种，UML2.0 增加了 4 种），分别是类图、对象图、构件图、部署图、活动图、状态图、用例图、时序图、
协作图 9 种，以及包图、组合结构图、时间图、交互概览图 4 种。
<html>
 <head></head>
 <body>
  <table> 
   <tbody> 
    <tr> 
     <th> 图名称</th> 
     <th> 解释</th> 
    </tr> 
    <tr> 
     <td> 类图（Class Diagrams）</td> 
     <td> 用于定义系统中的类</td> 
    </tr> 
    <tr> 
     <td> 对象图（Object Diagrams）</td> 
     <td> 类图的一个实例，描述了系统在具体时间点上所包含的对象及各个对象之间的关系</td> 
    </tr> 
    <tr> 
     <td> 构件图（Component Diagrams）</td> 
     <td> 一种特殊的 UML 图，描述系统的静态实现视图</td> 
    </tr> 
    <tr> 
     <td> 部署图（Deployment Diagrams）</td> 
     <td> 定义系统中软硬件的物理体系结构</td> 
    </tr> 
    <tr> 
     <td> 活动图（Activity Diagrams）</td> 
     <td> 用来描述满足用例要求所要进行的活动及活动间的约束关系</td> 
    </tr> 
    <tr> 
     <td> 状态图（State Chart Diagrams）</td> 
     <td> 用来描述类的对象的所有可能的状态和时间发生时，状态的转移条件</td> 
    </tr> 
    <tr> 
     <td> 用例图（Usecase Diagrams）</td> 
     <td> 用来描述用户的需求，从用户的角度描述系统的功能，并指出各功能的执行者，强调谁在使用系统、系统为执行者完成哪些功能</td> 
    </tr> 
    <tr> 
     <td> 时序图（Sequence Diagrams）</td> 
     <td> 描述对象之间的交互顺序，着重体现对象间消息传递的时间顺序，强调对象之间消息的发送顺序，同时显示对象之间的交互过程</td> 
    </tr> 
    <tr> 
     <td> 协作图（Collaboration Diagrams）</td> 
     <td> 描述对象之间的合作关系，更侧重向用户对象说明哪些对象有消息的传递</td> 
    </tr> 
    <tr> 
     <td> 包图（Package Diagrams）</td> 
     <td> 对构成系统的模型元素进行分组整理的图</td> 
    </tr> 
    <tr> 
     <td> 组合结构图（Composite Structure Diagrams）</td> 
     <td> 表示类或者构建内部结构的图</td> 
    </tr> 
    <tr> 
     <td> 时间图（Timing Diagrams）</td> 
     <td> 用来显示随时间变化，一个或多个元素的值或状态的更改，也显示时间控制事件之间的交互及管理它们的时间和期限约束</td> 
    </tr> 
    <tr> 
     <td> 交互概览图（Interaction Overview Diagrams）</td> 
     <td> 用活动图来表示多个交互之间的控制关系的图</td> 
    </tr> 
   </tbody> 
  </table>
 </body>
</html>


#### UML类图及类图之间的关系

##### 类、接口和类图

1. 类
   
   类（Class）是指具有相同属性、方法和关系的对象的抽象，它封装了数据和行为，是面向对象程序设计（OOP）的基础，具有封装性、继承性和多态性等三大特性。
   在 UML 中，类使用包含类名、属性和操作且带有分隔线的矩形来表示。

(1) 类名（Name）是一个字符串，例如，Student。
   
(2) 属性（Attribute）是指类的特性，即类的成员变量。UML 按以下格式表示：
[可见性]属性名:类型[=默认值]
例如：-name:String

注意：“可见性”表示该属性对类外的元素是否可见，包括公有（Public）、私有（Private）、受保护（Protected）和朋友（Friendly）4 种，在类图中分别用符号+、-、#、~表示。
    
(3) 操作（Operations）是类的任意一个实例对象都可以使用的行为，是类的成员方法。UML 按以下格式表示：
[可见性]名称(参数列表)[:返回类型]

示例：

<img src = "http://c.biancheng.net/uploads/allimg/181112/3-1Q1121K933555.gif">

    类图用 3 个矩形拼接表示，最上面的部分标识类的名称，中间的部分标识类的属性，最下面的部分标识类的方法。

类图中，需注意以下几点：
* 抽象类或抽象方法用斜体表示
* 如果是接口，则在类名上方加 <<Interface>>
* 字段和方法返回值的数据类型非必需
* 静态类或静态方法加下划线

2. 接口

接口（Interface）是一种特殊的类，它具有类的结构但不可被实例化，只可以被子类实现。它包含抽象操作，但不包含属性。它描述了类或组件对外可见的动作。
在 UML 中，接口使用一个带有名称的小圆圈来进行表示。

如下所示是图形类接口的 UML 表示。

<img src = "http://c.biancheng.net/uploads/allimg/181112/3-1Q1121K9493J.gif">

3. 类图
   
   类图（ClassDiagram）是用来显示系统中的类、接口、协作以及它们之间的静态结构和关系的一种静态模型。它主要用于描述软件系统的结构化设计，
   帮助人们简化对软件系统的理解，它是系统分析与设计阶段的重要产物，也是系统编码与测试的重要模型依据。

类图中的类可以通过某种编程语言直接实现。类图在软件系统开发的整个生命周期都是有效的，它是面向对象系统的建模中最常见的图。
如下所示是“计算长方形和圆形的周长与面积”的类图，图形接口有计算面积和周长的抽象方法，长方形和圆形实现这两个方法供访问类调用。

<img src = "http://c.biancheng.net/uploads/allimg/181112/3-1Q1121P6195T.gif">


##### 类之间的关系

UML 将事物之间的联系归纳为 6 种，并用对应的图形类表示。下面根据类与类之间的耦合度从弱到强排列。UML 中的类图有以下几种关系：
依赖关系、关联关系、聚合关系、组合关系、泛化关系和实现关系。其中泛化和实现的耦合度相等，它们是最强的。


1. 依赖关系
   
   依赖（Dependency）关系是一种使用关系，它是对象之间耦合度最弱的一种关联方式，是临时性的关联。在代码中，某个类的方法通过局部变量、
   方法的参数或者对静态方法的调用来访问另一个类（被依赖类）中的某些方法来完成一些职责。

在 UML 类图中，依赖关系使用带箭头的虚线来表示，箭头从使用类指向被依赖的类。如下是人与手机的关系图，人通过手机的语音传送方法打电话。

<img src = "http://c.biancheng.net/uploads/allimg/181112/3-1Q1121PA2Y5.gif">

2. 关联关系
   
   关联（Association）关系是对象之间的一种引用关系，用于表示一类对象与另一类对象之间的联系，如老师和学生、师傅和徒弟、丈夫和妻子等。
   关联关系是类与类之间最常用的一种关系，分为一般关联关系、聚合关系和组合关系。我们先介绍一般关联。

关联可以是双向的，也可以是单向的。在 UML 类图中，双向的关联可以用带两个箭头或者没有箭头的实线来表示，单向的关联用带一个箭头的实线来表示，
箭头从使用类指向被关联的类。也可以在关联线的两端标注角色名，代表两种不同的角色。

在代码中通常将一个类的对象作为另一个类的成员变量来实现关联关系。如下是老师和学生的关系图，每个老师可以教多个学生，每个学生也可向多个老师学，他们是双向关联。

<img src = "http://c.biancheng.net/uploads/allimg/181112/3-1Q1121Q5115Q.gif">

3. 聚合关系
   
   聚合（Aggregation）关系是关联关系的一种，是强关联关系，是整体和部分之间的关系，是 has-a 的关系。

聚合关系也是通过成员对象来实现的，其中成员对象是整体对象的一部分，但是成员对象可以脱离整体对象而独立存在。例如，学校与老师的关系，学校包含老师，但如果学校停办了，老师依然存在。

在 UML 类图中，聚合关系可以用带空心菱形的实线来表示，菱形指向整体。如下是大学和教师的关系图。

<img src = "http://c.biancheng.net/uploads/allimg/181112/3-1Q1121Q541410.gif">

4.组合关系

组合（Composition）关系也是关联关系的一种，也表示类之间的整体与部分的关系，但它是一种更强烈的聚合关系，是 contains-a 关系。

在组合关系中，整体对象可以控制部分对象的生命周期，一旦整体对象不存在，部分对象也将不存在，部分对象不能脱离整体对象而存在。
例如，头和嘴的关系，没有了头，嘴也就不存在了。

在 UML 类图中，组合关系用带实心菱形的实线来表示，菱形指向整体。如下是头和嘴的关系图。

<img src = "http://c.biancheng.net/uploads/allimg/181112/3-1Q1121QFD27.gif">

5.泛化关系

泛化（Generalization）关系是对象之间耦合度最大的一种关系，表示一般与特殊的关系，是父类与子类之间的关系，是一种继承关系，是 is-a 的关系。

在 UML 类图中，泛化关系用带空心三角箭头的实线来表示，箭头从子类指向父类。在代码实现时，使用面向对象的继承机制来实现泛化关系。
例如，Student 类和 Teacher 类都是 learnjava.Person 类的子类，其类图如下所示。

<img src = "http://c.biancheng.net/uploads/allimg/181112/3-1Q1121Q62C57.gif">

6.实现关系

实现（Realization）关系是接口与实现类之间的关系。在这种关系中，类实现了接口，类中的操作实现了接口中所声明的所有的抽象操作。

在 UML 类图中，实现关系使用带空心三角箭头的虚线来表示，箭头从实现类指向接口。例如，汽车和船实现了交通工具，其类图如下所示。

<img src = "http://c.biancheng.net/uploads/allimg/181112/3-1Q1121QI4317.gif">

