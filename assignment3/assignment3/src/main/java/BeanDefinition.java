import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Ryann
 * @create 2023/10/7 - 8:47
 * 表示bean的配置信息
 */
public class BeanDefinition {
    private String id;
    private String className;
    private Map<String, String> properties = new HashMap<>();
    private List<Map<Class<?>, Object>> constructorArgs = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public List<Map<Class<?>, Object>> getConstructorArgs() {
        return constructorArgs;
    }
}
