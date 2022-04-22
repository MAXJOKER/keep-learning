package pdaitech;

import java.lang.reflect.Array;
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
        Point<String> point = new Point<String>();
        point.setVar("test");
        System.out.println(point.getVar());

        // 多元泛型类
        MultiGeneric<String, Integer> multiGeneric = new MultiGeneric<String, Integer>();
        multiGeneric.setKey("Man");
        multiGeneric.setValue(18);
        System.out.println(multiGeneric);

        // 泛型接口
        Info<String> info = new InfoImpl<>();
        info.setVar("generic test");
        System.out.println(info.getVar());

        // 泛型方法
        try {
            // 为什么要使用泛型方法呢？因为泛型类要在实例化的时候就指明类型，如果想换一种类型，不得不重新new一次，
            // 可能不够灵活；而泛型方法可以在调用的时候指明类型，更加灵活。
            Object obj = getObject(Class.forName("pdaitech.Point"));
            System.out.println(obj.toString());
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            System.out.println("get object failed: " + e.getMessage());
        }

        // 泛型的上下限
        //
        // 上限
        // 为了解决泛型中隐含的转换问题，Java泛型加入了类型参数的上下边界机制。
        // <? extends A>表示该类型参数可以是A(上边界)或者A的子类类型。
        // 编译时擦除到类型A，即用A类型代替类型参数。
        Price<Integer> price = new Price<>();
        price.setVar(1);
        System.out.println(price.getVar());

        // 下限
        count(price);

        // 泛型上下限总结
        // <?> 无限制通配符
        // <? extends E> extends 关键字声明了类型的上界，表示参数化的类型可能是所指定的类型，或者是此类型的子类
        // <? super E> super 关键字声明了类型的下界，表示参数化的类型可能是指定的类型，或者是此类型的父类
        //
        // 使用原则《Effective Java》
        // 为了获得最大限度的灵活性，要在表示 生产者或者消费者 的输入参数上使用通配符，
        // 使用的规则就是：生产者有上限、消费者有下限
        //
        // 1. 如果参数化类型表示一个 T 的生产者，使用 < ? extends T>;
        // 2. 如果它表示一个 T 的消费者，就使用 < ? super T>；
        // 3. 如果既是生产又是消费，那使用通配符就没什么意义了，因为你需要的是精确的参数类型。

        // 泛型数组
        // 合理使用
        //  public ArrayWithTypeToken(Class<T> type, int size) {
        //    array = (T[]) Array.newInstance(type, size);
        //  }

        // 深入理解泛型
        //
        // 如何理解Java中的泛型是伪泛型？泛型中类型擦除 Java泛型这个特性是从JDK 1.5才开始加入的，
        // 因此为了兼容之前的版本，Java泛型的实现采取了“伪泛型”的策略，即Java在语法上支持泛型，
        // 但是在编译阶段会进行所谓的“类型擦除”（Type Erasure），
        // 将所有的泛型表示（尖括号中的内容）都替换为具体的类型（其对应的原生态类型），就像完全没有泛型一样。
        // 理解类型擦除对于用好泛型是很有帮助的，尤其是一些看起来“疑难杂症”的问题，弄明白了类型擦除也就迎刃而解了。
        // 泛型的类型擦除原则是：
        // 消除类型参数声明，即删除<>及其包围的部分。
        // 根据类型参数的上下界推断并替换所有的类型参数为原生态类型：
        // 如果类型参数是无限制通配符或没有上下界限定则替换为Object，
        // 如果存在上下界限定则根据子类替换原则取类型参数的最左边限定类型（即父类）。
        // 为了保证类型安全，必要时插入强制类型转换代码。
        // 自动产生“桥接方法”以保证擦除类型后的代码仍然具有泛型的“多态性”。

        // 如何进行擦除？
        // 擦除类定义中的类型参数 - 无限制类型擦除
        // 当类定义中的类型参数没有任何限制时，在类型擦除中直接被替换为Object，即形如<T>和<?>的类型参数都被替换为Object
        // https://pdai-1257820000.cos.ap-beijing.myqcloud.com/pdai.tech/public/_images/java/java-basic-generic-1.png
        //
        // 擦除类定义中的类型参数 - 有限制类型擦除
        // 当类定义中的类型参数存在限制（上下界）时，在类型擦除中替换为类型参数的上界或者下界，
        // 比如形如<T extends Number>和<? extends Number>的类型参数被替换为Number，
        // <? super Number>被替换为Object。
        // https://pdai-1257820000.cos.ap-beijing.myqcloud.com/pdai.tech/public/_images/java/java-basic-generic-2.png
        //
        // 擦除方法定义中的类型参数
        // 擦除方法定义中的类型参数原则和擦除类定义中的类型参数是一样的
        // https://pdai-1257820000.cos.ap-beijing.myqcloud.com/pdai.tech/public/_images/java/java-basic-generic-3.png
        //
        // 类型擦除证明
        ArrayList<String> stringArrayList = new ArrayList<>();
        stringArrayList.add("test");
        ArrayList<Integer> integerArrayList = new ArrayList<>();
        integerArrayList.add(1);
        System.out.println(stringArrayList.getClass() == integerArrayList.getClass());

        try {
            stringArrayList.getClass().getMethod("add", Object.class).invoke(stringArrayList, 1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(stringArrayList);

        // 原始类型 就是擦除去了泛型信息，最后在字节码中的类型变量的真正类型，无论何时定义一个泛型，
        // 相应的原始类型都会被自动提供，类型变量擦除，并使用其限定类型（无限定的变量用Object）替换。

        // 如何理解泛型的编译期检查？
        // 既然说类型变量会在编译的时候擦除掉，那为什么我们往 ArrayList 创建的对象中添加整数会报错呢？
        // 不是说泛型变量String会在编译的时候变为Object类型吗？
        // 为什么不能存别的类型呢？既然类型擦除了，如何保证我们只能使用泛型变量限定的类型呢？
        // Java编译器是通过先检查代码中泛型的类型，然后在进行类型擦除，再进行编译。
        // stringArrayList.add(1); 编译不通过
        // 说明这就是在编译之前的检查，因为如果是在编译之后检查，类型擦除后，原始类型为Object，
        // 是应该允许任意引用类型添加的。可实际上却不是这样的，这恰恰说明了关于泛型变量的使用，是会在编译之前检查的。
        // 类型检查就是针对引用的，谁是一个引用，用这个引用调用泛型方法，
        // 就会对这个引用调用的方法进行类型检测，而无关它真正引用的对象。
        // ArrayList<Object> l = new ArrayList<>(String); 编译出错
        // 引用传递是不允许，不支持类型强转

        // 如何理解泛型的多态？泛型的桥接方法
        // 类型擦除会造成多态的冲突，而JVM解决方法就是桥接方法。
        // 这里分析的很到位
        // https://pdai.tech/md/java/basic/java-basic-x-generic.html#%E5%A6%82%E4%BD%95%E7%90%86%E8%A7%A3%E6%B3%9B%E5%9E%8B%E7%9A%84%E5%A4%9A%E6%80%81%E6%B3%9B%E5%9E%8B%E7%9A%84%E6%A1%A5%E6%8E%A5%E6%96%B9%E6%B3%95

        // 如何理解基本类型不能作为泛型类型？
        // 比如，我们没有ArrayList<int>，只有ArrayList<Integer>, 为何？
        // 因为当类型擦除后，ArrayList的原始类型变为Object，但是Object类型不能存储int值，只能引用Integer的值。
        // 另外需要注意，我们能够使用list.add(1)是因为Java基础类型的自动装箱拆箱操作

        // 如何理解泛型类型不能实例化？
        // 不能实例化泛型类型, 这本质上是由于类型擦除决定的：
        // T test = new T(); 编译出错
        //因为在 Java 编译期没法确定泛型参数化类型，也就找不到对应的类字节码文件，所以自然就不行了，
        // 此外由于T 被擦除为 Object，如果可以 new T() 则就变成了 new Object()，失去了本意。
        //
        // 如果我们确实需要实例化一个泛型，应该如何做呢？可以通过反射实现：
        //  static <T> T newTClass (Class < T > clazz) throws InstantiationException, IllegalAccessException {
        //      T obj = clazz.newInstance();
        //      return obj;
        //  }

        // 泛型数组：能不能采用具体的泛型类型进行初始化？
        // List<String>[] lsa = new List<String>[10]; Not really allowed.
        // Object o = lsa;
        // Object[] oa = (Object[]) o;
        // List<Integer> li = new ArrayList<Integer>();
        // li.add(new Integer(3));
        // oa[1] = li; // Unsound, but passes run time store check
        // String s = lsa[1].get(0); // Run-time error ClassCastException.
        // 由于 JVM 泛型的擦除机制，所以上面代码可以给 oa[1] 赋值为 ArrayList 也不会出现异常，
        // 但是在取出数据的时候却要做一次类型转换，所以就会出现 ClassCastException，
        // 如果可以进行泛型数组的声明则上面说的这种情况在编译期不会出现任何警告和错误，
        // 只有在运行时才会出错，但是泛型的出现就是为了消灭 ClassCastException，
        // 所以如果 Java 支持泛型数组初始化操作就是搬起石头砸自己的脚。

        // List<?>[] lsa = new List<?>[10]; // OK, array of unbounded wildcard type.
        // Object o = lsa;
        // Object[] oa = (Object[]) o;
        // List<Integer> li = new ArrayList<Integer>();
        // li.add(new Integer(3));
        // oa[1] = li; // Correct.
        // Integer i = (Integer) lsa[1].get(0); // OK
        // 所以说采用通配符的方式初始化泛型数组是允许的，因为对于通配符的方式最后取出数据是要做显式类型转换的，
        // 符合预期逻辑。综述就是说 Java 的泛型数组初始化时数组类型不能是具体的泛型类型，
        // 只能是通配符的形式，因为具体类型会导致可存入任意类型对象，在取出时会发生类型转换异常，
        // 会与泛型的设计思想冲突，而通配符形式本来就需要自己强转，符合预期。

        // 泛型数组：如何正确的初始化泛型数组实例？
        // public class ArrayWithTypeToken<T> {
        //    private T[] array;
        //
        //    public ArrayWithTypeToken(Class<T> type, int size) {
        //        array = (T[]) Array.newInstance(type, size);
        //    }
        //
        //    public void put(int index, T item) {
        //        array[index] = item;
        //    }
        //
        //    public T get(int index) {
        //        return array[index];
        //    }
        //
        //    public T[] create() {
        //        return array;
        //    }
        //}
        ////...
        //
        // ArrayWithTypeToken<Integer> arrayToken = new ArrayWithTypeToken<Integer>(Integer.class, 100);
        // Integer[] array = arrayToken.create();

        // 使用反射来初始化泛型数组算是优雅实现，因为泛型类型 T在运行时才能被确定下来，
        // 我们能创建泛型数组也必然是在 Java 运行时想办法，而运行时能起作用的技术最好的就是反射了。

        // 如何理解泛型类中的静态方法和静态变量？
        // 泛型类中的静态方法和静态变量不可以使用泛型类所声明的泛型类型参数
        // public class Test2<T> {
        //    public static T one;   //编译错误
        //    public static  T show(T one){ //编译错误
        //        return null;
        //    }
        // }
        // 因为泛型类中的泛型参数的实例化是在定义对象的时候指定的，
        // 而静态变量和静态方法不需要使用对象来调用。
        // 对象都没有创建，如何确定这个泛型参数是何种类型，所以当然是错误的。

        // public class Test2<T> {
        //
        //    public static <T >T show(T one){ //这是正确的
        //        return null;
        //    }
        // }
        // 因为这是一个泛型方法，在泛型方法中使用的T是自己在方法中定义的 T，而不是泛型类中的T。

        // 如何理解异常中使用泛型？
        // 不能抛出也不能捕获泛型类的对象。事实上，泛型类扩展Throwable都不合法。例如：下面的定义将不会通过编译：
        // public class Problem<T> extends Exception {
        //
        // }
        // 为什么不能扩展Throwable，因为异常都是在运行时捕获和抛出的，而在编译的时候，
        // 泛型信息全都会被擦除掉，那么，假设上面的编译可行，那么，在看下面的定义：
        // try{
        //
        // } catch(Problem<Integer> e1) {
        //
        // } catch(Problem<Number> e2) {
        //
        // }
        // 类型信息被擦除后，那么两个地方的catch都变为原始类型Object，那么也就是说，
        // 这两个地方的catch变的一模一样,就相当于下面的这样
        // try{
        //
        // } catch(Problem<Object> e1) {
        //
        // } catch(Problem<Object> e2) {
        //
        // }

        // 不能在catch子句中使用泛型变量
        // public static <T extends Throwable> void doWork(Class<T> t) {
        // try {
        //        ...
        //    } catch(T e) { //编译错误
        //        ...
        //    }
        // }
        // 因为泛型信息在编译的时候已经变味原始类型，也就是说上面的T会变为原始类型Throwable，
        // 那么如果可以再catch子句中使用泛型变量，那么，下面的定义呢：
        // public static <T extends Throwable> void doWork(Class<T> t){
        //    try {
        //
        //    } catch(T e) { //编译错误
        //
        //    } catch(IndexOutOfBounds e) {
        //
        //    }
        // }
        // 根据异常捕获的原则，一定是子类在前面，父类在后面，那么上面就违背了这个原则。
        // 即使你在使用该静态方法的使用T是ArrayIndexOutOfBounds，在编译之后还是会变成Throwable，
        // ArrayIndexOutOfBounds是IndexOutOfBounds的子类，违背了异常捕获的原则。
        // 所以java为了避免这样的情况，禁止在catch子句中使用泛型变量。
        //
        // 但是在异常声明中可以使用类型变量。下面方法是合法的。
        // public static<T extends Throwable> void doWork(T t) throws T {
        // try{
        //        ...
        //    } catch(Throwable realCause) {
        //        t.initCause(realCause);
        //        throw t;
        //    }
        // }

        // 如何获取泛型的参数类型？
        // 既然类型被擦除了，那么如何获取泛型的参数类型呢？可以通过反射（java.lang.reflect.Type）获取泛型
        // java.lang.reflect.Type是Java中所有类型的公共高级接口, 代表了Java中的所有类型.
        // Type体系中类型的包括：数组类型(GenericArrayType)、参数化类型(ParameterizedType)、
        // 类型变量(TypeVariable)、通配符类型(WildcardType)、原始类型(Class)、基本类型(Class),
        // 以上这些类型都实现Type接口。
        // public class GenericType<T> {
        //    private T data;
        //
        //    public T getData() {
        //        return data;
        //    }
        //
        //    public void setData(T data) {
        //        this.data = data;
        //    }
        //
        //    public static void main(String[] args) {
        //        GenericType<String> genericType = new GenericType<String>() {};
        //        Type superclass = genericType.getClass().getGenericSuperclass();
        //        //getActualTypeArguments 返回确切的泛型参数, 如Map<String, Integer>返回[String, Integer]
        //        Type type = ((ParameterizedType) superclass).getActualTypeArguments()[0];
        //        System.out.println(type);//class java.lang.String
        //    }
        // }

    }

    /**
     * 只能接收Integer或者Object类型的参数
     * @param price
     */
    public static void count(Price<? super Integer> price) {
        System.out.println(price);
    }

    /**
     * 泛型方法
     * @param c 用来创建泛型对象
     * 为什么要用变量c来创建对象呢？
     * 既然是泛型方法，就代表着我们不知道具体的类型是什么，也不知道构造方法如何，
     * 因此没有办法去new一个对象，但可以利用变量c的newInstance方法去创建对象，也就是利用反射创建对象。
     * @param <T> 声明一个泛型方法  定义泛型方法时，必须在返回值前边加一个<T>，来声明这是一个泛型方法
     * @return T 表明返回值为类型T
     *
     * Class<T> 作用是指明泛型T的具体类型
     *
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public static <T> T getObject(Class<T> c) throws InstantiationException, IllegalAccessException {
        T t = c.newInstance();
        return t;
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

/**
 * 泛型类
 * @param <T>
 */
class Point<T> { // T 是 type 的简称
    private T var; // var 的类型由T指定，即由外部调用指定
    public T getVar() { // 返回值也是由外部指定
        return var;
    }

    public void setVar(T var) { // 设置类型也是由外部指定
        this.var = var;
    }
}

/**
 * 多元泛型类
 * @param <K>
 * @param <V>
 */
class MultiGeneric<K, V> {
    private K key;
    private V value;

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public void setValue(V value) {
        this.value =value;
    }

    @Override
    public String toString() {
        return "{Key: " + key + ", value: " + value + "}";
    }
}

/**
 * 泛型接口
 * @param <T>
 */
interface Info<T> {
    /**
     * 定义抽象方法，返回值设置为泛型
     * @return
     */
    public T getVar();

    public void setVar(T var);
}

/**
 * 实现泛型接口
 * @param <T>
 */
class InfoImpl<T> implements Info<T> {
    private T var;

    @Override
    public T getVar() {
        return var;
    }

    @Override
    public void setVar(T var) {
        this.var = var;
    }
}

/**
 * 泛型的上边界
 * 传入的类型只能是Number或者Number的子类（Double，Float，Integer等）
 * @param <T>
 */
class Price<T extends Number> {
    private T var;

    public void setVar(T var) {
        this.var =var;
    }

    public T getVar() {
        return var;
    }
}
