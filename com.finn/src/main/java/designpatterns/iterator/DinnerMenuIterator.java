package designpatterns.iterator;

/**
 * @author maxjoker
 * @date 2022-01-27 17:47
 */
public class DinnerMenuIterator implements Iterator{
    MenuItem[] items;
    int position = 0;

    public DinnerMenuIterator(MenuItem[] items) {
        this.items = items;
    }

    @Override
    public boolean hasNext() {
        if (position >= items.length || items[position] == null) {
            return false;
        }

        return true;
    }

    @Override
    public Object next() {
        return items[position++];
    }
}
