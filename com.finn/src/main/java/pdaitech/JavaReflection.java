package pdaitech;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author maxjoker
 * @date 2022-04-17
 *
 * java反射
 *
 * https://pdai.tech/md/java/basic/java-basic-x-reflection.html
 *
 */
public class JavaReflection {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException,
            InstantiationException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        // JAVA反射机制是在运行状态中，
        // 对于任意一个类，都能够知道这个类的所有属性和方法；
        // 对于任意一个对象，都能够调用它的任意一个方法和属性；
        // 这种动态获取的信息以及动态调用对象的方法的功能称为java语言的反射机制。
        // 反射就是把java类中的各种成分映射成一个个的Java对象

        // 每个类都有一个  Class  对象，包含了与类有关的信息。
        // 当编译一个新类时，会产生一个同名的 .class 文件，该文件内容保存着 Class 对象。
        // 类加载相当于 Class 对象的加载。类在第一次使用时才动态加载到 JVM 中，可以使用 Class.forName("com.mysql.jdbc.Driver")
        // 这种方式来控制类的加载，该方法会返回一个 Class 对象。
        // 反射可以提供运行时的类信息，并且这个类可以在运行时才加载进来，甚至在编译时期该类的 .class 不存在也可以加载进来。

        // Class 和 java.lang.reflect 一起对反射提供了支持，java.lang.reflect 类库主要包含了以下三个类:
        // Field : 可以使用 get() 和 set() 方法读取和修改 Field 对象关联的字段；
        // Method : 可以使用 invoke() 方法调用与 Method 对象关联的方法；
        // Constructor : 可以用 Constructor 创建新的对象。

        // 手动编写的类被编译后会产生一个Class对象，其表示的是创建的类的类型信息，而且这个Class对象保存在同名.class的文件中(字节码文件)
        // 每个通过关键字class标识的类，在内存中有且只有一个与之对应的Class对象来描述其类型信息，无论创建多少个实例对象，其依据的都是用一个Class对象。

        // 类的加载过程：加载 -> 验证 -> 准备 -> 解析 -> 初始化 -> 使用 -> 卸载
        // java类加载机制：https://pdai.tech/md/java/jvm/java-jvm-classload.html
        // java类字节码详解：https://pdai.tech/md/java/jvm/java-jvm-class.html

        // Class类对象的获取
        // 在类加载的时候，jvm会创建一个class对象
        // 获取方式主要有三种：
        // 1. 根据类名：类名.class
        // 2. 根据对象：对象.getClass()
        // 3. 根据全限定类名：Class.forName(全限定类名)
        System.out.println("根据类名：" + UserInfo.class);
        System.out.println("根据对象：" + new UserInfo().getClass());
        System.out.println("根据全限定类名：" + Class.forName("pdaitech.JavaReflection"));

        // Class类的方法
        // forName(): (1)获取Class对象的一个引用，但引用的类还没加载就加载了这个类；(2)为了产生Class引用，forName()立即就进行了**初始化**
        // newInstance(): 返回一个Object对象，是实现 虚拟构造器 的一种途径。使用该方法创建的类，必须带有无参构造器
        // Object.getClass(): 获取Class对象的一个引用，
        // getName(): 返回全限定类名
        // getSimpleName(): 返回类名，不包括包名
        // getCanonicalName(): 获取全限定类名，包括包名
        // isInterface(): 判断Class对象是否为接口
        // getInterfaces(): 返回Class对象数组，表示Class对象所引用的类所实现的所有接口
        // getSuperclass(): 返回Class对象，表示Class对象所引用的类所继承的直接基类。应用该方法可在运行时发现一个对象完整的继承结构。
        // getFields(): 获得某个类的所有的公共（public）的字段，包括继承自父类的所有公共字段。 类似的还有getMethods和getConstructors。
        // getDeclaredFields: 获得某个类的自己声明的字段，即包括public、private和proteced，默认但是不包括父类声明的任何字段。
        // 类似的还有getDeclaredMethods和getDeclaredConstructors。

        // 常用方法
        System.out.println("获取全限定类名1：" + UserInfo.class.getName());
        System.out.println("获取全限定类名2：" + UserInfo.class.getCanonicalName());
        System.out.println("获取类名：" + new UserInfo().getClass().getSimpleName());
        System.out.println("实例化：" + Class.forName("pdaitech.JavaReflection").newInstance());
        System.out.println("是否为接口：" + UserInfo.class.isInterface());
        System.out.println("获取实现的接口：" + UserInfo.class.getInterfaces());
        System.out.println("获取继承的基类：" + UserInfo.class.getSuperclass());
        System.out.println("获取所有公共字段：");
        for (Field f : UserInfo.class.getFields()) {
            System.out.println(f.getName());
        }
        System.out.println("获取所有字段：");
        for (Field f : UserInfo.class.getDeclaredFields()) {
            System.out.println(f.getName());
        }

        // Constructor类及其用法
        // Constructor类存在于反射包(java.lang.reflect)中，反映的是Class 对象所表示的类的构造方法。
        // 获取Constructor对象是通过Class类中的方法获取的，Class类与Constructor相关的主要方法如下：
        //
        // forName(String className): 返回与带有给定字符串名的类或接口相关联的 Class 对象。
        // getConstructor(Class<?> ...parameterTypes): 返回指定参数类型、具有public访问权限的构造函数对象
        // getConstructors(): 返回所有具有public访问权限的构造函数的Constructor对象数组
        // getDeclaredConstructor(Class<?>... parameterTypes): 返回指定参数类型、所有声明的（包括private）构造函数对象
        // getDeclaredConstructor(): 返回所有声明的（包括private）构造函数对象
        // newInstance(): 调用无参构造器创建此 Class 对象所表示的类的一个新实例

        Class<?> clazz = null;
        clazz = Class.forName("pdaitech.UserInfo");
        UserInfo userInfo = (UserInfo) clazz.newInstance();
        userInfo.setName("a");
        userInfo.setAge(18);
        userInfo.setSex("M");
        userInfo.setId();
        System.out.println(userInfo.toString());

        // 带参构造器
        Constructor u1 = clazz.getConstructor(Long.class, String.class);
        System.out.println("构造方法的类是：" + u1.getDeclaringClass());
        System.out.println("以字符串形式返回此构造器名字：" + u1.getName());
        UserInfo userInfo1 = (UserInfo) u1.newInstance(System.currentTimeMillis(), "John");
        userInfo1.setSex("F");
        System.out.println(userInfo1.toString());

        // 获取私有构造器
        Constructor u2 = clazz.getDeclaredConstructor(Long.class, String.class, String.class, Integer.class);
        // 私有构造器，设置可访问性
        u2.setAccessible(true);
        UserInfo userInfo2 = (UserInfo) u2.newInstance(System.currentTimeMillis(), "Rich", "F", 20);
        System.out.println(userInfo2.toString());

        // 获取所有构造器
        Constructor<?> con[] = clazz.getDeclaredConstructors();
        for (int i = 0; i < con.length; i++) {
            System.out.println(con[i].toString());
        }


        // Field类及其用法
        // Field 提供有关类或接口的单个字段的信息，以及对它的动态访问权限。反射的字段可能是一个类（静态）字段或实例字段。
        //
        // getDeclaredField(String name): 获取指定name名称的(包含private修饰的)字段，不包括继承的字段
        // getDeclaredFields(): 获取Class对象所表示的类或接口的所有(包含private修饰的)字段,不包括继承的字段
        // getField(String name): 获取指定name名称、具有public修饰的字段，包含继承字段
        // getFields(): 获取修饰符为public的字段，包含继承字段

        Field field = clazz.getField("id");
        System.out.println("Field: " + field);

        Field[] fields = clazz.getFields();
        for (Field f : fields) {
            System.out.println("file: " + f);
        }

        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field f : declaredFields) {
            System.out.println("declaredField: " + f);
        }

        Field declaredField = clazz.getDeclaredField("age");
        System.out.println("declaredField: " + declaredField);

        // 设置值
        UserInfo userInfo3 = (UserInfo) clazz.newInstance();
        Field field1 = clazz.getField("id");
        field1.set(userInfo3, System.currentTimeMillis());
        System.out.println(userInfo3.toString());
        Field field2 = clazz.getDeclaredField("age");
        field2.setAccessible(true);
        field2.set(userInfo3, 21);
        System.out.println(userInfo3.toString());

        // 获取值
        System.out.println(field2.get(userInfo3));


        // Method类及其用法
        // Method 提供关于类或接口上单独某个方法（以及如何访问该方法）的信息，所反映的方法可能是类方法或实例方法（包括抽象方法）。
        //
        // getDeclaredMethod(String name, Class<?> parameterTypes): 返回一个指定参数的Method对象，该对象反映此 Class 对象所表示的类或接口的指定已声明方法。
        // getDeclaredMethod(): 返回 Method 对象的一个数组，这些对象反映此 Class 对象表示的类或接口声明的所有方法，包括公共、保护、默认（包）访问和私有方法，但不包括继承的方法。
        // getMethod(String name, Class<?> parameterTypes): 返回一个 Method 对象，它反映此 Class 对象所表示的类或接口的指定公共成员方法。
        // getMethods(): 返回一个包含某些 Method 对象的数组，这些对象反映此 Class 对象所表示的类或接口（包括那些由该类或接口声明的以及从超类和超接口继承的那些的类或接口）的公共 member 方法。
        // invoke(Object obj, Object... args): 对带有指定参数的指定对象调用由此 Method 对象表示的底层方法。
        // getReturnType(): 返回一个 Class 对象，该对象描述了此 Method 对象所表示的方法的正式返回类型,即方法的返回类型
        // getParameterTypes(): 按照声明顺序返回 Class 对象的数组，这些对象描述了此 Method 对象所表示的方法的形参类型。即返回方法的参数类型组成的数组
        // getName(): 返回方法名称
        // isVarArgs(): 判断方法是否带可变参数

        Method method = clazz.getMethod("setAge", Integer.class);
        System.out.println("method: " + method);

        // 获取所有public方法(包含继承的，所以会看到有 Object类的 equals(), hashcode()等方法
        Method[] methods = clazz.getMethods();
        for (Method m : methods) {
            System.out.println("foreach method: " + m);
        }

        // 获取private方法
        Method privateMethod = clazz.getDeclaredMethod("sleep");
        UserInfo userInfo4 = (UserInfo) clazz.newInstance();
        System.out.println("privateMethod: " + privateMethod);
        privateMethod.setAccessible(true);
        // 调用实例中的方法
        privateMethod.invoke(userInfo4);

        // 获取所有方法(不包含继承的)
        Method[] privateMethods = clazz.getDeclaredMethods();
        for (Method p : privateMethods) {
            System.out.println("foreach allMethod: " + p);
        }

        // 总结：
        // 1. 反射类及反射方法的获取，都是通过从列表中搜寻查找匹配的方法，所以查找性能会随类的大小方法多少而变化；
        // 2. 每个类都会有一个与之对应的Class实例，从而每个类都可以获取method反射方法，并作用到其他实例身上；
        // 3. 反射也是考虑了线程安全的，放心使用；反射类获取取的时候会加 synchronized 关键字获取锁
        // 4. 反射使用软引用relectionData缓存class信息，避免每次重新从jvm获取带来的开销；
        // 5. 反射调用多次生成新代理Accessor, 而通过字节码生存的则考虑了卸载功能，所以会使用独立的类加载器；
        // 6. 当找到需要的方法，都会copy一份出来，而不是使用原来的实例，从而保证数据隔离；
        // 7. 调度反射方法，最终是由jvm执行invoke0()执行
    }
}

class UserInfo {
    private String name;
    private Integer age;
    private String sex;
    public Long id;

    public UserInfo() {}

    public UserInfo(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    private UserInfo(Long id, String name, String sex, Integer age) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setId() {
        this.id = System.currentTimeMillis();
    }

    private void sleep() {
        System.out.println("go to sleep.");
    }

    private void wakeup() {
        System.out.println("wakeup.");
    }

    @Override
    public String toString() {
        return "UserInfo{" + "id = " + this.id +", name = " + this.name + ", age = " + this.age + ", sex = " + this.sex + "}";
    }
}
