package edu.whu;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Ryann
 * @create 2023/9/21 - 10:37
 */
public class Main {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchFieldException, ClassNotFoundException {
        // 基于配置文件创建对象
        Object ob = new PropertyReader().createObjectByProperty("/myapp.properties");

        // 如果创建的对象的类方法上有被@InitMethod注解的方法，则调用该方法
        callAnnotatedMethod(ob);
    }

    public static Object callAnnotatedMethod(Object ob) throws InvocationTargetException, IllegalAccessException {
        for (Method method: ob.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(InitMethod.class)) {
                method.invoke(ob);
            }
        }
        return ob;
    }
}
