package learnjava;

/**
 * @author maxjoker
 * @date 2022-04-14 19:50
 *
 * 基本数据类型
 *                      缓冲池
 * boolean  1       true / false
 * byte     8
 * char     16      \u0000 ~ \u007F
 * short    16      -128 ~ 127
 * int      32      -128 ~ 127
 * float    32
 * double   64
 * long     64
 *
 */
public class DataType {
    public static void main(String[] args) {

        /**
         * Integer
         */
        System.out.println("Integer start");
        // 自动装箱
        Integer x = 2;
        // 自动拆箱
        int y = x;

        // a、b 均为同一个对象引用，结果为true
        int a = 321;
        int b = 321;
        System.out.println(a == b);

        Integer c = new Integer(123);
        Integer e = new Integer(123);
        Integer d = Integer.valueOf(123);
        Integer f = Integer.valueOf(123);
        Integer h = Integer.valueOf(256);
        Integer i = Integer.valueOf(256);
        // false, c 和 e 是不同的对象，new Integer() 每次都会新建一个对象
        System.out.println(c == e);
        // false, Integer.valueOf() 会使用缓冲池中的对象，多次调用会取同一对象的引用,int 缓冲池 -128 ~ 127
        System.out.println(c == d);
        // true
        System.out.println(d == f);
        // false, 超出缓冲池的范围，新建对象
        System.out.println(h == i);
        System.out.println("Integer end");
        System.out.println("");


        /**
         * String
         */
        System.out.println("String start");
        String s1 = new String("111");
        String s2 = new String("111");
        String s3 = s1.intern();
        // false
        System.out.println(s1 == s2);
        // true, intern()的做法是先到string pool中看看有没有s1引用的对象，有就直接返回；没有就加到string pool 里面，然后返回，看方法注释
        System.out.println(s1.intern() == s3);



    }
}
