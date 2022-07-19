```
/**
 * @author maxjoker
 * @date 2022-07-20 00:42
 * arthas thread 命令
 */
```

##### 查看当前线程信息，查看线程的堆栈

```
// 展示当前最忙的前N个线程并打印堆栈
thread -n 3

// 展示所有匹配的线程
thread -all

// 显示指定线程的运行堆栈
thread 线程id

// 找出当前阻塞其他线程的线程
// 有时候我们发现应用卡住了， 通常是由于某个线程拿住了某个锁， 并且其他线程都在等待这把锁造成的。 
// 为了排查这类问题， arthas提供了thread -b， 一键找出那个罪魁祸首。
// 注意， 目前只支持找出synchronized关键字阻塞住的线程， 如果是java.util.concurrent.Lock， 目前还不支持。
thread -b

// 指定采样时间间隔
// 统计最近1000ms内的线程CPU时间
thread -i 1000
// 列出1000ms内最忙的3个线程栈
// thread -n 3 -i 1000

// 查看指定状态的线程 WAITING, BLOCKED, TIMED_WAITING, NEW, RUNNABLE, TEMINATED
thread -state WAITING

```

