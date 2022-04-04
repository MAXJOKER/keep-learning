@[TOC]( )
### 1、问题

使用 @Autowired 注解是 Spring 依赖注入的方法，但有些场景下仅仅靠这个注解无法明确要注入的是哪个bean。
默认情况下，@Autowired 按照类型装配 Spring bean。如果容器中有多个类型相同的bean，则框架将抛出 NoUniqueBeanDefinitionException，
以此提示有多个满足条件的bean进行自动装配。

下面的例子中，启动后会报错，抛出 NoUniqueBeanDefinitionException，这是因为 Spring **不知道要注入哪个 bean**。为了避免这个问题，
有几种解决方案。

```java
public class QualifierTest {
    @Autowired
    private Formatter formatter;

    public void test() {
        formatter.format();
    }
}

interface Formatter {
    String format();
}

@Component("fooFormatter")
class FooFormatter implements Formatter {
    @Override
    public String format() {
        return "foo";
    }
}

@Component("barFormatter")
class BarFormatter implements Formatter {
    @Override
    public String format() {
        return "bar";
    }
}
```
@[TOC]( )
### 2、@Qualifier
通过使用 @Qualifier 注解，我们可以**消除需要注入哪个bean的问题**。代码如下：

```java
public class QualifierTest {
    @Autowired
    @Qualifier("fooFormatter")
    private Formatter formatter;

    public void test() {
        formatter.format();
    }
}
```
@[TOC]( )
### 3、@Qualifier 与 @Primary

@Primary 注解，也可以用来决定要注入哪个bean，当有多个类型相同的bean时，此注解定义了首选项，也就是决定默认注入哪个bean（设置默认值）
```
@Bean
public Human man() {
    return new Human("Adam");
}

@Bean
@Primary
public Human woman() {
    return new Human("Eva");
}
```

上述两个方法中，Spring 默认注入的bean是 方法woman 返回的bean，因为包含了 @Primary 注解。
当我们想要指定默认注入的bean时，可以使用此注解。而如果我们要使用到另外的bean，则可以通过 @Qualifier 注解指定。

注意，当 @Primary 和 @Qualifier 同时存在时，@Qualifier 注解具有优先权，@Primary 注解定义了默认值，
@Qualifier 则是具体指定

@[TOC]( )
### 4、通过名称来自动注入
如果 @Autowired 进行自动装配时，如果没有其他注解，会按照**注入的变量名**来寻找合适的bean，
```
@Autowired
private Formatter fooFormatter;
```

上述代码中，最终被注入的bean是 FooFormatter，因为变量名与@Component中定义的值匹配。



