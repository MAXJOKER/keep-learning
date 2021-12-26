　　【转载】请尊重作者劳动成果，转载请标明转载地址：

 　　http://www.cnblogs.com/dolphin0520/p/3778589.html

相信String这个类是Java中使用得最频繁的类之一，并且又是各大公司面试喜欢问到的地方，今天就来和大家一起学习一下String、StringBuilder和StringBuffer这几个类，分析它们的异同点以及了解各个类适用的场景。下面是本文的目录大纲：

　　一.你了解String类吗？

　　二.深入理解String、StringBuffer、StringBuilder

　　三.不同场景下三个类的性能测试

　　四.常见的关于String、StringBuffer的面试题（辟谣网上流传的一些曲解String类的说法）

　　若有不正之处，请多多谅解和指正，不胜感激。



### 一.你了解String类吗？ ###
　　想要了解一个类，最好的办法就是看这个类的实现源代码，String类的实现在

　　\jdk1.6.0_14\src\java\lang\String.java   文件中。

　　打开这个类文件就会发现String类是被final修饰的：
```
public final class String
    implements java.io.Serializable, Comparable<String>, CharSequence
{
    /** The value is used for character storage. */
    private final char value[];
 
    /** The offset is the first index of the storage that is used. */
    private final int offset;
 
    /** The count is the number of characters in the String. */
    private final int count;
 
    /** Cache the hash code for the string */
    private int hash; // Default to 0
 
    /** use serialVersionUID from JDK 1.0.2 for interoperability */
    private static final long serialVersionUID = -6849794470754667710L;
 
    ......
}
```

　　从上面可以看出几点：

　　1）String类是final类，也即意味着String类不能被继承，并且它的成员方法都默认为final方法。在Java中，被final修饰的类是不允许被继承的，并且该类中的成员方法都默认为final方法。在早期的JVM实现版本中，被final修饰的方法会被转为内嵌调用以提升执行效率。而从Java SE5/6开始，就渐渐摈弃这种方式了。因此在现在的Java SE版本中，不需要考虑用final去提升方法调用效率。只有在确定不想让该方法被覆盖时，才将方法设置为final。

　　2）上面列举出了String类中所有的成员属性，从上面可以看出String类其实是通过char数组来保存字符串的。

　　下面再继续看String类的一些方法实现：

```
public String substring(int beginIndex, int endIndex) {
    if (beginIndex < 0) {
        throw new StringIndexOutOfBoundsException(beginIndex);
    }
    if (endIndex > count) {
        throw new StringIndexOutOfBoundsException(endIndex);
    }
    if (beginIndex > endIndex) {
        throw new StringIndexOutOfBoundsException(endIndex - beginIndex);
    }
    return ((beginIndex == 0) && (endIndex == count)) ? this :
        new String(offset + beginIndex, endIndex - beginIndex, value);
    }
 
 public String concat(String str) {
    int otherLen = str.length();
    if (otherLen == 0) {
        return this;
    }
    char buf[] = new char[count + otherLen];
    getChars(0, count, buf, 0);
    str.getChars(0, otherLen, buf, count);
    return new String(0, count + otherLen, buf);
    }
 
 public String replace(char oldChar, char newChar) {
    if (oldChar != newChar) {
        int len = count;
        int i = -1;
        char[] val = value; /* avoid getfield opcode */
        int off = offset;   /* avoid getfield opcode */
 
        while (++i < len) {
        if (val[off + i] == oldChar) {
            break;
        }
        }
        if (i < len) {
        char buf[] = new char[len];
        for (int j = 0 ; j < i ; j++) {
            buf[j] = val[off+j];
        }
        while (i < len) {
            char c = val[off + i];
            buf[i] = (c == oldChar) ? newChar : c;
            i++;
        }
        return new String(0, len, buf);
        }
    }
    return this;
```

　　从上面的三个方法可以看出，无论是sub操、concat还是replace操作都不是在原有的字符串上进行的，而是重新生成了一个新的字符串对象。也就是说进行这些操作后，最原始的字符串并没有被改变。

　　在这里要永远记住一点：

　　<font color=red>“对String对象的任何改变都不影响到原对象，相关的任何change操作都会生成新的对象”。</font>

　　在了解了于String类基础的知识后，下面来看一些在平常使用中容易忽略和混淆的地方。

### 二.深入理解String、StringBuffer、StringBuilder ###

1.String str="hello world"和String str=new String("hello world")的区别

　　想必大家对上面2个语句都不陌生，在平时写代码的过程中也经常遇到，那么它们到底有什么区别和联系呢？下面先看几个例子：
```
public class Main {
         
    public static void main(String[] args) {
        String str1 = "hello world";
        String str2 = new String("hello world");
        String str3 = "hello world";
        String str4 = new String("hello world");
         
        System.out.println(str1==str2);
        System.out.println(str1==str3);
        System.out.println(str2==str4);
    }
}
```
　　这段代码的输出结果为
```
false
true
false
```
　　
　　为什么会出现这样的结果？下面解释一下原因：

　　在前面一篇讲解关于JVM内存机制的一篇博文中提到 ，在class文件中有一部分 来存储编译期间生成的 字面常量以及符号引用，这部分叫做class文件常量池，在运行期间对应着方法区的运行时常量池。

　　因此在上述代码中，String str1 = "hello world";和String str3 = "hello world"; 都在编译期间生成了 字面常量和符号引用，运行期间字面常量"hello world"被存储在运行时常量池（当然只保存了一份）。通过这种方式来将String对象跟引用绑定的话，JVM执行引擎会先在运行时常量池查找是否存在相同的字面常量，如果存在，则直接将引用指向已经存在的字面常量；否则在运行时常量池开辟一个空间来存储该字面常量，并将引用指向该字面常量。

　　众所周知，通过new关键字来生成对象是在堆区进行的，而在堆区进行对象生成的过程是不会去检测该对象是否已经存在的。因此通过new来创建对象，创建出的一定是不同的对象，即使字符串的内容是相同的。

2.String、StringBuffer以及StringBuilder的区别

　　既然在Java中已经存在了String类，那为什么还需要StringBuilder和StringBuffer类呢？

　　那么看下面这段代码：
```
public class Main {
         
    public static void main(String[] args) {
        String string = "";
        for(int i=0;i<10000;i++){
            string += "hello";
        }
    }
}
```
　　这句 string += "hello";的过程相当于将原有的string变量指向的对象内容取出与"hello"作字符串相加操作再存进另一个新的String对象当中，再让string变量指向新生成的对象。如果大家还有疑问可以反编译其字节码文件便清楚了：

　　

　　从这段反编译出的字节码文件可以很清楚地看出：从第8行开始到第35行是整个循环的执行过程，并且每次循环会new出一个StringBuilder对象，然后进行append操作，最后通过toString方法返回String对象。也就是说这个循环执行完毕new出了10000个对象，试想一下，如果这些对象没有被回收，会造成多大的内存资源浪费。从上面还可以看出：string+="hello"的操作事实上会自动被JVM优化成：
```
　　StringBuilder str = new StringBuilder(string);

　　str.append("hello");

　　str.toString();
```

　　再看下面这段代码：
```
public class Main {
         
    public static void main(String[] args) {
        StringBuilder stringBuilder = new StringBuilder();
        for(int i=0;i<10000;i++){
            stringBuilder.append("hello");
        }
    }
}
```
　　反编译字节码文件得到：

　　

　　从这里可以明显看出，这段代码的for循环式从13行开始到27行结束，并且new操作只进行了一次，也就是说只生成了一个对象，append操作是在原有对象的基础上进行的。因此在循环了10000次之后，这段代码所占的资源要比上面小得多。

　　那么有人会问既然有了StringBuilder类，为什么还需要StringBuffer类？查看源代码便一目了然，事实上，StringBuilder和StringBuffer类拥有的成员属性以及成员方法基本相同，区别是StringBuffer类的成员方法前面多了一个关键字：synchronized，不用多说，这个关键字是在多线程访问时起到安全保护作用的,也就是说StringBuffer是线程安全的。

　　下面摘了2段代码分别来自StringBuffer和StringBuilder，insert方法的具体实现：

　　StringBuilder的insert方法
```
public StringBuilder insert(int index, char str[], int offset,
                              int len)
  {
      super.insert(index, str, offset, len);
  return this;
  }
```

　　StringBuffer的insert方法：
```
public synchronized StringBuffer insert(int index, char str[], int offset,
                                            int len)
    {
        super.insert(index, str, offset, len);
        return this;
    }
```

### 三.不同场景下三个类的性能测试 ###
　　从第二节我们已经看出了三个类的区别，这一小节我们来做个小测试，来测试一下三个类的性能区别：
```
public class Main {
    private static int time = 50000;
    public static void main(String[] args) {
        testString();
        testStringBuffer();
        testStringBuilder();
        test1String();
        test2String();
    }
     
     
    public static void testString () {
        String s="";
        long begin = System.currentTimeMillis();
        for(int i=0; i<time; i++){
            s += "java";
        }
        long over = System.currentTimeMillis();
        System.out.println("操作"+s.getClass().getName()+"类型使用的时间为："+(over-begin)+"毫秒");
    }
     
    public static void testStringBuffer () {
        StringBuffer sb = new StringBuffer();
        long begin = System.currentTimeMillis();
        for(int i=0; i<time; i++){
            sb.append("java");
        }
        long over = System.currentTimeMillis();
        System.out.println("操作"+sb.getClass().getName()+"类型使用的时间为："+(over-begin)+"毫秒");
    }
     
    public static void testStringBuilder () {
        StringBuilder sb = new StringBuilder();
        long begin = System.currentTimeMillis();
        for(int i=0; i<time; i++){
            sb.append("java");
        }
        long over = System.currentTimeMillis();
        System.out.println("操作"+sb.getClass().getName()+"类型使用的时间为："+(over-begin)+"毫秒");
    }
     
    public static void test1String () {
        long begin = System.currentTimeMillis();
        for(int i=0; i<time; i++){
            String s = "I"+"love"+"java";
        }
        long over = System.currentTimeMillis();
        System.out.println("字符串直接相加操作："+(over-begin)+"毫秒");
    }
     
    public static void test2String () {
        String s1 ="I";
        String s2 = "love";
        String s3 = "java";
        long begin = System.currentTimeMillis();
        for(int i=0; i<time; i++){
            String s = s1+s2+s3;
        }
        long over = System.currentTimeMillis();
        System.out.println("字符串间接相加操作："+(over-begin)+"毫秒");
    }
     
}
```

　　测试结果（win7，Eclipse，JDK6)：
```
String 8098ms
StringBuffer 3ms
StringBuilder 2ms
字符串直接相加 1ms
字符串间接相加 9ms
```

　　
　　上面提到string+="hello"的操作事实上会自动被JVM优化，看下面这段代码：
```
public class Main {
    private static int time = 50000;
    public static void main(String[] args) {
        testString();
        testOptimalString();
    }
     
     
    public static void testString () {
        String s="";
        long begin = System.currentTimeMillis();
        for(int i=0; i<time; i++){
            s += "java";
        }
        long over = System.currentTimeMillis();
        System.out.println("操作"+s.getClass().getName()+"类型使用的时间为："+(over-begin)+"毫秒");
    }
     
    public static void testOptimalString () {
        String s="";
        long begin = System.currentTimeMillis();
        for(int i=0; i<time; i++){
            StringBuilder sb = new StringBuilder(s);
            sb.append("java");
            s=sb.toString();
        }
        long over = System.currentTimeMillis();
        System.out.println("模拟JVM优化操作的时间为："+(over-begin)+"毫秒");
    }
     
}
```
    
　　执行结果：
```
String 8654ms
模拟JVM优化操作时间 8651ms
```
　　

　　得到验证。

　　下面对上面的执行结果进行一般性的解释：

　　1）对于直接相加字符串，效率很高，因为在编译器便确定了它的值，也就是说形如"I"+"love"+"java"; 的字符串相加，在编译期间便被优化成了"Ilovejava"。这个可以用javap -c命令反编译生成的class文件进行验证。

　　对于间接相加（即包含字符串引用），形如s1+s2+s3; 效率要比直接相加低，因为在编译器不会对引用变量进行优化。

　　2）String、StringBuilder、StringBuffer三者的执行效率：

　　<B>StringBuilder > StringBuffer > String</B>

　　当然这个是相对的，不一定在所有情况下都是这样。

　　比如String str = "hello"+ "world"的效率就比 StringBuilder st  = new StringBuilder().append("hello").append("world")要高。

　　因此，这三个类是各有利弊，应当根据不同的情况来进行选择使用：

　　当字符串相加操作或者改动较少的情况下，建议使用 String str="hello"这种形式；

　　当字符串相加操作较多的情况下，建议使用StringBuilder，如果采用了多线程，则使用StringBuffer。

 

### 四.常见的关于String、StringBuffer的面试题 ###
　　下面是一些常见的关于String、StringBuffer的一些面试笔试题，若有不正之处，请谅解和批评指正。

1. 下面这段代码的输出结果是什么？
```
    String a = "hello2"; 　　
    String b = "hello" + 2;
    System.out.println((a == b));
```
　　输出结果为：true。原因很简单，"hello"+2在编译期间就已经被优化成"hello2"，因此在运行期间，变量a和变量b指向的是同一个对象。

2. 下面这段代码的输出结果是什么？
```
    String a = "hello2"; 　  
    String b = "hello";       
    String c = b + 2;       
    System.out.println((a == c));
```
　　输出结果为:false。由于有符号引用的存在，所以  String c = b + 2;不会在编译期间被优化，不会把b+2当做字面常量来处理的，因此这种方式生成的对象事实上是保存在堆上的。因此a和c指向的并不是同一个对象。javap -c得到的内容：

　　

3. 下面这段代码的输出结果是什么？
```
　　 String a = "hello2";   　 
    final String b = "hello";       
    String c = b + 2;       
    System.out.println((a == c));
``` 
　　输出结果为：true。对于被final修饰的变量，会在class文件常量池中保存一个副本，也就是说不会通过连接而进行访问，对final变量的访问在编译期间都会直接被替代为真实的值。那么String c = b + 2;在编译期间就会被优化成：String c = "hello" + 2; 下图是javap -c的内容：


4. 下面这段代码输出结果为：
```
public class Main {
    public static void main(String[] args) {
        String a = "hello2";
        final String b = getHello();
        String c = b + 2;
        System.out.println((a == c));
    }
     
    public static String getHello() {
        return "hello";
    }
}
```
　　输出结果为false。这里面虽然将b用final修饰了，但是由于其赋值是通过方法调用返回的，那么它的值只能在运行期间确定，因此a和c指向的不是同一个对象。

5. 下面这段代码的输出结果是什么？
```
public class Main {
    public static void main(String[] args) {
        String a = "hello";
        String b =  new String("hello");
        String c =  new String("hello");
        String d = b.intern();
         
        System.out.println(a==b); // false
        System.out.println(b==c); // false
        System.out.println(b==d); // false
        System.out.println(a==d); // true
    }
}
```

　　这里面涉及到的是String.intern方法的使用。在String类中，intern方法是一个本地方法，在JAVA SE6之前，intern方法会在运行时常量池中查找是否存在内容相同的字符串，如果存在则返回指向该字符串的引用，如果不存在，则会将该字符串入池，并返回一个指向该字符串的引用。因此，a和d指向的是同一个对象。

6. String str = new String("abc")创建了多少个对象？

　　这个问题在很多书籍上都有说到比如《Java程序员面试宝典》，包括很多国内大公司笔试面试题都会遇到，大部分网上流传的以及一些面试书籍上都说是2个对象，这种说法是片面的。

　　如果有不懂得地方可以参考这篇帖子：

　　http://rednaxelafx.iteye.com/blog/774673/

　　首先必须弄清楚创建对象的含义，创建是什么时候创建的？这段代码在运行期间会创建2个对象么？毫无疑问不可能，用javap -c反编译即可得到JVM执行的字节码内容：

　　很显然，new只调用了一次，也就是说只创建了一个对象。

　　而这道题目让人混淆的地方就是这里，这段代码在运行期间确实只创建了一个对象，即在堆上创建了"abc"对象。而为什么大家都在说是2个对象呢，这里面要澄清一个概念  该段代码执行过程和类的加载过程是有区别的。在类加载的过程中，确实在运行时常量池中创建了一个"abc"对象，而在代码执行过程中确实只创建了一个String对象。

　　因此，这个问题如果换成 String str = new String("abc")涉及到几个String对象？合理的解释是2个。

　　个人觉得在面试的时候如果遇到这个问题，可以向面试官询问清楚”是这段代码执行过程中创建了多少个对象还是涉及到多少个对象“再根据具体的来进行回答。

7. 下面这段代码1）和2）的区别是什么？
```
public class Main {
    public static void main(String[] args) {
        String str1 = "I";
        //str1 += "love"+"java";        1)
        str1 = str1+"love"+"java";      //2)
         
    }
}
```
　　1）的效率比2）的效率要高，1）中的"love"+"java"在编译期间会被优化成"lovejava"，而2）中的不会被优化。下面是两种方式的字节码：　

　　可以看出，在1）中只进行了一次append操作，而在2）中进行了两次append操作。

### 总结一下 ###

String：适用于少量的字符串操作的情况

StringBuilder：适用于单线程下在字符缓冲区进行大量操作的情况

StringBuffer：适用多线程下在字符缓冲区进行大量操作的情况

```
　　参考文章：http://rednaxelafx.iteye.com/blog/774673/

　　　　　　　http://www.blogjava.net/Jack2007/archive/2008/06/17/208602.html

　　　　　　   http://www.jb51.net/article/36041.htm

　　　　　　　http://blog.csdn.net/yirentianran/article/details/2871417

　　　　　　   http://www.jb51.net/article/33398.htm
```