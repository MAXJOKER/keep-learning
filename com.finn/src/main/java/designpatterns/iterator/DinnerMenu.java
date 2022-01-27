package designpatterns.iterator;

/**
 * @author maxjoker
 * @date 2022-01-27 17:47
 */
public class DinnerMenu implements Menu{
    static final int MAX_ITEMS = 6;
    int numbersOfItem = 0;
    MenuItem[] items;

    public DinnerMenu() {
        items = new MenuItem[MAX_ITEMS];
        addItem("意面", "很好吃的意面", true, 88);
        addItem("猛龙过江", "非常生猛", false, 188);
        addItem("年年有余", "钱多多", false, 198);
        addItem("恭喜发财", "你要发达啦", false, 888);
    }

    public void addItem(String name, String description, boolean vegetarian, double price) {
        MenuItem menuItem = new MenuItem(name, description, vegetarian, price);
        if (numbersOfItem >= MAX_ITEMS) {
            System.out.println("can't add to menu");
            return ;
        }

        items[numbersOfItem++] = menuItem;
    }

    @Override
    public Iterator createIterator() {
        return new DinnerMenuIterator(items);
    }
}
