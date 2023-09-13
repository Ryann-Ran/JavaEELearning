/**
@author Ryann
@create 2023/9/13 - 16:54
*/
package a;

import java.util.ArrayList;
import java.util.List;

public class JavaBasics {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();  // List是一个接口，而ArrayList是List接口的一个实现类
        list.add("Hello");
        list.add("world");

        // 基本数据类型
        // 数值型：整数类型（byte, short, int, long） and 浮点类型（float, double）
        int myInt = 5;
        float myFloat = 5.99f;  // 不加f会报错，因为默认是double
        // 字符型
        char myLetter = 'A';
        char myVar = 65;
        System.out.println(myVar);  // A
        // 布尔型
        boolean myBool = true;

        // 每个基本类型都有对应的封装类
        Float myFloat2 = 5.99f;
        Integer myInt2 = 5;

        // 自动转型
        double myDouble = myInt;
        System.out.println(myDouble + " " + myInt);  // 5.0 5
        // 强制转型
        double myDouble2 = 12.6d;
        int myInt3 = (int)myDouble2;  // 下取整
        System.out.println(myDouble2 + " " + myInt3);  // 12.6 12

        // 引用数据类型：类/接口/数组/枚举/注解
        // String类型
        String str = "hello";
        System.out.println(str.length());
        System.out.println(str.substring(1));  // ello
        System.out.println(str.substring(1, 4));  // ell  前开后闭
        // String-字符/字符串查找
        System.out.println(str.indexOf('l'));  // 2
        System.out.println(str.lastIndexOf('l'));  // 3
        System.out.println(str.indexOf("ll"));  // 2
        System.out.println(str.indexOf("hll"));  // -1
        // String-字符串匹配（只能匹配字符串，不能匹配字符）
        System.out.println(str.contains("hl"));  // false
        System.out.println(str.startsWith("ell"));  // false
        System.out.println(str.endsWith("llo"));  // true
        // System.out.println(str.matches(String regex));  // 正则表达式

        // For-each Loop

    }
}
