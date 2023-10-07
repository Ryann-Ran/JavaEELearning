import org.junit.jupiter.api.Test;
import service.BookService;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Ryann
 * @create 2023/10/7 - 16:52
 */
public class MyApplicationContextTest {
    /**
     *  测试属性注入的正常情况
     */
    @Test
    public void testSetterInject() throws MyIOCException{
        MyApplicationContext ctx = new MyApplicationContext("setterInjectTest.xml");
        BookService bookService = (BookService) ctx.getBean("bookService");  //  获取bean
        assertNotNull(bookService);
    }

    /**
     *  测试读取配置文件的各种错误情况
     */
    @Test
    public void testLoadPropFile(){
        // 测试不存在的配置文件
        MyIOCException exception = assertThrows(MyIOCException.class,()->{
            MyApplicationContext ctx = new MyApplicationContext("unknown.xml");
        });
        assertEquals(MyIOCException.ErrorType.FILE_NOTFOUND,exception.getErrorType());

        // 测试不合法的配置文件
        exception=assertThrows(MyIOCException.class,()->{
            MyApplicationContext ctx = new MyApplicationContext("illegalConfigFile.xml");
        });
        assertEquals(MyIOCException.ErrorType.CONFIG_READ_ERROR,exception.getErrorType());
    }

    /**
     *  测试创建对象的各种错误
     */
    @Test
    public void testCreatObject() {
        // 测试不存在类错误
        MyIOCException exception=assertThrows(MyIOCException.class,()->{
            MyApplicationContext ctx = new MyApplicationContext("classNotFound.xml");
        });
        assertEquals(MyIOCException.ErrorType.CLASS_NOTFOUND,exception.getErrorType());

        // 测试抽象类
        exception=assertThrows(MyIOCException.class,()->{
            MyApplicationContext ctx = new MyApplicationContext("abstractClass.xml");
        });
        assertEquals(MyIOCException.ErrorType.CREATE_OBJECT_ERROR,exception.getErrorType());

        // 测试构造函数为私有的情况
        exception=assertThrows(MyIOCException.class,()->{
            MyApplicationContext ctx = new MyApplicationContext("privateConstructor.xml");
        });
        assertEquals(MyIOCException.ErrorType.CREATE_OBJECT_ERROR,exception.getErrorType());

        // 测试不存在无参构造函数错误
        exception=assertThrows(MyIOCException.class,()->{
            MyApplicationContext ctx = new MyApplicationContext("withoutParamlessConstructor.xml");
        });
        assertEquals(MyIOCException.ErrorType.CREATE_OBJECT_ERROR,exception.getErrorType());
    }

    /**
     *  测试属性注入的错误情况
     */
    @Test
    public void testSetterInjectException () {
        // 测试类的属性不存在的情况
        MyIOCException exception=assertThrows(MyIOCException.class,()->{
            MyApplicationContext ctx = new MyApplicationContext("propNotFound.xml");
        });
        assertEquals(MyIOCException.ErrorType.PROP_NOTFOUND,exception.getErrorType());
    }

    /**
     *  测试构造函数注入的正常情况
     */
    @Test
    public void testConstructorInject() throws MyIOCException{
        MyApplicationContext ctx = new MyApplicationContext("constructorInjectTest.xml");
        BookService bookService = (BookService) ctx.getBean("bookService");  //  获取bean
        assertNotNull(bookService);
    }

    /**
     *  测试构造函数注入的错误情况
     */
    @Test
    public void testConstructorInjectException() throws MyIOCException{
        // 测试构造函数参数类型与配置定义不匹配的情况
        MyIOCException exception=assertThrows(MyIOCException.class,()->{
            MyApplicationContext ctx = new MyApplicationContext("propTypeError.xml");
        });
        assertEquals(MyIOCException.ErrorType.CREATE_OBJECT_ERROR,exception.getErrorType());
    }
}
