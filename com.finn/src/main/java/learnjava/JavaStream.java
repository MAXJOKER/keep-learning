package learnjava;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author maxjoker
 * @date 2022-04-10 19:42
 *
 * Java8 stream 用法记录
 *
 * Java 8 API添加了一个新的抽象称为流Stream，可以让你以一种声明的方式处理数据。
 *
 * Stream 使用一种类似用 SQL 语句从数据库查询数据的直观方式来提供一种对 Java 集合运算和表达的高阶抽象。
 *
 * Stream（流）是一个来自数据源的元素队列并支持聚合操作
 *
 * - 元素是特定类型的对象，形成一个队列。 Java中的Stream并不会存储元素，而是按需计算。
 * - 数据源 流的来源。 可以是集合，数组，I/O channel， 产生器generator 等。
 * - 聚合操作 类似SQL语句一样的操作， 比如filter, map, reduce, find, match, sorted等。
 *
 * 在 Java 8 中, 集合接口有两个方法来生成流：
 *
 * - stream() − 为集合创建串行流。
 *
 * - parallelStream() − 为集合创建并行流。
 *
 */
public class JavaStream {
    public static void main(String[] args) {
        List<String> string = Arrays.asList("abc", "a", "b", "", "e", "gh");
        // 过滤
        List<String> filtered = string.stream().filter(str -> !str.isEmpty()).collect(Collectors.toList());
        long count = string.stream().filter(str -> !str.isEmpty()).count();
        // [abc, a, b, e, gh]
        // 5
        System.out.println(filtered);
        System.out.println(count);

        // limit、foreach 随机输出
        Random random = new Random();
        random.ints().limit(10).forEach(System.out::println);

        // map 映射每个元素到对应的结果
        List<Integer> numbers = Arrays.asList(1, 3, 5, 7, 9, 1);
        // distinct() 获取非重复元素
        List<Integer> squares = numbers.stream().map(i -> i * i).distinct().collect(Collectors.toList());
        // [1, 9, 25, 49, 81]
        System.out.println(squares);

        // sorted 对流进行 升序排序
        random.ints().limit(10).sorted().forEach(System.out::println);

        // 并行 parallel 程序
        List<String> string1 = Arrays.asList("sdf", "afsd", "wqert", "dfdf", "");
        List<String> s = string1.parallelStream().filter(str -> !str.isEmpty()).collect(Collectors.toList());
        // [sdf, afsd, wqert, dfdf]
        System.out.println(s);

        // Collector 归约操作
        List<String> string2 = Arrays.asList("H", "e", "l", "l", "o");
        String s2 = string2.stream().collect(Collectors.joining(""));
        // Hello
        System.out.println(s2);

        // list 转 map
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("Female", 18));
        personList.add(new Person("Male", 19));
        personList.add(new Person("Female", 20));
        personList.add(new Person("Male", 21));

        // 报错 Duplicate key 18，key重复了
        // Map<String, Integer> personMap = personList.stream().collect(Collectors.toMap(pdaitech.Person::getSex, pdaitech.Person::getAge));
        /**
         *  toMap 参数，
         * keyMapper：key的映射函数，
         * valueMapper：value的映射函数，
         * mergeFunction：当key冲突时，调用的合并方法，(相同key处理)
         * mapSupplier：map构造器，在需要返回特定map时使用
         */
        Map<String, Integer> personMap = personList.stream().collect(Collectors.toMap(Person::getSex, Person::getAge,
                (key1, key2) -> key1));
        // {Male=19, Female=18}
        System.out.println(personMap);

        // 如果 map 的value想要设置成 对象本身
        Map<Integer, Person> personMap1 = personList.stream().collect(Collectors.toMap(Person::getAge, person -> person));
        // 或者
        Map<Integer, Person> personMap2 = personList.stream().collect(Collectors.toMap(Person::getAge, Function.identity()));
        // sout -> {18=pdaitech.Person(sex=Female, age=18), 19=pdaitech.Person(sex=Male, age=19), 20=pdaitech.Person(sex=Female, age=20), 21=pdaitech.Person(sex=Male, age=21)}
        System.out.println(personMap1);
        System.out.println(personMap2);

        // map 转 list
        List<Integer> ageList = new ArrayList<>(personMap1.keySet());
        List<Integer> ageStreamList = personMap1.keySet().stream().collect(Collectors.toList());
        // sout -> [18, 19, 20, 21]
        System.out.println(ageList);
        System.out.println(ageStreamList);
        List<String> sexList = new ArrayList<>(personMap.keySet());
        List<Person> personList1 = personMap1.values().stream().collect(Collectors.toList());
        // sout -> [Male, Female]
        // sout -> [pdaitech.Person(sex=Female, age=18), pdaitech.Person(sex=Male, age=19), pdaitech.Person(sex=Female, age=20), pdaitech.Person(sex=Male, age=21)]
        System.out.println(sexList);
        System.out.println(personList1);

        // list 转 array
        Person[] p = personList1.toArray(new Person[personList1.size()]);

        // array 转 list
        // 不支持 add、remove
        List<Person> personList2 = Arrays.asList(p);
        // 支持 add、remove
        List<Person> personList3 = new ArrayList<>(Arrays.asList(p));

        // 排序（升序）
        personList3 = personList3.stream().sorted(Comparator.comparing(Person::getAge)).collect(Collectors.toList());
        // [pdaitech.Person(sex=Female, age=18), pdaitech.Person(sex=Male, age=19), pdaitech.Person(sex=Female, age=20), pdaitech.Person(sex=Male, age=21)]
        System.out.println(personList3);

        // 排序（降序） reversed() 方法
        personList3 = personList2.stream().sorted(Comparator.comparing(Person::getAge).reversed()).collect(Collectors.toList());
        // [pdaitech.Person(sex=Male, age=21), pdaitech.Person(sex=Female, age=20), pdaitech.Person(sex=Male, age=19), pdaitech.Person(sex=Female, age=18)]
        System.out.println(personList3);

        // 交换list中的位置
        Collections.swap(personList3, 1,0);
        // [pdaitech.Person(sex=Female, age=20), pdaitech.Person(sex=Male, age=21), pdaitech.Person(sex=Male, age=19), pdaitech.Person(sex=Female, age=18)]
        System.out.println(personList3);

        // 两个 list 交集
        List<Person> personList4 = personList1.stream().filter(personList3::contains).collect(Collectors.toList());
        // [pdaitech.Person(sex=Female, age=18), pdaitech.Person(sex=Male, age=19), pdaitech.Person(sex=Female, age=20), pdaitech.Person(sex=Male, age=21)]
        System.out.println(personList4);

        // 两个 list 差集
        List<Person> finalPersonList = personList3;
        List<Person> personList5 = personList1.stream().filter(item -> {
            return !finalPersonList.contains(item);
        }).collect(Collectors.toList());
        // personList5[]
        System.out.println("personList5" + personList5);

        // groupingBy
        // 聚合
        Map<String, List<Person>> pm = personList3.stream().collect(Collectors.groupingBy(Person::getSex));
        // {Male=[pdaitech.Person(sex=Male, age=21), pdaitech.Person(sex=Male, age=19)], Female=[pdaitech.Person(sex=Female, age=20), pdaitech.Person(sex=Female, age=18)]}
        System.out.println(pm);
        // 聚合统计 {Male=2, Female=2}
        Map<String, Long> pc = personList3.stream().collect(Collectors.groupingBy(Person::getSex, Collectors.counting()));
        System.out.println(pc);
        // sum {Male=40, Female=38}，还要 maxBy ，minBy 求最大最小值
        Map<String, Integer> ps = personList3.stream().collect(Collectors.groupingBy(Person::getSex, Collectors.summingInt(Person::getAge)));
        System.out.println(ps);
        // mapping {Male=[21, 19], Female=[20, 18]}
        Map<String, List<Integer>> pl = personList3.stream().collect(Collectors.groupingBy(Person::getSex, Collectors.mapping(Person::getAge, Collectors.toList())));
        System.out.println(pl);
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Person {
    public String sex;

    public int age;

}
