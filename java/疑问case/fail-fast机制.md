#### 什么是 fail-fast？

```
维基百科：
In systems design, a fail-fast system is one which immediately reports at its interface any condition that is 
likely to indicate a failure. Fail-fast systems are usually designed to stop normal operation rather than attempt to 
continue a possibly flawed process. Such designs often check the system's state at several points in an operation, 
so any failures can be detected early. The responsibility of a fail-fast module is detecting errors, then letting 
the next-highest level of the system handle them.
```

也就是说，fail-fast是一种通用的系统设计思想，一种错误检测机制，一旦检测到可能发生错误，就立马抛异常，程序不再继续往下执行。

#### 集合中的fail-fast机制

```
List<String> list = new ArrayList<>();
list.add("1");
list.add("2");
list.add("3");

        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String temp = iterator.next();
            System.out.println(temp);
            if (temp.equals("1")) {
                list.remove(temp);
            }
        }
        System.out.println(list);
```

上面的代码会抛出 ConcurrentModificationException 异常，原因是 next()方法中调用checkForComodification()方法，如果 modCount 不等于 
expectedModCount，则抛出异常。

在用迭代器遍历一个集合对象时，如果遍历过程中对集合对象的结构进行了增加、删除，则会抛出Concurrent Modification Exception（并发修改异常）。

项目里的 testcase.FailFastTest 类中，有个有趣的case，可以去看看。

#### 如何避免 fail-fast

    1、如果在迭代器遍历的时候需要修改集合，则使用迭代器中的方法，而不是使用集合直接操作

    2、如果是并发环境，对迭代器进行加锁

    3、直接使用普通的for循环对集合进行操作

    4、使用fail-safe机制的集合，CopyOnWriteArrayList

    5、使用Java8中提供的filter过滤，list = list.stream().filter(l -> !l.equals("1")).collect(Collectors.toList());

#### 什么是 fail-safe？

采用安全失败机制的集合容器，在 Iterator 的实现上没有设计抛出 ConcurrentModificationException 的代码段，从而避免了fail-fast。

典型采用fail-safe的容器——CopyOnWriteArrayList

写时复制，简单理解就是，当我们往一个容器添加元素的时候，先将当前容器复制出一个新的容器，然后新的容器里添加元素，添加完元素之后，
再将原容器的引用指向新的容器。这样做的好处是我们可以对CopyOnWrite容器进行并发的读，而不需要加锁，因为当前容器不会添加任何元素。
所以CopyOnWrite容器也是一种读写分离的思想，读和写不同的容器。

CopyOnWrite的应用场景：CopyOnWrite并发容器用于读多写少的并发场景。比如白名单，黑名单，商品类目的访问和更新场景。

CopyOnWrite的缺点：CopyOnWrite容器有很多优点，但是同时也存在两个问题，即 内存占用问题 和 数据一致性问题。所以在开发的时候需要注意一下：

<b>内存占用问题</b>：因为CopyOnWrite的写时复制机制，所以在进行写操作的时候，内存里会同时驻扎两个对象的内存，旧的对象和新写入的对象
（注意:在复制的时候只是复制容器里的引用，只是在写的时候会创建新对象添加到新容器里，而旧容器的对象还在使用，所以有两份对象内存）。
如果这些对象占用的内存比较大，比如说200M左右，那么再写入100M数据进去，内存就会占用300M，那么这个时候很有可能造成频繁的Yong GC和Full GC。

简单看下 CopyOnWriteArrayList的其中一个add方法

```
    /**
     * Appends the specified element to the end of this list.
     *
     * @param e element to be appended to this list
     * @return {@code true} (as specified by {@link Collection#add})
     */
    public boolean add(E e) {
        final ReentrantLock lock = this.lock;
        lock.lock(); // 注意这里会加锁
        try {
            Object[] elements = getArray(); // 获取原数组
            int len = elements.length;
            Object[] newElements = Arrays.copyOf(elements, len + 1); // 复制到新数组
            newElements[len] = e; // 添加新元素
            setArray(newElements); // 将新数组设置回去
            return true;
        } finally {
            lock.unlock();
        }
    }
```

针对内存占用问题，可以通过压缩容器中的元素的方法来减少大对象的内存消耗，比如，如果元素全是10进制的数字，可以考虑把它压缩成36进制或64进制。
或者不使用CopyOnWrite容器，而使用其他的并发容器，如ConcurrentHashMap。

<b>数据一致性问题</b>：CopyOnWrite容器只能保证数据的最终一致性，不能保证数据的实时一致性。所以如果你希望写入的的数据，马上能读到，
请不要使用CopyOnWrite容器。

参考文章：

    1. https://juejin.cn/post/6879291161274482695
    2. https://www.cnblogs.com/54chensongxia/p/12470446.html


