package learnjava.javaspi;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author maxjoker
 * @date 2022-04-28 15:48
 */
public class FileSearchImpl implements Search{
    @Override
    public List<String> searchDoc(String keyword) {
        List<String> result = new ArrayList<>();

        File file = new File("KK:\\test");
        File[] files = file.listFiles();
        if (files == null) {
            return result;
        }

        for (File f : files) {
            if (f.getName().contains(keyword)) {
                result.add(f.getName());

            }
        }

        return result;
    }
}
