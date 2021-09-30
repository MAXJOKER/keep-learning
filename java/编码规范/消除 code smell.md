## 自勉
写代码要有点追求好吧，这么多code smell，自己都觉得不合适，更别说别人了。code smell 日积月累，不仅影响自身的编码水平，代码的性能也可能变差，屎上雕花这种事情咱们还是不要做了。精益求精。

### 性能提升

  <b>1. 需要取Map的键值时，应使用 entrySet()</b>
   
当需要取Map的key时，使用 keySet() 是正确的。但如果需要取 key，value，使用 entrySet() 更加高效。// todo 为啥更高效

<B>keySet() 的做法</B>
   ```
        Map<String, String> map = ...;
        for (String key : map.keySet()) {
            String value = map.get(key);
        }
   ```

<B>entrySet() 的做法</B>
   ```
        Map<String, String> map = ...;
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String value = entry.getValue();
            String key = entry.getKey();
        }
   ```

  <b>2. 使用 Collection.isEmpty() 检测空</b>

使用Collection.size() 检测空是没问题的，但使用 Collection.isEmpty() 更加易读，并且能获得更好的性能。任何 Collection.isEmpty() 实现的时间复杂度都是O(1)，而某些 Collection.size() 实现的时间复杂度可能是O(n)。 如果还需要检查是否为空，可以使用 CollectionUtils.isEmpty。//todo 哪些？

  <b>3. 字符串拼接使用StringBuilder</b>

  一般的字符拼接在编译期java会进行优化，但在循环中拼接字符串，java编译器无法做到优化。
  // todo 使用 StringBuilder 的好处是？
  ```
  StringBuilder str = new StringBuilder();
  for (i == 0; i < 10; i ++) {
    str.append(i);
  }
  ```

  <b>4. 频繁调用Collection.contains()方法请替换使用set</b>

  在java集合类库中，List的contains方法普遍的时间复杂度为O(n)，如果代码中需要频繁地调用contains方法校验数据，可以将数据转成HashSet实现，将O(n)复杂度降为O(1)。
  ```
  ArrayList list = getData();
  Set<Integer> s = new HashSet(list);
  s.contains(value);
  ```



  ### 代码优雅

  <b>1. 不要使用集合实现赋值静态成员变量</b>

  <b>反例</b>
  ```
  private static Map<Integer, String> map = new HashMap<Integer, String>() {
    put(1, "1");
  }
  ```

  <b>正确使用</b>
  ```
  private static Map<Integer, String> map = new HashMap();
  static {
    map.put(1, "1");
  }
  ```

  <b>2. 工具类应该屏蔽构造函数</b>
  工具类是一堆静态字段和函数的合集，不应该被实例化。
  ```
  public class TestUtils {
    private TestUtils() {}
    public static final String a = "test";
    public static int sum(Integer a, Integer b) {
      return a + b;
    }
  }
  ```


  ### 远离BUG

  <b> 1. 禁止使用BigDecimal(Double)构造方法</b>
  BigDecimal(Double) 存在精度丢失风险，在精确计算或者比较的场景中可能会导致业务异常。
  
  BadCase：
  ```
  BigDecimal a = new BigDecimal(0.1D); // 0.100000000000000005551115
  ```

  应该这样使用：
  ```
  BigDecimal a = BigDecimal.valueOf(0.1D); // 0.1
  ```

  <b>2. 返回空数组或者空集合而不是null</b>

  返回null的话，调用方需要强制判断是否为null，否则就会抛空异常；而如果返回的是空数组或者空集合，则避免调用方没有判断null而报错的问题。

  <b>3. 小心String.split(String regex)</b>

  String.split()方法传入的分隔符是正则表达式，部分关键字需要转义。
  ```
  "a.aa.aaa".split("\\."); // 结果为 ["a", "aa", "aaa"]
  ```