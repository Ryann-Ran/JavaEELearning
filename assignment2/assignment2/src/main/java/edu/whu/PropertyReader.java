package edu.whu;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Ryann
 * @create 2023/9/21 - 10:44
 */
public class PropertyReader {
    // 基于配置文件创建对象
    public Object createObjectByProperty(String propPath) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        // 读取classpath下的属性文件
        Properties props = new Properties();
        try (InputStream input = PropertyReader.class.getResourceAsStream(propPath)) {  // 从classpath读取资源
            if (input == null) {
                return null;
            }
            props.load(input);  // 读入键值对
        } catch (IOException e) {
            System.out.println("Read properties error!");
            return null;
        }

        // 根据配置的类名创建对象
        String className = props.getProperty("bootstrapClass");
        Class myClass = Class.forName(className);
        Object ob = myClass.newInstance();
        return ob;
    }
}
