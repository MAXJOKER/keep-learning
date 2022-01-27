package designpatterns.iterator;

/**
 * @author maxjoker
 * @date 2022-01-27 17:47
 */
public class Test {
    public static void main(String[] args) {
//        Waitress waitress = new Waitress(new DinnerMenu(), new PancakeHouseMenu(), new CafeMenu());
//        waitress.printMenu();

        Waitress2 waitress2 = new Waitress2(new CafeMenu());
        waitress2.printMenu();
    }
}
