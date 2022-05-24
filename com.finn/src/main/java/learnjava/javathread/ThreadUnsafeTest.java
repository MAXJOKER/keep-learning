package learnjava.javathread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author maxjoker
 * @date 2022-05-19 20:21
 * 线程不安全例子
 */
public class ThreadUnsafeTest {
    public static void main(String[] args) throws InterruptedException {
        final int threadSize = 1000;
        TreadUnsafe threadUnsafe = new TreadUnsafe();
        final CountDownLatch countDownLatch = new CountDownLatch(threadSize);
        ExecutorService executorService = Executors.newCachedThreadPool();
        // 循环执行 1000 次
        for (int i = 0; i < threadSize; i++) {
            executorService.execute(() -> {
                threadUnsafe.add();
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        // 多次执行，这里get到的值很多时候都不是1000
        System.out.println(threadUnsafe.get());
    }
}

class TreadUnsafe {
    private int count = 0;

    public void add() {
        count++;
    }

    public int get() {
        return count;
    }
}
