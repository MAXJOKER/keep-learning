package designpatterns.singleton;

public class Singleton {
    private static Singleton singletonInstance;

    private Singleton() {

    }

    /**
     * 懒汉模式 需要用到才请求创建实例
     * @return
     */
    public static Singleton getInstance() {
        // 问题，多线程时可能会造成 new 多个实例，造成混乱
        if (singletonInstance == null) {
            singletonInstance = new Singleton();
        }

        return singletonInstance;
    }

    /**
     * 同步方法 解决多线程问题
     * 新的问题：效率低，因为 singleInstance 只需要初始化一次就好了，之后每次调用，同步是一个累赘
     * @return
     */
    public static synchronized Singleton getInstance2() {
        if (singletonInstance == null) {
            singletonInstance = new Singleton();
        }

        return singletonInstance;
    }

    /**
     * 在静态初始化器中创建单例，保证了线程安全（但达不到需要用到才初始化的效果）
     */
    private static Singleton singletonInstance3 = new Singleton();
    public static Singleton getInstance3() {
        return singletonInstance3;
    }

    /**
     * 双重检查加锁 (double-checked locking)
     * 只有第一次创建实例才需要同步
     * 被volatile修饰的变量的值，将不会被本地线程缓存，所有对该变量的读写都是直接操作共享内存，从而确保多个线程能正确的处理该变量。
     * volatile 只能用在java 5 及以上版本
     *
     * 由于volatile关键字可能会屏蔽掉虚拟机中一些必要的代码优化，所以运行效率并不是很高。因此一般建议，没有特别的需要，不要使用。
     * 也就是说，虽然可以使用“双重检查加锁”机制来实现线程安全的单例，但并不建议大量采用，可以根据情况来选用。
     */
    private volatile static Singleton singletonInstance4;
    public static Singleton getSingletonInstance4() {
        if (singletonInstance4 == null) {
            synchronized (Singleton.class) {
                if (singletonInstance4 == null) {
                    singletonInstance4 = new Singleton();
                }
            }
        }
        return singletonInstance4;
    }

    /**
     * 当getInstance方法第一次被调用的时候，它第一次读取SingletonHolder.instance，导致SingletonHolder类得到初始化；
     * 而这个类在装载并被初始化的时候，会初始化它的静态域，从而创建Singleton的实例，由于是静态的域，因此只会在虚拟机装载类的时候初始化一次，
     * 并由虚拟机来保证它的线程安全性。
     *
     * 这个模式的优势在于，getInstance方法并没有被同步，并且只是执行一个域的访问，因此延迟初始化并没有增加任何访问成本。
     *
     * 类级的内部类，也就是静态的成员式内部类，该内部类的实例与外部类的实例
     * 没有绑定关系，而且只有被调用到时才会装载，从而实现了延迟加载。
     *
     * 类级内部类？
     * 　　简单点说，类级内部类指的是，有static修饰的成员式内部类。如果没有static修饰的成员式内部类被称为对象级内部类。
     *
     * 　　类级内部类相当于其外部类的static成分，它的对象与外部类对象间不存在依赖关系，因此可直接创建。而对象级内部类的实例，是绑定在外部对象实例中的。
     *
     * 　　类级内部类中，可以定义静态的方法。在静态方法中只能够引用外部类中的静态成员方法或者成员变量。
     *
     * 　　类级内部类相当于其外部类的成员，只有在第一次被使用的时候才被会装载。
     *
     * 多线程缺省同步锁的知识
     * 　　大家都知道，在多线程开发中，为了解决并发问题，主要是通过使用synchronized来加互斥锁进行同步控制。但是在某些情况中，
     *    JVM已经隐含地为您执行了同步，这些情况下就不用自己再来进行同步控制了。这些情况包括：
     *
     * 　　1.由静态初始化器（在静态字段上或static{}块中的初始化器）初始化数据时
     *
     * 　　2.访问final字段时
     *
     * 　　3.在创建线程之前创建对象时
     *
     * 　　4.线程可以看见它将要处理的对象时
     */
    private static class SingletonHolder {
        // 静态初始化器，由JVM来保证线程安全
        private static Singleton singletonInstance5 = new Singleton();
    }
    public static Singleton getSingletonInstance5() {
        return SingletonHolder.singletonInstance5;
    }
}
