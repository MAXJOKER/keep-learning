package testcase;

import java.util.ArrayList;
import java.util.List;

/**
 * @author maxjoker
 * @date 2022-01-20 21:08
 */
public class HeapDumpTest {
    public static void main(String[] args) throws InterruptedException {
        int size = 1024 * 1024 * 8;
        List<byte[]> list = new ArrayList<>();
        for (int i = 0; i < 1024; i++) {
            Thread.sleep(1000);
            list.add(new byte[size]);
        }
    }
}
