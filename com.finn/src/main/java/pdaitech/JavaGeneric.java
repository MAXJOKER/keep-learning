package pdaitech;

import java.util.ArrayList;
import java.util.List;

/**
 * @author maxjoker
 * @date 2022-04-22 00:41
 *
 * java泛型
 *
 * https://pdai.tech/md/java/basic/java-basic-x-generic.html
 *
 */
public class JavaGeneric {
    public static void main(String[] args) {
        // 泛型的本质是为了参数化类型（在不创建新的类型的情况下，通过泛型指定的不同类型来控制形参具体限制的类型）。
        // 也就是说在泛型使用过程中，操作的数据类型被指定为一个参数，这种参数类型可以用在类、接口和方法中，
        // 分别被称为泛型类、泛型接口、泛型方法。

        // 引入泛型的意义在于：适用于多种数据类型执行相同的代码（代码复用）
        // 看下面几个case：
//        add(1, 2);
//        add(1.5, 6.5);
        // 如果没有泛型，要实现不同类型的加法，每种类型都要重载add方法
        // 而通过泛型，可以复用一个方法
        GenericAdd(2, 3);
        GenericAdd(3.9, 6.2);

        // 接着往下看
        List list = new ArrayList();
        list.add("aaa");
        list.add(1);
        list.add(new Object());
        System.out.println(list);

        // 上面的代码，list中的元素都是Object类型，get的时候需要进行类型的强制转换
        // 如果转换的类型不正确，会很容易出现 ClassCastException 类型转换异常
        // 下面的代码运行后便会抛出转换异常，因为取出来的是 Integer类型
//        String a = (String) list.get(1);

        // 因此，引入泛型，可以提供类型的约束，提供编译前的检查
        // list1中只能放String类型的元素
        List<String> list1 = new ArrayList<>();
        // list1.add(1); 直接编译不通过
        list1.add("hhh"); // 可以编译通过
        System.out.println(list1);

        // 泛型的使用
        //
        // 1. 泛型类
        // 2. 泛型接口
        // 3. 泛型方法

        // 泛型类
    }

/*    public static int add(int a, int b) {
        System.out.println("Integer add: a + b = " + (a + b));
        return a + b;
    }

    public static double add(double a, double b) {
        System.out.println("Double add: a + b = " + (a + b));
        return a + b;
    }*/

    public static <T extends Number> Number GenericAdd(T a, T b) {
        System.out.println("Generic add: a + b = " + (a.doubleValue() + b.doubleValue()));
        return a.doubleValue() + b.doubleValue();
    }
}
