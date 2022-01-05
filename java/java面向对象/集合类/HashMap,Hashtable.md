转载】原文链接：https://blog.csdn.net/yang13563758128/article/details/86655574

### HashMap和Hashtable的区别 ### 

HashMap 时间复杂度 O(1)

1. 共同点:都是双列集合,底层都是哈希算法,默认负载因子都为0.75

2. 区别:
* 1.HashMap是线程不安全的，效率高，JDK1.2版本
* Hashtable是线程安全的，效率低，JDK1.0版本
* 2.HashMap可以存储null键和null值
* Hashtable不可以存储null键和null值


3. 代码示例:
```
public class testHashtable {
    public static void main(String []args){
        HashMap<String,Integer> hm=new HashMap<>();
        hm.put(null,1);
        hm.put("张三",null);
        System.out.println(hm);
        Hashtable<String,Integer> ht=new Hashtable<>();
        ht.put(null,8);
        ht.put("张三",null);
        System.out.println(ht);
    }
}
```
HashMap与Hashtable的区别是面试中经常遇到的一个问题。这个问题看似简单，但如果深究进去，也能了解到不少知识。本文对两者从来源，特性，算法等多个方面进行对比总结。力争多角度，全方位的展示二者的不同，做到此问题的终结版。


### 产生时间 ###
Hashtable是java一开始发布时就提供的键值映射的数据结构，而HashMap产生于JDK1.2。虽然Hashtable比HashMap出现的早一些，但是现在Hashtable基本上已经被弃用了。而HashMap已经成为应用最为广泛的一种数据类型了。造成这样的原因一方面是因为Hashtable是线程安全的，效率比较低。另一方面可能是因为Hashtable没有遵循驼峰命名法吧。。。

### 继承的父类不同 ###
HashMap和Hashtable不仅作者不同，而且连父类也是不一样的。HashMap是继承自AbstractMap类，而HashTable是继承自Dictionary类。不过它们都实现了同时实现了map、Cloneable（可复制）、Serializable（可序列化）这三个接口


Dictionary类是一个已经被废弃的类（见其源码中的注释）。父类都被废弃，自然而然也没人用它的子类Hashtable了。 
* NOTE: This class is obsolete. New implementations should 
* implement the Map interface, rather than extending this class.

### 对外提供的接口不同 ###
Hashtable比HashMap多提供了elments() 和contains() 两个方法。

elments() 方法继承自Hashtable的父类Dictionnary。elements() 方法用于返回此Hashtable中的value的枚举。

contains()方法判断该Hashtable是否包含传入的value。它的作用与containsValue()一致。事实上，contansValue() 就只是调用了一下contains() 方法。

### 对Null key 和Null value的支持不同 ###
Hashtable既不支持Null key也不支持Null value。Hashtable的put()方法的注释中有说明。 

当key为Null时，调用put() 方法，运行到下面这一步就会抛出空指针异常。因为拿一个Null值去调用方法了。 

当value为null值时，Hashtable对其做了限制，运行到下面这步也会抛出空指针异常。 

HashMap中，null可以作为键，这样的键只有一个；可以有一个或多个键所对应的值为null。当get()方法返回null值时，可能是 HashMap中没有该键，也可能使该键所对应的值为null。因此，在HashMap中不能由get()方法来判断HashMap中是否存在某个键， 而应该用containsKey()方法来判断。

### 线程安全性不同 ###
Hashtable是线程安全的，它的每个方法中都加入了Synchronize方法。在多线程并发的环境下，可以直接使用Hashtable，不需要自己为它的方法实现同步

HashMap不是线程安全的，在多线程并发的环境下，可能会产生死锁等问题。具体的原因在下一篇文章中会详细进行分析。使用HashMap时就必须要自己增加同步处理，

虽然HashMap不是线程安全的，但是它的效率会比Hashtable要好很多。这样设计是合理的。在我们的日常使用当中，大部分时间是单线程操作的。HashMap把这部分操作解放出来了。当需要多线程操作的时候可以使用线程安全的<b>ConcurrentHashMap</b>。ConcurrentHashMap虽然也是线程安全的，但是它的效率比Hashtable要高好多倍。因为ConcurrentHashMap使用了分段锁，并不对整个数据进行锁定。

### 遍历方式的内部实现上不同 ###
Hashtable、HashMap都使用了 Iterator。而由于历史原因，Hashtable还使用了Enumeration的方式 。

HashMap的Iterator是fail-fast迭代器。当有其它线程改变了HashMap的结构（增加，删除，修改元素），将会抛出ConcurrentModificationException。不过，通过Iterator的remove()方法移除元素则不会抛出ConcurrentModificationException异常。但这并不是一个一定发生的行为，要看JVM。

JDK8之前的版本中，Hashtable是没有fast-fail机制的。在JDK8及以后的版本中 ，HashTable也是使用fast-fail的


modCount的使用类似于并发编程中的CAS（Compare and Swap）技术。我们可以看到这个方法中，每次在发生增删改的时候都会出现modCount++的动作。而modcount可以理解为是当前hashtable的状态。每发生一次操作，状态就向前走一步。设置这个状态，主要是由于hashtable等容器类在迭代时，判断数据是否过时时使用的。尽管hashtable采用了原生的同步锁来保护数据安全。但是在出现迭代数据的时候，则无法保证边迭代，边正确操作。于是使用这个值来标记状态。一旦在迭代的过程中状态发生了改变，则会快速抛出一个异常，终止迭代行为。

### 初始容量大小和每次扩充容量大小的不同 ### 
Hashtable默认的初始大小为11，之后每次扩充，容量变为原来的2n+1。HashMap默认的初始化大小为16。之后每次扩充，容量变为原来的2倍。

创建时，如果给定了容量初始值，那么Hashtable会直接使用你给定的大小，而HashMap会将其扩充为2的幂次方大小。也就是说Hashtable会尽量使用素数、奇数。而HashMap则总是使用2的幂作为哈希表的大小。

之所以会有这样的不同，是因为Hashtable和HashMap设计时的侧重点不同。Hashtable的侧重点是哈希的结果更加均匀，使得哈希冲突减少。当哈希表的大小为素数时，简单的取模哈希的结果会更加均匀。而HashMap则更加关注hash的计算效率问题。在取模计算时，如果模数是2的幂，那么我们可以直接使用位运算来得到结果，效率要大大高于做除法。HashMap为了加快hash的速度，将哈希表的大小固定为了2的幂。当然这引入了哈希分布不均匀的问题，所以HashMap为解决这问题，又对hash算法做了一些改动。这从而导致了Hashtable和HashMap的计算hash值的方法不同 

### 计算hash值的方法不同 ###
为了得到元素的位置，首先需要根据元素的 KEY计算出一个hash值，然后再用这个hash值来计算得到最终的位置。

Hashtable直接使用对象的hashCode。hashCode是JDK根据对象的地址或者字符串或者数字算出来的int类型的数值。然后再使用除留余数发来获得最终的位置。 

Hashtable在计算元素的位置时需要进行一次除法运算，而除法运算是比较耗时的。 
HashMap为了提高计算效率，将哈希表的大小固定为了2的幂，这样在取模预算时，不需要做除法，只需要做位运算。位运算比除法的效率要高很多。

HashMap的效率虽然提高了，但是hash冲突却也增加了。因为它得出的hash值的低位相同的概率比较高，而计算位运算

为了解决这个问题，HashMap重新根据hashcode计算hash值后，又对hash值做了一些运算来打散数据。使得取得的位置更加分散，从而减少了hash冲突。当然了，为了高效，HashMap只做了一些简单的位处理。从而不至于把使用2 的幂次方带来的效率提升给抵消掉。

### 针对触发扩容的时机不同 ###
前面有说到，在两者在默认构造的时候，都有一个默认的负载因子值为0.75f。通过该负载因子计算得出阈值(threshold)，这个阈值就是作为扩容的触发点。
HashTable在触发扩容操作时，是当前哈希表中的Entry总数>=阈值(threshold)时。

//HashTable
//如果容器中的元素数量已经达到阀值，则进行扩容操作  
```
if (count >= threshold) {
	// Rehash the table if the threshold is exceeded
	rehash();
	
	tab = table;
	hash = hash(key);
	index = (hash & 0x7FFFFFFF) % tab.length;
}

protected void rehash() {
	.......
	int newCapacity = (oldCapacity << 1) + 1;//原容量的2倍+1
	.......
  	Entry<K,V>[] newMap = new Entry[newCapacity];
  	.......   
    for (int i = oldCapacity ; i-- > 0 ;) {
        for (Entry<K,V> old = oldMap[i] ; old != null ; ) {
            Entry<K,V> e = old;
            old = old.next;
            
            if (rehash) {
                e.hash = hash(e.key);
            }
            //& 0x7FFFFFFF目的为了去掉符号；% newCapacity减少hash冲突
            int index = (e.hash & 0x7FFFFFFF) % newCapacity;
            e.next = newMap[index];
            newMap[index] = e;
        }
    }
}
```

而HashMap触发扩容操作时，要同时判断阈值和哈希冲突，当两者同时满足时，才进行原基础的2倍扩容
```
//HashMap
//如果容器中的元素数量已经达到阀值且哈希桶不为空 
if ((size >= threshold) && (null != table[bucketIndex])) {
	// 每次扩充为原来的2n 
	resize(2 * table.length);
	hash = (null != key) ? hash(key) : 0;
	bucketIndex = indexFor(hash, table.length);
}
///获取桶索引
static int indexFor(int h, int length) {
	// assert Integer.bitCount(length) == 1 : "length must be a non-zero power of 2";
	return h & (length-1);
}
```