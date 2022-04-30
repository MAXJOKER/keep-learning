package pdaitech.javacollections;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * @author maxjoker
 * @date 2022-04-30 22:05
 *
 * LinkedList
 *
 */
public class JavaLinkedList {
    public static int a = 1;
    public static void main(String[] args) {
        // LinkedList同时实现了List接口和Deque接口，也就是说它既可以看作一个顺序容器，又可以看作一个队列(Queue)，
        // 同时又可以看作一个栈(Stack)
        // 关于栈或队列，现在的首选是ArrayDeque，它有着比LinkedList(当作栈或队列使用时)有着更好的性能。
        // 非线程安全
        // 如果需要多个线程并发访问，可以先采用Collections.synchronizedList()方法对其进行包装。
        //
        // 底层数据结构
        // 双向链表，私有内部类
        //     private static class Node<E> {
        //        E item;
        //        Node<E> next;
        //        Node<E> prev;
        //
        //        Node(Node<E> prev, E element, Node<E> next) {
        //            this.item = element;
        //            this.next = next;
        //            this.prev = prev;
        //        }
        //    }
        //
        //
        // 属性
        // transient int size = 0;
        // transient Node<E> first;
        // transient Node<E> last;

        // add，get，set 这些方法，直接点源码看就好了
        // 以下是觉得比较有意思的方法

        // 根据下标查找节点
        // 可以看到是 比较 index 与 size >> 1  (即 size / 2) 决定往哪个方向搜索
        // 时间复杂度为 O(n)
        //    /**
        //     * Returns the (non-null) Node at the specified element index.
        //     */
        //    Node<E> node(int index) {
        //        // assert isElementIndex(index);
        //
        //        if (index < (size >> 1)) {
        //            Node<E> x = first;
        //            for (int i = 0; i < index; i++)
        //                x = x.next;
        //            return x;
        //        } else {
        //            Node<E> x = last;
        //            for (int i = size - 1; i > index; i--)
        //                x = x.prev;
        //            return x;
        //        }
        //    }

        // Queue 操作
        // peek() 返回链表头元素
        //     public E peek() {
        //        final Node<E> f = first;
        //        return (f == null) ? null : f.item;
        //    }
        //
        // element() 也是 返回链表头元素
        //     public E element() {
        //        return getFirst();
        //    }
        //
        // poll() 删除并返回链表头元素
        //     public E poll() {
        //        final Node<E> f = first;
        //        return (f == null) ? null : unlinkFirst(f);
        //    }
        //
        // offer() 添加元素
        //    public boolean offer(E e) {
        //        return add(e);
        //    }

        // Stack 操作
        //
        // push() 将元素加到链表头
        //     public void push(E e) {
        //        addFirst(e);
        //    }
        //
        // pop() 删除链表头且返回其值
        //     public E pop() {
        //        return removeFirst();
        //    }

        // 内部迭代器
        // 仔细看迭代器的描述，也是fail-fast机制，而且对元素的修改添加如果不是通过迭代器自身操作，会抛出异常
        //
        // The list-iterator is fail-fast: if the list is structurally modified at any time after the Iterator is created,
        // in any way except through the list-iterator's own remove or add methods, the list-iterator will throw a
        // ConcurrentModificationException. Thus, in the face of concurrent modification, the iterator fails quickly
        // and cleanly, rather than risking arbitrary, non-deterministic behavior at an undetermined time in the future.
        //
        // 如何得知不是通过迭代器修改或添加的？
        // 通过对比modCount 和 expectedModCount
        // 如果在迭代器中，直接通过list.add(), list.remove()操作，modCount++，但expectedModCount不会增加
        // 相反，如果是在迭代器中add(), remove(), 会分别调用外层 link() , unlink() 方法，modCount++,
        // 调用完后，自身方法中会 expectedModCount++
        //         final void checkForComodification() {
        //            if (modCount != expectedModCount)
        //                throw new ConcurrentModificationException();
        //        }
        //
        LinkedList list = new LinkedList();
        list.add("a");
        list.add("b");
        Iterator iterator = list.listIterator(1);
        while (iterator.hasNext()) {
            iterator.next();
            iterator.remove();
        }
        list.element();
    }
}
