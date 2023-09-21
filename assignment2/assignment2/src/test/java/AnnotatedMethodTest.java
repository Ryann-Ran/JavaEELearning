import edu.whu.Main;
import edu.whu.MyClass;
import edu.whu.MyClass2;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import static org.junit.Assert.assertEquals;

/**
 * @author Ryann
 * @create 2023/9/21 - 12:26
 */
public class AnnotatedMethodTest {
    // 检测是否成功调用被InitMethod注解的方法
    @Test
    public void testCall() throws InvocationTargetException, IllegalAccessException, NoSuchFieldException {
        Object ob = Main.callAnnotatedMethod(new MyClass());
        Field myField = ob.getClass().getDeclaredField("myVal");  // 获取属性
        myField.setAccessible(true);
        assertEquals(135, myField.get(ob));

        Object ob2 = Main.callAnnotatedMethod(new MyClass2());
        Field myField2 = ob2.getClass().getDeclaredField("myVal");
        myField2.setAccessible(true);
        assertEquals(159, myField2.get(ob2));
    }
}
