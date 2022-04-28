package pdaitech;

/**
 * @author maxjoker
 * @date 2022-04-28 14:52
 *
 * 笔记链接：https://pdai.tech/md/java/advanced/java-advanced-spi.html
 * https://zhuanlan.zhihu.com/p/28909673
 *
 * java SPI
 *
 */
public class JavaSpi {
    public static void main(String[] args) {
        // SPI（Service Provider Interface），是JDK内置的一种 服务提供发现机制，可以用来启用框架扩展和替换组件，
        // 主要是被框架的开发人员使用，比如java.sql.Driver接口，其他不同厂商可以针对同一接口做出不同的实现，
        // MySQL和PostgreSQL都有不同的实现提供给用户，而Java的SPI机制可以为某个接口寻找服务实现。
        // Java中SPI机制主要思想是将装配的控制权移到程序之外，在模块化设计中这个机制尤其重要，其核心思想就是 解耦。
        // 机制流程图: https://pdai-1257820000.cos.ap-beijing.myqcloud.com/pdai.tech/public/_images/java/java-advanced-spi-8.jpg
        // 当服务的提供者提供了一种接口的实现之后，需要在classpath下的META-INF/services/目录里创建一个以服务接口命名的文件
        // 这个文件里的内容就是这个接口的具体的实现类
        // 当其他的程序需要这个服务的时候，就可以通过查找这个jar包（一般都是以jar包做依赖）的META-INF/services/中的配置文件，
        // (为什么要放在META-INF/services目录下？ ServiceLoader定义了文件的PREFIX为 "META-INF/services"，具体打开ServiceLoader类）
        // 配置文件中有接口的具体实现类名，可以根据这个类名进行加载实例化，就可以使用该服务了
        // JDK中查找服务的实现的工具类是：java.util.ServiceLoader

        // API的特征:
        // 1. 概念上更接近实现方
        // 2. 组织上位于实现方所在的包中
        // 3. 实现和接口在一个包中

        // SPI的特征:
        // 1. 概念上更依赖调用方
        // 2. 组织上位于调用方所在的包中
        // 3. 实现位于独立的包中（也可认为在提供方中）
        // https://pic2.zhimg.com/80/v2-e755e1d56a1bc819f7a1b02a6bda5d81_720w.png

        // API 与 SPI
        // https://pic1.zhimg.com/80/v2-5210ed7234f7046273104bed9bfd7764_720w.png

        // SPI 简单例子
        // 具体见 javaspi目录中的代码

        // SPI机制的广泛应用
        //
        // SPI机制 - JDBC DriverManager
        // mysql-connector-java jar包中，META-INF/services 目录下有个 java.sql.Driver 文件, 里面的内容就是针对Java中定义的接口的实现
        // 主要是 Driver.java  DriverManager.java
        // DriverManager 中 有个静态代码块
        //    /**
        //     * Load the initial JDBC drivers by checking the System property
        //     * jdbc.properties and then use the {@code ServiceLoader} mechanism
        //     */
        //    static {
        //        loadInitialDrivers();
        //        println("JDBC DriverManager initialized");
        //    }
        // 具体分析：https://pdai.tech/md/java/advanced/java-advanced-spi.html#spi%E6%9C%BA%E5%88%B6---jdbc-drivermanager
        // loadInitialDrivers 代码主要步骤是：
        // 1. 从系统变量中获取有关驱动的定义。
        // 2. 使用SPI来获取驱动的实现。
        // 3. 遍历使用SPI获取到的具体实现，实例化各个实现类。iterator.next() 的时候进行了实例化
        // 4. 根据第一步获取到的驱动列表来实例化具体实现类。

        // SPI机制 - Common-Logging
        // 具体分析：https://pdai.tech/md/java/advanced/java-advanced-spi.html#spi%E6%9C%BA%E5%88%B6---common-logging
        // 抽象类LogFactory加载具体实现的步骤如下：
        // 1. 从vm系统属性org.apache.commons.logging.LogFactory
        // 2. 使用SPI服务发现机制，发现org.apache.commons.logging.LogFactory的实现
        // 3. 查找classpath根目录commons-logging.properties的org.apache.commons.logging.LogFactory属性是否指定factory实现
        // 4. 使用默认factory实现，org.apache.commons.logging.impl.LogFactoryImpl

        // SPI机制 - Spring中SPI机制
        // 在springboot的自动装配过程中，最终会加载META-INF/spring.factories文件，而加载的过程是由SpringFactoriesLoader加载的。
        // 从CLASSPATH下的每个Jar包中搜寻所有META-INF/spring.factories配置文件，
        // 然后将解析properties文件，找到指定名称的配置后返回。
        // 需要注意的是，其实这里不仅仅是会去ClassPath路径下查找，会扫描所有路径下的Jar包，只不过这个文件只会在Classpath下的jar包中。

        // SPI机制实现原理
        // 细看 ServiceLoader类
        // parseLine() 方法 一行一行地解析 META-INF/services 中的配置
        // 并且会检查配置中的内容，是否为合法的java标识符, isJavaIdentifierStart(), isJavaIdentifierPart() 这两个方法
        // 然后把服务提供者缓存到 providers 中
        // 实例化是在 ServiceLoader中的nextService() 方法实现的，ServiceLoader类实现了Iterator接口，实现next()方法，next()
        // 会调用 nextService() 方法，从而实例化服务提供者
        // 所以我们可以看到ServiceLoader不是实例化以后，就去读取配置文件中的具体实现，并进行实例化。
        // 而是等到使用迭代器去遍历的时候，才会加载对应的配置文件去解析，调用hasNext方法的时候会去加载配置文件进行解析，
        // 调用next方法的时候进行实例化并缓存。
        // 所有的配置文件只会加载一次，服务提供者也只会被实例化一次，重新加载配置文件可使用reload方法
        // 看代码就很清晰了

        // 使用规范
        // https://pdai-1257820000.cos.ap-beijing.myqcloud.com/pdai.tech/public/_images/java/java-advanced-spi-2.jpg

        // SPI机制的缺陷
        // 通过上面的解析，可以发现，我们使用SPI机制的缺陷：
        // 1. 不能按需加载，需要遍历所有的实现，并实例化，然后在循环中才能找到我们需要的实现。如果不想用某些实现类，或者某些类实例化很耗时，
        //    它也被载入并实例化了，这就造成了浪费。
        // 2. 获取某个实现类的方式不够灵活，只能通过 Iterator 形式获取，不能根据某个参数来获取对应的实现类。
        // 3. 多个并发多线程使用 ServiceLoader 类的实例是不安全的。
    }
}
