package designpatterns.composite.safe;

import java.util.ArrayList;
import java.util.List;

/**
 * @author maxjoker
 * @date 2022-01-28 12:07
 */
public class Composite implements Component {

    /**
     * 用来存储组合对象中包含的子组件对象
     */
    private List<Component> childComponent = new ArrayList<Component>();

    /**
     * 组合对象名字
     */
    private String name;

    public Composite(String name) {
        this.name = name;
    }

    /**
     * 聚集管理方法：增加一个子构建对象
     * @param child
     */
    public void addChild(Component child) {
        childComponent.add(child);
    }

    /**
     * 聚集管理方法：删除一个子构建对象
     * @param index
     */
    public void removeChild(int index) {
        childComponent.remove(index);
    }

    /**
     * 返回所有子构建对象
     * @return
     */
    public List<Component> getChild() {
        return childComponent;
    }

    /**
     * 输出自身结构
     * @param preStr 前缀，主要是按照层级拼接空格，实现向后缩进
     */
    @Override
    public void printStruct(String preStr) {
        // 输出自身
        System.out.println(preStr + " + " + this.name);
        // 如果还有子组件，也需要输出
        if (childComponent != null) {
            // 添加两个空格，表示向后缩进两个空格
            preStr += "  ";
            for (Component c : childComponent) {
                c.printStruct(preStr);
            }
        }
    }
}
