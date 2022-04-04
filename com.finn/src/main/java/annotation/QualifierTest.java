package annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


/**
 * @author maxjoker
 * @date 2022-04-02 20:25
 *
 * @Qualifier 注解测试
 *
 */
public class QualifierTest {
    @Autowired
    @Qualifier("fooFormatter")
    private Formatter formatter;

    public void test() {
        formatter.format();
    }
}

interface Formatter {
    String format();
}

@Component("fooFormatter")
class FooFormatter implements Formatter {
    @Override
    public String format() {
        return "foo";
    }
}

@Component("barFormatter")
class BarFormatter implements Formatter {
    @Override
    public String format() {
        return "bar";
    }
}
