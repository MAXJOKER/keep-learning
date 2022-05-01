package pdaitech.javacollections;

import java.util.ArrayDeque;

/**
 * @author maxjoker
 * @date 2022-05-01 21:33
 *
 * ArrayDeque
 *
 */
public class JavaArrayDeque {
    public static void main(String[] args) {
        ArrayDeque<Integer> a = new ArrayDeque<Integer>(12); // 初始化后的大小为 16
        a.addFirst(1);
        a.addFirst(2);
        a.addLast(3);
        System.out.println(-1 & 15);

        System.out.println(12>>>1); // 结果是 6

        // ArrayDeque底层通过数组实现，为了满足可以同时在数组两端插入或删除元素的需求，该数组还必须是循环的，
        // 即循环数组(circular array)，也就是说数组的任何一点都可能被看作起点或者终点。
        // ArrayDeque是非线程安全的(not thread-safe)，当多个线程同时使用的时候，需要程序员手动同步；
        // 另外，该容器不允许放入null元素。

        // 属性
        // transient Object[] elements; // 底层由一个Object数组组成
        // transient int head; 头的位置
        // transient int tail; 尾的位置

        //     /**
        //     * The minimum capacity that we'll use for a newly created deque.
        //     * Must be a power of 2.  ArrayDeque的大小必须是2的次方数
        //     */
        //    private static final int MIN_INITIAL_CAPACITY = 8;

        // 计算大小
        //     private static int calculateSize(int numElements) {
        //        int initialCapacity = MIN_INITIAL_CAPACITY;
        //        // Find the best power of two to hold elements.
        //        // Tests "<=" because arrays aren't kept full.
        //        if (numElements >= initialCapacity) {
        //            initialCapacity = numElements;
        //            initialCapacity |= (initialCapacity >>>  1); // 无符号右移
        //            initialCapacity |= (initialCapacity >>>  2);
        //            initialCapacity |= (initialCapacity >>>  4);
        //            initialCapacity |= (initialCapacity >>>  8);
        //            initialCapacity |= (initialCapacity >>> 16);
        //            initialCapacity++;
        //
        //            if (initialCapacity < 0)   // Too many elements, must back off
        //                initialCapacity >>>= 1;// Good luck allocating 2 ^ 30 elements
        //        }
        //        return initialCapacity;
        //    }
        //
        // 因为 数组 elements 的大小必须是 2 的次方数，所以这里进行多次 逻辑与 以及 无符号右移 运算便是这个目的
        //
        // 补充知识点：
        // 无符号右移：无符号位移是指在进行位移时，符号位也跟着移动
        // 逻辑或 | 运算：
        //  进行运算的两个数据，按二进制位进行“或”运算
        //  规则: 只要有1，结果为1


        // add
        //     public void addFirst(E e) {
        //        if (e == null)
        //            throw new NullPointerException();
        //        elements[head = (head - 1) & (elements.length - 1)] = e;
        //        if (head == tail)
        //            doubleCapacity();
        //    }
        //
        //    public void addLast(E e) {
        //        if (e == null)
        //            throw new NullPointerException();
        //        elements[tail] = e;
        //        if ( (tail = (tail + 1) & (elements.length - 1)) == head)
        //            doubleCapacity();
        //    }
        //
        // 1、不允许添加 null 元素
        // 2、head = (head - 1) & (elements.length - 1) 的作用是防止数组越界
        //      因为 deque 是循环数组，加入head = 0, head - 1 就变成负数了，因此需要和数组长度做 与 运算
        // 补充知识点：
        // 逻辑与 & 运算：
        //  进行运算的两个数据，按二进制位进行“与”运算
        //  规则: 同为1，结果才为1，否则为0
        // 负数与运算，先将负数转换为有符号二进制，-1 就是 1000 0001
        // 取其反码 1111 1110（负数的反码：除符号位外，其余取反），再取其补码 1111 1111 （反码加1）
        // 正数 的 原码、反码、补码 一样
        // 1111 1111 & 0000 1110 = 0000 1110  转换为十进制 则是 15

        // 扩容 增加1倍容量
        //     /**
        //     * Doubles the capacity of this deque.  Call only when full, i.e.,
        //     * when head and tail have wrapped around to become equal.
        //     */
        //    private void doubleCapacity() {
        //        assert head == tail;
        //        int p = head;
        //        int n = elements.length;
        //        int r = n - p; // number of elements to the right of p
        //        int newCapacity = n << 1; // 左移，相当于乘2
        //        if (newCapacity < 0)
        //            throw new IllegalStateException("Sorry, deque too big");
        //        Object[] a = new Object[newCapacity];
        //        System.arraycopy(elements, p, a, 0, r);
        //        System.arraycopy(elements, 0, a, r, p);
        //        elements = a;
        //        head = 0;
        //        tail = n;
        //    }

        // ArrayDeque中还有其他方法，可以直接看源码，这里讲得主要是有几点要注意：
        // 1、ArrayDeque底层是循环数组，head 比一定比 tail 小，每次操作都会检查数组是否越界
        // 2、不能添加 null 元素，否则会抛出异常
        // 3、数组的大小一定是2的次方数
        // 4、扩容后，新容量 = 当前容量 * 2
    }
}
