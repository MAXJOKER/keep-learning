package designpatterns.composite.safe;

/**
 * @author maxjoker
 * @date 2022-01-28 14:58
 */
public class Test {
    public static void main(String[] args) {
        Composite root = new Composite("服装");
        Composite c1 = new Composite("男装");
        Composite c2 = new Composite("女装");

        Leaf l1 = new Leaf("衬衫");
        Leaf l2 = new Leaf("西装裤");
        Leaf l3 = new Leaf("连衣裙");
        Leaf l4 = new Leaf("套装");

        root.addChild(c1);
        root.addChild(c2);
        c1.addChild(l1);
        c1.addChild(l2);
        c2.addChild(l3);
        c2.addChild(l4);

        root.printStruct("");
    }
}
