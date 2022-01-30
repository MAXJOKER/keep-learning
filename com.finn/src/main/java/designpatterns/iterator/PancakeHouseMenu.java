package designpatterns.iterator;

import java.util.ArrayList;

/**
 * @author maxjoker
 * @date 2022-01-27 17:47
 */
public class PancakeHouseMenu implements Menu{
    ArrayList<MenuItem> items;

    public PancakeHouseMenu() {
        items = new ArrayList<>();
        addItem("手抓饼", "纯手抓饼", true, 8);
        addItem("手抓饼加肠", "加火腿肠", false, 10);
        addItem("手抓饼加肠蛋", "加火腿肠加蛋", false, 13);
        addItem("手抓饼全套", "啥都有，一个管饱", false, 20);
    }

    public void addItem(String name, String description, boolean vegetarian, double price) {
        MenuItem menuItem = new MenuItem(name, description, vegetarian, price);
        items.add(menuItem);
    }

    @Override
    public Iterator createIterator() {
        return new PancakeHouseMenuIterator(items);
    }
}
