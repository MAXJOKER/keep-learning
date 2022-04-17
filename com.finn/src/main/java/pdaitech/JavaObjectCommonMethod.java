package pdaitech;

/**
 * @author maxjoker
 * @date 2022-04-17 15:58
 *
 * Object通用方法
 *
 */
public class JavaObjectCommonMethod {
    public static void main(String[] args) {
        String x = "1";
        String y = "1";
        String z = "1";

        // 自反性
        boolean result = x.equals(x);
        System.out.println(result);

        // 对称性
        if (x.equals(y) == y.equals(x)) {
            System.out.println(true);
        }

        // 传递性
        if (x.equals(y) && y.equals(z)) {
            System.out.println(x.equals(z));
        }

        // 一致性
        if (x.equals(y) == x.equals(y)) {
            System.out.println(true);
        }

        // 与null比较
        if (!x.equals(null)) {
            System.out.println(false);
        }

        // equals 与 ==
        // 对于基本类型，== 判断两个值是否相等，基本类型没有equals方法
        // 对于引用类型，== 判断两个变量是否引用同一个对象，equals判断引用的对象是否等价
        Integer a = new Integer(1);
        Integer b = new Integer(1);
        System.out.println(a == b); // false
        System.out.println(a.equals(b)); // true

        // clone 对应文档详见 java.疑问case.java clone.md
        //使用 clone() 方法来拷贝一个对象即复杂又有风险，它会抛出异常，并且还需要类型转换。
        // Effective Java 书上讲到，最好不要去使用 clone()，可以使用拷贝构造函数或者拷贝工厂来拷贝一个对象。
    }
}
