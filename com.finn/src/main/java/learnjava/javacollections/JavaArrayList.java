package learnjava.javacollections;

import java.util.ArrayList;
import java.util.List;

/**
 * @author maxjoker
 * @date 2022-04-29 16:43
 *
 * ArrayList 学习
 *
 */
public class JavaArrayList {
    public static void main(String[] args) {
        // ArrayList 实现了 List 接口，是顺序容器，即元素存放的数据与放进去的顺序相同，允许放入null元素，底层通过数组实现
        //
        // size(), get(), set(), isEmpty() 方法均能在常数时间内完成, add() 方法的时间开销与插入位置有关
        // addAll() 方法的时间开销与添加元素的个数成正比

        // 源码
        //
        // 底层数据
        //   /**
        //     * The array buffer into which the elements of the ArrayList are stored.
        //     * The capacity of the ArrayList is the length of this array buffer. Any
        //     * empty ArrayList with elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA
        //     * will be expanded to DEFAULT_CAPACITY when the first element is added.
        //     */
        //    transient Object[] elementData; // non-private to simplify nested class access
        //
        //    transient 关键字:
        //    1. 一旦变量被transient修饰，变量将不再是对象持久化的一部分，该变量内容在序列化后无法被访问
        //    2. transient关键字只能修饰变量，而不能修饰方法和类。本地变量是不能被transient关键字修饰的。
        //       变量如果是用户自定义类变量，则该类需要实现Serializable接口。
        //    3. 一个静态变量不管是否被transient修饰，均不能被序列化(如果反序列化后类中static变量还有值，则值为当前JVM中对应static变量的值)。
        //       序列化保存的是对象状态，静态变量保存的是类状态，因此序列化并不保存静态变量。

        //
        //    /**
        //     * The size of the ArrayList (the number of elements it contains).
        //     *
        //     * @serial
        //     */
        //    private int size;

        // 自动扩容
        // 每当向数组中添加元素时，都要去检查添加后元素的个数是否会超出当前数组的长度，如果超出，数组将会进行扩容，以满足添加数据的需求。
        // 数组扩容通过一个公开的方法ensureCapacity(int minCapacity)来实现
        // 在实际添加大量元素前，也可以使用ensureCapacity来手动增加ArrayList实例的容量，以减少递增式再分配的数量
        // 数组进行扩容时，会将老数组中的元素重新拷贝一份到新的数组中，每次数组容量的增长大约是其原容量的1.5倍。
        // 这种操作的代价是很高的，因此在实际使用时，我们应该尽量避免数组容量的扩张。
        // 当我们可预知要保存的元素的多少时，要在构造ArrayList实例时，就指定其容量，以避免数组扩容的发生。
        // 或者根据实际需求，通过调用ensureCapacity方法来手动增加ArrayList实例的容量。
        //   /**
        //     * Increases the capacity of this <tt>ArrayList</tt> instance, if
        //     * necessary, to ensure that it can hold at least the number of elements
        //     * specified by the minimum capacity argument.
        //     *
        //     * @param   minCapacity   the desired minimum capacity
        //     */
        //    public void ensureCapacity(int minCapacity) {
        //        int minExpand = (elementData != DEFAULTCAPACITY_EMPTY_ELEMENTDATA)
        //            // any size if not default element table
        //            ? 0
        //            // larger than default for default empty table. It's already
        //            // supposed to be at default size.
        //            : DEFAULT_CAPACITY;
        //
        //        if (minCapacity > minExpand) {
        //            ensureExplicitCapacity(minCapacity);
        //        }
        //    }
        //
        //    private void ensureExplicitCapacity(int minCapacity) {
        //        modCount++;
        //
        //        // overflow-conscious code
        //        if (minCapacity - elementData.length > 0)
        //            grow(minCapacity);
        //    }
        //
        //
        //   /**
        //     * Increases the capacity to ensure that it can hold at least the
        //     * number of elements specified by the minimum capacity argument.
        //     *
        //     * @param minCapacity the desired minimum capacity
        //     */
        //    private void grow(int minCapacity) {
        //        // overflow-conscious code
        //        int oldCapacity = elementData.length;
        //        int newCapacity = oldCapacity + (oldCapacity >> 1);
        //        if (newCapacity - minCapacity < 0)
        //            newCapacity = minCapacity;
        //        if (newCapacity - MAX_ARRAY_SIZE > 0)
        //            newCapacity = hugeCapacity(minCapacity);
        //        // minCapacity is usually close to size, so this is a win:
        //        elementData = Arrays.copyOf(elementData, newCapacity);
        //    }
        List<String> list = new ArrayList<>();
        list.add("test");

        // add(), addAll()
        // 这两个方法都是向容器中添加新元素，这可能会导致capacity不足，因此在添加元素之前，都需要进行剩余空间检查，如果需要则自动扩容。
        // 扩容操作最终是通过grow()方法完成的。
        //    /**
        //     * Appends the specified element to the end of this list.
        //     *
        //     * @param e element to be appended to this list
        //     * @return <tt>true</tt> (as specified by {@link Collection#add})
        //     */
        //    public boolean add(E e) {
        //        ensureCapacityInternal(size + 1);  // Increments modCount!!
        //        elementData[size++] = e;
        //        return true;
        //    }
        //
        //
        //    /**
        //     * Inserts the specified element at the specified position in this
        //     * list. Shifts the element currently at that position (if any) and
        //     * any subsequent elements to the right (adds one to their indices).
        //     *
        //     * @param index index at which the specified element is to be inserted
        //     * @param element element to be inserted
        //     * @throws IndexOutOfBoundsException {@inheritDoc}
        //     */
        //    public void add(int index, E element) {
        //        // 检查index是否越界
        //        rangeCheckForAdd(index);
        //
        //        ensureCapacityInternal(size + 1);  // Increments modCount!!
        //        // 注意这里用到了 arraycopy() 方法
        //        System.arraycopy(elementData, index, elementData, index + 1,
        //                         size - index);
        //        elementData[index] = element;
        //        size++;
        //    }
        //
        // add() 与 addAll() 类似

        // set()
        // 直接对指定位置赋值
        //   /**
        //     * Replaces the element at the specified position in this list with
        //     * the specified element.
        //     *
        //     * @param index index of the element to replace
        //     * @param element element to be stored at the specified position
        //     * @return the element previously at the specified position
        //     * @throws IndexOutOfBoundsException {@inheritDoc}
        //     */
        //    public E set(int index, E element) {
        //        rangeCheck(index);
        //
        //        E oldValue = elementData(index);
        //        elementData[index] = element;
        //        return oldValue;
        //    }


        // get()
        // 获取指定位置的值
        //   /**
        //     * Returns the element at the specified position in this list.
        //     *
        //     * @param  index index of the element to return
        //     * @return the element at the specified position in this list
        //     * @throws IndexOutOfBoundsException {@inheritDoc}
        //     */
        //    public E get(int index) {
        //        rangeCheck(index);
        //
        //        return elementData(index);
        //    }

        // remove()
        //   /**
        //     * Removes the element at the specified position in this list.
        //     * Shifts any subsequent elements to the left (subtracts one from their
        //     * indices).
        //     *
        //     * @param index the index of the element to be removed
        //     * @return the element that was removed from the list
        //     * @throws IndexOutOfBoundsException {@inheritDoc}
        //     */
        //    public E remove(int index) {
        //        rangeCheck(index);
        //
        //        modCount++;
        //        E oldValue = elementData(index);
        //
        //        int numMoved = size - index - 1;
        //        if (numMoved > 0)
        //            System.arraycopy(elementData, index+1, elementData, index,
        //                             numMoved);
        //        elementData[--size] = null; // clear to let GC do its work 为了让GC起作用，必须显式的为最后一个位置赋null值
        //
        //        return oldValue;
        //    }
        //
        //
        //
        //   /**
        //     * Removes the first occurrence of the specified element from this list,
        //     * if it is present.  If the list does not contain the element, it is
        //     * unchanged.  More formally, removes the element with the lowest index
        //     * <tt>i</tt> such that
        //     * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equals(get(i)))</tt>
        //     * (if such an element exists).  Returns <tt>true</tt> if this list
        //     * contained the specified element (or equivalently, if this list
        //     * changed as a result of the call).
        //     *
        //     * @param o element to be removed from this list, if present
        //     * @return <tt>true</tt> if this list contained the specified element
        //     */
        //    public boolean remove(Object o) {
        //        if (o == null) {
        //            for (int index = 0; index < size; index++)
        //                if (elementData[index] == null) {
        //                    fastRemove(index);
        //                    return true;
        //                }
        //        } else {
        //            for (int index = 0; index < size; index++)
        //                if (o.equals(elementData[index])) {
        //                    fastRemove(index);
        //                    return true;
        //                }
        //        }
        //        return false;
        //    }
        //
        //
        //    /**
        //     * Private remove method that skips bounds checking and does not
        //     * return the value removed.
        //     */
        //    private void fastRemove(int index) {
        //        modCount++;
        //        int numMoved = size - index - 1;
        //        if (numMoved > 0)
        //            System.arraycopy(elementData, index+1, elementData, index, numMoved);
        //        elementData[--size] = null; // clear to let GC do its work
        //    }
        // 关于Java GC这里需要特别说明一下，有了垃圾收集器并不意味着一定不会有内存泄漏。对象能否被GC的依据是是否还有引用指向它，
        // 上面代码中如果不手动赋null值，除非对应的位置被其他元素覆盖，否则原来的对象就一直不会被回收

        // trimToSize()
        // 将底层数组的容量调整为当前列表保存的实际元素的大小
        //    /**
        //     * Trims the capacity of this <tt>ArrayList</tt> instance to be the
        //     * list's current size.  An application can use this operation to minimize
        //     * the storage of an <tt>ArrayList</tt> instance.
        //     */
        //    public void trimToSize() {
        //        modCount++;
        //        if (size < elementData.length) {
        //            elementData = (size == 0)
        //              ? EMPTY_ELEMENTDATA
        //              : Arrays.copyOf(elementData, size);
        //        }
        //    }

        // indexOf()
        //    /**
        //     * Returns the index of the first occurrence of the specified element
        //     * in this list, or -1 if this list does not contain the element.
        //     * More formally, returns the lowest index <tt>i</tt> such that
        //     * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equals(get(i)))</tt>,
        //     * or -1 if there is no such index.
        //     */
        //    public int indexOf(Object o) {
        //        if (o == null) {
        //            for (int i = 0; i < size; i++)
        //                if (elementData[i]==null)
        //                    return i;
        //        } else {
        //            for (int i = 0; i < size; i++)
        //                if (o.equals(elementData[i]))
        //                    return i;
        //        }
        //        return -1;
        //    }

        // lastIndexOf()
        //    /**
        //     * Returns the index of the last occurrence of the specified element
        //     * in this list, or -1 if this list does not contain the element.
        //     * More formally, returns the highest index <tt>i</tt> such that
        //     * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equals(get(i)))</tt>,
        //     * or -1 if there is no such index.
        //     */
        //    public int lastIndexOf(Object o) {
        //        if (o == null) {
        //            for (int i = size-1; i >= 0; i--)
        //                if (elementData[i]==null)
        //                    return i;
        //        } else {
        //            for (int i = size-1; i >= 0; i--)
        //                if (o.equals(elementData[i]))
        //                    return i;
        //        }
        //        return -1;
        //    }

        // Fail-Fast机制：
        // ArrayList也采用了快速失败的机制，通过记录modCount参数来实现。在面对并发的修改时，迭代器很快就会完全失败，
        // 而不是冒着在将来某个不确定时间发生任意不确定行为的风险
    }
}