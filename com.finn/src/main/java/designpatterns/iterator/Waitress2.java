package designpatterns.iterator;

/**
 * @author maxjoker
 * @date 2022-01-27 17:47
 */
public class Waitress2 {
    Menu menu;

    public Waitress2(Menu menu) {
        this.menu = menu;
    }

    public void printMenu() {
        Iterator menuIterator = menu.createIterator();
        printMenu(menuIterator);
    }

    public void printMenu(Iterator iterator) {
        while (iterator.hasNext()) {
            MenuItem item = (MenuItem) iterator.next();
            System.out.println("=============================");
            System.out.println(item.getName());
            System.out.println(item.getDescription());
            System.out.println(item.isVegetarian());
            System.out.println(item.getPrice());
            System.out.println("=============================");
        }
    }
}
