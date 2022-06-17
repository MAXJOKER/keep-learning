package learnjava.javathread;

import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author maxjoker
 * @date 2022-06-16 22:14
 *
 * 线程基础
 *
 * 来源：https://pdai.tech/md/java/thread/java-thread-x-thread-basic.html
 *
 * question: Condition 中的 await 方法如何指定等待条件？
 * 看这篇文章：http://www.javashuo.com/article/p-eprpebsa-gu.html
 * 最好看看《java并发编程的艺术》这本书
 *
 * 线程有哪几种状态? 新建 / 可运行 / 无限期等待 / 限期等待 / 阻塞 / 死亡
 * 分别说明从一种状态到另一种状态转变有哪些方式?
 * 通常线程有哪几种使用方式? Runnable / Callable / Thread(继承)
 * 基础线程机制有哪些? Executor
 * 线程的中断方式有哪些? interrupt() / Executor shutdown() shutdownNow()
 * 线程的互斥同步方式有哪些? synchronized / ReentrantLock
 * 如何比较和选择?
 * 线程之间有哪些协作方式? join() / wait(), notify(), notifyAll() / await(), signal(), signalAll()
 *
 */
public class ThreadBase {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /**
         * 线程状态转换
         *
         * 新建 (New)：创建后尚未启动
         *
         * 可运行（Runnable）：可能正在运行，也可能正在等待CPU时间片
         *      包含了操作系统线程状态中的 running 和 ready
         *
         * 阻塞（Blocking）：等待获取一个排他锁，如果其线程释放了锁就会结束此状态
         *
         * 无限期等待（Waiting）：等待其他线程显式地唤醒，否则不会被分配CPU时间片。
         *          （不指定timeout参数）Object.wait() 进入，Object.notify() / Object.notifyAll() 退出
         *          （不指定timeout参数）Thread.join() 进入，被调用的线程执行完成后退出
         *          LockSupport.park()
         *
         * 限期等待（Timed waiting)：无需等待其他线程显式地唤醒，在一定时间后会被系统自动唤醒
         *      调用 Thread.sleep() 方法使线程进入限期等待状态时，常常用“使一个线程睡眠”进行描述。
         *      调用 Object.wait() 方法使线程进入限期等待或者无限期等待时，常常用“挂起一个线程”进行描述。
         *
         *      睡眠和挂起是用来描述行为，而阻塞和等待用来描述状态。
         *      阻塞和等待的区别在于，阻塞是被动的，它是在等待获取一个排它锁。而等待是主动的，
         *      通过调用 Thread.sleep() 和 Object.wait() 等方法进入。
         *
         *      Thread.sleep() 进入，时间结束后退出
         *      （设置timeout）Object.wait() 进入，时间结束 / Object.notify() / Object.notifyAll() 退出
         *      （设置timeout）Thread.join() 进入，时间结束 退出
         *      LockSupport.parkNanos()
         *      LockSupport.parkUntil()
         *
         * 死亡（Terminated）：线程结束任务后自己结束或者异常结束
         *
         */

        /**
         * 线程使用方式
         *
         * 1. 实现 Runnable 接口
         * 2. 实现 Callable 接口
         * 3. 继承 Thread 类
         *
         * 实现 Runnable 和 Callable 接口的类只能当做一个可以在线程中运行的任务，不是真正意义上的线程，
         * 因此最后还需要通过 Thread 来调用。可以说任务是通过线程驱动从而执行的。
         *
         */
        MyRunnable myRunnable = new MyRunnable();
        for (int i = 0; i < 100; i++) {
//        myRunnable.run();
            Thread thread = new Thread(myRunnable);
//            thread.start();

            // 调用 interrupt() 方法
//            thread.interrupt();
        }

        /**
         * 与 Runnable 相比，Callable 可以有返回值，返回值通过 FutureTask 进行封装。
         */
        MyCallable myCallable = new MyCallable();

        for (int j = 0; j < 100; j++) {
            FutureTask<Integer> ft = new FutureTask<>(myCallable);
            Thread t = new Thread(ft);
//            t.start();
//            System.out.println(ft.get());
        }

        /**
         * 继承Thread类
         */
        MyThread myThread = new MyThread();
//        myThread.start();
//        myThread.interrupt();

        /**
         * 实现接口 OR 继承类
         *  实现接口会更好一些，因为:
         *          1. Java 不支持多重继承，因此继承了 Thread 类就无法继承其它类，但是可以实现多个接口；
         *          2. 类可能只要求可执行就行，继承整个 Thread 类开销过大。
         */

        /**
         * 基础线程机制
         *
         * Executor
         *      Executor 管理多个异步任务的执行，而无需程序员显式地管理线程的生命周期。这里的异步是指
         *      多个任务的执行互不干扰，不需要进行同步操作。
         *
         *      主要有3中executor：
         *          1. CachedThreadPool: 一个任务创建一个线程
         *          2. FixedThreadPool: 所有任务只能使用固定大小的线程
         *          3. SingleThreadExecutor: 相当于大小为1的FixedThreadPool
         */

//        ExecutorService executorService = Executors.newCachedThreadPool();
//        ExecutorService executorService = Executors.newFixedThreadPool(5);
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
//            executorService.execute(new MyRunnable());
        }
        executorService.shutdown();

        /**
         * Daemon
         * 守护线程是程序运行时在后台提供服务的线程，不属于程序中不可或缺的部分。
         * 当所有非守护线程结束时，程序也就终止，同时会杀死所有守护线程。
         * main() 属于非守护线程。 使用 setDaemon() 方法将一个线程设置为守护线程。
         */
        Thread t = new Thread(myRunnable);
        t.setDaemon(true);

        /**
         * 线程中断
         * 一个线程执行完毕之后会自动结束，如果在运行过程中发生异常也会提前结束
         *
         * Interruption
         *     通过调用 interrupt() 方法中断线程，如果该线程处于阻塞、限期等待或者无限期等待状态，
         *     那么就会抛出 InterruptedException，从而提前结束该线程。但是不能中断 I/O 阻塞和 synchronized 锁阻塞。
         *
         * interrupted()
         *          如果一个线程的 run() 方法执行一个无限循环，并且没有执行 sleep() 等会抛出
         *          InterruptedException 的操作，那么调用线程的 interrupt() 方法就无法使线程提前结束。
         *          但是调用 interrupt() 方法会设置线程的中断标记，此时调用 interrupted() 方法会返回 true。
         *          因此可以在循环体中使用 interrupted() 方法来判断线程是否处于中断状态，从而提前结束线程。
         *
         * Executor 的中断操作
         *      调用 Executor 的 shutdown() 方法会等待线程都执行完毕之后再关闭，
         *      但是如果调用的是 shutdownNow() 方法，则相当于调用每个线程的 interrupt() 方法。
         *
         *      如果只想中断 Executor 中的一个线程，可以通过使用 submit() 方法来提交一个线程，
         *      它会返回一个 Future<?> 对象，通过调用该对象的 cancel(true) 方法就可以中断线程。
         */
        ExecutorService executorService1 = Executors.newCachedThreadPool();
        executorService1.execute(() -> {
            try {
                Thread.sleep(1000);
                System.out.println("Thread run");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
//        executorService1.shutdown();
//        executorService1.shutdownNow();

        Future<?> future = executorService1.submit(() -> {
            System.out.println("future planet");
        });
        future.cancel(true);
        executorService1.shutdown();
        System.out.println("main run");

        /**
         * 线程互斥同步
         * Java 提供了两种锁机制来控制多个线程对共享资源的互斥访问，第一个是 JVM 实现的 synchronized，
         * 而另一个是 JDK 实现的 ReentrantLock
         *
         * synchronized
         *      1. 同步代码块：只作用于同一个对象，如果调用两个对象上的同步代码块，就不会进行同步
         *      2. 同步方法：它和同步代码块一样，作用于同一个对象。
         *      3. 同步一个类：作用于整个类，也就是说两个线程调用同一个类的不同对象上的这种同步语句，也会进行同步。
         *      4. 同步静态变量 / 静态方法
         */
        for (int i = 0; i < 10; i++) {
//            new Thread(myRunnable).start();
//            new Thread(myRunnable).start();
//            new Thread(new MyRunnable()).start();
        }

        /**
         * ReentrantLock：ReentrantLock 是 java.util.concurrent(J.U.C)包中的锁。
         *
         *
         */

//        LockDemo lockDemo = new LockDemo();
//        ExecutorService executorService2 = Executors.newCachedThreadPool();
//        executorService2.execute(() -> lockDemo.func());
//        executorService2.execute(() -> lockDemo.func());

        /**
         * 比较
         *
         * 1. 锁的实现：
         *  synchronized 是 JVM 实现的，而 ReentrantLock 是 JDK 实现的。
         *
         * 2. 性能
         *  新版本 Java 对 synchronized 进行了很多优化，例如自旋锁等，synchronized 与 ReentrantLock 大致相同。
         *
         * 3. 等待可中断
         *  当持有锁的线程长期不释放锁的时候，正在等待的线程可以选择放弃等待，改为处理其他事情。
         *  synchronized 不可中断，ReentrantLock 可中断
         *
         * 4. 公平锁
         *  公平锁是指多个线程在等待同一个锁时，必须按照申请锁的时间顺序来依次获得锁。
         *  synchronized 中的锁是非公平的，ReentrantLock 默认情况下也是非公平的，但是也可以是公平的。(AQS队列）
         *
         * 5. 锁绑定多个条件
         *   一个 ReentrantLock 可以同时绑定多个 Condition 对象。
         *
         * 使用选择
         *      除非需要使用 ReentrantLock 的高级功能，否则优先使用 synchronized。
         *      这是因为 synchronized 是 JVM 实现的一种锁机制，JVM 原生地支持它，
         *      而 ReentrantLock 不是所有的 JDK 版本都支持。并且使用 synchronized
         *      不用担心没有释放锁而导致死锁问题，因为 JVM 会确保锁的释放
         *
         * 线程之间的协作
         *   当多个线程可以一起工作去解决某个问题时，如果某些部分必须在其它部分之前完成，那么就需要对线程进行协调。
         *
         *   join()
         *   在线程中调用另一个线程的 join() 方法，会将当前线程挂起，而不是忙等待，直到目标线程结束
         */
        JoinDemo joinDemo = new JoinDemo();
        joinDemo.test();

        /**
         * wait() / notify() / notifyAll()
         *
         * 调用 wait() 使得线程等待某个条件满足，线程在等待时会被挂起，当其他线程的运行使得这个条件满足时，
         * 其它线程会调用 notify() 或者 notifyAll() 来唤醒挂起的线程。
         *
         * 它们都属于 Object 的一部分，而不属于 Thread。
         *
         * 只能用在同步方法或者同步控制块中使用，否则会在运行时抛出 IllegalMonitorStateException。
         *
         * 使用 wait() 挂起期间，线程会释放锁。这是因为，如果没有释放锁，那么其它线程就无法进入对象的同步方法或者
         * 同步控制块中，那么就无法执行 notify() 或者 notifyAll() 来唤醒挂起的线程，造成死锁。
         *
         * wait() 和 sleep() 的区别
         *
         *      wait() 是 Object 的方法，而 sleep() 是 Thread 的静态方法；
         *      wait() 会释放锁，sleep() 不会。
         */
        ExecutorService executorService2 = Executors.newCachedThreadPool();
        WaitDemo waitDemo = new WaitDemo();
//        executorService2.execute(() -> waitDemo.after());
//        executorService2.execute(() -> waitDemo.before());

        /**
         * await() / signal() / signalAll()
         *
         * java.util.concurrent 类库中提供了 Condition 类来实现线程之间的协调，
         * 可以在 Condition 上调用 await() 方法使线程等待，其它线程调用 signal() 或 signalAll() 方法
         * 唤醒等待的线程。相比于 wait() 这种等待方式，await() 可以指定等待的条件，因此更加灵活。
         */
        AwaitDemo awaitDemo = new AwaitDemo();
        ExecutorService executorService3 = Executors.newCachedThreadPool();
        executorService3.execute(() -> awaitDemo.after());
        executorService3.execute(() -> awaitDemo.before());

        /**
         * 从总体上来看Object的wait和notify/notify是与对象监视器配合完成线程间的等待/通知机制，
         * 而Condition与Lock配合完成等待通知机制，前者是java底层级别的，后者是语言级别的，具备更高的可控制性和扩展性。
         */


        System.exit(0);
    }
}

class MyRunnable implements Runnable {
    int i = 0;

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            testSynchronizedStaticMethod();
//            testSynchronizedClass();
//            testSynchronizedMethod();
//            testSynchronized();
//            i++;
//            System.out.println("MyRunnable is running.");
//            System.out.println(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void testSynchronized() {
        // 同步代码块
        synchronized (this) {
            i++;
            System.out.println(i);
        }
    }

    /**
     * 同步方法：它和同步代码块一样，作用于同一个对象。
     */
    public synchronized void testSynchronizedMethod() {
        i++;
        System.out.println(i);
    }

    /**
     * 同步一个类：作用于整个类，也就是说两个线程调用同一个类的不同对象上的这种同步语句，也会进行同步。
     */
    public void testSynchronizedClass() {
        synchronized (MyRunnable.class) {
            i++;
            System.out.println(i);
        }
    }

    /**
     * 同步一个静态方法：作用于整个类
     */
    public synchronized static void testSynchronizedStaticMethod() {
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
        }
    }
}

class MyCallable implements Callable {
    int j = 0;

    @Override
    public Object call() throws Exception {
        return j++;
    }
}

class MyThread extends Thread {
    int k = 0;
    public void run() {
        while (interrupted()) {
            System.out.println("Interrupted! Exit!");
            System.exit(0);
        }
        k++;
        System.out.println(k);
    }
}

class LockDemo {
    private Lock lock = new ReentrantLock();

    public void func() {
        lock.lock();
        try {
            for (int i = 10; i < 20; i++) {
                System.out.println(i + " ");
            }
        } finally {
            lock.unlock(); // 确保释放锁，从而避免发生死锁。
        }
    }
}

class JoinDemo {
    private class A extends Thread {
        @Override
        public void run() {
            // todo ? 这里有时候是A打印完才执行B中的打印，有时候是反过来
            for (int i = 0; i < 10; i++) {
                System.out.println("A");
            }
        }
    }

    private class B extends Thread {
        private A a;

        B(A a) {
            this.a = a;
        }

        @Override
        public void run() {
            try {
                a.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("B");
        }
    }

    public void test() {
        A a = new A();
        B b = new B(a);
        b.start();
        a.start();
    }
}

class WaitDemo {
    public synchronized void before() {
        System.out.println("before");
        notifyAll();
    }

    public synchronized void after() {
        try {
            wait();
            System.out.println("wait");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("after");
    }
}

class AwaitDemo {
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void before() {
        lock.lock();
        try {
            System.out.println("before");
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void after() {
        lock.lock();
        try {
            condition.await();
            System.out.println("await");
            System.out.println("after");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}


