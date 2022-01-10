### 参数介绍

-server: Server模式可以大大提高性能，但应用的启动会比client模式慢大概10%。当JVM用于启动GUI界面的交互应用时适合于使用client模式，当JVM用于运行服务器后台程序时建议用Server模式。

-Xmx: 指定堆内存的最大值。JVM最大可用内存。

-Xms: 指定堆内存的初始大小（最小值）。JVM最小可用内存。 通常 -Xmx，-Xms 参数值设为一样，可避免堆空间在动态扩容时的开销。

-Xmn: 设置年轻代的大小，默认年轻代占堆大小的1/3。

https://www.cnblogs.com/duanxz/p/3520829.html
https://blog.csdn.net/vincent_wen0766/article/details/111638554
https://blog.csdn.net/earbao/article/details/103495508
https://liujiacai.net/blog/2018/06/15/garbage-collection-intro/

### 面试题
大厂面试题：

1、JVM垃圾回收时候如何确定垃圾？是否知道什么是GC Roots

2、你说你做过JVM参数调优和参数配置，请问如何盘点查看JVM系统默认值

3、你平时工作中用过的JVM常用基本配置参数有哪些？

4、强引用、软引用、弱引用、虚引用费别是什么？

5、请你谈谈对OOM的认识

6、GC垃圾回收算法与垃圾收集器的关系？分别是什么请你谈谈？

7、 怎么查看服务器默认的垃圾回收器是哪一个？

       生产上如何配置垃圾收集器的？

       谈谈你对垃圾收集器的理解？

8、G1垃圾收集器

9、生产环境服务器变慢，诊断思路和性能评估谈谈？

10、假如生产环境CPU占用过高，请谈谈你的分析思路和定位。

11、对于JDK自带的监控和性能分析工具用过哪些？一般你怎么用的？