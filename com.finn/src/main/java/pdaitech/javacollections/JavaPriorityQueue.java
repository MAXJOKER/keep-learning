package pdaitech.javacollections;

import java.util.PriorityQueue;

/**
 * @author maxjoker
 * @date 2022-05-01 22:59
 *
 * PriorityQueue
 *
 * 参考：
 * https://pdai.tech/md/java/collection/java-collection-PriorityQueue.html
 * https://www.cnblogs.com/longhuihu/p/11128406.html
 * https://www.cnblogs.com/tstd/p/5125949.html
 *
 */
public class JavaPriorityQueue {
    public static void main(String[] args) {
        // 优先队列的作用是能保证每次取出的元素都是队列中权值最小的(Java的优先队列每次取最小元素，C++的优先队列每次取最大元素)。
        // 这里牵涉到了大小关系，元素大小的评判可以通过元素本身的自然顺序(natural ordering)，
        // 也可以通过构造时传入的比较器(Comparator，类似于C++的仿函数)。

        // Java中PriorityQueue实现了Queue接口，不允许放入null元素；其通过堆实现，具体说是通过完全二叉树(complete binary tree)
        // 实现的小顶堆(任意一个非叶子节点的权值，都不大于其左右子节点的权值)，也就意味着可以通过数组来作为PriorityQueue的底层实现。

        // 父子节点的编号之间有如下关系:
        // leftNo = parentNo * 2 + 1
        // rightNo = parentNo * 2 + 2
        // parentNo = (nodeNo - 1) / 2

        // 底层结构
        // /**
        //  * Priority queue represented as a balanced binary heap: the two children of queue[n] are queue[2*n+1]
        //  * and queue[2*(n+1)]. The priority queue is ordered by comparator, or by the elements' natural ordering,
        //  * if comparator is null: For each node n in the heap and each descendant d of n, n <= d.
        //  * The element with the lowest value is in queue[0], assuming the queue is nonempty.
        //  */
        // transient Object[] queue;

        //     /**
        //     * The number of elements in the priority queue.
        //     */
        //    private int size = 0;

        //     /**
        //     * The comparator, or null if priority queue uses elements'
        //     * natural ordering.
        //     * 比较器
        //     */
        //    private final Comparator<? super E> comparator;

        // 扩容
        //    /**
        //     * Increases the capacity of the array.
        //     *
        //     * @param minCapacity the desired minimum capacity
        //     */
        //    private void grow(int minCapacity) {
        //        int oldCapacity = queue.length;
        //        // Double size if small; else grow by 50%
        //        int newCapacity = oldCapacity + ((oldCapacity < 64) ?
        //                                         (oldCapacity + 2) :
        //                                         (oldCapacity >> 1));
        //        // overflow-conscious code
        //        if (newCapacity - MAX_ARRAY_SIZE > 0)
        //            newCapacity = hugeCapacity(minCapacity);
        //        queue = Arrays.copyOf(queue, newCapacity);
        //    }
        //
        //     private static int hugeCapacity(int minCapacity) {
        //        if (minCapacity < 0) // overflow  溢出了
        //            throw new OutOfMemoryError();
        //        return (minCapacity > MAX_ARRAY_SIZE) ?
        //            Integer.MAX_VALUE :
        //            MAX_ARRAY_SIZE;
        //    }

        // 构建小顶堆
        //    /**
        //     * Establishes the heap invariant (described above) in the entire tree,
        //     * assuming nothing about the order of the elements prior to the call.
        //     */
        //    @SuppressWarnings("unchecked")
        //    private void heapify() {
        //        // size >>> 1 - 1  找寻最后一个非叶子节点
        //        for (int i = (size >>> 1) - 1; i >= 0; i--)
        //            siftDown(i, (E) queue[i]);
        //    }
        //
        //     /**
        //     * Inserts item x at position k, maintaining heap invariant by
        //     * demoting x down the tree repeatedly until it is less than or
        //     * equal to its children or is a leaf.
        //     *
        //     * @param k the position to fill
        //     * @param x the item to insert
        //     */
        //    private void siftDown(int k, E x) {
        //        if (comparator != null)
        //            siftDownUsingComparator(k, x);
        //        else
        //            siftDownComparable(k, x);
        //    }
        //
        //
        //    @SuppressWarnings("unchecked")
        //    private void siftDownComparable(int k, E x) {
        //        // 比较器comparator为空，需要插入的元素实现Comparable接口，用于比较大小
        //        Comparable<? super E> key = (Comparable<? super E>)x;
        //        // 通过size/2找到一个没有叶子节点的元素
        //        int half = size >>> 1;        // loop while a non-leaf
        //        // 比较位置k和half，如果k小于half，则k位置的元素就不是叶子节点
        //        while (k < half) {
        //            // 找到根元素的左孩子的位置[2n+1]
        //            int child = (k << 1) + 1; // assume left child is least
        //            Object c = queue[child];
        //            // 找到根元素的右孩子的位置[2(n+1)]
        //            int right = child + 1;
        //            // 如果左孩子大于右孩子，则将c复制为右孩子的值，这里也就是找出左右孩子哪个最小
        //            if (right < size &&
        //                ((Comparable<? super E>) c).compareTo((E) queue[right]) > 0)
        //                c = queue[child = right];
        //            // 如果队尾元素比根元素孩子都要小，则不需"下移"，此时k就是插入位置，结束
        //            if (key.compareTo((E) c) <= 0)
        //                break;
        //            // 队尾元素比根元素孩子都大，则需要"下移"
        //            // 交换跟元素和孩子c的位置
        //            queue[k] = c;
        //            // 将根元素位置k指向最小孩子的位置，进入下层循环
        //            k = child;
        //        }
        //        // 找到队尾元素x的合适位置k之后进行赋值
        //        queue[k] = key;
        //    }
        //
        //    @SuppressWarnings("unchecked")
        //    private void siftDownUsingComparator(int k, E x) {
        //        int half = size >>> 1;
        //        while (k < half) {
        //            int child = (k << 1) + 1;
        //            Object c = queue[child];
        //            int right = child + 1;
        //            if (right < size &&
        //                comparator.compare((E) c, (E) queue[right]) > 0)
        //                c = queue[child = right];
        //            if (comparator.compare(x, (E) c) <= 0)
        //                break;
        //            queue[k] = c;
        //            k = child;
        //        }
        //        queue[k] = x;
        //    }

        // 添加元素
        //    /**
        //     * Inserts the specified element into this priority queue.
        //     *
        //     * @return {@code true} (as specified by {@link Collection#add})
        //     * @throws ClassCastException if the specified element cannot be
        //     *         compared with elements currently in this priority queue
        //     *         according to the priority queue's ordering
        //     * @throws NullPointerException if the specified element is null
        //     */
        //    public boolean add(E e) {
        //        return offer(e);
        //    }
        //
        //    /**
        //     * Inserts the specified element into this priority queue.
        //     *
        //     * @return {@code true} (as specified by {@link Queue#offer})
        //     * @throws ClassCastException if the specified element cannot be
        //     *         compared with elements currently in this priority queue
        //     *         according to the priority queue's ordering
        //     * @throws NullPointerException if the specified element is null
        //     */
        //    public boolean offer(E e) {
        //        if (e == null) //不允许放入null元素，会抛出异常
        //            throw new NullPointerException();
        //        modCount++;
        //        int i = size; // 元素先放在队尾
        //        if (i >= queue.length)
        //            grow(i + 1); //自动扩容
        //        size = i + 1;
        //        if (i == 0) //队列原来为空，这是插入的第一个元素
        //            queue[0] = e;
        //        else
        //            siftUp(i, e); //调整
        //        return true;
        //    }
        //
        //    /**
        //     * Inserts item x at position k, maintaining heap invariant by
        //     * promoting x up the tree until it is greater than or equal to
        //     * its parent, or is the root.
        //     *
        //     * To simplify and speed up coercions and comparisons. the
        //     * Comparable and Comparator versions are separated into different
        //     * methods that are otherwise identical. (Similarly for siftDown.)
        //     *
        //     * @param k the position to fill
        //     * @param x the item to insert
        //     */
        //    private void siftUp(int k, E x) {
        //        if (comparator != null)
        //            siftUpUsingComparator(k, x);
        //        else
        //            siftUpComparable(k, x);
        //    }
        //
        //    @SuppressWarnings("unchecked")
        //    private void siftUpComparable(int k, E x) {
        //        Comparable<? super E> key = (Comparable<? super E>) x;
        //        while (k > 0) {
        //            int parent = (k - 1) >>> 1; //parentNo = (nodeNo-1)/2
        //            Object e = queue[parent];
        //            // 如果新增的元素k比其父亲e大，则不需要"上移"，跳出循环结束
        //            if (key.compareTo((E) e) >= 0)
        //                break;
        //            // x比父亲小，则需要进行"上移"
        //            queue[k] = e;
        //            k = parent;
        //        }
        //        // 找到新增元素x的合适位置k之后进行赋值
        //        queue[k] = key;
        //    }
        //
        //    @SuppressWarnings("unchecked")
        //    private void siftUpUsingComparator(int k, E x) {
        //        while (k > 0) {
        //            int parent = (k - 1) >>> 1;
        //            Object e = queue[parent];
        //            if (comparator.compare(x, (E) e) >= 0)
        //                break;
        //            queue[k] = e;
        //            k = parent;
        //        }
        //        queue[k] = x;
        //    }

        // 删除元素
        //    /**
        //     * Removes a single instance of the specified element from this queue,
        //     * if it is present.  More formally, removes an element {@code e} such
        //     * that {@code o.equals(e)}, if this queue contains one or more such
        //     * elements.  Returns {@code true} if and only if this queue contained
        //     * the specified element (or equivalently, if this queue changed as a
        //     * result of the call).
        //     *
        //     * @param o element to be removed from this queue, if present
        //     * @return {@code true} if this queue changed as a result of the call
        //     */
        //    public boolean remove(Object o) {
        //        int i = indexOf(o);
        //        if (i == -1)
        //            return false;
        //        else {
        //            removeAt(i);
        //            return true;
        //        }
        //    }
        //
        //     /**
        //     * Removes the ith element from queue.
        //     *
        //     * Normally this method leaves the elements at up to i-1,
        //     * inclusive, untouched.  Under these circumstances, it returns
        //     * null.  Occasionally, in order to maintain the heap invariant,
        //     * it must swap a later element of the list with one earlier than
        //     * i.  Under these circumstances, this method returns the element
        //     * that was previously at the end of the list and is now at some
        //     * position before i. This fact is used by iterator.remove so as to
        //     * avoid missing traversing elements.
        //     */
        //    @SuppressWarnings("unchecked")
        //    private E removeAt(int i) {
        //        // assert i >= 0 && i < size;
        //        modCount++;
        //        int s = --size;
        //        if (s == i) // removed last element
        //            queue[i] = null;
        //        else {
        //            E moved = (E) queue[s];
        //            queue[s] = null;
        //            // 插入的过程是先siftDown，如果siftDown的最终位置就是i，那么说明move比i的子树元素都小，
        //            // 此时再尝试一下siftUp，看看是否比 i 元素的父节点大；否则siftUp是不需要执行的；
        //            // 当siftUp执行的结果是末尾元素，被移动到了i之前，那么返回这个元素，其他情况都返回null
        //            siftDown(i, moved);
        //            if (queue[i] == moved) {
        //                siftUp(i, moved);
        //                if (queue[i] != moved)
        //                    return moved;
        //            }
        //        }
        //        return null;
        //    }
        //
        // 删除第一个元素，并调整
        //    @SuppressWarnings("unchecked")
        //    public E poll() {
        //        if (size == 0)
        //            return null;
        //        int s = --size;
        //        modCount++;
        //        E result = (E) queue[0];
        //        E x = (E) queue[s];
        //        queue[s] = null;
        //        if (s != 0)
        //            siftDown(0, x);
        //        return result;
        //    }

        // 取队头元素
        //    @SuppressWarnings("unchecked")
        //    public E peek() {
        //        return (size == 0) ? null : (E) queue[0];
        //    }
    }
}
