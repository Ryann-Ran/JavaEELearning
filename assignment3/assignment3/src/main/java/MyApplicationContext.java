import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * @author Ryann
 * @create 2023/10/7 - 8:44
 */
public class MyApplicationContext {
    // 存放bean
    private Map<String, Object> beans = new HashMap<>();
    // bean的配置信息
    private Map<String, BeanDefinition> beanDefinitions = new HashMap<>();
    // 要new对象，否则进行put操作时会报空指针异常

    public MyApplicationContext(String configFileName) throws MyIOCException {
        readConfigFile(configFileName);
        createBeans();
        injectDependenciesByName();
    }

    // 使用Dom4j解析Spring的配置文件，读取bean配置信息
    public void readConfigFile(String configFileName) throws MyIOCException {
        // 创建解析器对象
        SAXReader saxReader = new SAXReader();
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(configFileName);  // 从classPath路径加载资源，获取资源输入流
            if (inputStream == null) {
                throw new MyIOCException(MyIOCException.ErrorType.FILE_NOTFOUND, "加载配置文件出错，请检查文件是否存在");
            }
            Document document = saxReader.read(inputStream);

            Element rootElement = document.getRootElement();  // 获取根节点
            List<Element> beanElements = rootElement.elements("bean");  // 获取bean标签列表

            // 挨个读取bean的配置信息
            for (Element beanElement : beanElements) {
                BeanDefinition beanDefinition = new BeanDefinition();

                beanDefinition.setId(beanElement.attributeValue("id"));
                beanDefinition.setClassName(beanElement.attributeValue("class"));

                // 属性注入
                List<Element> propertyElements = beanElement.elements("property");  // 获取当前bean标签的属性标签列表
                for (Element propertyElement : propertyElements) {
                    String propertyName = propertyElement.attributeValue("name");
                    String propertyRef = propertyElement.attributeValue("ref");
                    beanDefinition.getProperties().put(propertyName, propertyRef);
                }

                // 构造函数注入
                List<Element> constructorArgElements = beanElement.elements("constructor-arg");
                for (Element argElement : constructorArgElements) {
                    // 仅考虑参数为String和基本数据类型的情况
                    if (argElement.attributeValue("type") == null) {  // 构造函数参数为String类型
                        Map<Class<?>, Object> arg = new HashMap<>();
                        arg.put(String.class, argElement.attributeValue("value"));
                        beanDefinition.getConstructorArgs().add(arg);
                    } else if (argElement.attributeValue("value") != null) {  // 构造函数参数为基本数据类型
                        String argType = argElement.attributeValue("type");
                        String argValue = argElement.attributeValue("value");
                        Map<Class<?>, Object> arg = new HashMap<>();
                        switch (argType) {
                            case "int":
                                arg.put(int.class, Integer.parseInt(argValue));
                                beanDefinition.getConstructorArgs().add(arg);
                                break;
                            case "float":
                                arg.put(float.class, Float.parseFloat(argValue));
                                beanDefinition.getConstructorArgs().add(arg);
                                break;
                            case "double":
                                arg.put(double.class, Double.parseDouble(argValue));
                                beanDefinition.getConstructorArgs().add(arg);
                                break;
                            case "byte":
                                arg.put(byte.class, Byte.parseByte(argValue));
                                beanDefinition.getConstructorArgs().add(arg);
                                break;
                            case "short":
                                arg.put(short.class, Short.parseShort(argValue));
                                beanDefinition.getConstructorArgs().add(arg);
                                break;
                            case "long":
                                arg.put(long.class, Long.parseLong(argValue));
                                beanDefinition.getConstructorArgs().add(arg);
                                break;
                            case "boolean":
                                arg.put(boolean.class, Boolean.parseBoolean(argValue));
                                beanDefinition.getConstructorArgs().add(arg);
                                break;
                            case "char":
                                arg.put(char.class, argValue.charAt(0));
                                beanDefinition.getConstructorArgs().add(arg);
                                break;
                            default:
                                arg.put(String.class, argValue);
                                beanDefinition.getConstructorArgs().add(arg);
                        }
                    }
                }
                beanDefinitions.put(beanDefinition.getId(), beanDefinition);
            }
        } catch (DocumentException e) {
            throw new MyIOCException(MyIOCException.ErrorType.CONFIG_READ_ERROR, "配置文件读取失败");
        }
    }

    // 根据Bean配置信息，使用反射创建Bean，放入beans列表
    public void createBeans() throws MyIOCException {
        for (String beanId: beanDefinitions.keySet()) {
            try {
                BeanDefinition beanDefinition = beanDefinitions.get(beanId);
                Class<?> beanClass = Class.forName(beanDefinition.getClassName());  // 根据类名反射获取class

                // 获取构造函数参数
                List<Map<Class<?>, Object>> constructorArgs = beanDefinition.getConstructorArgs();
                Class<?>[] constructorArgTypes = new Class<?>[constructorArgs.size()];
                Object[] constructorArgObjects = new Object[constructorArgs.size()];
                for (int i = 0; i < constructorArgs.size(); i++) {
                    Iterator iter = constructorArgs.get(i).keySet().iterator();
                    Class<?> argClass = (Class<?>) iter.next();
                    constructorArgTypes[i] = argClass;
                    constructorArgObjects[i] = constructorArgs.get(i).get(argClass);
                }

                // 创建bean对象
                Object beanObject = null;
                if (constructorArgs.size() == 0) {  // 属性注入
                    beanObject = beanClass.newInstance();  // 创建bean对象
                } else {  // 构造函数注入
                    Constructor<?> constructor = beanClass.getDeclaredConstructor(constructorArgTypes);  // 获取有参构造方法
                    beanObject = constructor.newInstance(constructorArgObjects);
                }
                beans.put(beanId, beanObject);
            } catch (ClassNotFoundException e) {
                throw new MyIOCException(MyIOCException.ErrorType.CLASS_NOTFOUND, "配置文件中标注的类不存在");
            } catch (InstantiationException e) {
                throw new MyIOCException(MyIOCException.ErrorType.CREATE_OBJECT_ERROR, "创建对象失败：请检查是否有无参构造函数");
            } catch (IllegalAccessException e) {
                throw new MyIOCException(MyIOCException.ErrorType.CREATE_OBJECT_ERROR, "创建对象失败：类不能是抽象类，构造函数不能为私有."+e.getMessage());
            } catch (InvocationTargetException | NoSuchMethodException e) {
                throw new MyIOCException(MyIOCException.ErrorType.CREATE_OBJECT_ERROR, "创建对象失败：请检查有参构造函数");
            }
        }
    }

    // 根据Bean配置信息，使用反射，对每个Bean的属性进行依赖注入
    // 注入方式1：根据name注入
    public void injectDependenciesByName() throws MyIOCException {
        for (String beanId: beanDefinitions.keySet()) {  // 遍历bean配置信息列表
            try {
                BeanDefinition beanDefinition = beanDefinitions.get(beanId);  // 获取对应bean的配置信息
                Object beanObject = beans.get(beanId);  // 获取对应bean对象
                Class<?> beanClass = beanObject.getClass();  // 获取对应bean的class

                // 根据bean配置信息中的properties，实现属性依赖注入
                for (String propertyName: beanDefinition.getProperties().keySet()) {
                    String propertyRef = beanDefinition.getProperties().get(propertyName);
                    Field field = beanClass.getDeclaredField(propertyName);  // 获取属性对应的字段
                    field.setAccessible(true);
                    field.set(beanObject, getBean(propertyRef));  // 设置属性值
                }
            } catch (NoSuchFieldException e) {
                throw new MyIOCException(MyIOCException.ErrorType.PROP_NOTFOUND, "该bean对应的类的属性不存在");
            } catch (IllegalAccessException e) {
                throw new MyIOCException(MyIOCException.ErrorType.SET_PROP_ERROR, "该bean对应的类的属性无法访问");
            }
        }
    }

    public Object getBean(String beanId) {
        return beans.get(beanId);
    }
}
