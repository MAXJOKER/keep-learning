package learnjava.javacollections;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;

/**
 * @author maxjoker
 * @date 2022-05-13 10:44
 *
 * Java LinkedHashMap & LinkedHashSet
 *
 */
public class JavaLinkedHashMap<K,V> extends LinkedHashMap<K, V> {
    private final int maxCapacity = 3;

    public static void main(String[] args) {
        // LinkedHashSet 继承了 HashSet，而HashSet是对HashMap做了一层封装，因此 LinkedHashSet
        // 我们看 LinkedHashSet的构造方法
        //  public LinkedHashSet(int initialCapacity, float loadFactor) {
        //        super(initialCapacity, loadFactor, true); // super的最后一个参数 dummy 如果为 true
        //  }
        //
        // dummy 为 true，对应的父类构造方法是
        //    /**
        //     * Constructs a new, empty linked hash set.  (This package private
        //     * constructor is only used by LinkedHashSet.) The backing
        //     * HashMap instance is a LinkedHashMap with the specified initial
        //     * capacity and the specified load factor.
        //     *
        //     * @param      initialCapacity   the initial capacity of the hash map
        //     * @param      loadFactor        the load factor of the hash map
        //     * @param      dummy             ignored (distinguishes this
        //     *             constructor from other int, float constructor.)
        //     * @throws     IllegalArgumentException if the initial capacity is less
        //     *             than zero, or if the load factor is nonpositive
        //     */
        //    HashSet(int initialCapacity, float loadFactor, boolean dummy) {
        //        map = new LinkedHashMap<>(initialCapacity, loadFactor);
        //    }
        // 从代码可以看出，LinkedHashSet 构造器最终会new出一个 LinkedHashMap 对象 (适配器模式)


        // LinkedHashMap 是 HashMap 的子类，二者唯一的区别是 LinkedHashMap在HashMap的基础上，采用 双向链表的形式，将所有 entry 连接
        // 起来，保证元素迭代顺序与插入顺序相同
        // 除了可以保迭代历顺序，这种结构还有一个好处 : 迭代LinkedHashMap时不需要像HashMap那样遍历整个table，
        // 而只需要直接遍历header指向的双向链表即可，也就是说LinkedHashMap的迭代时间就只跟entry的个数相关，而跟table的大小无关。

        // LinkedHashMap 用法
        //
        // 1、利用 LinkedHashMap 实现LRU缓存
        //
        // LRU即Least Recently Used，最近最少使用，也就是说，当缓存满了，会优先淘汰那些最近最不常访问的数据。我们的LinkedHashMap正好满足
        // 这个特性，为什么呢？当我们开启accessOrder为true时，最新访问(get或者put(更新操作))的数据会被丢到队列的尾巴处，
        // 那么双向队列的头就是最不经常使用的数据了。比如:
        // 如果有1 2 3这3个Entry，那么访问了1，就把1移到尾部去，即2 3 1。每次访问都把访问的那个数据移到双向队列的尾部去，那么每次要淘汰数据
        // 的时候，双向队列最头的那个数据不就是最不常访问的那个数据了吗？换句话说，双向链表最头的那个数据就是要淘汰的数据。
        // 此外，LinkedHashMap还提供了一个方法，这个方法就是为了我们实现LRU缓存而提供的，removeEldestEntry(Map.Entry<K,V> eldest) 方法。
        // 该方法可以提供在每次添加新条目时移除最旧条目的实现程序，默认返回 false。


        // 全局变量 、 内部类
        //
        //    LinkedHashMap 的 entry 结构，包含 before，after，用于指向双向链表的 前、后 节点
        //    /**
        //     * HashMap.Node subclass for normal LinkedHashMap entries.
        //     */
        //    static class Entry<K,V> extends HashMap.Node<K,V> {
        //        Entry<K,V> before, after; // before、After是用于维护Entry插入的先后顺序的(为了维护双向链表)
        //        Entry(int hash, K key, V value, Node<K,V> next) { // next是用于维护HashMap指定table位置上连接的Entry的顺序
        //            super(hash, key, value, next);
        //        }
        //    }
        //
        //    /**
        //     * The head (eldest) of the doubly linked list.  队头，迭代的时候，直接从队头开始，不用像HashMap那样迭代整个table
        //     */
        //    transient LinkedHashMap.Entry<K,V> head;
        //
        //    /**
        //     * The tail (youngest) of the doubly linked list. 队尾
        //     */
        //    transient LinkedHashMap.Entry<K,V> tail;
        //
        //    /**
        //     * The iteration ordering method for this linked hash map: true for access-order, false for insertion-order.
        //     * 链表的顺序，true 为 访问顺序，false 为 插入顺序
        //     * @serial
        //     */
        //    final boolean accessOrder;

        // 方法

        // 添加元素的方法
        //
        //    尾插法：把新增的元素插入链表末尾，从 newNode() 方法中可知
        //    // link at the end of list
        //    private void linkNodeLast(LinkedHashMap.Entry<K,V> p) {
        //        LinkedHashMap.Entry<K,V> last = tail;
        //        tail = p;
        //        if (last == null)
        //            head = p;
        //        else {
        //            p.before = last;
        //            last.after = p;
        //        }
        //    }
        //
        //    Node<K,V> newNode(int hash, K key, V value, Node<K,V> e) {
        //        LinkedHashMap.Entry<K,V> p =
        //            new LinkedHashMap.Entry<K,V>(hash, key, value, e);
        //        linkNodeLast(p);
        //        return p;
        //    }

        // 构造函数
        //    /**
        //     * Constructs an empty insertion-ordered <tt>LinkedHashMap</tt> instance
        //     * with the specified initial capacity and load factor.
        //     *
        //     * @param  initialCapacity the initial capacity
        //     * @param  loadFactor      the load factor
        //     * @throws IllegalArgumentException if the initial capacity is negative
        //     *         or the load factor is nonpositive
        //     */
        //    public LinkedHashMap(int initialCapacity, float loadFactor) {
        //        super(initialCapacity, loadFactor); // 指定 容量 和 负载因子
        //        accessOrder = false; // 链表顺序为插入顺序
        //    }
        //
        //    /**
        //     * Constructs an empty insertion-ordered <tt>LinkedHashMap</tt> instance
        //     * with the specified initial capacity and a default load factor (0.75).
        //     *
        //     * @param  initialCapacity the initial capacity
        //     * @throws IllegalArgumentException if the initial capacity is negative
        //     */
        //    public LinkedHashMap(int initialCapacity) {
        //        super(initialCapacity); // 指定 容量 ，使用默认的负载因子 0.75f
        //        accessOrder = false; // 链表顺序为插入顺序
        //    }
        //
        //    /**
        //     * Constructs an empty insertion-ordered <tt>LinkedHashMap</tt> instance
        //     * with the default initial capacity (16) and load factor (0.75).
        //     */
        //    public LinkedHashMap() {
        //        super(); // 使用默认容量 16，以及默认负载因子 0.75f
        //        accessOrder = false; // 链表顺序为插入顺序
        //    }
        //
        //    /**
        //     * Constructs an insertion-ordered <tt>LinkedHashMap</tt> instance with
        //     * the same mappings as the specified map.  The <tt>LinkedHashMap</tt>
        //     * instance is created with a default load factor (0.75) and an initial
        //     * capacity sufficient to hold the mappings in the specified map.
        //     *
        //     * @param  m the map whose mappings are to be placed in this map
        //     * @throws NullPointerException if the specified map is null
        //     */
        //    public LinkedHashMap(Map<? extends K, ? extends V> m) {
        //        super(); // 使用默认容量 16，以及默认负载因子 0.75f
        //        accessOrder = false; // 链表顺序为插入顺序
        //        putMapEntries(m, false); // 把 map m 放到 LinkedHashMap 中
        //    }
        //
        //    /**
        //     * Constructs an empty <tt>LinkedHashMap</tt> instance with the
        //     * specified initial capacity, load factor and ordering mode.
        //     *
        //     * @param  initialCapacity the initial capacity
        //     * @param  loadFactor      the load factor
        //     * @param  accessOrder     the ordering mode - <tt>true</tt> for
        //     *         access-order, <tt>false</tt> for insertion-order
        //     * @throws IllegalArgumentException if the initial capacity is negative
        //     *         or the load factor is nonpositive
        //     */
        //    public LinkedHashMap(int initialCapacity, float loadFactor, boolean accessOrder) {
        //        super(initialCapacity, loadFactor); // 指定 容量 和 负载因子
        //        this.accessOrder = accessOrder; // 指定排序，如果是false，则排序是根据访问次数来排，最少访问的放在head
        //    }

        // get方法
        //    /**
        //     * Returns the value to which the specified key is mapped,
        //     * or {@code null} if this map contains no mapping for the key.
        //     *
        //     * <p>More formally, if this map contains a mapping from a key
        //     * {@code k} to a value {@code v} such that {@code (key==null ? k==null :
        //     * key.equals(k))}, then this method returns {@code v}; otherwise
        //     * it returns {@code null}.  (There can be at most one such mapping.)
        //     *
        //     * <p>A return value of {@code null} does not <i>necessarily</i>
        //     * indicate that the map contains no mapping for the key; it's also
        //     * possible that the map explicitly maps the key to {@code null}.
        //     * The {@link #containsKey containsKey} operation may be used to
        //     * distinguish these two cases.
        //     */
        //    public V get(Object key) {
        //        Node<K,V> e;
        //        if ((e = getNode(hash(key), key)) == null)
        //            return null;
        //        if (accessOrder) // 注意看这里，如果 accessOrder 为 true的话，会额外调整链表的顺序
        //            afterNodeAccess(e);
        //        return e.value;
        //    }
        //
        //    来看看 afterNodeAccess 这个方法
        //
        //    void afterNodeAccess(Node<K,V> e) { // move node to last
        //        LinkedHashMap.Entry<K,V> last;
        //        if (accessOrder && (last = tail) != e) { // 如果 get的节点不是队尾
        //            LinkedHashMap.Entry<K,V> p = (LinkedHashMap.Entry<K,V>) e; // 赋值
        //            LinkedHashMap.Entry<K,V> b = p.before;
        //            LinkedHashMap.Entry<K,V> a = p.after;
        //            p.after = null; // 先将 p.after 置为空，因为 p 要移到队尾了
        //            if (b == null) // 如果 p.before 为空，说明 p 就是head，那么当p移到末尾时，p.after就要当做head
        //                head = a;
        //            else
        //                b.after = a; // p.before 不为空，那么 p.before.after = p.after，这一步是把 p 断链
        //            if (a != null) // p.after 不为空，那么 p.after.before = p.before
        //                a.before = b;
        //            else
        //                last = b; // p.after 为空，说明p是末尾了，此时 p.before 就变成末尾了
        //            if (last == null) // 上面的赋值中 last = tail / last = p.before，如果last为空，说明p前后都为空，p = head
        //                head = p;
        //            else { // last 不为空，p要移到末尾，那么 p.before = last，last.after = p，重新链接
        //                p.before = last;
        //                last.after = p;
        //            }
        //            tail = p; // p移到末尾，赋值给tail
        //            ++modCount; // modCount加1
        //        }
        //    }

        // 再来看看 当 accessOrder 为 true时，往LinkedHashMap里面插入元素 put(), putAll() 这两个方法都是调用父类 HashMap中的方法
        // 当执行到父类方法末尾的时候，会调用这两个方法
        // afterNodeAccess() // 1. move node to last
        // afterNodeInsertion(boolean evict) // 2. possibly remove eldest
        // 可以看出来，（1）插入后，会把元素插入到末尾；（2）可能会删除老key（很久没被访问到的key，位于head）
        // 看看 afterNodeInsertion(boolean evict) 的代码
        //
        //    /**
        //     * @param boolean evict 是否驱逐 eldest
        //     *
        //     */
        //    void afterNodeInsertion(boolean evict) { // possibly remove eldest
        //        LinkedHashMap.Entry<K,V> first;
        //        // 通过removeEldestEntry决定是否移除老key, LinkedHashMap 中默认是返回false，如果有这个需要，继承LinkedHashMap后，
        //        // 重写一下 removeEldestEntry 方法就好了
        //        if (evict && (first = head) != null && removeEldestEntry(first)) {
        //            K key = first.key;
        //            removeNode(hash(key), key, null, false, true);
        //        }
        //    }
        //
        //     /**
        //     * Returns <tt>true</tt> if this map should remove its eldest entry.
        //     * This method is invoked by <tt>put</tt> and <tt>putAll</tt> after
        //     * inserting a new entry into the map.  It provides the implementor
        //     * with the opportunity to remove the eldest entry each time a new one
        //     * is added.  This is useful if the map represents a cache: it allows
        //     * the map to reduce memory consumption by deleting stale entries.
        //     *
        //     * <p>Sample use: this override will allow the map to grow up to 100
        //     * entries and then delete the eldest entry each time a new entry is
        //     * added, maintaining a steady state of 100 entries.
        //     * <pre>
        //     *     private static final int MAX_ENTRIES = 100;
        //     *
        //     *     protected boolean removeEldestEntry(Map.Entry eldest) {
        //     *        return size() &gt; MAX_ENTRIES;
        //     *     }
        //     * </pre>
        //     *
        //     * <p>This method typically does not modify the map in any way,
        //     * instead allowing the map to modify itself as directed by its
        //     * return value.  It <i>is</i> permitted for this method to modify
        //     * the map directly, but if it does so, it <i>must</i> return
        //     * <tt>false</tt> (indicating that the map should not attempt any
        //     * further modification).  The effects of returning <tt>true</tt>
        //     * after modifying the map from within this method are unspecified.
        //     *
        //     * <p>This implementation merely returns <tt>false</tt> (so that this
        //     * map acts like a normal map - the eldest element is never removed).
        //     *
        //     * @param    eldest The least recently inserted entry in the map, or if
        //     *           this is an access-ordered map, the least recently accessed
        //     *           entry.  This is the entry that will be removed it this
        //     *           method returns <tt>true</tt>.  If the map was empty prior
        //     *           to the <tt>put</tt> or <tt>putAll</tt> invocation resulting
        //     *           in this invocation, this will be the entry that was just
        //     *           inserted; in other words, if the map contains a single
        //     *           entry, the eldest entry is also the newest.
        //     * @return   <tt>true</tt> if the eldest entry should be removed
        //     *           from the map; <tt>false</tt> if it should be retained.
        //     */
        //    protected boolean removeEldestEntry(Map.Entry<K,V> eldest) {
        //        return false; // 默认返回false
        //    }

        JavaLinkedHashMap<String, Integer> linkedHashMap = new JavaLinkedHashMap<String, Integer>();
        linkedHashMap.put("1", 1);
        linkedHashMap.put("2", 2);
        linkedHashMap.put("3", 3);

        System.out.println(linkedHashMap);

    }

    public JavaLinkedHashMap() {
        super(16, 0.75f, true);
    }

    /**
     * 重写 removeEldestEntry 方法，如果 size() LinkedHashMap 大小 大于 最大容量 maxCapacity，那么当新元素插入时，驱逐head
     * @param eldest
     * @return
     */
    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > maxCapacity;
    }
}

class Test {
    public static void main(String[] args) {
        JavaLinkedHashMap<String, Integer> map = new JavaLinkedHashMap<>();
        map.put("1", 1);
        map.put("2", 2);
        map.put("3", 3);
        map.get("1");
        map.put("4", 4);

        // 打印的结果是 {3=3, 1=1, 4=4}
        System.out.println(map);
    }
}

/**
 * 使用 LinkedHashMap 简单实现 LRU算法
 */
class LRUCache extends LinkedHashMap
{
    public LRUCache(int maxSize)
    {
        super(maxSize, 0.75F, true);
        maxElements = maxSize;
    }

    @Override
    protected boolean removeEldestEntry(java.util.Map.Entry eldest)
    {
        // 逻辑很简单，当大小超出了Map的容量，就移除掉双向队列头部的元素，给其他元素腾出点地来。
        return size() > maxElements;
    }

    private static final long serialVersionUID = 1L;
    protected int maxElements;
}
