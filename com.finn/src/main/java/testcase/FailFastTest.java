package testcase;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class FailFastTest {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");

        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String temp = iterator.next();
            System.out.println(temp);
            // 如果是从外层直接删除2，这里是不会报错的，非常几把
            // 因为删除2后，有个 --size 的操作，此时size = 2，而迭代器iterator中的cursor此时也等于2
            // cursor含义：index of next element to return ，下一个要返回的变量的坐标
            // 遍历到2的时候，cursor =  2，删除2的时候，size = 2
            // hasNext()的实现是：cursor != size，而此时 cursor = size，直接就退出循环了，根本就遍历不到3
            // 也就是不会执行next()方法，不会执行checkForComodification()方法，最终不会抛出 ConcurrentModificationException
            // 太狗了
            if (temp.equals("2")) {
//                list.set(1, "4");
                list.remove(temp);
            }
        }
        System.out.println(list);

        list = list.stream().filter(l -> !l.equals("1")).collect(Collectors.toList());
        System.out.println(list);
    }
}
