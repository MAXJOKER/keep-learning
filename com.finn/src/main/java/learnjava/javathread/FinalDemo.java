package learnjava.javathread;

import java.util.Random;

/**
 * @author maxjoker
 * @date 2022-06-14 15:54
 *
 * final 关键字
 *
 * 参考：https://pdai.tech/md/java/thread/java-thread-x-key-final.html
 *
 * 问题：
 *
 * 所有的final修饰的字段都是编译期常量吗?
 *       不一定
 *
 * 如何理解private所修饰的方法是隐式的final?
 *      类中所有private方法都隐式地指定为final的，由于无法取用private方法，所以也就不能覆盖它。
 *      可以对private方法增添final关键字，但这样做并没有什么好处。
 *
 * 说说final类型的类如何拓展? 比如String是final类型，我们想写个MyString复用所有String中方法，同时增加一个新的toMyString()的方法，应该如何做?
 *      搞一个新类 MyString，然后有个成员变量 private String str，通过这个str调用老方法；
 *      toMyString直接实现就行了
 *
 *      public class MyClass {
 *          private String str;
 *
 *          public int length() {
 *              return str.length(); // 调用老方法
 *          }
 *
 *          public void toMyString() {
 *              // do something
 *          }
 *      }
 *
 * final方法可以被重载吗?
 *      可以
 *
 * 父类的final方法能不能够被子类重写?
 *      不可以
 *
 * 说说final域重排序规则?
 *      写final域重排序规则
 *      读final域重排序规则
 *
 *      final域为引用类型:
 *          对final修饰的对象的成员域写操作
 *          对final修饰的对象的成员域读操作
 *
 *      基本数据类型:
 *              final域写：禁止final域写与构造方法重排序，即禁止final域写重排序到构造方法之外，从而保证该对象对所有线程可见时，
 *                          该对象的final域全部已经初始化过。
 *              final域读：禁止初次读对象的引用与读该对象包含的final域的重排序。
 *
 *      引用数据类型：
 *          额外增加约束：禁止在构造函数对一个final修饰的对象的成员域的写入与随后将这个被构造的对象的引用赋值的引用变量 重排序
 *                      （十分拗口，看下面的例子， writeOne方法）
 *
 * 说说final的原理?
 *      也就是final的重排序规则
 *      写final域会要求编译器在final域写之后，构造函数返回前插入一个StoreStore屏障，确保在对象引用为任意线程可见之前，对象的final域已经被正确初始化过了
 *      读final域的重排序规则会要求编译器在读final域的操作前插入一个LoadLoad屏障，确保在读一个对象的final域之前，一定会先读这个包含这个final域的对象的引用。
 *
 * 使用 final 的限制条件和局限性?
 *      当声明一个 final 成员时，必须在构造函数退出前设置它的值。
 *      将指向对象的成员声明为 final 只能将该引用设为不可变的，而非所指的对象。
 */
public class FinalDemo {
    private int a; // 普通域
    private final int b; // final 域
    private static FinalDemo finalDemo;

    /**
     * 非编译器常量
     */
    Random r = new Random();
    private final int k = r.nextInt();


    /**
     * 写final域的重排序规则禁止对final域的写重排序到 构造函数 之外，这个规则的实现主要包含了两个方面：
     *      JMM禁止编译器把final域的写重排序到构造函数之外；
     *      编译器会在final域写之后，构造函数return之前，插入一个StoreStore屏障。这个屏障可以禁止处理器把final域的写重排序到构造函数之外。
     *
     * 由于a,b之间没有数据依赖性，普通域(普通变量)a可能会被重排序到构造函数之外，线程B就有可能读到的是普通变量a初始化之前的值(零值)，
     * 这样就可能出现错误。而final域变量b，根据重排序规则，会禁止final修饰的变量b重排序到构造函数之外，从而b能够正确赋值，
     * 线程B就能够读到final变量初始化后的值。
     *
     * 因此，写final域的重排序规则可以确保：在对象引用为任意线程可见之前，对象的final域已经被正确初始化过了，而普通域就不具有这个保障
     */
    public FinalDemo() {
        a = 1; // 1. 写普通域
        b = 2; // 2. 写final域
    }

    /**
     * writer方法，虽然只有一行代码，但实际上做了两件事情：
     *
     *      1. 构造了一个FinalDemo对象；
     *      2. 把这个对象赋值给成员变量finalDemo。
     */
    public static void writer() {
        finalDemo = new FinalDemo();
    }

    /**
     * 读final域重排序规则: 在一个线程中，初次读对象引用和初次读该对象包含的final域，JMM会禁止这两个操作的重排序。
     * (注意，这个规则仅仅是针对处理器)，处理器会在读final域操作的前面插入一个LoadLoad屏障
     * LoadLoad Barriers:	Load1; LoadLoad; Load2	确保 Load1 数据的装载，之前于 Load2 及所有后续装载指令的装载。
     *
     * 读final域的重排序规则可以确保：在读一个对象的final域之前，一定会先读这个包含这个final域的对象的引用。
     *
     */
    public static void reader() {
        FinalDemo demo = finalDemo; // 3. 读取对象应用
        int a = demo.a; // 4. 读普通域
        int b = demo.b; // 5. 读final域
        System.out.println("read a : " + a);
        System.out.println("read b : " + b);
    }

    public static void main(String[] args) {

        for (int i = 0; i < 1000; i++) {
            /**
             * 线程A：构造函数执行 -> 2. 写final域 b = 2 -> StoreStore 屏障 -> 构造函数执行完毕 -> 把构造对象的引用赋值给引用变量 finalDemo
             *      而 a = 1 有可能被重排序到构造函数之外
             */
            new Thread(new Runnable() {
                @Override
                public void run() {
                    writer();
                }
            }).start();

            /**
             * 线程B：3. 读对象引用 -> 4. 读普通域 a -> 5. 读final域 b
             */
            new Thread(new Runnable() {
                @Override
                public void run() {
                    reader();
                }
            }).start();
        }


        // 多线程  引用类型
        FinalReferenceDemo finalReferenceDemo = new FinalReferenceDemo();

        for (int i = 0; i < 1000; i++) {
            // 由于对final域的写禁止重排序到构造方法外，因此1和3不能被重排序
            // 由于一个final域的引用对象的 成员域写入 不能与 随后将这个被构造出来的对象赋给 引用变量 重排序，因此2和3不能重排序。
            /**
             * 线程A：构造函数开始执行 -> 1. 写final类型应用 -> 2. 写final引用的对象的成员域 -> StoreStore 屏障 -> 构造函数执行完毕 -> 3. 把构造对象的引用赋值给引用变量
             */
            new Thread(new Runnable() {
                @Override
                public void run() {
                    finalReferenceDemo.writeOne();
                }
            }).start();

            /**
             * 线程B : 4. 写final引用对象的成员域
             */
            new Thread(new Runnable() {
                @Override
                public void run() {
                    finalReferenceDemo.writeTwo();
                }
            }).start();

            /**
             * 线程C: 5. 读对象引用 -> LoadLoad 屏障 -> 6. 读final引用的对象的成员域
             * 对final修饰的对象的成员域读操作
             * JMM可以确保线程C至少能看到写线程A对final引用的对象的成员域的写入，即能看下arrays[0] = 1，而写线程B对数组元素的写入可能看到可能看不到。
             * 所以打印的结果，temp = 1 或者 temp = 3
             * JMM不保证线程B的写入对线程C可见，线程B和线程C之间存在数据竞争，此时的结果是不可预知的。如果可见的，可使用锁或者volatile。
             */
            new Thread(new Runnable() {
                @Override
                public void run() {
                    finalReferenceDemo.read();
                }
            }).start();
        }

        // One More Thing
        byte b1 = 1;
        byte b2 = 2;
//        byte b3 = b1 + b2; // 报错，因为b1、b2可以自动转换成int类型的变量，运算时java虚拟机对它进行了转换，结果导致把一个int赋值给byte
//
        final byte b5 = 1;
        final byte b6 = 2;
        byte b7 = b5 + b6; // 不会报错，因为基础类型被final修饰后其值不再改变，也就不会对b1,b2进行自动int提升，则不会出错。
    }
}

/**
 * 针对引用数据类型，final域写针对编译器和处理器重排序增加了这样的约束：
 *      在构造函数内对一个final修饰的对象的成员域的写入，与随后在构造函数之外把这个被构造的对象的引用赋给一个引用变量，这两个操作是不能被重排序的。
 *      注意这里的是“增加”也就说前面对final基本数据类型的重排序规则在这里还是使用。
 *
 *
 */
class FinalReferenceDemo {
    final int[] array;
    private FinalReferenceDemo finalReferenceDemo;

    public FinalReferenceDemo() {
        array = new int[1]; // 1
        array[0] = 1; //  2
    }

    public void writeOne() {
        finalReferenceDemo = new FinalReferenceDemo(); // 3
    }

    public void writeTwo() {
        finalReferenceDemo.array[0] = 3; // 4
    }

    public void read() {
        if (finalReferenceDemo != null) { // 5
            int temp = finalReferenceDemo.array[0]; // 6
            System.out.println("temp: " + temp);
        }
    }
}

/**
 * 关于final重排序的总结
 * 按照final修饰的数据类型分类：
 *
 * 基本数据类型:
 *      final域写：禁止final域写与构造方法重排序，即禁止final域写重排序到构造方法之外，从而保证该对象对所有线程可见时，该对象的final域全部已经初始化过。
 *      final域读：禁止初次读对象的引用与读该对象包含的final域的重排序。
 *
 * 引用数据类型：
 *      额外增加约束：禁止在构造函数对一个final修饰的对象的成员域的写入与随后将这个被构造的对象的引用赋值的引用变量 重排序 （十分拗口，看上面的例子， writeOne方法）
 *
 *
 */