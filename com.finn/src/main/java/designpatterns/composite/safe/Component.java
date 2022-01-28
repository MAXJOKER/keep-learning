package designpatterns.composite.safe;

/**
 * @author maxjoker
 * @date 2022-01-28 12:06
 * 抽象构建角色类
 */
public interface Component {
    /**
     * 输出自身的结构
     * @param preStr
     */
    public void printStruct(String preStr);
}
