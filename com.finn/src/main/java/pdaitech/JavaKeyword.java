package pdaitech;

/**
 * @author maxjoker
 * @date 2022-04-17 16:23
 *
 * 关键字
 *
 */
public class JavaKeyword {
    private int k = 1; // 实例变量
    private static int g = 2; // 静态变量

    // 静态语句块
    static {
        for (int i = 0; i < 10; i++) {
            System.out.println("this is a static block.");
        }
    }

    public static void main(String[] args) {
        // final
        // 1. 数据
        // 声明数据为常量，可以是编译时常量，也可以是在运行时被初始化后不能被改变的常量。
        // 对于基本类型，final 使数值不变；
        // 对于引用类型，final 使引用不变，也就不能引用其它对象，但是被引用的对象本身是可以修改的。
        final int a = 1;
        // a = 2; 报错 Cannot assign a value to final variable 'a'
        final Person p = new Person();
        // p = new Person(); 报错 Cannot assign a value to final variable 'p'
        p.age = 18;

        // 2. 方法
        // 声明为final的方法不能被子类重写
        // private 方法隐式地被指定为 final，如果在子类中定义的方法和基类中的一个 private 方法签名相同，
        // 此时子类的方法不是重写基类方法，而是在子类中定义了一个新的方法。

        // 3. 类
        // 声明为 final 的类不能被继承


        // static
        // 1. 静态变量
        // 静态变量: 又称为类变量，也就是说这个变量属于类的，类所有的实例都共享静态变量，可以直接通过类名来访问它；
        // 静态变量在内存中只存在一份。
        // 实例变量：每创建一个实例就会产生一个实例变量，它与该实例同生共死。

        // 2. 静态方法
        // 静态方法在类加载的时候就存在了，它不依赖于任何实例。所以静态方法必须有实现，也就是说它不能是抽象方法(abstract)。
        // main方法就是静态方法
        // 静态方法中，只能访问所属类的静态字段和静态方法，方法中不能有 this 和 super 关键字。
        // this.k = a; 报错 'pdaitech.JavaKeyword.this' cannot be referenced from a static context

        // 3. 静态语句块
        // 静态语句块在类初始化时运行一次。

        // 4. 静态内部类
        // 非静态内部类依赖于外部类的实例，而静态内部类不需要。
        // 静态内部类不能访问外部类的非静态的变量和方法。
        // InnerClass innerClass = new InnerClass(); 'pdaitech.JavaKeyword.this' cannot be referenced from a static context
        JavaKeyword keyword = new JavaKeyword();
        InnerClass innerClass = keyword.new InnerClass(); // 依赖外部类
        StaticInnerClass staticInnerClass = new StaticInnerClass();

        // 5. 静态导包
        // 在使用静态变量和方法时不用再指明 ClassName，从而简化代码，但可读性大大降低。
        // import static com.finn.xxx.*

        // 6. 初始化顺序
        // 静态变量和静态语句块优先于实例变量和普通语句块，静态变量和静态语句块的初始化顺序取决于它们在代码中的顺序。
        // 存在继承的情况下，初始化顺序为:
        //      父类(静态变量、静态语句块)
        //      子类(静态变量、静态语句块)
        //      父类(实例变量、普通语句块)
        //      父类(构造函数)
        //      子类(实例变量、普通语句块)
        //      子类(构造函数)

        // transient 关键字
        // 文章：https://www.cnblogs.com/eternityz/p/15329950.html
        // https://blog.csdn.net/u012723673/article/details/80699029
        // https://blog.csdn.net/u010188178/article/details/83581506
    }

    class InnerClass {

    }

    static class StaticInnerClass {
    }
}
