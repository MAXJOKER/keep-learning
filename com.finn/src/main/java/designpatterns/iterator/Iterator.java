package designpatterns.iterator;

/**
 * @author maxjoker
 * @date 2022-01-27 17:47
 * 也可以使用java提供的迭代器 Iterator
 */
public interface Iterator {
    boolean hasNext();
    Object next();
}
