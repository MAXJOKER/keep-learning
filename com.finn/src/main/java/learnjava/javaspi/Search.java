package learnjava.javaspi;

import java.util.List;

/**
 * @author maxjoker
 * @date 2022-04-28 15:47
 */
public interface Search {
    /**
     * 文档搜索
     * @param keyword
     * @return
     */
    public List<String> searchDoc(String keyword);
}
