package learnjava.javaspi;

import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;

/**
 * @author maxjoker
 * @date 2022-04-28 15:58
 *
 * 测试case
 *
 */
public class TestCase {
    public static void main(String[] args) {
        ServiceLoader<Search> s = ServiceLoader.load(Search.class);
        Iterator<Search> iterator = s.iterator();
        while (iterator.hasNext()) {
            Search search = iterator.next();
            List<String> searchResult = search.searchDoc("l");
            System.out.println(searchResult);
        }
    }
}
