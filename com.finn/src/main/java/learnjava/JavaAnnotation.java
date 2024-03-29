package learnjava;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;
import java.lang.reflect.Method;
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
        // 注解是一个接口，一个继承自Annotation的接口。 里面每一个属性，其实就是接口的一个抽象方法

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
        // JDK 1.5 提供了4个标准的元注解:
        // @Target, @Retention, @Documented, @Inherited
        // JDK 1.8 提供了2个元注解：
        // @Repeatable
        // @Native

        // @Target
        // 描述注解的使用范围
        // 枚举类：java.lang.annotation.ElementType;

        // @Retention
        // 描述注解保留的时间范围
        // 枚举类：java.lang.annotation.RetentionPolicy;
        // SOURCE: 源文件保留
        // CLASS: 编译器保留，默认值
        // RUNTIME: 运行时保留，可以通过反射获取注解信息
        // 测试 javap -verbose JavaAnnotation.class 文件
        // 可以看到 SOURCE 阶段的方法反编译后
        // 1. 编译器并没有记录下 retentionSource() 方法的注解信息；
        // 2. 编译器分别使用了 RuntimeInvisibleAnnotations 和 RuntimeVisibleAnnotations
        // 属性去记录了retentionClass()方法 和 retentionRuntime()方法 的注解信息；

        // @Documented
        // 作用：在使用 javadoc 工具为类生成帮助文档时是否要保留其注解信息

        // @Inherited
        // 作用：被它修饰的Annotation将具有继承性。如果某个类使用了被@Inherited修饰的Annotation，则其子类将自动具有该注解。
        Student stu = new Student();
        stu.test();

        // @Repeatable
        // 重复注解：允许在同一申明类型(类，属性，或方法)的多次使用同一个注解
        RepeatTest repeatTest = new RepeatTest();
        repeatTest.method();
        Class clazz = RepeatTest.class;
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            System.out.println(method.getName());
            Annotation[] annotations = method.getDeclaredAnnotations();
            for (Annotation annotation : annotations) {
                System.out.println(annotation.toString());
            }
        }

        // @Native
        // 使用 @Native 注解修饰成员变量，则表示这个变量可以被本地代码引用，常常被代码生成工具使用。
        // 对于 @Native 注解不常使用，了解即可

        // 自定义注解
        try {
            Method[] methods1 = ApiReportTest.class.
                    getClassLoader().
                    loadClass("learnjava.ApiReportTest")
                    .getDeclaredMethods();
            for (Method method : methods1) {
                if (method.isAnnotationPresent(ApiReport.class)) {
                    for (Annotation annotation : method.getDeclaredAnnotations()) {
                        System.out.println("method: " + method + ", annotation: " + annotation);
                    }

                    ApiReport apiReport = method.getAnnotation(ApiReport.class);
                    System.out.println("name: " +apiReport.name());
                    System.out.println("description: " +apiReport.description());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // 注解不支持继承
        // 不能使用关键字extends来继承某个@interface，但注解在编译后，编译器会自动继承java.lang.annotation.Annotation接口.


        // 注解的应用场景
        // 1. Spring 框架 配置化到注解化的转变，xml文件配置 到 注解
        // 2. 继承实现到注解实现 - Junit3到Junit4。
        // 一个模块的封装大多数类都是通过继承和组合等模式来实现的，但是如果结合注解将可以极大程度提高实现的优雅度（降低耦合度）。
        // 而Junit3 到Junit4的演化就是最好的一个例子。

        // 自定义注解和AOP - 通过切面实现解耦
        // 比如接口请求上报，自定义注解 ApiReport，定义切面ReportAspect，切入点为使用ApiReport注解的方法
        // 在controller的方法中，使用注解ApiReport，代码在下面
        ApiReportTest apiReportTest = new ApiReportTest();
        apiReportTest.apiReport();

        // Java注解处理器
        // 译文：https://www.race604.com/annotation-processing/
        // 原文：http://hannesdorfmann.com/annotation-processing/annotationprocessing101/

        // TODO 深入底层相关的还需要再找多几篇文章看看

    }

    /**
     * Retention 测试
     */
    @RetentionSource
    public void retentionSource() {}

    @RetentionClass
    public void retentionClass() {}

    @RetentionRuntime
    public void retentionRuntime() {}
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


/**
 * Retention 测试
 */
@Retention(RetentionPolicy.SOURCE)
@interface RetentionSource {}

@Retention(RetentionPolicy.CLASS)
@interface RetentionClass {}

@Retention(RetentionPolicy.RUNTIME)
@interface RetentionRuntime {}

/**
 * Inherited 测试
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@interface TestInheritedAnnotation {
    String[] values();
    int number();
}

@TestInheritedAnnotation(values = {"value"}, number = 10)
class P {

}

class Student extends P {
    public void test() {
        Class clazz = Student.class;
        Annotation[] annotations = clazz.getAnnotations();
        for (Annotation ann : annotations) {
            System.out.println(ann.toString());
        }
    }
}

/**
 * @Repeatable 测试
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Repeatable(Repeats.class)
@interface Repeat{
    String role();
}

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@interface Repeats {
    Repeat[] value();
}

class RepeatTest {
    @Repeat(role = "Teacher")
    @Repeat(role = "Programmer")
    public void method() {
    }
}

/**
 * 自定义注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE, ElementType.METHOD})
@interface ApiReport {
    String name();
    String description();
}

@Aspect
@Component
class ReportAspect {
    public ReportAspect() {}

    @Pointcut(value = "@annotation(apiReport)", argNames = "apiReport")
    public void pointcut(ApiReport apiReport) {
    }

    @Before(value = "pointcut(apiReport)", argNames = "apiReport")
    public void before(ApiReport apiReport) {
        System.out.println("@Before");
    }

    @Around(value = "pointcut(apiReport)", argNames = "joinPoint, apiReport")
    public Object around(ProceedingJoinPoint joinPoint, ApiReport apiReport) throws Throwable {
        Object object = new Object();
        // do something
        System.out.println("before proceed.");
        object = joinPoint.proceed();
        // do something
        System.out.println("after proceed.");
        return object;
    }
}


class ApiReportTest {
    @ApiReport(name = "apiReport", description = "apiReport test")
    public void apiReport() {
        System.out.println("api report");
    }
}
