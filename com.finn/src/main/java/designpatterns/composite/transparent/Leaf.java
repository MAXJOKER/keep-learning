package designpatterns.composite.transparent;

/**
 * @author maxjoker
 * @date 2022-01-28 12:32
 */
public class Leaf extends Component {

    /**
     * 叶子对象名字
     */
    private String name;

    /**
     * @param name
     */
    public Leaf(String name) {
        this.name = name;
    }

    /**
     * 输出叶子对象结构，叶子对象没有子对象，直接输出名字
     * @param preStr
     */
    @Override
    public void printStruct(String preStr) {
        System.out.println(preStr + " - " + name);
    }
}
