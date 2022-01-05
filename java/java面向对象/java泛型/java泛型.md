### 1、为什么需要泛型 ###
JDK5 引入了泛型机制。 为什么需要泛型？看以下例子
```
public clss Demo {
    public static void main(String[] args) {
        List list = new ArrayList<>();
        list.add(1);
        list.add("2");
        list.add(new ArrayList<>());
        
        Object obj1 = list.get(1); // 运行正常
        int int2 = (int) list.get(2); // 编译异常
    }
}
```