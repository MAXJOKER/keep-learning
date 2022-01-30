package designpatterns.iterator;

/**
 * @author maxjoker
 * @date 2022-01-27 17:47
 */
public class Waitress {
    Menu dinnerMenu;
    Menu pancakeHouseMenu;
    Menu cafeMenu;

    public Waitress(DinnerMenu dinnerMenu, PancakeHouseMenu pancakeHouseMenu, CafeMenu cafeMenu) {
        this.dinnerMenu = dinnerMenu;
        this.pancakeHouseMenu = pancakeHouseMenu;
        this.cafeMenu = cafeMenu;
    }

    public void printMenu() {
        Iterator dinnerIterator = dinnerMenu.createIterator();
        Iterator pancakeHouseIterator = pancakeHouseMenu.createIterator();
        Iterator cafeIterator = cafeMenu.createIterator();
        printMenu(dinnerIterator);
        printMenu(pancakeHouseIterator);
        printMenu(cafeIterator);
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
