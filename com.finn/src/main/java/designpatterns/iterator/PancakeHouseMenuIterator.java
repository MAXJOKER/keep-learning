package designpatterns.iterator;

import java.util.ArrayList;

/**
 * @author maxjoker
 * @date 2022-01-27 17:47
 */
public class PancakeHouseMenuIterator implements Iterator{
    ArrayList<MenuItem> items;
    int position = 0;

    public PancakeHouseMenuIterator(ArrayList<MenuItem> items) {
        this.items = items;
    }

    @Override
    public boolean hasNext() {
        if (position >= items.size() || items.get(position) == null) {
            return false;
        }

        return true;
    }

    @Override
    public Object next() {
        MenuItem menuItem = items.get(position);
        position++;
        return menuItem;
    }
}
