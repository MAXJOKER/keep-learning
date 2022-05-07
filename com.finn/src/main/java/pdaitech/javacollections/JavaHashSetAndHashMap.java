package pdaitech.javacollections;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @author maxjoker
 * @date 2022-05-06 17:47
 * 参考：
 * https://onblogs.net/_posts/2020/2020-07-10-%E9%80%90%E8%A1%8C%E8%A7%A3%E8%AF%BBHashMap%E6%BA%90%E7%A0%81%E5%85%A8%E6%96%87/
 * https://www.cxyxiaowu.com/18069.html
 * https://zhuanlan.zhihu.com/p/79219960
 * https://juejin.cn/post/6844903799748821000
 * https://blog.csdn.net/weixin_42340670/article/details/80673127
 * https://blog.csdn.net/SnailMann/article/details/83989635?utm_medium=distribute.pc_relevant.none-task-blog-2~default~baidujs_title~default-0-83989635-blog-80673127.pc_relevant_default&spm=1001.2101.3001.4242.1&utm_relevant_index=3
 * https://juejin.cn/post/6844903574552461320
 * https://tech.meituan.com/2016/06/24/java-hashmap.html
 * https://segmentfault.com/a/1190000012926722
 */
public class JavaHashSetAndHashMap {
    public static void main(String[] args) {
        // HashSet 其实就是对 HashMap 进行了一层封装而已
        // 看其底层结构便知
        //     private transient HashMap<E,Object> map;
        //     /**
        //     * Constructs a new, empty set; the backing <tt>HashMap</tt> instance has
        //     * default initial capacity (16) and load factor (0.75).
        //     */
        //    public HashSet() {
        //        map = new HashMap<>();
        //    }

        // 使用拉链法来解决哈希冲突


        HashMap<String, String> hashMap = new HashMap<>();


        // 全局变量
        //     /**
        //      * 扩容阈值
        //      * The next size value at which to resize (capacity * load factor).
        //      * @serial
        //      */
        //     int threshold;
        //
        //    /**
        //     * The load factor for the hash table.
        //     * 负载因子 默认是 0.75
        //     * @serial
        //     */
        //    final float loadFactor;


        // 常量
        //    /**
        //     * The default initial capacity - MUST be a power of two.  默认初始化容量为16
        //     */
        //    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16


        // 构造方法
        // 有4个，常用的是无参构造方法
        //
        //    /**
        //     * 无参构造方法
        //     * Constructs an empty <tt>HashMap</tt> with the default initial capacity
        //     * (16) and the default load factor (DEFAULT_LOAD_FACTOR = 0.75).
        //     */
        //    public HashMap() {
        //        // 初始化负载因子
        //        this.loadFactor = DEFAULT_LOAD_FACTOR; // all other fields defaulted
        //    }
        //
        //
        //    /**
        //     * Constructs an empty <tt>HashMap</tt> with the specified initial
        //     * capacity and load factor.
        //     *
        //     * @param  initialCapacity the initial capacity
        //              初始容量（默认16）： hashMap底层由数组实现+链表（或红黑树）实现，
        //              但是还是从数组开始，所以当储存的数据越来越多的时候，就必须进行扩容操作，如果在知道需要储存数据大小的情况下，
        //              指定合适的初始容量，可以避免不必要的扩容操作，提升效率
        //     * @param  loadFactor      the load factor
        //              加载因子（默认0.75）：当负载因子较大时，去给table数组扩容的可能性就会少，
        //              所以相对占用内存较少（空间上较少），但是每条entry链上的元素会相对较多，查询的时间也会增长（时间上较多）。
        //              反之就是，负载因子较少的时候，table数组扩容的可能性就高，那么内存空间占用就多，但是entry链上的元素就会相对较少，
        //              查出的时间也会减少。所以才有了负载因子是时间和空间上的一种折中的说法。所以设置负载因子的时候要考虑自己追求的是时间
        //              还是空间上的少。（一般情况下不需要设置，系统给的默认值已经比较适合了）
        //     *        threshold
        //     *        hashMap所能容纳的最大键值对数量，如果超过则需要扩容，计算方式：threshold=initialCapacity*loadFactor
        //              （构造方法中直接通过tableSizeFor(initialCapacity)方法进行了赋值，主要原因是在构造方法中，数组table并没有初始化，
        //              put方法中进行初始化，同时put方法中也会对threshold进行重新赋值）
        //     * @throws IllegalArgumentException if the initial capacity is negative
        //     *         or the load factor is nonpositive
        //     */
        //    public HashMap(int initialCapacity, float loadFactor) {
        //        if (initialCapacity < 0)
        //            throw new IllegalArgumentException("Illegal initial capacity: " +
        //                                               initialCapacity);
        //        // MAXIMUM_CAPACITY = 1 <<< 30
        //        if (initialCapacity > MAXIMUM_CAPACITY)
        //            initialCapacity = MAXIMUM_CAPACITY;
        //        if (loadFactor <= 0 || Float.isNaN(loadFactor))
        //            throw new IllegalArgumentException("Illegal load factor: " +
        //                                               loadFactor);
        //        this.loadFactor = loadFactor;
        //        this.threshold = tableSizeFor(initialCapacity);
        //    }
        //
        //    /**
        //     * Returns a power of two size for the given target capacity. 找到大于或等于 cap 的最小2的幂
        //     */
        //    static final int tableSizeFor(int cap) {
        //        // cap - 1 防止在cap已经是2的n次幂的情况下，经过运算后得到的结果是cap的二倍的结果，例如如果n为l6，
        //        // 经过一系列运算之后，得到的结果是0001 1111，此时最后一步n+1 执行之后，就会返回32，但预期应该返回16
        //        int n = cap - 1;
        //        n |= n >>> 1;
        //        n |= n >>> 2;
        //        n |= n >>> 4;
        //        n |= n >>> 8;
        //        n |= n >>> 16;
        //        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
        //    }


        // put 方法
        //    /**
        //     * Associates the specified value with the specified key in this map.
        //     * If the map previously contained a mapping for the key, the old
        //     * value is replaced.
        //     *
        //     * @param key key with which the specified value is to be associated
        //     * @param value value to be associated with the specified key
        //     * @return the previous value associated with <tt>key</tt>, or
        //     *         <tt>null</tt> if there was no mapping for <tt>key</tt>.
        //     *         (A <tt>null</tt> return can also indicate that the map
        //     *         previously associated <tt>null</tt> with <tt>key</tt>.)
        //     */
        //    public V put(K key, V value) {
        //        return putVal(hash(key), key, value, false, true);
        //    }
        //
        //    这里先看下 hash 的方法
        //    /**
        //     * Computes key.hashCode() and spreads (XORs) higher bits of hash
        //     * to lower.  Because the table uses power-of-two(二次幂) masking(掩码), sets of
        //     * hashes that vary(变化) only in bits above the current mask will
        //     * always collide(碰撞). (Among known examples are sets of Float keys
        //     * holding consecutive(连续的) whole numbers in small tables.)  So we
        //     * apply a transform that spreads the impact of higher bits
        //     * downward. There is a tradeoff(权衡) between speed, utility, and
        //     * quality of bit-spreading. Because many common sets of hashes
        //     * are already reasonably distributed (so don't benefit from
        //     * spreading), and because we use trees to handle large sets of
        //     * collisions in bins, we just XOR some shifted bits in the
        //     * cheapest possible way to reduce systematic(系统) lossage(损失), as well as
        //     * to incorporate(合并) impact of the highest bits that would otherwise
        //     * never be used in index calculations because of table bounds.
        //     *
        //     * 从上面的注释来看，简单来说就是 为了让高位与低进行混合，让两者都参与运算，以便让hash值分布更加均匀.
        //     * (1) hash 是由键的 hashCode 产生。putVal 等方法中，计算余数时( (n - 1) & hash )，由于 n = size 比较小，hash 只有低位参与了计算，
        //     *    高位的计算可以认为是无效的。这样导致了计算结果只与低位信息有关，高位数据没发挥作用。为了处理这个缺陷，hash 高16位数据与低16位数据进行异或运算，
        //     *    即 hash ^ (hash >>> 16)。通过这种方式，让高位数据与低位数据进行异或，以此加大低位信息的随机性，变相的让高位数据参与到计算中。
        //     *    在 Java 中，hashCode 方法产生的 hash 是 int 类型，32 位。前16位为高位，后16位为低位，所以要右移16位。
        //     * (2) 重新计算 hash 的另一个好处是可以增加 hash 的复杂度，减少hash冲突
        //     */
        //    static final int hash(Object key) {
        //        int h;
        //        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
        //    }
        //
        //
        //    /**
        //     * Implements Map.put and related methods.
        //     *
        //     * @param hash hash for key
        //     * @param key the key
        //     * @param value the value to put
        //     * @param onlyIfAbsent if true, don't change existing value  如果参数为true，则不会修改已有的值
        //     * @param evict if false, the table is in creation mode.
        //     * @return previous value, or null if none
        //     * 总的来说，就是
        //     * (1) 检查table是否为空，为空则进行初始化，初始容量赋值，重新计算扩容阈值
        //     * (2) 计算插入下标，如果下标处为空，则直接插入；否则说明发生hash碰撞，需要继续找出最终的插入位置
        //     * (3) 判断碰撞的节点是否为头结点，如果是，则先保存头结点到一个临时节点中；如果不是，进行第4步
        //     * (4) 判断是否为红黑树结构，是的话使用红黑树方法插入；否则进入第5步
        //     * (5) 沿着链表循环往下找插入位置
        //     * (6) 如果找到链表末尾，也没有找到相同的值，直接将要插入的值放到链表末尾，并检查是否要将链表转变为红黑树
        //     * (7) 如果找到相同的值，则直接结束循环，否则一直循环下去
        //     * (8) 经过上面的循环后，如果e不为空，则说明上面插入的值已经存在于当前的hashMap中，那么更新指定位置的键值对(需要判断onlyIfAbsent)
        //     * (9) hashmap size++，并检查是否要扩容
        //     */
        //    final V putVal(int hash, K key, V value, boolean onlyIfAbsent, boolean evict) {
        //        Node<K,V>[] tab;
        //        Node<K,V> p;
        //        int n, i;
        //
        //        // 如果table还没被初始化，则在此处进行初始化，并对初始容量进行赋值，重新计算扩容阈值(resize()方法里操作)
        //        if ((tab = table) == null || (n = tab.length) == 0) {
        //            n = (tab = resize()).length;
        //        }
        //
        //        p = tab[i = (n - 1) & hash];
        //
        //        if (p == null) { // 取余计算出put的下标位置，如果指定插入的位置为空，则直接将数据插进去
        //            tab[i] = newNode(hash, key, value, null);
        //        } else { // 发生hash碰撞，若键已存在就返回该Node，并用属性 e 引用，若键不存在就创建一个新的Node，并直接插入到桶中
        //            Node<K,V> e;
        //            K k = p.key;
        //            if (p.hash == hash && (k == key || (key != null && key.equals(k)))) { // 检查碰撞的节点是否为头结点，即 头结点 == 插入节点
        //                e = p;
        //            } else if (p instanceof TreeNode) { // 如果bucket中的数据类型为 treeNode，使用红黑树的方法进行插入
        //                e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
        //            } else { // 此时bucket中的数据结构为链表，且碰撞的节点不是头结点，沿着链表循环继续往下找
        //                for (int binCount = 0; ; ++binCount) {
        //                    if ((e = p.next) == null) { // 如果链表中没有其他节点了(也没有相等的节点)，则将要插入的节点放到链表末尾
        //                        p.next = newNode(hash, key, value, null);
        //                        // 通过循环的次数可以得出链表的长度，如果达到树化阈值，则将链表转变为红黑树
        //                        if (binCount >= TREEIFY_THRESHOLD - 1) { // -1 for 1st
        //                            treeifyBin(tab, hash);
        //                        }
        //                        break;
        //                    }
        //                    // 条件为 true，表示当前链表包含要插入的键值对，终止遍历
        //                    if (e.hash == hash && ((k = e.key) == key || (key != null && key.equals(k)))) {
        //                        break;
        //                    }
        //                    p = e;
        //                }
        //            }
        //            // 经过上面的循环后，如果e不为空，则说明上面插入的值已经存在于当前的hashMap中，那么更新指定位置的键值对
        //            if (e != null) { // existing mapping for key
        //                V oldValue = e.value;
        //                // onlyIfAbsent 表示是否仅在 oldValue 为 null 的情况下更新键值对的值
        //                if (!onlyIfAbsent || oldValue == null) // 直接覆盖，并返回旧值
        //                    e.value = value;
        //                afterNodeAccess(e); // Callbacks to allow LinkedHashMap post-actions
        //                return oldValue;
        //            }
        //        }
        //        ++modCount;
        //        if (++size > threshold) // size大于扩容阈值，进行扩容
        //            resize();
        //        afterNodeInsertion(evict);
        //        return null;
        //    }
        //
        //
        //  resize() 方法
        //    /**
        //     * Initializes or doubles table size.  If null, allocates in
        //     * accord with initial capacity target held in field threshold.
        //     * Otherwise, because we are using power-of-two expansion, the
        //     * elements from each bin must either stay at same index, or move
        //     * with a power of two offset in the new table.
        //     *
        //     * @return the table
        //     */
        //    final Node<K,V>[] resize() {
        //        Node<K,V>[] oldTab = table;
        //        int oldCap = (oldTab == null) ? 0 : oldTab.length;
        //        int oldThr = threshold;
        //        int newCap, newThr = 0;
        //
        //        // table 已经初始化，且容量大于0
        //        if (oldCap > 0) {
        //            if (oldCap >= MAXIMUM_CAPACITY) { // 如果已经接近最大容量了，则不再进行扩容，直接设置扩容阈值为最大值
        //                threshold = Integer.MAX_VALUE;
        //                return oldTab;
        //            } else if ((newCap = oldCap << 1) < MAXIMUM_CAPACITY && oldCap >= DEFAULT_INITIAL_CAPACITY) {
        //                // 否则 新的容量 = 旧容量 * 2 并且 新的容量 < 最大容量 并且 旧容量 >= 默认初始容量(16)
        //                newThr = oldThr << 1; // double threshold 新的扩容阈值 = 旧阈值 * 2
        //            }
        //        } else if (oldThr > 0) { // initial capacity was placed in threshold 阈值大于0，暂时使用阈值来初始化容量
        //            newCap = oldThr;
        //        } else {               // zero initial threshold signifies using defaults 如果容量和阈值都为0，也就是初次使用的情况，通过 new HashMap<>() 无参构造;
        //            newCap = DEFAULT_INITIAL_CAPACITY;
        //            newThr = (int)(DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY);
        //        }
        //        if (newThr == 0) {
        //            float ft = (float)newCap * loadFactor;
        //            newThr = (newCap < MAXIMUM_CAPACITY && ft < (float)MAXIMUM_CAPACITY ? (int)ft : Integer.MAX_VALUE);
        //        }
        //        threshold = newThr; // 更新扩容阈值
        //        @SuppressWarnings({"rawtypes","unchecked"})
        //        Node<K,V>[] newTab = (Node<K,V>[])new Node[newCap];
        //        table = newTab; // 更新Node数组
        //        if (oldTab != null) { // 如果之前的数组桶里面已经存在数据，由于table容量发生变化，hash值也会发生变化，需要重新计算下标
        //            for (int j = 0; j < oldCap; ++j) { // 遍历旧数组，将旧数组的值移到新数组中
        //                Node<K,V> e;
        //                if ((e = oldTab[j]) != null) {
        //                    oldTab[j] = null; // 旧数组对应值置空，For GC
        //                    if (e.next == null) { // 如果Node中只有一个数据，则直接将数据存放到新计算的下标
        //                        newTab[e.hash & (newCap - 1)] = e;
        //                    } else if (e instanceof TreeNode) { // 如果是 TreeNode 结构
        //                        ((TreeNode<K,V>)e).split(this, newTab, j, oldCap);
        //                    } else { // preserve order  如果是链表，重新计算hash值，根据新的下标重新分组
        //                        Node<K,V> loHead = null, loTail = null; // 原桶的头尾节点引用
        //                        Node<K,V> hiHead = null, hiTail = null; // 新桶的头尾节点引用
        //                        Node<K,V> next;
        //                        do { // 循环遍历桶内碰撞节点
        //                            next = e.next;
        //
        //                            // 将链表节点按原顺序进行分组， e.hash & oldCap == 0，位置不变；e.hash & oldCap == 1，位置为 j + oldCap，即位移原来的容量大小
        //                            // https://onblogs.net/_posts/2020/2020-07-10-%E9%80%90%E8%A1%8C%E8%A7%A3%E8%AF%BBHashMap%E6%BA%90%E7%A0%81%E5%85%A8%E6%96%87/
        //                            // 因为我们使用的是 2 的幂，所以每个桶中的元素必须保持相同的索引，或者在新 table 中以 2 的幂偏移。
        //                            // 举个例子，例如容量从 16 扩展为 32 时，具体变化如下：
        //                            //
        //                            // 16-1  =  0000 0000 0000 0000 0000 0000 0000 1111
        //                            // hash1 =  0000 0000 0000 0000 0000 0000 0000 1111
        //                            // hash2 =  0000 0000 0000 0000 0000 0000 0001 1111
        //                            // 桶下标为
        //                            // (16-1)&hash1 = 0000 0000 0000 0000 0000 0000 0000 1111
        //                            // (16-1)&hash2 = 0000 0000 0000 0000 0000 0000 0000 1111
        //                            // 容量为 16 时，hash1 和 hash2 经过桶下标计算后结果相同，会进入同一个桶中。当容量扩展为 32 后，新的桶下标计算过程如下所示：
        //                            //
        //                            // 32-1  =  0000 0000 0000 0000 0000 0000 0001 1111
        //                            // hash1 =  0000 0000 0000 0000 0000 0000 0000 1111
        //                            // hash2 =  0000 0000 0000 0000 0000 0000 0001 1111
        //                            // 桶下标为
        //                            // (32-1)&hash1 = 0000 0000 0000 0000 0000 0000 0000 1111
        //                            // (32-1)&hash2 = 0000 0000 0000 0000 0000 0000 0001 1111
        //                            // hash1 和 hash2 经过桶下标公式重新计算之后，hash1的结果不变，所以依旧在原来的桶里；
        //                            // 而 hash2 的结果比原来多了 1 位，即 2^4 = 16，也就是偏移了原来的容量大小。
        //                            // 因此，在扩充 HashMap 的时候，不需要重新计算 hash，只需要检查二进制 hash 中与二进制桶下标中
        //                            // 新增的有效位的位置相同的那个位（以下简称“新增位”）是 0 还是 1 即可，是 0 的话索引没变，是 1 的话索引变成“原索引+oldCap”。
        //
        //                            // 如何检查新增位是 0 还是 1 呢？HashMap 中使用 hash & oldCap 位与运算检查该新增位。
        //                            // oldCap 是 2 的幂，故二进制表示只有 1 位是 1，且该位正好与之对应。不得不说这个设计还是非常巧妙的，
        //                            // 既省去了重新计算 hash 值的时间，而且，由于新增的 1 位是 0 还是 1 可以认为是随机的，
        //                            // 因此在扩容的过程，均匀的把之前碰撞的节点分散到新旧桶中。
        //
        //
        //                            if ((e.hash & oldCap) == 0) {
        //                                if (loTail == null)
        //                                    loHead = e;
        //                                else
        //                                    loTail.next = e;
        //                                loTail = e;
        //                            } else {
        //                                if (hiTail == null)
        //                                    hiHead = e;
        //                                else
        //                                    hiTail.next = e;
        //                                hiTail = e;
        //                            }
        //                        } while ((e = next) != null);
        //                        if (loTail != null) {
        //                            loTail.next = null;
        //                            newTab[j] = loHead;
        //                        }
        //                        if (hiTail != null) {
        //                            hiTail.next = null;
        //                            newTab[j + oldCap] = hiHead;
        //                        }
        //                    }
        //                }
        //            }
        //        }
        //        return newTab;
        //    }
        //
        //
        // 红黑树的插入
        //        /**
        //         * Tree version of putVal.
        //         */
        //        final TreeNode<K,V> putTreeVal(HashMap<K,V> map, Node<K,V>[] tab, int h, K k, V v) {
        //            Class<?> kc = null;
        //            boolean searched = false;
        //            TreeNode<K,V> root = (parent != null) ? root() : this;  // root: 树根节点
        //            for (TreeNode<K,V> p = root;;) {
        //                // p: 当前节点
        //                // dir: 标识新节点应该插入到当前节点的左子树还是右子树, -1或者0 - 左， 1 - 右
        //                // ph: 当前节点的hash值
        //                // pk: 当前节点的key
        //                int dir, ph; K pk;
        //                if ((ph = p.hash) > h) // 当前节点大于插入节点
        //                    dir = -1;
        //                else if (ph < h) // 当前节点小于插入节点
        //                    dir = 1;
        //                else if ((pk = p.key) == k || (k != null && k.equals(pk)))  // hash值相同，key也相同的话，就是相同的节点，不用插入了
        //                    return p;
        //                else if ((kc == null && (kc = comparableClassFor(k)) == null) || (dir = compareComparables(kc, k, pk)) == 0) {
        //                    // hash值相同，且不能够通过Comparable进行比较
        //                    // comparableClassFor方法来获取该元素键的Class, 看看是否实现了Comparable接口, 然后再通过compareComparables方法来比较两个对象的大小
        //                    if (!searched) {
        //                        // 在红黑树上查找看看有没有相同的key，有的话就直接返回了，不用更新
        //                        TreeNode<K,V> q, ch;
        //                        searched = true;
        //                        if (((ch = p.left) != null && (q = ch.find(h, k, kc)) != null) ||
        //                            ((ch = p.right) != null && (q = ch.find(h, k, kc)) != null))
        //                            return q;
        //                    }
        //                    dir = tieBreakOrder(k, pk); // 这里是最终确定插入节点的位置了
        //                }
        //
        //                TreeNode<K,V> xp = p;
        //                if ((p = (dir <= 0) ? p.left : p.right) == null) { // 遍历到空的节点
        //                    Node<K,V> xpn = xp.next; // 父节点指向新节点, 将当前的next先保存在xpn中
        //                    TreeNode<K,V> x = map.newTreeNode(h, k, v, xpn); // x.next是xpn
        //                    // 新节点位置在左子树还是右子树
        //                    if (dir <= 0)
        //                        xp.left = x;
        //                    else
        //                        xp.right = x;
        //                    xp.next = x; // 父节点xp的next变成x
        //                    x.parent = x.prev = xp; // 新节点指向父节点
        //                    if (xpn != null)  // 如果有兄弟节点，兄弟节点的上一个节点指向新节点
        //                        ((TreeNode<K,V>)xpn).prev = x;
        //                    // 最后平衡红黑树，并保证根节点在哈希桶的头部
        //                    moveRootToFront(tab, balanceInsertion(root, x));
        //                    return null;
        //                }
        //            }
        //        }
        //
        //    /**
        //     * Returns x's Class if it is of the form "class C implements
        //     * 如果对象x的类是C，如果C实现了Comparable<C>接口，那么返回C，否则返回null
        //     * Comparable<C>", else null.
        //     */
        //    static Class<?> comparableClassFor(Object x) {
        //        if (x instanceof Comparable) {
        //            Class<?> c;
        //            Type[] ts, as;
        //            Type t;
        //            ParameterizedType p;
        //            if ((c = x.getClass()) == String.class) // bypass checks // 如果x是个字符串对象
        //                // 为什么如果x是个字符串就直接返回c了呢 ? 因为String  实现了 Comparable 接口，可参考如下String类的定义
        //                // public final class String implements java.io.Serializable, Comparable<String>, CharSequence
        //                return c;
        //            // 如果 c 不是字符串类，获取c直接实现的接口（如果是泛型接口则附带泛型信息）
        //            if ((ts = c.getGenericInterfaces()) != null) {
        //                for (int i = 0; i < ts.length; ++i) {
        //                      // 如果当前接口t是个泛型接口
        //                      // 如果该泛型接口t的原始类型p 是 Comparable 接口
        //                      // 如果该Comparable接口p只定义了一个泛型参数
        //                      // 如果这一个泛型参数的类型就是c，那么返回c
        //                    if (((t = ts[i]) instanceof ParameterizedType) &&
        //                        ((p = (ParameterizedType)t).getRawType() ==
        //                         Comparable.class) &&
        //                        (as = p.getActualTypeArguments()) != null &&
        //                        as.length == 1 && as[0] == c) // type arg is c
        //                        return c;
        //                }
        //            }
        //        }
        //        return null;
        //    }
        //
        //    /**
        //     * Returns k.compareTo(x) if x matches kc (k's screened comparable
        //     * class), else 0.
        //     * 如果x所属的类是kc，返回k.compareTo(x)的比较结果
        //     * 如果x为空，或者其所属的类不是kc，返回0
        //     */
        //    @SuppressWarnings({"rawtypes","unchecked"}) // for cast to Comparable
        //    static int compareComparables(Class<?> kc, Object k, Object x) {
        //        return (x == null || x.getClass() != kc ? 0 :
        //                ((Comparable)k).compareTo(x));
        //    }
        //
        //        /**
        //         * Finds the node starting at root p with the given hash and key.
        //         * The kc argument caches comparableClassFor(key) upon first use
        //         * comparing keys.
        //         */
        //        final TreeNode<K,V> find(int h, Object k, Class<?> kc) {
        //            TreeNode<K,V> p = this;
        //            do {
        //                int ph, dir; K pk;
        //                TreeNode<K,V> pl = p.left, pr = p.right, q;
        //                if ((ph = p.hash) > h)
        //                    p = pl;
        //                else if (ph < h)
        //                    p = pr;
        //                else if ((pk = p.key) == k || (k != null && k.equals(pk)))
        //                    return p;
        //                else if (pl == null)
        //                    p = pr;
        //                else if (pr == null)
        //                    p = pl;
        //                else if ((kc != null ||
        //                          (kc = comparableClassFor(k)) != null) &&
        //                         (dir = compareComparables(kc, k, pk)) != 0)
        //                    p = (dir < 0) ? pl : pr;
        //                else if ((q = pr.find(h, k, kc)) != null)
        //                    return q;
        //                else
        //                    p = pl;
        //            } while (p != null);
        //            return null;
        //        }
        //
        //        /**
        //         * 用这个方法来比较两个对象，返回值要么大于0，要么小于0，不会为0,
        //         * 也就是说这一步一定能确定要插入的节点要么是树的左节点，要么是右节点，不然就无法继续满足二叉树结构了
        //         * Tie-breaking utility for ordering insertions when equal
        //         * hashCodes and non-comparable. We don't require a total
        //         * order, just a consistent insertion rule to maintain
        //         * equivalence across rebalancings. Tie-breaking further than
        //         * necessary simplifies testing a bit.
        //         */
        //        static int tieBreakOrder(Object a, Object b) {
        //            int d;
        //            // 先比较两个对象的类名，类名是字符串对象，就按字符串的比较规则
        //            // 如果两个对象是同一个类型，那么调用本地方法为两个对象生成hashCode值，再进行比较，hashCode相等的话返回-1
        //            if (a == null || b == null ||
        //                (d = a.getClass().getName().
        //                 compareTo(b.getClass().getName())) == 0)
        //                d = (System.identityHashCode(a) <= System.identityHashCode(b) ?
        //                     -1 : 1);
        //            return d;
        //        }

    }
}
