import edu.whu.PropertyReader;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Ryann
 * @create 2023/9/21 - 12:03
 */
public class PropertyReaderTest {
    // 测试基于配置文件是否成功创建对象
    @Test
    public void testCreation() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        assertNotNull(new PropertyReader().createObjectByProperty("/myapp.properties"));
    }

    // 测试类不存在的异常情况
    @Test
    public void testClassNotFound() {
        assertThrows (ClassNotFoundException.class, () -> {
            new PropertyReader().createObjectByProperty("/exception.properties");
        });
    }
}
