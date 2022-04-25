package pdaitech;

import java.util.ArrayList;
import java.util.List;

/**
 * @author maxjoker
 * @date 2022-04-26 00:12
 *
 * 学习链接：https://pdai.tech/md/java/basic/java-basic-x-annotation.html
 *
 * java注解
 *
 */
public class JavaAnnotation {
    public static void main(String[] args) {
        // 注解是JDK1.5版本开始引入的一个特性，
        // 用于对代码进行说明，可以对包、类、接口、字段、方法参数、局部变量等进行注解。
        // 它主要的作用有以下四方面：
        // 1. 生成文档，通过代码里标识的元数据生成javadoc文档。
        // 2. 编译检查，通过代码里标识的元数据让编译器在编译期间进行检查验证。
        // 3. 编译时动态处理，编译时通过代码里标识的元数据动态处理，例如动态生成代码。
        // 4. 运行时动态处理，运行时通过代码里标识的元数据动态处理，例如使用反射注入实例。

        // Java自带的标准注解，
        // 包括@Override、@Deprecated和@SuppressWarnings，
        // 分别用于标明重写某个方法、标明某个类或方法过时、标明要忽略的警告，用这些注解标明后编译器就会进行检查。
        // 元注解，元注解是用于定义注解的注解，包括@Retention、@Target、@Inherited、@Documented，
        // @Retention用于标明注解被保留的阶段，
        // @Target用于标明注解使用的范围，
        // @Inherited用于标明注解可继承，
        // @Documented用于标明是否生成javadoc文档。
        // 自定义注解，可以根据自己的需求定义注解，并可用元注解对自定义注解进行注解。

        // Java 内置注解
        // @Override：表示当前的方法定义将覆盖父类中的方法
        // @Deprecated: 表示代码被弃用，如果使用了被@Deprecated注解的代码则编译器将发出警告
        // @SuppressWarnings: 表示关闭编译器警告信息

        // @Override
        // @Target(ElementType.METHOD)
        // @Retention(RetentionPolicy.SOURCE)
        // public @interface Override {}
        // 注解可以被用来修饰方法，并且它只在编译时有效，在编译后的class文件中便不再存在。
        // 作用：告诉编译器被修饰的方法是重写的父类的中的相同签名的方法，编译器会对此做出检查，
        // 若发现父类中不存在这个方法或是存在的方法签名不同，则会报错。

        // @Deprecated
        // @Documented
        // @Retention(RetentionPolicy.RUNTIME)
        // @Target(value={CONSTRUCTOR, FIELD, LOCAL_VARIABLE, METHOD, PACKAGE, PARAMETER, TYPE})
        // public @interface Deprecated {}
        // 从源码看，它会被文档化，并且能够保留到运行时，
        // 能够修饰 构造方法、属性、局部变量、方法、包、参数、类型
        // 作用：告诉编译器被修饰的元素已被废弃，不建议使用

        // @SuppressWarnings
        // @Target({TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE})
        // @Retention(RetentionPolicy.SOURCE)
        // public @interface SuppressWarnings {
        //    String[] value();
        // }
        // 只能存活于源码编译期
        // 能够修饰 类型，属性，方法，参数，构造器，局部变量
        // 取值为 String[]
        // 取值枚举：
        // all: 抑制所有警告
        // boxing: 抑制装箱、拆箱时的警告
        // cast: 抑制映射时的警告
        // dep-ann: 抑制启用注释的警告
        // deprecation: 抑制过期方法警告
        // fallthrough: 抑制在switch中缺失break的警告
        // finally: 抑制finally模块没有返回的警告
        // hiding: to suppress warnings relative to locals that hide variable（）
        // incomplete-switch: 忽略没有完整的switch语句
        // nls: 忽略非nls格式的字符
        // null: 忽略对null的操作
        // rawtypes: 使用反射时忽略没有指定相应的类型
        // restriction: 抑制使用不建议或禁止参照的相关警告
        // serial: 忽略在serializable类中没有声明SerialVersionUID的警告
        // static-access: 抑制不正确的静态访问方式的警告
        // synthetic-access: 抑制子类没有按最优方法访问内部类的警告
        // unchecked: 抑制没有进行类型检查操作的警告
        // unqualified-field-access: 抑制没有权限访问的域的警告
        // unused: 抑制没被使用过的代码的警告

        // 元注解
        

    }
}

class A {
    public void test() {
        System.out.println("Class A test");
    }
}

class B extends A {
    /**
     * 重写父类方法
     */
    @Override
    public void test() {
        System.out.println("Class B test");
    }

    /**
     * 过时方法
     */
    @Deprecated
    public void oldTest() {
        System.out.println("This is a deprecated method");
    }

    /**
     * 忽略警告
     * @return
     */
    @SuppressWarnings("rawtypes")
    public List warning() {
        List list = new ArrayList();
        return list;
    }
}
