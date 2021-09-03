import java.util.List;
import java.util.ArrayList;

public class CaseTryCatchFinally {

    public static Integer test1() {
        int i = 1;
        try {
            i++;
            System.out.println("try, i= " + i);
        } catch (Exception e) {
            i++;
            System.out.println("catch, i= " + i);
        } finally {
            i = 3;
            System.out.println("finally, i= " + i);
        }

        return i;
    }

    public static Integer test2() {
        int j = 1;
        try {
            j++;
            System.out.println("try, j = " + j);
            return j;
        } catch (Exception e) {
            j++;
            System.out.println("catch, j = " + j);
            return j;
        } finally {
            j = 3;
            System.out.println("finally, j = " + j);
        }
    }

    public static List<String> test3() {
        List<String> n = new ArrayList<>();
        try {
            n.add("try");
            System.out.println("try, n = " + n.toString());
            return n;
        } catch (Exception e) {
            n.add("catch");
            System.out.println("catch, n = " + n.toString());
            return n;
        } finally {
            n.add("finally");
            System.out.println("finally, n = " + n.toString());
        }
    }

    public static Integer test4() {
        int k = 1;
        try {
            k++;
            // k = k / 0;
            System.out.println("try, k = " + k);
            return k;
        } catch (Exception e) {
            k++;
            System.out.println("catch, k = " + k);
            return k;
        } finally {
            k = 6;
            System.out.println("finally, k = " + k);
            return k;
        }
    }

    public static void main(String[] args) {
        int i = test1();
        System.out.println("main i = " + i);

        int j = test2();
        System.out.println("main j = " + j);

        List<String> n = test3();
        System.out.println("main n = " + n.toString());

        int k = test4();
        System.out.println("main k = " + k);
    }
}