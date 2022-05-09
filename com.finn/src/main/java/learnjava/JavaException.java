package learnjava;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author maxjoker
 * @date 2022-04-20 20:06
 *
 * java异常
 *
 * https://pdai.tech/md/java/basic/java-basic-x-exception.html
 *
 */
public class JavaException {
    public static void main(String[] args) {
        // Throwable 可以用来表示任何可以作为异常抛出的类，Throwable 是 Java 语言中所有错误与异常的超类。
        // Throwable 包含了其线程创建时线程执行堆栈的快照，它提供了 printStackTrace() 等接口用于获取堆栈跟踪数据等信息。
        // 分为两种:  Error  和 Exception。
        // Error 用来表示 JVM 无法处理的错误，
        // Exception 分为两种:
        // 受检异常 : 需要用 try...catch... 语句捕获并进行处理，并且可以从异常中恢复；
        // 非受检异常 : 是程序运行时错误，例如除 0 会引发 Arithmetic Exception，此时程序崩溃并且无法恢复。


        // Error
        // Error 类及其子类：程序中无法处理的错误，表示运行应用程序中出现了严重的错误。
        // 此类错误一般表示代码运行时 JVM 出现问题。通常有 Virtual MachineError（虚拟机运行错误）、NoClassDefFoundError（类定义错误）等。
        // 比如 OutOfMemoryError：内存不足错误；StackOverflowError：栈溢出错误。此类错误发生时，JVM 将终止线程。
        // 这些错误是不受检异常，非代码性错误。因此，当此类错误发生时，应用程序不应该去处理此类错误。
        // 按照Java惯例，我们是不应该实现任何新的Error子类的！

        // Exception
        // 程序本身可以捕获并且可以处理的异常。Exception 这种异常又分为两类：运行时异常和编译时异常。
        // 运行时异常:
        //  都是RuntimeException类及其子类异常，如NullPointerException(空指针异常)、IndexOutOfBoundsException(下标越界异常)等，
        //  这些异常是不检查异常，程序中可以选择捕获处理，也可以不处理。
        //  这些异常一般是由程序逻辑错误引起的，程序应该从逻辑角度尽可能避免这类异常的发生。
        //
        // 非运行时异常 （编译异常）:
        // 是RuntimeException以外的异常，类型上都属于Exception类及其子类。从程序语法角度讲是必须进行处理的异常，
        // 如果不处理，程序就不能编译通过。如IOException、SQLException等以及用户自定义的Exception异常，一般情况下不自定义检查异常
        //
        // 可查的异常（checked exceptions）: 编译器要求必须处置的异常
        // 正确的程序在运行中，很容易出现的、情理可容的异常状况。可查异常虽然是异常状况，但在一定程度上它的发生是可以预计的，
        // 而且一旦发生这种异常状况，就必须采取某种方式进行处理。 除了RuntimeException及其子类以外，其他的Exception类及其子类都属于可查异常。
        // 这种异常的特点是Java编译器会检查它，也就是说，当程序中可能出现这类异常，要么用try-catch语句捕获它，
        // 要么用throws子句声明抛出它，否则编译不会通过。
        //
        // 不可查的异常（unchecked exceptions）: 编译器不要求强制处置的异常
        // 包括运行时异常（RuntimeException与其子类）和错误（Error）。

        // 异常关键字
        // try - 用于监听。将要被监听的代码(可能抛出异常的代码)放在try语句块之内，当try语句块内发生异常时，异常就被抛出。
        // catch - 用于捕获异常。catch用来捕获try语句块中发生的异常。
        // finally - finally语句块总是会被执行。它主要用于回收在try块里打开的物力资源(如数据库连接、网络连接和磁盘文件)。
        //           只有finally块，执行完成之后，才会回来执行try或者catch块中的return或者throw语句，
        //           如果finally中使用了return或者throw等终止方法的语句，则就不会跳回执行，直接停止。
        // throw - 用于抛出异常
        // throws - 用在方法签名中，用于声明该方法可能抛出的异常。

        // 异常的捕获
        // 1. try-catch
        // 2. try-catch-finally
        // 3. try-finally
        // 4. try-with-resource

        HttpURLConnection httpURLConnection = null;
        // try-catch
        try {
            URL url = new URL("");
            httpURLConnection = (HttpURLConnection) url.openConnection();
        } catch (FileNotFoundException | UnknownHostException e) { // 多个异常可以用 | 隔开

        } catch (IOException e) {

        }

        // try-catch-finally
        try {
            httpURLConnection.getInputStream();
            System.out.println("[try-catch-finally] try no exception.");
        } catch (IOException | NullPointerException e) {
            System.out.println("[try-catch-finally] catch exception: " + e.getMessage());
        } finally {
            System.out.println("[try-catch-finally] finally.");
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        }

        // 这里得出的结果是， a = 3，所以请不要在finally中做任何return操作
        System.out.println(testTryCatchFinally());

        // try-finally 也是可以的
        ReentrantLock lock = new ReentrantLock();
        try {
            // 加锁代码
            lock.lock();
        } finally {
            lock.unlock();
        }

        // finally 在以下情况不会执行
        // 1. 在前面的代码中用了System.exit()退出程序。
        // 2. finally语句块中发生了异常。
        // 3. 程序所在的线程死亡。
        // 4. 关闭CPU

        // try-with-resource
        // try-with-resource是Java 7中引入的，很容易被忽略，实现资源的自动释放，自动释放的资源需要是实现了 AutoCloseable 接口的类。
        // try 代码块退出时，会自动调用 scanner.close 方法
        // 和把 scanner.close 方法放在 finally 代码块中不同的是，若 scanner.close 抛出异常，则会被抑制，抛出的仍然为原始异常。
        // 被抑制的异常会由 addSuppressed 方法添加到原来的异常，如果想要获取被抑制的异常列表，可以调用 getSuppressed 方法来获取
        // Scanner 实现了 Closeable 接口，Closeable 接口类继承自 AutoCloseable 接口类
        try (Scanner scanner = new Scanner(new FileInputStream("read.md"), "UTF-8");) {

        } catch (IOException e) {
            e.getSuppressed();
        }

        // Java编程思想一书中，对异常的总结。
        // 1. 在恰当的级别处理问题。（在知道该如何处理的情况下了捕获异常。）
        // 2. 解决问题并且重新调用产生异常的方法。
        // 3. 进行少许修补，然后绕过异常发生的地方继续执行。
        // 4. 用别的数据进行计算，以代替方法预计会返回的值。
        // 5. 把当前运行环境下能做的事尽量做完，然后把相同的异常重抛到更高层。
        // 6. 把当前运行环境下能做的事尽量做完，然后把不同的异常抛到更高层。
        // 7. 终止程序。
        // 8. 进行简化（如果你的异常模式使问题变得太复杂，那么用起来会非常痛苦）。
        // 9. 让类库和程序更安全。

        // 常用的异常：
        // RuntimeException
        // 1. java.lang.ArrayIndexOutOfBoundsException: 数组下标越界
        // 2. java.lang.ArithmeticException: 算术条件异常，如除以0
        // 3. java.lang.NullPointerException: 空指针异常，使用null进行操作
        // 4. java.lang.ClassNotFoundException: 找不到类异常，当应用试图根据字符串形式的类名构造类，而在遍历CLASSPAH之后找不到对应名称的class文件时，抛出该异常
        // 5. java.lang.NegativeArraySizeException: 数组长度为负异常
        // 6. java.lang.IllegalArgumentException: 非法参数异常

        // IOException
        // IOException：操作输入流和输出流时可能出现的异常。
        // EOFException   文件已结束异常
        // FileNotFoundException   文件未找到异常
        //
        // 其他
        // ClassCastException    类型转换异常类
        // ArrayStoreException  数组中包含不兼容的值抛出的异常
        // SQLException   操作数据库异常类
        // NoSuchFieldException   字段未找到异常
        // NoSuchMethodException   方法未找到抛出的异常
        // NumberFormatException    字符串转换为数字抛出的异常
        // StringIndexOutOfBoundsException 字符串索引超出范围抛出的异常
        // IllegalAccessException  不允许访问某类异常
        // InstantiationException  当应用程序试图使用Class类中的newInstance()方法创建一个类的实例，而指定的类对象无法被实例化时，抛出该异常

        // 异常实践：
        //
        // 1. 只针对不正常的情况才使用异常，
        //      （1）创建、抛出和捕获异常的开销是很昂贵的
        //      （2）把代码放在try-catch中返回阻止了JVM实现本来可能要执行的某些特定的优化
        //      （3）对数组进行遍历的标准模式并不会导致冗余的检查，有些现代的JVM实现会将它们优化掉
        // 2. 在 finally 块中清理资源或者使用 try-with-resource 语句
        // 3. 尽量使用标准的异常
        // 4. 对异常进行文档说明，当在方法上声明抛出异常时，也需要进行文档说明。目的是为了给调用者提供尽可能多的信息，从而可以更好地避免或处理异常。
        // 5. 优先捕获最具体的异常
        // 6. 不要捕获 Throwable 类，Throwable 是所有异常和错误的超类。你可以在 catch 子句中使用它，但是你永远不应该这样做
        // 7. 不要忽略异常，catch中不要什么都不做
        // 8. 不要记录并抛出异常，不要log一下，然后再把异常抛出
        // 9. 包装异常时不要抛弃原始的异常，不然跟踪异常时将变得很困难
        // 10. 不要使用异常控制程序的流程，异常的创建、抛出、捕获的开销是昂贵的
        // 11. 不要在finally块中使用return，会直接返回，忽略 try 或者 catch 中的return

        // 深入理解异常
        // 好文：https://www.iteye.com/blog/icyfenix-857722

        // 异常是否耗时？为什么会耗时？
        // 耗时对比:
        //      建立对象： 193300
        //      建立异常： 5032800
        //      建立、抛出、捕获异常： 6192900
        newObject();
        newException();
        throwException();
    }

    /**
     * 在finally中返回值测试
     * @return
     */
    public static int testTryCatchFinally() {
        int a = 0;
        try {
            a = 1;
            return a;
        } catch (Exception e) {
            a = 2;
            return a;
        } finally {
            a = 3;
            return a;
        }

    }

    /**
     * throws 学习
     * <p>
     * Throws抛出异常的规则：
     * <p>
     * 1、如果是不可查异常（unchecked exception），即Error、RuntimeException或它们的子类，那么可以不使用throws关键字来声明要抛出的异常，
     * 编译仍能顺利通过，但在运行时会被系统抛出。
     * 2、必须声明方法可抛出的任何可查异常（checked exception）。即如果一个方法可能出现可查异常，
     * 要么用try-catch语句捕获，要么用throws子句声明将它抛出，否则会导致编译错误。
     * 3、仅当抛出了异常，该方法的调用者才必须处理或者重新抛出该异常。当方法的调用者无力处理该异常的时候，应该继续抛出，而不是囫囵吞枣。
     * 4、调用方法必须遵循任何可查异常的处理和声明规则。若覆盖一个方法，则不能声明与覆盖方法不同的异常。
     * 声明的任何异常必须是被覆盖方法所声明异常的同类或子类。
     *
     * @throws IOException
     */
    public void testThrows() throws IOException {

    }

    /**
     * 建立对象
     */
    public static void newObject() {
        long l = System.nanoTime();
        for (int i = 0; i < 10000; i++) {
            new Object();
        }

        System.out.println("建立对象： " + (System.nanoTime() - l));
    }

    /**
     * 建立异常
     */
    public static void newException() {
        long l = System.nanoTime();
        for (int i = 0; i < 10000; i++) {
            new Exception();
        }

        System.out.println("建立异常： " + (System.nanoTime() - l));
    }

    /**
     * 抛出并catch异常
     */
    public static void throwException() {
        long l = System.nanoTime();
        for (int i = 0; i < 10000; i++) {
            try {
                throw new Exception();
            } catch (Exception e) {

            }
        }

        System.out.println("建立、抛出、捕获异常： " + (System.nanoTime() - l));
    }

}

/**
 * 自定义异常
 *
 * 习惯上，定义一个异常类应包含两个构造函数，
 * 一个无参构造函数
 * 一个带有详细描述信息的构造函数（Throwable 的 toString 方法会打印这些详细信息，调试时很有用）
 *
 */
class MyException extends Exception {
    public MyException() {

    }

    public MyException(String message) {
        super(message);
    }
}
