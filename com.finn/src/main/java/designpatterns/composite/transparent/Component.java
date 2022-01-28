package designpatterns.composite.transparent;

import java.util.List;

/**
 * @author maxjoker
 * @date 2022-01-28 15:11
 */
public abstract class Component {
    /**
     * 输出自身结构
     * @param preStr
     */
    public abstract void printStruct(String preStr);

    /**
     * 增加子对象
     * @param component
     */
    public void addChild(Component component) {
        /**
         * 缺省实现，抛出异常，因为叶子对象没有此功能
         * 或者子组件没有实现这个功能
         */
        throw new UnsupportedOperationException();
    }

    public void removeChild(int index) {
        /**
         * 缺省实现，抛出异常，因为叶子对象没有此功能
         * 或者子组件没有实现这个功能
         */
        throw new UnsupportedOperationException();
    }

    public List<Component> getChild() {
        /**
         * 缺省实现，抛出异常，因为叶子对象没有此功能
         * 或者子组件没有实现这个功能
         */
        throw new UnsupportedOperationException();
    }
}
