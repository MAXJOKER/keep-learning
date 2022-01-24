package designpatterns.singleton;

/**
 * 使用枚举来实现单实例控制会更加简洁，而且无偿地提供了序列化机制，并由JVM从根本上提供保障，绝对防止多次实例化，是更简洁、高效、安全的实现单例的方式。
 */
public enum SingletonEnum {

    // 定义一个枚举的元素，它就代表了Singleton的一个实例
    uniqueInstance;

    /**
     * 单例操作
     */
    public void singletonOperation() {
        // 具体功能
    }
}
