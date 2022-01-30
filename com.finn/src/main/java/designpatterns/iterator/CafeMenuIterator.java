package designpatterns.iterator;

import java.util.HashMap;

/**
 * @author maxjoker
 * @date 2022-01-27 17:47
 */
public class CafeMenuIterator implements Iterator{
    HashMap<String, MenuItem> items;

    public CafeMenuIterator(HashMap<String, MenuItem> items) {
        this.items = items;
    }

    @Override
    public boolean hasNext() {
        return items.values().iterator().hasNext();
    }

    @Override
    public Object next() {
        return items.values().iterator().next();
    }
}
