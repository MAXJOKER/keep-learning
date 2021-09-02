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

    public static void main(String[] args) {
        int i = test1();
        System.out.println("main i = " + i);

        int j = test2();
        System.out.println("main j = " + j);
    }
}