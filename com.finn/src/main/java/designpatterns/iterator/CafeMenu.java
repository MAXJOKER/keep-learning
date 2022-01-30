package designpatterns.iterator;

import java.util.HashMap;

/**
 * @author maxjoker
 * @date 2022-01-27 17:47
 */
public class CafeMenu implements Menu{
    HashMap<String, MenuItem> items = new HashMap<String, MenuItem>();

    public CafeMenu() {
        addItem("美式", "加冰", true, 28);
    }

    public void addItem(String name, String description, boolean vegetarian, double price) {
        MenuItem menuItem = new MenuItem(name, description, vegetarian, price);
        items.put(name, menuItem);
    }

    @Override
    public Iterator createIterator() {
        return new CafeMenuIterator(items);
    }
}
